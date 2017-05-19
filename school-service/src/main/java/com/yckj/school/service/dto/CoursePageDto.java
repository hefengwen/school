/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.dto
 * Type:    CoursePageDto
 * Author:  hefengwen
 * Date:    2016-12-16 15:53:31
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.dto;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.yckj.school.common.constant.Constants;

/**
 * @author hefengwen
 */
public class CoursePageDto {
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
    private CourseDto condition;
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
    private List<Map<String,Object>> courseList;
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public CourseDto getCondition() {
        return condition;
    }

    public void setCondition(CourseDto condition) {
        this.condition = condition;
    }

    public boolean getNeedTotal() {
        return needTotal;
    }

    public void setNeedTotal(boolean needTotal) {
        this.needTotal = needTotal;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<Map<String, Object>> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Map<String, Object>> courseList) {
        this.courseList = courseList;
    }
}
