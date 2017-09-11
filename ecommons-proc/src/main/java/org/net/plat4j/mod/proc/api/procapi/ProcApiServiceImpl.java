package org.net.plat4j.mod.proc.api.procapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.net.plat4j.mod.proc.api.INodePubSvc;
import org.net.plat4j.mod.proc.api.model.BidPkgProcNode;
import org.net.plat4j.mod.proc.api.model.NodeData;
import org.net.plat4j.mod.proc.api.model.ProcDefNodeNext;
import org.net.plat4j.mod.proc.api.model.ProcServiceParamModel;
import org.net.plat4j.sr.core.base.BaseException;
import org.net.plat4j.sr.core.base.SpecialFields;
import org.net.plat4j.sr.core.base.ValueObject;

import net.plat4j.core.spring.BeanFactory;
import net.sf.json.JSONObject;

/**
 * <h1>.</h1><BR>
 * 
 * @author yujie - 2015-05-20 11:49:48
 */

public class ProcApiServiceImpl{
	
	public static INodePubSvc getNodePubSvc(String nodePubSvcBeanName) {
		INodePubSvc nodePubSvc = BeanFactory.getBean(nodePubSvcBeanName);
		if (nodePubSvc == null)
			throw new BaseException("No implementation");
		return nodePubSvc;
	}

	
	public static final Pattern PATTERN_NODE_KEY = Pattern.compile("(\\d*)\\+(.*)");

	public static Map<String, List<NodeData>> parseNodesDataFromSpModel(ProcServiceParamModel spModel,
			List<String> nodePackageIds, Map<String, String> pkgProcIdMap, Map<String, String> pkgCurNodeIdMap) {
		Map<String, List<NodeData>> nodesData = new HashMap<>();
		Long curProcNodeId = spModel.getCurProcNodeId();
		SpecialFields pn = spModel.getEbsSpecialMaps().get("pn");
		SpecialFields pnd = spModel.getEbsSpecialMaps().get("pnd");

		Map<String, Map<String, Map<String, ValueObject>>> nodesMap = pnd.getSpecialFieldsMap();
		// 遍历节点数据
		for (String nodeKey : nodesMap.keySet()) {
			Matcher matcher = PATTERN_NODE_KEY.matcher(nodeKey);
			if (!matcher.find() || matcher.groupCount() < 2)
				throw new BaseException("Invalid nodeKey value: [" + nodeKey + "]");
			String procNodeIdStr = matcher.group(1);
			String nodeCode = matcher.group(2);
			Long procNodeId = Long.valueOf(procNodeIdStr);
			Map<String, String> pkgNodeIdMap = new HashMap<>(); // packageId ->
																// pkgNodeId 映射
			List<ValueObject> pkgNodeIdList = pn.getFieldList(nodeKey, "__PKG_NODE_ID");
			for (ValueObject pkgNodeIdObj : pkgNodeIdList) {
				pkgNodeIdMap.put(pkgNodeIdObj.getValue(), pkgNodeIdObj.getAttr());
			}

			Map<String, JSONObject> pkgNodeData = new HashMap<>();
			Map<String, List<ValueObject>> nodeFieldsValues = pnd.getListFieldsByGroup(nodeKey);
			// 遍历节点字段
			for (String fieldCode : nodeFieldsValues.keySet()) {
				List<ValueObject> fieldValues = nodeFieldsValues.get(fieldCode);
				String defaultValue = null;

				List<String> defaultPackageIds = new ArrayList<>(nodePackageIds);
				for (ValueObject fieldValue : fieldValues) {
					String attr = fieldValue.getAttr();
					String val = fieldValue.getValue();
					if (StringUtils.isEmpty(attr)) {
						defaultValue = fieldValue.getValue();
						continue;
					}
					String[] pkgIdsStr = attr.split(",");
					for (String pkgId : pkgIdsStr) {
						defaultPackageIds.remove(pkgId);

						JSONObject valObj = pkgNodeData.get(pkgId);
						if (valObj == null) {
							valObj = new JSONObject();
							pkgNodeData.put(pkgId, valObj);
						}
						valObj.put(fieldCode, val);
					}
				}

				if (defaultPackageIds.size() > 0) {
					for (String pkgId : defaultPackageIds) {
						JSONObject valObj = pkgNodeData.get(pkgId);
						if (valObj == null) {
							valObj = new JSONObject();
							pkgNodeData.put(pkgId, valObj);
						}
						valObj.put(fieldCode, defaultValue);
					}
				}
			}
			List<NodeData> nodeDatas = nodesData.get(nodeCode);
			if (nodeDatas == null) {
				nodeDatas = new ArrayList<>();
				nodesData.put(nodeCode, nodeDatas);
			}
			for (Map.Entry<String, JSONObject> e : pkgNodeData.entrySet()) {
				String pkgIdStr = e.getKey();
				NodeData nd = new NodeData();
				nd.setPackageId(Long.valueOf(pkgIdStr));
				nd.setPkgProcId(Long.valueOf(pkgProcIdMap.get(pkgIdStr)));
				nd.setCurProcNodeId(curProcNodeId);
				nd.setCurPkgNodeId(Long.valueOf(pkgCurNodeIdMap.get(pkgIdStr)));
				nd.setPkgNodeId(Long.valueOf(pkgNodeIdMap.get(pkgIdStr)));
				nd.setProcNodeId(procNodeId);
				nd.setNodeCode(nodeCode);
				nd.setDataVal(e.getValue().toString());
				nodeDatas.add(nd);
			}
		}
		return nodesData;
	}

