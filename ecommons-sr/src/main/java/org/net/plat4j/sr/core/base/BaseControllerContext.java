package org.net.plat4j.sr.core.base;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
/**
 * <h1>J2EE的request,response和session等托管类.</h1>
 */
@SuppressWarnings({"rawtypes"})
public class BaseControllerContext extends BControllerContext2 {
	public static final String DEFAULT_RESULT_CLOSE = "/spr/default/close.jsp";
	private final static ObjectMapper objectMapper;
	
	static{
		objectMapper = new ObjectMapper();
	}
	private String actionName;
	private String methodName;
	/**
	 * Added by Jay Yu@201410102248
	 * To support dynamic method code, like method=query@name
	 */
	private String dynaMethodCode;

	/**
	 * <h1>显示返回的结果,提示后自动关闭页面.</h1>
	 * @param spModel
	 * @param srModel
	 * @return BaseControllerForward
	 */
	public BaseControllerForward closeWindow(BaseServiceParamModel spModel, BaseServiceResultModel srModel){
		return new BaseControllerForward(DEFAULT_RESULT_CLOSE, spModel, srModel);
	}
	
	/**
	 * <h1>重定向跳转,前台是通过window.location实现,不传递spModel和srModel数据.</h1>
	 * @param result 跳转forward名
	 * @param getParam Get的参数清单
	 * @param srModel 
	 * @return BaseControllerForward
	 */
	public BaseControllerForward redirect(String url, Map getParam,
			BaseServiceParamModel spModel, BaseServiceResultModel srModel) {
		return null;
//		if(getParam==null){
//			getParam = new HashMap();
//		}
//		this.getResponse().sendRedirect(arg0);
//		ActionForward actionForward = super.findForward(arg0);
//		BaseControllerForward BaseControllerForward = new BaseControllerForward();
//		BeanUtils.copyProperties(actionForward, BaseControllerForward);
// 
//		StringBuffer path = new StringBuffer(BaseControllerForward.getPath());
//
//		String sep = null;
//		if (path.indexOf("?") >= 0) {
//			sep = "&";
//		} else {
//			sep = "?";
//		}
//		// convert attribute srModel's message To get-param message
//		if (srModel != null && srModel.getSrUserProcessModel() != null) {
//			String resultCode = srModel.getSrUserProcessModel().getResultCode();
//			if (resultCode != null && resultCode.trim().length() > 0
//					&& srModel.getSrUserProcessModel().getResultDesc() != null) {
//				getParam.put("SRMODEL_RESULTS", "1");
//				getParam.put("SRMODEL_RESULTS_1", srModel
//						.getSrUserProcessModel().getResultCode());
//				getParam.put("SRMODEL_RESULTS_2", srModel
//						.getSrUserProcessModel().getResultDesc());
//				getParam.put("SRMODEL_RESULTS_2_b", srModel
//						.getSrUserProcessModel().getResultDescBundle());
//				String[] args = srModel.getSrUserProcessModel()
//						.getResultDescArgs();
//				if (args != null) {
//					getParam.put("SRMODEL_RESULTS_3", SrPhp.implode(",", args));
//				}
//			}
//		}
//
//		if (getParam != null) {
//			Iterator i = getParam.keySet().iterator();
//			while (i.hasNext()) {
//				String key = (String) i.next();
//				Object value = getParam.get(key);
//				if (value != null) {
//					path.append(sep).append(URLEncoder.encode(key)).append("=")
//							.append(URLEncoder.encode(value.toString()));
//				}
//				sep = "&";
//			}
//		}
// 
//		BaseControllerForward.setPath(path.toString());
//		BaseControllerForward.setRedirect(true);
//		return BaseControllerForward;
	}

	/**
	 * <h1>定向调转,带spModel和srModel数据.</h1>
	 * @param result 跳转forward名
	 * @param spModel 
	 * @param srModel
	 * @return BaseControllerForward
	 */
	public BaseControllerForward findBaseForward(String result,
			BaseServiceParamModel spModel, BaseServiceResultModel srModel) {
		if (srModel != null
				&& srModel.getSrUserProcessModel() != null
				&& srModel.getSrUserProcessModel().getResultCode().equals(
						SrUserProcessModel.RESULT_CODE_EXCEPTION)) {
			throw new RuntimeException(srModel.getSrUserProcessModel().getResultDescArgs()[0]);
		}

		getRequest().setAttribute("spModel", spModel);
		getRequest().setAttribute("srModel", srModel);
		return new BaseControllerForward(result, spModel, srModel);
}
	/**
	 * <h1>返回ajax的数据.</h1>
	 * @param srModel
	 * @since v1.5.1
	 */
	public BaseControllerForward findAjaxForword(BaseServiceResultModel srModel){		
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = null;		
		try {	
			out = getResponse().getWriter();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setAllowNonStringKeys(true);
			String json = JSONObject.fromObject(srModel, jsonConfig).toString();
			out.println(json);			
		} catch (Exception e) {		
			srModel.setIsSuccess("0");
			srModel.setMessage(e.getMessage());
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setAllowNonStringKeys(true);
			String json = JSONObject.fromObject(srModel, jsonConfig).toString();
			out.println(json);
			///throw new BaseException(e);
		}
		return null;
	}
	
	/**
	 * html5开标大厅返回ajax数据
	 * @param srModel
	 * @return
	 */
	public BaseControllerForward findAjaxForword(Object obj){
		try {
			getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = getResponse().getWriter();
			String json = objectMapper.writeValueAsString(obj);
			out.println(json);
		} catch (Exception e) {
			throw new BaseException(e);
		}
		return null;
	}
	
	public BaseControllerForward findAjaxForwordExt(ClientApiResultModel obj){
		try {
			getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = getResponse().getWriter();
			String json = "";
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setAllowNonStringKeys(true);
			if(obj instanceof List) {
				json = JSONArray.fromObject(obj, jsonConfig).toString();
			} else {
				json = JSONObject.fromObject(obj, jsonConfig).toString();
			}
			
			out.println(json);
		} catch (Exception e) {
			throw new BaseException(e);
		}
		return null;
	}
	
	public BaseControllerForward findAjaxForwordExt(ClientApiResultStringModel obj){
		try {
			getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = getResponse().getWriter();
			String json = "";
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setAllowNonStringKeys(true);
			if(obj instanceof List) {
				json = JSONArray.fromObject(obj, jsonConfig).toString();
			} else {
				json = JSONObject.fromObject(obj, jsonConfig).toString();
			}
			
			out.println(json);
		} catch (Exception e) {
			throw new BaseException(e);
		}
		return null;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	/**
	 * @return the dynaMethodCode
	 */
	public String getDynaMethodCode() {
		return dynaMethodCode;
	}
	/**
	 * @param dynaMethodCode the dynaMethodCode to set
	 */
	public void setDynaMethodCode(String dynaMethodCode) {
		this.dynaMethodCode = dynaMethodCode;
	}
}
