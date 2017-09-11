/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: cairw
	Version: 1.0
	Created Time: 2015年5月26日 下午9:56:27
	
	Revision History:
	Version     Date                Author			Comments
	1.0        2015年5月26日下午9:56:27		cairw     	Create file
=========================================================================
 */

package org.net.plat4j.sr.core.base;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import org.net.plat4j.sr.core.utils.LogHelper;

/**
 * @author cairw
 *
 */
public class SpecialFields {
	
	private static LogHelper logger = new LogHelper(SpecialFields.class);
	private Map<String, Map<String, Map<String, ValueObject>>> specialFieldsMap = new HashMap<>();
	private final static String defalutGroup = " ";
	private final static String defalutSeq = "0";

	/**
	 * @return the specialFieldsMap
	 */
	public Map<String, Map<String, Map<String, ValueObject>>> getSpecialFieldsMap() {
		return specialFieldsMap;
	}

	/**
	 * @param specialFieldsMap
	 *            the specialFieldsMap to set
	 */
	public void setSpecialFieldsMap(
			Map<String, Map<String, Map<String, ValueObject>>> specialFieldsMap) {
		this.specialFieldsMap = specialFieldsMap;
	}

	private Map<String, Map<String, ValueObject>> getGroup() {
		return getGroup(defalutGroup);
	}

	private Map<String, Map<String, ValueObject>> getGroup(String group) {
		return this.specialFieldsMap.get(group);
	}

	private Map<String, ValueObject> getField(String field) {
		Map<String, Map<String, ValueObject>> group = getGroup();
		if (group == null)
			throw new BaseException(" field: [" + field
					+ "] have group in the jsp. ");
		Map<String, ValueObject> fieldMaps = getGroup().get(field);
		if(fieldMaps==null){
			throw new BaseException(" field: [" + field
					+ "] have not find in the jsp. ");
		}
		return fieldMaps;
	}

	private Map<String, ValueObject> getField(String group, String field) {
		Map<String, ValueObject> map = new HashMap<>();
		Map<String, Map<String, ValueObject>> groupMap = getGroup(group);
		if (groupMap == null)
			return map;
		if (!groupMap.containsKey(field)) {
			return map;
		} else {
			map = groupMap.get(field);
		}
		return map;
	}

	/**
	 * 存在没有分组的情形直接返回分组的所有字段
	 * 适用于金额类型处理
	 * @return
	 */
	public Map<String, List<ValueObject>> getListFieldsByGroup(){
		return getListFieldsByGroup(defalutGroup);
	}
	
	/**
	 * 根据 group 返回一个list 其中 key 为 field
	 * 
	 * @param group
	 * @return
	 */
	public Map<String, List<ValueObject>> getListFieldsByGroup(String group) {
		Map<String, List<ValueObject>> map = new HashMap<>();
		Map<String, Map<String, ValueObject>> groupMap = getGroup(group);
		for (Map.Entry<String, Map<String, ValueObject>> entry : groupMap
				.entrySet()) {
			List<ValueObject> list = new ArrayList<>();
			for (Map.Entry<String, ValueObject> iterable_element : entry
					.getValue().entrySet()) {
				list.add(iterable_element.getValue());
			}
			map.put(entry.getKey(), list);
		}
		return map;
	}

	/**
	 * 根据 group 返回一个Map 其中 第一层的keykey 为 field 其中第二层 的key 为attr为属性
	 * 
	 * @param group
	 * @return
	 */
	public Map<String, Map<String, Object>> getMapFieldsByGroup(String group) {
		Map<String, Map<String, Object>> map = new HashMap<>();
		Map<String, Map<String, ValueObject>> groupMap = getGroup(group);
		for (Map.Entry<String, Map<String, ValueObject>> entry : groupMap
				.entrySet()) {
			Map<String, Object> valueMap = new HashMap<>();
			for (Map.Entry<String, ValueObject> seqMap : entry.getValue()
					.entrySet()) {
				ValueObject value = seqMap.getValue();
				if(value==null) continue;
				if(StringUtils.isEmpty(value.getAttr())) continue;
				Object objectparse = getObjectparse(value.getValue(),
						value.getType());
				valueMap.put(value.getAttr(), objectparse);
			}
			map.put(entry.getKey(), valueMap);
		}
		return map;
	}

