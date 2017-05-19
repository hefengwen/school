package com.yckj.school.exception;

import com.yckj.school.common.exception.ServiceErrorType;
import com.yckj.school.common.exception.ServiceException;

public class SchoolException extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9073693049654779939L;
	/**
	 * 构造方法
	 * @param errorType
	 * @param params
	 */
	public SchoolException(ServiceErrorType errorType, Object[] params) {
		super(errorType, params);
	}

}
