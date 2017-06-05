/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.domain
 * Type:    QuestionVo
 * Author:  hefengwen
 * Date:    2017-05-31 14:56:27
 *
 * Copyright (c) 2017 yckj. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.domain;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.service.dto.QuestionDto;

/**
 * @author hefengwen
 */
public class QuestionVo extends QuestionDto{
    /**
     * 当前页数
     */
    private Integer curPage = 1;
    /**
     * 每页条数
     */
    private Integer pageCount = Constants.DEFAULT_PAGE_SIZE;
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
}
