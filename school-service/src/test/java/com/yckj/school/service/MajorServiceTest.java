/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service
 * Type:    MajorServiceTest
 * Author:  hefengwen
 * Date:    2016-12-16 15:07:42
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yckj.school.service.dto.MajorDto;

/**
 * @author hefengwen
 */
public class MajorServiceTest extends BaseTest{
    @Autowired
    private MajorService majorService;
    @Test
    public void testAddMajor(){
        MajorDto dto = new MajorDto();
        dto.setName("材料科学");
        dto.setCreateTime(new Date());
        boolean b = majorService.addMajor(dto);
        System.out.println("b="+b);
    }
}
