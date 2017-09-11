package org.net.plat4j.sr.core.utils;

import org.net.plat4j.sr.core.base.BaseServiceParamModel;
import org.net.plat4j.sr.core.base.BaseServiceResultModel;
import org.net.plat4j.sr.core.base.BaseSrModelException;
import org.net.plat4j.sr.core.base.session.BaseUserSession;

@SuppressWarnings({"rawtypes"})
public interface IBaseServiceUtils {
	public Class getSpModelClass(String serviceBean,
			Class serviceClass, String methodName) throws BaseSrModelException;
	public Class getSrModelClass(String serviceBean,
			Class serviceClass, String methodName) throws BaseSrModelException;
	public BaseServiceResultModel service(String serviceBean,
			Class serviceClass, String methodName, BaseServiceParamModel spModel)
			throws BaseSrModelException;

	public StringBuffer printServiceInfoLog(BaseServiceParamModel spModel,
			BaseServiceResultModel srModel, Throwable throwable);

	public BaseServiceResultModel serviceByView(String serviceBean,
			Class serviceClass, String methodName, BaseServiceParamModel spModel);
	public BaseServiceResultModel serviceByView(String serviceBean,
			Class serviceClass, String methodName, BaseServiceParamModel spModel,BaseUserSession session);
}
