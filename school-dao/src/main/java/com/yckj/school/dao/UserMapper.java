package com.yckj.school.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yckj.school.domain.User;
import com.yckj.school.domain.UserExample;

public interface UserMapper {
    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
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
    List<User> selectByPage(Map<String,Object> map);
}