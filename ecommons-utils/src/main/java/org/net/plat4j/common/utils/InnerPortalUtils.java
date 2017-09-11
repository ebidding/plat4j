package org.net.plat4j.common.utils;

import org.net.plat4j.common.innerPortal.IInnerPortalUtilService;
import org.net.plat4j.common.innerPortal.model.Yom2InnerBulletin;

import net.plat4j.core.spring.BeanFactory;

public class InnerPortalUtils {

	public static Long saveInnerBulletin(Yom2InnerBulletin innerBulltin) {
		return getInnerPortalService().saveInnerBulletin(innerBulltin);
	}

	public static Boolean revocationInnerBulletin(Long id) {
		return getInnerPortalService().revocationInnerBulletin(id);
	}

	private static IInnerPortalUtilService getInnerPortalService() {
		return (IInnerPortalUtilService) BeanFactory.getBean("IInnerUtilPortalService");
	}
}
