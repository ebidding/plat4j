/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-19 11:03:58
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-19 11:03:58		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;

import java.util.List;

/**
 * @author yujie
 *
 */
public class ProcDef {
	private Long procId;
	private Long agentId;
	private String procName;
	private String procDesc;
	private String procCode;
	private String procVersion;
	private String procStatus;
	private List<ProcDefNode> nodes;
	
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
	 * @return the agentId
	 */
	public Long getAgentId() {
		return agentId;
	}
	/**
	 * @param agentId the agentId to set
	 */
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	/**
	 * @return the procName
	 */
	public String getProcName() {
		return procName;
	}
	/**
	 * @param procName the procName to set
	 */
	public void setProcName(String procName) {
		this.procName = procName;
	}
	/**
	 * @return the procDesc
	 */
	public String getProcDesc() {
		return procDesc;
	}
	/**
	 * @param procDesc the procDesc to set
	 */
	public void setProcDesc(String procDesc) {
		this.procDesc = procDesc;
	}
	/**
	 * @return the procCode
	 */
	public String getProcCode() {
		return procCode;
	}
	/**
	 * @param procCode the procCode to set
	 */
	public void setProcCode(String procCode) {
		this.procCode = procCode;
	}
	/**
	 * @return the procVersion
	 */
	public String getProcVersion() {
		return procVersion;
	}
	/**
	 * @param procVersion the procVersion to set
	 */
	public void setProcVersion(String procVersion) {
		this.procVersion = procVersion;
	}
	/**
	 * @return the procStatus
	 */
	public String getProcStatus() {
		return procStatus;
	}
	/**
	 * @param procStatus the procStatus to set
	 */
	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
	}
	/**
	 * @return the nodes
	 */
	public List<ProcDefNode> getNodes() {
		return nodes;
	}
	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(List<ProcDefNode> nodes) {
		this.nodes = nodes;
	}
}
