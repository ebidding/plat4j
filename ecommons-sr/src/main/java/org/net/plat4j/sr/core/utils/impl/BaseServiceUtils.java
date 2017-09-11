package org.net.plat4j.sr.core.utils.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import net.plat4j.core.spring.BeanFactory;

import org.net.plat4j.common.utils.CacheUtils;
import org.net.plat4j.common.utils.ShiroUtils;
import org.net.plat4j.sr.core.base.BaseException;
import org.net.plat4j.sr.core.base.BaseMessageModel;
import org.net.plat4j.sr.core.base.BaseServiceParamModel;
import org.net.plat4j.sr.core.base.BaseServiceResultModel;
import org.net.plat4j.sr.core.base.BaseSrModelException;
import org.net.plat4j.sr.core.base.BaseTimeMonitor;
import org.net.plat4j.sr.core.base.BaseUserProcess;
import org.net.plat4j.sr.core.base.IBaseService;
import org.net.plat4j.sr.core.base.SrSysProcessModel;
import org.net.plat4j.sr.core.base.SrUserProcessModel;
import org.net.plat4j.sr.core.base.session.BaseUserSession;
import org.net.plat4j.sr.core.utils.IBaseRandamUtils;
import org.net.plat4j.sr.core.utils.IBaseServiceUtils;
import org.net.plat4j.sr.core.utils.ITextBeanUtils;
import org.net.plat4j.sr.core.utils.LogHelper;

@SuppressWarnings({"rawtypes"})
public class BaseServiceUtils implements IBaseServiceUtils{
	protected LogHelper logger = new LogHelper(getClass());
	private String appClusterName;
	private static String CACHE_NAME = "ebsTokenCache";
	/**
	 * 查找servce的method参数class,用于底层找上层的method spModel(不支持重写的方法)
	 * @param serviceBean
	 * @param serviceClass
	 * @param methodName
	 * @return
	 * @throws BaseSrModelException
	 */
	public Class getSpModelClass(String serviceBean,
			Class serviceClass, String methodName) throws BaseSrModelException {
		IBaseService baseService = BeanFactory.getBean(serviceBean, IBaseService.class);
		Method[] methods = baseService.getClass().getMethods();
		for(int i=0,j=methods.length;i<j;i++){
			if(methods[i].getName().equals(methodName) && methods[i].getParameterTypes().length==1){
				return methods[i].getParameterTypes()[0];
			}
		}
		throw new BaseSrModelException(new BaseMessageModel("comon",null,new String[]{methodName+" not found in "+serviceBean}));
	}
	/**
	 * 查找servce的method返回结果class,用于底层找上层的method spModel(不支持重写的方法)
	 * @param serviceBean
	 * @param serviceClass
	 * @param methodName
	 * @return
	 * @throws BaseSrModelException
	 */
	public Class getSrModelClass(String serviceBean,
			Class serviceClass, String methodName) throws BaseSrModelException {
		IBaseService baseService = BeanFactory.getBean(serviceBean, IBaseService.class);
		Method[] methods = baseService.getClass().getMethods();
		for(int i=0,j=methods.length;i<j;i++){
			if(methods[i].getName().equals(methodName) && methods[i].getParameterTypes().length==1){
				return methods[i].getReturnType();
			}
		}
		throw new BaseSrModelException(new BaseMessageModel("comon",null,new String[]{methodName+" not found in "+serviceBean}));
	}
	/**
	/* (non-Javadoc)
	 * @see org.net.plat4j.sr.core.utils.IBaseServiceUtils#serviceByView(java.lang.String, java.lang.Class, java.lang.String, org.net.plat4j.sr.core.base.BaseServiceParamModel)
	 */
	public BaseServiceResultModel serviceByView(String serviceBean,
			Class serviceClass, String methodName, BaseServiceParamModel spModel)   {
		return serviceByView(serviceBean,serviceClass,methodName,spModel,null);
	}
	/**
	 * servcie调用（捕获exception转为SrModel）
	 * @param serviceBean service的Bean Id
	 * @param serviceClass service的class
	 * @param methodName 调用方法名
	 * @param spModel sp输入参数
	 * @param session 用户信息BaseUserSession
	 * @return
	 */
	public BaseServiceResultModel serviceByView(String serviceBean,
			Class serviceClass, String methodName, BaseServiceParamModel spModel,BaseUserSession session)   {
		BaseServiceResultModel srModel = null;
		try {

			//Action需要用到把BaseExcpetionException强制转为srModel,其他Exception直接页面报错
			srModel = service(serviceBean, serviceClass, methodName, spModel);		
			srModel.setIsSuccess("1");
		} catch (BaseSrModelException e) {
			logger.error(e);
			try {
				Class srModelClass = getSrModelClass(serviceBean, serviceClass, methodName);
				srModel = (BaseServiceResultModel)srModelClass.newInstance();
				srModel.setSrSysProcessModel(e.getSrModel().getSrSysProcessModel());
				srModel.setSrUserProcessModel(e.getSrModel().getSrUserProcessModel());	
				srModel.setIsSuccess("0");
				srModel.setMessage(e.getSrModel().getMessage());
				srModel.setErrors(e.getSrModel().getErrors());
				srModel.setMessage(e.getMessage());
				this.recoverEbsToken(spModel.getRequest());
			} catch (Exception e2) {
				//业务代码级别不会到这里
				logger.error(e2);
				throw new BaseException(e2);
			}
		} catch (Exception e) { 
			logger.error(e);
			try {
				Class srModelClass = getSrModelClass(serviceBean, serviceClass, methodName);
				srModel = (BaseServiceResultModel)srModelClass.newInstance();
				srModel.setIsSuccess("0");
				srModel.setMessage(e.getMessage());
				this.recoverEbsToken(spModel.getRequest());
			} catch (Exception e2) {
				//业务代码级别不会到这里
				logger.error(e2);
				throw new BaseException(e2);
			}
		}
		return srModel;
	 
	}
	
