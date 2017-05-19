/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.dto
 * Type:    SavePageDto
 * Author:  hefengwen
 * Date:    2017-01-07 23:27:37
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.dto;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.domain.Save;

/**
 * @author hefengwen
 */
public class SavePageDto extends Save{
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
    private SaveDto condition;
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
     * 订阅结果列表
     */
    private List<Map<String,Object>> saveList;
    
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

    public SaveDto getCondition() {
        return condition;
    }

    public void setCondition(SaveDto condition) {
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

    public List<Map<String, Object>> getSaveList() {
        return saveList;
    }

    public void setSaveList(List<Map<String, Object>> saveList) {
        this.saveList = saveList;
    }
}
