/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service
 * Type:    QuestionService
 * Author:  hefengwen
 * Date:    2017-05-19 14:48:27
 *
 * Copyright (c) 2017 yckj. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service;

import java.util.List;
import java.util.Map;

import com.yckj.school.service.dto.QuestionDto;

/**
 * @author hefengwen
 */
public interface QuestionService {
    /**
     * 分页查询提问列表
     * @param param
     * @return
     */
    List<QuestionDto> queryQuestionsByPage(Map<String,Object> param);
    /**
     * 查询提问详情
     * @param dto
     * @return
     */
    QuestionDto queryQuestionById(long qid);
    /**
     * 新增提问
     * @param dto
     * @return
     */
    int saveQuestion(QuestionDto dto);
}
