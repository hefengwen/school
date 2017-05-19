/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.service.impl
 * Type:    NoticeServiceImpl
 * Author:  hefengwen
 * Date:    2016-12-16 15:32:15
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.util.PropertyUtils;
import com.yckj.school.dao.NoticeMapper;
import com.yckj.school.domain.Notice;
import com.yckj.school.exception.SchoolErrorType;
import com.yckj.school.exception.SchoolException;
import com.yckj.school.service.NoticeService;
import com.yckj.school.service.dto.NoticeDto;
import com.yckj.school.service.dto.NoticePageDto;

/**
 * @author hefengwen
 */
@Service
public class NoticeServiceImpl implements NoticeService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public boolean addNotice(NoticeDto dto) {
        logger.info("NoticeServiceImpl addNotice start ... ...");
        dto.setCreateTime(new Date());
        int cnt = noticeMapper.insertSelective(dto);
        return cnt==1;
    }

    @Override
    public boolean deleteNotice(long noticeId) {
        logger.info("NoticeServiceImpl deleteNotice start ... ...");
        Notice notice = new Notice();
        notice.setNoticeId(noticeId);
        notice.setStatue(Constants.NO);
        int cnt = noticeMapper.updateByPrimaryKeySelective(notice);
        return cnt == 1;
    }

    @Override
    public boolean updateNotice(NoticeDto dto) {
        logger.info("NoticeServiceImpl updateNotice start ... ...");
        try {
            Notice notice = new Notice();
            PropertyUtils.propertyCopy(notice, dto);
            int cnt = noticeMapper.updateByPrimaryKeySelective(notice);
            return cnt==1;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }

    @Override
    public NoticeDto queryNotice(long noticeId) {
        logger.info("NoticeServiceImpl queryNotice start ... ...");
        try {
            Notice notice = noticeMapper.selectByPrimaryKey(noticeId);
            if(notice==null)
                return null;
            NoticeDto dto = new NoticeDto();
            PropertyUtils.propertyCopy(dto, notice);
            return dto;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }

    @Override
    public NoticePageDto listAllNoticeByPage(NoticePageDto dto) {
        logger.info("NoticeServiceImpl listAllNoticeByPage start ... ...");
        try {
            Map<String,Object> map = new HashMap<>();
            map.put(Constants.START, (dto.getCurPage()-1)*dto.getPageCount());
            map.put(Constants.COUNT, dto.getPageCount());
            map.putAll(PropertyUtils.objectToMap(dto.getCondition()));
            
            if(dto.getNeedTotal()){
                int totalCount = noticeMapper.selectTotalCount(map);
                dto.setTotalCount(totalCount);
                dto.setTotalPageCount(totalCount%dto.getPageCount()==0?totalCount/dto.getPageCount():totalCount/dto.getPageCount()+1);
            }
            
            List<Notice> notices = noticeMapper.selectByPage(map);
            List<Map<String, Object>> noticeList = new ArrayList<>();
            for(Notice notice:notices){
                Map<String, Object> m = PropertyUtils.objectToMap(notice);
                noticeList.add(m);
            }
            dto.setNoticeList(noticeList);
        } catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
        return dto;
    }

}
