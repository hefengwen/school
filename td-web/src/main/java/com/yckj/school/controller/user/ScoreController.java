/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.controller.user
 * Type:    ScoreController
 * Author:  hefengwen
 * Date:    2017-01-23 11:07:41
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yckj.school.annotation.SchoolValidate;
import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.constant.DbConstants;
import com.yckj.school.domain.UserVo;
import com.yckj.school.service.UserService;
import com.yckj.school.service.dto.UserPageDto;

/**
 * @author hefengwen
 */
@Controller
@RequestMapping({ "/user" })
public class ScoreController {
    private static final Logger logger = LoggerFactory.getLogger(ScoreController.class);
    
    @Autowired
    private UserService userService;
    /**
     * 教师积分列表
     */
    @RequestMapping("/teacherScoreList")
    @SchoolValidate(ignoreSession=true)
    public String teacherScoreList(Model model,UserVo teacher){
        logger.info("ScoreController teacherScoreList start ... ...");
        UserPageDto dto = new UserPageDto();
        teacher.setStatue(Constants.YES);
        teacher.setType(Constants.TEACHER);
        teacher.setOrderByColumn(DbConstants.SCORE);
        teacher.setOrderType(Constants.ORDER_DESC);
        dto.setCurPage(teacher.getCurPage());
        dto.setPageCount(teacher.getPageCount());
        dto.setCondition(teacher);
        dto = userService.listAllUserByPage(dto);
        model.addAttribute(Constants.DOMAIN, teacher);
        model.addAttribute(Constants.RESULT, dto);
        return "user/view/score/teacherScoreList";
        
    }
}
