package org.net.plat4j.sr.core.base;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class BaseSrFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		//BaseLogFactoryImpl logFactory = BaseLogFactory.getFactory();

		chain.doFilter(request, response);

		//BaseLogFactoryImpl baseLogFactory = (BaseLogFactoryImpl) logFactory;
		//baseLogFactory.flush();
	}

	public void init(FilterConfig arg0) throws ServletException {
		/*		String key = System.currentTimeMillis() +"";
		 System.out.println("sysid8:"+key);
		 BaseLogFactory.newFactory("org.apache.commons.logging.impl.LogFactoryImpl", BaseLogFactory.getContextClassLoader());*/

		//		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log","org.net.plat4j.sr.core.base.log.BaseLog");
		//		System.setProperty("org.apache.commons.logging.log","org.net.plat4j.sr.core.base.log.BaseLog");
	}

}
