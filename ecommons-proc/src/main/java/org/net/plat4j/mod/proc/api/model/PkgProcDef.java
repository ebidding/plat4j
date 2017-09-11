/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-05-19 10:45:36
	
	Revision History:
	Version         Date               					Author			Comments
	1.0         		2015-05-19 10:45:36		yujie				Create file
=========================================================================
*/

package org.net.plat4j.mod.proc.api.model;

/**
 * @author yujie
 *
 */
public class PkgProcDef {
	private PkgProc pkgProc;
	private ProcDef procDef;
	
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
	 * @return the procDef
	 */
	public ProcDef getProcDef() {
		return procDef;
	}
	/**
	 * @param procDef the procDef to set
	 */
	public void setProcDef(ProcDef procDef) {
		this.procDef = procDef;
	}
}
