package org.net.plat4j.sr.core.utils;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import net.plat4j.common.util.DateUtil;

@SuppressWarnings({"rawtypes", "unchecked"})
public class JavaBeanUtil {	
	private static LogHelper logger = new LogHelper(JavaBeanUtil.class);
		/**
		 * 将Map的值复制到java对象的同名属性
		 * 
		 * @param targetBean
		 * @param dataMap
		 * @param ignoreEmptyString
		 * @throws Exception
		 */

		public static void copyMap2Pojo(Object targetBean, Map dataMap,
				boolean ignoreEmptyString) throws Exception {
			try {
				PropertyDescriptor origDescriptors[] = PropertyUtils
						.getPropertyDescriptors(targetBean);

				for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					if ("class".equals(name)) {
						continue;
					}

					if (PropertyUtils.isWriteable(targetBean, name)) {
						Object obj = dataMap.get(name);
						if (obj == null) {
							continue;
						}

						if ((obj.toString().trim()).length() == 0
								&& ignoreEmptyString) {
							continue;
						}
						obj = convertValue(origDescriptors[i], obj);
						BeanUtils.copyProperty(targetBean, name, obj);
					}
				}// for end
			} catch (Exception e) {
				logger.error(e);
				throw e;
			}
		}
		
		/**
		 * 将java对象的值复制到Map的同名属性
		 */
	
		public static Map copyPojo2Map(Object dataBean, boolean ignoreEmptyString) throws Exception {
			Map targetMap = new HashMap();
			try {
				PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(dataBean);

	 			for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					if ("class".equals(name))
						continue;

					Object obj = PropertyUtils.getProperty(dataBean, name);
					if (obj == null) {
						continue;
					}

					if ((obj.toString().trim()).length() == 0 && ignoreEmptyString) {
						continue;
					}
					
					if (obj instanceof Date){
						//obj = CommonMethod.dateFormat((Date)obj);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						obj = sdf.format((Date)obj);
						
					}
					//obj = convertValue(origDescriptors[i], obj);
					targetMap.put(name, obj);
					// BeanUtils.copyProperty(targetMap, name, obj);

				}// for end
			} catch (Exception e) {
				logger.error(e);
				throw e;
			}
			return targetMap;
		}
		
		/**
		 * 将一个对象的属性值取出来放置到Map中。Map的Key为对象属性名称
		 * 
		 * @param bean
		 * @return
		 * @throws Exception
		 */
	
		public static Map getProperties(Object bean) throws Exception {
			if (bean == null) {
				return null;
			}

			Map dataMap = new HashMap();
			try {
				PropertyDescriptor origDescriptors[] = PropertyUtils
						.getPropertyDescriptors(bean);

				for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					if (name.equals("class")) {
						continue;
					}

					if (PropertyUtils.isReadable(bean, name)) {
						Object obj = PropertyUtils.getProperty(bean, name);
						if (obj == null) {
							continue;
						}
						obj = convertValue(origDescriptors[i], obj);
						dataMap.put(name, obj);
					}
				}// for end
			} catch (Exception e) {
				logger.error(e);
				throw e;
			}
			return dataMap;
		}

		private static Object convertValue(PropertyDescriptor origDescriptor,
				Object obj) throws Exception {
			if (obj == null) {
				return null;
			}

			if (obj.toString().trim().length() == 0) {
				return null;
			}
			if (origDescriptor.getPropertyType() == java.util.Date.class) {
				//同一个时间，第一次从界面层传过来时，obj为String类型;转化后为Date类型
				 if (obj instanceof Date) {
					 return obj;
				}else{
					obj = DateUtil.getDate(obj.toString());
				}
			}
			return obj;
		}

		/**
		 * 将一个对象的属性值复制到目标对象的同名属性值
		 * 
		 * @param fromBean
		 * @param toBean
		 */
		public static void copyProperties(Object fromBean, Object toBean) {
			try {
				BeanUtils.copyProperties(toBean, fromBean);
			} catch (IllegalAccessException e) {
				logger.error(e);
			} catch (InvocationTargetException e) {
				logger.error(e);
			}
		}
	
}
