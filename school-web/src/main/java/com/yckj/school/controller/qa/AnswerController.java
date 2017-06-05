/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.controller.qa
 * Type:    AnswerController
 * Author:  hefengwen
 * Date:    2017-05-31 17:32:40
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.domain.AnswerVo;
import com.yckj.school.service.AnswerService;
import com.yckj.school.service.dto.AnswerDto;
import com.yckj.school.service.dto.UserDto;

/**
 * @author hefengwen
 */
@Controller
@RequestMapping({ "/qa" })
public class AnswerController {
    private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);
    @Autowired
    private AnswerService answerService;
    /**
     * 新增回复
     */
    @RequestMapping({ "/answerInfoAdd" })
    public String answerInfoAdd(HttpSession session, Model model,AnswerVo vo) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("AnswerController answerInfoAdd start , current user is " + user.getUserId());
        
        AnswerDto dto = new AnswerDto();
        dto.setUserId(user.getUserId());
        dto.setContent(vo.getContent());
        dto.setQuesId(vo.getQuesId());
        answerService.saveAnswer(dto);
        
        return "redirect:/qa/questionDetailInfo/"+vo.getQuesId();
    }
}
