/*
	Copyright (C) 2014 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2014-10-26 上午4:44:12
	
	Revision History:
	Version     Date              					Author			Comments
	1.0         	2014-10-26上午4:44:12		chenyj			Create file
=========================================================================
 */

package org.net.plat4j.common.comparator;

import java.util.Comparator;
import java.util.Map;

/**
 * <h1>按照版本号排序，比较器</h1><BR/>
 * 每一个元素为一个map，通过key进行版本号排序
 * 
 * @author chenyj - 2014-10-26
 */
public class MapComparator implements Comparator<Map<String, Object>> {
	/**
	 * 排序用到的key
	 */
	private String key;

	public MapComparator(String key) {
		this.key = key;
	}

	@Override
	public int compare(Map<String, Object> o1, Map<String, Object> o2) {
		// 获取比较的字符串
		String compareValue1 = (String) o1.get(key);
		String compareValue2 = (String) o2.get(key);
		String[] valueSplit1 = compareValue1.split("[.]");
		String[] valueSplit2 = compareValue2.split("[.]");
		int minLength = valueSplit1.length;
		if (minLength > valueSplit2.length) {
			minLength = valueSplit2.length;
		}
		for (int i = 0; i < minLength; i++) {
			int value1 = Integer.parseInt(valueSplit1[i]);
			int value2 = Integer.parseInt(valueSplit2[i]);
			if(value1 > value2){
				return 1;
			}else if(value1 < value2){
				return -1;
			}
		}
		return valueSplit1.length - valueSplit2.length;
	}
}
