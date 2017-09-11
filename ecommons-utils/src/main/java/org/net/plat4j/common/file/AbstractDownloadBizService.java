/*
	Copyright (C) 2014 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2014-08-23 15:24:14
	
	Revision History:
	Version     Date              				Author			Comments
	1.0         	2014-08-23 15:24:14		yujie				Create file
=========================================================================
*/

package org.net.plat4j.common.file;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.net.plat4j.sr.core.utils.LogHelper;

/**
 * @author yujie
 *
 */
public abstract class AbstractDownloadBizService implements IDownloadBizService {
	private LogHelper logger = new LogHelper(AbstractDownloadBizService.class);
	protected void printHtml(HttpServletResponse response,String content) {		
		PrintWriter out;
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			StringBuilder str = new StringBuilder();
			str.append("<!doctype html>");
			str.append("<html>");
			str.append("<head>");
			str.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
			str.append("<title>下载文件错误</title>");
			str.append("</head>");

			str.append("<body>");
			str.append("<div>"+content+"</div>");
			str.append("</body>");
			str.append("</html>");
			out = response.getWriter();
			out.println(str);
		} catch (IOException e) {
			logger.warn("Error occurred in AbstractDownloadBizService.printHtml:", e);
		}		
	}
}
