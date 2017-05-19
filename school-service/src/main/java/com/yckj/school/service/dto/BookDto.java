/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.dto
 * Type:    BookDto
 * Author:  hefengwen
 * Date:    2017-01-02 22:49:12
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.dto;

import java.io.InputStream;
import java.io.OutputStream;

import com.yckj.school.domain.Book;

/**
 * @author hefengwen
 */
public class BookDto extends Book {
    private InputStream in;

    private OutputStream out;

    private String currentUserId;

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
