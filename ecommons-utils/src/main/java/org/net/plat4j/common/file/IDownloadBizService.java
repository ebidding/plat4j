/*
	Copyright (C) 2014 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2014-08-23 15:06:43
	
	Revision History:
	Version     Date              				Author			Comments
	1.0         	2014-08-23 15:06:43		yujie				Create file
=========================================================================
*/

package org.net.plat4j.common.file;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 声明业务模块实现 Download Servlet 功能的接口。
 * 
 * @author yujie
 */
public interface IDownloadBizService {
	/**
	 * 通过设置 response 实现下载文件功能。
	 * 
	 * <pre>
	 * Author: yujie
	 * Created Time: 2014-08-23 15:17:44
	 * </pre>
	 * <pre>
	 * 	如：
	 * 		    response.setContentType(contentType);
	 * 		    response.addHeader("Content-Disposition", attachment + ";filename=" + fileName);
	 * 			if(contentLength > 0)
	 * 				response.addHeader("Content-Length", contentLength + "");
	 * 			OutputStream out = response.getOutputStream();
	 * 			FileUtil.downloadFile(filePath, out);
	 * </pre>
	 * 
	 * @param request
	 * @param response
	 */
	void doDownload(HttpServletRequest request, HttpServletResponse response);
}
