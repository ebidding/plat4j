/*
	Copyright (C) 2014 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2014-07-15 14:32:51
	
	Revision History:
	Version     Date              					Author			Comments
	1.0         	2014-07-15 14:32:51		yujie				Create file
=========================================================================
*/

package org.net.plat4j.common.workflow;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public interface IWorkflowUtil {
	/**
	 * 设置审批查看的审批内容
	 * 
	 * @param url 查看审批的内容(i.e.:Action.do?method=view&no_sitemesh&....)
	 */
	void setViewApproveContent(String url);
	/**
	 * 创建审批内容文件
	 * 
	 * @param instanceId 审批实例id
	 */
	void createWorkFlowViewFile(String instanceId,HttpServletRequest request );
	
	/**
	 * 设置工作流变量
	 * @param key 
	 * @param value
	 */
	void setVariable(String key, Object value);
	
	/**
	 * 设置工作流变量
	 * @param key 
	 * @param value
	 */
	void setObjectVariable(String key, Object value);
	
	/**
	 * 传递业务表名和业务表名id
	 * @param businessTableName
	 * @param businessTableId
	 */
	void setBusinessTableInfo(String businessTableName, String businessTableId);
	/**
	 * 根据流程KEY启动一个工作流
	 * @param processKeyHeader       工作流的前部分KEY。
	 * @param callbackBeanId  调用回调函数所需的beanId
	 * @param callbackMethodName 回调函数的名称。
	 * @param companyId 公司id
	 * @return 返回启动的流程的ID
	 * @throws Exception 如果找不到审批人则抛出异常
	 */
	String startProcessInstance(String processKey, Long companyId,String callbackBeanId,
		String callbackMethodName);
	/**
	 * 根据流程KEY启动一个工作流
	 * @param processKey       工作流的KEY
	 * @param companyId       公司ID
	 * @param callbackBeanId  调用回调函数所需的beanId
	 * @param callbackMethodName 回调函数的名称。
	 * @param submitRemark 提交备注信息
	 * @return 返回启动的流程的ID
	 * @throws Exception 如果找不到审批人则抛出异常
	 */
	String startProcessInstance(String processKey, String companyId,
		String callbackBeanId, String callbackMethodName,String submitRemark);
	
	/**
	 * 根据流程KEY启动一个工作流
	 * @param processKey       提供名称为 "WF_PROCESSKEY_HIDDEN" 的工作流键值的 request 实例。
	 * @param companyId       公司ID
	 * @param callbackBeanId  调用回调函数所需的beanId
	 * @param callbackMethodName 回调函数的名称。
	 * @return 返回启动的流程的ID
	 * @throws Exception 如果找不到审批人则抛出异常
	 */
	String startProcessInstance(HttpServletRequest request,
		String callbackBeanId, String callbackMethodName);
	/**根据业务表名和业务表ID 获得实例ID
	*@param tableName
	*@param tableId
	*@return
	*/
	Long getSwfInstanceId(String tableName,Long tableId,String workFlowVersion);
	/**根据业务表名和业务表ID 获得审批人名称
	*@param tableName
	*@param tableId
	*@return
	*/
	List<Map<String,Object>> getApproverName(String tableName,Long tableId,String workFlowVersion);
}
