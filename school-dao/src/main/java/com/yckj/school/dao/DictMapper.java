package com.yckj.school.dao;

import com.yckj.school.domain.Dict;
import com.yckj.school.domain.DictExample;
import com.yckj.school.domain.DictKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DictMapper {
    int deleteByExample(DictExample example);

    int deleteByPrimaryKey(DictKey key);

    int insert(Dict record);

    int insertSelective(Dict record);

    List<Dict> selectByExample(DictExample example);

    Dict selectByPrimaryKey(DictKey key);

    int updateByExampleSelective(@Param("record") Dict record, @Param("example") DictExample example);

    int updateByExample(@Param("record") Dict record, @Param("example") DictExample example);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
}