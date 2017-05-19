package com.yckj.school.common.exception;

/**
 * 业务逻辑错误类型接口
 * @author hefengwen
 *
 */
public interface ServiceErrorType {
	/**
	 * 获取错误码
	 * @return
	 */
	String getErrorCode();
}
