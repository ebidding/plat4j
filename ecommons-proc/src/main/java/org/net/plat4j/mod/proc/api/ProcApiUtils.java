/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-20 17:12:56
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-20 17:12:56		yujie			Create file
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
import net.plat4j.core.spring.BeanFactory;

/**
 * @author yujie
 *
 */
public class ProcApiUtils {
	private static IProcApi getApiImpl() {
		return BeanFactory.getBean("IProcApi");
	}

	/**
	 * 获取标段（包）流程定义数据
	 * 
	 * @param packageId 标段（包）编号
	 * @return
	 */
	public static PkgProcDef getPkgProcDef(Long packageId) {
		return getApiImpl().getPkgProcDef(packageId);
	}

	/**
	 * 
	 * 
	 * @param packageId
	 * @return
	 */
	public static PkgProc getPkgProc(Long packageId) {
		return getApiImpl().getPkgProc(packageId);
	}

	public static Boolean savePkgProc(PkgProc pkgProc, List<NodeData> nodesData) {
		return getApiImpl().savePkgProc(pkgProc, nodesData);
	}

	public static PkgNodesInfo getPkgNodesInfo(Long packageId) {
		return getApiImpl().getPkgNodesInfo(packageId);
	}

	public static Boolean savePkgNodesData(ProcServiceParamModel spModel) {
		return getApiImpl().savePkgNodesData(spModel);
	}
	
	public static Boolean updatePkgNodeStatus(Long pkgNodeId, String bizTableId, String pkgNodeStatus) {
		return getApiImpl().updatePkgNodeStatus(pkgNodeId, bizTableId, pkgNodeStatus);
	}

	public static Boolean issueRelatedNodesData(ProcServiceParamModel spModel) {
		return getApiImpl().issueRelatedNodesData(spModel);
	}
	/**
	 * 获取流程的定义
	 * @param agentId
	 * @return
	 */
	public static List<ProcDef> getProcDef(Long agentId){
		return getApiImpl().getProcDef(agentId);
	}
	/**
	 * 保存流程节点信息
	 * @param bidPackage
	 */
	public static void saveBidPkgProc(BidPackage bidPackage){
		getApiImpl().saveBidPkgProc(bidPackage);
	}

	/**
	 * @param procId
	 * @return
	 */
	public static List<ProcDefNode> getProcDefNode(Long procId) {
		return getApiImpl().getProcDefNode(procId);
	}

	/**
	 * @param procId
	 * @return
	 */
	public static List<ProcDefNodeNext> getProcDefNodeNext(Long procId) {
		return getApiImpl().getProcDefNodeNext(procId);
	}
	/**
	 * 通过bizTableId和nodeCode获取packageId
	 * @param bizTableId
	 * @param nodeCode
	 * @return
	 */
	public static List<Long> getPackageIds(String bizTableId , String nodeCode){
		return getApiImpl().getPackageIds(bizTableId, nodeCode);
	}
	/**
	 * 通过packageId和nodeCode查询bizTableId
	 * @param packageId
	 * @param nodeCodes
	 * @return
	 */
	public static List<BidPkgProcNodeBizInfo> getBizTableId(Long packageId, String... nodeCodes){
		return getApiImpl().getBizTableId(packageId, nodeCodes);
	}

	/**
	 * 获取流程值集与标段（包）的对应关系
	 * @return
	 */
	public static List<Map<String, Object>> getNodeDictPkgField() {
		return getApiImpl().getNodeDictPkgField();
	}

	/**
	 * 获取cfg_pkg_proc_node_items中的所有数据
	 * @return
	 */
	public static List<Map<String, Object>> getProcDefNodeItems() {
		return getApiImpl().getProcDefNodeItems();
	}
	
	/**
	 * 获取标段（包）所有的节点code
	 * @param packageId
	 * @return 标段（包）所有节点code，如果有多个，以逗号（,）分隔
	 */
	public static String getPkgNodeCodes(Long packageId){
		return getApiImpl().getPkgNodeCodes(packageId);
	}
	
