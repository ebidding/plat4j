/*
	Copyright (C) 2014 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2014-07-15 16:02:54
	
	Revision History:
	Version     Date              					Author			Comments
	1.0         	2014-07-15 16:02:54		yujie				Create file
=========================================================================
*/

package org.net.plat4j.common.config;

/**
 * @author yujie
 *
 */
public interface IConfigUtil {
	String getCompanyConfigValue(Long agencyId,String configCode);
}
