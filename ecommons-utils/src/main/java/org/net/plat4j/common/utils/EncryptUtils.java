/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2015年10月13日 下午3:37:58
	
	Revision History:
	Version     Date              					Author			Comments
	1.0      2015年10月13日下午3:37:58		    		chenyj			Create file
=========================================================================
 */
package org.net.plat4j.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.net.plat4j.sr.core.utils.LogHelper;

/**
 * @author chenyj
 *
 */
public class EncryptUtils {
	protected static LogHelper logger = new LogHelper(EncryptUtils.class);
	private static String ENCRYPTY_ALGORITHM = "AES";

	/**
	 * 获取密钥
	 * 
	 * @param password
	 * @return
	 */
	public static SecretKey getKey(String password) {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance(ENCRYPTY_ALGORITHM);
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			_generator.init(128, secureRandom);
			return _generator.generateKey();
		} catch (Exception e) {
			throw new RuntimeException(" 初始化密钥出现异常 ");
		}
	}

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static String encrypt(String content, String password) {
		try {
			SecretKey secretKey = getKey(password);
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, ENCRYPTY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(ENCRYPTY_ALGORITHM);// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return byte2HexStr(result);
		} catch (Exception e) {
			logger.error("加密失败", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static String decrypt(String content, String password) {
		try {
			SecretKey secretKey = getKey(password);
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, ENCRYPTY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(ENCRYPTY_ALGORITHM);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] decryptByte = hexStr2Byte(content);
			byte[] result = cipher.doFinal(decryptByte);
			return new String(result, "UTF-8"); // 加密
		} catch (Exception e) {
			logger.error("解密失败", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	protected static String byte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	protected static byte[] hexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String password = "123456";
		String encryptStr = EncryptUtils.encrypt("测试abcD", password);
		System.out.println("加密后：" + encryptStr);
		String decryptStr = EncryptUtils.decrypt(encryptStr, password);
		System.out.println("解密后：" + decryptStr);
	}
}
