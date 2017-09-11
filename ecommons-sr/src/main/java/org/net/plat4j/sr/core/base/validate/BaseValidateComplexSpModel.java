package org.net.plat4j.sr.core.base.validate;

import org.net.plat4j.sr.core.base.BaseServiceParamModel;
/**
 * 一般用于validate的继承spModel
 * @author chenshiming
 *
 */
public class BaseValidateComplexSpModel  extends BaseServiceParamModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 匹配的关键值<BR>
	 * 比如修改公司名的时候,要匹配公司名是否存在,需要与非当前公司的公司名比较<BR>
	 * 在这种情况下,ID就是当前公司的ID
	 */
	private String idValue;
	/**
	 * 用于多字段的validate校验
	 * javascript以json的格式post
	 */
	private String valueJson;

	public String getIdValue() {
		return idValue;
	}

	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}

	public String getValueJson() {
		return valueJson;
	}

	public void setValueJson(String valueJson) {
		this.valueJson = valueJson;
	}	
	
}
