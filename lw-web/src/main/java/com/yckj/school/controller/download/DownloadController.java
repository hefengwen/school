/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.controller.download
 * Type:    DownloadController
 * Author:  hefengwen
 * Date:    2016-12-26 17:00:47
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.controller.download;

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

import com.yckj.school.annotation.SchoolValidate;
import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.constant.DbConstants;
import com.yckj.school.domain.ResourceVo;
import com.yckj.school.exception.SchoolErrorType;
import com.yckj.school.exception.SchoolException;
import com.yckj.school.service.DictService;
import com.yckj.school.service.MajorService;
import com.yckj.school.service.ResourceService;
import com.yckj.school.service.ScoreService;
import com.yckj.school.service.dto.MajorDto;
import com.yckj.school.service.dto.ResourceDto;
import com.yckj.school.service.dto.ResourcePageDto;
import com.yckj.school.service.dto.ScoreDto;
import com.yckj.school.service.dto.UserDto;

/**
 * @author hefengwen
 */
@Controller
@RequestMapping({ "/download" })
public class DownloadController {
    private static final Logger logger = LoggerFactory.getLogger(DownloadController.class);
    
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
    @RequestMapping({ "/downloadInfo" })
    @SchoolValidate(ignoreSession=true)
    public String downloadInfo(HttpSession session, Model model,ResourceVo resource) {
        logger.info("DownloadController downloadInfo start ... ...");
        
        ResourcePageDto dto = new ResourcePageDto();
        resource.setStatue(Constants.YES);
        if(resource.getOrderByColumn()==null){
            resource.setOrderByColumn(DbConstants.RESOURCE_ID);
            resource.setOrderType(Constants.ORDER_ASC);
        }
        dto.setCurPage(resource.getCurPage());
        dto.setPageCount(resource.getPageCount());
        dto.setCondition(resource);
        dto = resourceService.listAllResourceByPage(dto);
        model.addAttribute(Constants.DOMAIN, resource);
        model.addAttribute(Constants.RESULT, dto);
        
        List<MajorDto> majors = majorService.queryAllMajor();
        model.addAttribute("majors", majors);
        
        model.addAttribute("resourceTypes", dictService.getDictByType(Constants.DICT_RESOURCE_TYPE));
        model.addAttribute("orderByColumns", dictService.getDictByType(Constants.DICT_RESOURCE_ORDER_TYPE));
        return "download/view/downloadInfo";
    }
    /**
     * 资源预览
     */
    @RequestMapping({ "/resourceInfo/{resourceId}" })
    @SchoolValidate(ignoreSession=true)
    public String resourceInfo(HttpSession session, Model model,HttpServletRequest request,@PathVariable long resourceId) {
        Object useAddress = request.getAttribute(Constants.USE_ADDRESS);
        if(useAddress!=null){
            return this.resourceInfo2(session, model, resourceId);
        }else{
            return this.resourceInfo1(session, model, request, resourceId);
        }
    }
    
    /**
     * 方式1:使用本地缓存(不能直接访问文件服务器时使用)
     */
    public String resourceInfo1(HttpSession session, Model model,HttpServletRequest request,@PathVariable long resourceId) {
        logger.info("DownloadController resourceInfo start ... ...");
        ResourceDto dto = resourceService.queryResource(resourceId);
        if(dto==null){
            logger.error(resourceId+" resource文件不存在");
            throw new SchoolException(SchoolErrorType.err_file_not_exist, null);
        }
        model.addAttribute(Constants.RESULT,dto);
        
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        if(user!=null){
            ScoreDto sd = new ScoreDto();
            sd.setUserId(user.getUserId());
            sd.setRefType(Constants.REF_TYPE_RESOURCE);
            sd.setRefId(resourceId);
            sd = scoreService.queryScore(sd);
            model.addAttribute("score",sd);
        }
        
        if("flv".equalsIgnoreCase(dto.getSuffix())||"mp4".equalsIgnoreCase(dto.getSuffix())){
            BufferedOutputStream out = null;
            try {
                String resourceRootDir = System.getProperty(Constants.LW_ROOT_PATH)+Constants.RESOURCE_DIR;
                String resourcePath = request.getContextPath()+"/"+Constants.RESOURCE_DIR;
                String resourceName = resourceId+"."+dto.getSuffix();
                logger.info("bookRootDir:"+resourceRootDir);
                logger.info("resourcePath:"+resourcePath);
                File resource = new File(resourceRootDir,resourceName);
                if(resource.exists()&&!resource.isDirectory()){
                    logger.info(dto.getName()+"文件存在，无需下载");
                    model.addAttribute("resourcePath", Constants.RESOURCE_DIR+resourceName);
                    return "download/view/resourceInfo";
                }
                File rootDir = new File(resourceRootDir);
                if(!rootDir.exists()||!rootDir.isDirectory()){
                    rootDir.mkdirs();
                }
                out = new BufferedOutputStream(new FileOutputStream(resource));
                dto.setOut(out);
                resourceService.resourcePreView1(dto);
                resource.createNewFile();
                if(!resource.exists()){
                    logger.error(resourceId+" resource文件下载失败");
                    throw new SchoolException(SchoolErrorType.err_system, null);
                }
                model.addAttribute("resourcePath", Constants.RESOURCE_DIR+resource.getName());
            }
            catch (IOException e) {
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
        return "download/view/resourceInfo";
    }
    /**
     * 方式2:直接访问文件服务器
     */
    public String resourceInfo2(HttpSession session, Model model,@PathVariable long resourceId) {
        logger.info("DownloadController resourceInfo start ... ...");
        ResourceDto dto = resourceService.queryResource(resourceId);
        if(dto==null){
            logger.error(resourceId+" resource文件不存在");
            throw new SchoolException(SchoolErrorType.err_file_not_exist, null);
        }
        model.addAttribute(Constants.RESULT,dto);
        
        UserDto user = (UserDto) session.getAttribute(Constants.CURRENT_USER);
        if(user!=null){
            ScoreDto sd = new ScoreDto();
            sd.setUserId(user.getUserId());
            sd.setRefType(Constants.REF_TYPE_RESOURCE);
            sd.setRefId(resourceId);
            sd = scoreService.queryScore(sd);
            model.addAttribute("score",sd);
        }
        String address = resourceService.resourcePreView2(dto);
        model.addAttribute("address", address);
        return "download/view/resourceInfo";
    }
    /**
     * 资源预览(可以直接使用字节流方式预览)
     */
    @RequestMapping("/resourcePreView/{resourceId}")
    @SchoolValidate(ignoreSession=true)
    public void resourcePreView(HttpSession session, @PathVariable long resourceId,HttpServletResponse response){
        logger.info("DownloadController resourcePreView start ... ...");
        try {
            ResourceDto dto = resourceService.queryResource(resourceId);
            dto.setOut(response.getOutputStream());
            resourceService.resourcePreView1(dto);
        } catch (Exception e) {
            logger.error("",e);
        }
    } 
}
