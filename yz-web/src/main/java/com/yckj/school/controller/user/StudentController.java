/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.controller
 * Type:    StudentController
 * Author:  hefengwen
 * Date:    2016-12-18 17:51:23
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
import com.yckj.school.domain.UserVo;
import com.yckj.school.service.UserService;
import com.yckj.school.service.dto.UserDto;
import com.yckj.school.service.dto.UserPageDto;

/**
 * @author hefengwen
 */
@Controller
@RequestMapping({ "/user" })
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    
    @Autowired
    private UserService userService;
    
    /**
     * 学生信息
     * 教师权限
     */
    @RequestMapping({ "/studentInfo" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String studentInfo(HttpSession session, Model model,UserVo student) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("StudentController studentInfo start , current user is " + user.getUserId());
        
        UserPageDto dto = new UserPageDto();
        student.setStatue(Constants.YES);
        student.setType(Constants.STUDENT);
//        student.setMajorId(user.getMajorId());
//        student.setTeacherId(user.getUserId());
        dto.setCurPage(student.getCurPage());
        dto.setPageCount(student.getPageCount());
        dto.setCondition(student);
        dto = userService.listAllUserByPage(dto);
        model.addAttribute(Constants.DOMAIN, student);
        model.addAttribute(Constants.RESULT, dto);
        return "user/view/studentInfo";
    }
    
    /**
     * 编辑学生视图
     * 教师权限
     */
    @RequestMapping({ "/studentInfoEditView/{userId}" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String studentInfoEditView(HttpSession session, Model model,@PathVariable String userId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("StudentController studentInfoEditView start , current user is " + user.getUserId());
        
        UserDto dto = userService.queryUser(userId);
        if(Constants.NO.equals(dto.getStatue())||!user.getUserId().equals(dto.getTeacherId()))
            return "forward:/user/studentInfo";
        model.addAttribute(Constants.RESULT, dto);
        
        return "user/view/studentInfoEdit";
    }
    
    /**
     * 编辑学生
     * 教师权限
     */
    @RequestMapping({ "/studentInfoEdit/{studentId}" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String studentInfoEdit(HttpSession session, Model model,UserVo student, @PathVariable String studentId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("StudentController studentInfoEdit start , current user is " + user.getUserId());
        
        student.setUserId(studentId);
        userService.updateUser(student);
        
        return "redirect:/user/studentInfo";
    }
    
    /**
     * 新增教师视图
     * 教师权限
     */
    @RequestMapping({ "/studentInfoAddView" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String studentInfoAddView(HttpSession session, Model model) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("StudentController studentInfoAddView start , current user is " + user.getUserId());
        
        return "user/view/studentInfoAdd";
    }
    
    /**
     * 新增学生
     * 教师权限
     */
    @RequestMapping({ "/studentInfoAdd" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String studentInfoAdd(HttpSession session, Model model,UserVo student) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("StudentController studentInfoAdd start , current user is " + user.getUserId());
        
        student.setType(Constants.STUDENT);
        student.setMajorId(user.getMajorId());
        student.setTeacherId(user.getUserId());
        student.setPasswd(Constants.DEFAULT_PASSWD);
        userService.addUser(student);
        
        return "redirect:/user/studentInfo";
    }
    /**
     * 删除学生
     * 教师权限
     */
    @RequestMapping({ "/studentInfoDelete/{studentId}" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    @ResponseBody
    public ResultData<?> studentInfoDelete(HttpSession session, @PathVariable String studentId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("StudentController studentInfoDelete start , current user is " + user.getUserId());
        ResultData<?> rst = new ResultData<>();
        
        try {
            boolean b = userService.deleteUser(studentId);
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
