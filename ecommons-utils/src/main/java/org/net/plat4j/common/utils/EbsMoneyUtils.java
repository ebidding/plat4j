/*
	Copyright (C) 2016 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: cairw
	Version: 1.0
	Created Time: 2016年1月25日 下午5:27:12
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2016年1月25日下午5:27:12		cairw			Create file
=========================================================================
*/

package org.net.plat4j.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

/**
 * @author cairw
 *
 */
public class EbsMoneyUtils {
	public static  final String MONEY_SYMBOL_FORMAT="#";
	
	public static final String UNIT="UNIT";
	
	public static final String CURRENCY ="CURRENCY";
	
	public static final String VARIABLE_CURRENCY="VARIABLE_CURRENCY";
	
	public static final String SELECT_UNIT="SELECT_UNIT";
	
	public static final String SELECT_CURRENCY="SELECT_CURRENCY";
	
	public static final String PATTERN="PATTERN";
	/**
	 * 
	 * @param arg0
	 * @param agr1
	 * @return  两个数相加之和
	 */
	public static  BigDecimal add(BigDecimal arg0 ,BigDecimal agr1){
		if(arg0==null) arg0=BigDecimal.ZERO;
		if(agr1==null) arg0=BigDecimal.ZERO;
		return arg0.add(agr1);
	}
	
	/**
	 * 
	 * @param arg0
	 * @param agr1
	 * @return  两个数相减
	 */
	public static  BigDecimal sub(BigDecimal arg0 ,BigDecimal agr1){
		if(arg0==null) arg0=BigDecimal.ZERO;
		if(agr1==null) arg0=BigDecimal.ZERO;
		return arg0.subtract(agr1);
	}
	
	/**
	 * 
	 * @param arg0
	 * @param agr1
	 * @return  两个数相除
	 */
	public static  BigDecimal div(BigDecimal arg0 ,BigDecimal agr1){
		if(arg0==null) arg0=BigDecimal.ZERO;
		if(agr1==null) arg0=BigDecimal.ONE;
		return arg0.divide(agr1);
	}
	
	/**
	 * 
	 * @param arg0
	 * @param agr1
	 * @return  两个数相乘之
	 */
	public static  BigDecimal mult(BigDecimal arg0 ,BigDecimal agr1){
		if(arg0==null) arg0=BigDecimal.ZERO;
		if(agr1==null) arg0=BigDecimal.ZERO;
		return arg0.multiply(agr1);
	}
	
	/**
	 * 
	 * @param arg0
	 * @param agr1
	 * @return  两个数比较
	 */
	public static  int compareTo(BigDecimal arg0 ,BigDecimal agr1){
		if(arg0==null) arg0=BigDecimal.ZERO;
		if(agr1==null) arg0=BigDecimal.ZERO;
		return arg0.compareTo(agr1);
	}
	
