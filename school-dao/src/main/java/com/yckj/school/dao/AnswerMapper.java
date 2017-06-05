package com.yckj.school.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yckj.school.domain.Answer;
import com.yckj.school.domain.AnswerExample;

public interface AnswerMapper {
    int deleteByExample(AnswerExample example);

    int deleteByPrimaryKey(Long ansId);

    int insert(Answer record);

    int insertSelective(Answer record);

    List<Answer> selectByExample(AnswerExample example);

    Answer selectByPrimaryKey(Long ansId);

    int updateByExampleSelective(@Param("record") Answer record, @Param("example") AnswerExample example);

    int updateByExample(@Param("record") Answer record, @Param("example") AnswerExample example);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
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
    List<Answer> selectByPage(Map<String,Object> map);
}