package org.net.plat4j.common.utils;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.ccit.eaps.timestamp.service.TimeStampService;
import com.ccit.eaps.timestamp.service.impl.TimeStampServiceImpl;
import net.plat4j.core.spring.BeanFactory;

import cn.as.ts.client.security.OperateHandle;
import org.net.plat4j.common.config.IConfigUtilFactory;
import org.net.plat4j.common.innerPortal.IInnerPortalUtilService;
import org.net.plat4j.common.innerPortal.model.Yom2InnerBulletin;
import org.net.plat4j.common.permissions.IPermissionsService;
import org.net.plat4j.common.user.ShiroUser;
import org.net.plat4j.sr.core.utils.LogHelper;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Copy from old project
 */
public class WebUtils {
	protected static LogHelper logger = new LogHelper(WebUtils.class);
	/**
	 * 表名：bid_package_status
	 * 当前功能进行中。
	 */
	public static final int STATUS_PROGRESS_WORKING=1;
	/**
	 * 表名：bid_package_status
	 * 当前功能锁定，比如待审批。
	 */
	public static final int STATUS_PROGRESS_LOCKED=2;
	/**
	 * 表名：bid_package_status
	 * 当前被驳回。
	 */
	public static final int STATUS_PROGRESS_DISMISSED=3;
	/**
	 * 表名：bid_package_status
	 * 当前功能可发布，如审批通过。
	 */
	public static final int STATUS_PROGRESS_ISSUABLE=4;
	/**
	 * 表名：bid_package_status
	 * 当前功能已完成。
	 */
	public static final int STATUS_PROGRESS_FINAL=5;
	/**
	 * 当前年度流水号是否正在使用。
	 */
	public static final String AUTO_NUMBER_USED = "0";
	
	/**
	 * 当前年度流水号是否废弃。
	 */
	public static final String AUTO_NUMBER_DISCARD = "1";
	/**
	 * 是否调用时间戳服务器 0表示调用 1表示不调用
	 */
	public static final String HAVE_TIEMSTEMP=AppConfig.getProperty("hava_Timestamp");
	/*
	 * 这是中认环宇的时间戳
	 */
	public static final String TIEMSTEMP_IP=AppConfig.getProperty("timestamp_ip");
	public static final String TIEMSTEMP_PORT=AppConfig.getProperty("timestamp_port");
	
	
	public static Long getUserId() {		
		Long userId = getShiroUser().getId();
		return userId == null?-1L:userId;
	}
	
	public static String getUserName() {		
		return getShiroUser().getUserName();
	}
	
	public static String getTrueName(){
		return getShiroUser().getTrueName();
	}
	
	public static String getCompanyName(){
		return getShiroUser().getCompanyName();
	}
	 
	public static Long getCompanyId() {
		Long companyId = getShiroUser().getCompanyId();
		return companyId == null?-1:companyId;
	}
	
	/***
	 * 当前登录人的用户类型
	 * @return
	 */
	public static String getUserType(){
		return getShiroUser().getUserType();
	}
	
	public static Long getDepartmentId(){
		Long departmentId = getShiroUser().getDepartId();
		return departmentId == null?-1:departmentId;
	}
	
	public static Long[] getRoleIds(){
		Long[] roleIds = getShiroUser().getRoleIds();
		return roleIds == null ? new Long[0] : roleIds;
	}
	
	public static ShiroUser getShiroUser() {		
		try {
			Subject currentUser = SecurityUtils.getSubject();	
			ShiroUser shiroUser =  (ShiroUser)currentUser.getPrincipal();
			return shiroUser == null?new ShiroUser():shiroUser;
		} catch(Exception e) {
			//logger.error(e);
			return new ShiroUser();
			
		}		
	}
	public static String encodeUrl(HttpServletRequest request,String url) {
		Base64 base64 = new Base64();
		String sid= "&JSESSIONID=" + request.getSession().getId();
		StringBuilder str = new StringBuilder();
		String[] urls = StringUtils.split(url, "?");
		if(urls.length == 2) {
			str.append(urls[0] + "?_QUERY_STRING=" + new String(base64.encode((urls[1] + sid).getBytes())));
		} else {
			str.append(url);
		}		
		return str.toString();
	}
	
