package com.yckj.school.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;

public class PropertyUtils {
	/**
     * 对象属性复制
     * @param dest：目标对象
     * @param orig：源对象
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
     */
    public static void propertyCopy(Object dest,Object orig) throws Exception{
            BeanUtils.copyProperties(dest, orig);
    }
    /**
     * map转对象
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String,Object> map,Class<?> beanClass) throws Exception{
    	if(MapUtils.isEmpty(map)){
    		return null;
    	}
    	Object obj = beanClass.newInstance();
    	Class<?> clazz = obj.getClass();
	    Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            int mod = field.getModifiers();
            if(Modifier.isStatic(mod)||Modifier.isFinal(mod)){
                continue;
            }
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }
    	return obj;
    }
    /**
     * 对象转map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String,Object> objectToMap(Object obj) throws Exception{
    	if(obj==null){
    		return null;
    	}
    	Map<String,Object> map = new HashMap<>();
    	Class<?> clazz = obj.getClass();
    	for(;clazz != Object.class;clazz=clazz.getSuperclass()){
    	    Field[] fields = clazz.getDeclaredFields();
            for(Field field:fields){
                field.setAccessible(true);
//                System.out.println("field name="+field.getName()+"field value="+field.get(obj));
                map.put(field.getName(), field.get(obj));
            }
    	}
    	return map;
    }
}
