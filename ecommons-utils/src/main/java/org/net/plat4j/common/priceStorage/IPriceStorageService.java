package org.net.plat4j.common.priceStorage;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.net.plat4j.common.utils.PriceStorageModel;


public interface IPriceStorageService {
	/**
	 * 获取金额主表Id对应的子表金额信息Map
	 * 
	 * @param mainId 金额主表Id。
	 */
	Map<String,PriceStorageModel> getPriceMap(Long mainId);
	/**
	 * 获取金额主表id对应的字表金额list
	 * @param mainId 金额主表id
	 * @return
	 */
	List<PriceStorageModel> getPriceList(Long mainId);
	/**
	 * 保存金额信息并返回主表id，若有mainId则更新金额信息，若无则新增
	 * 
	 * @param priceMap 存储金额信息对应的map。key为币种，value为数额。
	 * @param mainId 金额主表Id。
	 * @param type 金额主表类型描述。
	 */
	Long savePriceList(List<PriceStorageModel> priceMap,Long mainId,String type);
	/**
	 * 币种换算，若packageId有值则按标段（包）使用的汇率标准进行换算，若没有则使用币种库最新汇率计算
	 * 
	 * @param account 币种换算源币种金额。
	 * @param currency 币种换算源币种。
	 * @param targetCurrency 币种换算目的币种。
	 * @param packageId 使用汇率标准的packageId。
	 */
	BigDecimal priceExchange(BigDecimal account,String currency,String targetCurrency,Long packageId);
	/**
	 * 币种格式化
	 * 
	 * @param account 货币金额。
	 * @param currency 货币币种。
	 * @param unit 单位 0:元  ，1:万元。
	 * @param accuracy 精度0:保留整数，1保留一位小数，2保留两位小数，最多为8。
	 * @param style 样式0:无，1:大写，2:千分位，3:整数型。
	 */
	String priceFormate(BigDecimal account,String currency,String unit,String accuracy,String style);
	/**
	 * 获取汇率信息，获得标段（包）使用的汇率标准，否则获取币种库最新汇率信息
	 * 
	 * @param packageId 。
	 */
	Map<String,BigDecimal> getExchangeRate(Long packageId,Date date);
	/**
	 * 记录标段（包）使用的汇率标准
	 * 
	 * @param packageId 。
	 */
	void bindExchangeRate(Long packageId);
	/**
	 * 记录标段（包）使用的汇率标准
	 * 
	 * @param packageId 。
	 * @param rateMap 。
	 */
	void bindExchangeRate(Long packageId,Map<String,BigDecimal> rateMap);
}
