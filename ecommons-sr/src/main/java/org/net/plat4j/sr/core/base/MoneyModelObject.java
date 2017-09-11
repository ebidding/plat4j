/*
	Copyright (C) 2016 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: cairw
	Version: 1.0
	Created Time: 2016年1月27日 下午5:05:41
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2016年1月27日下午5:05:41		cairw			Create file
=========================================================================
*/

package org.net.plat4j.sr.core.base;

import java.math.BigDecimal;

/**
 * @author cairw
 * <h1>
 * 	此类用于专门处理金额类型的字段
 * 	用以返回 金额 单位 币种
 * </h1>
 */
public class MoneyModelObject {
	private BigDecimal value;
	
	private String unit;
	
	private String currency;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
