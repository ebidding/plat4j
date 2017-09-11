package org.net.plat4j.sr.core.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import net.plat4j.core.spring.BeanFactory;

import org.net.plat4j.common.workflow.IWorkflowUtil;
import org.net.plat4j.sr.core.base.session.BaseUserSession;
import org.net.plat4j.sr.core.utils.IBaseServiceParamModelUtils;
import org.net.plat4j.sr.core.utils.IBaseServiceUtils;
import org.net.plat4j.sr.core.utils.LogHelper;
import org.net.plat4j.sr.core.utils.MessageResourceUtils;
import org.net.plat4j.sr.core.utils.impl.BaseServiceParamModelUtils;
import net.sf.json.JSONObject;
/**
 * <h1>Struts-control层的基类:只处理数据转换,或者页面跳转判断,不处理任何业务逻辑.</h1><BR>
 * <strong>DEMO:</strong>{@link org.net.plat4j.demo.company2.action.Company2Action}<BR>
 * <Strong>方法编写规范</Strong><ol>
 * <li>方法名:强动词+名词</li>
 * <li>输入参数:org.net.plat4j.sr.core.base.BaseControllerContext</li>
 * <li>输出参数:org.net.plat4j.sr.core.base.BaseControllerForward</li>
 * 格式如下:public BaseControllerForward queryCompany(BaseControllerContext context)
 * </ol>
 * <Strong>方法内实现编写规范:</Strong><ol>
 * <li>必须调用一个service层方法,且service方法与action名同名</li>
 * <li>方法实现包含init,business和return的方法</li>
 * 格式如下:
 * <pre>
 *   // INIT
 *   //assertTokenValid(context);

		*   DeleteCompanySpModel spModel = this.getSpModel(context, *   DeleteCompanySpModel.class);
 *   //fillPageWithParameter(spModel, context.getRequest());
 *   // BUSSINESS
 *   BaseServiceResultModel srModel = (BaseServiceResultModel) invokeService(
 *   	"ICompany2Service", ICompany2Service.class, "deleteCompany", spModel,DeleteCompanySpModel.class);
 *   // RETURN
 *   //srModel = deleteCompanyPage(context).fillSrModel(srModel);	
 *   //form.clear();
 *   //return context.redirect("deleteCompany",null,spModel, srModel);
 *   return context.closeWindow(spModel,srModel);
 * </pre>  
 * </ol>
 * @author chenshiming
 */
public abstract class BaseController {
	protected LogHelper logger = new LogHelper(getClass());
	@RequestMapping("/*")
	public String execute(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
		BaseControllerContext actionContext = new BaseControllerContext();
		
		actionContext.setRequest(request);
		actionContext.setResponse(response);
		actionContext.setServletContext(request.getSession().getServletContext());
		
		Long startTime = System.currentTimeMillis();
		BaseControllerForward actionForward = execute(actionContext);
		Long useTime = System.currentTimeMillis() - startTime;
		if(useTime > 1000){
			logger.info("=======" + actionContext.getActionName() + " " + actionContext.getMethodName() + " 时间： " + useTime/1000.0 + " s");
		}
		logger.saveUserOperationLog(useTime.toString(),request);
		if(actionForward != null){//普通跳转
			request.setAttribute("spModel", actionForward.getSpModel());
			request.setAttribute("srModel", actionForward.getSrModel());
			model.addAttribute("spModel", actionForward.getSpModel());
			model.addAttribute("srModel", actionForward.getSrModel());
			return actionForward.getResult();
		}else{//ajax跳转
			return null;
		}
	}
	
	protected abstract BaseControllerForward execute(BaseControllerContext actionContext) throws Exception;
	
	/**
	 * 获取语言包信息
	 */
	public String getActionMessage(HttpServletRequest request,String bundle,String pattern,String[] args){
		logger.info("getActionMessage:"+bundle+":"+pattern);
		if(bundle==null || pattern==null){
			return "";
		}
		try{
			return MessageResourceUtils.getMessage(request.getLocale(), bundle, pattern, args);
		}catch(Exception e){
			logger.info("Not Found ActionMessage"+bundle+":"+pattern+"/"+e.getMessage());
			return "";
		}
	}	
	/**
	 * 分页用
	 */
	public void fillPageWithParameter(BaseServiceParamModel spModel,HttpServletRequest request ){
		IBaseServiceParamModelUtils baseServiceParamModelUtils = BaseServiceParamModelUtils.getInstance();// BeanFactory.getBean("IBaseServiceParamModelUtils");
		baseServiceParamModelUtils.fillPageWithParameter(spModel, request);
	}
	/**
	 * 抓取审批内容文件
	 */
	public void createWorkFlowViewFile(String instanceId,HttpServletRequest request ){
		if(StringUtils.isEmpty(instanceId)) return;
		IWorkflowUtil WorkflowUtil = BeanFactory.getBean("IWorkflowUtil");
		WorkflowUtil.createWorkFlowViewFile(instanceId,request);	
		return ;
	}
	
	/**
	 * <h1>调用service的方法,代替Bean.findServcie调用方式</h1>
	 * @param serviceBean 容器中注册的beanName
	 * @param serviceClass bean的class
	 * @param methodName 方法名
	 * @param spModel 输入参数
	 * @return 返回srModel,可强制转为xxxSrModel
	 */	
	public BaseServiceResultModel invokeService(String serviceBean,Class serviceClass,String methodName,BaseServiceParamModel spModel) {
		IBaseServiceUtils baseServiceUtils = BeanFactory.getBean("IBaseServiceUtils");
		BaseUserSession session = new BaseUserSession(spModel.getRequest().getSession());
		return baseServiceUtils.serviceByView(serviceBean, serviceClass, methodName, spModel, session);	
	}
	
	/**
	 * <h1>调用service的方法,代替Bean.findServcie调用方式</h1>
	 * @param serviceBean 容器中注册的beanName
	 * @param serviceClass bean的class
	 * @param methodName 方法名
	 * @param spModel 输入参数
	 * @return 返回srModel,可强制转为xxxSrModel
	 */	
	public BaseServiceResultModel invokeService(String serviceBean,Class serviceClass,String methodName,BaseServiceParamModel spModel,BaseUserSession session) {
		IBaseServiceUtils baseServiceUtils = BeanFactory.getBean("IBaseServiceUtils");
		spModel.setSession(session.getSession());
		return baseServiceUtils.serviceByView(serviceBean, serviceClass, methodName, spModel,session);	
	}

	
	/**
	 * <h1>获取参数信息,通过v(userName#String)的格式的类型</h1>
	 * @param context
	 * @return
	 */
	public <T extends BaseServiceParamModel> T getSpModel(BaseControllerContext context, Class<T> spModelClass) {
		return BaseServiceParamModelUtils.getInstance().getFormParameters(context.getRequest(), spModelClass);
	}
	
	/**
	 * <h1>防止重复提交(通过Struts的token实现)</h1>
	 * @param BaseControllerContext
	 */
	public void assertTokenValid(BaseControllerContext BaseControllerContext) {
	}	
	/**
	 * <h1>根绝request的v_json获取spModel.</h1>
	 * @param request j2ee的request
	 * @param cls 示例的class
	 * @return json的数据bean
	 * @since v1.5.1
	 */
	public Object getSpModelByJsonRequest(HttpServletRequest request,Class cls){
		String spModelJson = request.getParameter("v_json");
		System.out.println(spModelJson);
		JSONObject jsonObject = JSONObject.fromObject(spModelJson);
		return JSONObject.toBean(jsonObject, cls);
	}
}
