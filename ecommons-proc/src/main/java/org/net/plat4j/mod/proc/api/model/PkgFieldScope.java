/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-19 13:50:26
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-19 13:50:26		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;

/**
 * @author yujie
 *
 */
public class PkgFieldScope {
	private Long packageId;
	private String fieldName;
	private Boolean isForProj;
	
	/**
	 * @return the packageId
	 */
	public Long getPackageId() {
		return packageId;
	}
	/**
	 * @param packageId the packageId to set
	 */
	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}
	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @return the isForProj
	 */
	public Boolean getIsForProj() {
		return isForProj;
	}
	/**
	 * @param isForProj the isForProj to set
	 */
	public void setIsForProj(Boolean isForProj) {
		this.isForProj = isForProj;
	}
}
