package org.net.plat4j.sr.core.base.ws;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import org.net.plat4j.sr.core.base.BaseAxisWebService;
import org.net.plat4j.sr.core.base.BaseServiceParamModel;
import org.net.plat4j.sr.core.base.BaseServiceResultModel;
import org.net.plat4j.sr.core.utils.ITextBeanUtils;

import net.plat4j.core.spring.BeanFactory;
/**
 * <h1>Spr的Axis WebService层基础类.</h1>
 * <strong>DEMO:</strong>{@link org.net.plat4j.demo.ws.DisposeAxisWebServcie}<BR>
 * <strong>说明</strong>:目前提供了Json协议调用Spr Service<br>
 * <strong>配置说明</strong><ol>
 * <li>编写webservcie类,见org.net.plat4j.demo.ws.DisposeAxisWebServcie
 * </li>
 * <li>配置wsdd
 * <pre>
 * &lt;service name="JsonBaseDisposeAxisWebServcie" provider="java:RPC"&gt;
 *   &lt;parameter name="allowedMethods" value="invokeJsonServcie"/&gt;
 *   &lt;parameter name="className" value="org.net.plat4j.demo.ws.DisposeAxisWebServcie"/&gt;&lt;--需要修改类名--&gt;
 *   &lt;namespace&gt;http://www.bsteel.net&lt;/namespace&gt;
 * &lt;/service&gt;
 * </pre>
 * </li>
 * </ol>
 * @author chenshiming
 * @since v1.3.1
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class BaseDisposeAxisWebServcie extends BaseAxisWebService {
	//T001903实现WebServcie的Json格式协议调用(廖凯 负责)@廖凯2012-09-10编写
	/**
	 * 该json格式的字符串转换成目标的对象中只能支持有数组或自定义数组类型
	 * 如果存在集合类型建议改成自定义对象的数组类型
	 * @param methodName
	 * @param spModelJson
	 * @return srModel的Json格式
	 */
	public String invokeJsonService(String methodName,String spModelJson){
		BaseServiceResultModel srModel = null; 
		try {
			BaseServiceParamModel spModel = this.getSpModelByMethodName(methodName);
			JSONObject jsonObject = JSONObject.fromObject(spModelJson);
			spModel = (BaseServiceParamModel)JSONObject.toBean(jsonObject, spModel.getClass());
			srModel = touch(methodName,spModel);
			srModel.getSrUserProcessModel().setResultChineseDesc(getActionMessage(srModel));
			JSONObject reJsonObject = JSONObject.fromObject(srModel);
			return reJsonObject.toString();
		} catch (Exception e) {
			return e.getClass()+":"+e.getMessage();
		}
	}
	/**
	 * 
	 * @param method
	 * @param spModelMap
		resultCode<String-100>执行结果代码 info:正确 fail:错误 exception:接口异常 
		resultChineseDesc<Sting-255>执行结果中文描述 
	 * @return srModel的Map格式
	 */
	public Map invokeMapServcie(String methodName,Map spModelMap){
		Map srModelMap = new HashMap();
//		BaseServiceParamModel spModel = new BaseServiceParamModel();
		try {
			BaseServiceParamModel spModel = this.getSpModelByMethodName(methodName);
			BeanUtils.populate(spModel, spModelMap);
			BaseServiceResultModel srModel = touch(methodName,spModel);
			srModelMap = BeanUtils.describe(srModel); 
			
			srModelMap.put("resultCode", srModel.getSrUserProcessModel().getResultCode());
			srModelMap.put("resultChineseDesc", getActionMessage(srModel));
		} catch (Exception e) {
			srModelMap.put("resultCode", "exception");
			srModelMap.put("resultChineseDesc",e.getClass()+":"+e.getMessage());			
		}
		return srModelMap;
	}
	public static void main(String args){
		
	}
	public String invokeXmlServcie(String method,String spModelXml){
		ITextBeanUtils textBeanUtils = BeanFactory.getBean("ITextBeanUtils");
		BaseServiceParamModel spModel = (BaseServiceParamModel)textBeanUtils.getBeanFromText(spModelXml);
		BaseServiceResultModel srModel = touch(method,spModel);
		return textBeanUtils.getTextFromBean(srModel);	 
	}
	
	public BaseServiceResultModel invokeSpService(String method,BaseServiceParamModel spModel){
		return touch(method,spModel);
	}
	
	private BaseServiceResultModel touch(String method,BaseServiceParamModel spModel){
		try {
			Method m = this.getClass().getMethod(method, new Class[]{spModel.getClass()});
			Object tt = m.invoke(this, new Object[]{spModel});
			BaseServiceResultModel srModel = (BaseServiceResultModel)tt;
			return srModel;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	/**
	 * 1.获得该对象中方法名为methodName有且仅存在一个参数的方法,如果存在重载则取第一个
	 * 2.返回该方法参数类型的一个对象
	 * @param methodName
	 * @return spModel
	 * @throws Exception 
	 */
	private BaseServiceParamModel getSpModelByMethodName(String methodName) throws Exception{
		try {
			Method[] m = this.getClass().getDeclaredMethods();
			for(int i=0;i<m.length;i++){
				if (methodName.equals(m[i].getName())&&m[i].getParameterTypes().length==1) {
					Class c1 = m[i].getParameterTypes()[0];
					return (BaseServiceParamModel)c1.newInstance();
				}
			}
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		return null;
	}
}
