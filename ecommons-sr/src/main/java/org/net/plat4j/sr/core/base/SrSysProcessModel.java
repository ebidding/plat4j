package org.net.plat4j.sr.core.base;
/**
 * 输出Model：系统进程信息
 * @author chenshiming
 *
 */
public class SrSysProcessModel {	
	
	//进程定义
	private String serviceName;
	private String serviceClassName;
	private String methodName;
	private String serviceProcessId;
	//进程运行状况
	private Double serverBeginTimeMillis;
	private Double serverEndTimeMillis;
	private Double clientBeginTimeMillis;
	private Double clientEndTimeMillis;	
	
	 
	public String getServiceClassName() {
		return serviceClassName;
	}
	public void setServiceClassName(String serviceClassName) {
		this.serviceClassName = serviceClassName;
	}
	public Double getClientBeginTimeMillis() {
		return clientBeginTimeMillis;
	}
	public void setClientBeginTimeMillis(Double clientBeginTimeMillis) {
		this.clientBeginTimeMillis = clientBeginTimeMillis;
	}
	public Double getClientEndTimeMillis() {
		return clientEndTimeMillis;
	}
	public void setClientEndTimeMillis(Double clientEndTimeMillis) {
		this.clientEndTimeMillis = clientEndTimeMillis;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Double getServerBeginTimeMillis() {
		return serverBeginTimeMillis;
	}
	public void setServerBeginTimeMillis(Double serverBeginTimeMillis) {
		this.serverBeginTimeMillis = serverBeginTimeMillis;
	}
	public Double getServerEndTimeMillis() {
		return serverEndTimeMillis;
	}
	public void setServerEndTimeMillis(Double serverEndTimeMillis) {
		this.serverEndTimeMillis = serverEndTimeMillis;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceProcessId() {
		return serviceProcessId;
	}
	public void setServiceProcessId(String serviceProcessId) {
		this.serviceProcessId = serviceProcessId;
	}
  
	
	
}
