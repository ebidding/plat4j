package org.net.plat4j.sr.core.utils.impl;

//import java.lang.reflect.Array;
//import java.lang.reflect.Method;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.naming.directory.NoSuchAttributeException;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.axis.utils.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.struts.config.ActionConfig;
//
//import org.net.plat4j.sr.core.base.BaseActionForm;
//import org.net.plat4j.sr.core.base.BaseServiceParamModel;
//import org.net.plat4j.sr.core.base.BaseTimeMonitor;
//import org.net.plat4j.sr.core.utils.IBaseActionFormUtils;
//import org.net.plat4j.sr.core.utils.IBaseReflectUtils;
//import org.net.plat4j.sr.core.utils.MD5Util;
//import org.net.plat4j.sr.core.utils.QueryCacheUtil;

@SuppressWarnings({})
public class BaseControllerFormUtils { // implements IBaseActionFormUtils {
//	protected Log logger = LogFactory.getLog(getClass());
//	private IBaseReflectUtils baseReflectUtils = null;
//
//	public void setBaseReflectUtils(IBaseReflectUtils baseReflectUtils) {
//		this.baseReflectUtils = baseReflectUtils;
//	}
//	
//	public String getCacheKey(HttpServletRequest request) {
//		
//		ActionConfig ac = (ActionConfig) request.getAttribute("org.apache.struts.action.mapping.instance"); 
//		String uri = ac.getPath() + ".do";
//		String pageMethod = request.getParameter("method");  
//		
//		String sessionId = request.getSession().getId();
//		String key = MD5Util.md5Hex(sessionId + uri + pageMethod);
//		
//		return key;		
//	}
//	
//	public BaseActionForm getCacheForm(HttpServletRequest request,BaseActionForm form) {		
//		 
//		String m = request.getParameter("m");
//		if("true".equals(m)) return form;
//		
//		String key = getCacheKey(request);		
//		BaseActionForm _form = QueryCacheUtil.getElement(key);
//		if(_form != null) {
//			form = _form;
//		}
//		
//		return form;
//	}
//
//	public BaseActionForm fillBaseForm(BaseActionForm form,HttpServletRequest request) throws Exception { 
//			
//		BaseTimeMonitor monitor = new BaseTimeMonitor("BaseActionForm#fillBaseForm");
//		monitor.beginMonitor();
//		Map map = form.getV();
//		
//		form = getCacheForm(request,form);	
//		Iterator iterator = map.keySet().iterator();
//		while (iterator.hasNext()) {
//			String key = (String) iterator.next();
//			if("menuTreeName@queryMenuSpModel#String".equals(key)) {
//				System.out.println("ok");
//			}
//			String value = ((String) map.get(key)).trim();
//			if(key.indexOf("~count")>=0){
//				continue;
//			}
//			if (key.indexOf("#") < 0) {
//				key += "#String";
//			}
//			String[] tv = key.split("#");
//			String vValue = tv[0];
//			String vType = tv[1];
//			String[] tvValue = vValue.split("@");
//			logger.debug("FormValue-"+key+":"+value);
//			fillBean(map,form, tvValue, tvValue.length - 1, value, vType, request);
//		}
//		monitor.print();
//		
//		String m = request.getParameter("m"); 
//		String isQuery = request.getParameter("is_query");	
//		if("true".equals(isQuery) && !"true".equals(m)) {
//			QueryCacheUtil.addElement(getCacheKey(request), form);
//		}
//		return form;
//	}
//
//	private void fillBean(Map map,Object parentBean, String[] tvValue, int index,
//			String targetValue, String targetType, HttpServletRequest request) throws Exception {
//		String child = tvValue[index]; 
//		FieldFormatModel childModel = getFieldValue(map,child);
//		String fieldName = childModel.getFieldName();
//		int fieldIndex = -1;
//		Object obj = null;//child reference
//		if(childModel.isArray()){
//			//Array Bean
//			fieldIndex = childModel.getIndex();
//			Method g = baseReflectUtils.getGetMethod(parentBean.getClass(), fieldName);
//			Class objClass = getClassByArrayClass(g.getReturnType());
//			Object[] objs = (Object[])g.invoke(parentBean, new Object[] {});
//			if (objs == null) {
//				objs = (Object[])Array.newInstance(objClass, childModel.getCount());
//			}
//			if(objs[fieldIndex]==null){
//				objs[fieldIndex] = objClass.newInstance();
//			}
//			obj = objs[fieldIndex];
//			Method s = baseReflectUtils.getSetMethod(parentBean.getClass(), fieldName);
//			s.invoke(parentBean, new Object[] { objs });
//		}else{
//			//Bean
//			Method g = baseReflectUtils.getGetMethod(parentBean.getClass(), fieldName);
//			obj = g.invoke(parentBean, new Object[] {});
//			if (obj == null) {
//				obj = g.getReturnType().newInstance();
//			}		
//			Method s = baseReflectUtils.getSetMethod(parentBean.getClass(), fieldName);
//			s.invoke(parentBean, new Object[] { obj });
//		}
//		index--;
//		if (index == 0) {
//			if (obj != null) {
//				if (BaseServiceParamModel.class.isAssignableFrom(obj
//						.getClass())) {
//					BaseServiceParamModel spModel = (BaseServiceParamModel) obj;
//					spModel.setRequest(request);
//				}
//			}
//			String fieldNameParam = tvValue[0];
//			if (fieldNameParam.trim().length() <= 0) {
//				return;
//			}
//			FieldFormatModel lastModel = getFieldValue(map, fieldNameParam);
//			if(lastModel.isArray()){
//				Method gg = baseReflectUtils.getGetMethod(obj.getClass(), lastModel.getFieldName());
//				Object[] objs = (Object[])gg.invoke(obj, new Object[]{});
//				if (objs == null) {
//					objs = (Object[])Array.newInstance(baseReflectUtils.getObject(targetValue, targetType).getClass(), lastModel.getCount());
//				}
//				if(objs[lastModel.getIndex()]==null){
//					objs[lastModel.getIndex()] = baseReflectUtils.getObject(targetValue, targetType) ;
//				}
//				Method ss = baseReflectUtils.getSetMethod(obj.getClass(), lastModel.getFieldName());
//				ss.invoke(obj, new Object[] {objs});
//			}else{
//				Method ss = baseReflectUtils.getSetMethod(obj.getClass(), lastModel.getFieldName());
//				ss.invoke(obj, new Object[] { baseReflectUtils.getObject(targetValue, targetType) });
//				//BeanUtils.setProperty(obj, lastModel.getFieldName(),  baseReflectUtils.getObject(targetValue, targetType) );
//			}
//			return;				
//			
////			Method ss = baseReflectUtils.getSetMethod(obj.getClass(), tvValue[0]);
////			ss.invoke(obj, new Object[] { baseReflectUtils.getObject(targetValue, targetType) });
////			return;
//		}
//		fillBean(map,obj, tvValue, index, targetValue, targetType, request);
//	}
//
//	private FieldFormatModel getFieldValue(Map map,String fieldName) throws NoSuchAttributeException {
//		FieldFormatModel model = new FieldFormatModel();
// 
//		if(fieldName.indexOf("~")<0){
//			model.setFieldName(fieldName);
//			model.setArray(false);
//			return model;
//		}
//		System.out.println(fieldName);
//		Pattern fieldPattern = Pattern.compile("^([a-zA-Z0-9]+)~([0-9]*)$");
//		Matcher m = fieldPattern.matcher(fieldName);
//		m.matches();
//		if(m.groupCount()==2){
//			String countKey = m.group(1)+"~count";
//			if(!map.containsKey(countKey)){
//				throw new NoSuchAttributeException(countKey);
//			}
//			String count = (String)map.get(countKey);
//			
//			model.setArray(true);
//			model.setFieldName(m.group(1));
//			model.setIndex(new Integer(m.group(2)).intValue());
//			model.setCount(new Integer(count).intValue());
//		}else{
//			throw new NoSuchAttributeException("Form attributeKey error:"+fieldName);
//		}
//		return model;
//	}
//	private Class getClassByArrayClass(Class arrClass) throws ClassNotFoundException{
//		String str = arrClass.getName();
//		String className = str.substring(2,str.length()-1);
//		return Class.forName(className);
//	}
//	
//	public static void main(String[] args) throws Exception {
//	/*	IBaseReflectUtils baseReflectUtils = new BaseReflectUtils();
//		BaseControllerFormUtils BaseControllerFormUtils = new BaseControllerFormUtils();
//		BaseControllerFormUtils.setBaseReflectUtils(baseReflectUtils);
//		if (true) {
//			CompanyForm form = new CompanyForm();
//			Map map = new HashMap();
//			map.put("companyCode@queryCompanySpModel#String", "54321");
//			map.put("loginNo@demoEmployees~0@saveCompanySpModel#String", "0A");
//			map.put("userName@demoEmployees~0@saveCompanySpModel#String", "0AN");
//			map.put("loginNo@demoEmployees~1@saveCompanySpModel#String", "1A");
//			map.put("userName@demoEmployees~9@saveCompanySpModel#String", "1AN");	
//			map.put("demoEmployees~count", "10");	 
//
//			form.setV(map);
//
//			form = (CompanyForm) BaseControllerFormUtils.fillBaseForm(form);
//			System.out.println("v:"+form.getQueryCompanySpModel().getCompanyCode());	 
//			System.out.println("v:"+form.getSaveCompanySpModel().getDemoEmployees()[0].getLoginNo());	 
//			System.out.println("v:"+form.getSaveCompanySpModel().getDemoEmployees()[0].getUserName());	 
//			System.out.println("v:"+form.getSaveCompanySpModel().getDemoEmployees()[1].getLoginNo());	 
//			System.out.println("v:"+form.getSaveCompanySpModel().getDemoEmployees()[9].getUserName());
// 
//		}*/
//		
//	}
//	
//	class FieldFormatModel{
//		private boolean isArray;
//		private String fieldName;
//		private int index;
//		private int count;
//		public boolean isArray() {
//			return isArray;
//		}
//		public void setArray(boolean isArray) {
//			this.isArray = isArray;
//		}
//		public int getCount() {
//			return count;
//		}
//		public void setCount(int count) {
//			this.count = count;
//		}
//		public String getFieldName() {
//			return fieldName;
//		}
//		public void setFieldName(String fieldName) {
//			this.fieldName = fieldName;
//		}
//		public int getIndex() {
//			return index;
//		}
//		public void setIndex(int index) {
//			this.index = index;
//		}
//		
//		
//	}

}
