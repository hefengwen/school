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

import com.yckj.school.service.dto.QuestionDto;
import com.yckj.school.service.dto.QuestionPageDto;

/**
 * @author hefengwen
 */
public interface QuestionService {
    /**
     * 分页查询提问列表
     * @param param
     * @return
     */
    QuestionPageDto listAllQuestionByPage(QuestionPageDto param);
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
