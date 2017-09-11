/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2015年5月15日 下午1:15:14
	
	Revision History:
	Version     Date              					Author			Comments
	1.0      2015年5月15日下午1:15:14		    		chenyj			Create file
=========================================================================
*/
package net.plat4j.core.pageTools;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * @author chenyj
 *
 */
public class PageTools {
	public static final String PAGE_SIZE_NAME = "ebidding_page_size";
	public static final String PAGE_SIZE_CHANGE = "page_size_change";
	public static final String PAGE_NO_NAME = "ebidding_page_no";
	public static final int DEFAULT_PAGE_SIZE = 10;
	private boolean init = false;
	private int pageNo = 1;
	private int pageSize = 10;
	private int recordCount = 0;
	private int pageCount = 0;
	private int startRow = 0;
	private int lastPageRow = 0;
	private int currentPageNo = 0;

	public PageTools() {
	}
	  
	public PageTools(int recordCount, int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = (pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE);
		this.recordCount = recordCount;
		initialize();
	}
	  
	protected void initialize() {
		if (this.recordCount % this.pageSize == 0) {
			this.pageCount = this.recordCount / this.pageSize;
		} else {
			this.pageCount = this.recordCount / this.pageSize + 1;
		}
		if (this.pageNo > this.pageCount) {
			this.pageNo = this.pageCount;
		} else if (this.pageNo < 1) {
			this.pageNo = 1;
		}
		this.startRow = ((this.pageNo - 1) * this.pageSize);
		this.lastPageRow = ((this.pageCount - 1) * this.pageSize);
		this.init = true;
	}
	  
	public int getStartRow() {
		if (!this.init) {
			initialize();
		}
		return this.startRow > 0 ? this.startRow : 0;
	}
	  
	public int getLastPageRow() {
		return this.lastPageRow;
	}

	public int getPageSize() {
		return this.pageSize;
	}
	  
	public int getRecordCount() {
		return this.recordCount;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		this.init = false;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.init = false;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
		this.init = false;
	}
	  
	public static void main(String[] paramArrayOfString) {
		PageTools localPageTools = new PageTools(101, 4, 20);
		System.out.println(localPageTools.getStartRow());
		System.out.println(localPageTools.getLastPageRow());
	}
	  
	public int getPageCount() {
		return this.pageCount;
	}

	public int getCurrentPageNo() {
		return this.currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setLastPageRow(int lastPageRow) {
		this.lastPageRow = lastPageRow;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	  
	public void fillPageWithParameter(HttpServletRequest request) {
		String str1 = request.getParameter(PAGE_NO_NAME);
		String str2 = request.getParameter(PAGE_SIZE_NAME);
		if (StringUtils.isNotEmpty(str1)) {
			this.pageNo = Integer.parseInt(str1);
		}
		if (StringUtils.isNotEmpty(str2)) {
			this.pageSize = Integer.parseInt(str2);
		}
		if (StringUtils.isEmpty(str2)) {
			str2 = request.getParameter(PAGE_SIZE_CHANGE);
			if (StringUtils.isNotEmpty(str2)) {
				this.pageSize = Integer.parseInt(str2);
			}
		}
	}
	  
	public void fillRequestWithPage(HttpServletRequest request) {
		request.setAttribute(PAGE_NO_NAME, new Integer(this.pageNo));
		request.setAttribute(PAGE_SIZE_NAME, new Integer(this.pageSize));
	}
	  
	public void calc() {
		if (!this.init) {
			initialize();
		}
	}
}
