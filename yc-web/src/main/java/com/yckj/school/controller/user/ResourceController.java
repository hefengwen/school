/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.controller.user
 * Type:    ResourceController
 * Author:  hefengwen
 * Date:    2016-12-19 10:37:40
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.controller.user;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.List;

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
import com.yckj.school.common.util.PdfConvertUtils;
import com.yckj.school.common.util.VideoConvertUtils;
import com.yckj.school.domain.ResourceVo;
import com.yckj.school.domain.ScoreVo;
import com.yckj.school.exception.SchoolErrorType;
import com.yckj.school.exception.SchoolException;
import com.yckj.school.service.DictService;
import com.yckj.school.service.MajorService;
import com.yckj.school.service.ResourceService;
import com.yckj.school.service.ScoreService;
import com.yckj.school.service.dto.MajorDto;
import com.yckj.school.service.dto.ResourceDto;
import com.yckj.school.service.dto.ResourcePageDto;
import com.yckj.school.service.dto.UserDto;

/**
 * @author hefengwen
 */
@Controller
@RequestMapping({ "/user" })
public class ResourceController {
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
    
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private DictService dictService;
    @Autowired
    private ScoreService scoreService;
    /**
     * 资源信息
     */
    @RequestMapping({ "/resourceInfo" })
    public String resourceInfo(HttpSession session, Model model,ResourceVo resource) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("ResourceController resourceInfo start , current user is " + user.getUserId());
        
