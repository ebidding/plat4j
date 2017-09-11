/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-19 13:47:45
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-19 13:47:45		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;

import java.util.List;
import java.util.Map;

/**
 * @author yujie
 *
 */
public class RelatedPkgsNodeInfo {
	private List<PkgNodesInfo> pkgNodesInfos;
	/**
	 * 所有相关标段（包）的所有节点的数据，数据格式为：Map<PackageId, Map<ProcNodeId+NodeCode, NodeData>>
	 */
	private Map<Long, Map<String, NodeData>> nodesData;
	
	/**
	 * @return the pkgNodesInfos
	 */
	public List<PkgNodesInfo> getPkgNodesInfos() {
		return pkgNodesInfos;
	}
	/**
	 * @param pkgNodesInfos the pkgNodesInfos to set
	 */
	public void setPkgNodesInfos(List<PkgNodesInfo> pkgNodesInfos) {
		this.pkgNodesInfos = pkgNodesInfos;
	}
	/**
	 * 所有相关标段（包）的所有节点的数据，数据格式为：Map<packageId, Map<pkgNodeId, NodeData>
	 * @return the nodesData
	 */
	public Map<Long, Map<String, NodeData>> getNodesData() {
		return nodesData;
	}
	/**
	 * @param nodesData the nodesData to set
	 */
	public void setNodesData(Map<Long, Map<String, NodeData>> nodesData) {
		this.nodesData = nodesData;
	}
}
