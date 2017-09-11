package org.net.plat4j.sr.core.utils.impl;

import java.lang.reflect.Method;

import org.net.plat4j.sr.core.base.BaseException;
import org.net.plat4j.sr.core.utils.IBaseBeanUtils;
import org.net.plat4j.sr.core.utils.IBaseReflectUtils;

@SuppressWarnings({"rawtypes"})
public class BaseBeanUtils implements IBaseBeanUtils {

	private IBaseReflectUtils baseReflectUtils;

	public void setBaseReflectUtils(IBaseReflectUtils baseReflectUtils) {
		this.baseReflectUtils = baseReflectUtils;
	}

	/*public static void main(String[] args) throws Exception {
		IBaseReflectUtils baseReflectUtils = new BaseReflectUtils();
		BaseBeanUtils tt = new BaseBeanUtils();
		tt.setBaseReflectUtils(baseReflectUtils);

		DemoCompany src = new DemoCompany();
		src.setCompanyCode("A");
		src.setUpdateTime(null);

		DemoCompany dst = new DemoCompany();
		dst.setCompanyName("B");
		dst.setUpdateTime(new Date());

		dst = (DemoCompany) tt.copyProperties(dst, src,
				new String[] { "updateTime" });
		System.out.println(dst.getCompanyCode());
		System.out.println(dst.getCompanyName());
		System.out.println(dst.getUpdateTime());
	}*/

	public Object copyProperties(Object dst, Object src, String[] fields)
			throws Exception {
		Class srcCls = src.getClass();
		Class dstCls = dst.getClass();
		Method[] methods = srcCls.getMethods();
		for (int i = 0, j = methods.length; i < j; i++) {
			Method method = methods[i];
			if (method.getName().indexOf("get") == 0) {
				String fieldName = method.getName().substring(3, 4)
						.toLowerCase()
						+ method.getName().substring(4);
				if (isBeanField(srcCls, fieldName)) {
					Method srcGet = baseReflectUtils.getGetMethod(srcCls,
							fieldName);
					if (!srcGet.getReturnType().isArray()) {
						Object value = srcGet.invoke(src, new Object[] {});
						if (value != null || SrPhp.is_array(fieldName, fields)) {
							if (isBeanField(dstCls, fieldName)) {
								Method dstSet = baseReflectUtils.getSetMethod(
										srcCls, fieldName);
								dstSet.invoke(dst, new Object[] { value });
							}
						}
					}
				}
			}
		}
		return dst;
	}

	public boolean isBeanField(Class cls, String fieldName) {
		boolean ret = false;
		if (baseReflectUtils.hasGetMethod(cls, fieldName)
				&& baseReflectUtils.hasSetMethod(cls, fieldName)) {
			try {
				Method getMethod = baseReflectUtils
						.getGetMethod(cls, fieldName);
				Method setMethod = baseReflectUtils
						.getSetMethod(cls, fieldName);
				if (getMethod.getParameterTypes().length == 0
						&& setMethod.getParameterTypes().length == 1) {
					ret = true;
				}
			} catch (Exception e) {
				return false;
			}
		}
		return ret;
	}

	public Object getField(Object instance, String fieldName) {
		try {
			Method method = baseReflectUtils.getGetMethod(instance.getClass(),
					fieldName);
			return method.invoke(instance, new Object[] {});
		} catch (Exception e) {
			throw new BaseException(e);
		}

	}

	public void setField(Object instance, String fieldName, Object fieldValue) {
		try {
			Method method = baseReflectUtils.getSetMethod(instance.getClass(),
					fieldName);
			method.invoke(instance, new Object[] { fieldValue });
		} catch (Exception e) {
			throw new BaseException(e);
		}
	}

}
