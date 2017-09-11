/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: WGY
	Version: 1.0
	Created Time: 2017年3月24日 下午2:46:17
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年3月24日下午2:46:17		WGY			Create file
=========================================================================
*/

package org.net.plat4j.pdf.signature.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.net.plat4j.pdf.signature.PDFSignatureException;
import org.net.plat4j.pdf.signature.model.TextPositionSequence;


/**
 * @author WGY 2017年3月24日
 *
 */
public class PdfPositionUtil {
	protected static Logger logger = LoggerFactory.getLogger(PdfPositionUtil.class);
	/**
	 * 查找pdf文件中关键字的位置
	 * 
	 * @param filePath
	 *            pdf文件路径
	 * @param text
	 *            关键字
	 * @return 返回位置的集合
	 * @throws IOException
	 */
	public static List<TextPositionSequence> getTextPosition(String filePath, String text) {
		try {
			return PdfSearchUtil.getTextPosition(filePath, text);
			
		} catch (IOException e) {
			logger.error("查询关键字出错", e);
			throw new PDFSignatureException("查询关键字出错", e);
		} 
	}
}
