/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service
 * Type:    CourseService
 * Author:  hefengwen
 * Date:    2016-12-16 15:54:35
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service;

import com.yckj.school.service.dto.CourseDto;
import com.yckj.school.service.dto.CoursePageDto;

/**
 * @author hefengwen
 */
public interface CourseService {
    
    boolean addCourse(CourseDto dto);
    
    /**
     * 上传课程图片
     */
    String uploadPic(byte[] pic,String picName);
    /**
     * 删除课程图片
     */
    boolean deletePic(long courseId);

    boolean deleteCourse(long courseId);

    boolean updateCourse(CourseDto dto);

    CourseDto queryCourse(long courseId);
    /**
     * 课程浏览
     */
    CourseDto viewCourse(long courseId);

    CoursePageDto listAllCourseByPage(CoursePageDto dto);
    /**
     * 文件预览
     */
    String filePreView(CourseDto dto);
}