	/**
	 * 将传递过来的金额按照精度要求保留位数
	 * 	当传递过来的小数位数为NULL时则返回原有值
	 * @param money
	 * @param decimalPlaces
	 * @return
	 */
	public static BigDecimal moneyDecimalPlaces(BigDecimal money,Integer decimalPlaces){
		return decimalPlaces==null? money : money.divide(BigDecimal.ONE,decimalPlaces,BigDecimal.ROUND_HALF_UP);
	}
	/**
	 * 将传递过来的金额按照精度要求保留位数  默认方法保留两位精确到分
	 * @param money
	 * @return
	 */
	public static BigDecimal moneyDecimalPlaces(BigDecimal money){
		return moneyDecimalPlaces(money,2);
	}
	/**
	 * 将传递过来的金额按照精度要求保留位数  默认方法保留两位精确到分
	 * @param money
	 * @return
	 */
	public static BigDecimal moneyDecimalPlaces(String money){
		return moneyDecimalPlaces(new BigDecimal(money));
	}
	
	
	/**
	 * 将传递过来的金额按照精度要求保留位数
	 * @param money
	 * @param decimalPlaces
	 * @return
	 */
	public static BigDecimal moneyDecimalPlaces(String money,Integer decimalPlaces){
		return moneyDecimalPlaces(new BigDecimal(money),decimalPlaces);
	}
	
	
	/**
	 * 
	 * @param money 单位元
	 * @param pattern   形如"###,###.#   ###,###  #"
	 * @param decimalPlaces  保留的小数位数 
	 * @return <li>此方法提供将金额按照对应的格式 转换   主要实现 将元转换对应的元格式
	 *           如222123.455 转换成 222,123.46</li>
	 */
	public static String formatMoneyToYuan(BigDecimal money,String pattern,Integer decimalPlaces,boolean currencySymbol){
		return formatMoney(money, pattern, decimalPlaces, false,null,currencySymbol);
	}
	/**
	 * 
	 * @param money 单位元
	 * @param pattern   形如"###,###.#   ###,###  #"
	 * @param decimalPlaces  保留的小数位数 
	 * @return <li>此方法提供将金额按照对应的格式 转换   主要实现 将元转换对应的万元格式
	 *           </li>
	 */
	public static String formatMoneyToWanYuan(BigDecimal money,String pattern,Integer decimalPlaces,boolean currencySymbol){
		return formatMoney(money, pattern, decimalPlaces, true,null,currencySymbol);
	}
	/**
	 * 金额格式化
	 * @param value
	 * @param pattern
	 * @param unit
	 * @param currency
	 * @param decimalPlaces
	 * @param currencySymbol
	 * @return
	 */
	public static String formatMoney(String value,String pattern,String unit,String currency,Integer decimalPlaces,boolean currencySymbol){
		return formatMoney(StringUtils.isEmpty(value)? BigDecimal.ZERO: new BigDecimal(value), pattern, decimalPlaces, "1".equals(unit)? true :false, currency,currencySymbol);
	}
	
	/**
	 * 金额格式化   默认为 不加上金额 币种符号
	 * @param value
	 * @param pattern
	 * @param unit
	 * @param currency
	 * @param decimalPlaces
	 * @return
	 */
	public static String formatMoney(String value,String pattern,String unit,String currency,Integer decimalPlaces){
		return formatMoney(value, pattern, unit, currency, decimalPlaces, false);
	}
	
	/**
	 * @author cairw 
	 * @param money
	 * @param currency
	 * @param unit
	 * @return
	 */
	public static String formatMoney(BigDecimal money,String currency,String unit){
		String moneyView = formatMoney(money,null,null,"1".equals(unit)? true :false,currency ,true);
		String returnStr=moneyView;
		returnStr = StringUtils.isEmpty(unit) ? returnStr :	moneyView + " ("+DataDictionaryUtils.getDataDicName("STD_UNIT", unit, WebUtils.getUserAgentId())+")";
		return returnStr;
	}
	
	
	/**
	 * @author cairw  
	 * 支持java后台直接访问生成格式化
	 * @param money
	 * @param currency
	 * @param unit
	 * @param currencySymbol
	 * @return
	 */
	public static String formatMoney(BigDecimal money,String currency,String unit,boolean currencySymbol){
		String moneyView = 	formatMoney(money,null,null,"1".equals(unit)? true :false,currency ,currencySymbol);
		String dataDicName = DataDictionaryUtils.getDataDicName("STD_UNIT", unit, WebUtils.getUserAgentId());
		return moneyView + (" ("+dataDicName+")");
	}
	
