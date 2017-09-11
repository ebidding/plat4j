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


public interface IWorkflowUtilFactory {
	IWorkflowUtil create(String businessCode, String businessName, String businessUrl);
}
