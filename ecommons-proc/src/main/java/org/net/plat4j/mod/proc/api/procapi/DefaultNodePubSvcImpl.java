/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-25 13:44:03
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-25 13:44:03		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.procapi;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yujie
 *
 */

public class DefaultNodePubSvcImpl {

	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static  String  getStringType(String bizFieldColType, String bizFormatMask ){
		String append="?";
		if(StringUtils.isEmpty(bizFieldColType)) return append;
		if("DATE".equals(bizFieldColType)){
			if(StringUtils.isEmpty(bizFormatMask))
				append="to_date(?,'yyyy-MM-dd hh24:mi:ss')";
			else
				append="to_date(?,'"+bizFormatMask+"')";
		}
		return append;
	}
	
}