	/**
	 * 
	 * @param money    单位元
	 * @param pattern  形如"###,###.#   ###,###  #"
	 * @param decimalPlaces  保留的小数位数 
	 * @param isTenThousand  判断是否以万元格式转换 TRUE 表示是
	 * @param currency  币种 根据EBS 币种值集找到对应的币种的符合 如CNY
	 * @return  返回制定的格式
	 */
	public static String formatMoney(BigDecimal money,String pattern,Integer decimalPlaces,Boolean isTenThousand,String currency,boolean currencySymbol){
		//if(money.compareTo(BigDecimal.ZERO)==0) return "";
		money  =  (money==null) ? BigDecimal.ZERO  : money;
		StringBuffer result = new StringBuffer();
		BigDecimal newMoney = money;
		if(isTenThousand)
			newMoney=money.divide(new BigDecimal(10000));
		DecimalFormat decimalFormat =  new DecimalFormat(getPattern(newMoney.scale() ,pattern, decimalPlaces, currency,currencySymbol,isTenThousand));
		
		result.append(decimalFormat.format(moneyDecimalPlaces(newMoney, decimalPlaces)));
		return result.toString();
	}
	/**
	 * 人民币大写，支持将人民币大写
	 * @param money
	 * @return
	 */
	public static String rmbCaptial(BigDecimal money){
	  return	NumberToCN.number2CNMontrayUnit(money);
	}
	
	/**
	 *   获取对应的转换的格式
	 * @param pattern
	 * @param decimalPlaces
	 * @param currency
	 * @return
	 */
	private static final String getPattern(int scale ,String pattern,Integer decimalPlaces,String currency,boolean currencySymbol,boolean isTenThousand){
		StringBuffer patternsbf = new StringBuffer();
		if(currencySymbol)//当指定需要"$  时"
		     patternsbf.append(currencyTransform(currency));
		if (pattern != null){
			patternsbf.append(pattern);
			//系统未指定小数位数时，默认格式化参照指定的精度位数
			if(pattern.indexOf(".")==-1) patternsbf.append(getDecimalPattern(decimalPlaces,scale,isTenThousand));
		}else{
			patternsbf.append(MONEY_SYMBOL_FORMAT);
			patternsbf.append(getDecimalPattern(decimalPlaces,scale,isTenThousand));
		}
		
		return patternsbf.toString();
	}
	/**
	 * 获取币种转换格式
	 * @param currency
	 * @return
	 */
	private static final String currencyTransform(String currency){
		if(StringUtils.isEmpty(currency)) return "";
		String dataDicName = DataDictionaryUtils.getDataDicName("STD_CURRENCY_SYMBOL", currency);
		return dataDicName!=null ? dataDicName :"";
	}
	/**
	 * 获取对应的小数位精度格式 如 .## .####
	 * 当未指定返回的小数位数时，则返回原有小数位数，使用 # 将多余的0去掉
	 *  形如 123.498000 如果未指定返回的位数时返回 123.498 
	 * @param decimalPlaces
	 * @return
	 */
	private static final String getDecimalPattern(Integer decimalPlaces,int scale,boolean isTenThousand){
		if(decimalPlaces==null){
			//判断当为元时必须至少保留两位小数  如果为万元时至少保留6位小数
			if(!isTenThousand) 
				decimalPlaces=scale>=2? scale :2;
			else 
				decimalPlaces=scale>=6? scale :6;
				
		}
			
		String decimalPattern =  decimalPlaces>0 ? "." : "";
		for (Integer i = 0; i < decimalPlaces; i++) {
			decimalPattern+=MONEY_SYMBOL_FORMAT;
		}
		return decimalPattern;
	}
	
	
	
