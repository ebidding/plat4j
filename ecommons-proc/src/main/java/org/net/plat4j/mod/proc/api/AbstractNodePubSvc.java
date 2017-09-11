/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-25 13:44:03
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-25 13:44:03		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api;

import java.util.List;

import org.net.plat4j.mod.proc.api.model.NodeData;
import org.net.plat4j.mod.proc.api.model.PackageContext;


/**
 * @author yujie
 *
 */
public class AbstractNodePubSvc implements INodePubSvc {
	/* (non-Javadoc)
	 * @see cn.com.ebidding.mod.proc.api.INodePubSvc#issueNodeData(cn.com.ebidding.mod.proc.api.model.NodeData)
	 */
	@Override
	public Boolean issueNodeData(String nodeCode, List<NodeData> nodeData) {
		return false;
	}

	/* (non-Javadoc)
	 * @see cn.com.ebidding.mod.proc.api.INodePubSvc#getNodeData(java.lang.Long)
	 */
	@Override
	public NodeData getNodeData(String nodeCode, Long packageId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.com.ebidding.mod.proc.api.INodePubSvc#createNodeBizInstance(cn.com.ebidding.mod.proc.api.model.PackageContext)
	 */
	@Override
	public String createNodeBizInstance(String nodeCode, PackageContext pkgCxt) {
		return null;
	}

}
