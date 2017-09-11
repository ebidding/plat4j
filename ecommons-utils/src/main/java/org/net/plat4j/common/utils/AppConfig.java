
package org.net.plat4j.common.utils;


import org.apache.commons.lang3.StringUtils;

import net.plat4j.core.spring.BeanFactory;

import org.net.plat4j.common.appconfig.IAppConfigService;

public class AppConfig extends AppConfigure{	
	
	
	/**
	 * 获取配置项的值
	 * @param agentId 招标代理机构id
	 * @param key
	 * @return
	 */
	public static String getProperty(Long agentId, String key){
		IAppConfigService appConfigService = BeanFactory.getBean("IAppConfigService");
		String value = appConfigService.getProperties(agentId, key);
		if(StringUtils.isNotEmpty(value)){
			return value;
		}
		return getProperty(key);
	}
}
