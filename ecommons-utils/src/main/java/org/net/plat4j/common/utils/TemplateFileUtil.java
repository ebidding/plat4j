/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2015年9月21日 下午6:39:30
	
	Revision History:
	Version     Date              					Author			Comments
	1.0      2015年9月21日下午6:39:30		    		chenyj			Create file
=========================================================================
*/
package org.net.plat4j.common.utils;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import net.plat4j.core.jdbc.PageJdbcTemplate;

/**
 * @author chenyj
 *
 */
public class TemplateFileUtil {
	private static final String SQL_GET_TEMPLATE_FILE_PATH = "SELECT TEMPLATE_FILE_PATH templateFilePath FROM CFG_TEMPLATE_FILE WHERE AGENT_ID = ? AND TEMPLATE_CODE = ?";
	/**
	 * 获取文件的相对路径
	 * @param agentId 招标代理机构id
	 * @param templateCode 模版编号
	 * @return
	 */
	public static String getTemplateFilePath(Long agentId, String templateCode) {
		PageJdbcTemplate jdbcTemplate = new PageJdbcTemplate();
		Map<String, Object> map = jdbcTemplate.queryForMap(SQL_GET_TEMPLATE_FILE_PATH, new Object[]{agentId, templateCode});
		if(map != null){
			return (String)map.get("templateFilePath");
		}else{
			//取平台的模版
			map = jdbcTemplate.queryForMap(SQL_GET_TEMPLATE_FILE_PATH, new Object[]{0, templateCode});
			if(map != null){
				return (String)map.get("templateFilePath");
			}
		}
		return null;
	}
	public static File getTemplateFile(Long agentId, HttpServletRequest request, String templateCode){
		String filePath = getTemplateFilePath(agentId, templateCode);
		if(StringUtils.isEmpty(filePath)){
			filePath = getTemplateFilePath(Constants.PLATFORM_ID, templateCode);
		}
		String realPath = request.getServletContext().getRealPath("/");
		String templateFile = realPath + FileUtil.separator + "template" + FileUtil.separator + filePath;
		return new File(templateFile);
	}
}
