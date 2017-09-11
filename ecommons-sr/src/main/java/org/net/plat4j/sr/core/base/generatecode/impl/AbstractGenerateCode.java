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
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import org.net.plat4j.sr.core.base.generatecode.IGenerateCode;
import org.net.plat4j.sr.core.utils.LogHelper;


public abstract class AbstractGenerateCode implements IGenerateCode {
	protected LogHelper logger = new LogHelper(getClass());
	/**
	 * 作者
	 */
	protected String author;
	
	/**
	 * 代码输出路径
	 */
	protected String outPath;
	
	/**
	 * 项目编码
	 */
	protected String projectCode;
	
	/**
	 * 模块名称
	 */
	protected String module;
	
	/**
	 * 模块包
	 */
	protected String modulePackage;
	/**
	 * 包含的方法
	 */
	protected List<String> methodNames;
	protected Object[] parameters;
	protected enum ServiceType{
		service,business,validate
	}
	public AbstractGenerateCode(){
		this.methodNames = new ArrayList<String>();
	}
	/**
	 * 设置作者姓名
	 *
	 * @param    name  作者姓名
	 */
	@Override
	public void setAuthor(String name){
		this.author = name;
	}
	
	/**
	 * 设置输入路径
	 *
	 * @param    path  代码输入路径
	 */
	@Override
	public void setOutPath(String path){
		this.outPath = path;
	}
	
	/**
	 * 设置编码（默认为ebidding）
	 *
	 * @param    project  项目名称（默认为ebidding）
	 */
	@Override
	public void setProjectCode(String project){
		this.projectCode = project;
	}
	
	/**
	 * 设置模块名称
	 *
	 * @param    module  模块名称
	 */
	@Override
	public void setModule(String module){
		this.module = module;
	}
	
	/**
	 * 设置模块包名称
	 *
	 * @param    modulePackage  模块包名称
	 */
	@Override
	public void setModulePackage(String modulePackage){
		this.modulePackage = modulePackage;
	}
	
	/**
	 * 添加方法
	 *
	 * @param    method  方法名称
	 */
	@Override
	public void addMethod(String method){
		this.methodNames.add(method);
	}
	
	/**
	 * 生成代码
	 */
	@Override
	public void generateCode(){
		generateAction();
		generateServiceInterface();
		generateServiceImpl();
		generateDaoInterface();
		generateDaoImpl();
		generateBusinessInterface();
		generateBusinessImpl();
		generateValidateInterface();
		generateValidateImpl();
		generateAuthImpl();
		generateModels();
		genereateSpringConf();
	}
	
