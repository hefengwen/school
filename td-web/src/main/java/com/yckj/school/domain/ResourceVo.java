/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.domain
 * Type:    ResourceVo
 * Author:  hefengwen
 * Date:    2016-12-19 10:42:27
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.domain;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.service.dto.ResourceDto;

/**
 * @author hefengwen
 */
public class ResourceVo extends ResourceDto{
    /**
     * 当前页数
     */
    private Integer curPage = 1;
    /**
     * 每页条数
     */
    private Integer pageCount = Constants.DEFAULT_PAGE_SIZE;
    /**
     * 资源文件
     */
    private MultipartFile resourceFile;
    /**
     * 输入流
     */
    private InputStream in;
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
    public MultipartFile getResourceFile() {
        return resourceFile;
    }
    public void setResourceFile(MultipartFile resourceFile) {
        this.resourceFile = resourceFile;
    }
    public InputStream getIn() {
        return in;
    }
    public void setIn(InputStream in) {
        this.in = in;
    }
}
