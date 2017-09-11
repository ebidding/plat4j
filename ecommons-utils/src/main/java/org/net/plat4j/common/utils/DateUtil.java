/*
 * Created on 2003-12-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.net.plat4j.common.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;

import org.net.plat4j.sr.core.utils.LogHelper;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DateUtil {
	private static LogHelper log = new LogHelper(DateUtil.class);
	public static Date parse(String str) {
		
		if (str == null || str.trim().length() == 0) return null;
		Date ret = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			ret = sdf.parse(str);
		} catch (ParseException ex) {
			log.error(ex);
		}
		
		return ret;
	}
	
	
	public static Date parse(String str,String format) {
		
		if (str == null || str.trim().length() == 0) return null;
		Date ret = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		try {
			ret = sdf.parse(str);
		} catch (ParseException ex) {
			log.error(ex);
		}
		
		return ret;
	}
    
    
    
    private static String datePattern = "yyyyMMdd";

	private static String timePattern = datePattern + " HH:MM a";

	public static final String[] STR_YEAR = { "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };

	public static final String[] WEEK_ZH = { "星期日", "星期一", "星期二", "星期三", "星期四",
			"星期五", "星期六", "星期日" };

	public static final String[] MONTH_ZH = { "一月", "二月", "三月", "四月", "五月", "六月",
			"七月", "八月", "九月", "十月", "十一月", "十二月" };

	public static final String[] MONTH_UP = { "JANUARY", "FEBRUARY", "MARCH",
			"APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER",
			"NOVEMBER", "DECEMBER" };

	public static final String[] MONTH_LO = { "Janury", "February", "March", "April",
			"May", "June", "July", "Auguest", "September", "October",
			"Novenber", "December" };

	public static final String[] MON_UP = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN",
			"JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };

	public static final String[] MON_LO = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
			"Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	// ~ Methods
	// ================================================================

	/**
	 * Return default datePattern (MMddyyyy)
	 *
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		return datePattern;
	}

	/**
	 * This method attempts to convert an Oracle-formatted date in the form
	 * dd-MMM-yyyy to mm/dd/yyyy.
	 *
	 * @param aDate
	 *            date from database as a string
	 * @return formatted string for the ui
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(datePattern);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date/time in the
	 * format you specify on input
	 *
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return date;
	}

	/**
	 * This method returns the current date time in the format: MM/dd/yyyy HH:MM
	 * a
	 *
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 *
	 * @return the current date
	 * @throws ParseException
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(datePattern);

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 *
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * @see java.text.SimpleDateFormat
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return returnValue;
	}

	/**
	 * This method generates a string representation of a date based on the
	 * System Property 'dateFormat' in the format you specify on input
	 *
	 * @param aDate
	 *            A date to convert
	 * @return a string representation of the date
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(datePattern, aDate);
	}

	/**
	 * This method converts a String to a date using the datePattern
	 *
	 * @param strDate
	 *            the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String strDate)
			throws ParseException {
		Date aDate = null;

		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + datePattern);
			}

			aDate = convertStringToDate(datePattern, strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate
					+ "' to a date, throwing exception");
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());

		}

		return aDate;
	}

	private static String divisionChars = "- / , . ; :";

	private static String intToString(int val, double len) {
		Double dmask = new Double(Math.pow(10, len));
		int mask = dmask.intValue();
		return String.valueOf(val + mask).substring(1);
	}

	private static String intToString(int val, int len) {
		Integer ilen = new Integer(len);
		return intToString(val, ilen.doubleValue());
	}

	private static StringTokenizer filterDivision(String str) {
		StringTokenizer divisionToken;
		StringTokenizer strToken;

		divisionToken = new StringTokenizer(divisionChars, " ", false);

		while (divisionToken.hasMoreTokens()) {
			strToken = new StringTokenizer(str, divisionToken.nextToken(),
					false);
			str = "";
			while (strToken.hasMoreTokens()) {
				str += strToken.nextToken();
				str += " ";
			}
		}

		strToken = new StringTokenizer(str, " ", false);

		String temp = "";

		if (strToken.countTokens() == 1) {
			if (str.trim().length() == 8) {
				temp = str.substring(0, 4) + " " + str.substring(4, 6) + " "
						+ str.substring(6, 8);
				str = temp;
			} else if (str.trim().length() == 7) {
				if ("0".equals(str.substring(4, 5))) {
					temp = str.substring(0, 4) + " " + str.substring(4, 6)
							+ " " + str.substring(6, 7);
				} else {
					temp = str.substring(0, 4) + " " + str.substring(4, 5)
							+ " " + str.substring(5, 7);
				}
				str = temp;
			} else if (str.trim().length() == 6) {
				temp = str.substring(0, 4) + " " + str.substring(4, 5) + " "
						+ str.substring(5, 6);
				str = temp;
			}
		}

		strToken = new StringTokenizer(str, " ", false);

		return strToken;
		/*
		 * str = ""; while (strToken.hasMoreTokens ()) { str +=
		 * strToken.nextToken(); str += " "; } return str;
		 */
	}

	private static HashMap parseInput(StringTokenizer strToken,
			StringTokenizer fmtToken) throws Exception {
		if (strToken.countTokens() != fmtToken.countTokens())
			throw new Exception("Not match value string and format string!");

		HashMap hm = new HashMap();
		while (fmtToken.hasMoreTokens()) {
			hm.put(fmtToken.nextToken(), strToken.nextToken());
		}
		return hm;
	}

	private static Calendar parseYear(Calendar cal, HashMap hm, Locale lo)
			throws Exception {
		int year;

		if (hm.get("YYYY") != null) {
			try {
				year = Integer.parseInt((String) hm.get("YYYY"));
				cal.set(Calendar.YEAR, year);
			} catch (NumberFormatException nfe) {
				throw new Exception("The year string is not valid!");
			}
			return cal;
		}

		if (hm.get("YY") != null) {
			try {
				year = Integer.parseInt((String) hm.get("YY"));
				if (year < 50)
					year += 2000;
				else
					year += 1900;

				cal.set(Calendar.YEAR, year);
			} catch (NumberFormatException nfe) {
				throw new Exception("The year string is not valid!");
			}
			return cal;
		}

		return cal;
	}

	private static Calendar parseMonth(Calendar cal, HashMap hm, Locale lo)
			throws Exception {
		int month = 0;

		if (hm.get("MM") != null) {
			try {
				month = Integer.parseInt((String) hm.get("MM")) - 1;
				cal.set(Calendar.MONTH, month);
			} catch (NumberFormatException nfe) {
				throw new Exception("The month string is not valid!");
			}
			return cal;
		} else if (hm.get("Mon") != null || hm.get("MON") != null) {
			try {
				String mon = ((String) hm.get("Mon")).toUpperCase();
				for (int i = 0, l = MON_UP.length; i < l; i++) {
					if (mon.equals(MON_UP[i])) {
						month = i;
					}
				}

				cal.set(Calendar.MONTH, month);
			} catch (NumberFormatException nfe) {
				throw new Exception("The month string is not valid!");
			}
			return cal;
		}
		return cal;
	}

	private static Calendar parseDate(Calendar cal, HashMap hm, Locale lo)
			throws Exception {
		int date;

		if (hm.get("DD") != null) {
			try {
				date = Integer.parseInt((String) hm.get("DD"));
				cal.set(Calendar.DATE, date);
			} catch (NumberFormatException nfe) {
				throw new Exception("The date string is not valid!");
			}
			return cal;
		}
		return cal;
	}

	private static Calendar parseHour(Calendar cal, HashMap hm, Locale lo)
			throws Exception {
		int hour;

		if (hm.get("HH24") != null) {
			try {
				hour = Integer.parseInt((String) hm.get("HH24"));
				cal.set(Calendar.HOUR_OF_DAY, hour);
			} catch (NumberFormatException nfe) {
				throw new Exception("The hour string is not valid!");
			}
			return cal;
		}

		if (hm.get("HH") != null) {
			try {
				hour = Integer.parseInt((String) hm.get("HH"));
				cal.set(Calendar.HOUR, hour);
			} catch (NumberFormatException nfe) {
				throw new Exception("The hour string is not valid!");
			}
			return cal;
		}

		return cal;
	}

	private static Calendar parseMinute(Calendar cal, HashMap hm, Locale lo)
			throws Exception {
		int minute;

		if (hm.get("MI") != null) {
			try {
				minute = Integer.parseInt((String) hm.get("MI"));
				cal.set(Calendar.MINUTE, minute);
			} catch (NumberFormatException nfe) {
				throw new Exception("The minute string is not valid!");
			}
			return cal;
		}
		return cal;
	}

	private static Calendar parseSecond(Calendar cal, HashMap hm, Locale lo)
			throws Exception {
		int second;

		if (hm.get("SS") != null) {
			try {
				second = Integer.parseInt((String) hm.get("SS"));
				cal.set(Calendar.SECOND, second);
			} catch (NumberFormatException nfe) {
				throw new Exception("The second string is not valid!");
			}
			return cal;
		}
		return cal;
	}

	private static Calendar parseMeridian(Calendar cal, HashMap hm, Locale lo)
			throws Exception {
		int meridian = 0;
		String meridianStr;

		if (hm.get("AM") != null || hm.get("A.M.") != null
				|| hm.get("PM") != null || hm.get("P.M.") != null) {
			meridianStr = (String) hm.get("AM");
			if (meridianStr == null)
				meridianStr = (String) hm.get("A.M.");
			if (meridianStr == null)
				meridianStr = (String) hm.get("PM");
			if (meridianStr == null)
				meridianStr = (String) hm.get("P.M.");

			switch (lo.hashCode()) {
			// US
			case 1591:
				if ("PM".equals(meridianStr) || "P.M.".equals(meridianStr))
					meridian = 1;
				else if (!"AM".equals(meridianStr)
						&& !"A.M.".equals(meridianStr))
					throw new Exception("The meridian string is wrong!");

				break;
			// SIMPLIFIED_CHINESE
			case 1861:
				if ("下午".equals(meridianStr))
					meridian = 1;
				else if (!"上午".equals(meridianStr))
					throw new Exception("The meridian string is wrong!");

				break;
			default:
				if ("PM".equals(meridianStr) || "P.M.".equals(meridianStr))
					meridian = 1;
				else if (!"AM".equals(meridianStr)
						&& !"A.M.".equals(meridianStr))
					throw new Exception("The meridian string is wrong!");

				break;
			}
			cal.set(Calendar.AM_PM, meridian);
			return cal;
		}

		return cal;
	}

	public static Date stringToDate(String str, String fmt) throws Exception {
		return stringToCalendar(str, fmt).getTime();
	}

	public static Date stringToDate(String str, String fmt, Locale lo)
			throws Exception {
		return stringToCalendar(str, fmt, lo).getTime();
	}

	public static Calendar stringToCalendar(String str, String fmt)
			throws Exception {
		return stringToCalendar(str, fmt, Locale.US);
	}

	public static Calendar stringToCalendar(String str, String fmt, Locale lo)
			throws Exception {

		Calendar cal = Calendar.getInstance();
		HashMap hm = parseInput(filterDivision(str), filterDivision(fmt));

		cal = parseYear(cal, hm, lo);
		cal = parseMonth(cal, hm, lo);
		cal = parseDate(cal, hm, lo);
		cal = parseHour(cal, hm, lo);
		cal = parseMinute(cal, hm, lo);
		cal = parseSecond(cal, hm, lo);
		cal = parseMeridian(cal, hm, lo);

		return cal;
	}

	private static String parseYear(Calendar cal, String dtStr, Locale lo)
			throws Exception {
		StringBuffer strBuf;
		try {
			while (dtStr.lastIndexOf("YYYY") > -1) {
				strBuf = new StringBuffer(dtStr);
				strBuf.replace(dtStr.lastIndexOf("YYYY"), dtStr
						.lastIndexOf("YYYY") + 4, String.valueOf(cal
						.get(Calendar.YEAR)));
				dtStr = strBuf.toString();
			}

			while (dtStr.lastIndexOf("YY") > -1) {
				strBuf = new StringBuffer(dtStr);
				strBuf.replace(dtStr.lastIndexOf("YY"),
						dtStr.lastIndexOf("YY") + 2, String.valueOf(
								cal.get(Calendar.YEAR)).substring(2));
				dtStr = strBuf.toString();
			}
			return dtStr;
		} catch (StringIndexOutOfBoundsException ex) {
			throw new Exception("Parse Year Error!");
		}
	}

	private static String parseMonth(Calendar cal, String dtStr, Locale lo)
			throws Exception {
		StringBuffer strBuf;
		try {
			while (dtStr.lastIndexOf("MM") > -1) {
				strBuf = new StringBuffer(dtStr);
				strBuf.replace(dtStr.lastIndexOf("MM"),
						dtStr.lastIndexOf("MM") + 2, intToString(cal
								.get(Calendar.MONTH) + 1, 2));
				dtStr = strBuf.toString();
			}

			while (dtStr.lastIndexOf("Mon") > -1) {
				strBuf = new StringBuffer(dtStr);

				switch (lo.hashCode()) {
				// US
				case 1591:
					strBuf.replace(dtStr.lastIndexOf("Mon"), dtStr
							.lastIndexOf("Mon") + 3, MON_LO[cal
							.get(Calendar.MONTH)]);
					break;
				// SIMPLIFIED_CHINESE
				case 1861:
					strBuf.replace(dtStr.lastIndexOf("Mon"), dtStr
							.lastIndexOf("Mon") + 3, MONTH_ZH[cal
							.get(Calendar.MONTH)]);
					break;
				default:
					strBuf.replace(dtStr.lastIndexOf("Mon"), dtStr
							.lastIndexOf("Mon") + 3, MON_LO[cal
							.get(Calendar.MONTH)]);
				}
				dtStr = strBuf.toString();
			}
			return dtStr;
		} catch (StringIndexOutOfBoundsException ex) {
			throw new Exception("Parse Month Error!");
		}
	}

	private static String parseDate(Calendar cal, String dtStr, Locale lo)
			throws Exception {
		StringBuffer strBuf;
		try {
			while (dtStr.lastIndexOf("DD") > -1) {
				strBuf = new StringBuffer(dtStr);
				strBuf.replace(dtStr.lastIndexOf("DD"),
						dtStr.lastIndexOf("DD") + 2, intToString(cal
								.get(Calendar.DATE), 2));
				dtStr = strBuf.toString();
			}
			return dtStr;
		} catch (StringIndexOutOfBoundsException ex) {
			throw new Exception("Parse Date Error!");
		}
	}

	private static String parseHour(Calendar cal, String dtStr, Locale lo)
			throws Exception {
		StringBuffer strBuf;
		try {
			while (dtStr.lastIndexOf("HH24") > -1) {
				strBuf = new StringBuffer(dtStr);
				strBuf.replace(dtStr.lastIndexOf("HH24"), dtStr
						.lastIndexOf("HH24") + 4, intToString(cal
						.get(Calendar.HOUR_OF_DAY), 2));
				dtStr = strBuf.toString();
			}

			while (dtStr.lastIndexOf("HH") > -1) {
				strBuf = new StringBuffer(dtStr);
				strBuf.replace(dtStr.lastIndexOf("HH"),
						dtStr.lastIndexOf("HH") + 2, intToString(cal
								.get(Calendar.HOUR), 2));
				dtStr = strBuf.toString();
			}

			return dtStr;
		} catch (StringIndexOutOfBoundsException ex) {
			throw new Exception("Parse Hour Error!");
		}
	}

	private static String parseMinute(Calendar cal, String dtStr, Locale lo)
			throws Exception {
		StringBuilder strBuf;
		try {
			while (dtStr.lastIndexOf("MI") > -1) {
				strBuf = new StringBuilder(dtStr);
				strBuf.replace(dtStr.lastIndexOf("MI"),
						dtStr.lastIndexOf("MI") + 2, intToString(cal
								.get(Calendar.MINUTE), 2));
				dtStr = strBuf.toString();
			}
			return dtStr;
		} catch (StringIndexOutOfBoundsException ex) {
			throw new Exception("Parse Minute Error!");
		}
	}

	private static String parseSecond(Calendar cal, String dtStr, Locale lo)
			throws Exception {
		StringBuilder strBuf;
		try {
			while (dtStr.lastIndexOf("SS") > -1) {
				strBuf = new StringBuilder(dtStr);
				strBuf.replace(dtStr.lastIndexOf("SS"),
						dtStr.lastIndexOf("SS") + 2, intToString(cal
								.get(Calendar.SECOND), 2));
				dtStr = strBuf.toString();
			}
			return dtStr;
		} catch (StringIndexOutOfBoundsException ex) {
			throw new Exception("Parse Second Error!");
		}
	}

	private static String parseMeridian(Calendar cal, String dtStr, Locale lo)
			throws Exception {
		StringBuilder strBuf;
		int beginIndex;
		int endIndex;
		try {
			while (dtStr.lastIndexOf("AM") > -1 || dtStr.lastIndexOf("PM") > -1) {
				if (dtStr.lastIndexOf("AM") > -1) {
					beginIndex = dtStr.lastIndexOf("AM");
					endIndex = beginIndex + 2;
				} else {
					beginIndex = dtStr.lastIndexOf("PM");
					endIndex = beginIndex + 2;
				}

				strBuf = new StringBuilder(dtStr);

				switch (lo.hashCode()) {
				// US
				case 1591:
					strBuf.replace(beginIndex, endIndex, cal
							.get(Calendar.AM_PM) == 0 ? "=%A=%M" : "=%P=%M");
					break;
				// SIMPLIFIED_CHINESE
				case 1861:
					strBuf.replace(beginIndex, endIndex, cal
							.get(Calendar.AM_PM) == 0 ? "=%上=%午" : "=%下=%午");
					break;
				default:
					strBuf.replace(beginIndex, endIndex, cal
							.get(Calendar.AM_PM) == 0 ? "=%A=%M" : "=%P=%M");
				}
				dtStr = strBuf.toString();
			}

			while (dtStr.lastIndexOf("A.M.") > -1
					|| dtStr.lastIndexOf("P.M.") > -1) {
				strBuf = new StringBuilder(dtStr);
				if (dtStr.lastIndexOf("A.M.") > -1) {
					beginIndex = dtStr.lastIndexOf("A.M.");
					endIndex = beginIndex + 4;
				} else {
					beginIndex = dtStr.lastIndexOf("P.M.");
					endIndex = beginIndex + 4;
				}

				switch (lo.hashCode()) {
				// US
				case 1591:
					strBuf.replace(beginIndex, endIndex, cal
							.get(Calendar.AM_PM) == 0 ? "=%A=%.M=%."
							: "=%P=%.=%M=%.");
					break;
				// SIMPLIFIED_CHINESE
				case 1861:
					strBuf.replace(beginIndex, endIndex, cal
							.get(Calendar.AM_PM) == 0 ? "=%上=%午" : "=%下=%午");
					break;
				default:
					strBuf.replace(beginIndex, endIndex, cal
							.get(Calendar.AM_PM) == 0 ? "=%A=%.M=%."
							: "=%P=%.=%M=%.");
				}
				dtStr = strBuf.toString();
			}

			while (dtStr.lastIndexOf("=%") > -1) {
				strBuf = new StringBuilder(dtStr);
				strBuf.delete(dtStr.lastIndexOf("=%"),
						dtStr.lastIndexOf("=%") + 2);
				dtStr = strBuf.toString();
			}

			return dtStr;
		} catch (StringIndexOutOfBoundsException ex) {
			throw new Exception("Parse Date Error!");
		}
	}

	public static String dateToString(String fmt) throws Exception {
		return dateToString(fmt, Locale.US);
	}

	public static String dateToString(String fmt, Locale lo) throws Exception {
		Calendar cal = Calendar.getInstance();
		return calendarToString(cal, fmt, lo);
	}

	public static String dateToString(Date dt, String fmt) throws Exception {
		return dateToString(dt, fmt, Locale.US);
	}

	public static String dateToString(Date dt, String fmt, Locale lo)
			throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		return calendarToString(cal, fmt, lo);
	}

	public static String calendarToString(Calendar cal, String fmt)
			throws Exception {
		return calendarToString(cal, fmt, Locale.US);
	}

	public static String calendarToString(Calendar cal, String fmt, Locale lo)
			throws Exception {
		String dtStr = fmt;
		dtStr = parseYear(cal, dtStr, lo);
		dtStr = parseMonth(cal, dtStr, lo);
		dtStr = parseDate(cal, dtStr, lo);
		dtStr = parseHour(cal, dtStr, lo);
		dtStr = parseMinute(cal, dtStr, lo);
		dtStr = parseSecond(cal, dtStr, lo);
		dtStr = parseMeridian(cal, dtStr, lo);

		return dtStr;
	}

	public static Date getDate(int year, int month, int date) {
		return getDate(year, month, date, 0, 0, 0);
	}

	public static Date getDate(int year, int month, int date, int hour,
			int minute) {
		return getDate(year, month, date, hour, minute, 0);
	}

	public static Date getDate(int year, int month, int date, int hour,
			int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, date, hour, minute, second);
		return cal.getTime();
	}

	public static Date getDate(String year, String month, String date)
			throws Exception {
		return getDate(year, month, date, "0", "0", "0");
	}

	public static Date getDate(String year, String month, String date,
			String hour, String minute) throws Exception {
		return getDate(year, month, date, hour, minute, "0");
	}

	public static Date getDate(String year, String month, String date,
			String hour, String minute, String second) throws Exception {
		Calendar cal = Calendar.getInstance();
		try {
			cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1,
					Integer.parseInt(date), Integer.parseInt(hour), Integer
							.parseInt(minute), Integer.parseInt(second));
			return cal.getTime();
		} catch (NumberFormatException ne) {
			throw new Exception("Can not convert string to valid int!");
		}
	}

	public static Date beginOfDate(Date dt) {
		int hour = 0;
		int minute = 0;
		int second = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		return cal.getTime();
	}

	public static Date endOfDate(Date dt) {
		int hour = 23;
		int minute = 59;
		int second = 59;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		return cal.getTime();
	}

	// Add millisecond
	public static Date addMillisecond(Date dt, int millisecond) {
		return addSecond(dt, (long) millisecond);
	}

	public static Date addMillisecond(Date dt, long millisecond) {
		dt.setTime(dt.getTime() + millisecond);
		return dt;
	}

	// add second
	public static Date addSecond(Date dt, int second) {
		return addSecond(dt, (long) second);
	}

	public static Date addSecond(Date dt, float second) {
		return addSecond(dt, (double) second);
	}

	public static Date addSecond(Date dt, long second) {
		return addMillisecond(dt, 1000L * second);
	}

	public static Date addSecond(Date dt, double second) {
		Double millisecond = new Double(1000.0 * second);
		return addMillisecond(dt, millisecond.longValue());
	}

	// add minute
	public static Date addMinute(Date dt, int minute) {
		return addMinute(dt, (long) minute);
	}

	public static Date addMinute(Date dt, float minute) {
		return addMinute(dt, (double) minute);
	}

	public static Date addMinute(Date dt, long minute) {
		return addMillisecond(dt, 1000L * 60L * minute);
	}

	public static Date addMinute(Date dt, double minute) {
		Double millisecond = new Double(1000.0 * 60.0 * minute);
		return addMillisecond(dt, millisecond.longValue());
	}

	// add hour
	public static Date addHour(Date dt, int hour) {
		return addHour(dt, (long) hour);
	}

	public static Date addHour(Date dt, float hour) {
		return addHour(dt, (double) hour);
	}

	public static Date addHour(Date dt, long hour) {
		return addMillisecond(dt, 1000L * 60L * 60L * hour);
	}

	public static Date addHour(Date dt, double hour) {
		Double millisecond = new Double(1000.0 * 60.0 * 60.0 * hour);
		return addMillisecond(dt, millisecond.longValue());
	}

	// add day
	public static Date addDay(Date dt, int day) {
		return addDay(dt, (long) day);
	}

	public static Date addDay(Date dt, float day) {
		return addDay(dt, (double) day);
	}

	public static Date addDay(Date dt, long day) {
		return addMillisecond(dt, 1000L * 60L * 60L * 24L * day);
	}

	public static Date addDay(Date dt, double day) {
		Double millisecond = new Double(1000.0 * 60.0 * 60.0 * 24.0 * day);
		return addMillisecond(dt, millisecond.longValue());
	}

	// add month
	public static Date addMonth(Date dt, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + month);
		return cal.getTime();
	}

	public static Date addMonth(Date dt, float month) {
		return addMonth(dt, (double) month);
	}

	public static Date addMonth(Date dt, long month) {
		return addMonth(dt, (new Long(month)).intValue());
	}

	public static Date addMonth(Date dt, double month) {
		double floorMonth = Math.floor(month);
		double decimalMonth = month - floorMonth;
		dt = addMonth(dt, (new Double(floorMonth)).intValue());
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		Date nextdt = cal.getTime();
		long monthMillisecond = nextdt.getTime() - dt.getTime();
		double millisecond = (double) monthMillisecond * decimalMonth;
		return addMillisecond(dt, (long) millisecond);
	}

	// add year
	public static Date addYear(Date dt, int year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + year);
		return cal.getTime();
	}

	public static Date addYear(Date dt, float year) {
		return addYear(dt, (double) year);
	}

	public static Date addYear(Date dt, long year) {
		return addYear(dt, (new Long(year)).intValue());
	}

	public static Date addYear(Date dt, double year) {
		double floorYear = Math.floor(year);
		double decimalYear = year - floorYear;
		dt = addYear(dt, (new Double(floorYear)).intValue());
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
		Date nextdt = cal.getTime();
		long yearMillisecond = nextdt.getTime() - dt.getTime();
		double millisecond = (double) yearMillisecond * decimalYear;
		return addSecond(dt, (long) millisecond);
	}

	public static String getStrYear(int curYear) {
		if (String.valueOf(curYear) == null)
			return null;
		if (curYear - 2000 < 0 || curYear - 2000 > 25)
			return null;
		return STR_YEAR[curYear - 2000];
	}

	/**
	 * 按格式返回当前日期字符串
	 *
	 * @param str
	 * @return
	 */
	public static String getCurDate(String str) {
		try {
			return dateToString(new Date(), str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 按格式返回当前日期字符串
	 *
	 * @param str
	 * @return
	 */
	public static String getCurDate(Calendar calendar, String str) {
		try {
			return calendarToString(calendar, str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 判断是否为闰年
	 *
	 * @param str
	 * @param datePattern
	 * @return
	 * @throws Exception
	 */
	public static boolean isLeapYear(String str, String datePattern)
			throws Exception {
		Calendar cal = stringToCalendar(str, datePattern);
		GregorianCalendar gc = new GregorianCalendar();

		return gc.isLeapYear(cal.get(Calendar.YEAR));
	}

	/**
	 * 判断是否为闰年
	 *
	 * @param str
	 * @return
	 */
	public static boolean isLeapYear(String str) throws Exception {
		return isLeapYear(str, datePattern);
	}

	/**
	 * 取得一个月的最后一天
	 *
	 * @param i
	 * @return
	 */
	public static int getLastDayOfMonth(int year, int month) {
		int day = 30;

		switch (month) {
		case 1:
			day = 31;
			break;
		case 2:
			// 判断是否是闰年
			GregorianCalendar gc = new GregorianCalendar();
			if (gc.isLeapYear(year)) {
				day = 29;
			} else {
				day = 28;
			}
			break;
		case 3:
			day = 31;
			break;

		case 4:
			day = 30;
			break;

		case 5:
			day = 31;
			break;

		case 6:
			day = 30;
			break;

		case 7:
			day = 31;
			break;

		case 8:
			day = 31;
			break;

		case 9:
			day = 30;
			break;

		case 10:
			day = 31;
			break;

		case 11:
			day = 30;
			break;

		case 12:
			day = 31;
			break;

		default:
			day = 30;
			break;
		}

		return day;
	}

	/**
	 * 取得一个月的最后一天
	 *
	 * @param i
	 * @return
	 */
	public static int getLastDayOfMonth(String str, String datePattern)
			throws Exception {
		Calendar cal = stringToCalendar(str, datePattern);
		return getLastDayOfMonth(cal.get(Calendar.YEAR), cal
				.get(Calendar.MONTH));
	}

	/**
	 * 取得一个月的最后一天
	 *
	 * @param i
	 * @return
	 */
	public static int getLastDayOfMonth(String str) throws Exception {
		Calendar cal = stringToCalendar(str, datePattern);
		return getLastDayOfMonth(cal.get(Calendar.YEAR), cal
				.get(Calendar.MONTH) + 1);
	}

	/**
	 * 取得上年末的会计期
	 *
	 * @param date
	 * @return
	 */
	public static String getLastYearAcctPeriodNo(String acctPeriodNo) {
		String ret = "";
		acctPeriodNo = StringUtils.defaultIfEmpty(acctPeriodNo, "");
		if (acctPeriodNo.equals("") || acctPeriodNo.length() != 6) {
			return "";
		}

		String strYear = acctPeriodNo.substring(0, 4);
		String strMonth = acctPeriodNo.substring(4, 6);

		if (strMonth.equals("00")) {
			ret = String.valueOf(Integer.parseInt(strYear) - 2) + "13";
		} else {
			ret = String.valueOf(Integer.parseInt(strYear) - 1) + "13";
		}

		return ret;
	}

	/**
	 * 取得当前会计期的上一会计期
	 *
	 * @param acctPeriodNo
	 * @return
	 */
	public static String getLastMonth(String acctPeriodNo) {
		String ret = "";
		acctPeriodNo = StringUtils.defaultIfEmpty(acctPeriodNo, "");
		if (acctPeriodNo.equals("") || acctPeriodNo.length() != 6) {
			return "";
		}

		String strYear = acctPeriodNo.substring(0, 4);
		String strMonth = acctPeriodNo.substring(4, 6);

		if (strMonth.equals("01")) {
			ret = String.valueOf(Integer.parseInt(strYear) - 1) + "12";
		} else {
			int month = Integer.parseInt(strMonth) - 1;
			strMonth = month > 9 ? String.valueOf(month) : "0" + month;
			ret = strYear + strMonth;
		}

		return ret;
	}

	/**
	 * 根据传入会计期取得传入会计期所在季度的最后一个会计期
	 *
	 * @param acctPeriodNo
	 * @return
	 */
	public static String getLastPeriodOfQuarter(String acctPeriodNo) {
		String ret = "";
		acctPeriodNo = StringUtils.defaultIfEmpty(acctPeriodNo, "");
		if (acctPeriodNo.equals("") || acctPeriodNo.length() != 6) {
			return "";
		}

		String strYear = acctPeriodNo.substring(0, 4);

		// 取得当前会计期的季度
		int quarter = DateUtil.getQuarterOfMonth(acctPeriodNo);

		// 取得当前季度的最后一月
		String month = DateUtil.getLastMonthOfQuarter(quarter);

		ret = strYear + month;

		return ret;
	}

	/**
	 * 根据传入会计期取得传入会计期所在季度的上年最后一个会计期
	 *
	 * @param acctPeriodNo
	 * @return
	 */
	public static String getLastYearPeriodOfQuarter(String acctPeriodNo) {
		String ret = "";
		acctPeriodNo = StringUtils.defaultIfEmpty(acctPeriodNo, "");
		if (acctPeriodNo.equals("") || acctPeriodNo.length() != 6) {
			return "";
		}

		int year = Integer.parseInt(acctPeriodNo.substring(0, 4)) - 1;


		// 取得当前会计期的季度
		int quarter = DateUtil.getQuarterOfMonth(acctPeriodNo);

		// 取得当前季度的最后一月
		String month = DateUtil.getLastMonthOfQuarter(quarter);

		ret = String.valueOf(year) + month;

		return ret;
	}

	/**
	 * 根据传入会计期取得传入会计期上季度的最后一个会计期
	 *
	 * @param acctPeriodNo
	 * @return
	 */
	public static String getLastPeriodOfLastQuarter(String acctPeriodNo) {
		String ret = "";
		acctPeriodNo = StringUtils.defaultIfEmpty(acctPeriodNo, "");
		if (acctPeriodNo.equals("") || acctPeriodNo.length() != 6) {
			return "";
		}

		int year = Integer.parseInt(acctPeriodNo.substring(0, 4));

		// 取得当前会计期的季度
		int quarter = DateUtil.getQuarterOfMonth(acctPeriodNo);

		if(quarter == 1){
			year = year - 1;
			quarter = 4;
		}else{
			quarter = quarter - 1;
		}

		// 取得当前季度的最后一月
		String month = DateUtil.getLastMonthOfQuarter(quarter);

		ret = String.valueOf(year) + month;

		return ret;
	}

	/**
	 * 取得当前会计期所在的季度
	 *
	 * @param acctPeriodNo
	 * @return
	 */
	public static int getQuarterOfMonth(String acctPeriodNo) {
		int ret = 1;
		acctPeriodNo = StringUtils.defaultIfEmpty(acctPeriodNo, "");
		if (acctPeriodNo.equals("") || acctPeriodNo.length() != 6) {
			return 1;
		}

		int month = Integer.parseInt(acctPeriodNo.substring(4, 6));

		switch (month) {
		case 1:
		case 2:
		case 3:
			ret = 1;
			break;

		case 4:
		case 5:
		case 6:
			ret = 2;
			break;

		case 7:
		case 8:
		case 9:
			ret = 3;
			break;

		case 10:
		case 11:
		case 12:
			ret = 4;
			break;

		default:
			ret = 1;
			break;
		}

		return ret;
	}

	/**
	 * 取得当前季度所在的最后的月份
	 *
	 * @param acctPeriodNo
	 * @return
	 */
	public static String getLastMonthOfQuarter(int quarter) {
		String ret = "03";

		switch (quarter) {
		case 1:
			ret = "03";
			break;

		case 2:
			ret = "06";
			break;

		case 3:
			ret = "09";
			break;

		case 4:
			ret = "12";
			break;

		default:
			ret = "01";
			break;

		}

		return ret;
	}

	public static int diff(Date bDate, Date eDate, boolean include) {
		Calendar c1 = new GregorianCalendar();
		Calendar c2 = new GregorianCalendar();
		c1.setTime(bDate);
		c2.setTime(eDate);
		long time = c2.getTimeInMillis() - c1.getTimeInMillis();
		int diff = (int) (time / (24 * 60 * 60 * 1000));
		if (include)
			diff += 1;
		return diff;
	}
	
	public static long getMonthDays(String day) {
		long days =0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar d1 = Calendar.getInstance();
			Calendar d2 = Calendar.getInstance();

			d1.setTime(sdf.parse(day));
			d2.setTime(sdf.parse(day));
			d2.add(Calendar.MONTH, 1);
			 days = (d2.getTimeInMillis() - d1.getTimeInMillis())
					/ (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			log.error(e);
		}
		return days;
	}
	
	public static String getDayString(int i){
		String v=String.valueOf(i+1);
		if(v.length()==1)
			v="0"+v;
		return v;
	}
	public static String getDayString2(int i){
		String v=String.valueOf(i);
		if(v.length()==1)
			v="0"+v;
		return v;
	}
	
	
}
