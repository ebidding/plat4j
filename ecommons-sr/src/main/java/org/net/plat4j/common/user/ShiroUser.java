/*
	Copyright (C) 2014 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2014-07-15 13:28:20
	
	Revision History:
	Version     Date              					Author			Comments
	1.0         	2014-07-15 13:28:20		yujie				Create file
=========================================================================
*/

package org.net.plat4j.common.user;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public class ShiroUser implements Serializable {
	private static final long serialVersionUID = -1373760761780840081L;
	private Long id;
	private String userName; 
	private Long companyId;
	private String companyName;
	private String trueName;
	private String trueNameEn;
	private Long departId;
	private String departName;
	private Long[] roleIds;
	private String mobile;
	private String isAdmin;
	private String dataRight;
	private Long agentId;
	private String userType;
	private Boolean isUserType;
	private List <SysUserAgencyInfo> sysUserAgencyInfos;
	//菜单栏
	private List<UserMenuModel> userMenus;
	private List<UserMenuModel> rootMenus;
	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getCompanyId() {
		
		if(companyId == null) companyId = -1L;
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getTrueNameEn() {
		return trueNameEn;
	}

	public void setTrueNameEn(String trueNameEn) {
		this.trueNameEn = trueNameEn;
	}

	public Long getDepartId() {
		return departId;
	}

	public void setDepartId(Long departId) {
		this.departId = departId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    
	public String getIsAdmin() {
		return isAdmin;
	}
	
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return userName;
	}

	/**
	 * 重载equals,只计算username;
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[]{"userName"});
	}

	/**
	 * 重载equals,只比较userName
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, new String[]{"userName"});
	}


	public String getDataRight() {
		return dataRight;
	}

	public void setDataRight(String dataRight) {
		this.dataRight = dataRight;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	/**
	 * @return the agentId
	 */
	public Long getAgentId() {
		return agentId;
	}

	/**
	 * @param agentId the agentId to set
	 */
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public List<SysUserAgencyInfo> getSysUserAgencyInfos() {
		return sysUserAgencyInfos;
	}

	public void setSysUserAgencyInfos(List<SysUserAgencyInfo> sysUserAgencyInfos) {
		this.sysUserAgencyInfos = sysUserAgencyInfos;
	}

	public List<UserMenuModel> getUserMenus() {
		return userMenus;
	}

	public void setUserMenus(List<UserMenuModel> userMenus) {
		this.userMenus = userMenus;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<UserMenuModel> getRootMenus() {
		return rootMenus;
	}

	public void setRootMenus(List<UserMenuModel> rootMenus) {
		this.rootMenus = rootMenus;
	}

	public Boolean getIsUserType() {
		return isUserType;
	}

	public void setIsUserType(Boolean isUserType) {
		this.isUserType = isUserType;
	}
	
}