	/**
	 * 根据group field 返回一个field的list
	 * 
	 * @param group
	 * @param field
	 * @return
	 */
	public List<ValueObject> getFieldList(String group,String field){
		Map<String, ValueObject> seqMap = getField(group, field);
		List<ValueObject> list = new ArrayList<>(); 
		for (Map.Entry<String, ValueObject> entry : seqMap.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}

	/**
	 * 根据group field 返回一个field的Map
	 *  其中 key  为attr
	 * @param group
	 * @param field
	 * @return
	 */
	public Map<String, Object> getFieldMap(String group, String field) {
		Map<String, ValueObject> seqMap = getField(group, field);
		Map<String,Object> map = new HashMap<>();
		for (Map.Entry<String, ValueObject> entry : seqMap.entrySet()) {
			ValueObject value = entry.getValue();
			if(StringUtils.isEmpty(value.getAttr())) continue;
			Object objectparse = getObjectparse(value.getValue(), value.getType());
			map.put(value.getAttr(), objectparse);
		}
		return map;
	}
	/**
	 * 根据field  group 返回值
	 * @param group
	 * @param field
	 * @return
	 */
	public Object getObjec(String group, String field) {
		Map<String, ValueObject> seqMap = getField(group, field);
		if(seqMap==null) return null;
		ValueObject valueObject = seqMap.get(defalutSeq);
		if(valueObject==null) return null;
		return getObjectparse(valueObject.getValue(), valueObject.getType());
	}

	/**
	 * 根据field 获取对应的类型 如金额传递一个NAME 返回 RMB 266 USD 277
	 * 
	 * @param field
	 * @return
	 */
	public Map<String, Object> getMapValue(String field) {
		Map<String, Object> ebsMap = new HashMap<>();
		Map<String, ValueObject> seqMap = getField(field);
		for (Map.Entry<String, ValueObject> entry : seqMap.entrySet()) {
			ValueObject value = entry.getValue();
			Object typeValue = getObjectparse(value.getValue(), value.getType());
			ebsMap.put(value.getAttr(), typeValue);

		}
		return ebsMap;
	}

	/**
	 * 返回一个List 根据field 如 packagid_1=3 packageid_2=4 则返回一个List
	 * 
	 * @param field
	 * @return
	 */
	public List<ValueObject> getListValue(String field) {
		List<ValueObject> list = new ArrayList<>();
		Map<String, ValueObject> seqMap = getField(field);
		for (Map.Entry<String, ValueObject> entry : seqMap.entrySet()) {
			ValueObject value = entry.getValue();
			list.add(value);
		}
		return list;
	}

	/**
	 * 返回一个String
	 * 
	 * @param field
	 * @return
	 */
	public Object getObject(String field) {
		ValueObject valueObject = getField(field).get(defalutSeq);
		return getObjectparse(valueObject.getValue(), valueObject.getType());
	}

	private Object getObjectparse(String str, String type) {
		Object ret = null;
		if (type == null)
			return str;
		if (type.equals("String")) {
			return str;
		}
		if (str.equals("")) {
			return null;
		}
		if (type.equals("Double")) {
			ret = new Double(str);
		} else if (type.equals("Long")) {
			ret = new Long(str);
		} else if (type.equals("Float")) {
			ret = new Float(str);
		} else if (type.equals("Date")) {
			try {
				ret = DateUtils.parseDate(str, new String[] { "yyyy-MM-dd",
						"yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss" });
			} catch (ParseException e) {
				logger.error(e);
			}
		} else if (type.equals("Integer")) {
			ret = new Integer(str);
		} else if (type.equals("BigDecimal")) {
			ret = new BigDecimal(str);
		} else {
			ret = str;
		}
		return ret;
	}
}
