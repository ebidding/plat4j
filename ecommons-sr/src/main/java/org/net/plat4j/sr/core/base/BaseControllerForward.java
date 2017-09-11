package org.net.plat4j.sr.core.base;


public class BaseControllerForward {
	private String result;
	private BaseServiceResultModel srModel;
	private BaseServiceParamModel spModel;
	
	public BaseControllerForward() {}
	
	public BaseControllerForward(String result) {
		this.result = result;
	}
	
	public BaseControllerForward(String result, BaseServiceParamModel spModel, BaseServiceResultModel srModel) {
		this.result = result;
		this.spModel = spModel;
		this.srModel = srModel;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	/**
	 * @return the spModel
	 */
	public BaseServiceParamModel getSpModel() {
		return spModel;
	}

	/**
	 * @param spModel the spModel to set
	 */
	public void setSpModel(BaseServiceParamModel spModel) {
		this.spModel = spModel;
	}

	/**
	 * @return the srModel
	 */
	public BaseServiceResultModel getSrModel() {
		return srModel;
	}

	/**
	 * @param srModel the srModel to set
	 */
	public void setSrModel(BaseServiceResultModel srModel) {
		this.srModel = srModel;
	}
	
	public BaseServiceResultModel fillSrModel(BaseServiceResultModel orginalModel){
		if(srModel==null){
			return orginalModel;
		}
		srModel.setSrUserProcessModel(orginalModel.getSrUserProcessModel());
		srModel.setSrSysProcessModel(orginalModel.getSrSysProcessModel());
/*		srModel.setBaseInfoMessageModels(orginalModel.getBaseInfoMessageModels());
		srModel.setBaseWarnMessageModels(orginalModel.getBaseWarnMessageModels());
		srModel.setBaseFailMessageModels(orginalModel.getBaseFailMessageModels());
		srModel.setBaseExceptionMessageModels(orginalModel.getBaseExceptionMessageModels());*/
		return srModel;
	}	
}