	/**
	 * 生成service基础接口头部代码
	 * @param type类型
	 */
	protected String getBaseServiceInterfaceHeader(ServiceType type) {
		StringBuilder sb = new StringBuilder();
		sb.append("package {0}." + type + ";").append("\r\n\r\n");
		sb.append("import org.net.plat4j.sr.core.base.IBaseService;").append("\r\n");
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
		sb.append("public interface I{2}" + upperFirstChar(type.toString()) + " extends IBaseService'{'").append("\r\n");
		sb.append("\r\n");
		return MessageFormat.format(sb.toString(), getParameters());
	}
	/**
	 * 生成service基础接口头部代码
	 * @param type 类型
	 */
	protected String getBaseServiceImplHeader(ServiceType type) {
		StringBuilder sb = new StringBuilder();
		String type2 = upperFirstChar(type.toString());
		sb.append("package {0}."+type+".impl;").append("\r\n\r\n");
		sb.append("import org.net.plat4j.common.core.AppService;").append("\r\n");
		sb.append("import {0}.dao.I{2}Dao;").append("\r\n");
		sb.append("import {0}."+type+".I{2}"+type2+";").append("\r\n");
		sb.append("import org.net.plat4j.sr.core.base.BaseUserProcess;").append("\r\n");
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
	
	/**
	 * 生成action代码
	 */
	protected abstract void generateAction();
	
	protected abstract void generateServiceInterface();
	
	protected abstract void generateServiceImpl();
	
	protected abstract void generateDaoInterface();
	
	protected abstract void generateDaoImpl();
	
	protected abstract void generateAuthImpl();
	
	protected abstract void generateBusinessInterface();
	
	protected abstract void generateBusinessImpl();
	
	protected abstract void generateValidateInterface();
	
	protected abstract void generateValidateImpl();
	
	/**
	 * 根据方法名获取srModel的代码
	 * @param methodName 方法名
	 * @return
	 */
	protected String getBaseSrModel(String methodName){
		StringBuilder sb = new StringBuilder();
		sb.append("package {0}.service.model;").append("\r\n\r\n");
		sb.append("import org.net.plat4j.sr.core.base.BaseServiceResultModel;").append("\r\n");
		sb.append("\r\n/**").append("\r\n");
		sb.append(" * <h1>.</h1><BR>").append("\r\n");
		sb.append(" * @author {5} - {6}").append("\r\n");
		sb.append(" */").append("\r\n");
		sb.append("public class " + upperFirstChar(methodName) + "SrModel extends BaseServiceResultModel '{'").append("\r\n");
		sb.append("\tprivate static final long serialVersionUID = 1L;").append("\r\n");
		sb.append("\r\n");
		sb.append("'}'").append("\r\n");
		return MessageFormat.format(sb.toString(), getParameters());
	}
	
	protected String getBaseSpModel(String methodName){
		StringBuilder sb = new StringBuilder();
		sb.append("package {0}.service.model;").append("\r\n\r\n");
		sb.append("import org.net.plat4j.sr.core.base.BaseServiceParamModel;").append("\r\n");
		sb.append("\r\n/**").append("\r\n");
		sb.append(" * <h1>.</h1><BR>").append("\r\n");
		sb.append(" * @author {5} - {6}").append("\r\n");
		sb.append(" */").append("\r\n");
		sb.append("public class " + upperFirstChar(methodName) + "SpModel extends BaseServiceParamModel '{'").append("\r\n");
		sb.append("\tprivate static final long serialVersionUID = 1L;").append("\r\n");
		sb.append("\r\n");
		sb.append("'}'").append("\r\n");
		return MessageFormat.format(sb.toString(), getParameters());		
	}
	
	/**
	 * 生成SpModel和SrModel
	 */
	protected void generateModels() {
		String path = this.module + File.separator + "entity" + File.separator + "service" + File.separator + "model" + File.separator;
		for(String methodName : methodNames){
			outFile(getBaseSpModel(methodName), path + upperFirstChar(methodName) + "SpModel.java");
			outFile(getBaseSrModel(methodName),path + upperFirstChar(methodName) + "SrModel.java");
		}
	}
	
	/**
	 * 生成spring配置文件
	 */
	/**
	 * 
	 */
	protected void genereateSpringConf() {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append("\r\n");
		sb.append("<!DOCTYPE beans PUBLIC \"-//SPRING//DTD BEAN//EN\" \"http://www.springframework.org/dtd/spring-beans.dtd\">").append("\r\n");
		sb.append("<!--<import resource=\"ApplicationContext-{1}.xml\" /> In ApplicationContext.xml-->").append("\r\n");
		sb.append("<beans default-lazy-init=\"false\" default-dependency-check=\"none\"").append("\r\n");
		sb.append("	default-autowire=\"no\">").append("\r\n");
		sb.append("	").append("\r\n");
		sb.append("	<bean id=\"I{2}Service\" parent=\"baseTransactionProxy\">").append("\r\n");
		sb.append("		<property name=\"target\">").append("\r\n");
		sb.append("			<bean").append("\r\n");
		sb.append("				class=\"{0}.service.impl.{2}Service\">").append("\r\n");
		sb.append("				<property name=\"dao\" ref=\"I{2}Dao\" />").append("\r\n");
		sb.append("			</bean>").append("\r\n");
		sb.append("		</property>").append("\r\n");
		sb.append("	</bean>").append("\r\n");
		sb.append("	").append("\r\n");
		sb.append("	<bean id=\"I{2}Auth\" parent=\"baseTransactionProxy\">").append("\r\n");
		sb.append("		<property name=\"target\">").append("\r\n");
		sb.append("			<bean").append("\r\n");
		sb.append("				class=\"{0}.auth.impl.{2}Auth\" singleton=\"false\">").append("\r\n");
		sb.append("				<property name=\"dao\" ref=\"I{2}Dao\" />").append("\r\n");
		sb.append("			</bean>").append("\r\n");
		sb.append("		</property>").append("\r\n");
		sb.append("	</bean>").append("\r\n");
		sb.append("	").append("\r\n");			
		sb.append("	<bean id=\"I{2}Business\" parent=\"baseTransactionProxy\">").append("\r\n");
		sb.append("		<property name=\"target\">").append("\r\n");
		sb.append("			<bean").append("\r\n");
		sb.append("				class=\"{0}.business.impl.{2}Business\">").append("\r\n");
		sb.append("				<property name=\"dao\" ref=\"I{2}Dao\" />").append("\r\n");
		sb.append("			</bean>").append("\r\n");
		sb.append("		</property>").append("\r\n");
		sb.append("	</bean>").append("\r\n");
		sb.append("	").append("\r\n");		
		sb.append("	<bean id=\"I{2}Validate\" parent=\"baseTransactionProxy\">").append("\r\n");
		sb.append("		<property name=\"target\">").append("\r\n");
		sb.append("			<bean").append("\r\n");
		sb.append("				class=\"{0}.validate.impl.{2}Validate\">").append("\r\n");
		sb.append("				<property name=\"dao\" ref=\"I{2}Dao\" />").append("\r\n");
		sb.append("			</bean>").append("\r\n");
		sb.append("		</property>").append("\r\n");
		sb.append("	</bean>").append("\r\n");
		sb.append("	").append("\r\n");		
		sb.append("	<bean id=\"I{2}Dao\"").append("\r\n");
		sb.append("		class=\"{0}.dao.impl.{2}Dao\">").append("\r\n");
		sb.append("		<property name=\"sessionFactory\" ref=\"sessionFactory\" />").append("\r\n");
		sb.append("	</bean>	").append("\r\n");
		sb.append("	").append("\r\n");
		sb.append("</beans>").append("\r\n");		
		String path = this.module + File.separator + "conf" + File.separator;
		outFile(MessageFormat.format(sb.toString(), getParameters()), path + "ApplicationContext-"+ this.module+ ".xml");
	}
	
	/**
	 * 输出内容至文件
	 *
	 * @param    content  文件内容
	 * @param    path     相对于outPath的文件路径
	 */	
	protected void outFile(String content, String path) {
		String filePath = this.outPath + File.separator + path;
		File file = new File(filePath);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file);
			fileWriter.write(content);
		} catch (IOException e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally{
			IOUtils.closeQuietly(fileWriter);
		}
	}
	/**
	 * 参数列表
	 * @return
	 */
	protected Object[] getParameters(){
		if(parameters != null){
			return parameters;
		}
		parameters = new String[]{
				this.modulePackage,                    //0
				this.module,                           //1
				upperFirstChar(this.module),           //2
				this.projectCode,                      //3
				upperFirstChar(this.projectCode),      //4
				this.author,                           //5
				nowDate()                              //6
		};
		return parameters;
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	private String nowDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(new Date());
	}
	/**
	 * 字符串首字母大写
	 * @param str
	 * @return
	 */
	protected String upperFirstChar(String str){
		if(str==null||str.trim().length()<=1){
			return "";
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}
