package org.net.plat4j.sr.core.utils;

import java.util.Locale;

public interface IBaseUserThreadInfo {
	public String getTheme();
	public Locale getLanguage();
	public String getLocalResource();
	public Boolean getDebug();
	public void setTheme(String value);
	public void setLanguage(Locale value);
	public void setDebug(String debug);
	public void setLocalResource(String value);
	public void setDefaultLocalResource(String value);
	public void setDefaultLanguage(String value) ;
	public void setDefaultTheme(String value);
	public void setDefaultDebug(String value);
}
