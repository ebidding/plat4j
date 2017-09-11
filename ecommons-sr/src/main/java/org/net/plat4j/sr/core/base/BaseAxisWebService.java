package org.net.plat4j.sr.core.base;

import java.util.Locale;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;

import org.net.plat4j.sr.core.SrHelper;
import org.net.plat4j.sr.core.base.service.SprServiceHelper;
import org.net.plat4j.sr.core.utils.LogHelper;
import org.net.plat4j.sr.core.utils.MessageResourceUtils;
/**
 * <h1>Webservcie的基础类,提供语言转换和获取客户端IP等功能</h1>
 * @author chenshiming
 *
 */
@SuppressWarnings({"rawtypes"})
public abstract class BaseAxisWebService {

	//protected Log logger = LogFactory.getLog(getClass());
	protected LogHelper logger = new LogHelper(getClass());
	/**
	 * <h1>用于SprUnit</h1>
	 * @param serviceBean
	 * @param methodName
	 * @param spModelXml
	 */
	public String invokeServiceByString(String serviceBean,String methodName,String spModelXml){
		logger.info(getClientIp()+" Call:"+serviceBean+"#"+methodName+":"+spModelXml);
		return SrHelper.invokeServiceByString(serviceBean, methodName, spModelXml);
	}
	
	/**
	 * <h1>获取Request</h1>
	 */
	protected HttpServletRequest getHttpServletRequest() {
		MessageContext mc = null;
		try {
			mc = MessageContext.getCurrentContext();

			if (mc == null)
				throw new Exception("无法获取到MessageContext");
			return (HttpServletRequest) mc
					.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
		} catch (Exception e) {
			throw new BaseException(e);
		}
	}
	/**
	 * <h1>获取调用端IP</h1>
	 */
	protected String getClientIp(){
		return getHttpServletRequest().getRemoteAddr();
	}
	/**
	 * <h1>获取HttpServlet</h1>
	 */
	protected HttpServlet getHttpServletServlet() {
		MessageContext mc = null;
		try {
			mc = MessageContext.getCurrentContext();

			if (mc == null)
				throw new Exception("无法获取到MessageContext");
			return (HttpServlet) mc.getProperty(HTTPConstants.MC_HTTP_SERVLET);
		} catch (Exception e) {
			throw new BaseException(e);
		}
	}
	/**
	 * <h1>获取struts的语言包</h1>
	 */
	protected String getActionMessage(String bundle, String pattern, String[] args) {
		if (bundle == null || pattern == null) {
			return "";
		}
		try {
			Locale locale = getHttpServletRequest().getLocale();
			return MessageResourceUtils.getMessage(locale, bundle, pattern, args);
			
		} catch (Exception e) {
			logger.error("Not Found ActionMessage" + bundle + ":" + pattern
					+ "/" + e.getMessage(), e);
			return "";
		}
	}
	/**
	 * <h1>根据SrModel提取起返回信息的中文描述</h1>
	 */
	protected String getActionMessage(BaseServiceResultModel srModel) {
		if(srModel!=null&&srModel.getSrUserProcessModel()!=null){
			SrUserProcessModel pm = srModel.getSrUserProcessModel();
			return getActionMessage(pm.getResultDescBundle(),pm.getResultDesc(),pm.getResultDescArgs());
		}else{
			return null;
		}
		
	}
	/**
	 * <h1>提供给webservcie control的invokeservcie（view方式)</h1>
	 */
	protected static BaseServiceResultModel invokeService(String serviceBean,
			Class serviceClass, String methodName,
			BaseServiceParamModel spModel, Class srModelClass) {
		return SprServiceHelper.invokeServiceByView(serviceBean, serviceClass,
				methodName, spModel, srModelClass);
	}
}
