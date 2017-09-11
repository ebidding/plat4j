/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2017年3月22日 上午11:13:45
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年3月22日上午11:13:45		chenyj			Create file
=========================================================================
*/
package org.net.plat4j.pdf.signature.cert.impl;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.Certificate;

import org.net.plat4j.pdf.signature.PDFSignatureException;
import org.net.plat4j.pdf.signature.cert.ICertificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * p12格式证书实现类
 * 
 * @author chenyj
 */
public class P12Certificate implements ICertificate {
	private String p12Filepath;
	private String password;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @param p12Filepath
	 *            证书路径
	 * @param password
	 *            证书密码
	 */
	public P12Certificate(String p12Filepath, String password) {
		this.p12Filepath = p12Filepath;
		this.password = password;
	}

	@Override
	public Certificate[] getCertificate() throws PDFSignatureException {
		try {
			KeyStore ks = KeyStore.getInstance("pkcs12");
			ks.load(new FileInputStream(p12Filepath), password.toCharArray());
			String alias = (String) ks.aliases().nextElement();
			Certificate[] chain = ks.getCertificateChain(alias);
			return chain;
		} catch (Exception e) {
			logger.error("获取公钥证书失败，证书路径：" + p12Filepath + " , 证书密码：" + password, e);
			throw new PDFSignatureException("获取公钥证书失败，证书路径：" + p12Filepath + " , 证书密码：" + password, e);
		}
	}

	@Override
	public byte[] sign(byte[] src) throws PDFSignatureException {
		try {
			KeyStore ks = KeyStore.getInstance("pkcs12");
			ks.load(new FileInputStream(p12Filepath), password.toCharArray());
			String alias = (String) ks.aliases().nextElement();
			PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password.toCharArray());
			Signature rsa = Signature.getInstance("SHA1withRSA");
			rsa.initSign(privateKey);
			rsa.update(src);
			byte[] signData = rsa.sign();
			return signData;
		} catch (Exception e) {
			logger.error("使用私钥证书签名失败，证书路径：" + p12Filepath + " , 证书密码：" + password, e);
			throw new PDFSignatureException("使用私钥证书签名失败，证书路径：" + p12Filepath + " , 证书密码：" + password, e);
		}
	}

	@Override
	public PrivateKey getPrivateKey()throws PDFSignatureException  {
		try {
			KeyStore ks = KeyStore.getInstance("pkcs12");
			ks.load(new FileInputStream(p12Filepath), password.toCharArray());
			String alias = (String) ks.aliases().nextElement();
			PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password.toCharArray());
			return privateKey;
		} catch (Exception e) {
			logger.error("获取私钥证书失败，证书路径：" + p12Filepath + " , 证书密码：" + password, e);
			throw new PDFSignatureException("获取私钥证书失败，证书路径：" + p12Filepath + " , 证书密码：" + password, e);
		} 
	}

}
