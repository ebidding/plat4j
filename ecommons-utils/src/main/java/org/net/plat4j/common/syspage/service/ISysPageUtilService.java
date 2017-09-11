package org.net.plat4j.common.syspage.service;

import java.util.List;

import org.net.plat4j.common.syspage.model.SysPageModel;

public interface ISysPageUtilService {
	/**
	 * 通过uri和method获取页面信息
	 * @param actionName
	 * @param methodName
	 * @return
	 */
	List<SysPageModel> getPageDesc();
}
