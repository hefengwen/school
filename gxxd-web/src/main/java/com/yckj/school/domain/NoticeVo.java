/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.domain
 * Type:    NoticeVo
 * Author:  hefengwen
 * Date:    2016-12-19 10:10:05
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.domain;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.service.dto.NoticeDto;

/**
 * @author hefengwen
 */
public class NoticeVo extends NoticeDto{
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