	public static String decodeUrl(String url) {	
		Base64 base64 = new Base64();
		return new String(base64.decode(url.getBytes()));		
	}
	
	/**
	 * @author 芦帅
	 * @version 创建时间：2013-10-24 上午9:41:25
	 * 
	 * @param agencyId 公司ID
	 * @param configCode 配置的CODE
	 * @return 如果ConfigValue没有配置或等于0返回0，等于1的话返回1；(主要判断是否需要审批，1需要，0不需要)
	 */
	public static String getCompanyConfigValue(Long agencyId,String configCode) {
		IConfigUtilFactory confUtilFactory = BeanFactory.getBean("IConfigUtilFactory");
		return confUtilFactory.create().getCompanyConfigValue(agencyId, configCode);
	}
	
	/**
	 * @author 芦帅
	 * @version 创建时间：2013-10-24 上午9:41:25
	 * 
	 * @return 如果本公司在LdlConfigPara这张表里配置使用通用的专业，则返回通用公司ID，否则返回本公司的ID；(用于专业的维护和使用)
	 */
	public static Long getCompanyIdForSpeciality(){
		Long companyId = getCompanyId();
		String confValue = getCompanyConfigValue(companyId, "SPECIALITY_USE");
		if("1".equals(confValue)){
			companyId = NumberUtils.toLong(AppConfig.getString("expert_library"), -1);
		}
		return companyId;
	}
	
	/**
	 * @author 芦帅
	 * @version 创建时间：2013-10-24 上午9:41:25
	 * 
	 * @return 如果当前公司使用通用的评标专家库信息，则返回通用公司ID，否则返回本公司的ID；
	 */
	public static Long getCompanyIdForExpertLibrary(){
		Long companyId = getCompanyId();
		String confValue = getCompanyConfigValue(companyId, "EXPERT_LIBRARY");
		if("0".equals(confValue)){
			companyId = NumberUtils.toLong(AppConfig.getString("expert_library"), -1);
		}
		return companyId;
	}
	
	@SuppressWarnings("rawtypes")
	public static String paramReplace(String s,Map paramMap) {		
		String str = s;
		String key;
		String value;
		for(Iterator it = paramMap.keySet().iterator();it.hasNext();) {
			key = (String)it.next();
			value =String.valueOf(paramMap.get(key));
			str = StringUtils.replace(str, key, transactSqlInjection(value));
		}		
		return str;
	}
	
	/** 
	* 防止sql注入.
	* 
	* @param sql.
	* @return.
	个他                                                                                                                                                   */ 
	public static String transactSqlInjection(String sql) {  
		return sql.replaceAll(".*([';]+|(--)+).*", " ");  
	}
	/**
	 *  查看当前登录人是否有权限
	 * @param uri
	 * @param method
	 * @return
	 */
	public static Boolean authorize(String uri ,String method){
		
		
		IPermissionsService service = (IPermissionsService) BeanFactory.getBean("IPermissionsService");
     	return service.queryButtonPermissions(uri, method, WebUtils.getUserId());
	}
	
	
	/**
	 * 判断当前选择身份是不是招标人
	 * @return
	 */
	public static boolean isTenderee(){
		return SysUerTypeUtils.isTenderee(WebUtils.getShiroUser().getUserType());
	}
	/**
	 * 判断当前选择身份是不是专家
	 * @return
	 */
	public static boolean isExpert(){
		return SysUerTypeUtils.isExpert(WebUtils.getShiroUser().getUserType());
	}
	/**
	 * 判断当前选择身份是不是监标人
	 * @return
	 */
	public static boolean isSupervisor(){
		return SysUerTypeUtils.isSupervisor(WebUtils.getShiroUser().getUserType());
	}
	/**
	 * 判断当前选择身份是不是代理机构
	 * @return
	 */
	public static boolean isAgent(){
		return SysUerTypeUtils.isAgent(WebUtils.getShiroUser().getUserType());
	}
	/**
	 * 判断当前选择身份是不是投标人 
	 * @return
	 */
	public static boolean isSupplier(){
		return SysUerTypeUtils.isSupplier(WebUtils.getShiroUser().getUserType());
	}
	
