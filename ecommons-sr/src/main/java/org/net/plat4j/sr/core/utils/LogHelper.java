package org.net.plat4j.sr.core.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.ThreadContext;
import org.net.plat4j.common.syspage.model.SysPageModel;
import org.net.plat4j.common.utils.ShiroUtils;
import org.net.plat4j.common.utils.SysPageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.plat4j.core.jdbc.PageJdbcTemplate;
import net.sf.json.JSONObject;

public class LogHelper {

	protected Logger logger = null;
	protected org.apache.logging.log4j.Logger saveUserOperatorLogger = null;
	private static final String PATTERN_STR = ".*/(.*)/(.*)\\.htm";
	private static final String HTM = ".htm";

	public LogHelper(Class<?> clazz) {
		this.logger = LoggerFactory.getLogger(clazz);
	}

	public void debug(String msg) {
		logger.debug(msg);
	}

	public void debug(String format, Object... argArray) {
		if (logger.isDebugEnabled()) {
			String msg = MessageFormat.format(format, argArray);
			logger.debug(msg);
		}
	}

	public void debug(String msg, Throwable t) {
		logger.debug(msg, t);
	}

	public void info(String msg) {
		logger.info(msg);
	}

	public void info(String format, Object... argArray) {
		if (logger.isInfoEnabled()) {
			String msg = MessageFormat.format(format, argArray);
			logger.info(msg);
		}
	}

	public void info(String msg, Throwable t) {
		logger.info(msg, t);
	}

	public void warn(String msg) {
		logger.warn(msg);
	}

	public void warn(String format, Object... argArray) {
		if (logger.isWarnEnabled()) {
			String msg = MessageFormat.format(format, argArray);
			logger.warn(msg);
		}
	}

	public void warn(String msg, Throwable t) {
		logger.warn(msg, t);
	}

	public void error(String msg) {
		logger.error(msg);
	}

	public void error(String format, Object... argArray) {
		if (logger.isErrorEnabled()) {
			String msg = MessageFormat.format(format, argArray);
			logger.error(msg);
		}
	}

	public void error(String msg, Throwable t) {
		logger.error(msg, t);
	}

