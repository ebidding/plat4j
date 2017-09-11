/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-18 20:28:45
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-05-18 20:28:45		yujie			Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;
import java.util.List;
import cn.com.ebidding.web.model.BidBidder;
import cn.com.ebidding.web.model.BidMain;
import cn.com.ebidding.web.model.BidPackage;

/**
 * @author yujie
 *
 */
public class PackageContext {
	private BidMain project;
	private BidPackage pkgData;
	private List<BidBidder> pkgBidders;
	
	/**
	 * @return the project
	 */
	public BidMain getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(BidMain project) {
		this.project = project;
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
	 * @return the pkgBidders
	 */
	public List<BidBidder> getPkgBidders() {
		return pkgBidders;
	}
	/**
	 * @param pkgBidders the pkgBidders to set
	 */
	public void setPkgBidders(List<BidBidder> pkgBidders) {
		this.pkgBidders = pkgBidders;
	}
	
}
