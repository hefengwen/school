/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.domain
 * Type:    BookVo
 * Author:  hefengwen
 * Date:    2017-01-02 22:50:39
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.domain;

import org.springframework.web.multipart.MultipartFile;

import com.yckj.school.service.dto.BookDto;

/**
 * @author hefengwen
 */
public class BookVo extends BookDto{
    /**
     * 教材
     */
    private MultipartFile bookFile;

    public MultipartFile getBookFile() {
        return bookFile;
    }

    public void setBookFile(MultipartFile bookFile) {
        this.bookFile = bookFile;
    }
    
}
