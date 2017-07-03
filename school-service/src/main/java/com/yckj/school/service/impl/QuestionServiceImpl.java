/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.impl
 * Type:    QuestionServiceImpl
 * Author:  hefengwen
 * Date:    2017-05-19 14:58:17
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
import com.yckj.school.dao.CourseMapper;
import com.yckj.school.dao.QuestionMapper;
import com.yckj.school.dao.UserMapper;
import com.yckj.school.domain.Course;
import com.yckj.school.domain.Question;
import com.yckj.school.domain.User;
import com.yckj.school.exception.SchoolErrorType;
import com.yckj.school.exception.SchoolException;
import com.yckj.school.service.AnswerService;
import com.yckj.school.service.QuestionService;
import com.yckj.school.service.dto.AnswerDto;
import com.yckj.school.service.dto.AnswerPageDto;
import com.yckj.school.service.dto.CourseDto;
import com.yckj.school.service.dto.QuestionDto;
import com.yckj.school.service.dto.QuestionPageDto;
import com.yckj.school.service.dto.UserDto;

/**
 * @author hefengwen
 */
@Service
public class QuestionServiceImpl implements QuestionService{
    private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AnswerService answerService;

    @Override
    public QuestionPageDto listAllQuestionByPage(QuestionPageDto dto) {
        logger.info("QuestionServiceImpl queryQuestionsByPage start ... ...");
        try {
            Map<String,Object> map = new HashMap<>();
            map.put(Constants.START, (dto.getCurPage()-1)*dto.getPageCount());
            map.put(Constants.COUNT, dto.getPageCount());
            map.putAll(PropertyUtils.objectToMap(dto.getCondition()));
            
            if(dto.getNeedTotal()){
                int totalCount = questionMapper.selectTotalCount(map);
                dto.setTotalCount(totalCount);
                dto.setTotalPageCount(totalCount%dto.getPageCount()==0?totalCount/dto.getPageCount():totalCount/dto.getPageCount()+1);
            }
            
            List<Question> questions = questionMapper.selectByPage(map);
            List<Map<String, Object>> questionList = new ArrayList<>();
            for(Question question:questions){
                Map<String, Object> m = PropertyUtils.objectToMap(question);
                User u = userMapper.selectByPrimaryKey(question.getUserId());
                Course c = courseMapper.selectByPrimaryKey(question.getCourseId());
                m.put("quser", u);
                m.put("course", c);
                questionList.add(m);
            }
            dto.setQuestionList(questionList);
        } catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
        return dto;
    }

    @Override
    public QuestionDto queryQuestionById(long qid) {
        QuestionDto dto = new QuestionDto();
        try {
            Question q = questionMapper.selectByPrimaryKey(qid);
            if(q==null)
                return null;
            PropertyUtils.propertyCopy(dto, q);
            //获取课程信息
            Course course = courseMapper.selectByPrimaryKey(q.getCourseId());
            CourseDto courseDto = new CourseDto();
            PropertyUtils.propertyCopy(courseDto, course);
            dto.setCourse(courseDto);
            //获取提问者信息
            User user = userMapper.selectByPrimaryKey(q.getUserId());
            UserDto userDto = new UserDto();
            PropertyUtils.propertyCopy(userDto, user);
            dto.setUser(userDto);
            //获取回答信息
            AnswerPageDto apdto = new AnswerPageDto();
            AnswerDto adto = new AnswerDto();
            adto.setQuesId(qid);
            apdto.setCondition(adto);
            apdto.setPageCount(Integer.MAX_VALUE);
            dto.setAnswers(answerService.queryAnswersByPage(apdto));
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
        return dto;
    }

    @Override
    public int saveQuestion(QuestionDto dto) {
        Question q = new Question();
        try {
            PropertyUtils.propertyCopy(q, dto);
            q.setCreateTime(new Date());
            questionMapper.insertSelective(q);
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
        return 0;
    }

}
