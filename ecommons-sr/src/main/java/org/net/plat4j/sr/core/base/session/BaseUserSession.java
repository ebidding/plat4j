package org.net.plat4j.sr.core.base.session;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SuppressWarnings({"unchecked", "rawtypes"})
public class BaseUserSession{
	private Map map = new HashMap();
	private HttpSession session;
	public BaseUserSession(Map map){
		this.map = map;
	}
	public BaseUserSession(HttpSession session){
		this.session = session;
		Enumeration en = session.getAttributeNames();
		while(en.hasMoreElements()){
			String key = (String)en.nextElement();
			Object value = (Object)session.getAttribute(key);
			map.put(key,value);
		}
	}
	public BaseUserSession(HttpServletRequest request){
		if(true){
			Enumeration en = request.getAttributeNames();
			while(en.hasMoreElements()){
				String key = (String)en.nextElement();
				Object value = (Object)request.getAttribute(key);
				map.put(key,value);
			}			
		}
/*		if(true){
			HttpSession session = request.getSession();
			Enumeration en = session.getAttributeNames();
			while(en.hasMoreElements()){
				String key = (String)en.nextElement();
				Object value = (Object)session.getAttribute(key);
				map.put(key,value);
			}
		}*/
	}
	

	public Object getAttribute(String arg0) {
		return map.get(arg0);
	}

	public String[] getValueNames() {
		String[] rets = new String[map.size()];
		Iterator it = map.keySet().iterator();
		int i=0;
		while(it.hasNext()){
			rets[i] = (String)it.next();
			i++;
		}	
		return rets;
	}

	public void setAttribute(String arg0, Object arg1) {
		map.put(arg0, arg1);
	}
	public HttpSession getSession() {
		return session;
	}

} 
