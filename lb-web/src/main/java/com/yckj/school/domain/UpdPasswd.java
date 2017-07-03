/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.domain
 * Type:    UpdPasswd
 * Author:  hefengwen
 * Date:    2016-12-17 23:39:10
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.domain;

/**
 * @author hefengwen
 */
public class UpdPasswd {
    
    private String oldPasswd;
    
    private String newPasswd1;
    
    private String newPasswd2;

    public String getOldPasswd() {
        return oldPasswd;
    }

    public void setOldPasswd(String oldPasswd) {
        this.oldPasswd = oldPasswd;
    }

    public String getNewPasswd1() {
        return newPasswd1;
    }

    public void setNewPasswd1(String newPasswd1) {
        this.newPasswd1 = newPasswd1;
    }

    public String getNewPasswd2() {
        return newPasswd2;
    }

    public void setNewPasswd2(String newPasswd2) {
        this.newPasswd2 = newPasswd2;
    }
}
