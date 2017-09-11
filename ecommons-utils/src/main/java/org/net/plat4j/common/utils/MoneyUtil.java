/*
	Copyright (C) 2014 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2014-9-19 下午10:35:30
	
	Revision History:
	Version     Date              					Author			Comments
	1.0         	2014-9-19下午10:35:30		chenyj			Create file
=========================================================================
 */

package org.net.plat4j.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <h1>金额格式化类</h1><BR/>
 * 
 * @author chenyj - 2014-9-19
 */
public class MoneyUtil {
	/**
	 * 格式化
	 * 
	 * @param money
	 *            金额（单位为元）
	 * @param pattern
	 *            格式化符号，比如"###.##" "###,###"
	 * @param unit
	 *            转换单位，如果是100，则显示单位为元，如果是1000000则显示的是万元
	 * @return
	 */
	private static String formatMoney(Long money, String pattern, long unit) {
		DecimalFormat decimalFormat = null;
		if (pattern == null) {
			decimalFormat = new DecimalFormat();
		} else {
			decimalFormat = new DecimalFormat(pattern);
		}
		StringBuffer result = new StringBuffer();
		long moneyInt = 0;// 整数部分
		long moneyDecimal = 0;// 小数部分
		if(money < 0){
			result.append("-");
		}
		moneyInt = Math.abs(money) / unit;
		moneyDecimal = Math.abs(money) % unit;
		result.append(decimalFormat.format(moneyInt));
		result.append(".");
		DecimalFormat moneyDecimalFormat = null ;
		if(unit == 1){
			moneyDecimalFormat = new DecimalFormat("00");
		}else if(unit == 10000){
			moneyDecimalFormat = new DecimalFormat("0000");
		}
		result.append(moneyDecimalFormat.format(moneyDecimal));
		return result.toString();
	}
	/**
	 * 格式化
	 * 
	 * @param money
	 *            金额（单位为元）
	 * @param pattern
	 *            格式化符号，比如"###.##" "###,###"
	 * @param unit
	 *            转换单位，如果是100，则显示单位为元，如果是1000000则显示的是万元
	 * @return
	 */
	private static String formatMoney(String money, String pattern, long unit) {
		DecimalFormat decimalFormat = null;
		if (pattern == null) {
			decimalFormat = new DecimalFormat();
		} else {
			decimalFormat = new DecimalFormat(pattern);
		}
		StringBuffer result = new StringBuffer();
		
		String head =money.split("\\.")[0]; // 取整数部分
		String rail =money.split("\\.").length>1?money.split("\\.")[1]:"00"; // 取小数部分
		result.append(decimalFormat.format(Long.valueOf(head)));
		result.append(".");
		DecimalFormat moneyDecimalFormat = null ;
		if(unit == 1){
			moneyDecimalFormat = new DecimalFormat("00");
		}else if(unit == 10000){
			moneyDecimalFormat = new DecimalFormat("0000");
		}
		result.append(rail);
		return result.toString();
	}
	private static String formatMoneyWan(String money, String pattern, long unit) {
		DecimalFormat decimalFormat = null;
		if (pattern == null) {
			decimalFormat = new DecimalFormat();
		} else {
			decimalFormat = new DecimalFormat(pattern);
		}
		StringBuffer result = new StringBuffer();
		
		String head =money.split("\\.")[0]; // 取整数部分
		String rail =money.split("\\.").length>1?money.split("\\.")[1]:"00"; // 取小数部分
		String head1=Double.valueOf(Double.valueOf(head)/10000).toString();
		String rail1=head1.split("\\.").length>1?head1.split("\\.")[1]:"00"; // 取小数部分;
		if(unit==10000){
			head=head1.split("\\.")[0]; // 取整数部分
		}
		result.append(decimalFormat.format(Long.valueOf(head)));
		result.append(".");
		if(unit == 10000){
			String railReal=rail1+rail;
			result.append(railReal);
		}else{
			result.append(rail);
		}
		
		return result.toString();
	}
	/**
	 * 转换成元
	 * 
	 * @param money
	 *            金额
	 * @param type
	 *            1为基本类型，比如123456.23；2为三位以逗号隔开，比如123,456.23；3为四位以逗号隔开，比如12,
	 *            3456.12
	 * @return
	 */
	public static String formatMoneyToYuan(Long money, int type) {
		String pattern = null;
		switch (type) {
		case 1:
			pattern = "#";
			break;
		case 2:
			pattern = "###,###";
			break;
		case 3:
			pattern = "####,####";
			break;
		default:
			pattern = null;
		}
		return formatMoney(money, pattern, 1);
	}
	/**
	 * 转换成元
	 * 
	 * @param money
	 *            金额
	 * @param type
	 *            1为基本类型，比如123456.23；2为三位以逗号隔开，比如123,456.23；3为四位以逗号隔开，比如12,
	 *            3456.12
	 * @return
	 */
	public static String formatMoneyToYuan(String money, int type) {
		String pattern = null;
		switch (type) {
		case 1:
			pattern = "#";
			break;
		case 2:
			pattern = "###,###";
			break;
		case 3:
			pattern = "####,####";
			break;
		default:
			pattern = null;
		}
		return formatMoney(money, pattern, 1);
	}
	/**
	 * 转换成元
	 * 
	 * @param money
	 *            金额
	 * @param type
	 *            1为基本类型，比如123456.231234；2为三位以逗号隔开，比如123,456.231,234；3为四位以逗号隔开，
	 *            比如12,3456.1212,34
	 * @return
	 */
	public static String formatMoneyToWanYuan(String money, int type) {
		String pattern = null;
		switch (type) {
		case 1:
			pattern = "#";
			break;
		case 2:
			pattern = "###,###";
			break;
		case 3:
			pattern = "####,####";
			break;
		default:
			pattern = null;
		}
		return formatMoneyWan(money, pattern, 10000);
	}
	/**
	 * 转换成元
	 * 
	 * @param money
	 *            金额
	 * @param type
	 *            1为基本类型，比如123456.231234；2为三位以逗号隔开，比如123,456.231,234；3为四位以逗号隔开，
	 *            比如12,3456.1212,34
	 * @return
	 */
	public static String formatMoneyToWanYuan(Long money, int type) {
		String pattern = null;
		switch (type) {
		case 1:
			pattern = "#";
			break;
		case 2:
			pattern = "###,###";
			break;
		case 3:
			pattern = "####,####";
			break;
		default:
			pattern = null;
		}
		return formatMoney(money, pattern, 10000);
	}
	/**
	 * 单位为元
	 * @param money
	 * @return
	 */
	public static String formatMoneyToUpper(String money) {
		char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
		char[] vunit = { '万', '亿' }; // 段名表示
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
		String head =money.split("\\.")[0]; // 取整数部分
		String rail =money.split("\\.").length>1?money.split("\\.")[1]:"00"; // 取小数部分
		String prefix = ""; // 整数部分转化的结果
		String suffix = ""; // 小数部分转化的结果
		// 处理小数点后面的数
		if (rail.equals("00")) { // 如果小数部分为0
			suffix = "整";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "角" + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
		}
		// 处理小数点前面的数
		char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
		char zero = '0'; // 标志'0'表示出现过0
		byte zeroSerNum = 0; // 连续出现0的次数
		for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
			int idx = (chDig.length - i - 1) % 4; // 取段内位置
			int vidx = (chDig.length - i - 1) / 4; // 取段位置
			if (chDig[i] == '0') { // 如果当前字符是0
				zeroSerNum++; // 连续0次数递增
				if (zero == '0') { // 标志
					zero = digit[0];
				} else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				continue;
			}
			zeroSerNum = 0; // 连续0次数清零
			if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0']; // 转化该数字表示
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
			}
		}
		if (prefix.length() > 0)
			prefix += '圆'; // 如果整数部分存在,则有圆的字样
		return prefix + suffix; // 返回正确表示
	}
	public static String formatMoneyToUpper(Long money) {
		char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
		char[] vunit = { '万', '亿' }; // 段名表示
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
		long midVal = money.longValue()*100; // 转化成整形
		String valStr = String.valueOf(midVal); // 转化成字符串
		String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
		String rail = valStr.substring(valStr.length() - 2); // 取小数部分
		String prefix = ""; // 整数部分转化的结果
		String suffix = ""; // 小数部分转化的结果
		// 处理小数点后面的数
		if (rail.equals("00")) { // 如果小数部分为0
			suffix = "整";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "角" + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
		}
		// 处理小数点前面的数
		char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
		char zero = '0'; // 标志'0'表示出现过0
		byte zeroSerNum = 0; // 连续出现0的次数
		for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
			int idx = (chDig.length - i - 1) % 4; // 取段内位置
			int vidx = (chDig.length - i - 1) / 4; // 取段位置
			if (chDig[i] == '0') { // 如果当前字符是0
				zeroSerNum++; // 连续0次数递增
				if (zero == '0') { // 标志
					zero = digit[0];
				} else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				continue;
			}
			zeroSerNum = 0; // 连续0次数清零
			if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0']; // 转化该数字表示
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
			}
		}
		if (prefix.length() > 0)
			prefix += '圆'; // 如果整数部分存在,则有圆的字样
		return prefix + suffix; // 返回正确表示
	}
	//加法
	public static BigDecimal add(BigDecimal amount1,BigDecimal amount2){
		BigDecimal res=new BigDecimal(0);
		if(amount1==null)amount1=res;
		if(amount2==null)amount2=res;
		return amount1.add(amount2);
	};
	//减法
	public static BigDecimal subtract(BigDecimal amount1,BigDecimal amount2){
		BigDecimal res=new BigDecimal(0);
		if(amount1==null)amount1=res;
		if(amount2==null)amount2=res;
		return amount1.subtract(amount2);
	};
	
	
	public static void main(String[] args) {
		String a=MoneyUtil.formatMoneyToYuan("44.34341",1);
		System.out.println(a);
	}
	
}
