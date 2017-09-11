/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: WGY
	Version: 1.0
	Created Time: 2017年8月22日 下午5:38:50
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年8月22日下午5:38:50		WGY			Create file
=========================================================================
*/

package org.net.plat4j.common.druid;

import org.net.plat4j.common.security.RSAUtils;
import org.net.plat4j.sr.core.utils.LogHelper;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据库配置解密
 * @author WGY
 *
 */
public class MyDruidDataSource extends DruidDataSource {
	private static final long serialVersionUID = 1377390212442554779L;
	private static LogHelper logger = new LogHelper(MyDruidDataSource.class);
	
	@Override
	public void setUsername(String username) {
		try {
			username = new String(RSAUtils.decryptByPublicKey(username
					.getBytes()));
		} catch (Exception e) {
			logger.error("解密数据库用户名失败！", e);
			throw new RuntimeException( e);
		}
		super.setUsername(username);
	}
	@Override
	public void setPassword(String password) {
		try {
			password = new String(RSAUtils.decryptByPublicKey(password
					.getBytes()));
		} catch (Exception e) {
			logger.error("解密数据库密码失败！", e);
			throw new RuntimeException( e);
		}
		super.setPassword(password);
	}
	@Override
	public void setUrl(String jdbcUrl) {
		try {
			jdbcUrl = new String(RSAUtils.decryptByPublicKey(jdbcUrl.getBytes()));
		} catch (Exception e) {
			logger.error("解密数据库链接地址失败！", e);
			throw new RuntimeException( e);
		}
		super.setUrl(jdbcUrl);
    }
	
}
