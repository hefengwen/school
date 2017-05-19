/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.impl
 * Type:    SaveServiceImpl
 * Author:  hefengwen
 * Date:    2017-01-07 23:36:25
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.util.PropertyUtils;
import com.yckj.school.dao.CourseMapper;
import com.yckj.school.dao.ResourceMapper;
import com.yckj.school.dao.SaveMapper;
import com.yckj.school.domain.Course;
import com.yckj.school.domain.Resource;
import com.yckj.school.domain.Save;
import com.yckj.school.domain.SaveExample;
import com.yckj.school.exception.SchoolErrorType;
import com.yckj.school.exception.SchoolException;
import com.yckj.school.service.CourseService;
import com.yckj.school.service.ResourceService;
import com.yckj.school.service.SaveService;
import com.yckj.school.service.dto.CourseDto;
import com.yckj.school.service.dto.ResourceDto;
import com.yckj.school.service.dto.SavePageDto;

/**
 * @author hefengwen
 */
@Service
public class SaveServiceImpl implements SaveService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private CourseService courseService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private SaveMapper saveMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean subscribeCourse(CourseDto dto) {
        SaveExample example = new SaveExample();
        SaveExample.Criteria criteria = example.createCriteria();
        criteria.andRefIdEqualTo(dto.getCourseId());
        criteria.andRefTypeEqualTo(Constants.REF_TYPE_COURSE);
        criteria.andUserIdEqualTo(dto.getCurrentUserId());
        List<Save> saves = saveMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(saves)){
            throw new SchoolException(SchoolErrorType.err_file_has_saved, null);
        }
        
        courseMapper.addSaveCnt(dto);
        
        Save save = new Save();
        save.setRefId(dto.getCourseId());
        save.setRefType(Constants.REF_TYPE_COURSE);
        save.setUserId(dto.getCurrentUserId());
        save.setCreateTime(new Date());
        return saveMapper.insertSelective(save)==1;
    }

    @Override
    public SavePageDto listAllSaveByPage(SavePageDto dto) {
        logger.info("SaveServiceImpl listAllSaveByPage start ... ...");
        try {
            Map<String, Object> map = new HashMap<>();
            map.put(Constants.START, (dto.getCurPage() - 1) * dto.getPageCount());
            map.put(Constants.COUNT, dto.getPageCount());
            map.putAll(PropertyUtils.objectToMap(dto.getCondition()));

            if (dto.getNeedTotal()) {
                int totalCount = saveMapper.selectTotalCount(map);
                dto.setTotalCount(totalCount);
                dto.setTotalPageCount(totalCount % dto.getPageCount() == 0 ? totalCount / dto.getPageCount()
                        : totalCount / dto.getPageCount() + 1);
            }

            List<Save> saves = saveMapper.selectByPage(map);
            List<Map<String, Object>> saveList = new ArrayList<>();
            for (Save save : saves) {
                Map<String, Object> m = PropertyUtils.objectToMap(save);
                if (Constants.REF_TYPE_COURSE.equals(save.getRefType())) {
                    CourseDto course = courseService.queryCourse(save.getRefId());
                    Map<String, Object> cm = PropertyUtils.objectToMap(course);
                    Map<String, Object> bm = PropertyUtils.objectToMap(course.getBook());
                    m.put("course", cm);
                    m.put("book", bm);
                }
                else {
                    ResourceDto resource = resourceService.queryResource(save.getRefId());
                    Map<String, Object> rm = PropertyUtils.objectToMap(resource);
                    m.put("user", PropertyUtils.objectToMap(resource.getUser()));
                    m.put("major", PropertyUtils.objectToMap(resource.getMajor()));
                    m.put("fileSize", new DecimalFormat("######0.00").format(resource.getFileSize()*1.0/Constants.MIN_FILE_SIZE));
                    m.put("resource", rm);
                }
                saveList.add(m);
            }
            dto.setSaveList(saveList);
        }
        catch (Exception e) {
            logger.error("", e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
        return dto;
    }

    @Override
    public boolean saveInfoDelete(long saveId) {
        Save save = saveMapper.selectByPrimaryKey(saveId);
        if(save==null){
            throw new SchoolException(SchoolErrorType.err_file_not_exist, null);
        }
        saveMapper.deleteByPrimaryKey(saveId);
        if(Constants.REF_TYPE_COURSE.equals(save.getRefType())){
            Course course = new Course();
            course.setCourseId(save.getRefId());
            courseMapper.subSaveCnt(course);
        }else{
            Resource resource = new Resource();
            resource.setResourceId(save.getRefId());;
            resourceMapper.subSaveCnt(resource);
        }
        return true;
    }

}
