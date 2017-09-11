package org.net.plat4j.sr.core.utils;

public interface ITextBeanUtils {
	public void setBaseReflectUtils(IBaseReflectUtils baseReflectUtils);
	public String getTextFromBean(Object bean);
	public Object getBeanFromText(String text);
	public String getFullTextFromBean(Object bean);
}
