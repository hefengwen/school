/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.controller
 * Type:    MajorController
 * Author:  hefengwen
 * Date:    2016-12-17 18:15:28
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.controller.user;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yckj.school.annotation.SchoolValidate;
import com.yckj.school.common.ResultData;
import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.exception.ServiceException;
import com.yckj.school.domain.MajorVo;
import com.yckj.school.service.MajorService;
import com.yckj.school.service.dto.MajorDto;
import com.yckj.school.service.dto.MajorPageDto;
import com.yckj.school.service.dto.UserDto;

/**
 * @author hefengwen
 */
@Controller
@RequestMapping({ "/user" })
public class MajorController {
    private static final Logger logger = LoggerFactory.getLogger(MajorController.class);
    
    @Autowired
    private MajorService majorService;
    /**
     * 专业信息
     * 管理员权限
     */
    @RequestMapping({ "/majorInfo" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    public String majorInfo(HttpSession session, Model model,MajorVo major) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("MajorController majorInfo start , current user is " + user.getUserId());
        
        MajorPageDto dto = new MajorPageDto();
        major.setStatue(Constants.YES);
        dto.setCurPage(major.getCurPage());
        dto.setPageCount(major.getPageCount());
        dto.setCondition(major);
        dto = majorService.listAllMajorByPage(dto);
        model.addAttribute(Constants.DOMAIN, major);
        model.addAttribute(Constants.RESULT, dto);
        return "user/view/majorInfo";
    }
    
    /**
     * 编辑专业视图
     * 管理员权限
     */
    @RequestMapping({ "/majorInfoEditView/{majorId}" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    public String majorInfoEditView(HttpSession session, Model model,@PathVariable long majorId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("MajorController majorInfoEditView start , current user is " + user.getUserId());
        
        MajorDto dto = majorService.queryMajor(majorId);
        model.addAttribute(Constants.RESULT, dto);
        
        return "user/view/majorInfoEdit";
    }
    
    /**
     * 编辑专业
     * 管理员权限
     */
    @RequestMapping({ "/majorInfoEdit" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    public String majorInfoEdit(HttpSession session, Model model,MajorVo major) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("MajorController majorInfoEdit start , current user is " + user.getUserId());
        
        majorService.updateMajor(major);
        
        return "redirect:/user/majorInfo";
    }
    
    /**
     * 新增专业
     * 管理员权限
     */
    @RequestMapping({ "/majorInfoAdd" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    public String majorInfoAdd(HttpSession session, Model model,MajorVo major) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("MajorController majorInfoAdd start , current user is " + user.getUserId());
        
        majorService.addMajor(major);
        
        return "redirect:/user/majorInfo";
    }
    /**
     * 删除专业
     * 管理员权限
     */
    @RequestMapping({ "/majorInfoDelete/{majorId}" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    @ResponseBody
    public ResultData<?> majorInfoDelete(HttpSession session, @PathVariable long majorId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("MajorController majorInfoDelete start , current user is " + user.getUserId());
        ResultData<?> rst = new ResultData<>();
        
        try {
            boolean b = majorService.deleteMajor(majorId);
            if(!b){
                rst.setStatus(Constants.SYSTEM_ERROR_CODE);
                rst.setMsg("删除失败！");
            }
        }
        catch (ServiceException e) {
            logger.error("",e);
            rst.setStatus(e.getErrorCode());
            rst.setMsg(e.getMsg());
        }catch (Exception e) {
            logger.error("",e);
            rst.setStatus(Constants.SYSTEM_ERROR_CODE);
            rst.setMsg(Constants.SYSTEM_ERROR_MSG);
        } 
        return rst;
    }
}
