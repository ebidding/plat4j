package org.net.plat4j.sr.core;

import org.net.plat4j.sr.core.base.BaseException;
import org.net.plat4j.sr.core.base.BaseServiceParamModel;
import org.net.plat4j.sr.core.base.BaseServiceResultModel;
import org.net.plat4j.sr.core.base.IBaseService;
import org.net.plat4j.sr.core.utils.IBaseServiceUtils;
import org.net.plat4j.sr.core.utils.ITextBeanUtils;

import net.plat4j.core.spring.BeanFactory;

/**
 * 提供给外部的接口
 * @author chenshiming
 *
 */
@SuppressWarnings({"rawtypes"})
public final class SrHelper {
/*	
	public BaseServiceResultModel invokeService(String serviceBean,Class serviceClass,String methodName,BaseServiceParamModel spModel,Class srModelClass) throws Exception{
		BaseTimeMonitor monitor = new BaseTimeMonitor("invokeService#"+serviceBean+"#"+methodName);
		IBaseServiceUtils baseServiceUtils = BeanFactory.getBean("IBaseServiceUtils");
		BaseServiceResultModel srModel = null;
		try {
			//Action需要用到把BaseExcpetionException强制转为srModel,其他Exception直接页面报错
			srModel = baseServiceUtils.service(serviceBean, serviceClass, methodName, spModel);		
		} catch (BaseSrModelException e) {
			e.printStackTrace();
			srModel = (BaseServiceResultModel)srModelClass.newInstance();	
			srModel.setSrSysProcessModel(e.getSrModel().getSrSysProcessModel());
			srModel.setSrUserProcessModel(e.getSrModel().getSrUserProcessModel());
		} finally{
			monitor.print();
		}
		return srModel;
	}*/
	
	
	/**
	 * @param serviceBean
	 * @param serviceClass
	 * @param methodName
	 * @param spModel
	 * @return
	 * @throws BaseException
	 */
	public static BaseServiceResultModel invokeService(String serviceBean,Class serviceClass,String methodName,BaseServiceParamModel spModel) throws BaseException{
		IBaseServiceUtils baseServiceUtils = BeanFactory.getBean("IBaseServiceUtils");
		return baseServiceUtils.serviceByView(serviceBean, serviceClass, methodName, spModel);	
	}	
	
 
	public static String invokeServiceByString(String serviceBean,String methodName,String spModelXml){
		ITextBeanUtils textBeanUtils = BeanFactory.getBean("ITextBeanUtils");
		BaseServiceParamModel spModel = (BaseServiceParamModel)textBeanUtils.getBeanFromText(spModelXml);
		BaseServiceResultModel srModel = invokeService(serviceBean,IBaseService.class,methodName,spModel);
		String ret = textBeanUtils.getTextFromBean(srModel);
		return ret;
	}	
	
	
	
}
