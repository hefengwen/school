/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.impl
 * Type:    ScoreServiceImpl
 * Author:  hefengwen
 * Date:    2017-01-09 15:49:00
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yckj.school.common.util.PropertyUtils;
import com.yckj.school.dao.CourseMapper;
import com.yckj.school.dao.ResourceMapper;
import com.yckj.school.dao.ScoreMapper;
import com.yckj.school.domain.Course;
import com.yckj.school.domain.Resource;
import com.yckj.school.domain.Score;
import com.yckj.school.domain.ScoreExample;
import com.yckj.school.exception.SchoolErrorType;
import com.yckj.school.exception.SchoolException;
import com.yckj.school.service.ScoreService;
import com.yckj.school.service.dto.ScoreDto;

/**
 * @author hefengwen
 */
@Service
public class ScoreServiceImpl implements ScoreService{
    private static final Logger logger = LoggerFactory.getLogger(ScoreServiceImpl.class);
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public ScoreDto queryScore(ScoreDto dto) {
        try {
            ScoreExample se = new ScoreExample();
            ScoreExample.Criteria sc = se.createCriteria();
            sc.andUserIdEqualTo(dto.getUserId());
            sc.andRefTypeEqualTo(dto.getRefType());
            sc.andRefIdEqualTo(dto.getRefId());
            List<Score> scores = scoreMapper.selectByExample(se);
            if(CollectionUtils.isNotEmpty(scores)){
                PropertyUtils.propertyCopy(dto, scores.get(0));
                return dto;
            }
            return null;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean scoreCourse(ScoreDto dto) {
        ScoreExample se = new ScoreExample();
        ScoreExample.Criteria sc = se.createCriteria();
        sc.andUserIdEqualTo(dto.getUserId());
        sc.andRefIdEqualTo(dto.getRefId());
        sc.andRefTypeEqualTo(dto.getRefType());
        List<Score> scores = scoreMapper.selectByExample(se);
        if(CollectionUtils.isNotEmpty(scores)){
            return true;
        }
        
        dto.setCreateTime(new Date());
        scoreMapper.insertSelective(dto);
        Course c = new Course();
        c.setCourseId(dto.getRefId());
        c.setScore(dto.getScore());
        courseMapper.addScore(c);
        return true;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean scoreResource(ScoreDto dto) {
        ScoreExample se = new ScoreExample();
        ScoreExample.Criteria sc = se.createCriteria();
        sc.andUserIdEqualTo(dto.getUserId());
        sc.andRefIdEqualTo(dto.getRefId());
        sc.andRefTypeEqualTo(dto.getRefType());
        List<Score> scores = scoreMapper.selectByExample(se);
        if(CollectionUtils.isNotEmpty(scores)){
            return true;
        }
        
        dto.setCreateTime(new Date());
        scoreMapper.insertSelective(dto);
        Resource r = new Resource();
        r.setResourceId(dto.getRefId());
        r.setScore(dto.getScore());
        resourceMapper.addScore(r);
        return true;
    }

}
