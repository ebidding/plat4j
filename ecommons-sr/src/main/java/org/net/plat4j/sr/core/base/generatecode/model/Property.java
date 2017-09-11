/*
	Copyright (C) 2016 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2016年5月23日 下午6:14:32
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2016年5月23日下午6:14:32		chenyj			Create file
=========================================================================
*/
package org.net.plat4j.sr.core.base.generatecode.model;

import java.io.Serializable;

/**
 * @author chenyj
 *
 */
public class Property implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5435067923455960347L;
	private String columnName;
	private String dataType;
	private Integer dataLength;
	private Integer dataPrecision;
	private Integer dataScale;
	private String propertyName;
	private String propertyType;
	private boolean isLob;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public Integer getDataLength() {
		return dataLength;
	}
	public Integer getDataPrecision() {
		return dataPrecision;
	}
	public void setDataPrecision(Integer dataPrecision) {
		this.dataPrecision = dataPrecision;
	}
	public Integer getDataScale() {
		return dataScale;
	}
	public void setDataScale(Integer dataScale) {
		this.dataScale = dataScale;
	}
	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public boolean isLob() {
		return isLob;
	}
	public void setLob(boolean isLob) {
		this.isLob = isLob;
	}
}
