package com.yckj.school.exception;

import com.yckj.school.common.exception.ServiceErrorType;

public enum SchoolErrorType implements ServiceErrorType{
	/**
	 * 系统错误
	 */
	err_system("999999"),
	/**
	 * 上传文件错误
	 */
	err_upload_file("000001"),
	/**
	 * 删除文件错误
	 */
	err_delete_file("000002"),
	/**
	 * 下载文件错误
	 */
	err_download_file("000003"),
	/**
	 * 获取文件信息错误
	 */
	err_query_file("000004"),
	/**
	 * 文件不存在
	 */
	err_file_not_exist("000005"),
	/**
	 * 文件已收藏
	 */
	err_file_has_saved("000006"),
	/**
	 * 无上传权限
	 */
	err_no_upload_access("000007"),
	/**
	 * 无下载权限
	 */
	err_no_download_access("000008"),
	/**
	 * 无删除权限
	 */
	err_no_delete_access("000009"),
	/**
     * 专业下有教师，不能删除
     */
    err_exist_teacher("000010"),
    /**
     * 教师下有学生，不能删除
     */
    err_exist_student("000011"),
    /**
     * 文件系统连接超时
     */
    err_connection_timeout("000012"),
    /**
     * 文件格式不支持
     */
    err_filetype_not_support("000013");
	
	private String errorCode;
	
	
	private SchoolErrorType(String errorCode) {
	    this.errorCode = errorCode;
	  }

	@Override
	public String getErrorCode() {
		return errorCode;
	}

}
