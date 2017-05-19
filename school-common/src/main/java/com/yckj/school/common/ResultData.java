package com.yckj.school.common;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ResultData<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4421605267739639685L;
	/**
	 * 状态码
	 */
	private String status = "000000";
	/**
	 * 提示信息
	 */
	private String msg = "操作成功";
	/**
	 * 返回对象
	 */
	private T data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
