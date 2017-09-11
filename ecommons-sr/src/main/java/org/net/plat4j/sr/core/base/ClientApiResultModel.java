package org.net.plat4j.sr.core.base;

import java.util.Map;

@SuppressWarnings({"rawtypes"})
public class ClientApiResultModel {

	public String result_code;
	public String result_message; 
	public Map result_content;
	
	public ClientApiResultModel() {
		
	}

	public ClientApiResultModel(String result_code,String result_message) {
		this.result_code = result_code;
		this.result_message = result_message;
	}
	
	public ClientApiResultModel(String result_code,String result_message,Map result_content) {
		this.result_code = result_code;
		this.result_message = result_message;
		this.result_content = result_content;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getResult_message() {
		return result_message;
	}
	public void setResult_message(String result_message) {
		this.result_message = result_message;
	}

	public Map getResult_content() {
		return result_content;
	}

	public void setResult_content(Map result_content) {
		this.result_content = result_content;
	}
	
}
