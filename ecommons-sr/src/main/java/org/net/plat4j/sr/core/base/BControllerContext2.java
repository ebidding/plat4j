package org.net.plat4j.sr.core.base;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.plat4j.core.spring.BeanFactory;
import net.plat4j.core.spring.Service;

import org.net.plat4j.sr.core.utils.LogHelper;

public class BControllerContext2 {
	protected final LogHelper logger = new LogHelper(getClass());
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext servletContext;

	public static final int SCOPE_REQUEST = 1;
	public static final int SCOPE_SESSION = 2;
	public static final int SCOPE_APPLICATION = 3;

	public BControllerContext2() {
	}

	public BControllerContext2(ServletContext paramServletContext,
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) {
		this.servletContext = paramServletContext;
		this.request = paramHttpServletRequest;
		this.response = paramHttpServletResponse;
	}

	public HttpServletRequest getRequest() {
		return this.request;
	}

	public void setRequest(HttpServletRequest paramHttpServletRequest) {
		this.request = paramHttpServletRequest;
	}

	public HttpServletResponse getResponse() {
		return this.response;
	}

	public void setResponse(HttpServletResponse paramHttpServletResponse) {
		this.response = paramHttpServletResponse;
	}

	public ServletContext getServletContext() {
		return this.servletContext;
	}

	public void setServletContext(ServletContext paramServletContext) {
		this.servletContext = paramServletContext;
	}

	public HttpSession getSession() {
		return this.request != null ? this.request.getSession() : null;
	}

	public HttpSession getSession(boolean create) {
		if (this.request != null) {
			return this.request.getSession(create);
		}
		if (create) {
			throw new NullPointerException("No request.");
		}
		return null;
	}

	private Object getRequestAttribute(String attrName) {
		return this.request != null ? this.request.getAttribute(attrName)
				: null;
	}

	private Object getSessionAttribute(String attrName) {
		HttpSession session = this.getSession();
		return session != null ? session.getAttribute(attrName) : null;
	}

	private Object getApplicationAttribute(String attrName) {
		return this.servletContext != null ? this.servletContext
				.getAttribute(attrName) : null;
	}

	public Object findAttribute(String attrName) {
		Object attrValue = getRequestAttribute(attrName);
		if (attrValue == null) {
			attrValue = getSessionAttribute(attrName);
		}
		if (attrValue == null) {
			attrValue = getApplicationAttribute(attrName);
		}
		return attrValue;
	}

	public Object findAttribute(String attrName, int scope) {
		switch (scope) {
		case SCOPE_REQUEST:
			return getRequestAttribute(attrName);
		case SCOPE_SESSION:
			return getSessionAttribute(attrName);
		case SCOPE_APPLICATION:
			return getApplicationAttribute(attrName);
		}
		throw new IllegalArgumentException("Invalid scope value:" + scope);
	}

	public void setBean(String attrName, Object paramObject, int scope) {
		switch (scope) {
		case SCOPE_REQUEST:
			getRequest().setAttribute(attrName, paramObject);
			break;
		case SCOPE_SESSION:
			getSession().setAttribute(attrName, paramObject);
			break;
		case SCOPE_APPLICATION:
			getServletContext().setAttribute(attrName, paramObject);
			break;
		default:
			throw new IllegalArgumentException("Invalid scope value:" + scope);
		}
	}

	public <T extends Service> T findService(String paramString, Class<T> paramClass) {
		return BeanFactory.getBean(paramString, paramClass);
	}

	public String getCookieValue(String cookieName) {
		if ((cookieName == null) || (cookieName.length() == 0)) {
			return null;
		}
		if (this.request == null) {
			return null;
		}
		Cookie[] arrayOfCookie = this.request.getCookies();
		if ((arrayOfCookie == null) || (arrayOfCookie.length == 0)) {
			return null;
		}
		for (int i = 0; i < arrayOfCookie.length; i++) {
			if (cookieName.equals(arrayOfCookie[i].getName())) {
				return arrayOfCookie[i].getValue();
			}
		}
		return null;
	}
}
