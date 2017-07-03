/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.controller
 * Type:    TeacherController
 * Author:  hefengwen
 * Date:    2016-12-17 21:39:21
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.controller.user;

import java.util.List;

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
import com.yckj.school.domain.UserVo;
import com.yckj.school.service.MajorService;
import com.yckj.school.service.UserService;
import com.yckj.school.service.dto.MajorDto;
import com.yckj.school.service.dto.UserDto;
import com.yckj.school.service.dto.UserPageDto;

/**
 * @author hefengwen
 */
@Controller
@RequestMapping({ "/user" })
public class TeacherController {
    private static final Logger logger = LoggerFactory.getLogger(MajorController.class);
    
    @Autowired
    private UserService userService;
    @Autowired
    private MajorService majorService;
    
    /**
     * 教师信息
     * 管理员权限
     */
    @RequestMapping({ "/teacherInfo" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    public String teacherInfo(HttpSession session, Model model,UserVo teacher) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("TeacherController teacherInfo start , current user is " + user.getUserId());
        
        UserPageDto dto = new UserPageDto();
        teacher.setStatue(Constants.YES);
        teacher.setType(Constants.TEACHER);
        dto.setCurPage(teacher.getCurPage());
        dto.setPageCount(teacher.getPageCount());
        dto.setCondition(teacher);
        dto = userService.listAllUserByPage(dto);
        model.addAttribute(Constants.DOMAIN, teacher);
        model.addAttribute(Constants.RESULT, dto);
        return "user/view/teacherInfo";
    }
    
    /**
     * 编辑教师视图
     * 管理员权限
     */
    @RequestMapping({ "/teacherInfoEditView/{userId}" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    public String teacherInfoEditView(HttpSession session, Model model,@PathVariable String userId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("TeacherController teacherInfoEditView start , current user is " + user.getUserId());
        
        UserDto dto = userService.queryUser(userId);
        model.addAttribute(Constants.RESULT, dto);
        List<MajorDto> dtos = majorService.queryAllMajor();
        model.addAttribute("majors", dtos);
        
        return "user/view/teacherInfoEdit";
    }
    
    /**
     * 编辑教师
     * 管理员权限
     */
    @RequestMapping({ "/teacherInfoEdit/{teacherId}" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    public String teacherInfoEdit(HttpSession session, Model model,UserVo teacher, @PathVariable String teacherId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("TeacherController teacherInfoEdit start , current user is " + user.getUserId());
        
        teacher.setUserId(teacherId);
        userService.updateUser(teacher);
        
        return "redirect:/user/teacherInfo";
    }
    
    /**
     * 新增教师视图
     * 管理员权限
     */
    @RequestMapping({ "/teacherInfoAddView" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    public String teacherInfoAddView(HttpSession session, Model model) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("TeacherController teacherInfoAddView start , current user is " + user.getUserId());
        
        List<MajorDto> dtos = majorService.queryAllMajor();
        model.addAttribute(Constants.RESULT, dtos);
        
        return "user/view/teacherInfoAdd";
    }
    
    /**
     * 新增教师
     * 管理员权限
     */
    @RequestMapping({ "/teacherInfoAdd" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    public String teacherInfoAdd(HttpSession session, Model model,UserVo teacher) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("TeacherController teacherInfoAdd start , current user is " + user.getUserId());
        
        teacher.setType(Constants.TEACHER);
        teacher.setPasswd(Constants.DEFAULT_PASSWD);
        userService.addUser(teacher);
        
        return "redirect:/user/teacherInfo";
    }
    /**
     * 删除教师
     * 管理员权限
     */
    @RequestMapping({ "/teacherInfoDelete/{teacherId}" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    @ResponseBody
    public ResultData<?> teacherInfoDelete(HttpSession session, @PathVariable String teacherId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("TeacherController teacherInfoDelete start , current user is " + user.getUserId());
        ResultData<?> rst = new ResultData<>();
        
        try {
            boolean b = userService.deleteUser(teacherId);
            if(!b){
                rst.setStatus(Constants.SYSTEM_ERROR_CODE);
                rst.setMsg("删除失败！");
            }
        }
        catch (ServiceException e) {
            logger.error("",e);
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
