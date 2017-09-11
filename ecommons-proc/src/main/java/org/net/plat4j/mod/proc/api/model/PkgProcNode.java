/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-18 20:36:02
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-18 20:36:02		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;

import java.util.List;

/**
 * @author yujie
 *
 */
public class PkgProcNode {
	private Long pkgProcNodeId;
	private Long pkgProcId;
	private Long packageId;
	private Long procNodeId;
	private ProcDefNode procNodeDef;
	private String stageCode;
	private String nodeCode;
	private String pkgNodeName;
	private Boolean isRepeatable;
	private Boolean isGrouped;
	private String groupDictVal;
	private String pkgNodeStatus;
	private String bizTableId;
	private List<PkgProcNodeNext> preNodes;
	private List<PkgProcNodeNext> nextNodes;
	
	/**
	 * @return the pkgProcNodeId
	 */
	public Long getPkgProcNodeId() {
		return pkgProcNodeId;
	}
	/**
	 * @param pkgProcNodeId the pkgProcNodeId to set
	 */
	public void setPkgProcNodeId(Long pkgProcNodeId) {
		this.pkgProcNodeId = pkgProcNodeId;
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
	 * @return the procNodeDef
	 */
	public ProcDefNode getProcNodeDef() {
		return procNodeDef;
	}
	/**
	 * @param procNodeDef the procNodeDef to set
	 */
	public void setProcNodeDef(ProcDefNode procNodeDef) {
		this.procNodeDef = procNodeDef;
	}
	/**
	 * @return the stageCode
	 */
	public String getStageCode() {
		return stageCode;
	}
	/**
	 * @param stageCode the stageCode to set
	 */
	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
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
	 * @return the pkgNodeName
	 */
	public String getPkgNodeName() {
		return pkgNodeName;
	}
	/**
	 * @param pkgNodeName the pkgNodeName to set
	 */
	public void setPkgNodeName(String pkgNodeName) {
		this.pkgNodeName = pkgNodeName;
	}
	/**
	 * @return the isRepeatable
	 */
	public Boolean getIsRepeatable() {
		return isRepeatable;
	}
	/**
	 * @param isRepeatable the isRepeatable to set
	 */
	public void setIsRepeatable(Boolean isRepeatable) {
		this.isRepeatable = isRepeatable;
	}
	/**
	 * @return the isGrouped
	 */
	public Boolean getIsGrouped() {
		return isGrouped;
	}
	/**
	 * @param isGrouped the isGrouped to set
	 */
	public void setIsGrouped(Boolean isGrouped) {
		this.isGrouped = isGrouped;
	}
	/**
	 * @return the groupDictVal
	 */
	public String getGroupDictVal() {
		return groupDictVal;
	}
	/**
	 * @param groupDictVal the groupDictVal to set
	 */
	public void setGroupDictVal(String groupDictVal) {
		this.groupDictVal = groupDictVal;
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
	 * @return the preNodes
	 */
	public List<PkgProcNodeNext> getPreNodes() {
		return preNodes;
	}
	/**
	 * @param preNodes the preNodes to set
	 */
	public void setPreNodes(List<PkgProcNodeNext> preNodes) {
		this.preNodes = preNodes;
	}
	/**
	 * @return the nextNodes
	 */
	public List<PkgProcNodeNext> getNextNodes() {
		return nextNodes;
	}
	/**
	 * @param nextNodes the nextNodes to set
	 */
	public void setNextNodes(List<PkgProcNodeNext> nextNodes) {
		this.nextNodes = nextNodes;
	}
}
