/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2015年4月29日 下午5:20:30
	
	Revision History:
	Version     Date              					Author			Comments
	1.0      2015年4月29日下午5:20:30				chenyj			Create file
=========================================================================
*/
package org.net.plat4j.common.autocode;

import java.util.Map;

/**
 * @author chenyj
 *
 */
public interface IAutoCodeService {
	/**
	 * 新开一个事物获取编号
	 * @param code 编号编码
	 * @param agentId 代理机构id
	 * @param userVariable 用户自定义变量
	 * @return
	 */
	public String newTxGetCode(String code,Long agentId,Map<String,Object> userVariable);
	/**
	 * 新开一个事务废弃编号
	 * @param code 编号编码
	 * @param agentId 代理机构id
	 * @param autoNumber 编号
	 */
	public void newTxDiscardCode(String code,Long agentId,String autoNumber);
}
