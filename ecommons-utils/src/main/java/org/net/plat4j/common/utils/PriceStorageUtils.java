package org.net.plat4j.common.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.net.plat4j.common.priceStorage.IPriceStorageService;

import net.plat4j.core.spring.BeanFactory;

public class PriceStorageUtils {
	/**
	 * 获取金额主表Id对应的子表金额信息Map
	 * 
	 * @param mainId 金额主表ID。	
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public static Map<String,PriceStorageModel> getPriceMap(Long mainId) {
		IPriceStorageService svc = (IPriceStorageService)BeanFactory.getBean("IPriceStorageService");
		return svc.getPriceMap(mainId);
	}
	/**
	 *  获取金额主表Id对应的子表金额信息List
	 * @param mainId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<PriceStorageModel> getPriceList(Long mainId) {
		IPriceStorageService svc = (IPriceStorageService)BeanFactory.getBean("IPriceStorageService");
		return svc.getPriceList(mainId);
	}
	/**
	 * 更新主表id为mainId的金额信息并添加type描述
	 * 
	 * @param priceList 存储金额信息PriceStorageModel对应的List。
	 * @param mainId 金额主表Id。
	 * @param type 金额主表类型描述。
	 */
	public static Long savePriceList(List<PriceStorageModel> priceList,Long mainId,String type){
		IPriceStorageService svc = (IPriceStorageService)BeanFactory.getBean("IPriceStorageService");
		return svc.savePriceList(priceList,mainId,type);
	}
	/**
	 * 更新主表id为mainId的金额信息
	 * 
	 * @param priceMap 存储金额信息PriceStorageModel对应的List。
	 * @param mainId 金额主表Id。
	 */
	public static Long savePriceList(List<PriceStorageModel> priceList,Long mainId){
		String type=null;
		IPriceStorageService svc = (IPriceStorageService)BeanFactory.getBean("IPriceStorageService");
		return svc.savePriceList(priceList,mainId,type);
	}
	/**
	 * 将priceMap中的金额信息存入金额表并返回金额主表Id。
	 *  @param priceMap 存储金额信息PriceStorageModel对应的List。
	 * @param priceMap 存储金额信息对应的map。key为币种，value为数额。
	 */
	public static Long savePriceList(List<PriceStorageModel> priceList){
		String type=null;
		Long mainId=null;
		IPriceStorageService svc = (IPriceStorageService)BeanFactory.getBean("IPriceStorageService");
		return svc.savePriceList(priceList,mainId,type);
	}
	/**
	 * 使用packageId对应标段（包）的汇率标准进行汇率换算
	 * 
	 * @param account 币种换算源币种金额。
	 * @param currency 币种换算源币种。
	 * @param targetCurrency 币种换算目的币种。
	 * @param packageId 使用汇率标准的packageId。
	 */
	public static BigDecimal priceExchange(BigDecimal account,String currency,String targetCurrency,Long packageId){
		IPriceStorageService svc = (IPriceStorageService)BeanFactory.getBean("IPriceStorageService");
		return svc.priceExchange(account,currency,targetCurrency,packageId);
	}
	/**
	 * 使用币种库最新汇率标准进行汇率换算
	 * 
	 * @param account 币种换算源币种金额。
	 * @param currency 币种换算源币种。
	 * @param targetCurrency 币种换算目的币种。
	 */
	public static BigDecimal priceExchange(BigDecimal account,String currency,String targetCurrency){
		Long packageId=null;
		IPriceStorageService svc = (IPriceStorageService)BeanFactory.getBean("IPriceStorageService");
		return svc.priceExchange(account,currency,targetCurrency,packageId);
	}
	/**
	 * 币种格式化
	 * 
	 * @param account 货币金额。
	 * @param currency 货币币种。
	 * @param unit 单位 0:元  ，1:万元。
	 * @param accuracy 精度0:保留整数，1保留一位小数，2保留两位小数，最多为8。
	 * @param style 样式0:无，1:大写，2:千分位，3:整数型。
	 */
	public static String priceFormate(BigDecimal account,String currency,String unit,String accuracy,String style){
		IPriceStorageService svc = (IPriceStorageService)BeanFactory.getBean("IPriceStorageService");
		return svc.priceFormate(account,currency,unit,accuracy,style);
	}
	/**
	 * 获取汇率信息，获得packageId对应标段（包）使用的汇率标准、
	 * 
	 * @param packageId 。
	 */
	public static Map<String,BigDecimal> getExchangeRate(Long packageId){
		Date date=null;
		IPriceStorageService svc = (IPriceStorageService)BeanFactory.getBean("IPriceStorageService");
		return svc.getExchangeRate(packageId,date);
	}
	/**
	 * 获取汇率信息，币种库最新汇率标准、
	 * 
	 */
	public static Map<String,BigDecimal> getExchangeRate(){
		Long packageId=null;
		Date date=null;
		IPriceStorageService svc = (IPriceStorageService)BeanFactory.getBean("IPriceStorageService");
		return svc.getExchangeRate(packageId,date);
	}
	/**
	 * 获取汇率信息，币种库最新汇率标准、
	 * 
	 */
	public static Map<String,BigDecimal> getExchangeRate(Date date){
		Long packageId=null;
		IPriceStorageService svc = (IPriceStorageService)BeanFactory.getBean("IPriceStorageService");
		return svc.getExchangeRate(packageId,date);
	}
	/**
	 * 记录标段（包）使用的汇率标准
	 * 
	 * @param packageId 。
	 */
	public static void bindExchangeRate(Long packageId){
		IPriceStorageService svc = (IPriceStorageService)BeanFactory.getBean("IPriceStorageService");
		svc.bindExchangeRate(packageId);
	}
	/**
	 * 记录标段（包）使用的汇率标准
	 * 
	 * @param packageId 。
	 * @param rateMap 汇率信息 。
	 */
	public static void bindExchangeRate(Long packageId,Map<String,BigDecimal> rateMap){
		IPriceStorageService svc = (IPriceStorageService)BeanFactory.getBean("IPriceStorageService");
		svc.bindExchangeRate(packageId,rateMap);
	}
}
