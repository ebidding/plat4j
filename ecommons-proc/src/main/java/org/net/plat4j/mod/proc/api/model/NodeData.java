/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-19 13:42:50
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-19 13:42:50		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;

import java.util.Date;

/**
 * @author yujie
 *
 */
public class NodeData {
	private Long nodeDataId;
	private Long pkgProcId;
	private Long packageId;
	private Long curProcNodeId;
	private Long curPkgNodeId;
	private Long pkgNodeId;
	private Long procNodeId;
	private String nodeCode;
	private String dataVal;
	private Long createUserId;
	private Date createTime;
	
	/**
	 * @return the nodeDataId
	 */
	public Long getNodeDataId() {
		return nodeDataId;
	}
	/**
	 * @param nodeDataId the nodeDataId to set
	 */
	public void setNodeDataId(Long nodeDataId) {
		this.nodeDataId = nodeDataId;
	}
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
	 * @return the procNodeId
	 */
	public Long getProcNodeId() {
		return procNodeId;
	}
	/**
	 * @param procNodeId the procNodeId to set
	 */
	public void setProcNodeId(Long procNodeId) {
		this.procNodeId = procNodeId;
	}
	/**
	 * @return the nodeCode
	 */
	public String getNodeCode() {
		return nodeCode;
	}
	/**
	 * @param nodeCode the nodeCode to set
	 */
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	/**
	 * @return the dataVal
	 */
	public String getDataVal() {
		return dataVal;
	}
	/**
	 * @param dataVal the dataVal to set
	 */
	public void setDataVal(String dataVal) {
		this.dataVal = dataVal;
	}
	/**
	 * @return the createUserId
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
