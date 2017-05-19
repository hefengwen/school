/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service
 * Type:    ResourceService
 * Author:  hefengwen
 * Date:    2016-12-16 16:20:27
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service;

import com.yckj.school.service.dto.ResourceDto;
import com.yckj.school.service.dto.ResourcePageDto;

/**
 * @author hefengwen
 */
public interface ResourceService {
    boolean addResource(ResourceDto dto);

    boolean deleteResource(long resourceId);

    boolean updateResource(ResourceDto dto);

    ResourceDto queryResource(long resourceId);

    ResourcePageDto listAllResourceByPage(ResourcePageDto dto);
    
    boolean uploadResource(ResourceDto dto);
    
    boolean downloadResource(ResourceDto dto);
    
    boolean removeResource(String fileId);
    
    boolean saveResource(ResourceDto dto);
    
    String resourcePreView1(ResourceDto dto);
    
    String resourcePreView2(ResourceDto dto);
    
}
