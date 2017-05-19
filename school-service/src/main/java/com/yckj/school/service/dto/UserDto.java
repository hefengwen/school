/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.dto
 * Type:    UserDto
 * Author:  hefengwen
 * Date:    2016-12-16 13:30:45
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.dto;

import com.yckj.school.domain.User;

/**
 * @author hefengwen
 */
public class UserDto extends User{
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
