/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.impl
 * Type:    ResourceServiceImpl
 * Author:  hefengwen
 * Date:    2016-12-16 16:21:24
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.util.CommonUtils;
import com.yckj.school.common.util.PropertyUtils;
import com.yckj.school.dao.DownloadMapper;
import com.yckj.school.dao.MajorMapper;
import com.yckj.school.dao.ResourceMapper;
import com.yckj.school.dao.SaveMapper;
import com.yckj.school.dao.UserMapper;
import com.yckj.school.domain.Download;
import com.yckj.school.domain.Major;
import com.yckj.school.domain.Resource;
import com.yckj.school.domain.Save;
import com.yckj.school.domain.SaveExample;
import com.yckj.school.domain.User;
import com.yckj.school.exception.SchoolErrorType;
import com.yckj.school.exception.SchoolException;
import com.yckj.school.service.FastDFSService;
import com.yckj.school.service.ResourceService;
import com.yckj.school.service.dto.ResourceDto;
import com.yckj.school.service.dto.ResourcePageDto;

/**
 * @author hefengwen
 */
@Service
public class ResourceServiceImpl implements ResourceService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private FastDFSService fastDFSService;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private DownloadMapper downloadMapper;
    @Autowired
    private SaveMapper saveMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean addResource(ResourceDto dto) {
        logger.info("ResourceServiceImpl addResource start ... ...");
        try {
//            Resource resource = new Resource();
//            PropertyUtils.propertyCopy(resource, dto);
            dto.setSuffix(CommonUtils.getFileExt(dto.getName()));
            dto.setCreateTime(new Date());
            int cnt = resourceMapper.insertSelective(dto);
            return cnt==1;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean deleteResource(long resourceId) {
        logger.info("ResourceServiceImpl deleteResource start ... ...");
        ResourceDto dto = this.queryResource(resourceId);
        if(dto==null){
            throw new SchoolException(SchoolErrorType.err_file_not_exist, null);
        }
        Resource resource = new Resource();
        resource.setResourceId(resourceId);
        resource.setStatue(Constants.NO);
        int cnt = resourceMapper.updateByPrimaryKeySelective(resource);
        if(cnt!=1){
            throw new SchoolException(SchoolErrorType.err_delete_file, null);
        }
        this.removeResource(dto.getOriUrl());
        if(StringUtils.isNotEmpty(dto.getSwfUrl())||!dto.getOriUrl().equalsIgnoreCase(dto.getSwfUrl())){
            this.removeResource(dto.getSwfUrl());
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean updateResource(ResourceDto dto) {
        logger.info("ResourceServiceImpl updateResource start ... ...");
        try {
//            Resource resource = new Resource();
//            PropertyUtils.propertyCopy(resource, dto);
            int cnt = resourceMapper.updateByPrimaryKeySelective(dto);
            return cnt==1;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }

    @Override
    public ResourceDto queryResource(long resourceId) {
        logger.info("ResourceServiceImpl queryResource start ... ...");
        try {
            Resource resource = resourceMapper.selectByPrimaryKey(resourceId);
            if(resource==null)
                return null;
            ResourceDto dto = new ResourceDto();
            PropertyUtils.propertyCopy(dto, resource);
            User user = userMapper.selectByPrimaryKey(resource.getUserId());
            dto.setUser(user);
            Major major = majorMapper.selectByPrimaryKey(resource.getMajorId());
            dto.setMajor(major);
            return dto;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }

    @Override
    public ResourcePageDto listAllResourceByPage(ResourcePageDto dto) {
        logger.info("ResourceServiceImpl listAllResourceByPage start ... ...");
        try {
            Map<String,Object> map = new HashMap<>();
            map.put(Constants.START, (dto.getCurPage()-1)*dto.getPageCount());
            map.put(Constants.COUNT, dto.getPageCount());
            map.putAll(PropertyUtils.objectToMap(dto.getCondition()));
            
            if(dto.getNeedTotal()){
                int totalCount = resourceMapper.selectTotalCount(map);
                dto.setTotalCount(totalCount);
                dto.setTotalPageCount(totalCount%dto.getPageCount()==0?totalCount/dto.getPageCount():totalCount/dto.getPageCount()+1);
            }
            
            List<Resource> resources = resourceMapper.selectByPage(map);
            List<Map<String, Object>> resourceList = new ArrayList<>();
            for(Resource resource:resources){
                Map<String, Object> m = PropertyUtils.objectToMap(resource);
                User user = userMapper.selectByPrimaryKey(resource.getUserId());
                m.put("userName", user.getName());
                Major major = majorMapper.selectByPrimaryKey(resource.getMajorId());
                m.put("majorName", major.getName());
                m.put("fileSize", new DecimalFormat("######0.00").format(resource.getFileSize()*1.0/Constants.MIN_FILE_SIZE));
                resourceList.add(m);
            }
            dto.setResourceList(resourceList);
        } catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
        return dto;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean uploadResource(ResourceDto dto) {
        logger.info("ResourceServiceImpl uploadResource start ... ...");
        InputStream in = null;
        InputStream newIn = null;
        String fileId = null;
        try {
            in = dto.getIn();
            if(Constants.DEFAULT_FILE_SIZE>=dto.getFileSize()){
                byte[] bytes = new byte[dto.getFileSize().intValue()];
                in.read(bytes);
                fileId = fastDFSService.uploadFile(bytes, dto.getName());
            }else{
                byte[] bytes = new byte[Constants.DEFAULT_FILE_SIZE];
                in.read(bytes);
                fileId = fastDFSService.uploadAppendFile(bytes, dto.getName());
                int len = 0;
                while((len=in.read(bytes)) != -1){
                    if(len<Constants.DEFAULT_FILE_SIZE){
                        byte[] lastBytes = new byte[len];
                        System.arraycopy(bytes, 0, lastBytes, 0, len);
                        bytes = lastBytes;
                    }
                    int rst = fastDFSService.appendFile(bytes, fileId);
                    if(rst != 0){
                        throw new SchoolException(SchoolErrorType.err_upload_file, null);
                    }
                }
            }
            dto.setOriUrl(fileId);
            logger.info("ResourceServiceImpl uploadOriResource end ... ...");
            if(dto.getNewFilePath()!=null){
                //上传转换后格式文件
                File newFilePath = new File(dto.getNewFilePath());
                newIn = new FileInputStream(newFilePath);
                if(Constants.DEFAULT_FILE_SIZE>=newFilePath.length()){
                    byte[] bytes = new byte[new Long(newFilePath.length()).intValue()];
                    newIn.read(bytes);
                    fileId = fastDFSService.uploadFile(bytes, newFilePath.getName());
                }else{
                    byte[] bytes = new byte[Constants.DEFAULT_FILE_SIZE];
                    newIn.read(bytes);
                    fileId = fastDFSService.uploadAppendFile(bytes, newFilePath.getName());
                    int len = 0;
                    while((len=newIn.read(bytes)) != -1){
                        if(len<Constants.DEFAULT_FILE_SIZE){
                            byte[] lastBytes = new byte[len];
                            System.arraycopy(bytes, 0, lastBytes, 0, len);
                            bytes = lastBytes;
                        }
                        int rst = fastDFSService.appendFile(bytes, fileId);
                        if(rst != 0){
                            throw new SchoolException(SchoolErrorType.err_upload_file, null);
                        }
                    }
                }
                dto.setSwfUrl(fileId);
                logger.info("ResourceServiceImpl uploadSwfResource end ... ...");
            }else{
                dto.setSwfUrl(fileId);
            }
            return this.addResource(dto);
        } catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_upload_file, null);
        } finally{
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("",e);
                }
            }
            if(newIn != null){
                try {
                    newIn.close();
                } catch (IOException e) {
                    logger.error("",e);
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean downloadResource(ResourceDto dto) {
        logger.info("ResourceServiceImpl downloadResource start ... ...");
        OutputStream out = null;
        try {
            out = dto.getOut();
            byte[] bytes = null;
            long offset = 0;
            if(Constants.DEFAULT_FILE_SIZE>=dto.getFileSize()){
                bytes = fastDFSService.downloadFile(dto.getOriUrl(),offset,dto.getFileSize());
                out.write(bytes);
            }else{
                while(offset<dto.getFileSize()){
                    long length = (dto.getFileSize()-offset)<Constants.DEFAULT_FILE_SIZE?(dto.getFileSize()-offset):Constants.DEFAULT_FILE_SIZE;
                    bytes = fastDFSService.downloadFile(dto.getOriUrl(),offset,length);
                    out.write(bytes);
                    offset += Constants.DEFAULT_FILE_SIZE;
                }
            }
            logger.info("download resource "+dto.getName()+" over... ...");
            Download load = new Download();
            load.setRefId(dto.getResourceId());
            load.setRefType(Constants.REF_TYPE_RESOURCE);
            load.setUserId(dto.getCurrentUserId());
            load.setCreateTime(new Date());
            downloadMapper.insertSelective(load);
            resourceMapper.addLoadCnt(dto);
        } catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_download_file, null);
        } 
        return true;
    }
    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean removeResource(String fileId) {
        logger.info("ResourceServiceImpl removeResource start ... ...");
        return fastDFSService.deleteFile(fileId)==0;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean saveResource(ResourceDto dto) {
        logger.info("ResourceServiceImpl saveResource start ... ...");
        SaveExample example = new SaveExample();
        SaveExample.Criteria criteria = example.createCriteria();
        criteria.andRefIdEqualTo(dto.getResourceId());
        criteria.andRefTypeEqualTo(Constants.REF_TYPE_RESOURCE);
        criteria.andUserIdEqualTo(dto.getCurrentUserId());
        List<Save> saves = saveMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(saves)){
            throw new SchoolException(SchoolErrorType.err_file_has_saved, null);
        }
        
        resourceMapper.addSaveCnt(dto);
        
        Save save = new Save();
        save.setRefId(dto.getResourceId());
        save.setRefType(Constants.REF_TYPE_RESOURCE);
        save.setUserId(dto.getCurrentUserId());
        save.setCreateTime(new Date());
        return saveMapper.insertSelective(save)==1;
    }
    
    /**
     * 方式1:使用本地缓存(不能直接访问文件服务器时使用)
     */
    @Override
    public String resourcePreView1(ResourceDto dto) {
        logger.info("ResourceServiceImpl resourcePreView start ... ...");
        String fileId = dto.getOriUrl();
        if(StringUtils.isBlank(fileId))
            return null;
        OutputStream out = null;
        try {
            out = dto.getOut();
            byte[] bytes = null;
            long offset = 0;
            if(Constants.DEFAULT_FILE_SIZE>=dto.getFileSize()){
                bytes = fastDFSService.downloadFile(dto.getOriUrl(),offset,dto.getFileSize());
                out.write(bytes);
            }else{
                while(offset<dto.getFileSize()){
                    long length = (dto.getFileSize()-offset)<Constants.DEFAULT_FILE_SIZE?(dto.getFileSize()-offset):Constants.DEFAULT_FILE_SIZE;
                    bytes = fastDFSService.downloadFile(dto.getOriUrl(),offset,length);
                    out.write(bytes);
                    offset += Constants.DEFAULT_FILE_SIZE;
                }
            }
            out.flush();
            logger.info("download resource "+dto.getName()+" over... ...");
            return "";
        }
        catch (IOException e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }
  /**
  * 方式2:直接访问文件服务器
  */
    @Override
    public String resourcePreView2(ResourceDto dto) {
        logger.info("ResourceServiceImpl resourcePreView start ... ...");
        return fastDFSService.getFileStorage(dto.getSwfUrl()) + dto.getSwfUrl();
    }
}
