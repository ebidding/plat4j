/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2015年4月15日 下午1:01:51
	
	Revision History:
	Version     Date              					Author			Comments
	1.0      2015年4月15日下午1:01:51							chenyj			Create file
=========================================================================
 */
package org.net.plat4j.sr.core.base.generatecode.impl;

import java.io.File;
import java.text.MessageFormat;

public class EbiddingV3GenerateCode extends AbstractGenerateCode {
	/**
	 * 获取action头部代码
	 * @return
	 */
	protected String getActionHeader(){
		StringBuilder sb = new StringBuilder();
		sb.append("package {0}.action;").append("\r\n\r\n");
		sb.append("import org.net.plat4j.common.core.AppController;").append("\r\n");
		sb.append("import org.net.plat4j.sr.core.base.BaseControllerContext;").append("\r\n");
		sb.append("import org.net.plat4j.sr.core.base.BaseControllerForward;").append("\r\n");
		sb.append("import org.net.plat4j.sr.core.base.BaseServiceResultModel;").append("\r\n");
		sb.append("import org.net.plat4j.sr.core.base.session.BaseUserSession;").append("\r\n");
		sb.append("import {0}.service.I{2}Service;").append("\r\n");
		for(String methodName : methodNames){
			sb.append("import {0}.service.model." + upperFirstChar(methodName) + "SpModel;").append("\r\n");
		}
		sb.append("\r\n/**").append("\r\n");
		sb.append(" * @author {5} - {6}").append("\r\n");
		sb.append(" * @struts.action name=\"{2}Action\" path=\"/{2}Action\" validate=\"false\"").append("\r\n");
		sb.append(" *                parameter=\"method\"").append("\r\n");
		for(String methodName : methodNames){
			sb.append(" * @struts.action-forward name=\"" + methodName + "\"").append("\r\n");
			sb.append(" *                        path=\"/{3}/{1}/" + methodName + ".jsp\"").append("\r\n");
		}
		sb.append(" */").append("\r\n");
		sb.append("public class {2}Action extends AppController '{'").append("\r\n");
		sb.append("\tprivate static final long serialVersionUID = 1L;").append("\r\n");
		sb.append("\r\n");
		return MessageFormat.format(sb.toString(), getParameters());
	}
	/**
	 * 获取action的方法
	 * @param method 方法名
	 * @return
	 */
	protected String getActionMethod(String method){
		StringBuilder sb = new StringBuilder();
		sb.append("	/**").append("\r\n");
		sb.append("	 * <h1>.</h1><BR>").append("\r\n");
		sb.append("	 * ({5} 创建于 {6} )<BR>").append("\r\n");		
		sb.append("	 * <strong>编辑记录:</strong>").append("\r\n");
		sb.append("	 * <ol>").append("\r\n");
		sb.append("	 * <li></li>").append("\r\n");
		sb.append("	 * </ol>").append("\r\n");		
		sb.append("	 */").append("\r\n");
		sb.append("	public BaseControllerForward {7}(BaseControllerContext context)").append("\r\n");
		sb.append("			throws Exception '{'").append("\r\n");
		sb.append("		// INIT").append("\r\n");
		sb.append("		{8}SpModel spModel = this.getSpModel(context, {8}SpModel.class);").append("\r\n");
		sb.append("		//fillPageWithParameter(spModel, context.getRequest());").append("\r\n");
		sb.append("		// BUSSINESS").append("\r\n");
		sb.append("		BaseServiceResultModel srModel = (BaseServiceResultModel) invokeService(").append("\r\n");
		sb.append("				\"I{2}Service\", I{2}Service.class, \"{7}\", spModel,new BaseUserSession(context.getRequest().getSession()));").append("\r\n");
		sb.append("		// RETURN").append("\r\n");
		sb.append("		//srModel = {7}Page(context).fillSrModel(srModel);	").append("\r\n");
		sb.append("		return context.findBaseForward(\"{7}\",spModel, srModel);").append("\r\n");
		sb.append("	'}'").append("\r\n");
		Object[] parameters = getParameters();
		Object[] tempParameters = new String[parameters.length + 2];
		int i = 0;
		for(i = 0; i<parameters.length ; i++ ){
			tempParameters[i] = parameters[i];
		}
		tempParameters[i++] = method;
		tempParameters[i++] = upperFirstChar(method);
		return MessageFormat.format(sb.toString(), tempParameters);
	}
	/**
	 * 生成action代码
	 */
	@Override
	protected void generateAction() {
		StringBuilder content = new StringBuilder();
		content.append(getActionHeader());
		for(String methodName : methodNames){
			content.append(getActionMethod(methodName));
		}
		content.append("}\r\n");
		String path = this.module + File.separator + "entity" + File.separator + "action" + File.separator ;
		outFile(content.toString(), path + upperFirstChar(this.module) + "Action.java");
	}
	/**
	 * 获取Service接口的方法
	 * @param method 方法名
	 * @return
	 */
	protected String getServiceInterfaceMethod(String method){
		StringBuilder sb = new StringBuilder();
		sb.append("\tpublic {1}SrModel {0}({1}SpModel spModel);").append("\r\n");
		return MessageFormat.format(sb.toString(), method,upperFirstChar(method));
	}
	@Override
	protected void generateServiceInterface() {
		StringBuilder content = new StringBuilder();
		content.append(getBaseServiceInterfaceHeader(ServiceType.service));
		for(String methodName : methodNames){
			content.append(getServiceInterfaceMethod(methodName));
		}
		content.append("}\r\n");
		String path = this.module + File.separator + "entity" + File.separator + "service" + File.separator ;
		outFile(content.toString(), path + "I" + upperFirstChar(this.module) + "Service.java");
	}
	/**
	 * 获取Service实现类的方法
	 * @param method 方法名
	 * @return
	 */
	protected String getServiceImplMethod(String method, Boolean isService){
		StringBuilder sb = new StringBuilder();
		sb.append("	/**").append("\r\n");
		sb.append("	 * <h1>.</h1><BR>").append("\r\n");
		sb.append("	 * ({5} 创建于 {6} )<BR>").append("\r\n");
		sb.append("	 * <strong>触发条件</strong>").append("\r\n");
		sb.append("	 * ").append("\r\n");
		sb.append("	 * <pre>").append("\r\n");
		sb.append("	 * ").append("\r\n");
		sb.append("	 * </pre>").append("\r\n");
		sb.append("	 * ").append("\r\n");
		sb.append("	 * <strong>初始逻辑</strong>").append("\r\n");
		sb.append("	 * ").append("\r\n");
		sb.append("	 * <pre>").append("\r\n");
		sb.append("	 * </pre>").append("\r\n");
		sb.append("	 * ").append("\r\n");
		sb.append("	 * <strong>编辑记录:</strong>").append("\r\n");
		sb.append("	 * <ol>").append("\r\n");
		sb.append("	 * <li></li>").append("\r\n");
		sb.append("	 * </ol>").append("\r\n");
		sb.append("	 */").append("\r\n");
		sb.append("	public {8}SrModel {7}({8}SpModel spModel) '{'").append("\r\n");
		sb.append("		//INIT").append("\r\n");
		sb.append("		{8}SrModel srModel = new {8}SrModel();").append("\r\n");
		sb.append("		//BUSINESS").append("\r\n");
		sb.append("		//srModel = dao.{7}(spModel);").append("\r\n");
		sb.append("		//INFO").append("\r\n");
		if(isService) {
			sb.append("		BaseUserProcess process = new BaseUserProcess();").append("\r\n");
			sb.append("		process.setInfoResult(srModel,null);").append("\r\n");
			sb.append("		return srModel;").append("\r\n");
		} else {
			sb.append("		//BaseUserProcess process = new BaseUserProcess();").append("\r\n");
			sb.append("		//process.setInfoResult(srModel,null);").append("\r\n");
			sb.append("		return srModel;").append("\r\n");
		}
		sb.append("	}").append("\r\n");
		Object[] parameters = getParameters();
		Object[] tempParameters = new String[parameters.length + 2];
		int i = 0;
		for(i = 0; i<parameters.length ; i++ ){
			tempParameters[i] = parameters[i];
		}
		tempParameters[i++] = method;
		tempParameters[i++] = upperFirstChar(method);
		return MessageFormat.format(sb.toString(), tempParameters);
	}
	@Override
	protected void generateServiceImpl() {
		StringBuilder content = new StringBuilder();
		content.append(getBaseServiceImplHeader(ServiceType.service));
		for(String methodName : methodNames){
			content.append(getServiceImplMethod(methodName, true));
		}
		content.append("}\r\n");
		String path = this.module + File.separator + "entity" + File.separator + "service" + File.separator + "impl" + File.separator;
		outFile(content.toString(), path + upperFirstChar(this.module) + "Service.java");
	}
	/**
	 * 获取dao的接口代码
	 * @return
	 */
	protected String getBaseDaoInterface(){
		StringBuilder sb = new StringBuilder();
		sb.append("package {0}.dao;").append("\r\n\r\n");
		sb.append("import org.net.plat4j.sr.core.base.IBaseDao;").append("\r\n");
		sb.append("\r\n/**").append("\r\n");
		sb.append(" * <h1>.</h1><BR>").append("\r\n");
		sb.append(" * @author {5} - {6}").append("\r\n");
		sb.append(" */").append("\r\n");
		sb.append("public interface I{2}Dao extends IBaseDao'{'").append("\r\n");
		sb.append("\r\n");
		sb.append("'}'").append("\r\n");
		return MessageFormat.format(sb.toString(), getParameters());
	}
	@Override
	protected void generateDaoInterface() {
		StringBuilder content = new StringBuilder();
		content.append(getBaseDaoInterface());
		String path = this.module + File.separator + "entity" + File.separator + "dao" + File.separator;
		outFile(content.toString(), path + "I" + upperFirstChar(this.module) + "Dao.java");
	}
	/**
	 * 获取dao的实现类的代码
	 * @return
	 */
	protected String getBaseDaoImpl(){
		StringBuilder sb = new StringBuilder();
		sb.append("package {0}.dao.impl;").append("\r\n\r\n");
		sb.append("import org.net.plat4j.common.core.AppDao;").append("\r\n");
		sb.append("import {0}.dao.I{2}Dao;").append("\r\n");
		sb.append("\r\n/**").append("\r\n");
		sb.append(" * <h1>.</h1><BR>").append("\r\n");
		sb.append(" * @author {5} - {6}").append("\r\n");
		sb.append(" */").append("\r\n");
		sb.append("public class {2}Dao extends AppDao implements I{2}Dao '{'").append("\r\n");
		sb.append("	/*").append("\r\n");
		sb.append("	public Query{2}SrModel query{2}(Query{2}SpModel spModel) '{'").append("\r\n");
		sb.append("		StringBuffer sql = new StringBuffer();").append("\r\n");
		sb.append("		sql.append(\"select \" +").append("\r\n");
		sb.append("		    \"t1.id id, \" +").append("\r\n");
		sb.append("		    \"t1.company_Code companyCode, \" +").append("\r\n");
		sb.append("		    \"t1.company_Name companyName, \" +").append("\r\n");
		sb.append("		    \"t1.create_time createTime, \" +").append("\r\n");
		sb.append("		    //\"(select max(t1.user_name) from uup_user a1 where a1.user_id=t1.create_user_id ) createUserName,\"+").append("\r\n");
		sb.append("		    \"t1.type type \" +").append("\r\n");
		sb.append("		    \"\");").append("\r\n");
		sb.append("		sql.append(\"from demo_Company t1 \");").append("\r\n");
		sb.append("		sql.append(\"where 1=1 and is_deleted='0'\");").append("\r\n");
		sb.append("		").append("\r\n");
		sb.append("		BaseSqlBuffer sqlBuffer = new BaseSqlBuffer();").append("\r\n");
		sb.append("		sqlBuffer.addLikeCauseIfNotNull(\"and t1.company_Code like ?\",spModel.getCompanyCode());").append("\r\n");
		sb.append("		sqlBuffer.addLikeCauseIfNotNull(\"and t1.company_Name like ?\",spModel.getCompanyName());").append("\r\n");
		sb.append("		sqlBuffer.addCauseIfNotNull(\"and t1.type = ?\",spModel.getType());").append("\r\n");
		sb.append("		sqlBuffer.addCauseIfNotNull(\"and t1.create_Time > to_date(?,'yyyy-mm-dd')\",spModel.getBeginCreateTime());").append("\r\n");
		sb.append("		sqlBuffer.addCauseIfNotNull(\"and t1.create_Time < to_date(?,'yyyy-mm-dd')\",spModel.getEndCreateTime());").append("\r\n");
		sb.append("		sql.append(sqlBuffer.getBuffer());").append("\r\n");
		sb.append("		").append("\r\n");
		sb.append("		sql.append(\" order by id desc\");").append("\r\n");
		sb.append("		PageTools pageTools = getPage(spModel);").append("\r\n");
		sb.append("		List list = this.queryForPageBySql(sql.toString(),sqlBuffer.getData(),pageTools,QueryCompanyModel.class);").append("\r\n");
		sb.append("		").append("\r\n");
		sb.append("		QueryCompanySrModel srModel = new QueryCompanySrModel();").append("\r\n");
		sb.append("		setPage(srModel, pageTools);").append("\r\n");
		sb.append("		srModel.setModels((QueryCompanyModel[])toArray(list, QueryCompanyModel.class));").append("\r\n");
		sb.append("		").append("\r\n");
		sb.append("		return srModel;").append("\r\n");
		sb.append("	'}'").append("\r\n");
		sb.append("	*/").append("\r\n");	
		sb.append("\r\n");
		sb.append("'}'").append("\r\n");
		return MessageFormat.format(sb.toString(), getParameters());
	}
	@Override
	protected void generateDaoImpl() {
		StringBuilder content = new StringBuilder();
		content.append(getBaseDaoImpl());
		String path = this.module + File.separator + "entity" + File.separator + "dao" + File.separator + "impl" + File.separator;
		outFile(content.toString(), path + upperFirstChar(this.module) + "Dao.java");
	}

