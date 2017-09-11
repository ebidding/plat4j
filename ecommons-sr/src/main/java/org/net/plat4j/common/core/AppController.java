package org.net.plat4j.common.core;

import org.apache.commons.lang3.StringUtils;
import org.net.plat4j.sr.core.base.BaseControllerContext;
import org.net.plat4j.sr.core.base.BaseDispathController;
import org.net.plat4j.sr.core.base.BaseServiceResultModel;

public class AppController extends BaseDispathController {

	// protected Logger logger = LoggerFactory.getLogger(getClass().getName());
	public String getActionMessage(BaseControllerContext context, BaseServiceResultModel srModel) {
		String message = srModel.getMessage();
		if (StringUtils.isEmpty(message)) {
			message = this.getActionMessage(context.getRequest(), srModel.getSrUserProcessModel().getResultDescBundle(),
					srModel.getSrUserProcessModel().getResultDesc(),
					srModel.getSrUserProcessModel().getResultDescArgs());
		}
		return message;
	}

	// /**
	// * 获取工作流实例id.
	// *
	// * @param businessId 业务id.
	// * @param tableName 业务表名.
	// * @return 工作流实例id.
	// */
	// protected String getProcessInstanceId(String businessId,String tableName)
	// {
	//
	// return getProcessInstanceId(NumberUtils.toLong(businessId),tableName);
	// }
	//
	// /**
	// * 获取工作流实例id.
	// *
	// * @param businessId 业务id.
	// * @param tableName 业务表名.
	// * @return 工作流实例id.
	// */
	// protected String getProcessInstanceId(Long businessId,String tableName) {
	//
	// GetSysWorkflowBusinessSpModel sysWorkflowBusinessSpModel = new
	// GetSysWorkflowBusinessSpModel();
	// sysWorkflowBusinessSpModel.setBusinessId(businessId);
	// sysWorkflowBusinessSpModel.setTableName(tableName);
	// GetSysWorkflowBusinessSrModel sysWorkflowBusinessSrModel =
	// (GetSysWorkflowBusinessSrModel) invokeService("IWorkflowService",
	// IWorkflowService.class, "getSysWorkflowBusiness",
	// sysWorkflowBusinessSpModel);
	//
	// String processInstanceId =
	// sysWorkflowBusinessSrModel.getSysWorkflowBusiness().getProcessInstanceId();
	// return processInstanceId;
	// }
	//
	// /**
	// * 保存工作流实例id.
	// *
	// * @param businessId 业务id.
	// * @param tableName 业务表名.
	// * @param processInstanceId 工作流实例id.
	// */
	// protected void saveProcessInstanceId(String businessId,String
	// tableName,String processInstanceId) {
	//
	// saveProcessInstanceId(NumberUtils.toLong(businessId),tableName,processInstanceId);
	// }
	//
	// /**
	// * 保存工作流实例id.
	// *
	// * @param businessId 业务id.
	// * @param tableName 业务表名.
	// * @param processInstanceId 工作流实例id.
	// */
	// protected void saveProcessInstanceId(Long businessId,String
	// tableName,String processInstanceId) {
	//
	// UpdateSysWorkflowBusinessSpModel updateSysWorkflowBusinessSpModel = new
	// UpdateSysWorkflowBusinessSpModel();
	// updateSysWorkflowBusinessSpModel.setBusinessId(businessId);
	// updateSysWorkflowBusinessSpModel.setTableName(tableName);
	// updateSysWorkflowBusinessSpModel.setProcessInstanceId(processInstanceId);
	//
	// invokeService("IWorkflowService", IWorkflowService.class,
	// "updateSysWorkflowBusiness", updateSysWorkflowBusinessSpModel);
	//
	// }

}
