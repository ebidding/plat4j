package org.net.plat4j.common.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.stat.StatUtils;

public class EbiddingMath {
	private static int scale = 6;
	/**
	 *  取得平均数
	 * @param values
	 * @return
	 */
	public static double mean(double... values){
		BigDecimal  b  =  new  BigDecimal(StatUtils.mean(values));  
		return b.setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 *  取得最小值
	 * @param values
	 * @return
	 */
	public static double min(double... values){
		BigDecimal  b  =  new  BigDecimal(StatUtils.min(values));  
		return b.setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 *  取得最大值
	 * @param values
	 * @return
	 */
	public static double max(double... values){
		BigDecimal  b  =  new  BigDecimal(StatUtils.max(values));  
		return b.setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 去除最低，最高 然后算平均值
	 * @param values
	 * @return
	 */
	public static double mean2(double... values){
	 	Arrays.sort(values);
	 	int size=values.length;
	 	if(size>2){
		 	double newValues[]= new double[size-2];
		 	int count=0;
		 	for (int i = 0; i < size; i++) {
		 		if(i==0 || i==size-1)
		 			continue;
		 		else{
		 			newValues[count++]=values[i];
		 		}
		 			
			}
		 	return	mean(newValues);
	 	}else{
	 		return	mean(values);
	 	}
	 	
	}
	/**
	 * 取价格异常高比较值 （最高-次高）/次高
	 * @param values
	 * @return
	 */
	public static double topPrice(double... values){ 
	 	Arrays.sort(values);
	 	int size=	values.length;
	 	if (size>1) {
	 		double maxNum=values[size-1];
	 		double secondMaxNum=values[size-2];
	 		double resultNum=(maxNum-secondMaxNum)/secondMaxNum;
	 		return	resultNum;
		}else {
			return 0;
		}
	}
	/**
	 * 取去掉价格异常高后其余有效评标价的平均值
	 * @param values
	 * @return
	 */
	public static double mean4(double... values){
	 	Arrays.sort(values);
	 	int size=	values.length;
	 	double newValues[]= new double[size-1];
	 	int count=0;
	 	for (int i = 0; i < size; i++) {
	 		if(i==size-1)
	 			continue;
	 		else{
	 			newValues[count++]=values[i];
	 		}
	 			
		}
	 return	mean(newValues);
	}
	/**
	 * 判断数量是否大于等于法值，大于去除最高最低取平均值
	 * 否则取平均值
	 * @param i 
	 * @return
	 */
	public static double mean0(int i,double... values){
		if(values.length>=i){
			return	mean2(values);
		}else{
			return	mean(values);
		}
					
	}
	/**
	 * 最低价 和 次低价  比较值 （次低 - 最低）/最低
	 * @param values
	 * @return
	 */
	public static double lowerPrice(double... values){
	 	Arrays.sort(values);
	 	
	 	if (values.length>1) {
	 		double minNum=values[0];
	 		double secondMinNum=values[1];
	 		double resultNum=(secondMinNum-minNum)/secondMinNum;
	 		return	resultNum;
			
		}else {
			return 0;
		}
	}
	/**
	 * 判断最高价 高于 次高价 的 X%,是否是无效报价
	 * 无效报价得分为 0，有效报价为 1
	 * @param args
	 */
	public static double isTopPass(double moreNum ,double... values){
		double more = topPrice(values) * 100;
		int count;
		if(more>moreNum){
			count = 0;
		}else {
			count = 1;
		}
	 	return	count;
	}
	/**
	 * 判断最低价 低于 次低价 的 X%,是否是无效报价
	 * 无效报价得分为 0，有效报价为 1
	 * @param args
	 */
	public static double isLowerPass(double moreNum ,double... values){
		double more = lowerPrice(values) * 100;
		int count;
		if(more>moreNum){
			count = 0;
		}else {
			count = 1;
		}
	 	return	count;
	}
	/**
	 * 返回  长度
	 * @param args
	 */
	public static double tenderNum(double... values){
		
		return values.length;
	}
	/**
	 * 去掉最高 X 家、最低 Y 家，取平均值
	 * @param args
	 */   
	public static double outTopLower(int topPrice,int lastPrice,double... values){
		
		//无 有效投标人     取平均值
		if (values.length-topPrice-lastPrice<1) {
			return mean(values);
		}else {
			Arrays.sort(values);
			
			double[] str = new double[values.length-topPrice-lastPrice];
			
			
			int n = 0;
			for (int i = lastPrice; i < (values.length-topPrice); i++) {
				str[n]= values[i];
				n++;
			}
			return mean(str);
		}
	}
	/**
	 * 二次平均价
	 * @param args
	 */
	public static double secondAvg(double... values){
		
		if (values.length>0) {
			
			double avg = mean(values);
			
			double[] tAvg = new double[values.length];
			//低于平均价
			for (int i = 0; i < values.length; i++) {
				if (values[i]<avg) {
					tAvg[i]=values[i];
				}
			}
			//有效投标家数
			int count = 0 ;
			for (int i = 0; i < tAvg.length; i++) {
				if (tAvg[i] != 0) {
					count++;
				}
			}
			int n = 0;
			double[] twoAvg = new double[count];
			for (int i = 0; i < tAvg.length; i++) {
				if (tAvg[i] != 0) {
					twoAvg[n] = tAvg[i];
					n++;
				}
			}
			
			return mean(twoAvg);
		}else {
			return 0;
		}
		
	}
	/**
	 * 从字符串获取BigDecilam数组
	 * @param decimals
	 * @return
	 */
	public static List<BigDecimal> getBidDecimals(String decimals){
		if(StringUtils.isEmpty(decimals))
			return null;
		String[] numberStrs = decimals.split(",");
		List<BigDecimal> results = new ArrayList<BigDecimal>();
		for(int i=0;i<numberStrs.length;i++){
			results.add(new BigDecimal(numberStrs[i])) ;
		}
		return results;
	}
	/**
	 * 取得平均数
	 * @param number
	 * @return
	 */
	public static BigDecimal mean(List<BigDecimal> number){
		BigDecimal sum = new BigDecimal("0");
		for(BigDecimal addNum : number){
			sum.add(addNum);
		}
		sum.divide(new BigDecimal(number.size()+""));
		return sum;
	}
	
	
	
	
	public static void main(String[] args) {
//		double[] a =new double[]{370,360,380,340,350};
		System.out.println(min(4,3,2,0.6,1));
		
	}
}
