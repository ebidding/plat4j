package org.net.plat4j.common.dataDictionary;

import java.util.List;
import java.util.Map;

import org.net.plat4j.common.dataDictionary.CfgDataDictionary;

public interface IDataDictionaryService {
	/**
	 * 值集查询接口（输入val和code用于导出）
	 * 
	 * @param 值集code。
	 * @param 值集val。
	 * @param 代理机构agentId
	 */
	 String getDataDicName(String code,String val,Long agentId);
	 
	/**
	 * 值集查询接口（输入val和code用于导出）
	 * 
	 * @param 值集code。
	 * @param 值集val。
	 */
	 String getDataDicName(String code,String val);
	 
	/**
	 * 值集查询接口,返回key为val的map（输入code用于导出）
	 * 
	 * @param 值集code。
	 * @param 值集val。
	 * @param 代理机构agentId
	 */
	
	 Map<String,CfgDataDictionary> getDataDicNameMap(String code,Long agentId);
		 
	/**
	 * 值集查询接口,返回key为val的map（输入code用于导出）
	 * 
	 * @param 值集code。
	 * @param 值集val。
	 */
	 Map<String,CfgDataDictionary> getDataDicNameMap(String code);
	
	/**
	 * 值集val查询接口（用于导入、保存）
	 * 
	 * @param 值集code。
	 * @param 值集name。
	 * @param 代理机构agentId。
	 */
	 String getDataDicVal(String code,String name,Long agentId);
	/**
	 * 值集val查询接口（用于导入、保存）
	 * 
	 * @param 值集code。
	 * @param 值集name。
	 */
	 String getDataDicVal(String code,String name);
	 
	/**
	 * 值集val查询接口（用于导入、保存）
	 * 
	 * @param 值集code。
	 * @param 值集name。
	 * @param 代理机构agentId。
	 */
	 Map<String,String> getDataDicValMap(String code,Long agentId);
	/**
	 * 值集val查询接口（用于导入、保存）
	 * 
	 * @param 值集code。
	 * @param 值集name。
	 */
	 Map<String,String> getDataDicValMap(String code);
	 
	/**
	 * 值集查询接口
	 * 
	 * @param 值集code。
	 * @param 值集过滤条件groups。
	 * @param 代理机构agentId。
	 */
	 List<CfgDataDictionary> getDataDicList(Long agentId,String code,String groups);
	  
	/**
	 * 值集名ValDescCn查询接口
	 * 
     * @param 值集code。
	 * @param 值集过滤条件groups。
	 * @param 代理机构agentId。
	 */
	 List<CfgDataDictionary> getDataDicList(String code,String groups);
	  
	/**
	 * 值集查询接口
	 * 
	 * @param 值集code。
	 * @param 代理机构agentId。
	 */
	 List<CfgDataDictionary> getDataDicList(Long agentId,String code);
	  
	/**
	 * 值集查询接口
	 * 
	 * @param 值集code。
	 * @param 代理机构agentId。
	 */
	 List<CfgDataDictionary> getDataDicList(String code);
	
	/**
	 * 值集查询接口（输入val和code用于显示包括级联显示）
	 * 
	 * @param 值集code。
	 * @param 值集val。
	 */
	 CfgDataDictionary getDataDictionary(String code,String val);

	/**
	 * 加载值集接口
	 */
	 void refreshDataDicCache();

}
