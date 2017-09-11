package org.net.plat4j.sr.core.utils.impl;


import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.directory.NoSuchAttributeException;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import org.net.plat4j.sr.core.utils.IBaseReflectUtils;

@SuppressWarnings({"rawtypes"})
public class BaseReflectUtils implements IBaseReflectUtils {
	private static BaseReflectUtils baseReflectUtils;
	//private BaseReflectUtils() {}
	
	public static BaseReflectUtils getInstance(){
		if(baseReflectUtils == null){
			synchronized (BaseReflectUtils.class) {
				if(baseReflectUtils == null){
					baseReflectUtils = new BaseReflectUtils();
				}
			}
		}
		return baseReflectUtils;
	}
//	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
//			"yyyy-mm-dd");

	private Map allMethods = new HashMap();
	private Map allBean = new HashMap();

	public Object getObject(String str, String type)
			throws NoSuchAttributeException, ParseException {
		Object ret = null;
		if (type.equals("String")) {
			return str;
		} 
		if(str.equals("")){
			return null;
		}
		if (type.equals("Double")) {
			ret = new Double(str);
		} else if (type.equals("Long")) {
			ret = new Long(str);
		} else if (type.equals("Float")) {
			ret = new Float(str);
		} else if (type.equals("Date")) {			
			ret = DateUtils.parseDate(str, new String[]{"yyyy-MM-dd","yyyy-MM-dd HH:mm","yyyy-MM-dd HH:mm:ss"});
		} else if (type.equals("Integer")) {
			ret = new Integer(str);
		} else {
			throw new NoSuchAttributeException("Integer");
		}
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public Object getObject(String[] str, String type)
			throws NoSuchAttributeException, ParseException {
		String strValue= str.length>=1 ? str[0] :null;
		Object ret = null;
		if (StringUtils.isBlank(strValue)) {
			return null;
		}
		strValue=strValue.trim();
		if (type.equals("String")) {
			if(StringUtils.isNotEmpty(strValue) && strValue.toLowerCase().indexOf("<script") > -1){
				return StringEscapeUtils.escapeHtml4(strValue);
			}
			return strValue;
		}
		if (type.equals("Double")) {
			ret = new Double(strValue);
		} else if (type.equals("Long")) {
			ret = new Long(strValue);
		} else if (type.equals("Float")) {
			ret = new Float(strValue);
		} else if (type.equals("Date")) {
			ret = DateUtils.parseDate(strValue, new String[] { "yyyy-MM-dd",
					"yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss" });
		} else if (type.equals("Integer")) {
			ret = new Integer(strValue);
		} else if (type.equals("List")) {

			List valueList = new ArrayList<>();
			for (String string : str) {
				if(StringUtils.isNotEmpty(string) && string.toLowerCase().indexOf("<script") > -1){
					valueList.add(StringEscapeUtils.escapeHtml4(string));
				}else{
					valueList.add(string);
				}
			}
			ret = valueList;
		}else if(type.equals("BigDecimal")){
			ret = new BigDecimal(strValue);
		} else {
			throw new NoSuchAttributeException("Unsupported type:" + type);
		}
		return ret;
	}
	

	public Method getGetMethod(Class cls, String fieldName)
			throws NoSuchMethodException {
		return getMethodByPrex(cls, fieldName, "get");
	}

	public Method getSetMethod(Class cls, String fieldName)
			throws NoSuchMethodException {
		return getMethodByPrex(cls, fieldName, "set");
	}

	public Method getMethodByPrex(Class cls, String fieldName, String pre)
			throws NoSuchMethodException {
		String bFieldName = pre + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		if (allMethods.containsKey(cls + "#" + bFieldName)) {
			return (Method) allMethods.get(cls + "#" + bFieldName);
		}
		if(!allBean.containsKey(cls)){
			initCache(cls);
		}	
		Method[] methods = cls.getMethods();
		for (int i = 0, j = methods.length; i < j; i++) {
			Method method = methods[i];
			if (method.getName().equals(bFieldName)) {
				return method;
			}
		}
		throw new NoSuchMethodException(cls + "#" + bFieldName);
	}
	public boolean hasGetMethod(Class cls, String fieldName){
		return hasMethod(cls,fieldName,"get");
	}
	public boolean hasSetMethod(Class cls, String fieldName){
		return hasMethod(cls,fieldName,"set");
	}
	public boolean hasMethod(Class cls, String fieldName, String pre){
		String bFieldName = pre + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		if(!allBean.containsKey(cls)){
			initCache(cls);
		}
		return allMethods.containsKey(cls + "#" + bFieldName);
		
	}
	@SuppressWarnings("unchecked")
	private void initCache(Class cls){
		if(!allBean.containsKey(cls.getName())){
			allBean.put(cls.getName(), "");
		}		
		Method[] methods = cls.getMethods();
		for (int i = 0, j = methods.length; i < j; i++) {
			Method getMethod = methods[i];
			allMethods.put(cls + "#" + getMethod.getName(),
					getMethod);
		}
	}
	public static void main(String[] args) throws Exception {
	}
}
