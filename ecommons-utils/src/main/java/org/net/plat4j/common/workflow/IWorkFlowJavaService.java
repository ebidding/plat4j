package org.net.plat4j.common.workflow;

import org.activiti.engine.delegate.DelegateExecution;

/**
 * Activiti5 工作流JavaService Task调用的spring bean <li>1.定义流程时在服务任务中填写该bean.method</li>
 * <li>2.在类中定义相应的方法,在实现类中实现业务逻辑的处理</li> <li>3.如果需要相应的业务数据,在启动流程是添加相应的流程变量</li>
 * <li>4.所有的方法第一个参数使用DelegateExecution execution<br/>
 * 第二个参数为自定义,或者流程启动时初始化的参数
 * <code>public void printMessage(execution, myVar)</code></li>
 * 
 * @author matthewqu
 * 
 */
public interface IWorkFlowJavaService {
	static final String P_TYPE_AUDIT_SKIPPED = "-1";
	static final String P_TYPE_AUDIT_PASS = "1";
	static final String P_TYPE_AUDIT_REJECTED = "0";
	static final String P_TYPE_AUDIT_CANCELD = "-2";

	/**
	 * 工作流结束后的业务逻辑处理函数
	 * 
	 * @param pExecution
	 *            工作流委托的代理类, 包含相关业务信息，可以通过 pExecution.getVariable 方法获取设置的变量值
	 * @param pType
	 *            处理审批失败或审批拒绝的参数
	 * @param args
	 *            业务模块特有的在流程节点中定义的参数
	 */
	void callback(DelegateExecution pExecution, String pType, Object... args);
	
	/**
	 * 工作流结束后的业务逻辑处理函数
	 * 
	 * @param pExecution
	 *            工作流委托的代理类, 包含相关业务信息，可以通过 pExecution.getVariable 方法获取设置的变量值
	 * @param pType
	 *            处理审批失败或审批拒绝的参数
	 */
	void callback(DelegateExecution pExecution, String pType);

	/**
	 * 工作流结束后的业务逻辑处理函数
	 * 
	 * @param cxt
	 *            工作流相关上下文，可以通过 cxt.getVariable 方法获取设置的变量值。
	 * @param pType
	 *            处理审批失败或审批拒绝的参数，可以使用形如 IWorkFlowJavaService.P_TYPE_AUDIT_*
	 *            的常量值。
	 * @param args
	 *            业务模块特有的在流程节点中定义的参数。
	 */
	void doCallback(IWorkflowContext cxt, String pType, Object... args);
}