	/**
	 * 按照node_name拆分BidPkgProcNode
	 * 
	 * @param bidPkgProcNodes
	 * @return
	 */
	public static Map<String, BidPkgProcNode> splitBidPkgProcNodeMap(List<BidPkgProcNode> bidPkgProcNodes) {
		Map<String, BidPkgProcNode> resultMap = new HashMap<String, BidPkgProcNode>();
		for (BidPkgProcNode bidPkgProcNode : bidPkgProcNodes) {
			resultMap.put(bidPkgProcNode.getPkgNodeName(), bidPkgProcNode);
		}
		return resultMap;
	}


	/**
	 * 寻找下一节点
	 * 
	 * @param nextProcNodeId
	 *            下一节点id
	 * @param bidPkgProcNodeMap
	 * @param procDefNodeNextMap
	 * @return
	 */
	public static BidPkgProcNode getNextBidPkgProcNode(Long nextProcNodeId, Map<Long, BidPkgProcNode> bidPkgProcNodeMap,
			Map<Long, ProcDefNodeNext> procDefNodeNextMap) {
		if (nextProcNodeId == null) {
			return null;
		}
		BidPkgProcNode bidPkgProcNode = bidPkgProcNodeMap.get(nextProcNodeId);
		if (bidPkgProcNode != null) {
			return bidPkgProcNode;
		}
		// 如果下一节点不存，则从下一节点的下一节点查找
		ProcDefNodeNext procDefNext = procDefNodeNextMap.get(nextProcNodeId);
		if (procDefNext == null) {
			return null;
		} else {
			return getNextBidPkgProcNode(procDefNext.getNextProcNodeId(), bidPkgProcNodeMap, procDefNodeNextMap);
		}
	}

	/**
	 * 通过procNodeId把list变成map
	 * 
	 * @param nodeNextList
	 * @return 返回一个map，key为procNodeId
	 */
	public static Map<Long, ProcDefNodeNext> splitProcDefNodeNext(List<ProcDefNodeNext> nodeNextList) {
		if (nodeNextList == null || nodeNextList.size() == 0) {
			return null;
		}
		Map<Long, ProcDefNodeNext> resultMap = new HashMap<>();
		for (ProcDefNodeNext procDefNodeNext : nodeNextList) {
			resultMap.put(procDefNodeNext.getProcNodeId(), procDefNodeNext);
		}
		return resultMap;
	}

	/**
	 * 通过procNodeId把list变成map
	 * 
	 * @param pkgProcNodeList
	 * @return 返回一个map，key为procNodeId
	 */
	public static Map<Long, BidPkgProcNode> splitBidPkgProcNode(List<BidPkgProcNode> pkgProcNodeList) {
		if (pkgProcNodeList == null || pkgProcNodeList.size() == 0) {
			return null;
		}
		Map<Long, BidPkgProcNode> resultMap = new HashMap<>();
		for (BidPkgProcNode bidPkgProcNode : pkgProcNodeList) {
			resultMap.put(bidPkgProcNode.getProcNodeId(), bidPkgProcNode);
		}
		return resultMap;
	}
	
	
}
