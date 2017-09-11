package org.net.plat4j.sr.core.base;

public class BaseTokenException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] args;
	public BaseTokenException(String[] args){
		this.args = args;
	}
	public BaseServiceResultModel getSrModel(){
		SrUserProcessModel srUserProcessModel = new SrUserProcessModel();
		srUserProcessModel.setResultCode(SrUserProcessModel.RESULT_CODE_EXCEPTION);
		srUserProcessModel.setErrorCode(SrUserProcessModel.ERROR_CODE_SYS);		
		srUserProcessModel.setResultDescBundle("resource");
		srUserProcessModel.setResultDesc("message.exception.tokenerror");	
		srUserProcessModel.setResultDescArgs(args);
		
		BaseServiceResultModel srModel = new BaseServiceResultModel();
		srModel.setSrUserProcessModel(srUserProcessModel);
		
		return srModel;
	}
	 
}
