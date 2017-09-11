package org.net.plat4j.sr.core.utils.impl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.net.plat4j.sr.core.utils.IBaseUserThreadInfo;
import org.net.plat4j.sr.core.utils.LogHelper;

@SuppressWarnings({"rawtypes", "unchecked"})
public class BaseUserThreadInfo implements IBaseUserThreadInfo{
	protected LogHelper logger = new LogHelper(getClass());
	private InheritableThreadLocal threadLocal = new InheritableThreadLocal();
	private static Map keyMap = new HashMap();
	static{
		keyMap.put("theme", "THEMEKEY");
		keyMap.put("language", "LANGUAGE");
		keyMap.put("localResource", "LOCALRESOURCE");
		keyMap.put("debug", "");
	}
	//皮肤（外部资源）
	private String defaultTheme;
	//语言包
	private String defaultLanguage;
	//内部资源
	private String defaultLocalResource;
	//调试模式
	private String defaultDebug;
	
	private Map getMap() {
		Map map = (Map) threadLocal.get();
		if (map == null) {
			map = new HashMap();
			threadLocal.set(map); // Yang Fujun add
		}
		return map;
	}
	public String getTheme(){
		String value = (String)getMap().get(keyMap.get("theme"));
		if(value==null){
			return defaultTheme;
		}else{
			return value;
		}
	}
	public Locale getLanguage(){
		Locale value =  (Locale)getMap().get(keyMap.get("language"));
		if(value==null){
			return new Locale(defaultLanguage); 
		}else{
			return value;
		}	
	}	
	public String getLocalResource(){
		String value =  (String)getMap().get(keyMap.get("localResource"));
		if(value==null){
			return defaultLocalResource;
		}else{
			return value;
		}	
	}
	public Boolean getDebug() {
		String value = (String)getMap().get(keyMap.get("debug"));
		if(value==null){
			return new Boolean(defaultDebug);
		}else{
			return new Boolean(value);
		}
	}	
	public void setTheme(String value){
		getMap().put(keyMap.get("theme"),value);
	}
	public void setDebug(String value) {
		getMap().put(keyMap.get("debug"),value);
	}
	public void setLanguage(Locale value){
		getMap().put(keyMap.get("language"),value);
	}
	public void setLocalResource(String value){
		getMap().put(keyMap.get("localResource"),value);
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}
	public void setDefaultLocalResource(String defaultLocalResource) {
		this.defaultLocalResource = defaultLocalResource;
	}
	public void setDefaultTheme(String defaultTheme) {
		this.defaultTheme = defaultTheme;
	}
	public void setDefaultDebug(String defaultDebug) {
		this.defaultDebug = defaultDebug;
	}	
}
