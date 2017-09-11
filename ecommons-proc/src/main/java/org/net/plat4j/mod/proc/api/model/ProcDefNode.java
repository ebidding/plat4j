/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-19 11:06:34
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-19 11:06:34		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author yujie
 *
 */
public class ProcDefNode implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1679422490980762908L;
	private Long id;
	private Long procNodeId;
	private Long procId;
	private String stageCode;
	private String procNodeName;
	private Boolean isRepeatable;
	private Boolean isOptional;
	private Boolean isGrouped;
	private String nodeCode;
	private ProcNode node;
	private String groupDictCode;
	private String optionalCondition;
	private String isDisplayStatus;
	private String isKeyNode;
	private List<ProcDefNodeItem> groupedNodes; 
	private List<ProcDefNodeNext> preNodes;
	private List<ProcDefNodeNext> nextNodes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	 * @return the procNodeName
	 */
	public String getProcNodeName() {
		return procNodeName;
	}
	/**
	 * @param procNodeName the procNodeName to set
	 */
	public void setProcNodeName(String procNodeName) {
		this.procNodeName = procNodeName;
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
	 * @return the isOptional
	 */
	public Boolean getIsOptional() {
		return isOptional;
	}
	/**
	 * @param isOptional the isOptional to set
	 */
	public void setIsOptional(Boolean isOptional) {
		this.isOptional = isOptional;
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
	 * @return the groupDictCode
	 */
	public String getGroupDictCode() {
		return groupDictCode;
	}
	/**
	 * @param groupDictCode the groupDictCode to set
	 */
	public void setGroupDictCode(String groupDictCode) {
		this.groupDictCode = groupDictCode;
	}
	/**
	 * @return the groupedNodes
	 */
	public List<ProcDefNodeItem> getGroupedNodes() {
		return groupedNodes;
	}
	/**
	 * @param groupedNodes the groupedNodes to set
	 */
	public void setGroupedNodes(List<ProcDefNodeItem> groupedNodes) {
		this.groupedNodes = groupedNodes;
	}
	/**
	 * @return the preNodes
	 */
	public List<ProcDefNodeNext> getPreNodes() {
		return preNodes;
	}
	/**
	 * @param preNodes the preNodes to set
	 */
	public void setPreNodes(List<ProcDefNodeNext> preNodes) {
		this.preNodes = preNodes;
	}
	/**
	 * @return the nextNodes
	 */
	public List<ProcDefNodeNext> getNextNodes() {
		return nextNodes;
	}
	/**
	 * @param nextNodes the nextNodes to set
	 */
	public void setNextNodes(List<ProcDefNodeNext> nextNodes) {
		this.nextNodes = nextNodes;
	}
	public String getOptionalCondition() {
		return optionalCondition;
	}
	public void setOptionalCondition(String optionalCondition) {
		this.optionalCondition = optionalCondition;
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
	/**
	 * @return the isKeyNode
	 */
	public String getIsKeyNode() {
		return isKeyNode;
	}
	/**
	 * @param isKeyNode the isKeyNode to set
	 */
	public void setIsKeyNode(String isKeyNode) {
		this.isKeyNode = isKeyNode;
	}
}