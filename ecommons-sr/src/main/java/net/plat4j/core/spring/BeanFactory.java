/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2015年4月14日 上午11:04:40
	
	Revision History:
	Version     Date              					Author			Comments
	1.0      2015年4月14日上午11:04:40							chenyj			Create file
=========================================================================
 */
package net.plat4j.core.spring;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * Spring bean helper for get bean in a easy coding way.
 */
public class BeanFactory {
	/**
	 * Get bean instance that managed by spring with its id.
	 * 
	 * @param beanId The id that defined in spring ApplicationContext-xxx.xml file.
	 * @return The bean that instantiated and managed by spring with the id same as <code>beanId<code>.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanId) {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		return (T)webApplicationContext.getBean(beanId);
	}
	/**
	 * Get bean instance that managed by spring with its id.
	 * 
	 * @param beanId The id that defined in spring ApplicationContext-xxx.xml file.
	 * @return The bean that instantiated and managed by spring with the id same as <code>beanId<code>.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getRmBean(String beanId) {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		Object obj = null;
		try{
			obj = webApplicationContext.getBean(beanId);
		}catch(NoSuchBeanDefinitionException e){
			obj = webApplicationContext.getBean("rm" + beanId);
		}
		return (T)obj;
	}
	
	/**
	 * Get bean instance that managed by spring with its id.
	 * 
	 * @param beanId The id that defined in spring ApplicationContext-xxx.xml file.
	 * 
	 * @see net.plat4j.core.spring.BeanFactory
     * @deprecated As of ebs-sr-3.0.0, replaced by <code>&lt;T&gt; T BeanFactory.getBean(String beanId)</code>
     * 	to avoid type converting or warning everywhere.
	 */
	@Deprecated
	public static Object findBean(String beanId) {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		return webApplicationContext.getBean(beanId);
	}

	/**
	 * Get bean instance that managed by spring with its id. Compared with <code>getBean(String beanId)</code>
	 * 	method, this method will do a type checked, if the parameter <code>typeClass</code> is not assignable from
	 *  the instance found from spring, a ClassCastException will be thrown.  
	 * 
	 * @param beanId The id that defined in spring ApplicationContext-xxx.xml file.
	 * @param typeClass The class of the type of the expected instance.
	 * @return The bean that instantiated and managed by spring with the id same as <code>beanId<code>.
	 */
	@SuppressWarnings("unchecked")
	public static<T> T getBean(String beanId, Class<T> typeClass) {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		Object obj = webApplicationContext.getBean(beanId);
		if(typeClass.isAssignableFrom(obj.getClass())){
			return (T)obj;
		}
		throw new ClassCastException("The real class of Bean found is '" + obj.getClass().getName() + "'.");
	}
	@SuppressWarnings("unchecked")
	public static<T> T getRmBean(String beanId, Class<T> typeClass) {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		Object obj = null; 
		try {
			obj = webApplicationContext.getBean(beanId);
		} catch (NoSuchBeanDefinitionException e) {
			obj = webApplicationContext.getBean("rm" + beanId);
		}
		
		if(typeClass.isAssignableFrom(obj.getClass())){
			return (T)obj;
		}
		throw new ClassCastException("The real class of Bean found is '" + obj.getClass().getName() + "'.");
	}

	/**
	 * Get bean instance that managed by spring with its id.
	 * 
	 * @param beanId The id that defined in spring ApplicationContext-xxx.xml file.
	 * 
	 * @see net.plat4j.core.spring.BeanFactory
     * @deprecated As of ebs-sr-3.0.0, replaced by
     *  <code>&lt;T&gt; T BeanFactory.getBean(String beanId, Class<T> typeClass)</code>
     * 	to avoid type converting or warning everywhere.
	 */
	@Deprecated
	public static Service findService(String beanId, Class<?> serviceClass){
		Object obj = findBean(beanId);
		if(serviceClass.isAssignableFrom(obj.getClass())){
			return (Service)obj;
		}
		throw new ClassCastException("The real class of Bean found is '" + obj.getClass().getName() + "'.");
	}
}
