package org.net.plat4j.sr.core.base;
/**
 * 输出Model：用户进程信息
 * @author chenshiming
 *
 */
public class SrUserProcessModel {
	//执行结果：执行成功
	public static final String RESULT_CODE_INFO = "info";
	//执行结果：执行成功 但出现警告
	public static final String RESULT_CODE_WARN = "warn";
	//执行结果：执行出错
	public static final String RESULT_CODE_FAIL = "fail";
	//执行结果：执行异常
	public static final String RESULT_CODE_EXCEPTION = "exception";
	
	//错误代码：系统错误
	public static final String ERROR_CODE_SYS = "00000";
	
	
	private String resultCode;//执行结果
	private String resultDesc;//执行结果描述	
	private String resultDescBundle;//结果参数Bundle
	private String resultChineseDesc;//中文的结果描述
	private String[] resultDescArgs;//结果参数
	private String errorCode;//错误代码
	
	//进程异常
	private String exceptionClass;
	private String exceptionMessage;	
	
	private Double userBeginTimeMillis;
	private Double userEndTimeMillis;
	private String userTimeSepType;
//	private String skey1;
//	private String skey2;
//	private String skey3;
//	private String skey4;
//	private String skey5;
//	private Double dkey1;
//	private Double dkey2;
//	private Double dkey3;
//	private Double dkey4;
//	private Double dkey5;
	
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultDesc() {
		return resultDesc;
	}
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	public Double getUserBeginTimeMillis() {
		return userBeginTimeMillis;
	}
	public void setUserBeginTimeMillis(Double userBeginTimeMillis) {
		this.userBeginTimeMillis = userBeginTimeMillis;
	}
	public Double getUserEndTimeMillis() {
		return userEndTimeMillis;
	}
	public void setUserEndTimeMillis(Double userEndTimeMillis) {
		this.userEndTimeMillis = userEndTimeMillis;
	}
	public String getUserTimeSepType() {
		return userTimeSepType;
	}
	public void setUserTimeSepType(String userTimeSepType) {
		this.userTimeSepType = userTimeSepType;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getExceptionClass() {
		return exceptionClass;
	}
	public void setExceptionClass(String exceptionClass) {
		this.exceptionClass = exceptionClass;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	/**
	 * @return the resultDescArgs
	 */
	public String[] getResultDescArgs() {
		return resultDescArgs;
	}
	/**
	 * @param resultDescArgs the resultDescArgs to set
	 */
	public void setResultDescArgs(String[] resultDescArgs) {
		this.resultDescArgs = resultDescArgs;
	}
	/**
	 * @return the resultDescBundle
	 */
	public String getResultDescBundle() {
		return resultDescBundle;
	}
	/**
	 * @param resultDescBundle the resultDescBundle to set
	 */
	public void setResultDescBundle(String resultDescBundle) {
		this.resultDescBundle = resultDescBundle;
	}
	public String getResultChineseDesc() {
		return resultChineseDesc;
	}
	public void setResultChineseDesc(String resultChineseDesc) {
		this.resultChineseDesc = resultChineseDesc;
	}
	
	
}
