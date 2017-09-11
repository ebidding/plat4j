/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2017年2月24日 下午6:00:17
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年2月24日下午6:00:17		chenyj			Create file
=========================================================================
*/
package org.net.plat4j.pdf.signature;

/**
 * pdf签章异常
 * @author chenyj
 */
public class PDFSignatureException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1572659115555919542L;

	public PDFSignatureException(){
		super();
	}
	
	public PDFSignatureException(String message){
		super(message);
	}
	
	public PDFSignatureException(Throwable cause){
		super(cause);
	}
	
	public PDFSignatureException(String message, Throwable cause){
		super(message, cause);
	}
}
