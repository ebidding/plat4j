/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2015年9月16日 下午9:52:23
	
	Revision History:
	Version     Date              					Author			Comments
	1.0      2015年9月16日下午9:52:23		    		chenyj			Create file
=========================================================================
*/
package org.net.plat4j.common.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import org.net.plat4j.common.excelutil.JxlsWorkBook;
import org.net.plat4j.sr.core.utils.LogHelper;
import org.net.plat4j.web.model.SysDocFile;

/**
 * @author chenyj
 *
 */
public class ExcelUtil {
	protected static LogHelper logger = new LogHelper(ExcelUtil.class);
	private static String getCurDay(){
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	/**
	 * 通过模版替换文件
	 * @param request
	 * @param agentId 招标代理机构id
	 * @param templateCode 模版code
	 * @param data 替换的数据
	 * @param targetFileName 目标文件对应的文件名
	 * @return
	 */
	private static File replaceExcel(HttpServletRequest request, Long agentId, String templateCode,Map<String, Object> data,String targetFileName){
		String descDir = AppConfig.getProperty("Location_Default_Dir") + FileUtil.separator + getCurDay();
		File dir = new File(descDir);
		if(!dir.exists()){
			dir.mkdirs();
		}
		Map<String,Object> sheet1 = new HashMap<>();
		sheet1.put("sheet1", data);
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(sheet1);
		try {
			File templateFile = TemplateFileUtil.getTemplateFile(agentId, request, templateCode);
			JxlsWorkBook jwb = new JxlsWorkBook(templateFile);
			jwb.fillData2Excel(templateFile.getAbsolutePath(), list);
			File targetFile = jwb.writeToFile(descDir + FileUtil.separator + targetFileName);
			return targetFile;
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 通过模版替换文件
	 * @param request
	 * @param agentId 招标代理机构id
	 * @param templateCode 模版编码
	 * @param data 替换的数据
	 * @param targetFileName 目标文件对应的文件名
	 * @return
	 */
	public static SysDocFile replaceTempExcel(HttpServletRequest request,Long agentId, String templateCode,Map<String, Object> data,String targetFileName){
		File targetFile = replaceExcel(request,agentId, templateCode, data, targetFileName);
		String id = FileUtil.getUUID();
		SysDocFile sysDocFIle = FileUtil.saveSysDocFile(targetFile,id,null, "0");
		return sysDocFIle;
	}
	/**
	 * 通过模版替换文件
	 * @param request
	 * @param agentId 招标代理机构id
	 * @param templateCode 模版编码
	 * @param data 替换的数据
	 * @param targetFileName 目标文件对应的文件名
	 * @param moduleName 模块名称
	 * @return
	 */
	public static SysDocFile replaceForeverExcel(HttpServletRequest request,Long agentId, String templateCode,Map<String, Object> data,String targetFileName,String moduleName){
		File targetFile = replaceExcel(request, agentId, templateCode , data, targetFileName);
		if(StringUtils.isEmpty(moduleName)){
			throw new RuntimeException("模块名称不能为空");
		}
		SysDocFile sysDocFile = FileUtil.uploadForeverFile(agentId, targetFile, moduleName);
		return sysDocFile;
	}
}
