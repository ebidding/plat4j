/*
	Copyright (C) 2014 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2014-07-16 11:10:48
	
	Revision History:
	Version     Date              					Author			Comments
	1.0         	2014-07-16 11:10:48		yujie				Create file
=========================================================================
 */

package org.net.plat4j.common.utils;

/**
 * @author yujie
 * 
 */
public class PageUtils {
	/**
	 * 计算当前显示的可操作的页数范围。
	 * 
	 * @param pageNo
	 *            当前页数
	 * @param pageCount
	 *            总页数
	 * @return int 类型的数组，其中包括两个数字，第一个数字（[0]）表示开始页数（含）， 第二个数字表示结束页数（含）
	 */
	public static int[] getPageScope(int pageNo, int pageCount) {
		int startPage = pageNo - 2;
		int endPage = pageNo + 2;
		if (startPage < 1) {
			startPage = 1;
			endPage = endPage + (2 - pageNo);
		}

		if (endPage > pageCount) {
			endPage = pageCount;
			startPage = startPage - (2 - (endPage - pageCount));
		}

		if (startPage <= 0) {
			startPage = 1;
		}

		if (endPage > pageCount) {
			endPage = pageCount;
		}

		int[] result = new int[2];
		result[0] = startPage;
		result[1] = endPage;
		return result;
	}

	public static int[] getPageInfo(int pageNo, int pageCount) {

		int startPage = pageNo - 2;
		int endPage = pageNo + 2;
		if (startPage < 1) {
			startPage = 1;
			endPage = endPage + (2 - pageNo);
		}

		if (endPage > pageCount) {
			endPage = pageCount;
			startPage = startPage - (2 - (endPage - pageCount));
		}

		if (startPage <= 0) {
			startPage = 1;
		}

		if (endPage > pageCount) {
			endPage = pageCount;
		}

		int[] result = new int[2];
		result[0] = startPage;
		result[1] = endPage;
		return result;
	}
}
