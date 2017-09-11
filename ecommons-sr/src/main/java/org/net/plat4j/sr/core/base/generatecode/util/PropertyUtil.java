/*
	Copyright (C) 2016 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2016年5月24日 下午1:34:08
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2016年5月24日下午1:34:08		chenyj			Create file
=========================================================================
*/
package org.net.plat4j.sr.core.base.generatecode.util;

import org.apache.commons.lang3.StringUtils;

import org.net.plat4j.sr.core.base.generatecode.model.Property;


/**
 * @author chenyj
 *
 */
public class PropertyUtil {
	/**
	 * 把字符串首字母大写
	 * @param str
	 * @return
	 */
	public static String upperFirstChar(String str){
		StringBuilder sb = new StringBuilder();
		if(StringUtils.isNoneEmpty(str)){
			sb.append(str.substring(0, 1).toUpperCase());
			if(str.length() > 1){
				sb.append(str.substring(1));
			}
		}
		return sb.toString();
	}
	/**
	 * 把表名称转换成java类名
	 * @param tableName
	 * @return
	 */
	public static String tableName2ClassName(String tableName){
		String[] names = tableName.toLowerCase().split("_");
		StringBuilder result = new StringBuilder();
		for(String name : names){
			if(StringUtils.isNotEmpty(name)){
				result.append(upperFirstChar(name));
			}
		}
		return result.toString();
	}
	/**
	 * 把字段名称转换成java属性名
	 * @param tableName
	 * @return
	 */
	public static String columnName2PropertyName(String tableName){
		String[] names = tableName.toLowerCase().split("_");
		StringBuilder result = new StringBuilder();
		boolean isFirst = true;
		for(String name : names){
			if(name.length() > 0){
				if(isFirst){
					isFirst = false;
					result.append(name);
				}else{
					result.append(upperFirstChar(name));
				}
			}
		}
		return result.toString();
	}
	/**
	 * 把字段类型转换成java中的类型
	 * @param columnType
	 * @return
	 */
	public static String columnType2PropertyType(Property property){
		switch(property.getDataType()){
		case "NUMBER":
			if(null == property.getDataScale() || 0 == property.getDataScale().intValue()){
				property.setLob(false);
				property.setPropertyType("Long");
				return "Long";
			}else if(property.getDataPrecision().intValue() == 30 && property.getDataScale().intValue() == 8){
				property.setLob(false);
				property.setPropertyType("BigDecimal");
				return "BigDecimal";
			}else{
				property.setLob(false);
				property.setPropertyType("Double");
				return "Double";
			}
		case "NVARCHAR2":
		case "VARCHAR2":
		case "XMLTYPE":
			property.setLob(false);
			property.setPropertyType("String");
			return "String";
		case "DATE":
		case "TIMESTAMP(6)":
			property.setLob(false);
			property.setPropertyType("Date");
			return "Date";
		case "CLOB":
			property.setLob(true);
			property.setPropertyType("String");
			return "String";
		case "BLOB":
			property.setLob(true);
			property.setPropertyType("byte[]");
			return "byte[]";
		default:
			throw new RuntimeException("unsupport type : " + property.getDataType() );	
		}
	}
	public static void main(String[] args) {
		System.out.println(tableName2ClassName("bid_package"));
		System.out.println(columnName2PropertyName("package_code"));
	}
}
