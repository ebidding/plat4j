package org.net.plat4j.sr.core.base;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unchecked", "rawtypes"})
public class BaseThreadMap {
 
	private static final InheritableThreadLocal threadLocal = new InheritableThreadLocal();
	public static Map getMap() {
		Map map = (Map) threadLocal.get();
		if (map == null) {
			map = new HashMap();
			threadLocal.set(map);  
		}
		return map;
	}
}
