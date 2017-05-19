package com.yckj.school.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yckj.school.domain.Course;
import com.yckj.school.domain.CourseExample;
import com.yckj.school.domain.CourseWithBLOBs;

public interface CourseMapper {
    int deleteByExample(CourseExample example);

    int deleteByPrimaryKey(Long courseId);

    int insert(CourseWithBLOBs record);

    int insertSelective(CourseWithBLOBs record);

    List<CourseWithBLOBs> selectByExampleWithBLOBs(CourseExample example);

    List<Course> selectByExample(CourseExample example);

    CourseWithBLOBs selectByPrimaryKey(Long courseId);

    int updateByExampleSelective(@Param("record") CourseWithBLOBs record, @Param("example") CourseExample example);

    int updateByExampleWithBLOBs(@Param("record") CourseWithBLOBs record, @Param("example") CourseExample example);

    int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByPrimaryKeySelective(CourseWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CourseWithBLOBs record);

    int updateByPrimaryKey(Course record);
    
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
    List<CourseWithBLOBs> selectByPage(Map<String,Object> map);
    /**
     * 浏览次数加1
     * @param record
     * @return
     */
    int addViewCnt(Course record);
    /**
     * 收藏次数加1
     * @param record
     * @return
     */
    int addSaveCnt(Course record);
    /**
     * 收藏次数减1
     * @param record
     * @return
     */
    int subSaveCnt(Course record);
    /**
     * 评分
     */
    int addScore(Course record);
}