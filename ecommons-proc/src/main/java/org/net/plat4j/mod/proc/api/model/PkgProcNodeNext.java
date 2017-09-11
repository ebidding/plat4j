/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-22 15:41:19
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-22 15:41:19		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;

/**
 * @author yujie
 *
 */
public class PkgProcNodeNext {
	private Long pkgNodeId;
	private Long nextPkgNodeId;
	private Boolean isRequiredForNext;
	
	/**
	 * @return the pkgNodeId
	 */
	public Long getPkgNodeId() {
		return pkgNodeId;
	}
	/**
	 * @param pkgNodeId the pkgNodeId to set
	 */
	public void setPkgNodeId(Long pkgNodeId) {
		this.pkgNodeId = pkgNodeId;
	}
	/**
	 * @return the nextPkgNodeId
	 */
	public Long getNextPkgNodeId() {
		return nextPkgNodeId;
	}
	/**
	 * @param nextPkgNodeId the nextPkgNodeId to set
	 */
	public void setNextPkgNodeId(Long nextPkgNodeId) {
		this.nextPkgNodeId = nextPkgNodeId;
	}
	/**
	 * @return the isRequiredForNext
	 */
	public Boolean getIsRequiredForNext() {
		return isRequiredForNext;
	}
	/**
	 * @param isRequiredForNext the isRequiredForNext to set
	 */
	public void setIsRequiredForNext(Boolean isRequiredForNext) {
		this.isRequiredForNext = isRequiredForNext;
	}
}
