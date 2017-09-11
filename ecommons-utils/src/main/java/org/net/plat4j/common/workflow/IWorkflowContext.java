/*
	Copyright (C) 2014 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2014-08-15 11:07:29
	
	Revision History:
	Version     Date              				Author			Comments
	1.0         	2014-08-15 11:07:29		yujie				Create file
=========================================================================
*/

package org.net.plat4j.common.workflow;

/**
 * @author yujie
 *
 */
public interface IWorkflowContext {
    static final String CONTEXT_KEY_CALLBACK_METHOD_NAME = "CALLBACK_METHOD_NAME";
    static final String CONTEXT_KEY_CALLBACK_BEAN_ID = "CALLBACK_BEAN_ID";
    /**
     * 获取启动流程时设置的变量值。
     * 
     * <pre>
     * Author: yujie
     * Created Time: 2014-08-15 11:23:00
     * </pre>
     * 
     * @param varName 启动流程时设置的变量值的键。
     * @return 要获取的变量的值，如果启动流程时没有设定指定的变量名对应的变量，则返回 null。
     */
    <T> T getVariable(String varName);
}
