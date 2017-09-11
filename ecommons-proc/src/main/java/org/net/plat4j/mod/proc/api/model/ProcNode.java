/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-18 20:45:51
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-18 20:45:51		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;

import java.util.List;

/**
 * @author yujie
 *
 */
public class ProcNode {
	private String nodeCode;
	private String moduleCode;
	private String nodeName;
	private String nodeDesc;
	private String nodePubSvc;
	private List<ProcNodePubField> pubFields;
	private List<ProcNodeOp> ops;

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
	 * @return the moduleCode
	 */
	public String getModuleCode() {
		return moduleCode;
	}
	/**
	 * @param moduleCode the moduleCode to set
	 */
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	/**
	 * @return the nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}
	/**
	 * @param nodeName the nodeName to set
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	/**
	 * @return the nodeDesc
	 */
	public String getNodeDesc() {
		return nodeDesc;
	}
	/**
	 * @param nodeDesc the nodeDesc to set
	 */
	public void setNodeDesc(String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}
	/**
	 * @return the nodePubSvc
	 */
	public String getNodePubSvc() {
		return nodePubSvc;
	}
	/**
	 * @param nodePubSvc the nodePubSvc to set
	 */
	public void setNodePubSvc(String nodePubSvc) {
		this.nodePubSvc = nodePubSvc;
	}
	/**
	 * @return the pubFields
	 */
	public List<ProcNodePubField> getPubFields() {
		return pubFields;
	}
	/**
	 * @param pubFields the pubFields to set
	 */
	public void setPubFields(List<ProcNodePubField> pubFields) {
		this.pubFields = pubFields;
	}
	/**
	 * @return the ops
	 */
	public List<ProcNodeOp> getOps() {
		return ops;
	}
	/**
	 * @param ops the ops to set
	 */
	public void setOps(List<ProcNodeOp> ops) {
		this.ops = ops;
	}
}
