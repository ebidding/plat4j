/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2015年5月5日 上午9:59:26
	
	Revision History:
	Version     Date              					Author			Comments
	1.0      2015年5月5日上午9:59:26		    		chenyj			Create file
=========================================================================
 */
package org.net.plat4j.sr.core.utils.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import org.net.plat4j.sr.core.utils.LogHelper;

/**
 * @author chenyj
 * 
 */
public class BeanUtil {
	private static LogHelper logger = new LogHelper(BeanUtil.class);
	public enum MethodPrefix{get,set};
	/**
	 * 获取对象的值
	 * @param bean
	 *            对象
	 * @param propertyName
	 *            属性名称
	 * @return
	 */
	public static Object getProperty( Object bean, String propertyName) {
		Class<?> beanClass = bean.getClass();
		try {
			Method propertyMethod = beanClass.getMethod(getMethodName(propertyName, MethodPrefix.get));
//			propertyMethod.setAccessible(true);
			return propertyMethod.invoke(bean);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			logger.error("No such property. Bean [ " + bean + " ] , property [ " + propertyName
					+ " ]. ",e);
			throw new RuntimeException("No such property. Bean [ " + bean + " ] , property [ " + propertyName
					+ " ]. ");
		}

	}
	
	/**
	 * 设置对象的属性值
	 * @param bean 对象
	 * @param propertyName 属性名称
	 * @param propertyValue 属性值
	 */
	public static void setProperty(Object bean , String propertyName , Object propertyValue){
		Class<?> beanClass = bean.getClass();
		try {
			Method propertyMethod = beanClass.getMethod(propertyName, propertyValue.getClass());
			propertyMethod.invoke(bean, propertyValue);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.error("No such property. Bean [ " + bean + " ] , property name [ " + propertyName
					+ " ] property value [ " + propertyValue + " ]. ", e);;
			throw new RuntimeException("No such property. Bean [ " + bean + " ] , property name [ " + propertyName
					+ " ] property value [ " + propertyValue + " ]. ");
		}
	}

	/**
	 * 获取属性的getter，setter方法名
	 * 
	 * @param propertyName
	 *            属性名
	 * @param prefix
	 *            前缀
	 * @return
	 */
	public static String getMethodName(String propertyName, MethodPrefix prefix) {
		StringBuilder sb = new StringBuilder(prefix.toString());
		sb.append(propertyName.substring(0, 1).toUpperCase()).append(propertyName.substring(1));
		return sb.toString();
	}
}
