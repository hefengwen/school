/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service
 * Type:    SaveService
 * Author:  hefengwen
 * Date:    2017-01-07 23:35:48
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service;

import com.yckj.school.service.dto.CourseDto;
import com.yckj.school.service.dto.SavePageDto;

/**
 * @author hefengwen
 */
public interface SaveService {
    /**
     * 订阅课程
     */
    boolean subscribeCourse(CourseDto dto);
    
    SavePageDto listAllSaveByPage(SavePageDto dto);
    
    boolean saveInfoDelete(long saveId);
    
}
