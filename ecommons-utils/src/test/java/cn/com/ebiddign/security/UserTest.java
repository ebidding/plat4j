/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: WGY
	Version: 1.0
	Created Time: 2017年8月23日 下午2:20:57
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年8月23日下午2:20:57		WGY			Create file
=========================================================================
*/

package cn.com.ebiddign.security;

import org.apache.commons.codec.binary.Hex;
import org.net.plat4j.common.utils.Digests;


/**
 * @author WGY
 *
 */
public class UserTest {
	public static void main(String[] args) {
//		testSalt();
		testSha256Digest();
	}
	/**
	 * 
	 */
	private static void testSha256Digest() {
		String psw = "123456";
		byte[] salt = Digests.generateSalt(16);
		byte[] sha256 = Digests.sha256(psw.getBytes(),salt , 1024);
		System.out.println(Hex.encodeHexString(sha256));
		byte[] sha2562 = Digests.sha256(psw.getBytes(),salt , 1024);
		System.out.println(Hex.encodeHexString(sha2562));
		
	}
	public static void testSalt(){
		 byte[] salt = Digests.generateSalt(16);
		 System.out.println(Hex.encodeHexString(salt));
	}
}
