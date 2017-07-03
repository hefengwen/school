/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.controller.user
 * Type:    SaveController
 * Author:  hefengwen
 * Date:    2017-01-08 00:13:38
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
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

import com.yckj.school.common.ResultData;
import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.exception.ServiceException;
import com.yckj.school.domain.SaveVo;
import com.yckj.school.service.CourseService;
import com.yckj.school.service.ResourceService;
import com.yckj.school.service.SaveService;
import com.yckj.school.service.dto.CourseDto;
import com.yckj.school.service.dto.ResourceDto;
import com.yckj.school.service.dto.SavePageDto;
import com.yckj.school.service.dto.UserDto;

/**
 * @author hefengwen
 */
@Controller
@RequestMapping({ "/user" })
public class SaveController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    private SaveService saveService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ResourceService resourceService;
    /**
     * 订阅课程
     */
    @RequestMapping("/subscribeCourse/{courseId}")
    @ResponseBody
    public ResultData<?> subscribeCourse(@PathVariable long courseId,HttpSession session){ 
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("ResourceController saveCourse start , current user is " + user.getUserId());
        ResultData<?> result = new ResultData<>();
        try {
            CourseDto dto = courseService.queryCourse(courseId);
            if(dto==null){
                result.setStatus(Constants.SYSTEM_ERROR_CODE);
                result.setMsg("课程不存在或已删除！");
                return result;
            }
            dto.setCurrentUserId(user.getUserId());
            saveService.subscribeCourse(dto);
        }
        catch (ServiceException e) {
            logger.error("",e);
            result.setStatus(e.getErrorCode());
            result.setMsg(e.getMsg());
        }
        catch (Exception e) {
            logger.error("",e);
            result.setStatus(Constants.SYSTEM_ERROR_CODE);
            result.setMsg(Constants.SYSTEM_ERROR_MSG);
        } 
        return result;
    }
    /**
     * 资源收藏
     */
    @RequestMapping("/saveResource/{resourceId}")
    @ResponseBody
    public ResultData<?> saveResource(@PathVariable long resourceId,HttpSession session){ 
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("ResourceController saveResource start , current user is " + user.getUserId());
        ResultData<?> result = new ResultData<>();
        try {
            ResourceDto dto = resourceService.queryResource(resourceId);
            if(dto==null){
                result.setStatus(Constants.SYSTEM_ERROR_CODE);
                result.setMsg("资源不存在或已删除！");
                return result;
            }
            dto.setCurrentUserId(user.getUserId());
            resourceService.saveResource(dto);
        }
        catch (ServiceException e) {
            logger.error("",e);
            result.setStatus(e.getErrorCode());
            result.setMsg(e.getMsg());
        }
        catch (Exception e) {
            logger.error("",e);
            result.setStatus(Constants.SYSTEM_ERROR_CODE);
            result.setMsg(Constants.SYSTEM_ERROR_MSG);
        } 
        return result;
    }
    /**
     * 课程订阅列表
     */
    @RequestMapping({ "/courseSubscribe" })
    public String courseSubscribe(HttpSession session, Model model,SaveVo save) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("SaveController courseSubscribe start , current user is " + user.getUserId());
        
        SavePageDto dto = new SavePageDto();
        dto.setCurPage(save.getCurPage());
        dto.setPageCount(save.getPageCount());
        save.setUserId(user.getUserId());
        save.setRefType(Constants.REF_TYPE_COURSE);
        dto.setCondition(save);
        
        dto = saveService.listAllSaveByPage(dto);
        model.addAttribute(Constants.DOMAIN, save);
        model.addAttribute(Constants.RESULT, dto);
        return "user/view/courseSubscribe"+user.getType();
    }
    /**
     * 资源收藏列表
     */
    @RequestMapping({ "/resourceSave" })
    public String resourceSave(HttpSession session, Model model,SaveVo save) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("SaveController resourceSave start , current user is " + user.getUserId());
        
        SavePageDto dto = new SavePageDto();
        dto.setCurPage(save.getCurPage());
        dto.setPageCount(save.getPageCount());
        save.setUserId(user.getUserId());
        save.setRefType(Constants.REF_TYPE_RESOURCE);
        dto.setCondition(save);
        
        dto = saveService.listAllSaveByPage(dto);
        model.addAttribute(Constants.DOMAIN, save);
        model.addAttribute(Constants.RESULT, dto);
        return "user/view/resourceSave"+user.getType();
    }
    /**
     * 删除订阅、收藏
     */
    @RequestMapping({ "/saveInfoDelete/{saveId}" })
    @ResponseBody
    public ResultData<?> saveInfoDelete(HttpSession session, @PathVariable long saveId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("SaveController saveInfoDelete start , current user is " + user.getUserId());
        ResultData<?> rst = new ResultData<>();
        
        try {
            boolean b = saveService.saveInfoDelete(saveId);
            if(!b){
                rst.setStatus(Constants.SYSTEM_ERROR_CODE);
                rst.setMsg("删除失败！");
                return rst;
            }
        }
        catch (ServiceException e) {
            rst.setStatus(e.getErrorCode());
            rst.setMsg(e.getMsg());
        }
        catch (Exception e) {
            logger.error("",e);
            rst.setStatus(Constants.SYSTEM_ERROR_CODE);
            rst.setMsg(Constants.SYSTEM_ERROR_MSG);
        } 
        return rst;
    }
}
