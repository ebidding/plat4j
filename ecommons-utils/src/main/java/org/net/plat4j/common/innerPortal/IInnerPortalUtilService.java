package org.net.plat4j.common.innerPortal;

import org.net.plat4j.common.innerPortal.model.Yom2InnerBulletin;

public interface IInnerPortalUtilService {
	/**
	 * 将信息显示在内网门户中 滚动显示
	 * @param innerBulltin
	 * @return
	 */
    Long saveInnerBulletin(Yom2InnerBulletin innerBulltin);
    /**
     * 将在内网门户滚动显示去掉
     * @param id
     * @return
     */
    Boolean revocationInnerBulletin(Long id);
}
