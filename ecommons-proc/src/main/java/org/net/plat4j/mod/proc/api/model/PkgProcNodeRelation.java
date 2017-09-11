/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-06-08 10:15:08
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-06-08 10:15:08		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;

/**
 * @author yujie
 *
 */
public class PkgProcNodeRelation {
	private Long curPkgNodeId;
	private Long rePackageId;
	private Long rePkgProcId;
	private Long rePkgNodeId;
	
	/**
	 * @return the curPkgNodeId
	 */
	public Long getCurPkgNodeId() {
		return curPkgNodeId;
	}
	/**
	 * @param curPkgNodeId the curPkgNodeId to set
	 */
	public void setCurPkgNodeId(Long curPkgNodeId) {
		this.curPkgNodeId = curPkgNodeId;
	}
	/**
	 * @return the rePackageId
	 */
	public Long getRePackageId() {
		return rePackageId;
	}
	/**
	 * @param rePackageId the rePackageId to set
	 */
	public void setRePackageId(Long rePackageId) {
		this.rePackageId = rePackageId;
	}
	/**
	 * @return the rePkgProcId
	 */
	public Long getRePkgProcId() {
		return rePkgProcId;
	}
	/**
	 * @param rePkgProcId the rePkgProcId to set
	 */
	public void setRePkgProcId(Long rePkgProcId) {
		this.rePkgProcId = rePkgProcId;
	}
	/**
	 * @return the rePkgNodeId
	 */
	public Long getRePkgNodeId() {
		return rePkgNodeId;
	}
	/**
	 * @param rePkgNodeId the rePkgNodeId to set
	 */
	public void setRePkgNodeId(Long rePkgNodeId) {
		this.rePkgNodeId = rePkgNodeId;
	}
}
