/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2015年5月26日 下午4:40:33
	
	Revision History:
	Version     Date              					Author			Comments
	1.0      2015年5月26日下午4:40:33		    		chenyj			Create file
=========================================================================
*/
package org.net.plat4j.mod.proc.api;

/**
 * @author chenyj
 *
 */
public class PkgNodeStatus {
	/**
	 * 0 - 未创建（Uninitialized）
	 */
	public static final String UNINITIALIZED = "0";
	/**
	 * 1 - 编辑中（Working）
	 */
	public static final String WORKING = "1";
	/**
	 * 2 - 被锁定（Locked）
	 */
	public static final String LOCKED = "2";
	/**
	 * 3 - 被驳回（Locked）
	 */
	public static final String DISMISSED = "3";
	/**
	 * 4 - 可发布（Issuable）
	 */
	public static final String ISSUABLE = "4";
	/**
	 * 5 - 已结束（Final）
	 */
	public static final String FINAL = "5";
	
	private String status;
	
	public PkgNodeStatus(String statusText) {
		this.status = statusText;
	}
	
	public Boolean isUninitialized() {
		return UNINITIALIZED.equals(this.status);
	}
	
	public Boolean isWorking() {
		return WORKING.equals(this.status);
	}

	public Boolean isLocked() {
		return LOCKED.equals(this.status);
	}

	public Boolean isDismissed() {
		return DISMISSED.equals(this.status);
	}
	
	public Boolean isIssuable() {
		return ISSUABLE.equals(this.status);
	}
	
	public Boolean isFinal() {
		return FINAL.equals(this.status);
	}
	
	public String disp(String btnType) {
		btnType = btnType.toLowerCase();
		switch(btnType) {
			case "0":
			case "edit":
			case "mgt":
					return getEditDisplay();
			case "1":
			case "pub":
					return getPublishDisplay();
			case "2":
			case "view":
					return getViewDisplay();
			case "3":
			case "supplierview":
					return getSupplierViewDisplay();
			case "uninitview":
				return getUnInitViewDisplay();		
			default:
				return "-1";
		}
	}
	
	//0-灰色不可点击1-黑色可点击2-绿色可点击3-不显示
	
	public String getUnInitViewDisplay() {
		switch(this.status) {
			case UNINITIALIZED: return "0";
			case WORKING: return "0";
			case LOCKED: return "0";
			case DISMISSED: return "0";
			case ISSUABLE: return "0";
			case FINAL: return "1";
			default: return "-1";
		}
	}
	
	public String getEditDisplay() {
		switch(this.status) {
			case UNINITIALIZED: return "1";
			case WORKING: return "2";
			case LOCKED: return "0";
			case DISMISSED: return "2";
			case ISSUABLE: return "3";
			case FINAL: return "3";
			default: return "-1";
		}
	}
	
	public String getPublishDisplay() {
		switch(this.status) {
			case UNINITIALIZED: return "3";
			case WORKING: return "3";
			case LOCKED: return "3";
			case DISMISSED: return "3";
			case ISSUABLE: return "2";
			case FINAL: return "3";
			default: return "-1";
		}
	}
	
	public String getViewDisplay() {
		switch(this.status) {
			case UNINITIALIZED: return "3";
			case WORKING: return "3";
			case LOCKED: return "1";
			case DISMISSED: return "3";
			case ISSUABLE: return "3";
			case FINAL: return "1";
			default: return "-1";
		}
	}
	public String getSupplierViewDisplay() {
		switch(this.status) {
			case UNINITIALIZED: return "3";
			case WORKING: return "3";
			case LOCKED: return "3";
			case DISMISSED: return "3";
			case ISSUABLE: return "3";
			case FINAL: return "1";
			default: return "-1";
		}
	}
}
