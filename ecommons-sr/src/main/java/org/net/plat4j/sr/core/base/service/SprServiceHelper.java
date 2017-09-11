package org.net.plat4j.sr.core.base.service;

import org.net.plat4j.sr.core.base.BaseServiceParamModel;
import org.net.plat4j.sr.core.base.BaseServiceResultModel;
import org.net.plat4j.sr.core.utils.IBaseServiceUtils;
import org.net.plat4j.sr.core.utils.LogHelper;

import net.plat4j.core.spring.BeanFactory;
/**
 * Service的调用的辅助类
 * @author chenshiming
 *
 */
@SuppressWarnings({"rawtypes"})
public class SprServiceHelper {
	protected static LogHelper logger = new LogHelper(SprServiceHelper.class); 
	public static BaseServiceResultModel invokeServiceByView(String serviceBean,Class serviceClass,String methodName,BaseServiceParamModel spModel,Class srModelClass){
		IBaseServiceUtils baseServiceUtils = BeanFactory.getBean("IBaseServiceUtils");
		return baseServiceUtils.serviceByView(serviceBean, serviceClass, methodName, spModel);
	}
	
	public static BaseServiceResultModel invokeService(String serviceBean,Class serviceClass,String methodName,BaseServiceParamModel spModel,Class srModelClass){
		IBaseServiceUtils baseServiceUtils = BeanFactory.getBean("IBaseServiceUtils");
		return baseServiceUtils.service(serviceBean, serviceClass, methodName, spModel);
	}	
 
	
}
