/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2015年4月29日 下午5:20:59
	
	Revision History:
	Version     Date              					Author			Comments
	1.0      2015年4月29日下午5:20:59				chenyj			Create file
=========================================================================
*/
package org.net.plat4j.common.utils;

import java.util.Map;

import net.plat4j.core.spring.BeanFactory;

import org.net.plat4j.common.autocode.IAutoCodeService;

/**
 * @author chenyj
 *
 */
public class AutoCodeUtil {
	/**
	 * 获取编号
	 * @param codeType 编号类型
	 * @return
	 */
	public static synchronized String getCode(String codeType){
		IAutoCodeService autoCodeService = (IAutoCodeService) BeanFactory.getBean("IAutoCodeService");
		return autoCodeService.newTxGetCode(codeType, null, null);
	}
	/**
	 * 获取编号
	 * @param codeType 编号类型
	 * @param agentId 代理机构id
	 * @return
	 */
	public static synchronized String getCode(String codeType,Long agentId){
		IAutoCodeService autoCodeService = (IAutoCodeService) BeanFactory.getBean("IAutoCodeService");
		return autoCodeService.newTxGetCode(codeType, agentId, null);
	}
	/**
	 * 获取编号
	 * @param codeType 编号类型
	 * @param userVariable 用户自定义变量
	 * @return
	 */
	public static synchronized String getCode(String codeType,Map<String,Object> userVariable){
		IAutoCodeService autoCodeService = (IAutoCodeService) BeanFactory.getBean("IAutoCodeService");
		return autoCodeService.newTxGetCode(codeType, null, userVariable);
	}
	/**
	 * 获取编号
	 * @param codeType 编号类型
	 * @param agentId 代理机构id
	 * @param userVariable 用户自定义变量
	 * @return
	 */
	public static synchronized String getCode(String codeType,Long agentId,Map<String,Object> userVariable){
		IAutoCodeService autoCodeService = (IAutoCodeService) BeanFactory.getBean("IAutoCodeService");
		return autoCodeService.newTxGetCode(codeType, agentId, userVariable);
	}
	/**
	 * 获取平台级别的编号
	 * @param codeType 编号类型
	 * @return
	 */
	public static synchronized String getPlatformCode(String codeType){
		IAutoCodeService autoCodeService = (IAutoCodeService) BeanFactory.getBean("IAutoCodeService");
		return autoCodeService.newTxGetCode(codeType, Constants.PLATFORM_ID, null);
	}
	/**
	 * 获取平台级别的编号
	 * @param codeType 编号类型
	 * @param userVariable 用户自定义变量
	 * @return
	 */
	public static synchronized String getPlatformCode(String codeType,Map<String,Object> userVariable){
		IAutoCodeService autoCodeService = (IAutoCodeService) BeanFactory.getBean("IAutoCodeService");
		return autoCodeService.newTxGetCode(codeType, Constants.PLATFORM_ID, userVariable);
	}
	/**
	 * 废弃编号
	 * @param codeType 编号类型
	 * @param autoCode 编号
	 * @param agentId 代理机构id
	 */
	public static synchronized void discardCode(String codeType,String autoCode,Long agentId){
		IAutoCodeService autoCodeService = (IAutoCodeService) BeanFactory.getBean("IAutoCodeService");
		autoCodeService.newTxDiscardCode(codeType, agentId, autoCode);
	}
	/**
	 * 废弃编号
	 * @param codeType 编号类型
	 * @param autoCode 编号
	 */
	public static synchronized void discardCode(String codeType,String autoCode){
		IAutoCodeService autoCodeService = (IAutoCodeService) BeanFactory.getBean("IAutoCodeService");
		autoCodeService.newTxDiscardCode(codeType, null, autoCode);
	}
	/**
	 * 废弃平台级别编号
	 * @param codeType 编号类型
	 * @param autoCode 编号
	 */
	public static synchronized void discardPlatformCode(String codeType,String autoCode){
		IAutoCodeService autoCodeService = (IAutoCodeService) BeanFactory.getBean("IAutoCodeService");
		autoCodeService.newTxDiscardCode(codeType, Constants.PLATFORM_ID, autoCode);
	}
}
