/*******************************************************************************
 * Project: school-common
 * Package: com.yckj.school.common.util
 * Type:    CourseUtil
 * Author:  hefengwen
 * Date:    2016-12-18 21:02:59
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hefengwen
 */
public class CourseUtil {
    
    private static String[] courseTypes = {"公共基础课","专业必修课","专业选修课","实践课"};
    
    public static List<Map<String,Object>> getCourseType(){
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<courseTypes.length;i++){
            Map<String,Object> m = new HashMap<String,Object>();
            m.put("type", i+1);
            m.put("name", courseTypes[i]);
            list.add(m);
        }
        return list;
    }
}
