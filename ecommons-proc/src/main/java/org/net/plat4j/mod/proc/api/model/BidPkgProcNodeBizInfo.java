/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2015年8月5日 下午1:54:08
	
	Revision History:
	Version     Date              					Author			Comments
	1.0      2015年8月5日下午1:54:08		    		chenyj			Create file
=========================================================================
*/
package org.net.plat4j.mod.proc.api.model;

/**
 * @author chenyj
 *
 */
public class BidPkgProcNodeBizInfo {
	private Long pkgProcNodeId;
	private String nodeCode;
	private String nodeName;
	private String bizTableId;
	public String getNodeCode() {
		return nodeCode;
	}
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getBizTableId() {
		return bizTableId;
	}
	public void setBizTableId(String bizTableId) {
		this.bizTableId = bizTableId;
	}
	public Long getPkgProcNodeId() {
		return pkgProcNodeId;
	}
	public void setPkgProcNodeId(Long pkgProcNodeId) {
		this.pkgProcNodeId = pkgProcNodeId;
	}
	
}
