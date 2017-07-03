/*******************************************************************************
 * Project: school-web
 * Package: com.yckj.school.annotation
 * Type:    SchoolValidate
 * Author:  hefengwen
 * Date:    2016-12-16 19:07:39
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hefengwen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
@Inherited
@Documented
public @interface SchoolValidate
{
  boolean ignoreSession() default false;

  int accessUser() default -1;
}
