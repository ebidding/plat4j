package org.net.plat4j.sr.core.base;



public class ClientApiResultStringModel {
	
	public String result_code;
	public String result_message; 
	public String result_content;
	
	public ClientApiResultStringModel() {
		
	}

	public ClientApiResultStringModel(String result_code,String result_message) {
		this.result_code = result_code;
		this.result_message = result_message;
	}
	
	public ClientApiResultStringModel(String result_code,String result_message,String result_content) {
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
	public String getResult_content() {
		return result_content;
	}
	public void setResult_content(String result_content) {
		this.result_content = result_content;
	}
	
}
