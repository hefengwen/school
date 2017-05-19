package com.yckj.school.dao;

import com.yckj.school.domain.Download;
import com.yckj.school.domain.DownloadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DownloadMapper {
    int deleteByExample(DownloadExample example);

    int deleteByPrimaryKey(Long loadId);

    int insert(Download record);

    int insertSelective(Download record);

    List<Download> selectByExample(DownloadExample example);

    Download selectByPrimaryKey(Long loadId);

    int updateByExampleSelective(@Param("record") Download record, @Param("example") DownloadExample example);

    int updateByExample(@Param("record") Download record, @Param("example") DownloadExample example);

    int updateByPrimaryKeySelective(Download record);

    int updateByPrimaryKey(Download record);
}