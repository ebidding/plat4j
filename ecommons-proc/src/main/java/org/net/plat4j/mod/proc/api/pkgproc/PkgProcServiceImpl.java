package org.net.plat4j.mod.proc.api.pkgproc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.net.plat4j.mod.proc.api.model.BidPkgProcNode;
import org.net.plat4j.mod.proc.api.model.ProcDefNodeNext;

public class PkgProcServiceImpl {
	/**
	 * __IS_GROUPED
	 */
	public static final String IS_GROUPED = "__IS_GROUPED";
	/**
	 * __PROC_NODE_ID
	 */
	public static final String PROC_NODE_ID = "__PROC_NODE_ID";
	/**
	 * __NODE_CODE
	 */
	public static final String NODE_CODE = "__NODE_CODE";
	/**
	 * __IS_SELECTED
	 */
	public static final String IS_SELECTED = "__IS_SELECTED";
	/**
	 * __GROUP_DICT_VAL
	 */
	public static final String GROUP_DICT_VAL = "__GROUP_DICT_VAL";
	
	/**
	 * 寻找下一节点
	 * @param nextProcNodeId 下一节点id
	 * @param bidPkgProcNodeMap 
	 * @param procDefNodeNextMap
	 * @return
	 */
	public static BidPkgProcNode getNextBidPkgProcNode(Long nextProcNodeId, Map<Long, BidPkgProcNode> bidPkgProcNodeMap,
			Map<Long, ProcDefNodeNext> procDefNodeNextMap) {
		if(nextProcNodeId == null){
			return null;
		}
		BidPkgProcNode bidPkgProcNode = bidPkgProcNodeMap.get(nextProcNodeId);
		if(bidPkgProcNode != null){
			return bidPkgProcNode;
		}
		//如果下一节点不存，则从下一节点的下一节点查找
		ProcDefNodeNext procDefNext = procDefNodeNextMap.get(nextProcNodeId);
		if(procDefNext == null){
			return null;
		}else{
			return getNextBidPkgProcNode(procDefNext.getNextProcNodeId(), bidPkgProcNodeMap, procDefNodeNextMap);
		}
	}

	/**
	 * 通过procNodeId把list变成map
	 * @param nodeNextList
	 * @return 返回一个map，key为procNodeId
	 */
	public static Map<Long, ProcDefNodeNext> splitProcDefNodeNext(List<ProcDefNodeNext> nodeNextList) {
		if(nodeNextList == null || nodeNextList.size() == 0){
			return null;
		}
		Map<Long, ProcDefNodeNext> resultMap = new HashMap<>();
		for(ProcDefNodeNext procDefNodeNext : nodeNextList){
			resultMap.put(procDefNodeNext.getProcNodeId(), procDefNodeNext);
		}
		return resultMap;
	}
	/**
	 * 通过procNodeId把list变成map
	 * @param pkgProcNodeList
	 * @return 返回一个map，key为procNodeId
	 */
	public static Map<Long, BidPkgProcNode> splitBidPkgProcNode(List<BidPkgProcNode> pkgProcNodeList) {
		if(pkgProcNodeList == null || pkgProcNodeList.size() == 0){
			return null;
		}
		Map<Long, BidPkgProcNode> resultMap = new HashMap<>();
		for(BidPkgProcNode bidPkgProcNode : pkgProcNodeList){
			resultMap.put(bidPkgProcNode.getProcNodeId(), bidPkgProcNode);
		}
		return resultMap;
	}
}
