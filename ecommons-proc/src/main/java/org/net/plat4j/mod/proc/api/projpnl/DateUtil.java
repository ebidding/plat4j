package org.net.plat4j.mod.proc.api.projpnl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * 是否小于等于当天
	 * @param date
	 * @return
	 */
	public static boolean isTodayOrLater(Date date){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(date);
		String todayStr = sdf.format(new Date());
		
        return todayStr.compareTo(dateStr)>=0;
	}
}
