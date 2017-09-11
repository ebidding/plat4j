package org.net.plat4j.common.utils;

import java.math.BigDecimal;

public class PriceStorageModel {
	
	private String currency;
	
	private String quoteMode;
	
	private String unit;
	
	private BigDecimal value;

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @return the quoteMode
	 */
	public String getQuoteMode() {
		return quoteMode;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @return the value
	 */
	public BigDecimal getValue() {
		return value;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @param quoteMode the quoteMode to set
	 */
	public void setQuoteMode(String quoteMode) {
		this.quoteMode = quoteMode;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	
}