	/**
	 * 判断当前用户是不是招标人
	 * @return
	 */
	public static boolean hasTenderee(){
		return SysUerTypeUtils.hasTenderee(WebUtils.getShiroUser().getSysUserAgencyInfos());
	}
	/**
	 * 判断当前用户是不是专家
	 * @return
	 */
	public static boolean hasExpert(){
		return SysUerTypeUtils.hasExpert(WebUtils.getShiroUser().getSysUserAgencyInfos());
	}
	/**
	 * 判断当前用户是不是监标人
	 * @return
	 */
	public static boolean hasSupervisor(){
		return SysUerTypeUtils.hasSupervisor(WebUtils.getShiroUser().getSysUserAgencyInfos());
	}
	/**
	 * 判断当前用户是不是代理机构
	 * @return
	 */
	public static boolean hasAgent(){
		return SysUerTypeUtils.hasAgent(WebUtils.getShiroUser().getSysUserAgencyInfos());
	}
	/**
	 * 判断当前用户是不是投标人 
	 * @return
	 */
	public static boolean hasSupplier(){
		return SysUerTypeUtils.hasSupplier(WebUtils.getShiroUser().getSysUserAgencyInfos());
	}
	
	/**
	 * 判断是不是平台管理员
	 * @return
	 */
	public static boolean isPlatFormAdmin(){
		return	WebUtils.authorize("ROLEACTION", "IS_PLATFORM_ADMIN");
	}
	
	/**
	 * 判断是不是代理机构管理员
	 * @return
	 */
	public static boolean isAgentAdmin(){
		return	WebUtils.authorize("ROLEACTION", "IS_AGENT_ADMIN");
	}
	
	/**
	 * 判断是不是公司领导
	 * @return
	 */
	public static boolean isCompanyLeader(){
		return WebUtils.authorize("ROLEACTION", "IS_COMPANY_LEADER");
	}
	
	/**
	 * 判断是不是部门经理
	 * @return
	 */
	public static boolean isDepartManager(){
		return WebUtils.authorize("ROLEACTION", "IS_DEPART_MANAGER");
	}
	
	
	/**
	 * 内容滚动显示在内网门户上面。
	 * @param innerBulltin
	 * @return
	 */
	public static Long saveInnerBulletin(Yom2InnerBulletin innerBulltin){
		IInnerPortalUtilService  service =BeanFactory.getBean("IInnerPortalUtilService");
		return service.saveInnerBulletin(innerBulltin);
	}
	
	/**
	 * 将在内网门户上面滚动显示的内容删除掉
	 * @param id
	 * @return
	 */
	public static Boolean revocationInnerBulletin(Long id) {
		IInnerPortalUtilService  service =BeanFactory.getBean("IInnerPortalUtilService");
		return service.revocationInnerBulletin(id);
	}
	/**
	 * 获取时间戳的时间
	 * @return
	 * @throws Exception
	 */
	
