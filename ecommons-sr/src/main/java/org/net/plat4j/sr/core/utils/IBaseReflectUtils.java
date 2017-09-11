package org.net.plat4j.sr.core.utils;

import java.lang.reflect.Method;
import java.text.ParseException;

import javax.naming.directory.NoSuchAttributeException;

@SuppressWarnings({"rawtypes"})
public interface IBaseReflectUtils {
	public Method getGetMethod(Class cls, String fieldName)
			throws NoSuchMethodException;

	public Method getSetMethod(Class cls, String fieldName)
			throws NoSuchMethodException;

	public Method getMethodByPrex(Class cls, String fieldName, String pre)
			throws NoSuchMethodException;

	public Object getObject(String str, String type)
			throws NoSuchAttributeException, ParseException;
	
	public boolean hasMethod(Class cls, String fieldName, String pre);
	
	public boolean hasGetMethod(Class cls, String fieldName);
	public boolean hasSetMethod(Class cls, String fieldName);
}
