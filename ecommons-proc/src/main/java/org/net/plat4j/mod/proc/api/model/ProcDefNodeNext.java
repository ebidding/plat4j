/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-19 13:38:21
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-19 13:38:21		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;

import java.io.Serializable;

/**
 * @author yujie
 *
 */
public class ProcDefNodeNext implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9130535810342016577L;
	private Long procNodeId;
	private Long nextProcNodeId;
	private Boolean isRequiredForNext;
	
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
	 * @return the nextProcNodeId
	 */
	public Long getNextProcNodeId() {
		return nextProcNodeId;
	}
	/**
	 * @param nextProcNodeId the nextProcNodeId to set
	 */
	public void setNextProcNodeId(Long nextProcNodeId) {
		this.nextProcNodeId = nextProcNodeId;
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
