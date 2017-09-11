/*
	Copyright (C) 2016 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2016年5月24日 下午1:25:58
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2016年5月24日下午1:25:58		chenyj			Create file
=========================================================================
*/
package org.net.plat4j.sr.core.base.generatecode.model;

import java.io.Serializable;

/**
 * @author chenyj
 *
 */
public class Key implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7826404340958112760L;
	private String columnName;
	private int position;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "Key [columnName=" + columnName + ", position=" + position + "]";
	}
}
