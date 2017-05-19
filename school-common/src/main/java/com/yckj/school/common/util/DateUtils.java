package com.yckj.school.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static final String SIMPLE_DATE_PATTERN = "yyyy-MM-dd";
	
	
	public static String getSimpleDatePattern(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_PATTERN);
		return sdf.format(date);
	}
}
