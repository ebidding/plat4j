package org.net.plat4j.sr.core.base;

 
import java.io.Serializable;
import java.util.List;
 

 
/**
 * <h1>Spr Servcie的返回参数基类.</h1>
 * <Strong>编写规范:</strong><ol>
 * <li>只能包含Boolean,Byte,Class,Double,Float,Integer,Long和其类型的数组类型,可以包含Bean,但Bean中的参数也只能包含此规则的参数</li>
 * <li>不能包含boolean,byte,dobule,float,int等小写的java基本型</li>
 * <li>不能包含自定义的List,Map等高级对象</li>
 * <li>spModel除了复杂查询外不复用,内部的参数应该是servcie需要的最小要求</li>
 * </ol>
 * @author chenshiming
 *
 */
public class BaseServiceResultModel implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SrSysProcessModel srSysProcessModel;
	private SrUserProcessModel srUserProcessModel;
	private SrPageModel srPageModel;
	private String isSuccess;
	private String message;
	private String successFlag;
	private List<String> errors;
	private String isAudit;
	private String fileMaxSize = "100M";
	
	
/*	private BaseMessageModel[] baseInfoMessageModels;
	private BaseMessageModel[] baseWarnMessageModels;
	private BaseMessageModel[] baseFailMessageModels;
	private BaseMessageModel[] baseExceptionMessageModels;*/
	
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public SrSysProcessModel getSrSysProcessModel() {
		return srSysProcessModel;
	}
	public void setSrSysProcessModel(SrSysProcessModel srProcessModel) {
		this.srSysProcessModel = srProcessModel;
	}
	public SrPageModel getSrPageModel() {
		return srPageModel;
	}
	public void setSrPageModel(SrPageModel srPageModel) {
		this.srPageModel = srPageModel;
	}
/*	public BaseMessageModel[] getBaseExceptionMessageModels() {
		return baseExceptionMessageModels;
	}
	public void setBaseExceptionMessageModels(
			BaseMessageModel[] baseExceptionMessageModels) {
		this.baseExceptionMessageModels = baseExceptionMessageModels;
	}
	public BaseMessageModel[] getBaseFailMessageModels() {
		return baseFailMessageModels;
	}
	public void setBaseFailMessageModels(BaseMessageModel[] baseFailMessageModels) {
		this.baseFailMessageModels = baseFailMessageModels;
	}
	public BaseMessageModel[] getBaseInfoMessageModels() {
		return baseInfoMessageModels;
	}
	public void setBaseInfoMessageModels(BaseMessageModel[] baseInfoMessageModels) {
		this.baseInfoMessageModels = baseInfoMessageModels;
	}
	public BaseMessageModel[] getBaseWarnMessageModels() {
		return baseWarnMessageModels;
	}
	public void setBaseWarnMessageModels(BaseMessageModel[] baseWarnMessageModels) {
		this.baseWarnMessageModels = baseWarnMessageModels;
	}*/
	public SrUserProcessModel getSrUserProcessModel() {
		return srUserProcessModel;
	}
	public void setSrUserProcessModel(SrUserProcessModel srUserProcessModel) {
		this.srUserProcessModel = srUserProcessModel;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}
	public String getIsAudit() {
		return isAudit;
	}
	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}
	public String getFileMaxSize() {
		return fileMaxSize;
	}
	public void setFileMaxSize(String fileMaxSize) {
		this.fileMaxSize = fileMaxSize;
	}
	
}
