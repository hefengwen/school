/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.dto
 * Type:    CourseDto
 * Author:  hefengwen
 * Date:    2016-12-16 15:53:06
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.dto;

import java.io.OutputStream;

import com.yckj.school.domain.CourseWithBLOBs;

/**
 * @author hefengwen
 */
public class CourseDto extends CourseWithBLOBs{

    private OutputStream out;
    
    private BookDto book;
    
    private String currentUserId;
    
    private String orderByColumn;
    
    private String orderType;

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

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    public OutputStream getOut() {
        return out;
    }

    public void setOut(OutputStream out) {
        this.out = out;
    }
}
