/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2015年5月28日 下午5:17:50
	
	Revision History:
	Version     Date              					Author			Comments
	1.0      2015年5月28日下午5:17:50		    		chenyj			Create file
=========================================================================
*/
package org.net.plat4j.mod.proc.api.model;

/**
 * @author chenyj
 *
 */
public class PkgProcNodeModel {
	private Long packageId;
	private String pkgNodeName;
	private String pkgNodeStatus;
	private String isDisplayStatus;
	
	public Long getPackageId() {
		return packageId;
	}
	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}
	public String getPkgNodeName() {
		return pkgNodeName;
	}
	public void setPkgNodeName(String pkgNodeName) {
		this.pkgNodeName = pkgNodeName;
	}
	public String getPkgNodeStatus() {
		return pkgNodeStatus;
	}
	public void setPkgNodeStatus(String pkgNodeStatus) {
		this.pkgNodeStatus = pkgNodeStatus;
	}
	/**
	 * @return the isDisplayStatus
	 */
	public String getIsDisplayStatus() {
		return isDisplayStatus;
	}
	/**
	 * @param isDisplayStatus the isDisplayStatus to set
	 */
	public void setIsDisplayStatus(String isDisplayStatus) {
		this.isDisplayStatus = isDisplayStatus;
	}
}
