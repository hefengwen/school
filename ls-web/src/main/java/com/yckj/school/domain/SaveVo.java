/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.domain
 * Type:    SaveVo
 * Author:  hefengwen
 * Date:    2017-01-07 23:48:40
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.domain;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.service.dto.SaveDto;

/**
 * @author hefengwen
 */
public class SaveVo extends SaveDto{
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
