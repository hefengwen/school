package com.yckj.school.common.constant;

public class Constants {
    /**
     * 管理员
     */
    public static final int ADMIN = 0;
    /**
     * 教师
     */
    public static final int TEACHER = 1;
    /**
     * 学生
     */
    public static final int STUDENT = 2;
	/**
	 * 默认每页条数
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;
	/**
	 * 文件大小单位10M
	 */
	public static final int DEFAULT_FILE_SIZE = 1024*1024*10;
	/**
	 * 文件大小单位 1M
	 */
	public static final int MIN_FILE_SIZE = 1024*1024*1;
	/**
	 * 分页起始位置
	 */
	public static final String START = "start";
	/**
	 * 分页每页条数
	 */
	public static final String COUNT = "count";
	/**
	 * YES
	 */
	public static final String YES = "Y";
	/**
	 * NO
	 */
	public static final String NO = "N";
	/**
	 * 返回状态
	 * 000000 成功
	 * 其他 失败
	 */
	public static final String STATE = "state";
	/**
	 * 返回信息
	 */
	public static final String MSG = "msg";
	/**
	 * 返回结果
	 */
	public static final String RESULT = "result";
	/**
	 * 返回结果
	 */
	public static final String DOMAIN = "domain";
	/**
	 * 操作成功状态码
	 */
	public static final String SUCCESS = "000000";
	/**
	 * 系统错误状态码
	 */
	public static final String SYSTEM_ERROR_CODE = "999999";
	/**
	 * 系统错误提示信息
	 */
	public static final String SYSTEM_ERROR_MSG = "系统错误";
	/**
	 * 当前用户
	 */
	public static final String CURRENT_USER = "currentUser";
	/**
	 * 用户登录界面
	 */
	public static final String LOGIN_PATH = "/user/jsp/login";
	/**
     * 错误跳转界面
     */
    public static final String ERROR_PATH = "/user/view/login";
    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWD = "1";
    /**
     * 下载类型
     * 课程
     */
    public static final String REF_TYPE_COURSE = "C";
    /**
     * 下载类型
     * 资源
     */
    public static final String REF_TYPE_RESOURCE = "R";
    /**
     * 获取fastdfs连接超时时间
     * 1s
     */
    public static final int FASTDFS_CLIENT_WAIT_TIME = 1;
    /**
     * 排序字段
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";
    /**
     * 排序方式
     * asc 正序
     * desc 倒序
     */
    public static final String ORDER_TYPE = "orderType";
    public static final String ORDER_DESC = "desc";
    public static final String ORDER_ASC = "asc";
    /**
     * 项目根目录
     */
    public static final String LW_ROOT_PATH = "lw.root";
    /**
     * 项目根目录
     */
    public static final String SC_ROOT_PATH = "sc.root";
    /**
     * 项目根目录
     */
    public static final String YC_ROOT_PATH = "yc.root";
    /**
     * 项目根目录
     */
    public static final String YZ_ROOT_PATH = "yz.root";
    /**
     * 项目根目录
     */
    public static final String LB_ROOT_PATH = "lb.root";
    /**
     * 项目根目录
     */
    public static final String ROOT_PATH = "school.root";
    /**
     * 项目根目录
     */
    public static final String TD_ROOT_PATH = "td.root";
    /**
     * 项目根目录
     */
    public static final String GXXD_ROOT_PATH = "gxxd.root";
    /**
     * book首页
     */
    public static final String BOOK_INDEX = "/index.html";
    /**
     * ZIP压缩包后缀
     */
    public static final String ZIP = ".zip";
    /**
     * 数据字典课程类型
     */
    public static final int DICT_COURSE_TYPE = 1;
    /**
     * 数据字典资源类型
     */
    public static final int DICT_RESOURCE_TYPE = 2;
    /**
     * 数据字典课程排序
     */
    public static final int DICT_COURSE_ORDER_TYPE = 3;
    /**
     * 数据字典资源排序排序
     */
    public static final int DICT_RESOURCE_ORDER_TYPE = 4;
    /**
     * 课程公告
     */
    public static final String NOTICE_COURSE_TYPE = "C";
    /**
     * 系统公告
     */
    public static final String NOTICE_SYSTEM_TYPE = "S";
    /**
     * 课本临时文件
     */
    public static final String BOOK_DIR = "book/";
    /**
     * 资源临时文件
     */
    public static final String RESOURCE_DIR = "resource/";
    /**
     * 临时文件
     */
    public static final String TMP_DIR = "tmp/";
	/**
	 * 资源访问方式
	 */
    public static final String USE_ADDRESS = "useAddress";
}
