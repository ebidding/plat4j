/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2015年7月23日 上午11:50:40
	
	Revision History:
	Version     Date              					Author			Comments
	1.0      2015年7月23日上午11:50:40		    		chenyj			Create file
=========================================================================
 */
package org.net.plat4j.mod.proc.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.net.plat4j.common.utils.CacheUtils;
import org.net.plat4j.common.utils.Constants;
import org.net.plat4j.mod.proc.api.model.ProcDefNode;
import org.net.plat4j.mod.proc.api.model.ProcDefNodeNext;
import org.net.plat4j.sr.core.utils.LogHelper;

/**
 * 配置表中从缓存中获取节点工具类
 * 
 * @author chenyj
 */
public class ProcNodeCacheUtil {
	protected static LogHelper logger = new LogHelper(ProcNodeCacheUtil.class);
	private static final String PROC_DEF_NODE_CACHE_NAME = "CFG_PROC";
	private static final String NODE_DICT_PKG_FIELD_CACHE_KEY = "CFG_NODE_DICT_PKG_FIELD_KEY";
	private static final String PROC_DEF_NODE_ITEMS_CACHE_KEY = "CFG_PROC_DEF_NODE_ITEMS_KEY";
	private static final String PROC_ID_CAHCE_KEY = "CFG_PROC_ID_KEY";

	/**
	 * 获取配置节点信息
	 * 
	 * @param procId
	 * @return
	 */
	public static List<ProcDefNode> getProcDefNode(Long procId) {
		String cacheKey = "CFG_PROC_DEF_NODE_" + procId;
		List<ProcDefNode> nodeList = CacheUtils.getValue(PROC_DEF_NODE_CACHE_NAME, cacheKey);
		if (nodeList == null) {
			nodeList = ProcApiUtils.getProcDefNode(procId);
			CacheUtils.cacheValue(PROC_DEF_NODE_CACHE_NAME, cacheKey, nodeList);
		}
		return nodeList;
	}

	/**
	 * 获取配置节点关系信息
	 * 
	 * @param procId
	 * @return
	 */
	public static List<ProcDefNodeNext> getProcDefNodeNext(Long procId) {
		String cacheKey = "CFG_PROC_DEF_NODE_NEXT_" + procId;
		List<ProcDefNodeNext> nodeNextList = CacheUtils.getValue(PROC_DEF_NODE_CACHE_NAME, cacheKey);
		if (nodeNextList == null) {
			nodeNextList = ProcApiUtils.getProcDefNodeNext(procId);
			CacheUtils.cacheValue(PROC_DEF_NODE_CACHE_NAME, cacheKey, nodeNextList);
		}
		return nodeNextList;
	}

	/**
	 * 获取节点值集取值与标段（包）的关联关系
	 * 
	 * @return 返回一个Map，key为agentId，value为一个Map，<BR/>
	 *         value中的Map中的key为GROUP_DICT_CODE,value为标段（包）中的字段
	 */
	public static Map<Long, Map<String, String>> getNodeDictPkgField() {
		Map<Long, Map<String, String>> nodeDictPkgFieldMap = CacheUtils.getValue(PROC_DEF_NODE_CACHE_NAME,
				NODE_DICT_PKG_FIELD_CACHE_KEY);
		if (nodeDictPkgFieldMap == null) {
			List<Map<String, Object>> list = ProcApiUtils.getNodeDictPkgField();
			nodeDictPkgFieldMap = new HashMap<Long, Map<String, String>>();
			for (Map<String, Object> map : list) {
				Long agentId = Long.valueOf(map.get("agentId").toString());
				Map<String, String> agentIdMap = nodeDictPkgFieldMap.get(agentId);
				if (agentIdMap == null) {
					agentIdMap = new HashMap<String, String>();
					nodeDictPkgFieldMap.put(agentId, agentIdMap);
				}
				String groupDictCode = (String) map.get("groupDictCode");
				String fieldName = (String) map.get("fieldName");
				agentIdMap.put(groupDictCode, fieldName);
			}
			CacheUtils.cacheValue(PROC_DEF_NODE_CACHE_NAME, NODE_DICT_PKG_FIELD_CACHE_KEY, nodeDictPkgFieldMap);
		}
		return nodeDictPkgFieldMap;
	}

	/**
	 * 获取CFG_PROC_DEF_NODE_ITEMS信息
	 * 
	 * @return 返回一个多层嵌套的Map<br/>
	 *         第一层map的key为proc_node_id（CFG_PROC_DEF_NODE表的id），value为一个map<br/>
	 *         第二层map的key为group_dict_val，value为node_code
	 */
	public static Map<Long, Map<String, String>> getProcDefNodeItems() {
		Map<Long, Map<String, String>> procDefNodeItemsMap = CacheUtils.getValue(
				PROC_DEF_NODE_CACHE_NAME, PROC_DEF_NODE_ITEMS_CACHE_KEY);
		if (procDefNodeItemsMap == null) {
			procDefNodeItemsMap = new HashMap<>();
			List<Map<String, Object>> list = ProcApiUtils.getProcDefNodeItems();
			for(Map<String, Object> map : list){
				Long procNodeId = Long.valueOf(map.get("procNodeId").toString());
				Map<String, String> procNodeIdMap = procDefNodeItemsMap.get(procNodeId);
				if(procNodeIdMap == null){
					procNodeIdMap = new HashMap<>();
					procDefNodeItemsMap.put(procNodeId, procNodeIdMap);
				}
				String groupDictVal = (String)map.get("groupDictVal");
				String nodeCode = (String)map.get("nodeCode");
				procNodeIdMap.put(groupDictVal, nodeCode);
			}
			CacheUtils.cacheValue(PROC_DEF_NODE_CACHE_NAME, PROC_DEF_NODE_ITEMS_CACHE_KEY, procDefNodeItemsMap);
		}
		return procDefNodeItemsMap;
	}
	/**
	 * 通过procCode获取procId
	 * @param agentId 代理机构id
	 * @param procCode 
	 * @return
	 */
	public static Long getProcIdByProcCode(Long agentId, String procCode){
		Map<Long, Map<String, Long>> procIdMap = CacheUtils.getValue(PROC_DEF_NODE_CACHE_NAME, PROC_ID_CAHCE_KEY);
		if(procIdMap == null){
			List<Map<String, Object>> procInfoList = ProcApiUtils.getProcInfos();
			procIdMap = new HashMap<>();
			for(Map<String, Object> map : procInfoList){
				Long aId = Long.valueOf(map.get("agentId").toString());
				Long procId =Long.valueOf(map.get("procId").toString());
				String pCode = map.get("procCode").toString();
				Map<String, Long> procCodeMap = procIdMap.get(aId);
				if(procCodeMap == null){
					procCodeMap = new HashMap<>();
					procIdMap.put(aId, procCodeMap);
				}
				procCodeMap.put(pCode, procId);
			}
			CacheUtils.cacheValue(PROC_DEF_NODE_CACHE_NAME, PROC_ID_CAHCE_KEY, procIdMap);
		}
		Map<String, Long> map = procIdMap.get(agentId);
		Long procId = null;
		if(map == null){//未找到招标代理机构级别的配置，找平台的配置
			Map<String, Long> platformMap = procIdMap.get(Constants.PLATFORM_ID);
			if(platformMap != null){
				procId = platformMap.get(procCode);
			}
		}else{//取招标代理机构的配置
			procId = map.get(procCode);
		}
		if(procId == null){
			logger.info("未找到配置的流程，agentId : " + agentId + " , procCode : " + procCode);
			throw new RuntimeException("未找到配置的流程");
		}
		return procId;
	}
}
