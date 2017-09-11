/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2017年2月23日 下午3:58:37
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年2月23日下午3:58:37		chenyj			Create file
=========================================================================
*/
package org.net.plat4j.pdf.signature;

import org.net.plat4j.pdf.signature.cert.ICertificate;
import org.net.plat4j.pdf.signature.cert.impl.P12Certificate;

/**
 * @author chenyj
 *
 */
public class PDFSignatureTest {
	public static void signatureUsing1024() {
		String pdfFilePath = "/Users/rudy/keystore/testSignature.pdf";
		String signatureFilePath = "/Users/rudy/keystore/testSignature1.pdf";
		String keyword = "易招标签章位置";
		String imagePath = "/Users/rudy/keystore/default.jpg";
		ICertificate cert = new P12Certificate("/Users/rudy/keystore/pdfsignature1024.p12", "123456");
		PDFSignature signature = new PDFSignature(pdfFilePath, signatureFilePath, keyword, imagePath, cert);
		signature.signature();
	}

	public static void signatureUsing2048() {
		String pdfFilePath = "/Users/rudy/keystore/testSignature.pdf";
		String signatureFilePath = "/Users/rudy/keystore/testSignature2.pdf";
		String keyword = "易招标签章位置";
		String imagePath = "/Users/rudy/keystore/default.jpg";
		ICertificate cert = new P12Certificate("/Users/rudy/keystore/UserCert.p12", "16ef78f9");
		PDFSignature signature = new PDFSignature(pdfFilePath, signatureFilePath, keyword, imagePath, cert);
		signature.signature();
	}

	public static void main(String[] args) throws Exception {
//		signatureUsing1024();
		signatureUsing2048();
		System.out.println("end");
	}
}
