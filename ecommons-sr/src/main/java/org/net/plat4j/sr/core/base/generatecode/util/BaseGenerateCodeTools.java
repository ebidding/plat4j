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
package org.net.plat4j.sr.core.base.generatecode.util;

import org.apache.commons.lang3.StringUtils;

import org.net.plat4j.sr.core.base.generatecode.IGenerateCode;
import org.net.plat4j.sr.core.base.generatecode.impl.EbiddingV4GenerateCode;


public class BaseGenerateCodeTools {
	private IGenerateCode generateCode;
	
	/**
	 * @param author 作者姓名
	 * @param outPath 代码输出路径
	 * @param module 模块
	 * @param modulePackage 模块所在包
	 */
	public BaseGenerateCodeTools(String author,String outPath,String module,String modulePackage){
		if(StringUtils.isEmpty(author)){
			throw new RuntimeException("The author name must be not null.");
		}
		if(StringUtils.isEmpty(outPath)){
			throw new RuntimeException("The out path must be not null.");
		}
		if(StringUtils.isEmpty(module)){
			throw new RuntimeException("The module must be not null.");
		}
		if(StringUtils.isEmpty(modulePackage)){
			throw new RuntimeException("The package of module must be not null.");
		}
		generateCode = new EbiddingV4GenerateCode();
		generateCode.setAuthor(author);
		generateCode.setOutPath(outPath);
		generateCode.setModule(module);
		generateCode.setModulePackage(modulePackage);
		generateCode.setProjectCode("ebidding");
	}
	public void setProjectCode(String projectCode){
		generateCode.setProjectCode(projectCode);
	}
	public void addMethod(String methodName){
		if(StringUtils.isEmpty(methodName)){
			throw new RuntimeException("The method name must not be null.");
		}
		generateCode.addMethod(methodName);
	}
	public void generateCode() {
		generateCode.generateCode();
	}
	public static void main(String[] args) {
		BaseGenerateCodeTools generateCodeTools = new BaseGenerateCodeTools("wgy", "D:/autocode", "standard4Test", "org.net.plat4j.web.standard4.test");
		generateCodeTools.addMethod("queryTest");
		generateCodeTools.addMethod("submitTest");
		generateCodeTools.addMethod("addTest");
		generateCodeTools.addMethod("updateTest");
		generateCodeTools.addMethod("deleteTest");
		generateCodeTools.generateCode();
		System.out.println("End");
	}
}
