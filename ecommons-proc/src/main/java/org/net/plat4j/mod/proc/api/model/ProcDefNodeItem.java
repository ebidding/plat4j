/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-19 12:00:56
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-19 12:00:56		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;

/**
 * @author yujie
 *
 */
public class ProcDefNodeItem {
	private Long procDefNodeItemId;
	private Long procId;
	private Long procNodeId;
	private String nodeCode;
	private ProcNode node;
	private Long dispOrder;
	private String groupDictVal;
	
	/**
	 * @return the procDefNodeItemId
	 */
	public Long getProcDefNodeItemId() {
		return procDefNodeItemId;
	}
	/**
	 * @param procDefNodeItemId the procDefNodeItemId to set
	 */
	public void setProcDefNodeItemId(Long procDefNodeItemId) {
		this.procDefNodeItemId = procDefNodeItemId;
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
	 * @return the node
	 */
	public ProcNode getNode() {
		return node;
	}
	/**
	 * @param node the node to set
	 */
	public void setNode(ProcNode node) {
		this.node = node;
	}
	/**
	 * @return the dispOrder
	 */
	public Long getDispOrder() {
		return dispOrder;
	}
	/**
	 * @param dispOrder the dispOrder to set
	 */
	public void setDispOrder(Long dispOrder) {
		this.dispOrder = dispOrder;
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
}
