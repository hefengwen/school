/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service
 * Type:    BookService
 * Author:  hefengwen
 * Date:    2017-01-02 22:54:45
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service;

import com.yckj.school.service.dto.BookDto;

/**
 * @author hefengwen
 */
public interface BookService {
    
    boolean addBook(BookDto dto);

    boolean deleteBook(BookDto dto);

    BookDto queryBook(long bookId);
    
    BookDto queryBookByCourse(long courseId);

    boolean uploadBook(BookDto dto);
    
    BookDto downloadBook(BookDto dto);

}
