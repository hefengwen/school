/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service
 * Type:    FastDFSServiceTest
 * Author:  hefengwen
 * Date:    2017-01-25 15:06:01
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hefengwen
 */
public class FastDFSServiceTest extends BaseTest{
    @Autowired
    private FastDFSService fastDFSService;
    @Test
    public void testGetFileStorage(){
        String s = "";
        for(int i=0;i<1;i++){
            String address = fastDFSService.getFileStorage("group1/M00/00/01/wKiNyViIQjSEWFFjAAAAAJ4UUx0913.pdf");
            s = s+address+"\n";
        }
        System.out.println(s);
    }
}
