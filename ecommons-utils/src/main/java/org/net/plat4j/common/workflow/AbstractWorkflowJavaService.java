package org.net.plat4j.common.workflow;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.activiti.engine.delegate.DelegateExecution;

import org.net.plat4j.sr.core.utils.LogHelper;

/**
 * <pre>
 * @author yujie
 * </pre>
 *
 * 子类需要实现至少一个有如下签名的方法，其中的函数名应该和流程定义中的函数名一样：
 * <pre>
 * public void XX(IWorkflowContext cxt, String pType, Object... args)
 * </pre>
 */
public class AbstractWorkflowJavaService implements IWorkFlowJavaService {
	protected LogHelper logger = new LogHelper(getClass());
	
	@Override
	public void callback(DelegateExecution pExecution, String pType) {
		this.callback(pExecution, pType,null);
	}
	@Override
	public void callback(DelegateExecution pExecution, String pType, Object... args) {
		IWorkflowContext cxt = new DefaultWorkflowContext(pExecution);
		doCallback(cxt, pType, args);
	}
	
	public boolean isRejected(String pType) {
		return P_TYPE_AUDIT_REJECTED.equals(pType);
	}
	
	public boolean isPassed(String pType) {
		return P_TYPE_AUDIT_PASS.equals(pType);
	}
	
	public boolean isSkipped(String pType) {
		return P_TYPE_AUDIT_SKIPPED.equals(pType);
	}
	
	public boolean isCanceled(String pType){
		return P_TYPE_AUDIT_CANCELD.equals(pType);
	}

	@SuppressWarnings({"rawtypes" })
	@Override
	public void doCallback(IWorkflowContext cxt, String pType, Object... args) {
	    	String callbackMethodName = cxt.getVariable(IWorkflowContext.CONTEXT_KEY_CALLBACK_METHOD_NAME);
		try {
			Method m = null;
			Method[] methods = this.getClass().getMethods();
			for (Method tmpm : methods) {
				if (tmpm.getName().equals(callbackMethodName)) {
					m = tmpm;
					break;
				}
			}
			if (m == null)
				throw new IllegalArgumentException("无效的函数名："
						+ callbackMethodName);

			Class[] params = m.getParameterTypes();
			if (params.length > 2) {
				m.invoke(this, cxt, pType, args);
			} else {
				m.invoke(this, cxt, pType);
			}
		} catch (InvocationTargetException ex) {
			logger.error(ex);
			throw new RuntimeException("执行回调函数时发生错误：" + ex.getTargetException().getMessage(), ex);
		} catch (IllegalAccessException ex) {
			logger.error(ex);
			throw new RuntimeException("执行回调函数时发生错误："+ ex.getMessage(), ex);
		}
	}
}
