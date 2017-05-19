/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service
 * Type:    UserServiceTest
 * Author:  hefengwen
 * Date:    2016-12-16 13:47:29
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yckj.school.service.dto.UserDto;
import com.yckj.school.service.dto.UserPageDto;

/**
 * @author hefengwen
 */
public class UserServiceTest extends BaseTest{
    @Autowired
    private UserService userService;
    @Test
    public void testLogin(){
        UserDto dto = new UserDto();
        dto.setUserId("1");
        dto.setPasswd("2");
        boolean b = userService.login(dto);
        System.out.println("b="+b);
    }
    @Test
    public void testUpdPasswd(){
        UserDto dto = new UserDto();
        dto.setUserId("admin");
        dto.setPasswd("admin");
        boolean b = userService.updPasswd(dto);
        System.out.println("b="+b);
    }
    @Test
    public void testListAllUserByPage(){
        UserPageDto dto = new UserPageDto();
        UserDto user = new UserDto();
        dto.setCondition(user);
        dto.setNeedTotal(true);

        dto = userService.listAllUserByPage(dto);
        System.out.println("dto="+dto);
    }
    @Test
    public void testAddTeacher(){
        UserDto dto = new UserDto();
        dto.setUserId("1");
        dto.setName("teacher");
        dto.setPhone("13399999999");
        dto.setPasswd("2");
        dto.setType(1);
        dto.setCreateTime(new Date());
        dto.setMajorId(1000L);
        boolean b = userService.addUser(dto);
        System.out.println("b="+b);
    }
    @Test
    public void testDeleteUser(){
        String userId = "1";
        boolean b = userService.deleteUser(userId);
        System.out.println("b="+b);
    }
}
