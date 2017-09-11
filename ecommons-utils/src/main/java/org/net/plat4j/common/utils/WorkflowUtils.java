package org.net.plat4j.common.utils;

import java.util.List;
import java.util.Map;

import org.net.plat4j.common.workflow.IWorkflowUtil;
import org.net.plat4j.common.workflow.IWorkflowUtilFactory;

import net.plat4j.core.spring.BeanFactory;

/**
 * 
 */
public class WorkflowUtils {
	public static IWorkflowUtil createUtil(String businessCode, String businessName, String businessUrl) {
		IWorkflowUtilFactory wfUtilFactory = (IWorkflowUtilFactory) BeanFactory
				.getBean("IWorkflowUtilFactory");
		return wfUtilFactory.create(businessCode, businessName, businessUrl);
	}
	
	public static boolean isCurCompanyAuditRequired(String processKey) {
		return ConfigUtils.getCurCompanyConfigBooleanValue("WORKFLOW_AUDIT_NODE", processKey);		
	}
	
	public static boolean isCompanyAuditRequired(String processKey,Long companyId) {
		return ConfigUtils.getCompanyConfigBooleanValue(companyId,"WORKFLOW_AUDIT_NODE", processKey);		
	}

	public static String getCompanyProcessKey(String processKey,
			String companyId) {
		return processKey;
	}
	public static Long getSwfInstanceId(String tableName,Long tableId,String workFlowVersion){
		IWorkflowUtil workflowUtils =  BeanFactory.getBean("IWorkflowUtil");
		return workflowUtils.getSwfInstanceId(tableName, tableId,workFlowVersion);
		
	}
	
	/**
	 * 根据代理机构获取审批人名称
	 * @param tableName
	 * @param tableId
	 * @param agentId
	 * @return
	 */
	public static List<Map<String,Object>> getApproverName(String tableName,Long tableId,Long agentId){
		IWorkflowUtil workflowUtils = BeanFactory.getBean("IWorkflowUtil");
		String workFlowVersion = ConfigUtils.getAgentConfigValue(agentId, "FUNCTION_SWITCH", "WORKFLOW_VERSION");
		return workflowUtils.getApproverName(tableName, tableId,workFlowVersion);
	}

	public static List<Map<String,Object>> getApproverName(String tableName,Long tableId){
		IWorkflowUtil workflowUtils = BeanFactory.getBean("IWorkflowUtil");
		String workFlowVersion = ConfigUtils.getAgentConfigValue(WebUtils.getUserAgentId(), "FUNCTION_SWITCH", "WORKFLOW_VERSION");
		return workflowUtils.getApproverName(tableName, tableId,workFlowVersion);
	}
	
	public static List<Map<String,Object>> getApproverName(String tableName,Long tableId,String workFlowVersion){
		IWorkflowUtil workflowUtils = BeanFactory.getBean("IWorkflowUtil");
		return workflowUtils.getApproverName(tableName, tableId,workFlowVersion);
	}
}
