/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service
 * Type:    DictService
 * Author:  hefengwen
 * Date:    2017-01-08 18:05:40
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service;

import java.util.List;
import java.util.Map;

/**
 * @author hefengwen
 */
public interface DictService {
    List<Map<String,Object>> getDictByType(int type);
}
