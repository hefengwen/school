package com.yckj.school.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yckj.school.domain.Notice;
import com.yckj.school.domain.NoticeExample;

public interface NoticeMapper {
    int deleteByExample(NoticeExample example);

    int deleteByPrimaryKey(Long noticeId);

    int insert(Notice record);

    int insertSelective(Notice record);

    List<Notice> selectByExample(NoticeExample example);

    Notice selectByPrimaryKey(Long noticeId);

    int updateByExampleSelective(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByExample(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
    
    /**
     * 查询总记录数
     * @param map
     * @return
     */
    int selectTotalCount(Map<String,Object> map);
    /**
     * 分页查询
     * @param map
     * @return
     */
    List<Notice> selectByPage(Map<String,Object> map);
}