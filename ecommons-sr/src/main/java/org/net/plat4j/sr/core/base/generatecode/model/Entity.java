/*
	Copyright (C) 2016 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2016年5月23日 下午6:14:41
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2016年5月23日下午6:14:41		chenyj			Create file
=========================================================================
*/
package org.net.plat4j.sr.core.base.generatecode.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenyj
 *
 */
public class Entity  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8285842637305068902L;
	private String tableName;
	private List<Property> properties;
	private List<Key> primaryKeys;
	private List<Key> uniqueKeys;
	private String className;
	private boolean isComposeId;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<Property> getProperties() {
		return properties;
	}
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	public List<Key> getPrimaryKeys() {
		return primaryKeys;
	}
	public void setPrimaryKeys(List<Key> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}
	public List<Key> getUniqueKeys() {
		return uniqueKeys;
	}
	public void setUniqueKeys(List<Key> uniqueKeys) {
		this.uniqueKeys = uniqueKeys;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public boolean isComposeId() {
		return isComposeId;
	}
	public void setComposeId(boolean isComposeId) {
		this.isComposeId = isComposeId;
	}
	@Override
	public String toString() {
		return "Entity [tableName=" + tableName + ", properties=" + properties
				+ ", primaryKeys=" + primaryKeys + ", uniqueKeys=" + uniqueKeys + "]";
	}
	
}
