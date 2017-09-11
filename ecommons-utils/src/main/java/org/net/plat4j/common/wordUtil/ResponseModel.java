package org.net.plat4j.common.wordUtil;

public class ResponseModel {
	private int status;//200 成功
	
	private String responseContent; //false 为失败
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getResponseContent() {
		return responseContent;
	}
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}
	
}
