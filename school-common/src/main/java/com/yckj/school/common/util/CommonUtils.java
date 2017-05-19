package com.yckj.school.common.util;

import org.apache.commons.lang3.StringUtils;

public class CommonUtils {
	/**
	 * 获取文件后缀名
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName){
		if(StringUtils.isBlank(fileName)||!fileName.contains(".")){
			return "";
		}else{
			return fileName.substring(fileName.lastIndexOf(".")+1);
		}
	}

}
