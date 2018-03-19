/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.controller.user
 * Type:    NoticeController
 * Author:  hefengwen
 * Date:    2016-12-19 10:02:54
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
import com.yckj.school.common.constant.DbConstants;
import com.yckj.school.common.exception.ServiceException;
import com.yckj.school.domain.NoticeVo;
import com.yckj.school.service.NoticeService;
import com.yckj.school.service.dto.NoticeDto;
import com.yckj.school.service.dto.NoticePageDto;
import com.yckj.school.service.dto.UserDto;

/**
 * @author hefengwen
 */
@Controller
@RequestMapping({ "/user" })
public class NoticeController {
    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
    
    @Autowired
    private NoticeService noticeService;
    /**
     * 系统公告信息
     * 管理员权限
     */
    @RequestMapping({ "/noticeSystemInfo" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    public String noticeSystemInfo(HttpSession session, Model model,NoticeVo notice) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("NoticeController noticeSystemInfo start , current user is " + user.getUserId());
        
        NoticePageDto dto = new NoticePageDto();
        notice.setStatue(Constants.YES);
        notice.setType(Constants.NOTICE_SYSTEM_TYPE);
        dto.setCurPage(notice.getCurPage());
        dto.setPageCount(notice.getPageCount());
        dto.setCondition(notice);
        dto = noticeService.listAllNoticeByPage(dto);
        model.addAttribute(Constants.DOMAIN, notice);
        model.addAttribute(Constants.RESULT, dto);
        return "user/view/notice/noticeSystemInfo";
    }
    /**
     * 课程公告信息
     * 教师权限
     */
    @RequestMapping({ "/noticeCourseInfo" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String noticeCourseInfo(HttpSession session, Model model,NoticeVo notice) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("NoticeController noticeCourseInfo start , current user is " + user.getUserId());
        
        NoticePageDto dto = new NoticePageDto();
        notice.setStatue(Constants.YES);
        notice.setType(Constants.NOTICE_COURSE_TYPE);
        notice.setAuthor(user.getUserId());
        dto.setCurPage(notice.getCurPage());
        dto.setPageCount(notice.getPageCount());
        dto.setCondition(notice);
        dto = noticeService.listAllNoticeByPage(dto);
        model.addAttribute(Constants.DOMAIN, notice);
        model.addAttribute(Constants.RESULT, dto);
        return "user/view/notice/noticeCourseInfo";
    }
    
    /**
     * 编辑系统公告视图
     * 管理员权限
     */
    @RequestMapping({ "/noticeSystemInfoEditView/{noticeId}" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    public String noticeSystemInfoEditView(HttpSession session, Model model,@PathVariable long noticeId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("NoticeController noticeSystemInfoEditView start , current user is " + user.getUserId());
        
        NoticeDto dto = noticeService.queryNotice(noticeId);
        model.addAttribute(Constants.RESULT, dto);
        
        return "user/view/notice/noticeSystemInfoEditView";
    }
    
    /**
     * 编辑课程公告视图
     * 教师权限
     */
    @RequestMapping({ "/noticeCourseInfoEditView/{noticeId}" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String noticeCourseInfoEditView(HttpSession session, Model model,@PathVariable long noticeId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("NoticeController noticeCourseInfoEditView start , current user is " + user.getUserId());
        
        NoticeDto dto = noticeService.queryNotice(noticeId);
        model.addAttribute(Constants.RESULT, dto);
        
        return "user/view/notice/noticeCourseInfoEditView";
    }
    
    /**
     * 编辑系统公告
     * 管理员权限
     */
    @RequestMapping({ "/noticeSystemInfoEdit" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    public String noticeSystemInfoEdit(HttpSession session, Model model,NoticeVo notice) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("NoticeController noticeSystemInfoEdit start , current user is " + user.getUserId());
        
        noticeService.updateNotice(notice);
        
        return "redirect:/user/noticeSystemInfo";
    }
    
    /**
     * 编辑课程公告
     * 教师权限
     */
    @RequestMapping({ "/noticeCourseInfoEdit" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String noticeCourseInfoEdit(HttpSession session, Model model,NoticeVo notice) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("NoticeController noticeCourseInfoEdit start , current user is " + user.getUserId());
        
        noticeService.updateNotice(notice);
        
        return "redirect:/user/noticeCourseInfo";
    }
    
    /**
     * 新增系统公告视图
     * 管理员权限
     */
    @RequestMapping({ "/noticeSystemInfoAddView" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    public String noticeSystemInfoAddView(HttpSession session, Model model,NoticeVo notice) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("NoticeController noticeSystemInfoAddView start , current user is " + user.getUserId());
        
        return "user/view/notice/noticeSystemInfoAddView";
    }
    /**
     * 新增课程公告视图
     * 教师权限
     */
    @RequestMapping({ "/noticeCourseInfoAddView" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String noticeCourseInfoAddView(HttpSession session, Model model,NoticeVo notice) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("NoticeController noticeCourseInfoAddView start , current user is " + user.getUserId());
        
        return "user/view/notice/noticeCourseInfoAddView";
    }
    /**
     * 新增系统公告
     * 管理员权限
     */
    @RequestMapping({ "/noticeSystemInfoAdd" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    public String noticeSystemInfoAdd(HttpSession session, Model model,NoticeVo notice) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("NoticeController noticeSystemInfoAdd start , current user is " + user.getUserId());
        
        notice.setAuthor(user.getUserId());
        notice.setType(Constants.NOTICE_SYSTEM_TYPE);
        noticeService.addNotice(notice);
        
        return "redirect:/user/noticeSystemInfo";
    }
    /**
     * 新增课程公告
     * 教师权限
     */
    @RequestMapping({ "/noticeCourseInfoAdd" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String noticeCourseInfoAdd(HttpSession session, Model model,NoticeVo notice) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("NoticeController noticeCourseInfoAdd start , current user is " + user.getUserId());
        
        notice.setAuthor(user.getUserId());
        notice.setType(Constants.NOTICE_COURSE_TYPE);
        noticeService.addNotice(notice);
        
        return "redirect:/user/noticeCourseInfo";
    }
    /**
     * 删除系统公告
     * 管理员权限
     */
    @RequestMapping({ "/noticeSystemInfoDelete/{noticeId}" })
    @SchoolValidate(accessUser=Constants.ADMIN)
    @ResponseBody
    public ResultData<?> noticeSystemInfoDelete(HttpSession session, @PathVariable long noticeId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("NoticeController noticeSystemInfoDelete start , current user is " + user.getUserId());
        ResultData<?> rst = new ResultData<>();
        
        try {
            boolean b = noticeService.deleteNotice(noticeId);
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
    /**
     * 删除课程公告
     * 教师权限
     */
    @RequestMapping({ "/noticeCourseInfoDelete/{noticeId}" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    @ResponseBody
    public ResultData<?> noticeCourseInfoDelete(HttpSession session, @PathVariable long noticeId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("NoticeController noticeCourseInfoDelete start , current user is " + user.getUserId());
        ResultData<?> rst = new ResultData<>();
        
        try {
            boolean b = noticeService.deleteNotice(noticeId);
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
    /**
     * 系统公告列表
     */
    @RequestMapping("/systemNoticeList")
    @SchoolValidate(ignoreSession=true)
    public String systemNoticeList(Model model,NoticeVo systemNotice){
        NoticePageDto noticeDto = new NoticePageDto();
        systemNotice.setStatue(Constants.YES);
        systemNotice.setType(Constants.NOTICE_SYSTEM_TYPE);
        systemNotice.setOrderByColumn(DbConstants.CREATE_TIME);
        systemNotice.setOrderType(Constants.ORDER_DESC);
        noticeDto.setCurPage(systemNotice.getCurPage());
        noticeDto.setPageCount(5);
        noticeDto.setCondition(systemNotice);
        noticeDto = noticeService.listAllNoticeByPage(noticeDto);
        model.addAttribute(Constants.DOMAIN, systemNotice);
        model.addAttribute(Constants.RESULT,noticeDto);
        return "user/view/notice/systemNoticeList";
    }
    /**
     * 课程公告列表
     */
    @RequestMapping("/courseNoticeList")
    @SchoolValidate(ignoreSession=true)
    public String courseNoticeList(Model model,NoticeVo courseNotice){
        NoticePageDto courseDto = new NoticePageDto();
        courseNotice.setStatue(Constants.YES);
        courseNotice.setType(Constants.NOTICE_COURSE_TYPE);
        courseNotice.setOrderByColumn(DbConstants.CREATE_TIME);
        courseNotice.setOrderType(Constants.ORDER_DESC);
        courseDto.setCurPage(courseNotice.getCurPage());
        courseDto.setPageCount(5);
        courseDto.setCondition(courseNotice);
        courseDto = noticeService.listAllNoticeByPage(courseDto);
        model.addAttribute(Constants.DOMAIN, courseNotice);
        model.addAttribute(Constants.RESULT,courseDto);
        return "user/view/notice/courseNoticeList";
    }
    /**
     * 公告详情
     */
    @RequestMapping("/noticeDetail/{noticeId}")
    @SchoolValidate(ignoreSession=true)
    public String noticeDetail(Model model,@PathVariable long noticeId){
        NoticeDto dto = noticeService.queryNotice(noticeId);
        model.addAttribute(Constants.RESULT,dto);
        return "user/view/notice/noticeDetial";
    }
}