	/**
	 * 获取当前费用模型的对应的配置
	 *   配置值包括默认单位，默认币种，可选币种，是否可选择单位，是否可选择单位
	 *  当获取对应的费用模型不能够取到配置值时，则获取默认的DEFAULT级别的配置参数
	 * <pre>
	 * Author: cairw Created Time: 2016-01-27 
	 * </pre>
	 * @param agentId
	 * @param feeModelName
	 * @return
	 */
	public static Map <String,String> getFeeModelInfo(Long agentId,String feeModelName){
		Map<String,String> feeModelMap = new HashMap<>();
		String unitJson = ConfigUtils.getAgentConfigValue(agentId, "EBS_FINANCE", "FEE_MODEL_CONFIG");
		JSONObject fromObject = JSONObject.fromObject(unitJson);
		String unit=""; //配置的默认单位
		String currency=""; //配置的默认单位
		String variableCurrency="";//可选择的币种
		String selectUnit="";//是否单位可选择
		String selectCurrency="";//是否币种可选择
		String pattern="";//格式化 形如 "###,###.00"等
		Object defaultObject = fromObject.get("DEFAULT");
		JSONObject defalutModel = JSONObject.fromObject(defaultObject);
		if(StringUtils.isEmpty(feeModelName)){
			  unit= (String) defalutModel.get(UNIT);
			  currency=(String)defalutModel.get(CURRENCY);
		      variableCurrency=(String)defalutModel.get(VARIABLE_CURRENCY);
		      selectUnit=(String)defalutModel.get(SELECT_UNIT);
		      selectCurrency=(String)defalutModel.get(SELECT_CURRENCY);
		      pattern=(String)defalutModel.get(PATTERN);
		}else{
			Object feeObject = fromObject.get(feeModelName);
			if(feeObject==null) 
				feeObject=fromObject.get("DEFAULT");
			JSONObject feeModel = JSONObject.fromObject(feeObject);
			String feeUnit=(String) feeModel.get(UNIT);
			String feeCurrency=(String)feeModel.get(CURRENCY);
		    String feeVariableCurrency=(String)feeModel.get(VARIABLE_CURRENCY);
		    String feeSelectUnit=(String)feeModel.get(SELECT_UNIT);
		    String feeSelectCurrency=(String)feeModel.get(SELECT_CURRENCY);
		    String feePattern=(String)feeModel.get(PATTERN);
		    unit= feeUnit==null? (String) defalutModel.get(UNIT) :feeUnit;
		    currency=feeCurrency==null?(String) defalutModel.get(CURRENCY) :feeCurrency;
		    variableCurrency=feeVariableCurrency==null?(String)defalutModel.get(VARIABLE_CURRENCY):feeVariableCurrency;
		    selectUnit=feeSelectUnit==null?(String)defalutModel.get(SELECT_UNIT):feeSelectUnit;
		    selectCurrency=feeSelectCurrency==null? (String)defalutModel.get(SELECT_CURRENCY):feeSelectCurrency;
		    pattern=feePattern==null? (String)defalutModel.get(PATTERN):feePattern;
		}
		feeModelMap.put(UNIT, unit);
		feeModelMap.put(CURRENCY, currency);
		feeModelMap.put(VARIABLE_CURRENCY, variableCurrency);
		feeModelMap.put(SELECT_UNIT, selectUnit);
		feeModelMap.put(SELECT_CURRENCY, selectCurrency);
		feeModelMap.put(PATTERN, pattern);
		return feeModelMap;
	} 
	
	/**
	 * 此方法提供支持获取默认单位
	 * @author cairw 
	 * @param agentId
	 * @param feeModelName
	 * @return
	 */
	public static String getDefaultUnit(Long agentId,String feeModelName){
		Map<String, String> feeModelInfo = getFeeModelInfo(agentId, feeModelName);
		String string = feeModelInfo.get(UNIT);
	  return	string;
	}
	
	/**
	 * 此方法提供支持获取默认单位中文字符
	 * @author cairw 
	 * @param agentId
	 * @param feeModelName
	 * @return
	 */
	public static String getDefaultUnitCN(Long agentId,String feeModelName){
		Map<String, String> feeModelInfo = getFeeModelInfo(agentId, feeModelName);
		String string = feeModelInfo.get(UNIT);
		return DataDictionaryUtils.getDataDicName("STD_UNIT", string, agentId);
	}
	
	/**
	 * 此方法提供支持获取默认币种
	 * @author cairw 
	 * @param agentId
	 * @param feeModelName
	 * @return
	 */
	public static String getDefaultCurrency(Long agentId,String feeModelName){
		Map<String, String> feeModelInfo = getFeeModelInfo(agentId, feeModelName);
		String string = feeModelInfo.get(CURRENCY);
	     return	string;
	}
	
	public static void main(String[] args) {
		BigDecimal  b = null;
		//System.out.println( "syso :" +formatMoney(b, null, "1", null, null));
		System.out.println( "syso :" +formatMoney(b, null, null, true, null, true));
	}
}
