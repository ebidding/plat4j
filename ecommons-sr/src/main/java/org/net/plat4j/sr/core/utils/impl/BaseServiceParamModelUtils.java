package org.net.plat4j.sr.core.utils.impl;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import net.plat4j.core.pageTools.PageTools;

import org.net.plat4j.sr.core.base.BaseException;
import org.net.plat4j.sr.core.base.BaseServiceParamModel;
import org.net.plat4j.sr.core.base.SpPageModel;
import org.net.plat4j.sr.core.base.SpecialFields;
import org.net.plat4j.sr.core.base.ValueObject;
import org.net.plat4j.sr.core.utils.IBaseServiceParamModelUtils;
import org.net.plat4j.sr.core.utils.LogHelper;

public class BaseServiceParamModelUtils implements IBaseServiceParamModelUtils{
	private static  BaseServiceParamModelUtils baseserviceparammodelutils;
	private BaseServiceParamModelUtils() {}
	
	public static BaseServiceParamModelUtils getInstance(){
		if(baseserviceparammodelutils ==  null){
			synchronized (BaseServiceParamModelUtils.class) {
				if(baseserviceparammodelutils ==  null){
					baseserviceparammodelutils = new BaseServiceParamModelUtils();
				}
			}
		}
		return baseserviceparammodelutils;
	}
	
	protected LogHelper logger = new LogHelper(getClass());
	private Pattern ebsParamNamePattern= Pattern.compile("^(\\w+)[(](?:(\\w+?)(?:>>([0-9]+))?(?:>>a([^@]*))?)(?:@([^#]*))?(?:#(.*))?[)]$");
	private final static String defalutGroup=" ";
	private final static String defalutSeq="0";
	

	@SuppressWarnings("rawtypes")
	public <T extends BaseServiceParamModel> T getFormParameters(
			HttpServletRequest request, Class<T> spModelClass){
		T t;
		try {
			t = spModelClass.newInstance();
			t.setRequest(request);
			t.setSession(request.getSession());
		} catch (Exception e) {
			throw new BaseException(e);
		}
		String simpleName = spModelClass.getSimpleName();
		Map<String, MethodAttr> methodsInfo = getMethodsInfo(spModelClass);
		Enumeration names = request.getParameterNames();
		Map<String, SpecialFields> ebsSpecialMaps = t.getEbsSpecialMaps();
	    while (names.hasMoreElements()){
			String name = (String) names.nextElement();
			String paramValue = request.getParameter(name);
			if (StringUtils.isNotEmpty(paramValue) && paramValue.toLowerCase().indexOf("<script") > -1) {
				paramValue = StringEscapeUtils.escapeHtml4(paramValue);
			}
			Matcher m = ebsParamNamePattern.matcher(name);
			if (!m.find())
				continue;
			String vpd = m.group(1);
			String fieldName = m.group(2);
			String seq = m.group(3);
			String attr = m.group(4);
			String type = m.group(5);
			String group = m.group(6);
			if (vpd.equals("v") && (StringUtils.isEmpty(group) || simpleName.equals(group))) {
				String[] paramValues = request.getParameterValues(name);
				MethodAttr methodAttr = methodsInfo.get(fieldName);
				if (methodAttr != null) {
					Method method = methodAttr.getMethod();
					Object typeValue;
					try {
						typeValue = BaseReflectUtils.getInstance().getObject(paramValues, methodAttr.getParmaterType());
						method.invoke(t, typeValue);
					} catch (Exception e) {
						logger.error(e.getMessage());
						throw new BaseException(e);
					}
				}
				continue;
			}
			// step1 看大类是否存在
			SpecialFields specialFields = ebsSpecialMaps.get(vpd);
			if (specialFields == null) {
				specialFields = new SpecialFields();
				ebsSpecialMaps.put(vpd, specialFields);
			}
			Map<String, Map<String, Map<String, ValueObject>>> specialFieldsMap = specialFields.getSpecialFieldsMap();
			// step2 看分组是否存在
			if (StringUtils.isEmpty(group))
				group = defalutGroup;
			Map<String, Map<String, ValueObject>> groupMap = specialFieldsMap.get(group);
			if (!specialFieldsMap.containsKey(group)) {
				groupMap = new HashMap<>();
				specialFieldsMap.put(group, groupMap);
			}

			// step3 看字段Map是否存在
			Map<String, ValueObject> fieldMaps = groupMap.get(fieldName);
			if (!groupMap.containsKey(fieldName)) {
				fieldMaps = new HashMap<>();
				groupMap.put(fieldName, fieldMaps);
			}

			// step4 看序列是否存在
			if (StringUtils.isEmpty(seq))
				seq = defalutSeq;
			ValueObject valueObject = fieldMaps.get(seq);
			if (valueObject == null)
				valueObject = new ValueObject();
			if (StringUtils.isNotEmpty(attr)) {
				Map<String, String> attrMap = valueObject.getAttrMap();
				if (attrMap == null)
					attrMap = new HashMap<>();
				attrMap.put(attr, paramValue);
				valueObject.setAttrMap(attrMap);
				valueObject.setAttr(paramValue);
			} else {
				valueObject.setType(type);
				valueObject.setValue(paramValue);
				valueObject.setSeq(seq);
			}

			fieldMaps.put(seq, valueObject);
	    }
		return t;
	}
	public void fillPageWithParameter(BaseServiceParamModel spModel,HttpServletRequest request ){
		PageTools pageTool = new PageTools();
		pageTool.fillPageWithParameter(request);
		String isAdvancedSearch = request.getParameter("is_advanced_search");
		SpPageModel spPageModel = new SpPageModel();
		spPageModel.setPageNo(new Long(pageTool.getPageNo()));
		spPageModel.setPageSize(new Long(pageTool.getPageSize()));
		
		String ebiddingPageNo = request.getParameter("ebidding_page_no"); 
		String ebiddingPageSize = request.getParameter("ebidding_page_size"); 
		if(StringUtils.isNotEmpty(ebiddingPageNo) || StringUtils.isNotEmpty(ebiddingPageSize) || spModel.getSpPageModel() == null)
			spModel.setSpPageModel(spPageModel);
		if(StringUtils.isNotEmpty(isAdvancedSearch))
			spModel.setIsAdvancedSearch(isAdvancedSearch);
	}
	
