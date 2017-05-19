package com.yckj.school.common.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 资源串管理工具
 * @author hefengwen
 *
 */
public class LocaleUtils {
	/**
	   * 运行日志
	   */
	  private static final Logger logger = LoggerFactory.getLogger(LocaleUtils.class);

	  /**
	   * 资源管理集，用于缓存已经生成的某个资源文件对应的LocaleManager，当再次调用时，只需要从缓存中获取即可。
	   * <li>key：资源文件所在包名</li>
	   * <li>value：该资源文件对应的LocaleManager</li>
	   */
	  private static Map<String, LocaleUtils> managers = new ConcurrentHashMap<>();

	  /**
	   * ResourceBundle
	   */
	  private ResourceBundle bundle;

	  /**
	   * 获取资源文件对应的I18nManager.
	   * <p>
	   * 说明：
	   * <p>
	   * <b>1、相同的资源文件对应同一个LocaleManager，不会重复创建。</b>
	   * <p>
	   * <b>2、class和资源文件必须放在同一个包下才能找到。</b>
	   *
	   * @param clazz
	   *          资源文件所在包中的其中任意类，一般情况下为对应的Activator类
	   * @return 资源文件对应的LocaleManager
	   */
	  public static synchronized LocaleUtils getManager(Class<?> clazz) {
	    LocaleUtils mgr = managers.get(clazz.getName());
	    if (mgr == null) {
	      mgr = new LocaleUtils(clazz);
	      managers.put(clazz.getName(), mgr);
	    }

	    return mgr;
	  }

	  /**
	   * 从资源文件中获取字符串。
	   *
	   * @param key 
	   *          资源文件中的key
	   * @param args
	   *          变参参数组，用于替换资源串占位符
	   * @return 对应字符串。规则如下：
	   * <ul>
	   * <li>如果key和value均存在，则正常返回对应字符串</li>
	   * <li>如果key在资源文件中不存在，则直接返回 ?key?的方式（便于定位），如：<code>?userName1?</code></li>
	   * <li>如果key对应的value没有定义，则直接返回 key的值，如：<code>userName1</code></li>
	   * </ul>
	   */
	  public String getString(String key, Object... args) {
	    String value;

	    try {
	      value = this.bundle.getString(key);
	    }
	    
	    // 如果资源文件中找不到对应的key值，直接以"?key?"的形式返回
	    catch (MissingResourceException mre) {
	      
	      return "? " + key + " ?";
	    }

	    // 如果资源文件中key值对应的值未定义，则直接返回key值本身
	    if (value == null) {
	      value = key;
	    }

	    return format(value, args);
	  }

	  /**
	   * 创建LocalUtils
	   *
	   * @param clazz
	   *          资源文件所在包中的其中任意类
	   */
	  private LocaleUtils(Class<?> clazz) {
	    try {
	      this.bundle = ResourceBundle.getBundle(clazz.getName(), Locale.getDefault(), clazz.getClassLoader());
	    }
	    catch (Exception e) {
	      logger.error(null, e);
	    }
	  }

	  /**
	   * 占位符替换
	   *
	   * @param value
	   *          带有占位符的资源串
	   * @param args
	   *          每个占位符对应内容
	   * @return 替换完成的字符串
	   */
	  private String format(String value, Object... args) {
	    MessageFormat mf = new MessageFormat(value);
	    
	    mf.setLocale(Locale.getDefault());
	    
	    return mf.format(args, new StringBuffer(), null).toString();
	  }
}
