/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.controller
 * Type:    UserController
 * Author:  hefengwen
 * Date:    2016-12-16 19:08:41
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.controller.user;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yckj.school.annotation.SchoolValidate;
import com.yckj.school.common.ResultData;
import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.constant.DbConstants;
import com.yckj.school.domain.LoginUser;
import com.yckj.school.domain.UpdPasswd;
import com.yckj.school.service.CourseService;
import com.yckj.school.service.UserService;
import com.yckj.school.service.dto.CourseDto;
import com.yckj.school.service.dto.UserDto;

/**
 * @author hefengwen
 */
@Controller
@RequestMapping({ "/user" })
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @RequestMapping({ "/jsp/{jspname}" })
    @SchoolValidate(ignoreSession = true)
    public String reqJsp(@PathVariable String jspname) {
        logger.debug("current request jsp is " + jspname);

        return "user/jsp/" + jspname;
    }

    @RequestMapping({ "/view/{viewname}" })
    public String reqView(HttpSession session, Model model,@PathVariable String viewname) {
        logger.debug("current request view is " + viewname);
        
        return "user/view/" + viewname;
    }

    @RequestMapping(value = { "/login" }, method = {RequestMethod.POST })
    @SchoolValidate(ignoreSession = true)
    public String login(HttpSession session, @ModelAttribute LoginUser loginUser, Model model) {
        logger.info("UserController login start , current user is " + loginUser.getUserId());
        UserDto dto = new UserDto();
        dto.setUserId(loginUser.getUserId());
        dto.setPasswd(loginUser.getPasswd());
        boolean isSuc = this.userService.login(dto);
        if (!isSuc) {
            model.addAttribute(DbConstants.USERID, loginUser.getUserId());
            model.addAttribute(Constants.MSG, "用户名或密码错误");
            return "user/jsp/login";
        }

        session.setAttribute(Constants.CURRENT_USER, dto);
        return "redirect:/user/userInfo";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("UserController logout start , current user is " + user.getUserId());
        session.removeAttribute(Constants.CURRENT_USER);
        return "user/jsp/login";
    }
    /**
     * 当前用户信息
     */
    @RequestMapping({ "/userInfo" })
    public String userInfo(HttpSession session, Model model) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("UserController userCenter start , current user is " + user.getUserId());
        
        return "user/view/userInfo" + user.getType();
    }
    
    /**
     * 修改密码
     */
    @RequestMapping({ "/updPasswd" })
    public String updPasswd(HttpSession session, Model model,UpdPasswd updPasswd) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("UserController userCenter start , current user is " + user.getUserId());
        
        if(StringUtils.isBlank(updPasswd.getNewPasswd1())||StringUtils.isBlank(updPasswd.getOldPasswd())){
            model.addAttribute(Constants.MSG, "密码不能为空！");
            return "user/view/updPasswd";
        }
        if(!updPasswd.getNewPasswd1().equals(updPasswd.getNewPasswd2())){
            model.addAttribute(Constants.MSG, "新密码不一致！");
            return "user/view/updPasswd";
        }
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setPasswd(updPasswd.getOldPasswd());
        dto.setType(Constants.ADMIN);
        boolean isSuc = userService.login(dto);
        if(!isSuc){
            model.addAttribute(Constants.MSG, "原密码错误！");
        }else{
            dto.setPasswd(updPasswd.getNewPasswd1());
            boolean rst = userService.updPasswd(dto);
            if(rst)
                model.addAttribute(Constants.MSG, "密码修改成功！");
            else
                model.addAttribute(Constants.MSG, "密码修改失败！");
        }
        
        return "user/view/updPasswd"+ user.getType();
    }
    
    /**
     * 修改密码视图
     */
    @RequestMapping({ "/updPasswdView" })
    public String updPasswdView(HttpSession session, Model model) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("UserController userCenter start , current user is " + user.getUserId());
        
        return "user/view/updPasswd" + user.getType();
    }
    
    /**
     * 检查用户ID是否存在
     * 管理员权限
     */
    @RequestMapping({ "/queryUserById/{userId}" })
    @ResponseBody
    public ResultData<?> queryUserById(HttpSession session, Model model,@PathVariable String userId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("UserController queryUserById start , current user is " + user.getUserId());
        
        ResultData<?> rst = new ResultData<>();
        UserDto dto = userService.queryUser(userId);
        if(dto != null){
            rst.setStatus(Constants.SYSTEM_ERROR_CODE);
            rst.setMsg("用户已存在！");
        }
        
        return rst;
    }
    /**
     * 根据课程ID获取课程封面图片
     */
    @RequestMapping("/filePreView/{courseId}")
    @SchoolValidate(ignoreSession=true)
    public void filePreView(@PathVariable long courseId,HttpServletResponse response){
        try {
            CourseDto dto = new CourseDto();
            dto.setCourseId(courseId);
            dto.setOut(response.getOutputStream());
            courseService.filePreView(dto);
        } catch (Exception e) {
            logger.error("",e);
        }
    } 
}
