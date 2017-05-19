/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.impl
 * Type:    CourseServiceImpl
 * Author:  hefengwen
 * Date:    2016-12-16 15:57:12
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.util.PropertyUtils;
import com.yckj.school.dao.BookMapper;
import com.yckj.school.dao.CourseMapper;
import com.yckj.school.domain.Book;
import com.yckj.school.domain.BookExample;
import com.yckj.school.domain.Course;
import com.yckj.school.domain.CourseWithBLOBs;
import com.yckj.school.exception.SchoolErrorType;
import com.yckj.school.exception.SchoolException;
import com.yckj.school.service.CourseService;
import com.yckj.school.service.FastDFSService;
import com.yckj.school.service.dto.BookDto;
import com.yckj.school.service.dto.CourseDto;
import com.yckj.school.service.dto.CoursePageDto;

/**
 * @author hefengwen
 */
@Service
public class CourseServiceImpl implements CourseService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    private FastDFSService fastDFSService;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private BookMapper bookMapper;
    
    @Override
    public boolean addCourse(CourseDto dto) {
        logger.info("CourseServiceImpl addCourse start ... ...");
        try {
            CourseWithBLOBs course = new CourseWithBLOBs();
            PropertyUtils.propertyCopy(course, dto);
            course.setCreateTime(new Date());
            int cnt = courseMapper.insertSelective(course);
            return cnt==1;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }
    
    @Override
    public String uploadPic(byte[] pic,String picName) {
        return fastDFSService.uploadFile(pic, picName);
    }
    
    @Override
    public boolean deletePic(long courseId) {
        String pic = this.queryCourse(courseId).getPicture();
        if(StringUtils.isBlank(pic))
            return true;
        int cnt = fastDFSService.deleteFile(pic);
        return cnt==0;
    }

    @Override
    public boolean deleteCourse(long courseId) {
        logger.info("CourseServiceImpl deleteCourse start ... ...");
        CourseWithBLOBs course = new CourseWithBLOBs();
        course.setCourseId(courseId);
        course.setStatue(Constants.NO);
        int cnt = courseMapper.updateByPrimaryKeySelective(course);
        return cnt == 1;
    }

    @Override
    public boolean updateCourse(CourseDto dto) {
        logger.info("CourseServiceImpl updateCourse start ... ...");
        try {
            CourseWithBLOBs course = new CourseWithBLOBs();
            PropertyUtils.propertyCopy(course, dto);
            int cnt = courseMapper.updateByPrimaryKeySelective(course);
            return cnt==1;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }

    @Override
    public CourseDto queryCourse(long courseId) {
        logger.info("CourseServiceImpl queryCourse start ... ...");
        try {
            CourseWithBLOBs course = courseMapper.selectByPrimaryKey(courseId);
            if(course==null)
                return null;
            CourseDto dto = new CourseDto();
            PropertyUtils.propertyCopy(dto, course);
            BookExample be = new BookExample();
            BookExample.Criteria bc = be.createCriteria();
            bc.andCourseIdEqualTo(course.getCourseId());
            bc.andStatueEqualTo(Constants.YES);
            List<Book> books = bookMapper.selectByExample(be);
            if(CollectionUtils.isNotEmpty(books)){
                BookDto book = new BookDto();
                PropertyUtils.propertyCopy(book, books.get(0));
                dto.setBook(book);
            }
            return dto;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }
    @Override
    public CourseDto viewCourse(long courseId) {
        logger.info("CourseServiceImpl viewCourse start ... ...");
        Course course = new Course();
        course.setCourseId(courseId);
        courseMapper.addViewCnt(course);
        return this.queryCourse(courseId);
    }

    @Override
    public CoursePageDto listAllCourseByPage(CoursePageDto dto) {
        logger.info("CourseServiceImpl listAllCourseByPage start ... ...");
        try {
            Map<String,Object> map = new HashMap<>();
            map.put(Constants.START, (dto.getCurPage()-1)*dto.getPageCount());
            map.put(Constants.COUNT, dto.getPageCount());
            map.putAll(PropertyUtils.objectToMap(dto.getCondition()));
            
            if(dto.getNeedTotal()){
                int totalCount = courseMapper.selectTotalCount(map);
                dto.setTotalCount(totalCount);
                dto.setTotalPageCount(totalCount%dto.getPageCount()==0?totalCount/dto.getPageCount():totalCount/dto.getPageCount()+1);
            }
            
            List<CourseWithBLOBs> courses = courseMapper.selectByPage(map);
            List<Map<String, Object>> courseList = new ArrayList<>();
            for(CourseWithBLOBs course:courses){
                Map<String, Object> m = PropertyUtils.objectToMap(course);
                BookExample be = new BookExample();
                BookExample.Criteria bc = be.createCriteria();
                bc.andCourseIdEqualTo(course.getCourseId());
                bc.andStatueEqualTo(Constants.YES);
                List<Book> books = bookMapper.selectByExample(be);
                if(CollectionUtils.isNotEmpty(books)){
                    Book book = books.get(0);
                    Map<String, Object> bm = PropertyUtils.objectToMap(book);
                    m.put("book", bm);
                }
                courseList.add(m);
            }
            dto.setCourseList(courseList);
        } catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
        return dto;
    }
    /**
     * 方式1:使用本地缓存(不能直接访问文件服务器时使用)
     */
    @Override
    public String filePreView(CourseDto dto) {
        logger.info("CourseServiceImpl filePreView start ... ...");
        String pic = courseMapper.selectByPrimaryKey(dto.getCourseId()).getPicture();
        //this.queryCourse(dto.getCourseId()).getPicture();
        if(StringUtils.isBlank(pic))
            return "";
        OutputStream out = null;
        byte[] bytes = null;
        try {
            out = dto.getOut();
            bytes = fastDFSService.downloadFile(pic);
            out.write(bytes);
            return "";
        }
        catch (IOException e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }
//    /**
//     * 方式2:直接访问文件服务器
//     */
//    @Override
//    public String filePreView(CourseDto dto) {
//        logger.info("CourseServiceImpl filePreView start ... ...");
//        String pic = courseMapper.selectByPrimaryKey(dto.getCourseId()).getPicture();
//        //this.queryCourse(dto.getCourseId()).getPicture();
//        if(StringUtils.isBlank(pic))
//            return null;
//        return fastDFSService.getFileStorage(pic)+pic;
//    }
}