	/**
	 * 获取auth实现类的头部信息
	 * @return
	 */
	protected String getBaseAuthImplHeader(){
		StringBuilder sb = new StringBuilder();
		sb.append("package {0}.auth.impl;").append("\r\n\r\n");
		sb.append("import org.net.plat4j.common.core.AppAuth;").append("\r\n");
		sb.append("import {0}.dao.I{2}Dao;").append("\r\n");
		sb.append("import {0}.service.I{2}Service;").append("\r\n");
		sb.append("//import org.net.plat4j.sr.core.base.BaseUserProcess;").append("\r\n");
		for(String methodName : methodNames){
			sb.append("import {0}.service.model." + upperFirstChar(methodName) + "SpModel;").append("\r\n");
			sb.append("import {0}.service.model." + upperFirstChar(methodName) + "SrModel;").append("\r\n");
		}
		sb.append("\r\n/**").append("\r\n");
		sb.append(" * <h1>.</h1><BR>").append("\r\n");
		sb.append(" * @author {5} - {6}").append("\r\n");
		sb.append(" */").append("\r\n");
		sb.append("public class {2}Auth extends AppAuth implements I{2}Service '{'").append("\r\n");
		sb.append("//\tprivate I{2}Dao dao;").append("\r\n");
		sb.append("	public void setDao(I{2}Dao dao) '{'").append("\r\n");
		sb.append("//\t\tthis.dao = dao;").append("\r\n");
		sb.append("	'}'").append("\r\n");
		sb.append("\r\n");
		return MessageFormat.format(sb.toString(), getParameters());
	}
	@Override
	protected void generateAuthImpl() {
		StringBuilder content = new StringBuilder();
		content.append(getBaseAuthImplHeader());
		for(String methodName : methodNames){
			content.append(getServiceImplMethod(methodName, false));
		}
		content.append("}\r\n");
		String path = this.module + File.separator + "entity" + File.separator + "auth" + File.separator + "impl" + File.separator;
		outFile(content.toString(), path + upperFirstChar(this.module) + "Auth.java");
	}

