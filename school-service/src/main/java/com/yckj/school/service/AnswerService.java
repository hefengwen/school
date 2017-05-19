/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service
 * Type:    AnswerService
 * Author:  hefengwen
 * Date:    2017-05-19 14:48:37
 *
 * Copyright (c) 2017 yckj. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service;

import java.util.List;
import java.util.Map;

import com.yckj.school.service.dto.AnswerDto;
import com.yckj.school.service.dto.QuestionDto;

/**
 * @author hefengwen
 */
public interface AnswerService {
    /**
     * 分页查询回答列表
     * @param param
     * @return
     */
    List<AnswerDto> queryAnswersByPage(Map<String,Object> param);
    /**
     * 查询回答详情
     * @param dto
     * @return
     */
    AnswerDto queryAnswerById(long qid);
    /**
     * 新增回答
     * @param dto
     * @return
     */
    int saveAnswer(AnswerDto dto);
}
