/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.dto
 * Type:    QuestionDto
 * Author:  hefengwen
 * Date:    2017-05-19 14:49:45
 *
 * Copyright (c) 2017 yckj. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.dto;

import java.util.List;

import com.yckj.school.domain.Answer;
import com.yckj.school.domain.Question;

/**
 * @author hefengwen
 */
public class QuestionDto extends Question{
    
    private CourseDto course;
    
    private UserDto user;
    
    private List<AnswerDto> answers;

    /**
     * @return course
     */
    public CourseDto getCourse() {
        return course;
    }

    /**
     * @param course set course
     */
    public void setCourse(CourseDto course) {
        this.course = course;
    }

    /**
     * @return user
     */
    public UserDto getUser() {
        return user;
    }

    /**
     * @param user set user
     */
    public void setUser(UserDto user) {
        this.user = user;
    }

    /**
     * @return answers
     */
    public List<AnswerDto> getAnswers() {
        return answers;
    }

    /**
     * @param answers set answers
     */
    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }
    
}
