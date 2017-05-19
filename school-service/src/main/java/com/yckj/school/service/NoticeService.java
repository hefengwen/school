/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service
 * Type:    NoticeService
 * Author:  hefengwen
 * Date:    2016-12-16 15:30:19
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service;

import com.yckj.school.service.dto.NoticeDto;
import com.yckj.school.service.dto.NoticePageDto;

/**
 * @author hefengwen
 */
public interface NoticeService {
    
    boolean addNotice(NoticeDto dto);

    boolean deleteNotice(long noticeId);

    boolean updateNotice(NoticeDto dto);

    NoticeDto queryNotice(long noticeId);

    NoticePageDto listAllNoticeByPage(NoticePageDto dto);
}
