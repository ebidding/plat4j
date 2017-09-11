/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-19 10:02:54
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-19 10:02:54		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;

/**
 * @author yujie
 *
 */
public class ProcNodeOp implements Comparable<ProcNodeOp>{
	private String opCode;
	private String nodeCode;
	private String opDisplayName;
	private Long opOrder;
	private String opDesc;
	private String opActionUrl;
	private String opDisplayCondition;
	private String isBlank;

	/**
	 * @return the opId
	 */
	public String getOpCode() {
		return opCode;
	}
	/**
	 * @param opId the opId to set
	 */
	public void setOpCode(String opCode) {
		this.opCode = opCode;
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
	 * @return the opDisplayName
	 */
	public String getOpDisplayName() {
		return opDisplayName;
	}
	/**
	 * @param opDisplayName the opDisplayName to set
	 */
	public void setOpDisplayName(String opDisplayName) {
		this.opDisplayName = opDisplayName;
	}
	/**
	 * @return the opOrder
	 */
	public Long getOpOrder() {
		return opOrder;
	}
	/**
	 * @param opOrder the opOrder to set
	 */
	public void setOpOrder(Long opOrder) {
		this.opOrder = opOrder;
	}
	/**
	 * @return the opDesc
	 */
	public String getOpDesc() {
		return opDesc;
	}
	/**
	 * @param opDesc the opDesc to set
	 */
	public void setOpDesc(String opDesc) {
		this.opDesc = opDesc;
	}
	/**
	 * @return the opActionUrl
	 */
	public String getOpActionUrl() {
		return opActionUrl;
	}
	/**
	 * @param opActionUrl the opActionUrl to set
	 */
	public void setOpActionUrl(String opActionUrl) {
		this.opActionUrl = opActionUrl;
	}
	/**
	 * @return the opDisplayCondition
	 */
	public String getOpDisplayCondition() {
		return opDisplayCondition;
	}
	/**
	 * @param opDisplayCondition the opDisplayCondition to set
	 */
	public void setOpDisplayCondition(String opDisplayCondition) {
		this.opDisplayCondition = opDisplayCondition;
	}
	public String getIsBlank() {
		return isBlank;
	}
	public void setIsBlank(String isBlank) {
		this.isBlank = isBlank;
	}
	@Override
	public int compareTo(ProcNodeOp o) {
		return (int) (this.opOrder - o.opOrder);
	}
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
