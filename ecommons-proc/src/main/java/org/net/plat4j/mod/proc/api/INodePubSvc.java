/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-20 13:34:49
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-20 13:34:49		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api;

import java.util.List;

import org.net.plat4j.mod.proc.api.model.NodeData;
import org.net.plat4j.mod.proc.api.model.PackageContext;


/**
 * @author yujie
 *
 *	节点公共信息需要实现的接口，项目流程框架需要通过此接口
 *	创建节点数据、获取节点数据和发布节点数据。
 */
public interface INodePubSvc {
	/**
	 * 获取节点初始数据
	 * 
	 * @param nodeCode 当前节点代码
	 * @param packageId
	 * @return
	 */
	NodeData getNodeData(String nodeCode, Long packageId);
	/**
	 * 发布节点数据并使其生效
	 * 
	 * @param nodeCode 当前节点代码
	 * @param nodeData
	 * @return 操作成功返回 true，否则返回 false。
	 */
	Boolean issueNodeData(String nodeCode, List<NodeData> nodeDatas);
	/**
	 * 创建节点业务表记录并返回业务表编号
	 * 
	 * @param nodeCode 当前节点代码
	 * @param pkgCxt
	 * @return
	 */
	String createNodeBizInstance(String nodeCode, PackageContext pkgCxt);
}
