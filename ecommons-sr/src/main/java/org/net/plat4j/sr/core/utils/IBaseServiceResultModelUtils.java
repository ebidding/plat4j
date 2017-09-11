package org.net.plat4j.sr.core.utils;

import java.util.List;

import net.plat4j.core.pageTools.PageTools;

import org.net.plat4j.sr.core.base.BaseServiceResultModel;

@SuppressWarnings({"rawtypes"})
public interface IBaseServiceResultModelUtils {
	public void setPage(BaseServiceResultModel srModel,PageTools pageTools);
	public Object[] toArray(List list,Class cls);
}
