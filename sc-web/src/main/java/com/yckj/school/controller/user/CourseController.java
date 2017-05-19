/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.controller
 * Type:    CourseController
 * Author:  hefengwen
 * Date:    2016-12-18 14:15:41
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.controller.user;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yckj.school.annotation.SchoolValidate;
import com.yckj.school.common.ResultData;
import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.exception.ServiceException;
import com.yckj.school.common.util.CourseUtil;
import com.yckj.school.common.util.DecomprossUtils;
import com.yckj.school.domain.BookVo;
import com.yckj.school.domain.CourseVo;
import com.yckj.school.domain.ScoreVo;
import com.yckj.school.exception.SchoolErrorType;
import com.yckj.school.exception.SchoolException;
import com.yckj.school.service.BookService;
import com.yckj.school.service.CourseService;
import com.yckj.school.service.MajorService;
import com.yckj.school.service.ScoreService;
import com.yckj.school.service.dto.BookDto;
import com.yckj.school.service.dto.CourseDto;
import com.yckj.school.service.dto.CoursePageDto;
import com.yckj.school.service.dto.MajorDto;
import com.yckj.school.service.dto.UserDto;

/**
 * @author hefengwen
 */
@Controller
@RequestMapping({ "/user" })
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    
    @Autowired
    private CourseService courseService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private BookService bookService;
    @Autowired
    private ScoreService scoreService;
    
    /**
     * 课程信息
     * 教师权限
     */
    @RequestMapping({ "/courseInfo" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String courseInfo(HttpSession session, Model model,CourseVo course) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("CourseController courseInfo start , current user is " + user.getUserId());
        
        CoursePageDto dto = new CoursePageDto();
        course.setStatue(Constants.YES);
        dto.setCurPage(course.getCurPage());
        dto.setPageCount(course.getPageCount());
        dto.setCondition(course);
        dto = courseService.listAllCourseByPage(dto);
        model.addAttribute(Constants.DOMAIN, course);
        model.addAttribute(Constants.RESULT, dto);
        return "user/view/courseInfo";
    }
    
    
    /**
     * 编辑课程视图
     * 教师权限
     */
    @RequestMapping({ "/courseInfoEditView/{courseId}" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String courseInfoEditView(HttpSession session, Model model,@PathVariable long courseId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("CourseController courseInfoEditView start , current user is " + user.getUserId());
        
        CourseDto dto = courseService.queryCourse(courseId);
        if(Constants.NO.equals(dto.getStatue())||!user.getUserId().equals(dto.getUserId()))
            return "forward:/user/courseInfo";
        model.addAttribute(Constants.RESULT, dto);
        List<MajorDto> dtos = majorService.queryAllMajor();
        model.addAttribute("majors", dtos);
        
        model.addAttribute("courseTypes", CourseUtil.getCourseType());
        
        return "user/view/courseInfoEdit";
    }
    
    /**
     * 编辑课程
     * 教师权限
     */
    @RequestMapping({ "/courseInfoEdit" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String courseInfoEdit(HttpSession session, Model model,CourseVo course) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("CourseController courseInfoEdit start , current user is " + user.getUserId());
        try {
            String coursePic = null;
            if(course.getPictureFile().getSize() != 0){
                MultipartFile file = course.getPictureFile();
                coursePic = courseService.uploadPic(file.getBytes(), file.getOriginalFilename());
            }
            course.setPicture(coursePic);
            courseService.updateCourse(course);
        }
        catch (ServiceException e) {
            logger.error("",e);
            throw e;
        }
        catch (IOException e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
        return "redirect:/user/courseInfo";
    }
    /**
     * 新增课程视图
     * 教师权限
     */
    @RequestMapping({ "/courseInfoAddView" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String courseInfoAddView(HttpSession session, Model model) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("CourseController courseInfoAddView start , current user is " + user.getUserId());
        
        List<MajorDto> dtos = majorService.queryAllMajor();
        model.addAttribute(Constants.RESULT, dtos);
        
        model.addAttribute("courseTypes", CourseUtil.getCourseType());
        
        return "user/view/courseInfoAdd";
    }
    /**
     * 新增课程
     * 教师权限
     */
    @RequestMapping({ "/courseInfoAdd" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String courseInfoAdd(HttpSession session, Model model,CourseVo course) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("CourseController courseInfoAdd start , current user is " + user.getUserId());
        try {
            String coursePic = null;
            if(course.getPictureFile().getSize() != 0){
                MultipartFile file = course.getPictureFile();
                coursePic = courseService.uploadPic(file.getBytes(), file.getOriginalFilename());
            }
            course.setPicture(coursePic);
            course.setMajorId(user.getMajorId());
            course.setUserId(user.getUserId());
            courseService.addCourse(course);
        }
        catch (ServiceException e) {
            logger.error("",e);
            throw e;
        }
        catch (IOException e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
        
        return "redirect:/user/courseInfo";
    }
    /**
     * 删除课程
     * 教师权限
     */
    @RequestMapping({ "/courseInfoDelete/{courseId}" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    @ResponseBody
    public ResultData<?> courseInfoDelete(HttpSession session, @PathVariable long courseId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("CourseController courseInfoDelete start , current user is " + user.getUserId());
        ResultData<?> rst = new ResultData<>();
        
        try {
            boolean b = courseService.deleteCourse(courseId);
            if(!b){
                rst.setStatus(Constants.SYSTEM_ERROR_CODE);
                rst.setMsg("删除失败！");
                return rst;
            }
            b = courseService.deletePic(courseId);
            if(!b){
                logger.error(courseId+"删除图片失败！");
            }
            BookDto oldBook = bookService.queryBookByCourse(courseId);
            if(oldBook!=null){
                bookService.deleteBook(oldBook);
                String bookRootDir = System.getProperty(Constants.SC_ROOT_PATH)+Constants.BOOK_DIR;
                File bookDir = new File(bookRootDir,String.valueOf(oldBook.getBookId()));
                if(bookDir.exists()){
                    deleteDirAndFile(bookDir);
                }
                File bookZip = new File(bookRootDir,oldBook.getBookId()+"."+oldBook.getType());
                if(bookZip.exists()){
                    deleteDirAndFile(bookZip);
                }
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
    
    private boolean deleteDirAndFile(File dir){
        if (dir.isDirectory()) {
            String[] children = dir.list();
//　　　　递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDirAndFile(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    /**
     * 新增课程教材视图
     * 教师权限
     */
    @RequestMapping({ "/courseBookAddView/{courseId}" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String courseBookAddView(HttpSession session, Model model,@PathVariable long courseId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("CourseController courseBookAddView start , current user is " + user.getUserId());
        model.addAttribute("courseId", courseId);
        return "user/view/courseBookAdd";
    }
    /**
     * 新增教材
     * 教师权限
     */
    @RequestMapping({ "/courseBookAdd" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    @ResponseBody
    public ResultData<?> courseBookAdd(HttpSession session, Model model,BookVo book) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("CourseController courseBookAdd start , current user is " + user.getUserId());
        ResultData<?> rst = new ResultData<>();
        try {
            BookDto oldBook = bookService.queryBookByCourse(book.getCourseId());
//            String rootPath = System.getProperty(Constants.ROOT_PATH);
//            if(book!=null){
//                File bookDir = new File(rootPath,String.valueOf(oldBook.getBookId()));
//                if(bookDir.exists()){
//                    bookDir.delete();
//                }
//                File bookZip = new File(rootPath,oldBook.getBookId()+"."+book.getType());
//                if(bookZip.exists()){
//                    bookZip.delete();
//                }
//            }
            MultipartFile file = book.getBookFile();
            book.setName(file.getOriginalFilename());
            book.setFileSize(file.getSize());
            book.setIn(file.getInputStream());
            boolean isSuc = bookService.uploadBook(book);
            if(isSuc&&oldBook!=null){
                bookService.deleteBook(oldBook);
            }
        } catch (ServiceException e) {
            logger.error("",e);
            rst.setStatus(e.getErrorCode());
            rst.setMsg(e.getMsg());
        } catch (Exception e) {
            logger.error("",e);
            rst.setStatus(Constants.SYSTEM_ERROR_CODE);
            rst.setMsg(Constants.SYSTEM_ERROR_MSG);
        } 
        return rst;
    }
    /**
     * 课程教材预览
     * 首先将课程文件下载到本地
     * 然后客户端访问本地文件
     */
    @RequestMapping("/courseBookView/{bookId}")
    @SchoolValidate(ignoreSession=true)
    public void courseBookView(HttpSession session, @PathVariable long bookId,HttpServletRequest request,HttpServletResponse response){
        logger.info("CourseController courseBookView start ");
        //文件类型 swf:application/x-shockwave-flash  pdf:application/pdf
        BufferedOutputStream out = null;
        try {
            String bookRootDir = System.getProperty(Constants.SC_ROOT_PATH)+Constants.BOOK_DIR;
            String bookPath = request.getContextPath()+"/"+Constants.BOOK_DIR;
            logger.info("bookRootDir:"+bookRootDir);
            logger.info("bookPath:"+bookPath);
//            logger.info("ContextPath:"+request.getContextPath()+"/MyBatis3/index.html");
            BookDto book = bookService.queryBook(bookId);
            if(book==null){
                logger.error(bookId+" book文件不存在");
                throw new SchoolException(SchoolErrorType.err_file_not_exist, null);
            }
            File bookDir = new File(bookRootDir,String.valueOf(bookId));
            if(bookDir.exists()&&bookDir.isDirectory()){
                logger.info(book.getName()+"文件夹已存在，无需下载");
                response.sendRedirect(bookPath+bookId+Constants.BOOK_INDEX);
                return;
            }
            if(!bookDir.exists()){
                bookDir.mkdirs();
            }
            File bookZip = new File(bookRootDir,bookId+"."+book.getType());
            if(bookZip.exists()){
                bookZip.delete();
            }
            out = new BufferedOutputStream(new FileOutputStream(bookZip));
            book.setOut(out);
            bookService.downloadBook(book);
            bookZip.createNewFile();
            if(!bookZip.exists()){
                logger.error(bookId+" book文件下载失败");
                throw new SchoolException(SchoolErrorType.err_system, null);
            }
            DecomprossUtils.zipDecomprossing(bookZip.getAbsolutePath(), bookRootDir+bookId+"/");
            response.sendRedirect(bookPath+bookId+Constants.BOOK_INDEX);
        }
        catch (ServiceException e) {
            logger.error("",e);
            throw e;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
        finally{
            if(out!=null){
                try {
                    out.close();
                }
                catch (IOException e) {
                    logger.error("",e);
                }
            }
        }
    } 
    /**
     * 评价课程
     * 学生权限
     */
    @RequestMapping({ "/scoreCourse" })
    @SchoolValidate(accessUser=Constants.STUDENT)
    @ResponseBody
    public ResultData<?> scoreCourse(HttpSession session, ScoreVo score) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("CourseController scoreCourse start , current user is " + user.getUserId());
        ResultData<?> rst = new ResultData<>();
        
        try {
            score.setUserId(user.getUserId());
            score.setRefType(Constants.REF_TYPE_COURSE);
            boolean b = scoreService.scoreCourse(score);
            if(!b){
                rst.setStatus(Constants.SYSTEM_ERROR_CODE);
                rst.setMsg("评价失败！");
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
