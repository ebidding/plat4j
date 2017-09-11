package org.net.plat4j.sr.core.base.validate;

import org.net.plat4j.sr.core.base.BaseServiceParamModel;

/**
 * 用于validate的spModel
 * 不能继承 如果要支持多参数,则使用BaseValidateComplexSpModel
 * @author chenshiming
 *
 */
public final class BaseValidateSpModel extends BaseValidateComplexSpModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用于校验的值
	 */
	private String value;
	/**
	 * 匹配的关键值<BR>
	 * 比如修改公司名的时候,要匹配公司名是否存在,需要与非当前公司的公司名比较<BR>
	 * 在这种情况下,ID就是当前公司的ID
	 */
	private String idValue;
	


 
 


	public String getIdValue() {
		return idValue;
	}

	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
 
	
	
}
