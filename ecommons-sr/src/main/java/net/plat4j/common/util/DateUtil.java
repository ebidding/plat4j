/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2015年5月15日 下午3:02:02
	
	Revision History:
	Version     Date              					Author			Comments
	1.0      2015年5月15日下午3:02:02		    		chenyj			Create file
=========================================================================
 */
package net.plat4j.common.util;

import java.util.Calendar;

/**
 * @author chenyj
 *
 */
public class DateUtil {
	public static java.util.Date getDate(String paramString) {
		if ((paramString == null) || (paramString.length() == 0)) {
			return null;
		}
		int i = Integer.parseInt(paramString.substring(0, 4));
		int j = Integer.parseInt(paramString.substring(5, 7));
		int k = Integer.parseInt(paramString.substring(8, 10));
		int m = 0;
		int n = 0;
		int i1 = 0;
		if (paramString.length() >= 13) {
			m = Integer.parseInt(paramString.substring(11, 13));
		}
		if (paramString.length() >= 16) {
			n = Integer.parseInt(paramString.substring(14, 16));
		}
		if (paramString.length() >= 19) {
			i1 = Integer.parseInt(paramString.substring(17, 19));
		}
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.set(i, j - 1, k, m, n, i1);
		java.util.Date localDate = localCalendar.getTime();
		return localDate;
	}
}
