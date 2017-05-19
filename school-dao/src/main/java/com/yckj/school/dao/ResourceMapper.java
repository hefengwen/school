package com.yckj.school.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yckj.school.domain.Resource;
import com.yckj.school.domain.ResourceExample;

public interface ResourceMapper {
    int deleteByExample(ResourceExample example);

    int deleteByPrimaryKey(Long resourceId);

    int insert(Resource record);

    int insertSelective(Resource record);

    List<Resource> selectByExample(ResourceExample example);

    Resource selectByPrimaryKey(Long resourceId);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
    
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
    List<Resource> selectByPage(Map<String,Object> map);
    /**
     * 下载次数加1
     * @param record
     * @return
     */
    int addLoadCnt(Resource record);
    /**
     * 收藏次数加1
     * @param record
     * @return
     */
    int addSaveCnt(Resource record);
    /**
     * 收藏次数减1
     * @param record
     * @return
     */
    int subSaveCnt(Resource record);
    /**
     * 评分
     */
    int addScore(Resource record);
}