package org.net.plat4j.sr.core.base;


public class BaseUserProcess {
	private SrUserProcessModel userProcessModel = new SrUserProcessModel();
/*	private List baseInfoMessageModelList = new ArrayList();
	private List baseWarnMessageModelList = new ArrayList();
	private List baseFailMessageModelList = new ArrayList();
	private List baseExceptionMessageModelList = new ArrayList();*/
	public void setResult(String resuleCode,BaseServiceResultModel srModel,BaseMessageModel messageModel){
		userProcessModel.setResultCode(resuleCode);
		if(messageModel!=null){
			userProcessModel.setResultDescBundle(messageModel.getBundle());
			userProcessModel.setResultDesc(messageModel.getPatternName());
			userProcessModel.setResultDescArgs(messageModel.getArgus());
		}
		fillBaseServiceResultModel(srModel);		
	}
	
	public void setInfoResult(BaseServiceResultModel srModel,BaseMessageModel messageModel){
		userProcessModel.setResultCode(SrUserProcessModel.RESULT_CODE_INFO);
		if(messageModel!=null){
			userProcessModel.setResultDescBundle(messageModel.getBundle());
			userProcessModel.setResultDesc(messageModel.getPatternName());
			userProcessModel.setResultDescArgs(messageModel.getArgus());
		}
		fillBaseServiceResultModel(srModel);
	}
	public void setWarnResult(BaseServiceResultModel srModel,BaseMessageModel messageModel){
		userProcessModel.setResultCode(SrUserProcessModel.RESULT_CODE_WARN);
		if(messageModel!=null){
			userProcessModel.setResultDescBundle(messageModel.getBundle());
			userProcessModel.setResultDesc(messageModel.getPatternName());
			userProcessModel.setResultDescArgs(messageModel.getArgus());
		}		
		fillBaseServiceResultModel(srModel);
	}
	public void setFailResult(BaseServiceResultModel srModel,BaseMessageModel messageModel){
		userProcessModel.setResultCode(SrUserProcessModel.RESULT_CODE_FAIL);
		if(messageModel!=null){
			userProcessModel.setResultDescBundle(messageModel.getBundle());
			userProcessModel.setResultDesc(messageModel.getPatternName());
			userProcessModel.setResultDescArgs(messageModel.getArgus());
		}
		fillBaseServiceResultModel(srModel);
	}
	/**
	 * 
	 */
	public void setExceptionResult(BaseServiceResultModel srModel,Throwable e){
		userProcessModel.setResultCode(SrUserProcessModel.RESULT_CODE_EXCEPTION);
		userProcessModel.setResultDesc("message.key.null");
		userProcessModel.setResultDescBundle("resource");
		if(e!=null){
			userProcessModel.setResultDescArgs(new String[]{e.getMessage()});		
		} 
		fillBaseServiceResultModel(srModel);
	}
	public void setExceptionResult(BaseServiceResultModel srModel,BaseMessageModel messageModel){
		userProcessModel.setResultCode(SrUserProcessModel.RESULT_CODE_EXCEPTION);
		if(messageModel!=null){
			userProcessModel.setResultDescBundle(messageModel.getBundle());
			userProcessModel.setResultDesc(messageModel.getPatternName());
			userProcessModel.setResultDescArgs(messageModel.getArgus());
		}		
		fillBaseServiceResultModel(srModel);
	} 
	private void fillBaseServiceResultModel(BaseServiceResultModel srModel){		
/*		BaseMessageModel[] baseInfoMessageModels = new BaseMessageModel[baseInfoMessageModelList.size()];
		baseInfoMessageModelList.toArray(baseInfoMessageModels);
		srModel.setBaseInfoMessageModels(baseInfoMessageModels);

		BaseMessageModel[] baseWarnMessageModels = new BaseMessageModel[baseWarnMessageModelList.size()];
		baseWarnMessageModelList.toArray(baseWarnMessageModels);
		srModel.setBaseWarnMessageModels(baseWarnMessageModels);

		BaseMessageModel[] baseFailessageModels = new BaseMessageModel[baseFailMessageModelList.size()];
		baseFailMessageModelList.toArray(baseFailessageModels);
		srModel.setBaseFailMessageModels(baseFailessageModels);
		
		BaseMessageModel[] baseExceptionMessageModels = new BaseMessageModel[baseExceptionMessageModelList.size()];
		baseExceptionMessageModelList.toArray(baseExceptionMessageModels);
		srModel.setBaseExceptionMessageModels(baseExceptionMessageModels);
	*/	
		srModel.setSrUserProcessModel(userProcessModel);		
	}
	
	
/*	public void addInfoMessage(BaseMessageModel messageModel){
		baseInfoMessageModelList.add(messageModel);
	}
	public void addWarnMessage(BaseMessageModel messageModel){
		baseWarnMessageModelList.add(messageModel);
	}
	public void addFailMessage(BaseMessageModel messageModel){
		baseFailMessageModelList.add(messageModel);
	}
	*//**
	 * @deprecated
	 *//*
	public void addExceptionMessage(BaseMessageModel messageModel){
		baseExceptionMessageModelList.add(messageModel);
	}	*/
	
}
