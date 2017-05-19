/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.impl
 * Type:    MajorServiceImpl
 * Author:  hefengwen
 * Date:    2016-12-16 14:54:51
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.impl;

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

import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.util.PropertyUtils;
import com.yckj.school.dao.MajorMapper;
import com.yckj.school.dao.UserMapper;
import com.yckj.school.domain.Major;
import com.yckj.school.domain.MajorExample;
import com.yckj.school.domain.User;
import com.yckj.school.domain.UserExample;
import com.yckj.school.exception.SchoolErrorType;
import com.yckj.school.exception.SchoolException;
import com.yckj.school.service.MajorService;
import com.yckj.school.service.dto.MajorDto;
import com.yckj.school.service.dto.MajorPageDto;

/**
 * @author hefengwen
 */
@Service
public class MajorServiceImpl implements MajorService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public boolean addMajor(MajorDto dto) {
        logger.info("MajorServiceImpl addMajor start ... ...");
        try {
            Major major = new Major();
            PropertyUtils.propertyCopy(major, dto);
            major.setCreateTime(new Date());
            int cnt = majorMapper.insertSelective(major);
            return cnt==1;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }

    @Override
    public boolean deleteMajor(long majorId) {
        logger.info("MajorServiceImpl deleteMajor start ... ...");
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andMajorIdEqualTo(majorId);
        criteria.andStatueEqualTo(Constants.YES);
        List<User> users = userMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(users)){
            logger.error("该专业下有教师存在，不能删除");
            throw new SchoolException(SchoolErrorType.err_exist_teacher, null);
        }
        Major major = new Major();
        major.setMajorId(majorId);
        major.setStatue(Constants.NO);
        int cnt = majorMapper.updateByPrimaryKeySelective(major);
        return cnt == 1;
    }

    @Override
    public boolean updateMajor(MajorDto dto) {
        logger.info("MajorServiceImpl deleteMajor start ... ...");
        try {
            Major major = new Major();
            PropertyUtils.propertyCopy(major, dto);
            int cnt = majorMapper.updateByPrimaryKeySelective(major);
            return cnt==1;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }

    @Override
    public MajorDto queryMajor(long majorId) {
        logger.info("MajorServiceImpl queryMajor start ... ...");
        try {
            Major major = majorMapper.selectByPrimaryKey(majorId);
            if(major==null)
                return null;
            MajorDto dto = new MajorDto();
            PropertyUtils.propertyCopy(dto, major);
            return dto;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }
    
    @Override
    public List<MajorDto> queryAllMajor() {
        logger.info("MajorServiceImpl queryAllMajor start ... ...");
        try {
            MajorExample example = new MajorExample();
            MajorExample.Criteria criteria = example.createCriteria();
            criteria.andStatueEqualTo(Constants.YES);
            List<Major> majors = majorMapper.selectByExample(example);
            
            List<MajorDto> dtos = new ArrayList<>();
            for(Major major:majors){
                MajorDto dto = new MajorDto();
                PropertyUtils.propertyCopy(dto, major);
                dtos.add(dto);
            }
            return dtos;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }

    @Override
    public MajorPageDto listAllMajorByPage(MajorPageDto dto) {
        logger.info("MajorServiceImpl listAllMajorByPage start ... ...");
        try {
            Map<String,Object> map = new HashMap<>();
            map.put(Constants.START, (dto.getCurPage()-1)*dto.getPageCount());
            map.put(Constants.COUNT, dto.getPageCount());
            map.putAll(PropertyUtils.objectToMap(dto.getCondition()));
            
            if(dto.getNeedTotal()){
                int totalCount = majorMapper.selectTotalCount(map);
                dto.setTotalCount(totalCount);
                dto.setTotalPageCount(totalCount%dto.getPageCount()==0?totalCount/dto.getPageCount():totalCount/dto.getPageCount()+1);
            }
            
            List<Major> majors = majorMapper.selectByPage(map);
            List<Map<String, Object>> majorList = new ArrayList<>();
            for(Major major:majors){
                Map<String, Object> m = PropertyUtils.objectToMap(major);
                majorList.add(m);
            }
            dto.setMajorList(majorList);
        } catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
        return dto;
    }

}
