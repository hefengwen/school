/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.dto
 * Type:    ResourceDto
 * Author:  hefengwen
 * Date:    2016-12-16 16:18:44
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.dto;

import java.io.InputStream;
import java.io.OutputStream;

import com.yckj.school.domain.Major;
import com.yckj.school.domain.Resource;
import com.yckj.school.domain.User;

/**
 * @author hefengwen
 */
public class ResourceDto extends Resource{
    
    private InputStream in;
    
    private OutputStream out;
    
    private String currentUserId;
    
    private Major major;
    
    private User user;
    
    private String orderByColumn;
    
    private String orderType;
    /**
     * 视频格式转换临时文件
     */
    private String newFilePath;

    public String getNewFilePath() {
        return newFilePath;
    }

    public void setNewFilePath(String newFilePath) {
        this.newFilePath = newFilePath;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public InputStream getIn() {
        return in;
    }

    public void setIn(InputStream in) {
        this.in = in;
    }

    public OutputStream getOut() {
        return out;
    }

    public void setOut(OutputStream out) {
        this.out = out;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }
}
