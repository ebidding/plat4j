package org.net.plat4j.sr.core.base;

import java.util.List;

@SuppressWarnings({"unchecked", "rawtypes"})
public class BaseSrModelException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BaseServiceResultModel srModel;
	private Throwable throwable;
	
	public BaseSrModelException(BaseServiceResultModel srModel) {
		this.srModel = srModel;
	}
	public BaseSrModelException(BaseServiceResultModel srModel,Throwable e) {
		this.srModel = srModel;
		this.throwable = e;
	}	
	public BaseSrModelException(String resultCode,BaseMessageModel messageModel){
		BaseServiceResultModel srModel = new BaseServiceResultModel();
		BaseUserProcess process = new BaseUserProcess(); 
		process.setResult(resultCode,srModel,messageModel);
		this.srModel = srModel;		
	}
	
	public BaseSrModelException(BaseMessageModel messageModel){
		BaseServiceResultModel srModel = new BaseServiceResultModel();
		BaseUserProcess process = new BaseUserProcess();
		process.setExceptionResult(srModel,messageModel);
		this.srModel = srModel;
	}	
	
	public BaseSrModelException(BaseMessageModel messageModel,List errors){
		BaseServiceResultModel srModel = new BaseServiceResultModel();
		srModel.setErrors(errors);
		BaseUserProcess process = new BaseUserProcess();
		process.setExceptionResult(srModel,messageModel);
		this.srModel = srModel;
	}
	
	public BaseServiceResultModel getSrModel() {
		return srModel;
	}
	public void setSrModel(BaseServiceResultModel srModel) {
		this.srModel = srModel;
	}		
	public void printStackTrace() {
		if (throwable != null) {
			throwable.printStackTrace();
		} else {
			super.printStackTrace();
		}
	}	
}
