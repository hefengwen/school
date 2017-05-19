/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.domain
 * Type:    UserVo
 * Author:  hefengwen
 * Date:    2016-12-17 21:40:47
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.domain;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.service.dto.UserDto;

/**
 * @author hefengwen
 */
public class UserVo extends UserDto{
    /**
     * 专业
     */
    private String major;
    /**
     * 当前页数
     */
    private Integer curPage = 1;
    /**
     * 每页条数
     */
    private Integer pageCount = Constants.DEFAULT_PAGE_SIZE;
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
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
}
