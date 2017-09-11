package org.net.plat4j.sr.core.base;

public class BaseMessageModel {
	public BaseMessageModel(){}
	
	public BaseMessageModel(String message){	
		this.bundle = "resource";
		this.patternName = "message.key.null";
		this.argus = new String[]{message};		
	}
	/**
	 * 
	 * @param bundle
	 * @param patternName
	 *     如果为null，则直接打印后面第一个参数
	 * @param argus
	 */
	public BaseMessageModel(String bundle,String patternName,String[] argus){
		if(patternName==null||patternName.trim().equals("")){
			patternName = "message.key.null";
		}
		this.bundle = bundle;
		this.patternName = patternName;
		this.argus = argus;
	}
	public BaseMessageModel(BaseServiceResultModel srModel){
		this.bundle = srModel.getSrUserProcessModel().getResultDescBundle();
		this.patternName = srModel.getSrUserProcessModel().getResultDesc();
		this.argus = srModel.getSrUserProcessModel().getResultDescArgs();
	}	
	private String bundle;
	private String patternName;
	private String[] argus;
 
	public String getBundle() {
		return bundle;
	}
	public void setBundle(String bundle) {
		this.bundle = bundle;
	}
	public String[] getArgus() {
		return argus;
	}
	public void setArgus(String[] argus) {
		this.argus = argus;
	}
	public String getPatternName() {
		return patternName;
	}
	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}
 
	
	
}
