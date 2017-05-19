/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service
 * Type:    ScoreService
 * Author:  hefengwen
 * Date:    2017-01-09 15:47:14
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service;

import com.yckj.school.service.dto.ScoreDto;

/**
 * @author hefengwen
 */
public interface ScoreService {
    
    ScoreDto queryScore(ScoreDto dto);
    
    boolean scoreCourse(ScoreDto dto);
    
    boolean scoreResource(ScoreDto dto);

}
