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

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yckj.school.common.util.PropertyUtils;
import com.yckj.school.dao.AnswerMapper;
import com.yckj.school.domain.Answer;
import com.yckj.school.exception.SchoolErrorType;
import com.yckj.school.exception.SchoolException;
import com.yckj.school.service.AnswerService;
import com.yckj.school.service.dto.AnswerDto;

/**
 * @author hefengwen
 */
@Service
public class AnswerServiceImpl implements AnswerService{
    private static final Logger logger = LoggerFactory.getLogger(AnswerServiceImpl.class);
    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public List<AnswerDto> queryAnswersByPage(Map<String, Object> param) {
        // TODO Write your code here
        return null;
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
        // TODO Write your code here
        return 0;
    }

}
