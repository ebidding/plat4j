/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: cairw
	Version: 1.0
	Created Time: 2015年5月28日 下午1:41:25
	
	Revision History:
	Version     Date                Author			Comments
	1.0        2015年5月28日下午1:41:25		cairw     	Create file
=========================================================================
*/

package org.net.plat4j.sr.core.utils.impl;

import java.lang.reflect.Method;

/**
 * @author cairw
 *
 */
public class MethodAttr {
	private Method method;
	private String parmaterType;
	/**
	 * @return the method
	 */
	public Method getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(Method method) {
		this.method = method;
	}
	/**
	 * @return the parmaterType
	 */
	public String getParmaterType() {
		return parmaterType;
	}
	/**
	 * @param parmaterType the parmaterType to set
	 */
	public void setParmaterType(String parmaterType) {
		this.parmaterType = parmaterType;
	}
	
}
