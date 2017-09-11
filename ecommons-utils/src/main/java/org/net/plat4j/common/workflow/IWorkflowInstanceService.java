package org.net.plat4j.common.workflow;

public interface IWorkflowInstanceService {
	/**
	 * 获取实例processInstanceId和processdeFinitionId<br>
	 * getInstanceId为空时,value为null<br>
	 * getInstanceId不为空时,length长度为2<br>
	 * @param businessTableName 业务表名
	 * @param businessTableId 业务表Id
	 * @return
	 */
	String[] getInstanceId(String businessTableName,String businessTableId,String workFlowVersion);
}
