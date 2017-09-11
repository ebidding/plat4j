package org.net.plat4j.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.net.plat4j.sr.core.utils.LogHelper;

public class AssertTools {
	private static LogHelper logger = new LogHelper(AssertTools.class);
	/**
	 * 不能为空，因为有些字段 ， 是业务完成所必须的
	 * 
	 * @param object
	 * @param message
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}
	/**
	 * 检查对应对象 不能为空的 属性；
	 * 
	 * @param obj
	 * @param str
	 */
	public static void notNullProperty(Object obj, String[] str) { 
		notNull(obj, "this business object is null !!"); 
		Map<String, Object> map = getMethod(obj);
		Set<String> set = map.keySet();
		for (int i = 0; i < str.length; i++) {
			if (!set.contains(str[i].trim())) {
				notNull(null, obj.getClass() + "'s property:【" + str[i] + "】  is null!");
			}

		}
	}
	/**
	 * 将对象的拆分成一个map集合 《key=property，value=值》
	 * 
	 * @param obj
	 *            要检查的对象；
	 * @param flag
	 *            =1将有值得都打出来 ；=other将对象所有的属性及他的值打出来；；
	 * @return
	 */
	private static Map<String, Object> getMethod(Object obj) {
		TreeMap<String, Object> map = new TreeMap<String, Object>();
		if (obj != null) {
			try {
				Class<?> clazz = obj.getClass();
				for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
					Field field[] = clazz.getDeclaredFields();
					for (int i = 0; i < field.length; i++) {
						String[] meth = propertyToMethod(field[i], "get");
						Method method = clazz.getMethod(meth[0]);
						Object callBackValue = method.invoke(obj);
						String property = meth[2];
						if (callBackValue != null) {
							map.put(property, callBackValue);
						}
					}
				}
			} catch (SecurityException e) {
				logger.error(e);
			} catch (NoSuchMethodException e) {
				logger.error(e);
			} catch (IllegalArgumentException e) {
				logger.error(e);
			} catch (IllegalAccessException e) {
				logger.error(e);
			} catch (InvocationTargetException e) {
				logger.error(e);
			}
		}
		return map;
	}
	/**
	 * 将属性Field 拆分 并转为方法名；
	 * 
	 * @param field
	 *            属性
	 * @param prefix
	 *            前缀
	 * @return
	 */
	private static String[] propertyToMethod(Field field, String prefix) {
		String node[] = field.toString().split(" ");
		int lastPoint = node[2].lastIndexOf(".");
		String type = node[1];
		String propertity = node[2].substring(lastPoint + 1);
		String methodNameOfPropertity = propertyToMethodName(propertity, prefix);
		String methodAndType[] = { methodNameOfPropertity, type, propertity };
		return methodAndType;
	}
	/**
	 * 直接将属性转为方法名；
	 * 
	 * @param propertyName
	 * @param prefix
	 * @return
	 */
	private static String propertyToMethodName(String propertyName, String prefix) {
		String head = propertyName.substring(0, 1).toUpperCase();
		String body = propertyName.substring(1);
		String method = prefix.concat(head.concat(body));
		return method;

	}
}
