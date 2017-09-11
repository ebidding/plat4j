/*
	Copyright (C) 2014 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2014-08-08 10:26:41
	
	Revision History:
	Version     Date              				Author			Comments
	1.0         	2014-08-08 10:26:41		yujie				Create file
=========================================================================
 */

package org.net.plat4j.common.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 缓存工具类，提供保存缓存值和获取缓存值操作。
 * 
 * @author yujie
 * 
 */
public class CacheUtils {
	/**
	 * 缓存指定的对象到指定的缓存中。
	 * 
	 * Author: yujie Created Time: 2014-08-08 10:31:43
	 * 
	 * @param T 要缓存的对象的类型。
	 * @param cacheName 缓存名称，应该命名为特有的名称，以避免与其它的缓存名称发生冲突。
	 * @param key 缓存中的键值。
	 * @param object 要缓存的对象。
	 */
	public static <T> void cacheValue(String cacheName, String key, T object) {
		CacheManager manager = CacheManager.getInstance();
		Cache cache = manager.getCache(cacheName);
		if(cache == null){
			manager.addCache(cacheName);
			cache = manager.getCache(cacheName);
		}
		Element element = new Element(key, object);
		cache.put(element);
	}

	/**
	 * 获取缓存的对象，如果没有指定的缓存名称或者键值，将返回 null。
	 * 
	 * Author: yujie Created Time: 2014-08-08 10:31:49
	 * 
	 * @param T 要获取的缓存的对象的类型。
	 * @param cacheName 缓存名称，应该命名为特有的名称，以避免与其它的缓存名称发生冲突。
	 * @param key 缓存中的键值。
	 * @return 缓存的对象，如果没有指定的缓存名称或者键值，将返回 null。
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getValue(String cacheName, String key) {
		CacheManager manager = CacheManager.getInstance();
		Cache cache = manager.getCache(cacheName);
		if(cache == null){
			return null;
		}
		Element element = cache.get(key);
		Object cacheObj = element == null ? null : element.getObjectValue();
		return cacheObj == null ? null : (T) cacheObj;
	}
	
	/**
	 * 从指定的缓存中移除指定键值对应的对象。
	 * 
	 * <pre>
	 * Author: yujie
	 * Created Time: 2014-08-23 12:05:00
	 * </pre>
	 * 
	 * @param cacheName 缓存名称，应该命名为特有的名称，以避免与其它的缓存名称发生冲突。
	 * @param key 缓存中的键值。
	 */
	public static void removeValue(String cacheName, String key) {
		CacheManager manager = CacheManager.getInstance();
		Cache cache = manager.getCache(cacheName);
		cache.remove(key);		
	}

	/**
	 * 移除指定的缓存中的所有对象。
	 * 
	 * <pre>
	 * Author: yujie
	 * Created Time: 2014-08-23 12:05:00
	 * </pre>
	 * 
	 * @param cacheName 缓存名称，应该命名为特有的名称，以避免与其它的缓存名称发生冲突。
	 */
	public static void clearCache(String cacheName) {
		CacheManager manager = CacheManager.getInstance();
		Cache cache = manager.getCache(cacheName);
		
		if(cache != null)
			cache.removeAll();
	}
}
