/*
	Copyright (C) 2014 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2014-08-15 11:09:03
	
	Revision History:
	Version     Date              				Author			Comments
	1.0         	2014-08-15 11:09:03		yujie				Create file
=========================================================================
*/

package org.net.plat4j.common.workflow;

import org.activiti.engine.delegate.DelegateExecution;

/**
 * 基于 Activiti 工作流的默认实现。
 * 
 * @author yujie
 *
 */
public class DefaultWorkflowContext implements IWorkflowContext {
    private DelegateExecution pExecution;
    
    public DefaultWorkflowContext(DelegateExecution pExecution) {
	this.pExecution = pExecution;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getVariable(String varName) {
	return (T)this.pExecution.getVariable(varName);
    }

}