        ResourcePageDto dto = new ResourcePageDto();
        resource.setStatue(Constants.YES);
        dto.setCurPage(resource.getCurPage());
        dto.setPageCount(resource.getPageCount());
        dto.setCondition(resource);
        dto = resourceService.listAllResourceByPage(dto);
        model.addAttribute(Constants.DOMAIN, resource);
        model.addAttribute(Constants.RESULT, dto);
        return "user/view/resourceInfo";
    }
    
    /**
     * 新增资源视图
     * 教师权限
     */
    @RequestMapping({ "/resourceInfoAddView" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    public String resourceInfoAddView(HttpSession session, Model model) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("ResourceController resourceInfoAddView start , current user is " + user.getUserId());
        
        List<MajorDto> dtos = majorService.queryAllMajor();
        model.addAttribute(Constants.RESULT, dtos);
        model.addAttribute("courseTypes", dictService.getDictByType(Constants.DICT_RESOURCE_TYPE));
        
        return "user/view/resourceInfoAdd";
    }
    /**
     * 新增资源
     * 教师权限
     */
    @RequestMapping({ "/resourceInfoAdd" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    @ResponseBody
    public ResultData<?> resourceInfoAdd(HttpSession session, Model model,ResourceVo resource) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("ResourceController resourceInfoAdd start , current user is " + user.getUserId());
        ResultData<?> rst = new ResultData<>();
        File oldFile = null;
        File newFile = null;
        try {
            MultipartFile file = resource.getResourceFile();
            String fileName = file.getOriginalFilename();
            //先进行视频格式转换
            if(fileName.toLowerCase().endsWith(".swf")){
                resource.setIn(file.getInputStream());
            }else if(fileName.toLowerCase().endsWith(".flv")){
                resource.setIn(file.getInputStream());
            }else if(fileName.toLowerCase().endsWith(".pdf")){
                resource.setIn(file.getInputStream());
                String tmpDir = System.getProperty(Constants.YC_ROOT_PATH)+Constants.TMP_DIR;
                File tmp = new File(tmpDir);
                if(!tmp.exists()){
                    tmp.mkdirs();
                }
                long curTime = System.currentTimeMillis();
                String oldFilePath = tmpDir+user.getUserId()+curTime;
                oldFile = new File(oldFilePath);
                logger.info("tmp:"+oldFile.getAbsolutePath());
                file.transferTo(oldFile);
                String newFilePath = PdfConvertUtils.pdf2SWF(oldFile.getAbsolutePath());
                if(newFilePath==null){
                    throw new SchoolException(SchoolErrorType.err_upload_file, null);
                }
                newFile = new File(newFilePath);
                resource.setNewFilePath(newFilePath);
                FileInputStream in = new FileInputStream(oldFilePath);
                resource.setIn(in);
            }else {
                String tmpDir = System.getProperty(Constants.ROOT_PATH)+Constants.TMP_DIR;
                File tmp = new File(tmpDir);
                if(!tmp.exists()){
                    tmp.mkdirs();
                }
                long curTime = System.currentTimeMillis();
                String oldFilePath = tmpDir+user.getUserId()+curTime+fileName;
                oldFile = new File(oldFilePath);
                logger.info("tmp:"+oldFile.getAbsolutePath());
                file.transferTo(oldFile);
                String newFilePath = VideoConvertUtils.videoConvert(oldFile.getAbsolutePath());
                if(newFilePath==null){
                    throw new SchoolException(SchoolErrorType.err_upload_file, null);
                }
                newFile = new File(newFilePath);
                resource.setNewFilePath(newFilePath);
                FileInputStream in = new FileInputStream(oldFilePath);
                resource.setIn(in);
            }
            resource.setName(fileName);
            resource.setFileSize(file.getSize());
            resource.setUserId(user.getUserId());
            resource.setMajorId(user.getMajorId());
            resourceService.uploadResource(resource);
        } catch (ServiceException e) {
            logger.error("",e);
            rst.setStatus(e.getErrorCode());
            rst.setMsg(e.getMsg());
        } catch (Exception e) {
            logger.error("",e);
            rst.setStatus(Constants.SYSTEM_ERROR_CODE);
            rst.setMsg(Constants.SYSTEM_ERROR_MSG);
        } finally{
            if(oldFile!=null&&oldFile.exists()){
                oldFile.delete();
            }
            if(newFile!=null&&newFile.exists()){
                newFile.delete();
            }
        }
        return rst;
    }
    /**
     * 删除资源
     * 教师权限
     */
    @RequestMapping({ "/resourceInfoDelete/{resourceId}" })
    @SchoolValidate(accessUser=Constants.TEACHER)
    @ResponseBody
    public ResultData<?> resourceInfoDelete(HttpSession session, @PathVariable long resourceId) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("ResourceController resourceInfoDelete start , current user is " + user.getUserId());
        ResultData<?> rst = new ResultData<>();
        
        try {
            boolean b = resourceService.deleteResource(resourceId);
            if(!b){
                rst.setStatus(Constants.SYSTEM_ERROR_CODE);
                rst.setMsg("删除失败！");
                return rst;
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
     * 查询下载资源信息
     * 判断用户是否有权限下载
     */
    @RequestMapping("/queryDownloadResource/{resourceId}")
    @ResponseBody
    public ResultData<String> queryDownloadResource(HttpSession session){
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("ResourceController queryDownloadResource start , current user is " + user.getUserId());
        ResultData<String> result = new ResultData<>();
        //此处可添加判断逻辑，暂无
        result.setData("downloadResource");
        return result;
    }
    /**
     * 下载资源
     */
    @RequestMapping("/downloadResource/{resourceId}")
    public void downloadResource(@PathVariable long resourceId,HttpServletResponse response,HttpSession session){
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("ResourceController downloadResource start , current user is " + user.getUserId());
        ResultData<?> result = new ResultData<>();
        try {
            ResourceDto dto = resourceService.queryResource(resourceId);
            response.setContentType("application/x-msdownload");
            response.addHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(dto.getName(),"UTF-8"));
            dto.setOut(response.getOutputStream());
            dto.setCurrentUserId(user.getUserId());
            resourceService.downloadResource(dto);
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
    }
    /**
     * 评价资源
     * 学生权限
     */
    @RequestMapping({ "/scoreResource" })
    @SchoolValidate(accessUser=Constants.STUDENT)
    @ResponseBody
    public ResultData<?> scoreResource(HttpSession session, ScoreVo score) {
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        logger.info("ResourceController scoreResource start , current user is " + user.getUserId());
        ResultData<?> rst = new ResultData<>();
        
        try {
            score.setUserId(user.getUserId());
            score.setRefType(Constants.REF_TYPE_RESOURCE);
            boolean b = scoreService.scoreResource(score);
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