	@Override
	protected void generateBusinessInterface() {
		StringBuilder content = new StringBuilder();
		content.append(getBaseServiceInterfaceHeader(ServiceType.business));
		content.append("}\r\n");
		String path = this.module + File.separator + "entity" + File.separator + "business" + File.separator;
		outFile(content.toString(), path + "I" + upperFirstChar(this.module) + "Business.java");
	}

	@Override
	protected void generateBusinessImpl() {
		StringBuilder content = new StringBuilder();
		content.append(getBaseBusinessImplHeader(ServiceType.business));
		content.append("}\r\n");
		String path = this.module + File.separator + "entity" + File.separator + "business" + File.separator + "impl" + File.separator;
		outFile(content.toString(), path + upperFirstChar(this.module) + "Business.java");
	}
	
	/**
	 * 生成service基础接口头部代码
	 * @param type 类型
	 */
	protected String getBaseBusinessImplHeader(ServiceType type) {
		StringBuilder sb = new StringBuilder();
		String type2 = upperFirstChar(type.toString());
		sb.append("package {0}."+type+".impl;").append("\r\n\r\n");
		sb.append("import org.net.plat4j.common.core.AppService;").append("\r\n");
		sb.append("import {0}.dao.I{2}Dao;").append("\r\n");
		sb.append("import {0}."+type+".I{2}"+type2+";").append("\r\n");
		sb.append("//import org.net.plat4j.sr.core.base.BaseUserProcess;").append("\r\n");
		if(ServiceType.service == type){
			for(String methodName : methodNames){
				sb.append("import {0}.service.model." + upperFirstChar(methodName) + "SpModel;").append("\r\n");
				sb.append("import {0}.service.model." + upperFirstChar(methodName) + "SrModel;").append("\r\n");
			}
		}
		sb.append("\r\n/**").append("\r\n");
		sb.append(" * <h1>.</h1><BR>").append("\r\n");
		sb.append(" * @author {5} - {6}").append("\r\n");
		sb.append(" */").append("\r\n");
		sb.append("public class {2}"+type2+" extends AppService implements I{2}"+type2+" '{'").append("\r\n");
		sb.append("//\tprivate I{2}Dao dao;").append("\r\n");
		sb.append("	public void setDao(I{2}Dao dao) '{'").append("\r\n");
		sb.append("//\t\tthis.dao = dao;").append("\r\n");
		sb.append("	'}'").append("\r\n");
		sb.append("\r\n");
		return MessageFormat.format(sb.toString(), getParameters());
	}

	@Override
	protected void generateValidateInterface() {
		StringBuilder content = new StringBuilder();
		content.append(getBaseServiceInterfaceHeader(ServiceType.validate));
		content.append("}\r\n");
		String path = this.module + File.separator + "entity" + File.separator + "validate" + File.separator;
		outFile(content.toString(), path + "I" + upperFirstChar(this.module) + "Validate.java");
	}

	@Override
	protected void generateValidateImpl() {
		StringBuilder content = new StringBuilder();
		content.append(getBaseBusinessImplHeader(ServiceType.validate));
		content.append("}\r\n");
		String path = this.module + File.separator + "entity" + File.separator + "validate" + File.separator + "impl" + File.separator;
		outFile(content.toString(), path + upperFirstChar(this.module) + "Validate.java");
	}
	
}
