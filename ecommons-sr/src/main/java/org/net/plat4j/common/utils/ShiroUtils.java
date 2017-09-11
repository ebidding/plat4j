package org.net.plat4j.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import org.net.plat4j.common.user.ShiroUser;

public class ShiroUtils {
	public static ShiroUser getShiroUser() {		
		try {
			Subject currentUser = SecurityUtils.getSubject();	
			ShiroUser shiroUser =  (ShiroUser) currentUser.getPrincipal();
			return shiroUser == null?new ShiroUser():shiroUser;
		} catch(Exception e) {
			//logger.error(e);
			return new ShiroUser();
		}		
	}
	
	public static Long getUserId() {		
		Long userId = getShiroUser().getId();
		return userId == null?-1L:userId;
	}
	public static String getTrueName(){
		return getShiroUser().getTrueName();
	}
	/**
	 *  获取当前用户所对应的代理机构的Id
	 * @return
	 */
	public static Long getUserAgentId(){
		return getShiroUser().getAgentId();
	}
}
