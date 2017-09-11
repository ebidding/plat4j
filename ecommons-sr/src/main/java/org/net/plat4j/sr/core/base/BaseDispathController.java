/*
 * Created on Mar 12, 2004
 *
 * (c) 2004, Mark Eagle, meagle@meagle.com
 * relased under terms of the GNU public license 
 * http://www.gnu.org/licenses/licenses.html#TOCGPL
 */
package org.net.plat4j.sr.core.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.net.plat4j.common.datarightutils.DataRightUtils;
import org.net.plat4j.common.utils.CacheUtils;
import org.net.plat4j.common.utils.ShiroUtils;
import org.net.plat4j.sr.core.annotation.EbsDataRight;
import org.net.plat4j.sr.core.annotation.EbsRequestMethod;
import org.net.plat4j.sr.core.annotation.EbsToken;
import org.net.plat4j.sr.core.utils.MessageResourceUtils;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BaseDispathController extends BaseController {
	protected static boolean INSERT_USER_ACCESS_LOG = false;
	protected static String FORWARD_MESSAGE = "forwardMessage";
	protected static String REQUEST_MESSAGE = "message";
	protected static String REQUEST_URL = "url";
	protected static String REQUEST_SIDE = "side";
	protected Map methods = new ConcurrentHashMap<>();
	protected Class clazz = this.getClass();
	protected String actionName = this.getClass().getName();
	protected Class[] paramTypes = { BaseControllerContext.class };
	private static String CACHE_NAME = "ebsTokenCache";
	/** jsp路径后缀 */
	public static final String JSP_SUFFIX = ".jsp";
	protected BaseControllerForward execute(BaseControllerContext actionContext)
			throws Exception {
		HttpServletRequest request = actionContext.getRequest();
		if(logger.isInfoEnabled()) {
			Enumeration en = request.getParameterNames();
			StringBuilder sb = new StringBuilder();
			sb.append("request uri :").append(request.getRequestURI()).append("\r\n");
			sb.append("Post Infomation:\r\n");
			while(en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String value = request.getParameter(key);
				sb.append("\t").append(key).append("=").append(value)
						.append("\r\n");
			}
			logger.info(sb.toString());
			logger.saveUserOperationLog(request);
		}
		
		// Get the parameter. This could be overridden in subclasses.
		String methodParam = "method";
		// Get the method's name. This could be overridden in subclasses.
		String methodName = getMethodName(request, methodParam);

		// Make sure we have a valid method name to call.
		// This may be null if the user hacks the query string.
		if(methodName == null) {
			return this.unspecified(request, methodParam);
		}

		// Prevent recursive calls
		if("execute".equals(methodName) || "perform".equals(methodName)) {
			String message = "Recursively call method \"" + methodName + "\".";
			logger.error(message);
			throw new ServletException(message);
		}

		/* inserUserAccessLog */
		if(INSERT_USER_ACCESS_LOG) {
		}

		// Invoke the named method, and return the result
		return dispatchMethod(actionContext, methodName);
	}

	protected String getMethodName(HttpServletRequest request,
			String methodParam) throws Exception {
		// Identify the method name to be dispatched to.
		// dispatchMethod() will call unspecified() if name is null
		String methodName = request.getParameter(methodParam);
		if(StringUtils.isNotBlank(methodName)){
			return methodName;
		}
		Pattern pattern = Pattern.compile("/(\\w+)\\.htm");
		String servletPath = request.getServletPath();
		Matcher m = pattern.matcher(servletPath);
		if(m.find()){
			methodName = m.group(1);
			return methodName;
		}
		return null;
		
	}

	protected BaseControllerForward unspecified(HttpServletRequest request,
			String methodParam) throws Exception {
		String message = String.format(
				"The method name for action \"{0}\" is not specified,"
						+ " it should be specified by parameter \"{1}\"",
				this.actionName, methodParam);
		logger.error(message);
		throw new ServletException(message);
	}

	protected BaseControllerForward dispatchMethod(BaseControllerContext actionContext,
			String methodName) throws Exception {
		// added by Jay Yu@201410102254 to support dynamic method code
		String[] ns = methodName.split("@");
		String dynamicMethodCode = null;
		if(ns.length > 1) {
			methodName = ns[0];
			dynamicMethodCode = ns[1];
		}
		// end of added by Jay Yu@201410102254

		// Identify the method object to be dispatched to
		Method method = null;
		try {
			method = getMethod(methodName);
		} catch (NoSuchMethodException e) {
			String message = String.format(
					"No method with name \"{0}\" found in action \"{1}\"",
					methodName, this.actionName);
			logger.error(message, e);
			throw e;
		}
		
		BaseControllerForward forward = null;
		//判断方法上是否有@EbsRequestMethod注解，用于验证该请求是否符合请求注解要求，防止非法请求
		if(method.isAnnotationPresent(EbsRequestMethod.class)){
			boolean isRequiredMethod = checkRequestMethod(method, actionContext.getRequest());
			if(! isRequiredMethod){
				String errorMessage = MessageResourceUtils.tryGetMessage(actionContext.getRequest(), "resource",
						"token.error.message");
				return this.promptErrorMessage(actionContext, "/tokenError.jsp", errorMessage);
			}
		}
		
		//判断方法上是否有@EbsToken注解，用于验证token,防止表单重复提交
		if(method.isAnnotationPresent(EbsToken.class)){
			//判断token是否相同
			boolean isSameToken = this.checkEbsToken(actionContext.getRequest());
			if(! isSameToken){
				String errorMessage = MessageResourceUtils.tryGetMessage(actionContext.getRequest(), "resource",
						"token.error.message");
				return this.promptErrorMessage(actionContext, "/tokenError.jsp", errorMessage);
			}
		};
		
		if(method.isAnnotationPresent(EbsDataRight.class)){
			boolean hasRight = checkDataRight(method, actionContext.getRequest());
			if(! hasRight){
				String errorMessage = MessageResourceUtils.tryGetMessage(actionContext.getRequest(), "resource",
						"dataright.error.message");
				return this.promptErrorMessage(actionContext, "/datarightwarning.jsp", errorMessage);
			}
			
		}
		
		// added by Jay Yu@201410102254 to support dynamic method code
		actionContext.setDynaMethodCode(dynamicMethodCode);
		// end of added by Jay Yu@201410102254
		try {
			actionContext.setActionName(this.actionName);
			actionContext.setMethodName(methodName);
			Object[] args = { actionContext };
			forward = (BaseControllerForward) method.invoke(this, args);
		} catch (ClassCastException e) {
			logger.error(e);
			throw e;
		} catch (IllegalAccessException e) {
			logger.error(e);
			throw e;
		} catch (InvocationTargetException e) {
			// Rethrow the target exception if possible so that the
			// exception handling machinery can deal with it
			Throwable t = e.getTargetException();
			if(t instanceof Exception) {
				throw ((Exception) t);
			} else {
				logger.error(e);
				throw new ServletException(t);
			}
		}
		/*
		 * catch (Exception e){ saveErrors(actionContext, e); }
		 */
//		if(response.isCommitted()) {
//			return null;
//		}
		// Return the returned ActionForward instance
		return (forward);
	}
	
	private boolean checkDataRight(Method method, HttpServletRequest request) {
		// TODO Auto-generated method stub
		EbsDataRight dataRoght = method.getDeclaredAnnotation(EbsDataRight.class);
		String businessCode = dataRoght.businessCode();
		if(StringUtils.isBlank(businessCode)){
			logger.error(method.getName()+"方法上【@EbsDataRight】注解需要设置businessCode！");
			return false;
		}
		String idVal = request.getParameter(dataRoght.param());
		if(StringUtils.isBlank(idVal)){
			logger.error("表单中"+dataRoght.param()+"：【参数值不能为空！】");
			return false;
		}
		return DataRightUtils.hasDataRight(businessCode, idVal);
	}

	/**
	 * 根据请求的方法，判断是否与注解中要求的方法一致
	 * @param method
	 * @param request
	 * @return 一致返回true,不一致返回false
	 */
	private boolean checkRequestMethod(Method method,HttpServletRequest request){
		EbsRequestMethod ebsRequestMethod = method.getDeclaredAnnotation(EbsRequestMethod.class);
		RequestMethod[] requireMethods = ebsRequestMethod.value();
		String requestMethod = request.getMethod();
		for (RequestMethod requireMethod : requireMethods) {
			if(requestMethod.equalsIgnoreCase(requireMethod.name())){
				return true;
			}
		}
		return false;
	}
	
	// 判断request请求方式，根据请求方式返回不同提示信息。
	private BaseControllerForward promptErrorMessage(BaseControllerContext actionContext,String result,String message) throws IOException{
		BaseControllerForward forward = null;
		if(isAjaxRequest(actionContext.getRequest())){
			actionContext.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = actionContext.getResponse().getWriter();	
			out.print("{\"isSuccess\":\"0\",\"message\":\""+message+"\"}");
			return null;
		}else{
			forward = new BaseControllerForward();
			forward.setResult(result);
			return forward;
		}
	}
	
	//判断是否是ajax请求
	private boolean isAjaxRequest(ServletRequest request){
		HttpServletRequest req = (HttpServletRequest) request;
		return "XMLHttpRequest".equalsIgnoreCase(req.getHeader("X-Requested-With"));
	}
	
	protected Method getMethod(String name) throws NoSuchMethodException {
		Method method = (Method) methods.get(name);
		if(method == null) {
			if(logger.isDebugEnabled())
				logger.debug("{method:" + name + ",types:" + paramTypes[0]
						+ "}");
			method = clazz.getMethod(name, paramTypes);
			methods.put(name, method);
		}
		return method;
	}

	// protected ActionForward saveErrors(BActionContext context, Exception e){
	// Throwable t = e;
	// while (t.getCause() != null)
	// t = t.getCause();
	// ActionMessages errors = new ActionMessages();
	// errors.add("errors", new ActionMessage("errors.defultErrors",
	// t.getMessage()));
	// saveErrors(context.getRequest(), errors);
	// return context.findForward("DefaultErrorPage");
	// }
	
	/**
	 * 判断用户form表单中的token与redis中的token是否保持一致
	 * @param request
	 * @return 一致返回true，不一致返回false
	 */
	private boolean checkEbsToken(HttpServletRequest request){
		String formEbsToken = request.getParameter("ebsToken");
		if(StringUtils.isBlank(formEbsToken)){
			logger.error("表单中token不能为空！");
			return false;
		}
		String userId = CacheUtils.getValue(CACHE_NAME, formEbsToken);
		if(StringUtils.isBlank(userId)){
			logger.error("不能重复提交表单！");
			return false;
		}
		if(ShiroUtils.getUserId().toString().equals(userId)){
			//删除cache中token
			CacheUtils.removeValue(CACHE_NAME, formEbsToken);
			return true;
		}
		logger.error("form中token与系统中不同，不能提交表单！");
		return false;
	}
	
	
}
