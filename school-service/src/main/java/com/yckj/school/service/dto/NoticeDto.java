/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.dto
 * Type:    NoticeDto
 * Author:  hefengwen
 * Date:    2016-12-16 15:24:05
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.dto;

import com.yckj.school.domain.Notice;

/**
 * @author hefengwen
 */
public class NoticeDto extends Notice{
    private String orderByColumn;
    
    private String orderType;

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
