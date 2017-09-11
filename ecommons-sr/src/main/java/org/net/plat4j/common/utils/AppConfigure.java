package org.net.plat4j.common.utils;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import org.net.plat4j.sr.core.utils.LogHelper;

public class AppConfigure {
	protected static LogHelper logger = new LogHelper(AppConfigure.class);
	private static String PARAM_CONFIG_FILE_PATH= "appconfig.properties";
	
	private static Properties ps;
	
	public synchronized static void init() {
		
		try { 
			InputStream input = AppConfigure.class.getClassLoader().getResourceAsStream(PARAM_CONFIG_FILE_PATH); 
			ps = new Properties();
			ps.load(input);
		} catch (Exception ex) {
			  logger.error(ex);
		}
	}	
	
	public static String getString(String key) {
		
		return getValue(key);
	}
	
	public static String getProperty(String key) {
		
		return getString(key);
	}
	
	public static int  getInt(String key) {
		
		String value = getValue(key);
		if(StringUtils.isNotEmpty(value))
			return new Integer(value);
		
		return 0;
	}
	
	public static String  getValue(String key) {
		
		if(ps == null)
			init();
		
		try {
			String value = ps.getProperty(key);
			if(StringUtils.isNotEmpty(value))
			     value = new String(value.getBytes("iso-8859-1"),"utf-8");
			return StringUtils.replace(value,"\\", "/");
		} catch(Exception e) {
			 logger.error(e);
		}
		return "";
	}
}
