/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-19 13:47:45
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-19 13:47:45		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;

import java.util.List;

import cn.com.ebidding.web.model.BidPackage;

/**
 * @author yujie
 *
 */
public class PkgNodesInfo {
	private PkgProc pkgProc;
	private BidPackage pkgData;
	private List<PkgFieldScope> pkgFieldsScope;
	
	/**
	 * @return the pkgProc
	 */
	public PkgProc getPkgProc() {
		return pkgProc;
	}
	/**
	 * @param pkgProc the pkgProc to set
	 */
	public void setPkgProc(PkgProc pkgProc) {
		this.pkgProc = pkgProc;
	}
	/**
	 * @return the pkgData
	 */
	public BidPackage getPkgData() {
		return pkgData;
	}
	/**
	 * @param pkgData the pkgData to set
	 */
	public void setPkgData(BidPackage pkgData) {
		this.pkgData = pkgData;
	}
	/**
	 * @return the pkgFieldsScope
	 */
	public List<PkgFieldScope> getPkgFieldsScope() {
		return pkgFieldsScope;
	}
	/**
	 * @param pkgFieldsScope the pkgFieldsScope to set
	 */
	public void setPkgFieldsScope(List<PkgFieldScope> pkgFieldsScope) {
		this.pkgFieldsScope = pkgFieldsScope;
	}
}
