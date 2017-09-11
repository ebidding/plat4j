/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015年4月27日 下午7:34:44
	
	Revision History:
	Version     Date              				Author			Comments
	1.0         	2015年4月27日 下午7:34:44		yujie				Create file
=========================================================================
*/

package org.net.plat4j.api.utils;

import net.plat4j.core.spring.BeanFactory;

/**
 * Api 相关工具类的基类，提供获取实现工具类接口的实例方法。
 */
public class UtilsServiceFactory {
	public static <TUtilsServiceInterf> TUtilsServiceInterf getService(Class<TUtilsServiceInterf> utilsServiceInterf) {
	    return BeanFactory.getBean(utilsServiceInterf.getSimpleName());
	}
}