	/**
	 * service方法执行失败后，用于恢复session中的token
	 * @param request
	 * @param session
	 */
	private void recoverEbsToken(HttpServletRequest request){
		String formEbsToken = request.getParameter("ebsToken");
		if(StringUtils.isNotBlank(formEbsToken)){
			CacheUtils.cacheValue(CACHE_NAME, formEbsToken, ShiroUtils.getUserId().toString());
		}
	}
	

	public BaseServiceResultModel service(String serviceBean,
			Class serviceClass, String methodName, BaseServiceParamModel spModel) throws BaseSrModelException {
		BaseServiceResultModel srModel = null;
		IBaseRandamUtils baseRandamUtils = BeanFactory.getBean("IBaseRandamUtils");
		// 设置运行信息
		SrSysProcessModel srProcessModel = new SrSysProcessModel();
		srProcessModel.setServiceProcessId(baseRandamUtils.getUniqueRandamString());
		srProcessModel.setServiceName(serviceBean);
		srProcessModel.setServiceClassName(serviceClass.getName());
		srProcessModel.setMethodName(methodName);
		srProcessModel.setServerBeginTimeMillis(new Double(System
				.currentTimeMillis()));
		
		

		Throwable throwable = null;

		try {
			//调用业务类
 
			IBaseService baseService = BeanFactory.getBean(serviceBean, IBaseService.class);
			
			Method method = baseService.getClass().getMethod(methodName,
					new Class[] { spModel.getClass()});
			srModel = (BaseServiceResultModel) method.invoke(baseService,
					new Object[] { spModel });
			//logger.info("====================");		
			if(srModel==null){ 
				//如果是返回为null,则认为是todo(auth和validate等外部切面式的是可以通过返回null作为todo)
				Class srModelClass = getSrModelClass(serviceBean, serviceClass, methodName);
				srModel = (BaseServiceResultModel)srModelClass.newInstance();
				BaseUserProcess process = new BaseUserProcess();
				process.setInfoResult(srModel, null);
				srModel.setSrSysProcessModel(srProcessModel);
				//throw new BaseException("BaseServiceResultModel is null");
			} 
			if(srModel.getSrUserProcessModel()==null){
				throw new BaseException("SrUserProcessModel is null");
			}	
			if(srModel.getSrUserProcessModel().getResultCode()==null){
				throw new BaseException("SrUserProcessModel ResultCode is null");
			}	

		} catch (InvocationTargetException invocationTargetException) {
			Throwable t = invocationTargetException.getCause();
			if (t != null) {
				throwable = t;
			} else {
				throwable = invocationTargetException;
			}
		}catch (BaseSrModelException e) {
			throwable = e ;
		}catch (BaseException e) {
			throwable = e ;
		}catch (Exception e) {
			throwable = e ; 
		}catch (Error e) {
			throwable = e ;
		}finally{
			srProcessModel.setServerEndTimeMillis(new Double(System
					.currentTimeMillis()));			
			if(throwable!=null){
				//所有的返回都是BaseSrModelException
				if (throwable instanceof BaseSrModelException) {
					BaseSrModelException srModelException = (BaseSrModelException) throwable;
					srModel = srModelException.getSrModel();
					srModel.setSrSysProcessModel(srProcessModel);
					throw srModelException;	
				}else{			
					srModel = new BaseServiceResultModel();
					BaseUserProcess process = new BaseUserProcess();
					process.setExceptionResult(srModel,throwable); 
					srModel.setSrSysProcessModel(srProcessModel);
					srModel.setMessage("操作失败，请联系管理员!");
					logger.error(throwable);
					throw new BaseSrModelException(srModel,throwable);
				}
			}else{
				srModel.setSrSysProcessModel(srProcessModel);
			}
			//StringBuffer sb = printServiceInfoLog(spModel,srModel,throwable);
			//logger.info(sb.toString());  
		}
		return srModel;
	}
	
