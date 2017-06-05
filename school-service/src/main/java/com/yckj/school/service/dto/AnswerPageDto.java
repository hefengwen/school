/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.dto
 * Type:    AnswerPageDto
 * Author:  hefengwen
 * Date:    2017-05-31 13:51:44
 *
 * Copyright (c) 2017 yckj. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.dto;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.yckj.school.common.constant.Constants;

/**
 * @author hefengwen
 */
public class AnswerPageDto {
    /**
     * 当前页数
     */
    private Integer curPage = 1;
    /**
     * 每页条数
     */
    private Integer pageCount = Constants.DEFAULT_PAGE_SIZE;
    /**
     * 查询条件
     */
    private AnswerDto condition;
    /**
     * 是否查询总数
     */
    private boolean needTotal = true;
    
    /**
     * 总页数
     */
    private Integer totalPageCount;
    /**
     * 总记录数
     */
    private Integer totalCount;
    /**
     * 课程结果列表
     */
    private List<Map<String,Object>> answerList;
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    /**
     * @return curPage
     */
    public Integer getCurPage() {
        return curPage;
    }

    /**
     * @param curPage set curPage
     */
    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    /**
     * @return pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     * @param pageCount set pageCount
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * @return condition
     */
    public AnswerDto getCondition() {
        return condition;
    }

    /**
     * @param condition set condition
     */
    public void setCondition(AnswerDto condition) {
        this.condition = condition;
    }

    /**
     * @return needTotal
     */
    public boolean getNeedTotal() {
        return needTotal;
    }

    /**
     * @param needTotal set needTotal
     */
    public void setNeedTotal(boolean needTotal) {
        this.needTotal = needTotal;
    }

    /**
     * @return totalPageCount
     */
    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    /**
     * @param totalPageCount set totalPageCount
     */
    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    /**
     * @return totalCount
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount set totalCount
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return courseList
     */
    public List<Map<String, Object>> getAnswerList() {
        return answerList;
    }

    /**
     * @param courseList set courseList
     */
    public void setAnswerList(List<Map<String, Object>> answerList) {
        this.answerList = answerList;
    }
}
