/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.controller.study
 * Type:    StudyController
 * Author:  hefengwen
 * Date:    2016-12-18 20:42:59
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.controller.study;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yckj.school.annotation.SchoolValidate;
import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.constant.DbConstants;
import com.yckj.school.domain.CourseVo;
import com.yckj.school.service.CourseService;
import com.yckj.school.service.DictService;
import com.yckj.school.service.MajorService;
import com.yckj.school.service.ScoreService;
import com.yckj.school.service.UserService;
import com.yckj.school.service.dto.CourseDto;
import com.yckj.school.service.dto.CoursePageDto;
import com.yckj.school.service.dto.MajorDto;
import com.yckj.school.service.dto.ScoreDto;
import com.yckj.school.service.dto.UserDto;

/**
 * @author hefengwen
 */
@Controller
@RequestMapping({ "/study" })
public class StudyController {
    private static final Logger logger = LoggerFactory.getLogger(StudyController.class);
    @Autowired
    private CourseService courseService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private UserService userService;
    @Autowired
    private DictService dictService;
    @Autowired
    private ScoreService scoreService;
    /**
     * 课程信息
     */
    @RequestMapping({ "/studyInfo" })
    @SchoolValidate(ignoreSession=true)
    public String studyInfo(HttpSession session, Model model,CourseVo course) {
        logger.info("StudyController studyInfo start ... ...");
        CoursePageDto dto = new CoursePageDto();
        course.setStatue(Constants.YES);
        if(course.getOrderByColumn()==null){
            course.setOrderByColumn(DbConstants.COURSE_ID);
            course.setOrderType(Constants.ORDER_ASC);
        }
        dto.setCurPage(course.getCurPage());
        dto.setPageCount(8);
        dto.setCondition(course);
        dto = courseService.listAllCourseByPage(dto);
        
        model.addAttribute(Constants.DOMAIN, course);
        model.addAttribute(Constants.RESULT, dto);
        
        List<MajorDto> majors = majorService.queryAllMajor();
        model.addAttribute("majors", majors);
        
        model.addAttribute("courseTypes", dictService.getDictByType(Constants.DICT_COURSE_TYPE));
        model.addAttribute("orderByColumns", dictService.getDictByType(Constants.DICT_COURSE_ORDER_TYPE));
        return "study/view/studyInfo";
    }
    @RequestMapping({ "/courseInfo/{courseId}" })
    @SchoolValidate(ignoreSession=true)
    public String courseInfo(HttpSession session, Model model,@PathVariable long courseId) {
        
        CourseDto cdto = courseService.viewCourse(courseId);
        model.addAttribute("course",cdto);
        //相关课程
        CoursePageDto ldto = new CoursePageDto();
        CourseDto course = new CourseDto();
        course.setStatue(Constants.YES);
        course.setMajorId(cdto.getMajorId());
        course.setType(cdto.getType());
        if(course.getOrderByColumn()==null){
            course.setOrderByColumn(DbConstants.COURSE_ID);
            course.setOrderType(Constants.ORDER_ASC);
        }
        ldto.setNeedTotal(false);
        ldto.setPageCount(5);
        ldto.setCondition(course);
        ldto = courseService.listAllCourseByPage(ldto);
        model.addAttribute("likesCourses",ldto);
        
        UserDto udto = userService.queryUser(cdto.getUserId());
        model.addAttribute("teacher",udto);
        
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        if(user!=null){
            ScoreDto sd = new ScoreDto();
            sd.setUserId(user.getUserId());
            sd.setRefType(Constants.REF_TYPE_COURSE);
            sd.setRefId(courseId);
            sd = scoreService.queryScore(sd);
            model.addAttribute("score",sd);
        }
        
        return "study/view/courseInfo";
    }
}
