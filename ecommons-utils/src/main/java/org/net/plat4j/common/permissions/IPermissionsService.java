/*
	Copyright (C) 2014 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: cairw
	Version: 1.0
	Created Time: 2014-8-1 下午5:19:06
	
	Revision History:
	Version     Date              					Author			Comments
	1.0         	2014-8-1下午5:19:06		cairw			Create file
=========================================================================
*/


package org.net.plat4j.common.permissions;

/**
 * @author cairw
 *
 */
public interface IPermissionsService {
	/**
	 * 
	 * @param uri  路径
	 * @param method 方法
	 * @param userId  用户ID
	 * @return 判断是否有这个按钮的权限
	 */
	Boolean queryButtonPermissions(String uri ,String method,Long userId);
}
