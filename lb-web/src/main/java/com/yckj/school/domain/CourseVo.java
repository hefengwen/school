/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.domain
 * Type:    CourseVo
 * Author:  hefengwen
 * Date:    2016-12-18 14:17:19
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.domain;

import org.springframework.web.multipart.MultipartFile;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.service.dto.CourseDto;

/**
 * @author hefengwen
 */
public class CourseVo extends CourseDto{
    /**
     * 当前页数
     */
    private Integer curPage = 1;
    /**
     * 每页条数
     */
    private Integer pageCount = Constants.DEFAULT_PAGE_SIZE;
    /**
     * 封面图片文件
     */
    private MultipartFile pictureFile;
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
    public MultipartFile getPictureFile() {
        return pictureFile;
    }
    public void setPictureFile(MultipartFile pictureFile) {
        this.pictureFile = pictureFile;
    }
}
