/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.impl
 * Type:    DictServiceImpl
 * Author:  hefengwen
 * Date:    2017-01-08 18:06:42
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yckj.school.common.util.PropertyUtils;
import com.yckj.school.dao.DictMapper;
import com.yckj.school.domain.Dict;
import com.yckj.school.domain.DictExample;
import com.yckj.school.exception.SchoolErrorType;
import com.yckj.school.exception.SchoolException;
import com.yckj.school.service.DictService;

/**
 * @author hefengwen
 */
@Service
public class DictServiceImpl implements DictService {
    private static final Logger logger = LoggerFactory.getLogger(DictServiceImpl.class);

    @Autowired
    private DictMapper dictMapper;

    @Override
    public List<Map<String, Object>> getDictByType(int type) {
        try {
            DictExample de = new DictExample();
            DictExample.Criteria dc = de.createCriteria();
            dc.andDictTypeEqualTo(type);
            List<Dict> dicts = dictMapper.selectByExample(de);
            List<Map<String,Object>> list = new ArrayList<>();
            for(Dict d:dicts){
                Map<String,Object> m = PropertyUtils.objectToMap(d);
                list.add(m);
            }
            return list;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }

}
