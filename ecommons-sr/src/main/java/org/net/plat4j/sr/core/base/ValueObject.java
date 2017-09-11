/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: cairw
	Version: 1.0
	Created Time: 2015年5月26日 下午4:26:28
	
	Revision History:
	Version     Date                Author			Comments
	1.0        2015年5月26日下午4:26:28		cairw     	Create file
=========================================================================
*/

package org.net.plat4j.sr.core.base;

import java.util.Map;

/**
 * @author cairw
 *
 */
public class ValueObject {
	/**
	 * 值
	 */
	private String value;
	/**
	 * 附加属性
	 */
	private String attr;
	
	/**
	 * 对应的数据类型
	 */

	private String type;
	
	/**
	 * seq
	 */
	private String seq;
	
	/**
	 * attrMap 
	 */
	private Map<String,String> attrMap;
	
	
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the attr
	 */
	public String getAttr() {
		
		return attr;
	}
	/**
	 * @param attr the attr to set
	 */
	public void setAttr(String attr) {
		this.attr = attr;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the seq
	 */
	public String getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public Map<String, String> getAttrMap() {
		return attrMap;
	}
	public void setAttrMap(Map<String, String> attrMap) {
		this.attrMap = attrMap;
	}
	

}
