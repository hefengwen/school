package com.yckj.school.common.exception;

import com.yckj.school.common.util.LocaleUtils;

/**
 * 业务逻辑通用异常。
 * <p>该类为抽象类，不能直接使用，各个模块应该定义自己的异常，且应该继承该类。
 * @author hefengwen
 *
 */
public abstract class ServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1691447739127988706L;
	/**
	   * 错误码
	   */
	  private String errorCode;

	  /**
	   * 错误信息
	   */
	  private String msg;

	  /**
	   * 构造方法
	   * @param errorType 错误类型，必须为实现了{@link ServiceErrorType}的各模块异常类型
	   * @param params 用于替换异常错误类型资源文件中的占位符，例如：
	   * <p>资源文件中的错误信息定义为：
	   * <pre><code>3 = 用户组{0}中的用户数已经超过最大值{1}</code></pre>
	   * <p>对于以下的构造方法：
	   * <pre><code>new UserException(UserErrType.userGrp_exceed, "VIP组", 500);</code></pre>
	   * <p>最终的错误信息为：
	   * <p><pre>用户组VIP组中的用户数已经超过最大值500</pre>
	   */
	  public ServiceException(ServiceErrorType errorType, Object... params) {
	    super();

	    LocaleUtils localeUtils = LocaleUtils.getManager(errorType.getClass());

	    this.msg = localeUtils.getString(errorType.getErrorCode(), params);
	    this.errorCode = errorType.getErrorCode();
	  }

	  /**
	   * @return errCode
	   */
	  public String getErrorCode() {
	    return errorCode;
	  }

	  /**
	   * @return msg
	   */
	  public String getMsg() {
	    return msg;
	  }
}
