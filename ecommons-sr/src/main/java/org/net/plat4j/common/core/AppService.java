package org.net.plat4j.common.core;

import java.util.ArrayList;
import java.util.List;

import org.net.plat4j.sr.core.base.BaseService;
import org.net.plat4j.sr.core.base.BaseServiceResultModel;
import org.net.plat4j.sr.core.base.SrPageModel;
import org.net.plat4j.sr.core.utils.IBaseBeanUtils;

import net.plat4j.core.spring.BeanFactory;
import net.plat4j.core.spring.Service;
import com.github.pagehelper.Page;

public class AppService extends BaseService implements Service{

//	/**
//	 * 获取工作流实例id.
//	 * 
//	 * @param businessId 业务id.
//	 * @param tableName 业务表名.
//	 * @return 工作流实例id.
//	 */
//	protected String getProcessInstanceId(String businessId,String tableName) {
//		
//		return getProcessInstanceId(NumberUtils.toLong(businessId),tableName);
//	}
//	
//	/**
//	 * 获取工作流实例id.
//	 * 
//	 * @param businessId 业务id.
//	 * @param tableName 业务表名.
//	 * @return 工作流实例id.
//	 */
//	protected String getProcessInstanceId(Long businessId,String tableName) {
//		
//		GetSysWorkflowBusinessSpModel sysWorkflowBusinessSpModel = new GetSysWorkflowBusinessSpModel();
//		sysWorkflowBusinessSpModel.setBusinessId(businessId);
//		sysWorkflowBusinessSpModel.setTableName(tableName);
//		GetSysWorkflowBusinessSrModel sysWorkflowBusinessSrModel = (GetSysWorkflowBusinessSrModel) invokeService("IWorkflowService", IWorkflowService.class, "getSysWorkflowBusiness", sysWorkflowBusinessSpModel);
//		
//		String processInstanceId = sysWorkflowBusinessSrModel.getSysWorkflowBusiness().getProcessInstanceId();
//		return processInstanceId;
//	}
//	
//	/**
//	 * 保存工作流实例id.
//	 * 
//	 * @param businessId 业务id.
//	 * @param tableName 业务表名.
//	 * @param processInstanceId 工作流实例id.
//	 */
//	protected void saveProcessInstanceId(String businessId,String tableName,String processInstanceId) {
//		
//		saveProcessInstanceId(NumberUtils.toLong(businessId),tableName,processInstanceId);
//	}
//	
//	/**
//	 * 保存工作流实例id.
//	 * 
//	 * @param businessId 业务id.
//	 * @param tableName 业务表名.
//	 * @param processInstanceId 工作流实例id.
//	 */
//	protected void saveProcessInstanceId(Long businessId,String tableName,String processInstanceId) {
//		
//		UpdateSysWorkflowBusinessSpModel updateSysWorkflowBusinessSpModel = new UpdateSysWorkflowBusinessSpModel();
//		updateSysWorkflowBusinessSpModel.setBusinessId(businessId);
//		updateSysWorkflowBusinessSpModel.setTableName(tableName);
//		updateSysWorkflowBusinessSpModel.setProcessInstanceId(processInstanceId);
//		 
//		invokeService("IWorkflowService", IWorkflowService.class, "updateSysWorkflowBusiness", updateSysWorkflowBusinessSpModel);
//	
//	}
	/**
	 * <h1>排除id的值相等记录的List</h1><BR>excludeIdValue仅支持String,Long型
	 * <strong>Usage</strong>在list中排除记录id为1的值
	 * <pre>
	 * list = getListAfterRemoveIdValue(list, "id", "1");
	 * </pre>
	 * @param list Model的数据集合
	 * @param excludeIdField 排除记录的字段名
	 * @param excludeIdValue 排除记录的值
	 * @return 返回排除后的list记录
	 */
	
	public void setPage(BaseServiceResultModel srModel, Page<Object> startPage) {
	    SrPageModel srPageModel = new SrPageModel();
	    srPageModel.setPageCount(Long.valueOf(startPage.getPages()));
	    srPageModel.setPageNo(Long.valueOf(startPage.getPageNum()));
	    srPageModel.setPageSize(Long.valueOf(startPage.getPageSize()));
	    srPageModel.setRecordCount(Long.valueOf(startPage.getTotal()));
	    srModel.setSrPageModel(srPageModel);
	  }
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public List getListAfterRemoveIdValue(List list,String excludeIdField,String excludeIdValue){
		IBaseBeanUtils baseBeanUtils = BeanFactory.getBean("IBaseBeanUtils"); 
		List ret = new ArrayList();
		if(list!=null){
			for(int i=0,j=list.size();i<j;i++){
				Object listModelValue = baseBeanUtils.getField(list.get(i), excludeIdField);
				if(excludeIdValue==null && listModelValue==null){
					continue;
				}				
				if(excludeIdValue!=null && listModelValue!=null && excludeIdValue.equals(listModelValue.toString())){
					continue;
				}
				ret.add(listModelValue);
			}
		}			
		return ret;
	}
}
