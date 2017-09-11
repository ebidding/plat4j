package org.net.plat4j.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.plat4j.core.spring.BeanFactory;

import org.net.plat4j.common.syspage.model.SysPageModel;
import org.net.plat4j.common.syspage.service.ISysPageUtilService;

/**
 * 读取sys_page的信息
 * @author sunnn
 *
 */
public class SysPageUtil {
	private final static String CACHE_NAME = "sysPage";
	private final static String CACHE_KEY = "sysPageKey";
	public static SysPageModel getSysPage(String actionName,String methodName){
		Map<String,SysPageModel> uriMap = CacheUtils.getValue(CACHE_NAME, CACHE_KEY);
		if( uriMap== null){
			//query
			List<SysPageModel> list = null;
			ISysPageUtilService service = BeanFactory.getBean("ISysPageUtilService");
			list = service.getPageDesc();
			if(list != null&&list.size()>0){
				Map<String, SysPageModel> pageMap = buildMap(list);
				uriMap = new HashMap<String,SysPageModel>();
				//形成子页面和父页面信息的关联
				for(SysPageModel pageModel : list){
					if(StringUtils.isNotEmpty(pageModel.getParentCode())){
						pageModel.setParentPage(pageMap.get(pageModel.getParentCode()));
					}
				}
				//将所有的页面信息放入缓存中
				for(SysPageModel pageModel : list){
					uriMap.put(pageModel.getPageUri() + pageModel.getPageMethod(), pageModel);
				}
				//缓存
				CacheUtils.cacheValue(CACHE_NAME, CACHE_KEY, uriMap);
			}
			
		}
		if(uriMap == null){
			return null;
		}
		return uriMap.get(actionName + methodName);
	}
	
	/**
	 * @param list
	 * @return
	 */
	private static Map<String, SysPageModel> buildMap(List<SysPageModel> list) {
		Map<String, SysPageModel> map = new HashMap<>();
		for(SysPageModel page : list){
			map.put(page.getPageCode(), page);
		}
		return map;
	}
	
	public static void clearSysPageCache(){
		
		CacheUtils.clearCache(CACHE_NAME);
	}
}
