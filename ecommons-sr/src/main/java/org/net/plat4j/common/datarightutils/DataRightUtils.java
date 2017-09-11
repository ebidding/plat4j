package org.net.plat4j.common.datarightutils;

import net.plat4j.core.spring.BeanFactory;

public class DataRightUtils {
	
	private static IDataRightUtilService getService(){
		return BeanFactory.getBean("IDataRightService");
	}
	
	public static String getControlSQL(String businessCode){
		return getService().getControlSQL(businessCode);
	}
	
	public static boolean hasDataRight(String businessCode , String idVal){
		return getService().hasDataRight(businessCode, idVal);
	}
}