	public PageTools getPage(BaseServiceParamModel spModel){
		if(spModel.getSpPageModel()==null || spModel.getSpPageModel().getPageNo()==null || spModel.getSpPageModel().getPageSize()==null){
			logger.debug("BaseServiceParamModel pageModel is null,due to lost common 'fillPageWithParameter(spModel, context.getRequest());' in action");
		}
		PageTools pageTool = new PageTools();
		int pageNo = 0;
		if(spModel.getSpPageModel()!=null && spModel.getSpPageModel().getPageNo()!=null){
			pageNo = spModel.getSpPageModel().getPageNo().intValue();
		}
		pageTool.setPageNo(pageNo);
		int pageSize = 20;
		if(spModel.getSpPageModel()!=null && spModel.getSpPageModel().getPageSize()!=null){
			pageSize = spModel.getSpPageModel().getPageSize().intValue();
		}		
		pageTool.setPageSize(pageSize);
		return pageTool;
	}

	/**
	 * 返回一个 Map  以字段名称为key  methodAttr 为method  和 返回值类型
	 * 不考虑 多参和无残的 非set 方法
	 * @param clazz
	 * @return
	 */
	private Map<String ,MethodAttr> getMethodsInfo(Class<?> clazz){
		Method[] methods = clazz.getMethods();
		Map<String,MethodAttr> methodInfo = new HashMap<>();
		for (Method method : methods) {
			if(method.getName().startsWith("set")){
				 Class<?>[] parameterTypes = method.getParameterTypes();
				 if(parameterTypes.length==1){
						String name = method.getName();
						if(name.length()<4) continue;
					    String substring = name.substring(3, 4).toLowerCase(); 
					 	String fieldName=substring+name.substring(4,name.length());
					 	String type = method.getParameterTypes()[0].getSimpleName();
					 	MethodAttr m = new MethodAttr();
					 	m.setMethod(method);
					 	m.setParmaterType(type);
					 	methodInfo.put(fieldName, m);	
				 }
			
			} 
		}
		return methodInfo;
	}


}
