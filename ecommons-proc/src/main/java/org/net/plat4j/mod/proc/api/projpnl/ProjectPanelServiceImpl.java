package org.net.plat4j.mod.proc.api.projpnl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.net.plat4j.mod.proc.api.PkgNodeStatus;
import org.net.plat4j.mod.proc.api.model.PkgProcNode;
import org.net.plat4j.mod.proc.api.model.PkgProcNodeModel;
import org.net.plat4j.mod.proc.api.model.PkgProcNodeNext;


public class ProjectPanelServiceImpl {
	
	/**
	 * 按标段（包）id拆分PkgProcNodeModel
	 * @param procNodeModels
	 * @return 返回一个map，map的key为packageId
	 */
	public static Map<Long, List<PkgProcNodeModel>> splitNodeModels(List<PkgProcNodeModel> procNodeModels) {
		Map<Long, List<PkgProcNodeModel>> map = new HashMap<>();
		for(PkgProcNodeModel model : procNodeModels){
			Long packageId = model.getPackageId();
			List<PkgProcNodeModel> modelList = map.get(packageId);
			if(modelList == null){
				modelList = new ArrayList<>();
				map.put(packageId, modelList);
			}
			modelList.add(model);
		}
		return map;
	}

	/**
	 * 按标段（包）拆id分bid_package_status
	 * @param bidPackageStatus
	 * @return 返回一个map，map的key为packageId
	 */
	/*
	private Map<Long, List<BidPackageStatusModel>> splitPackageStatus(List<BidPackageStatusModel> bidPackageStatus) {
		Map<Long, List<BidPackageStatusModel>> packageStatus = new HashMap<>();
		for(BidPackageStatusModel model : bidPackageStatus){
			Long packageId = model.getPackageId();
			List<BidPackageStatusModel> packageList = packageStatus.get(packageId);
			if(packageList == null){
				packageList = new ArrayList<>();
				packageStatus.put(packageId, packageList);
			}
			packageList.add(model);
		}
		return packageStatus;
	}
	*/
	
	/**
	 * 根据流程阶段拆分流程节点
	 * @param nodeList 所有的流程节点
	 * @return 返回一个map，map的key为stageCode
	 */
	public static Map<String,List<PkgProcNode>> splitNodeListByStage(List<PkgProcNode> nodeList){
		Map<String,List<PkgProcNode>> stageNodeMap = new HashMap<>();
		for(PkgProcNode procNode : nodeList){
			String stageCode = procNode.getStageCode();
			List<PkgProcNode> stageNodeList = stageNodeMap.get(stageCode);
			if(stageNodeList == null){
				stageNodeList = new ArrayList<>();
				stageNodeMap.put(stageCode,stageNodeList);
			}
			stageNodeList.add(procNode);
		}
		return stageNodeMap;
	}
	/**
	 * 根据id拆分流程节点，方便根据id获取流程节点
	 * @param nodeList 所有的流程节点
	 * @return 返回一个map，map的key为当前节点的id
	 */
	public static Map<Long, PkgProcNode> splitNodeListById(List<PkgProcNode> nodeList) {
		Map<Long,PkgProcNode> allNodeMap = new HashMap<>();
		for(PkgProcNode procNode : nodeList){
			allNodeMap.put(procNode.getPkgProcNodeId() , procNode);
		}
		return allNodeMap;
	}
	/**
	 * 节点排序
	 * @param stageNodeMap
	 * @return
	 */
	public static Map<String, List<PkgProcNode>> orderStageNode(Map<String, List<PkgProcNode>> stageNodeMap) {
		Map<String,List<PkgProcNode>> orderProcNodeMap = new HashMap<>();
		Iterator<Entry<String,List<PkgProcNode>>> iterator = stageNodeMap.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String,List<PkgProcNode>> entry = iterator.next();
			List<PkgProcNode> orderList = orderList(entry.getValue());
			orderProcNodeMap.put(entry.getKey(), orderList);
		}
		return orderProcNodeMap;
	}
	
	/**
	 * 节点排序
	 * @param value
	 * @return
	 */
	public static List<PkgProcNode> orderList(List<PkgProcNode> list) {
		List<PkgProcNode> orderList = new ArrayList<>();
		Map<Long,PkgProcNode> allStageNodeMap = splitNodeListById(list);
		while(list.size() > 0){
			List<PkgProcNode> tempRemoveList = new ArrayList<>();
			for(PkgProcNode procNode : list){
				if(canAdd(procNode,orderList,allStageNodeMap)){
					orderList.add(procNode);
					tempRemoveList.add(procNode);
				}
			}
			list.removeAll(tempRemoveList);
		}
		return orderList;
	}
	
	/**
	 * 判断节点是否可以插入到已排序的list中
	 * @param procNode 节点
	 * @param orderList 已排序的list
	 * @param allStageNodeMap 所有节点的map，key为节点id
	 * @return
	 */
	public static boolean canAdd(PkgProcNode procNode, List<PkgProcNode> orderList, Map<Long, PkgProcNode> allStageNodeMap) {
		List<PkgProcNodeNext> preNodes = procNode.getPreNodes();
		if(preNodes == null || preNodes.size() == 0){
			return true;
		}
		for(PkgProcNodeNext procNodeNext : preNodes){
			Long preNodeId = procNodeNext.getPkgNodeId();
			PkgProcNode preNode = allStageNodeMap.get(preNodeId);
			if(preNode == null){//如果前置节点在当前阶段不存在，可以插入
				continue;
			}else{
				if(orderList.contains(preNode)){//如果前置节点已经在已排序的列表中，可以插入
					continue;
				}else{
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 查询上一节点的最小的状态
	 * @param procNode
	 * @param allNodeMap
	 * @return
	 */
	public static String getPreNodeStatus(PkgProcNode procNode, Map<Long, PkgProcNode> allNodeMap) {
		List<PkgProcNodeNext> preNodes = procNode.getPreNodes();
		if(preNodes == null || preNodes.size() == 0){
			return null;
		}
		int status = Integer.parseInt(PkgNodeStatus.FINAL);
		for(PkgProcNodeNext nodeNext : preNodes){
			PkgProcNode preNode = allNodeMap.get(nodeNext.getPkgNodeId());
			if(preNode == null){
				continue;
			}
			int preNodeStatus = Integer.parseInt(preNode.getPkgNodeStatus());
			if(status > preNodeStatus){
				status = preNodeStatus;
			}
		}
		return String.valueOf(status);
	}
}
