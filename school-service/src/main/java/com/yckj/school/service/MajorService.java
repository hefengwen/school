/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service
 * Type:    MajorService
 * Author:  hefengwen
 * Date:    2016-12-16 14:48:25
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service;

import java.util.List;

import com.yckj.school.service.dto.MajorDto;
import com.yckj.school.service.dto.MajorPageDto;

/**
 * @author hefengwen
 */
public interface MajorService {
    
    boolean addMajor(MajorDto dto);
    
    boolean deleteMajor(long majorId);
    
    boolean updateMajor(MajorDto dto);
    
    MajorDto queryMajor(long majorId);
    
    List<MajorDto> queryAllMajor();
    
    MajorPageDto listAllMajorByPage(MajorPageDto dto);
}