	public void error(Throwable t) {
		this.error("", t);
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	/**
	 * 记录用户操作日志
	 * 
	 * @param request
	 */
	public void saveUserOperationLog(HttpServletRequest request) {
		this.saveUserOperationLog("", request);
//		if(this.saveUserOperatorLogger == null){
//			this.saveUserOperatorLogger = LogManager.getLogger("saveUserOperatorLog");
//		}
//		this.saveUserOperatorLogger.info("just for testing!");
	}

	private static final String INSERT_SYS_LOG = "INSERT INTO sys_log(url,thread,priority,category,ip_addr,param,operator_true_name,is_deleted,create_user_id,create_time,IS_SHOW,message,page_uri,page_method,USEING_TIME,agent_id) " 
			+ " values(?,?, ?, ?, ?, ?,?, '0' , ? , SYSDATE() , ? ,?,?,?,?, "
			+ " case "
			+ " when ? is not null then ? "
			+ " when ? is not null then (select agent_id from bid_package where id = ?) "
			+ " when ? is not null then (select agent_id from bid_main where id = ?) "
			+ " else -1 "
			+ " end)";
	public void saveUserOperationLog(String msg, HttpServletRequest request) {
		String uri = request.getRequestURI();
		String method = null; 
		String pageUri = null;
		Pattern pattern = Pattern.compile(PATTERN_STR);
		Matcher matcher;
		matcher=pattern.matcher(uri);
		if(matcher.find()){
			method  = matcher.group(2)+HTM;
			pageUri = matcher.group(1);
		}
		// 从缓存中取到页面描述
		SysPageModel pageModel = SysPageUtil.getSysPage(pageUri, method);
		List<Object> args = new ArrayList<Object>(); 
		args.add(request.getRequestURL());
		args.add(getNotNullValue(Thread.currentThread().getName()));
		args.add("INFO");
		args.add("");
		args.add(getNotNullValue(IpAddressUtil.getIpAddress(request)));
		args.add(getNotNullValue(getParamValue(request).toString()));
		args.add(getNotNullValue(ShiroUtils.getTrueName()));
		args.add(ShiroUtils.getUserId());
		if (pageModel != null) {//sys_page中有这个页面，显示
			args.add("1");
			args.add(getNotNullValue(pageModel.getPageName()));
			args.add(getNotNullValue(pageModel.getPageUri()));
			args.add(getNotNullValue(pageModel.getPageMethod()));
			
		}else{//sys_page中没有这个页面，不显示
			args.add("0");
			args.add("");
			args.add("");
			args.add("");
		}
		//useringTime 操作耗时
		if(NumberUtils.isNumber(msg)){//msg为数字
			args.add(Long.parseLong(msg));
		}else{//msg不为数字
			args.add(-1);
		}
		
		String param = getParamValueString(request);
		String agentId = getBidIdOrPackageId("agentId", param);
		if(StringUtils.isEmpty(agentId)){
			agentId = ShiroUtils.getUserAgentId() == null ? "" : ShiroUtils.getUserAgentId().toString();
		}
		Long agentid = -1L;
		if(NumberUtils.isNumber(agentId)){
			agentid = Long.parseLong(agentId);
		}
		args.add(agentid);
		args.add(agentid);
		String packageId = getBidIdOrPackageId("packageId", param);
		args.add(getNotNullValue(packageId));
		args.add(getNotNullValue(packageId));
		String bidId = getBidIdOrPackageId("bidId", param);
		args.add(getNotNullValue(bidId));
		args.add(getNotNullValue(bidId));
		PageJdbcTemplate jdbcTemplate = new PageJdbcTemplate();
		jdbcTemplate.update(INSERT_SYS_LOG, args.toArray());
//		
//		
//		if(this.saveUserOperatorLogger == null){
//			this.saveUserOperatorLogger = LoggerFactory.getLogger("saveUserOperatorLog");
//		}
//		if (pageModel != null) {
//			// ip地址
//			try {
//				ThreadContext.put("ipAddr", IpAddressUtil.getIpAddress(request));
//			} catch (Exception e) {
//				logger.error("", e);
//			}
//			// 参数的值
//			ThreadContext.put("param", getParamValue(request).toString());
//			// agentId
//			setAgentId(getParamValueString(request), request);
//			// 页面描述
//			ThreadContext.put("message", pageModel.getPageName());
//			// 用户id
//			ThreadContext.put("userId", WebUtils.getUserId().toString());
//			// 用户真实姓名
//			ThreadContext.put("trueName", WebUtils.getShiroUser().getTrueName());
//			// 用户姓名
//			ThreadContext.put("userName", WebUtils.getUserName());
//			// action名
//			ThreadContext.put("pageUri", request.getRequestURI());
//			// method名
//			ThreadContext.put("pageMethod", request.getParameter("method"));
//			// 存入数据库
//			this.saveUserOperatorLogger.info("+++++++++++++++" + pageModel.getPageName());
//			ThreadContext.clearAll();
//		}
	}
	public static String getNotNullValue(String str){
		if(StringUtils.isEmpty(str)){
			return "";
		}
		return str;
	}

	/**
	 * JSON字符串表示参数
	 * @param request
	 * @return
	 */
	public static JSONObject getParamValue(HttpServletRequest request) {
		Enumeration<String> en = request.getParameterNames();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		while(en.hasMoreElements()) {
			String key = en.nextElement();
			String value = request.getParameter(key);
			paramMap.put(key, value);
		}
		JSONObject jsonObject = JSONObject.fromObject(paramMap);
		return jsonObject;
	}
	/**
	 * 字符串表示参数
	 * @param request
	 * @return
	 */
	public static String getParamValueString(HttpServletRequest request) {
		Enumeration<String> en = request.getParameterNames();
		StringBuffer sb = new StringBuffer();
		while(en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String value = request.getParameter(key);
			sb.append(key).append("=").append(value).append(",");
		}
		return sb.toString();
	}
	/**
	 * 获取操作用户的agentId，如果是agentId为空，则是平台级的，否则是代理机构级的
	 * 如果是agentId为空，则查找bidId或者packageId，找到项目经理对应的agentId
	 * @param request
	 * @return agentId
	 */

	private static void setAgentId(String paramStr,HttpServletRequest request) {
		if(null!=getBidIdOrPackageId("bidId",paramStr)){
			//获取bidId的值
			String bidIdValue = getBidIdOrPackageId("bidId",paramStr);
			ThreadContext.put("bidId", bidIdValue);
		}else if(null!=getBidIdOrPackageId("packageId",paramStr)){
			String packageId = getBidIdOrPackageId("packageId",paramStr);
			ThreadContext.put("packageId", packageId);
		}else if(""!=request.getParameter("userAgentId")&&request.getParameter("userAgentId")!=null){
			ThreadContext.put("agentId", request.getParameter("userAgentId"));
		}else if(ShiroUtils.getUserAgentId()!=null){
			ThreadContext.put("agentId", ShiroUtils.getUserAgentId().toString());
		}else{
			ThreadContext.put("agentId", "");
		}
	}
	public static String getBidIdOrPackageId(String type ,String paramStr){
		String patString = type+".*?=(\\d+)";
		String result="";
		Pattern pattern = Pattern.compile(patString);
		Matcher matcher = pattern.matcher(paramStr);
		if(-1!=paramStr.indexOf(type)){
			while(matcher.find()){
				if(""!=matcher.group(1)&&null!=matcher.group(1)){
					result = matcher.group(1);
					break;
				}
			}
			return result;
		}else{
			return null;
		}
	
	}
}
