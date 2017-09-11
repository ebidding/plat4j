package org.net.plat4j.sr.core.base.ws;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import org.net.plat4j.sr.core.utils.LogHelper;

public class BaseDisposeAxisWebServcieClient {
	private static LogHelper logger = new LogHelper(BaseDisposeAxisWebServcieClient.class);
	public static void main(String[] args){
		String url = "http://127.0.0.1:8081/ebidding/services/JsonBaseDisposeAxisWebServcie";
		String methodName = "viewCompany";
		String spModelJson = "{\"id\":\"761\"}";
		String srModelJson = invokeJsonService(url,methodName,spModelJson);
		System.out.println(srModelJson);
	}
	
	public static String invokeJsonService(String url,String methodName,String spModelJson){
		String srModelJson = null;
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url));
			call.setOperationName("invokeJsonService");
			srModelJson = (String) call.invoke(new Object[]{methodName,spModelJson});
		} catch (Exception e) {
			logger.error(e);
		}		
		return srModelJson;
	}	
}
