/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-29 15:41:42
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-29 15:41:42		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;

import cn.com.ebidding.api.taurus.utils.dynForm2.DynFormServiceParamModel;

/**
 * @author yujie
 *
 */
public class ProcServiceParamModel extends DynFormServiceParamModel {
	private static final long serialVersionUID = 1L;

	private Long curPackageId;
	private Long curPkgProcId;
	private Long curPkgProcNodeId;
	private Long curProcNodeId;
	private String bizTableId;
	private String pkgNodeStatus;
	
	/**
	 * @return the curPackageId
	 */
	public Long getCurPackageId() {
		return curPackageId;
	}
	/**
	 * @param curPackageId the curPackageId to set
	 */
	public void setCurPackageId(Long curPackageId) {
		this.curPackageId = curPackageId;
	}
	/**
	 * @return the curPkgProcId
	 */
	public Long getCurPkgProcId() {
		return curPkgProcId;
	}
	/**
	 * @param curPkgProcId the curPkgProcId to set
	 */
	public void setCurPkgProcId(Long curPkgProcId) {
		this.curPkgProcId = curPkgProcId;
	}
	/**
	 * @return the curPkgProcNodeId
	 */
	public Long getCurPkgProcNodeId() {
		return curPkgProcNodeId;
	}
	/**
	 * @param curPkgProcNodeId the curPkgProcNodeId to set
	 */
	public void setCurPkgProcNodeId(Long curPkgProcNodeId) {
		this.curPkgProcNodeId = curPkgProcNodeId;
	}
	/**
	 * @return the curProcNodeId
	 */
	public Long getCurProcNodeId() {
		return curProcNodeId;
	}
	/**
	 * @param curProcNodeId the curProcNodeId to set
	 */
	public void setCurProcNodeId(Long curProcNodeId) {
		this.curProcNodeId = curProcNodeId;
	}
	/**
	 * @return the bizTableId
	 */
	public String getBizTableId() {
		return bizTableId;
	}
	/**
	 * @param bizTableId the bizTableId to set
	 */
	public void setBizTableId(String bizTableId) {
		this.bizTableId = bizTableId;
	}
	/**
	 * @return the pkgNodeStatus
	 */
	public String getPkgNodeStatus() {
		return pkgNodeStatus;
	}
	/**
	 * @param pkgNodeStatus the pkgNodeStatus to set
	 */
	public void setPkgNodeStatus(String pkgNodeStatus) {
		this.pkgNodeStatus = pkgNodeStatus;
	}
}
