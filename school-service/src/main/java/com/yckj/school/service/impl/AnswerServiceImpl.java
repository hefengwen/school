/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.impl
 * Type:    AnswerServiceImpl
 * Author:  hefengwen
 * Date:    2017-05-19 14:58:54
 *
 * Copyright (c) 2017 yckj. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.util.PropertyUtils;
import com.yckj.school.dao.AnswerMapper;
import com.yckj.school.dao.UserMapper;
import com.yckj.school.domain.Answer;
import com.yckj.school.domain.User;
import com.yckj.school.exception.SchoolErrorType;
import com.yckj.school.exception.SchoolException;
import com.yckj.school.service.AnswerService;
import com.yckj.school.service.dto.AnswerDto;
import com.yckj.school.service.dto.AnswerPageDto;

/**
 * @author hefengwen
 */
@Service
public class AnswerServiceImpl implements AnswerService{
    private static final Logger logger = LoggerFactory.getLogger(AnswerServiceImpl.class);
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public AnswerPageDto queryAnswersByPage(AnswerPageDto dto) {
        logger.info("AnswerServiceImpl queryAnswersByPage start ... ...");
        try {
            Map<String,Object> map = new HashMap<>();
            map.put(Constants.START, (dto.getCurPage()-1)*dto.getPageCount());
            map.put(Constants.COUNT, dto.getPageCount());
            map.putAll(PropertyUtils.objectToMap(dto.getCondition()));
            
            if(dto.getNeedTotal()){
                int totalCount = answerMapper.selectTotalCount(map);
                dto.setTotalCount(totalCount);
                dto.setTotalPageCount(totalCount%dto.getPageCount()==0?totalCount/dto.getPageCount():totalCount/dto.getPageCount()+1);
            }
            
            List<Answer> answers = answerMapper.selectByPage(map);
            List<Map<String, Object>> answerList = new ArrayList<>();
            for(Answer answer:answers){
                Map<String, Object> m = PropertyUtils.objectToMap(answer);
                User u = userMapper.selectByPrimaryKey(answer.getUserId());
                m.put("auser", u);
                answerList.add(m);
            }
            dto.setAnswerList(answerList);
        } catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
        return dto;
    }

    @Override
    public AnswerDto queryAnswerById(long aid) {
        AnswerDto dto = new AnswerDto();
        try {
            Answer answer = answerMapper.selectByPrimaryKey(aid);
            PropertyUtils.propertyCopy(dto, answer);
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
        return dto;
    }

    @Override
    public int saveAnswer(AnswerDto dto) {
        Answer a = new Answer();
        try {
            PropertyUtils.propertyCopy(a, dto);
            a.setCreateTime(new Date());
            answerMapper.insertSelective(a);
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
        return 0;
    }

}
