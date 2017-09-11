/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-06-16 19:49:08
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-06-16 19:49:08		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.projpnl;

/**
 * @author yujie
 *
 */
public class NodeStatus {
	private String status;
	
	public NodeStatus(String status) {
		this.status = status;
	}
	
	public Boolean isUninitialized() {
		return this.status == "0";
	}
	
	public Boolean isWorking() {
		return this.status == "1";
	}
	
	public Boolean isLocked() {
		return this.status == "2";
	}
	
	public Boolean isIssuable() {
		return this.status == "3";
	}
	
	public Boolean isFinal() {
		return this.status == "4";
	}
}