	  @SuppressWarnings("deprecation")
	public static  Date getCurrentTimeStamp()  {
		  Date currentDate=new Date();
		
	if("1".equals(HAVE_TIEMSTEMP)){
		String caOrg = ConfigUtils.getPlatformConfigValue("FUNCTION_SWITCH", "CA_ORG");
		if("ZRHY".equals(caOrg)){
			 return getTimestamp();
		  }else{
				  TimeStampService service = new TimeStampServiceImpl();
				    String inData = UUID.randomUUID().toString();
				    String timeStampReq;
					try {
						timeStampReq = service.SOF_CreateTimeStampRequest(inData.getBytes());
						 logger.info("----------------------------------");
						 logger.info("timeStampReq == " + timeStampReq);

						    String timeStampResp = service
						      .SOF_CreateTimeStampResponse(timeStampReq);
						    logger.info("----------------------------------");
						    logger.info("timeStampResp == " + timeStampResp);

						    boolean ret = service.SOF_VerifyTimeStamp(inData.getBytes(), timeStampResp);
						    logger.info("----------------------------------");
						    logger.info("verifyResult == " + ret);

						    String time = service.SOF_GetTimeStampInfo(timeStampResp, 1);
						    logger.info("----------------------------------");
						    logger.info("time == " + time);
							Pattern p = Pattern.compile("^(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})Z$");
							Matcher m = p.matcher(time);
							
							if(m.find()) {
								currentDate =new Date(Integer.valueOf(m.group(1)) - 1900, Integer.valueOf(m.group(2)) - 1, Integer.valueOf(m.group(3)), Integer.valueOf(m.group(4)), Integer.valueOf(m.group(5)), Integer.valueOf(m.group(6)));
							}
							
					} catch (Exception e) {
						logger.error("调用时间戳服务器 异常：", e);
					} 
			  	}  
		  }
		   
		return currentDate;
	  } 
	  /**
	   * 获取默认的平台级别ID
	   * @return
	   */
	  public static Long getDefaultAgentId(){
		  String platformConfigValue = ConfigUtils.getPlatformConfigValue("PLATFORM_DEFAULT", "DEFAULT_AGENT_ID");
		  return Long.valueOf(platformConfigValue);
	  }
	  
	/**
	 *  获取当前用户所对应的代理机构的Id
	 * @return
	 */
	public static Long getUserAgentId(){
		return getShiroUser().getAgentId();
	}
	
	public static void issueDispatcher(ServletRequest request, ServletResponse response, String url) {
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error("error occurred while issueDispatcher: ", e);
			throw new RuntimeException(e);
		}
	}	
	
	public static String createTicket() {
		
		CacheManager manager = CacheManager.create();
		Cache cache = manager.getCache("ticketCache");	
		String ticket = FileUtil.getUUID();
		Element element = new Element(ticket,WebUtils.getUserName());
		cache.put(element);
		
		return ticket;
	}
	
	public static String getTicket(String ticket) {
		
		CacheManager manager = CacheManager.create(); 
		Cache cache = manager.getCache("ticketCache");		
		Element element = cache.get(ticket);
		String str = "";
		if(element != null) {
			str = (String)element.getValue();
			//cache.remove(ticket);
		} 
		return str;
	}
	
	@SuppressWarnings("deprecation")
	public static Date getTimestamp() {
		String inData = UUID.randomUUID().toString();
		Properties prop = new Properties();
        prop.setProperty("serverNum", "1");
        prop.setProperty("address1", TIEMSTEMP_IP);
        prop.setProperty("port1", TIEMSTEMP_PORT);
        prop.setProperty("minConnNum", "10");
        prop.setProperty("maxConnNum", "20");
        prop.setProperty("timeout", "2000");
        prop.setProperty("isDebug", "false");
        prop.setProperty("encoding", "GBK");
        OperateHandle.setProperties(prop);
        OperateHandle sed=null;
       
        String timeStampResponse = null;
        Matcher m =null;
		try {
			sed = OperateHandle.getOperateHandle("2048");
			if (null == sed){
				return null;
				
			}
			String timeStampRequest = sed.generateTSReq(inData.getBytes(),true);
			timeStampResponse = sed.generateTS(timeStampRequest);
			
			  String time = sed.getTSInfo(timeStampResponse, 1);
			    System.out.println("----------------------------------");
			    System.out.println("time == " + time);
				Pattern p = Pattern.compile("^(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})Z$");
				 m = p.matcher(time);
		} catch (Exception e) {
			logger.error("初始化时间戳失败！", e);
		}	
			Date currentDate =new Date();
			if(m.find()) {
				currentDate =new Date(Integer.valueOf(m.group(1)) - 1900, Integer.valueOf(m.group(2)) - 1, Integer.valueOf(m.group(3)), Integer.valueOf(m.group(4)), Integer.valueOf(m.group(5)), Integer.valueOf(m.group(6)));
			}
		return currentDate;
	}
}