	/**
	 * 
	 * @param packageId
	 * @param bizTableId
	 * @param statusCode
	 * @param statusProgress
	 */
	public static void saveNodeStatusInfo(Long packageId,String bizTableId,String nodeCode,String statusProgress){
		getApiImpl().saveNodeStatusInfo(packageId, bizTableId, nodeCode,  statusProgress);
	}
	/**
	 * 改变流程节点信息 多标段（包）联合操作
	 * @param packageIds
	 * @param bizTableId
	 * @param statusCode
	 * @param statusValue
	 * @param statusProgress
	 */
	public static void saveNodeStatusInfo(List<Long> packageIds,String bizTableId,String nodeCode,String statusProgress){
		getApiImpl().deleteBizTables(packageIds, bizTableId,nodeCode);
		getApiImpl().saveNodeStatusInfo(packageIds, bizTableId, nodeCode, statusProgress);
	}
	/**
	 * 获取节点的状态
	 * @param packageId 标段（包）id
	 * @param nodeCode 节点编码
	 * @return 返回节点的状态，0 - 未创建（Uninitialized），1 - 编辑中（Working），2 - 被锁定（Locked），3 - 可发布（Issuable），4 - 已结束（Final）
	 */
	public static String getNodeStatus(Long packageId, String nodeCode){
		return getApiImpl().getNodeStatus(packageId, nodeCode);
	}
	private static PkgNodeStatus getStatus(Long packageId, String nodeCode){
		String nodeStatus = getNodeStatus(packageId, nodeCode);
		PkgNodeStatus status = new PkgNodeStatus(nodeStatus);
		return status;
	}
	/**
	 * 判断此节点是不是未创建状态
	 * @param packageId 标段（包）id
	 * @param nodeCode 节点编码
	 * @return 
	 */
	public static Boolean isNodeUninitialized(Long packageId, String nodeCode){
		return getStatus(packageId, nodeCode).isUninitialized();
	}
	/**
	 * 判断此节点是不是编辑中的状态
	 * @param packageId 标段（包）id
	 * @param nodeCode 节点编码
	 * @return 
	 */
	public static Boolean isNodeWorking(Long packageId, String nodeCode){
		return getStatus(packageId, nodeCode).isWorking();
	}
	/**
	 * 判断此节点是不是被锁定的状态
	 * @param packageId 标段（包）id
	 * @param nodeCode 节点编码
	 * @return 
	 */
	public static Boolean isNodeLocked(Long packageId, String nodeCode){
		return getStatus(packageId, nodeCode).isLocked();
	}
	/**
	 * 判断此节点是不是可发布的状态
	 * @param packageId 标段（包）id
	 * @param nodeCode 节点编码
	 * @return 
	 */
	public static Boolean isNodeIssuable(Long packageId, String nodeCode){
		return getStatus(packageId, nodeCode).isIssuable();
	}
	/**
	 * 判断此节点是不是已结束的状态
	 * @param packageId 标段（包）id
	 * @param nodeCode 节点编码
	 * @return 
	 */
	public static Boolean isNodeFinal(Long packageId, String nodeCode){
		return getStatus(packageId, nodeCode).isFinal();
	}

	/**
	 * @return
	 */
	public static List<Map<String, Object>> getProcInfos() {
		return getApiImpl().getProcInfos();
	}
	/**
	 * 通过当前流程节点 和目标packageId 找到同一流程下的bizTableId
	 */
	public static BidPkgProcNodeBizInfo getBizTableIdByDiff(Long packageId, Long curPkgProcNodeId){
		return getApiImpl().getPkgProcDef( packageId,  curPkgProcNodeId); 
	}
	/**
	 * 企业采购招标转采购改变流程节点实现方法
	 * @param packageId 标段id
	 * @param purchaseMode 所需要转的企采项目类型
	 */
	public static void updatePkgNodeInfo(Long packageId,String purchaseMode){
		getApiImpl().updatePkgNodeInfo(packageId, purchaseMode); 
	}
	
	/**
	 * 流程过程中，插入流程节点前后顺序
	 * @param packageId
	 * @param nodeCode
	 * @param nextNodeCode
	 */
	public static void insertBidPkgProcNodeNext(Long packageId, String nodeCode, String nextNodeCode){
		getApiImpl().insertBidPkgProcNodeNext(packageId, nodeCode, nextNodeCode);
	}
	/**
	 * 流程过程中，删除流程节点前后关系
	 * @param packageId
	 * @param nodeCode
	 * @param nextNodeCode
	 */
	public static void deleteBidPkgProcNodeNext(Long packageId, String nodeCode, String nextNodeCode){
		getApiImpl().deleteBidPkgProcNodeNext(packageId, nodeCode, nextNodeCode);
	}
	/**
	 * 将当前标段进度分包ID、状态编号、状态参数、当前进度状态  表名<bidPackageStatus><br>
	 * @param packageId 分包ID<br>
	 * @param statusCode 状态编号<br>
	 * @param statusValue 状态参数<br>
	 * @param STATUS_PROGRESS_INPROGRESS 当前进度状态
	 */
	public static void saveBidPackageStatusInfo(String packageId,String statusCode,String statusValue,int STATUS_PROGRESS){
		getApiImpl().saveBidPackageStatusInfo(packageId, statusCode, statusValue,STATUS_PROGRESS);
	}
	/**
	 * 将当前标段进度分包ID、状态编号、状态参数、当前进度状态、当前状态是否是结束  表名<bidPackageStatus><br>
	 * @param packageId 分包ID<br>
	 * @param statusCode 状态编号<br>
	 * @param statusValue 状态参数<br>
	 * @param STATUS_PROGRESS 当前进度状态
	 * @param statusIsEnd 当前状态是否是结束状态
	 */
	public static void saveBidPackageStatusInfo(String packageId,String statusCode,String statusValue,int STATUS_PROGRESS,boolean statusIsEnd){
		getApiImpl().saveBidPackageStatusInfo(packageId, statusCode, statusValue,STATUS_PROGRESS,statusIsEnd);
	}
	/**
	 * 删除当前状态 表名<bidPackageStatus><br>
	 * @param packageId 分包ID<br>
	 * @param statusCode 状态编号<br>
	 */
	public static void deletedBidPackageStatusInfo(String packageId,String statusCode){
		getApiImpl().deletedBidPackageStatusInfo(packageId, statusCode);
	}
	
	
}
