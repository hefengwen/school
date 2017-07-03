/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.controller.qa
 * Type:    QuestionController
 * Author:  hefengwen
 * Date:    2017-05-31 14:54:55
 *
 * Copyright (c) 2017 yckj. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.controller.qa;

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
import com.yckj.school.domain.QuestionVo;
import com.yckj.school.service.CourseService;
import com.yckj.school.service.QuestionService;
import com.yckj.school.service.dto.CourseDto;
import com.yckj.school.service.dto.CoursePageDto;
import com.yckj.school.service.dto.QuestionDto;
import com.yckj.school.service.dto.QuestionPageDto;
import com.yckj.school.service.dto.UserDto;

/**
 * @author hefengwen
 */
@Controller
@RequestMapping({ "/qa" })
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CourseService courseService;
    
    /**
     * 提问信息
     */
    @RequestMapping({ "/questionInfo" })
    @SchoolValidate(ignoreSession=true)
    public String questionInfo(HttpSession session, Model model,QuestionVo question) {
        logger.info("QuestionController questionInfo start ... ...");
        
        QuestionPageDto dto = new QuestionPageDto();
        question.setStatue(Constants.YES);
        dto.setCurPage(question.getCurPage());
        dto.setPageCount(question.getPageCount());
        dto.setCondition(question);
        dto = questionService.listAllQuestionByPage(dto);
        model.addAttribute(Constants.DOMAIN, question);
        model.addAttribute(Constants.RESULT, dto);
        
        return "qa/view/questionInfo";
    }
    /**
     * 提问详情
     */
    @RequestMapping({ "/questionDetailInfo/{quesId}" })
    @SchoolValidate(ignoreSession=true)
    public String questionDetailInfo(HttpSession session, Model model,@PathVariable Long quesId) {
        logger.info("QuestionController questionDetailInfo start ... ...");
        
        QuestionDto dto = questionService.queryQuestionById(quesId);
        model.addAttribute(Constants.RESULT, dto);
        
        return "qa/view/questionDetailInfo";
    }
    /**
     * 新增提问视图
     */
    @RequestMapping({ "/questionInfoAddView" })
    public String questionInfoAddView(HttpSession session, Model model) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("QuestionController questionInfoAddView start , current user is " + user.getUserId());
        
        CoursePageDto dto = new CoursePageDto();
        CourseDto condition = new CourseDto();
        dto.setCondition(condition);
        dto.setPageCount(Integer.MAX_VALUE);
        dto = courseService.listAllCourseByPage(dto);
        model.addAttribute("courses", dto.getCourseList());
        
        return "qa/view/questionInfoAdd";
    }
    /**
     * 新增提问
     */
    @RequestMapping({ "/questionInfoAdd" })
    public String questionInfoAdd(HttpSession session, Model model,QuestionVo vo) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("QuestionController questionInfoAdd start , current user is " + user.getUserId());
        
        QuestionDto dto = new QuestionDto();
        dto.setUserId(user.getUserId());
        dto.setTitle(vo.getTitle());
        dto.setContent(vo.getContent());
        dto.setCourseId(vo.getCourseId());
        questionService.saveQuestion(dto);
        
        return "redirect:/qa/questionInfo";
    }
}
