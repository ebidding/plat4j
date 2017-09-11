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
package org.net.plat4j.sr.core.base.generatecode;


public interface IGenerateCode {
	/**
	 * 设置作者姓名
	 *
	 * @param    name  作者姓名
	 */
	public void setAuthor(String name);
	
	/**
	 * 设置输入路径
	 *
	 * @param    path  代码输入路径
	 */
	public void setOutPath(String path);
	
	/**
	 * 设置编码（默认为ebidding）
	 *
	 * @param    project  项目名称（默认为ebidding）
	 */
	public void setProjectCode(String project);
	
	/**
	 * 设置模块名称
	 *
	 * @param    module  模块名称
	 */
	public void setModule(String module);
	
	/**
	 * 设置模块包名称
	 *
	 * @param    modulePackage  模块包名称
	 */
	public void setModulePackage(String modulePackage);
	
	/**
	 * 添加方法
	 *
	 * @param    method  方法名称
	 */
	public void addMethod(String method);
	
	/**
	 * 生成代码
	 */
	public void generateCode();
}
