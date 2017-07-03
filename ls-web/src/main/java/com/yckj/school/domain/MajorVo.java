/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.domain
 * Type:    MajorQuery
 * Author:  hefengwen
 * Date:    2016-12-17 18:05:35
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.domain;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.service.dto.MajorDto;

/**
 * @author hefengwen
 */
public class MajorVo extends MajorDto{
    /**
     * 当前页数
     */
    private Integer curPage = 1;
    /**
     * 每页条数
     */
    private Integer pageCount = Constants.DEFAULT_PAGE_SIZE;
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
