package org.net.plat4j.sr.core.utils;

@SuppressWarnings({"rawtypes"})
public interface IBaseBeanUtils {
	public Object copyProperties(Object dst,Object src,String[] fields) throws Exception;
	public boolean isBeanField(Class cls,String fieldName);
 	public Object getField(Object instance,String fieldName) ; 
	public void setField(Object instance,String fieldName,Object fieldValue ) ; 
}
