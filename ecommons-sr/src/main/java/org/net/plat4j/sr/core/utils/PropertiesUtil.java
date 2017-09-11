package org.net.plat4j.sr.core.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@SuppressWarnings({"rawtypes"})
public class PropertiesUtil {
	protected final static LogHelper logger = new LogHelper(PropertiesUtil.class);
	public static Map map = new HashMap();
	
	private static PropertiesUtil propertiesUtil = null;
	private PropertiesUtil(){
		
	}
	
	public static PropertiesUtil getInstance(){
		if(propertiesUtil == null){
			synchronized (PropertiesUtil.class) {
				if(propertiesUtil == null){
					propertiesUtil =  new PropertiesUtil();
				}
			}
		}
		return propertiesUtil;
	}
	

	
	/**
	 * 读取.properties配置文件的内容至Map中
	 * @param propertiesFile
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  Map read(String propertiesFile) {
		
		Map maps = (Map)map.get(propertiesFile);
		if(maps == null){
			maps = new HashMap();
			ResourceBundle rb = ResourceBundle.getBundle(propertiesFile);
			Enumeration enu = rb.getKeys();
			while (enu.hasMoreElements()) {
				Object obj = enu.nextElement();
				Object objv = rb.getObject(obj.toString());
				
				if( logger.isDebugEnabled()){
					logger.debug("property ["+ obj +"]:"+ objv);
				}
				maps.put(obj, objv);
			}
			map.put(propertiesFile, maps);
		}
		return maps;
	}
	

}

