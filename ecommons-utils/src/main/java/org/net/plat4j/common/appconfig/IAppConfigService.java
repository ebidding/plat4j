/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2015年9月14日 下午2:00:22
	
	Revision History:
	Version     Date              					Author			Comments
	1.0      2015年9月14日下午2:00:22		    		chenyj			Create file
=========================================================================
*/
package org.net.plat4j.common.appconfig;

import org.net.plat4j.sr.core.base.IBaseService;

/**
 * @author chenyj
 *
 */
public interface IAppConfigService extends IBaseService{
	public String getProperties(Long agentId ,String key);
}