	public StringBuffer printServiceInfoLog(BaseServiceParamModel spModel,BaseServiceResultModel srModel,Throwable throwable){
		StringBuffer sb = new StringBuffer();
		if(srModel!=null){
			SrSysProcessModel process = srModel.getSrSysProcessModel();
			
			SrUserProcessModel userProcessModel = srModel.getSrUserProcessModel();
			if(process!=null){
				
				double sep = process.getServerEndTimeMillis().doubleValue() - process.getServerBeginTimeMillis().doubleValue();
				
				sb.append("Invoke Service:\r\n");
				sb.append("\tBSID-"+process.getServiceProcessId()+":").append(process.getServiceName()).append(".").append(process.getMethodName());
				sb.append("(").append(sep).append("ms)\t");
				if(userProcessModel.getResultCode()==null){
					sb.append("userProcessModel.getResultCode:null");
				}if(userProcessModel.getResultCode().equalsIgnoreCase(SrUserProcessModel.RESULT_CODE_INFO)){	
					sb.append("INFO:");
				}else if(userProcessModel.getResultCode().equalsIgnoreCase(SrUserProcessModel.RESULT_CODE_WARN)){
					sb.append("WARN:");
				}else if(userProcessModel.getResultCode().equalsIgnoreCase(SrUserProcessModel.RESULT_CODE_FAIL)){
					sb.append("FAIL:");
				}else if(userProcessModel.getResultCode().equalsIgnoreCase(SrUserProcessModel.RESULT_CODE_EXCEPTION)){
					sb.append("EXCEPTION:");
				}
				sb.append(userProcessModel.getResultDesc());
				sb.append("\r\n");
				if(userProcessModel.getResultCode().equalsIgnoreCase(SrUserProcessModel.RESULT_CODE_EXCEPTION)){
					sb.append("EXCEPTION Message:"+userProcessModel.getExceptionClass()+"("+userProcessModel.getExceptionMessage()+")");
				}
				ITextBeanUtils textBeanUtils = BeanFactory.getBean("ITextBeanUtils");
				
				sb.append("\tParam:"+spModel);
				sb.append("\r\n");
				sb.append(textBeanUtils.getTextFromBean(spModel));
				sb.append("\r\n");
				sb.append("\tReturn:"+srModel.toString());	
				sb.append("\r\n");		
				sb.append(textBeanUtils.getTextFromBean(srModel));
				sb.append("\r\n");
			}
		}
		
		
		
	/*	if(srModel.getBaseInfoMessageModels()!=null && srModel.getBaseInfoMessageModels().length>0){
			sb.append("\tInfo Message:\r\n");
			BaseMessageModel[] messageModels = srModel.getBaseInfoMessageModels();
			for(int i=0,j=messageModels.length;i<j;i++){
				sb.append("\t\t").append(messageModels[i].getPatternName()).append(":").append(messageModels[i].getArgus());
				sb.append("\r\n");
			}			
		}
		if(srModel.getBaseWarnMessageModels()!=null && srModel.getBaseWarnMessageModels().length>0){
			sb.append("\tWarn Message:\r\n");
			BaseMessageModel[] messageModels = srModel.getBaseWarnMessageModels();
			for(int i=0,j=messageModels.length;i<j;i++){
				sb.append("\t\t").append(messageModels[i].getPatternName()).append(":").append(messageModels[i].getArgus());
				sb.append("\r\n");
			}			
		}
		if(srModel.getBaseFailMessageModels()!=null && srModel.getBaseFailMessageModels().length>0){
			sb.append("\tFail Message:\r\n");
			BaseMessageModel[] messageModels = srModel.getBaseFailMessageModels();
			for(int i=0,j=messageModels.length;i<j;i++){
				sb.append("\t\t").append(messageModels[i].getPatternName()).append(":").append(messageModels[i].getArgus());
				sb.append("\r\n");
			}			
		}
		if(srModel.getBaseExceptionMessageModels()!=null && srModel.getBaseExceptionMessageModels().length>0){
			sb.append("\tException Message:\r\n");
			BaseMessageModel[] messageModels = srModel.getBaseExceptionMessageModels();
			for(int i=0,j=messageModels.length;i<j;i++){
				sb.append("\t\t").append(messageModels[i].getPatternName()).append(":").append(messageModels[i].getArgus());
				sb.append("\r\n");
			}			
		}*/	 
		
		
		BaseTimeMonitor monitor = new BaseTimeMonitor("textBeanUtils#Bean2Text");
		monitor.beginMonitor();
		//sb.append(textBeanUtils.getTextFromBean(textBeanUtils.getBeanFromText(textBeanUtils.getTextFromBean(srModel))));
		monitor.print();
		 
		if(throwable!=null){
			logger.error(throwable);
		}
		return sb;
	}
	
	public String getAppClusterName() {
		return appClusterName;
	}

	public void setAppClusterName(String appClusterName) {
		this.appClusterName = appClusterName;
	}
	
	public static void main(String[] args) throws BaseException{
		//QueryCompanySpModel spModel = new QueryCompanySpModel();
		//new BaseServiceUtils().service("AbcService", String.class, "process", spModel);
	}
}
