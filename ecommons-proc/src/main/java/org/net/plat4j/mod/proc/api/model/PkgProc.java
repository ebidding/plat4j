/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-18 20:37:29
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-18 20:37:29		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;

import java.util.List;

/**
 * @author yujie
 *
 * 标段（包）流程信息。
 */
public class PkgProc {
	private Long pkgProcId;
	private Long packageId;
	private Long procId;
	private Long newProcId;//招标转采购后新的流程节点信息
	private String pkgProcStatus;
//	private Long curProcNodeId;
//	private Long curPkgProcNodeId;
	private List<PkgProcNode> allNodes;
	
	/**
	 * @return the pkgProcId
	 */
	public Long getPkgProcId() {
		return pkgProcId;
	}
	/**
	 * @param pkgProcId the pkgProcId to set
	 */
	public void setPkgProcId(Long pkgProcId) {
		this.pkgProcId = pkgProcId;
	}
	/**
	 * @return the packageId
	 */
	public Long getPackageId() {
		return packageId;
	}
	/**
	 * @param packageId the packageId to set
	 */
	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}
	/**
	 * @return the procId
	 */
	public Long getProcId() {
		return procId;
	}
	/**
	 * @param procId the procId to set
	 */
	public void setProcId(Long procId) {
		this.procId = procId;
	}
	/**
	 * @return the pkgProcStatus
	 */
	public String getPkgProcStatus() {
		return pkgProcStatus;
	}
	/**
	 * @param pkgProcStatus the pkgProcStatus to set
	 */
	public void setPkgProcStatus(String pkgProcStatus) {
		this.pkgProcStatus = pkgProcStatus;
	}
//	/**
//	 * @return the curProcNodeId
//	 */
//	public Long getCurProcNodeId() {
//		return curProcNodeId;
//	}
//	/**
//	 * @param curProcNodeId the curProcNodeId to set
//	 */
//	public void setCurProcNodeId(Long curProcNodeId) {
//		this.curProcNodeId = curProcNodeId;
//	}
//	/**
//	 * @return the curPkgProcNodeId
//	 */
//	public Long getCurPkgProcNodeId() {
//		return curPkgProcNodeId;
//	}
//	/**
//	 * @param curPkgProcNodeId the curPkgProcNodeId to set
//	 */
//	public void setCurPkgProcNodeId(Long curPkgProcNodeId) {
//		this.curPkgProcNodeId = curPkgProcNodeId;
//	}
	/**
	 * @return the allNodes
	 */
	public List<PkgProcNode> getAllNodes() {
		return allNodes;
	}
	/**
	 * @param allNodes the allNodes to set
	 */
	public void setAllNodes(List<PkgProcNode> allNodes) {
		this.allNodes = allNodes;
	}
	public Long getNewProcId() {
		return newProcId;
	}
	public void setNewProcId(Long newProcId) {
		this.newProcId = newProcId;
	}
}
