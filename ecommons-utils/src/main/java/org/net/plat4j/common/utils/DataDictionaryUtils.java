package org.net.plat4j.common.utils;

import java.util.List;
import java.util.Map;

import org.net.plat4j.common.dataDictionary.CfgDataDictionary;
import org.net.plat4j.common.dataDictionary.IDataDictionaryService;

import net.plat4j.core.spring.BeanFactory;

public class DataDictionaryUtils {
	/**
	 * 值集查询接口（输入val和code用于导出）
	 * 
	 * @param 值集code。
	 * @param 值集val。
	 * @param 代理机构agentId
	 */
	 public static String getDataDicName(String code,String val,Long agentId){
		IDataDictionaryService datadic = (IDataDictionaryService)BeanFactory.getBean("IDataDictionaryService");
		return datadic.getDataDicName(code, val, agentId);
	 }
	 
	/**
	 * 值集查询接口（输入val和code用于导出）
	 * 
	 * @param 值集code。
	 * @param 值集val。
	 */
	 public static String getDataDicName(String code,String val){
		IDataDictionaryService datadic = (IDataDictionaryService)BeanFactory.getBean("IDataDictionaryService");
		return datadic.getDataDicName(code, val);
	 }
	 
	/**
	 * 值集查询接口,返回key为val的map（输入code用于导出）
	 * 
	 * @param 值集code。
	 * @param 代理机构agentId
	 */
	 public static Map<String,CfgDataDictionary> getDataDicNameMap(String code,Long agentId){
		IDataDictionaryService datadic = (IDataDictionaryService)BeanFactory.getBean("IDataDictionaryService");
		return datadic.getDataDicNameMap(code, agentId);
	 }
		 
	/**
	 * 值集查询接口，返回key为val的map（输入code用于导出）
	 * 
	 * @param 值集code。
	 */
	 public static Map<String,CfgDataDictionary> getDataDicNameMap(String code){
		IDataDictionaryService datadic = (IDataDictionaryService)BeanFactory.getBean("IDataDictionaryService");
		return datadic.getDataDicNameMap(code);
	 }
	
	/**
	 * 值集val查询接口（用于导入、保存）
	 * 
	 * @param 值集code。
	 * @param 值集name。
	 * @param 代理机构agentId
	 */
	 public static String getVal(String code,String name,Long agentId){
		IDataDictionaryService datadic = (IDataDictionaryService)BeanFactory.getBean("IDataDictionaryService");
		return datadic.getDataDicVal(code, name, agentId);
	 }
	 
	/**
	 * 值集val查询接口（用于导入、保存）
	 * 
	 * @param 值集code。
	 * @param 值集name。
	 */
	 public static String getVal(String code,String name){
		IDataDictionaryService datadic = (IDataDictionaryService)BeanFactory.getBean("IDataDictionaryService");
	   	return datadic.getDataDicVal(code, name);
	 }
	  
	/**
	 * 值集val查询接口,返回name-val的map（用于导入、保存）
	 * 
	 * @param 值集code。
	 * @param 代理机构agentId
	 */
	 public static Map<String,String> getValMap(String code,Long agentId){
		IDataDictionaryService datadic = (IDataDictionaryService)BeanFactory.getBean("IDataDictionaryService");
	   	return datadic.getDataDicValMap(code, agentId);
	 }
		 
	/**
	 * 值集val查询接口,返回name-val的map（用于导入、保存）
	 * 
	 * @param 值集code。
	 */
	 public static Map<String,String> getValMap(String code){
		IDataDictionaryService datadic = (IDataDictionaryService)BeanFactory.getBean("IDataDictionaryService");
		return datadic.getDataDicValMap(code);
	 }
	 
	/**
	 * 值集名ValDescCn查询接口
	 * 
	 * @param 值集code。
	 * @param 值集过滤条件groups。
	 * @param 代理机构agentId
	 */
	 public static List<CfgDataDictionary> getDataDicList(String code,String groups,Long agentId){
		IDataDictionaryService datadic = (IDataDictionaryService)BeanFactory.getBean("IDataDictionaryService");
		return datadic.getDataDicList(agentId, code, groups);
	 }
	  
	/**
	 * 值集名ValDescCn查询接口
	 * 
	 * @param 值集code。
	 * @param 值集过滤条件groups。
	 */
	 public static List<CfgDataDictionary> getDataDicList(String code,String groups){
		IDataDictionaryService datadic = (IDataDictionaryService)BeanFactory.getBean("IDataDictionaryService");
		return datadic.getDataDicList(code, groups);
	 }
	   
	/**
	 * 值集名ValDescCn查询接口
	 * 
	 * @param 值集code。
	 * @param 值集过滤条件groups。
	 * @param 代理机构agentId
	 */
	 public static List<CfgDataDictionary> getDataDicList(String code,Long agentId){
		IDataDictionaryService datadic = (IDataDictionaryService)BeanFactory.getBean("IDataDictionaryService");
		return datadic.getDataDicList(agentId, code);
	 }
		  
	/**
	 * 值集名ValDescCn查询接口
	 * 
	 * @param 值集code。
	 * @param 值集过滤条件groups。
	 */
	 public static List<CfgDataDictionary> getDataDicList(String code){
		IDataDictionaryService datadic = (IDataDictionaryService)BeanFactory.getBean("IDataDictionaryService");
		return datadic.getDataDicList(code);
	 }
	 
	/**
	 * 值集查询接口（输入val和code用于显示包括级联显示）
	 * 
	 * @param 值集code。
	 * @param 值集val。
	 */
	 public static CfgDataDictionary getDataDictionary(String code,String val){
		 IDataDictionaryService datadic = (IDataDictionaryService)BeanFactory.getBean("IDataDictionaryService");
		 return datadic.getDataDictionary(code, val);
	 }
		 
	 /**
	  * 
	  * @param pCode 父CODE
	  * @param pVal 父Value
	  * @return 返回子对象LIST
	  */
	 public static List<CfgDataDictionary> getSonCfgDataDictionaryLst(String pCode,String pVal){
		 CfgDataDictionary dataDictionary = getDataDictionary(pCode,pVal);
		 return dataDictionary.getSonDicList();
	 }
	/**
	 * 加载值集
	 */
	 public static void refreshDataDicCache(){
		IDataDictionaryService datadic = (IDataDictionaryService)BeanFactory.getBean("IDataDictionaryService");
		datadic.refreshDataDicCache();
	 }
}
