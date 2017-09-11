package org.net.plat4j.sr.core.base;

import org.net.plat4j.sr.core.utils.IBaseServiceUtils;
import org.net.plat4j.sr.core.utils.ITextBeanUtils;

import net.plat4j.core.spring.BeanFactory;

@SuppressWarnings({"rawtypes"})
public abstract class BaseXmlWebService {
	public String service(String methodName, String paramXML) {
		ITextBeanUtils textBeanUtils = BeanFactory.getBean("ITextBeanUtils");
		BaseServiceParamModel spModel = (BaseServiceParamModel) textBeanUtils
				.getBeanFromText(paramXML);

		IBaseServiceUtils baseServiceUtils = BeanFactory.getBean("IBaseServiceUtils");
		BaseServiceResultModel srModel = baseServiceUtils.service(
				getServiceBean(), getServiceClass(), methodName,
				spModel);

		return textBeanUtils.getTextFromBean(srModel);
	}
	
	public abstract String getServiceBean();
	public abstract Class getServiceClass();
	 
}
