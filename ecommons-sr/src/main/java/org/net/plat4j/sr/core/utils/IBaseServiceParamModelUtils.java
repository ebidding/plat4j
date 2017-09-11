package org.net.plat4j.sr.core.utils;

import javax.servlet.http.HttpServletRequest;

import net.plat4j.core.pageTools.PageTools;

import org.net.plat4j.sr.core.base.BaseServiceParamModel;

public interface IBaseServiceParamModelUtils {
	public void fillPageWithParameter(BaseServiceParamModel spModel,HttpServletRequest request );
	public PageTools getPage(BaseServiceParamModel spModel);
}
