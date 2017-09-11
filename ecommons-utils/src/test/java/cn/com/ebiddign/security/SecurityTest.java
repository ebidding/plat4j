/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: WGY
	Version: 1.0
	Created Time: 2017年8月21日 下午6:21:23
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年8月21日下午6:21:23		WGY			Create file
=========================================================================
*/

package cn.com.ebiddign.security;

import org.net.plat4j.common.security.RSAUtils;

/**
 * @author WGY
 *
 */
public class SecurityTest {
	public static void main(String[] args) throws Exception {
		RSAUtilTest();
	}
	
	
	public static void RSAUtilTest() throws Exception{
		String data = "jdbc:mysql://120.132.27.94:1!@#$%^&*()_/";		
		
		byte[] encrypt = RSAUtils.encryptByPrivateKey(data.getBytes());
		System.out.println(new String(encrypt) );
		byte[] decryptByPublicKey = RSAUtils.decryptByPublicKey(encrypt);
		System.out.println(new String(decryptByPublicKey));
	}
}
