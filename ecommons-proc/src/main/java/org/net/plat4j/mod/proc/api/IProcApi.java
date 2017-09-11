/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-18 17:25:22
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-18 17:25:22		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api;

import java.util.List;
import java.util.Map;

import org.net.plat4j.mod.proc.api.model.BidPkgProcNodeBizInfo;
import org.net.plat4j.mod.proc.api.model.NodeData;
import org.net.plat4j.mod.proc.api.model.PkgNodesInfo;
import org.net.plat4j.mod.proc.api.model.PkgProc;
import org.net.plat4j.mod.proc.api.model.PkgProcDef;
import org.net.plat4j.mod.proc.api.model.ProcDef;
import org.net.plat4j.mod.proc.api.model.ProcDefNode;
import org.net.plat4j.mod.proc.api.model.ProcDefNodeNext;
import org.net.plat4j.mod.proc.api.model.ProcServiceParamModel;

import cn.com.ebidding.web.model.BidPackage;

/**
 * @author yujie
 *
 */
public interface IProcApi {
	PkgProcDef getPkgProcDef(Long packageId);
	PkgProc getPkgProc(Long packageId);
	Boolean savePkgProc(PkgProc pkgProc, List<NodeData> nodesData);
	PkgNodesInfo getPkgNodesInfo(Long packageId);
	Boolean savePkgNodesData(ProcServiceParamModel spModel);
	Boolean updatePkgNodeStatus(Long pkgNodeId, String bizTableId, String pkgNodeStatus);
	Boolean issueRelatedNodesData(ProcServiceParamModel spModel);
	public List<ProcDef> getProcDef(Long agentId);
	
	/**
	 * @param procId
	 * @return
	 */
	List<ProcDefNode> getProcDefNode(Long procId);
	/**
	 * @param procId
	 * @return
	 */
	List<ProcDefNodeNext> getProcDefNodeNext(Long procId);
	/**
	 * @param bizTableId
	 * @param nodeCode
	 * @return
	 */
	List<Long> getPackageIds(String bizTableId, String nodeCode);
	/**
	 * @param bidPackage
	 */
	public void saveBidPkgProc(BidPackage bidPackage);
	/**
	 * @param packageId
	 * @param nodeCodes
	 * @return
	 */
	public List<BidPkgProcNodeBizInfo> getBizTableId(Long packageId, String... nodeCodes);
	/**
	 * 获取流程值集与标段（包）的对应关系
	 * @return
	 */
	public List<Map<String, Object>> getNodeDictPkgField();
	/**
	 * 获取cfg_pkg_proc_node_items中的所有数据
	 * @return
	 */
	public List<Map<String, Object>> getProcDefNodeItems();
	/**
	 * @param packageId
	 * @return
	 */
	public String getPkgNodeCodes(Long packageId);
	
	/**
	 * 
	 * @param packageId
	 * @param bizTableId
	 * @param nodeCode
	 * @param statusProgress
	 */
	public void saveNodeStatusInfo(Long packageId,String bizTableId,String nodeCode,String statusProgress);
	
	/**
	 * 
	 * @param packageIds
	 * @param bizTableId
	 * @param nodeCode
	 * @param statusProgress
	 */
	public void saveNodeStatusInfo(List<Long> packageIds,String bizTableId,String nodeCode,String statusProgress);
	/**
	 * 获取节点的状态
	 * @param packageId 标段（包）id
	 * @param nodeCode 节点编码
	 */
	public String getNodeStatus(Long packageId, String nodeCode);
	/**
	 * @return
	 */
	public List<Map<String, Object>> getProcInfos();
	/**
	 * 通过当前流程节点 和目标packageId 找到同一流程下的bizTableId
	 */
	public BidPkgProcNodeBizInfo getPkgProcDef(Long packageId, Long curPkgProcNodeId);
    /**
     * 将bizTableId、nodeCode下 非packageIds里面的数据的流程数据(bizTableId、pkgNodeStatus)清空
     * @param packageIds
     * @param bizTableId
     * @param nodeCode
     */
	void deleteBizTables(List<Long> packageIds, String bizTableId,
			String nodeCode);
	/**
	 * 
	 * @param packageId
	 * @param purchaseMode
	 */
	public void updatePkgNodeInfo(Long packageId, String purchaseMode);
	
	public void insertBidPkgProcNodeNext(Long packageId, String nodeCode, String nextNodeCode);
	
	public void deleteBidPkgProcNodeNext(Long packageId, String nodeCode, String nextNodeCode);
	
	/**
	 * 将当前标段进度分包ID、状态编号、状态参数、当前进度状态  表名<bidPackageStatus><br>
	 * @param packageId 分包ID<br>
	 * @param statusCode 状态编号<br>
	 * @param statusValue 状态参数<br>
	 * @param STATUS_PROGRESS_INPROGRESS 当前进度状态
	 */
	void saveBidPackageStatusInfo(String packageId,String statusCode,String statusValue,int STATUS_PROGRESS);
	
	/**
	 * 将当前标段进度分包ID、状态编号、状态参数、当前进度状态、当前状态是否是结束  表名<bidPackageStatus><br>
	 * @param packageId 分包ID<br>
	 * @param statusCode 状态编号<br>
	 * @param statusValue 状态参数<br>
	 * @param STATUS_PROGRESS 当前进度状态
	 * @param statusIsEnd 当前状态是否是结束状态
	 */
	void saveBidPackageStatusInfo(String packageId,String statusCode,String statusValue,int STATUS_PROGRESS,boolean statusIsEnd);
	/**
	 * 删除当前状态 表名<bidPackageStatus><br>
	 * @param packageId 分包ID<br>
	 * @param statusCode 状态编号<br>
	 */
	void deletedBidPackageStatusInfo(String packageId,String statusCode);
}
