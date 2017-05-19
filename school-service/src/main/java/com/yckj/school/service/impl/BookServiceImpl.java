/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.impl
 * Type:    BookServiceImpl
 * Author:  hefengwen
 * Date:    2017-01-02 22:56:59
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.util.CommonUtils;
import com.yckj.school.common.util.PropertyUtils;
import com.yckj.school.dao.BookMapper;
import com.yckj.school.domain.Book;
import com.yckj.school.domain.BookExample;
import com.yckj.school.exception.SchoolErrorType;
import com.yckj.school.exception.SchoolException;
import com.yckj.school.service.BookService;
import com.yckj.school.service.FastDFSService;
import com.yckj.school.service.dto.BookDto;

/**
 * @author hefengwen
 */
@Service
public class BookServiceImpl implements BookService{
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    private FastDFSService fastDFSService;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public boolean addBook(BookDto dto) {
        logger.info("BookServiceImpl addBook start ... ...");
        try {
            dto.setType(CommonUtils.getFileExt(dto.getName()));
            dto.setCreateTime(new Date());
            int cnt = bookMapper.insertSelective(dto);
            return cnt==1;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }

    @Override
    public boolean deleteBook(BookDto book) {
        try {
            fastDFSService.deleteFile(book.getOriUrl());
            book.setStatue(Constants.NO);
            bookMapper.updateByPrimaryKeySelective(book);
        }
        catch (Exception e) {
            logger.error("",e);
        }
        return true;
    }

    @Override
    public BookDto queryBook(long bookId) {
        Book book = bookMapper.selectByPrimaryKey(bookId);
        BookDto dto = new BookDto();
        try {
            PropertyUtils.propertyCopy(dto, book);
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
        return dto;
    }

    @Override
    public boolean uploadBook(BookDto dto) {
        logger.info("BookServiceImpl uploadBook start ... ...");
        InputStream in = null;
        String fileId = null;
        try {
            in = dto.getIn();
            if(Constants.DEFAULT_FILE_SIZE>=dto.getFileSize()){
                byte[] bytes = new byte[dto.getFileSize().intValue()];
                in.read(bytes);
                fileId = fastDFSService.uploadFile(bytes, dto.getName());
            }else{
                byte[] bytes = new byte[Constants.DEFAULT_FILE_SIZE];
                in.read(bytes);
                fileId = fastDFSService.uploadAppendFile(bytes, dto.getName());
                int len = 0;
                while((len=in.read(bytes)) != -1){
                    if(len<Constants.DEFAULT_FILE_SIZE){
                        byte[] lastBytes = new byte[len];
                        System.arraycopy(bytes, 0, lastBytes, 0, len);
                        bytes = lastBytes;
                    }
                    int rst = fastDFSService.appendFile(bytes, fileId);
                    if(rst != 0){
                        throw new SchoolException(SchoolErrorType.err_upload_file, null);
                    }
                }
            }
            dto.setOriUrl(fileId);
            logger.info("BookServiceImpl addBook end ... ...");
            return this.addBook(dto);
        } catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_upload_file, null);
        } finally{
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("",e);
                }
            }
        }
    }

    @Override
    public BookDto downloadBook(BookDto dto) {
        logger.info("BookServiceImpl downloadBook start ... ...");
        OutputStream out = null;
        try {
            out = dto.getOut();
            byte[] bytes = null;
            long offset = 0;
            if(Constants.DEFAULT_FILE_SIZE>=dto.getFileSize()){
                bytes = fastDFSService.downloadFile(dto.getOriUrl(),offset,dto.getFileSize());
                out.write(bytes);
            }else{
                while(offset<dto.getFileSize()){
                    long length = (dto.getFileSize()-offset)<Constants.DEFAULT_FILE_SIZE?(dto.getFileSize()-offset):Constants.DEFAULT_FILE_SIZE;
                    bytes = fastDFSService.downloadFile(dto.getOriUrl(),offset,length);
                    out.write(bytes);
                    offset += Constants.DEFAULT_FILE_SIZE;
                }
            }
            logger.info("download book "+dto.getName()+" over... ...");
        } catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_download_file, null);
        } 
        return dto;
    }
    
    @Override
    public BookDto queryBookByCourse(long courseId) {
        BookExample be = new BookExample();
        BookExample.Criteria bc = be.createCriteria();
        bc.andCourseIdEqualTo(courseId);
        bc.andStatueEqualTo(Constants.YES);
        List<Book> books = bookMapper.selectByExample(be);
        if(CollectionUtils.isNotEmpty(books)){
            Book book = books.get(0);
            BookDto dto = new BookDto();
            try {
                PropertyUtils.propertyCopy(dto, book);
            }
            catch (Exception e) {
                logger.error("",e);
                throw new SchoolException(SchoolErrorType.err_system, null);
            }
            return dto;
        }
        return null;
    }

}
