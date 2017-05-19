package com.yckj.school.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yckj.school.domain.Save;
import com.yckj.school.domain.SaveExample;

public interface SaveMapper {
    int deleteByExample(SaveExample example);

    int deleteByPrimaryKey(Long saveId);

    int insert(Save record);

    int insertSelective(Save record);

    List<Save> selectByExample(SaveExample example);

    Save selectByPrimaryKey(Long saveId);

    int updateByExampleSelective(@Param("record") Save record, @Param("example") SaveExample example);

    int updateByExample(@Param("record") Save record, @Param("example") SaveExample example);

    int updateByPrimaryKeySelective(Save record);

    int updateByPrimaryKey(Save record);
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
    List<Save> selectByPage(Map<String,Object> map);
}