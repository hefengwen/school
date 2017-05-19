/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.controller.index
 * Type:    IndexController
 * Author:  hefengwen
 * Date:    2017-01-01 17:05:23
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.controller.index;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yckj.school.annotation.SchoolValidate;
import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.constant.DbConstants;
import com.yckj.school.domain.CourseVo;
import com.yckj.school.service.CourseService;
import com.yckj.school.service.NoticeService;
import com.yckj.school.service.ResourceService;
import com.yckj.school.service.UserService;
import com.yckj.school.service.dto.CoursePageDto;
import com.yckj.school.service.dto.NoticeDto;
import com.yckj.school.service.dto.NoticePageDto;
import com.yckj.school.service.dto.ResourceDto;
import com.yckj.school.service.dto.ResourcePageDto;
import com.yckj.school.service.dto.UserDto;
import com.yckj.school.service.dto.UserPageDto;

/**
 * @author hefengwen
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private CourseService courseService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;
    /**
     * 首页
     */
    @RequestMapping("/index")
    @SchoolValidate(ignoreSession=true)
    public String index(Model model){
        logger.info("IndexController index start ... ...");
        //专业课程
        CourseVo course = new CourseVo();
        CoursePageDto cdto = new CoursePageDto();
        course.setStatue(Constants.YES);
        cdto.setPageCount(8);
        cdto.setCondition(course);
        cdto.setNeedTotal(false);
        cdto = courseService.listAllCourseByPage(cdto);
        model.addAttribute("courses",cdto);
        //课程标准
        ResourcePageDto sdto = new ResourcePageDto();
        ResourceDto resource = new ResourceDto();
        resource.setStatue(Constants.YES);
        resource.setType(1);
        sdto.setPageCount(5);
        sdto.setCondition(resource);
        sdto = resourceService.listAllResourceByPage(sdto);
        model.addAttribute("standards",sdto);
        model.addAttribute("standardsType",1);
        
        //电子教案
        ResourcePageDto ddto = new ResourcePageDto();
        resource = new ResourceDto();
        resource.setStatue(Constants.YES);
        resource.setType(2);
        ddto.setPageCount(5);
        ddto.setCondition(resource);
        ddto = resourceService.listAllResourceByPage(ddto);
        model.addAttribute("books",ddto);
        model.addAttribute("booksType",2);
        
        //系统公告
        NoticeDto systemNotice = new NoticeDto();
        NoticePageDto systemDto = new NoticePageDto();
        systemNotice.setStatue(Constants.YES);
        systemNotice.setType(Constants.NOTICE_SYSTEM_TYPE);
        systemNotice.setOrderByColumn(DbConstants.CREATE_TIME);
        systemNotice.setOrderType(Constants.ORDER_DESC);
        systemDto.setPageCount(5);
        systemDto.setCondition(systemNotice);
        systemDto.setNeedTotal(false);
        systemDto = noticeService.listAllNoticeByPage(systemDto);
        model.addAttribute("systemNotices",systemDto);
        //课程公告
        NoticeDto courseNotice = new NoticeDto();
        NoticePageDto courseDto = new NoticePageDto();
        courseNotice.setStatue(Constants.YES);
        courseNotice.setType(Constants.NOTICE_COURSE_TYPE);
        courseNotice.setOrderByColumn(DbConstants.CREATE_TIME);
        courseNotice.setOrderType(Constants.ORDER_DESC);
        courseDto.setPageCount(5);
        courseDto.setCondition(courseNotice);
        courseDto.setNeedTotal(false);
        courseDto = noticeService.listAllNoticeByPage(courseDto);
        model.addAttribute("courseNotices",courseDto);
        //用户排行榜
        UserDto user = new UserDto();
        UserPageDto udto = new UserPageDto();
        user.setStatue(Constants.YES);
        user.setType(Constants.TEACHER);
        user.setOrderByColumn(DbConstants.SCORE);
        user.setOrderType(Constants.ORDER_DESC);
        udto.setPageCount(5);
        udto.setCondition(user);
        udto.setNeedTotal(false);
        
        udto = userService.listAllUserByPage(udto);
        model.addAttribute("teachers",udto);
        
        return "index/view/index";
    }
    
}
