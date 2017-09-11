/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: cairw
	Version: 1.0
	Created Time: 2015年9月18日 下午4:18:45
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2015年9月18日下午4:18:45		cairw			Create file
=========================================================================
*/

package org.net.plat4j.common.user;

/**
 * @author cairw
 *
 */
public class SysUserAgencyInfo {
	private Long userId;
	private Long identity;
	private String identityName;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getIdentity() {
		return identity;
	}
	public void setIdentity(Long identity) {
		this.identity = identity;
	}
	public String getIdentityName() {
		return identityName;
	}
	public void setIdentityName(String identityName) {
		this.identityName = identityName;
	}
}
