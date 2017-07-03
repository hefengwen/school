/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.domain
 * Type:    LoginUser
 * Author:  hefengwen
 * Date:    2016-12-16 19:09:14
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author hefengwen
 */
public class LoginUser
{
  private String userId;
  private String passwd;

  public String toString()
  {
    return ReflectionToStringBuilder.toString(this);
  }
  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPasswd() {
    return this.passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
}
