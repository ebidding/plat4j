/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2017年2月24日 下午2:52:11
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年2月24日下午2:52:11		chenyj			Create file
=========================================================================
*/
package org.net.plat4j.pdf.signature.cert;

import java.security.PrivateKey;
import java.security.cert.Certificate;

import org.net.plat4j.pdf.signature.PDFSignatureException;

/**
 * @author chenyj
 */
public interface ICertificate {
	/**
	 * 获取公钥证书链
	 * @return
	 */
	public Certificate[] getCertificate() throws PDFSignatureException;
	/**
	 * 通过私钥签名
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public byte[] sign(byte[] src) throws PDFSignatureException;
	
	public PrivateKey getPrivateKey()throws PDFSignatureException ;
}
