/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: cairw
	Version: 1.0
	Created Time: 2015年9月18日 下午4:48:13
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2015年9月18日下午4:48:13		cairw			Create file
=========================================================================
*/

package org.net.plat4j.common.utils;

import java.util.List;

import org.net.plat4j.common.user.SysUserAgencyInfo;

/**
 * @author cairw
 *
 */
public class SysUerTypeUtils {
	
	/**
	 * 代理机构。 1
	 */
	public static final int AGENCY = 0x01 << 0;
	/**
	 * 投标人。2
	 */
	public static final int SUPPLIER = 0x01 << 1;
	/**
	 * 招标人。4
	 */
	public static final int TENDEREE = 0x01 << 2;
	/**
	 * 专家。 8
	 */
	public static final int EXPERT = 0x01 << 3;
	/**
	 * 监标人。16
	 */
	public static final int SUPERVISOR = 0x01 << 4;
	
	/**
	 *系统管理员  32
	 */
	public static final int ADMIN=0X01<<5;
	
	
	/**
	 * 判断当前选择身份是否是代理机构
	 * 
	 * <pre>
	 * Author: cairw
	 * Created Time: 2015-09-18 10:02:39
	 * </pre>
	 * 
	 * @param 
	 * @return 
	 */
	public static boolean isAgent(String userType) {
	 	return isCheckedType2(userType,AGENCY);
	}
	
	/**
	 * 判断当前选择身份是否是投标人
	 * 
	 * <pre>
	 * Author: cairw
	 * Created Time: 2015-09-18 10:02:39
	 * </pre>
	 * 
	 * @param 
	 * @return 
	 */
	public static boolean isSupplier(String userType) {
		return isCheckedType2(userType,SUPPLIER);
	}
	
	/**
	 * 判断当前选择身份是否是招标人
	 * 
	 * <pre>
	 * Author: cairw
	 * Created Time: 2015-09-18 10:02:39
	 * </pre>
	 * 
	 * @param 
	 * @return 
	 */
	public static boolean isTenderee(String userType) {
		return isCheckedType2(userType,TENDEREE);
	}
	
	

	/**
	 * 判断当前选择身份是否是专家
	 * 
	 * <pre>
	 * Author: cairw
	 * Created Time: 2015-09-18 10:02:39
	 * </pre>
	 * 
	 * @param 
	 * @return 
	 */
	public static boolean isExpert(String userType) {
		return isCheckedType2(userType,EXPERT);
	}
	
	/**
	 * 判断当前选择身份是否是监标人
	 * 
	 * <pre>
	 * Author: cairw
	 * Created Time: 2015-09-18 10:02:39
	 * </pre>
	 * 
	 * @param 
	 * @return 
	 */
	public static boolean isSupervisor(String userType) {
		return isCheckedType2(userType,SUPERVISOR);
	}
	
	/**
	 * 判断这个公司是否有代理机构权限
	 * 
	 * <pre>
	 * Author: cairw
	 * Created Time: 2015-09-18 10:02:39
	 * </pre>
	 * 
	 * @param 
	 * @return 
	 */
	public static boolean hasAgent(List <SysUserAgencyInfo> userAgencyInfos) {
	 	return isCheckedType(userAgencyInfos,AGENCY);
	}
	
	
	/**
	 * 判断这个公司是否有投标人权限
	 * 
	 * <pre>
	 * Author: cairw
	 * Created Time: 2015-09-18 10:02:39
	 * </pre>
	 * 
	 * @param 
	 * @return 
	 */
	public static boolean hasSupplier(List <SysUserAgencyInfo> userAgencyInfos) {
		return isCheckedType(userAgencyInfos,SUPPLIER);
	}
	
	/**
	 * 判断这个公司是否有招标人权限
	 * 
	 * <pre>
	 * Author: cairw
	 * Created Time: 2015-09-18 10:02:39
	 * </pre>
	 * 
	 * @param 
	 * @return 
	 */
	public static boolean hasTenderee(List <SysUserAgencyInfo> userAgencyInfos) {
		return isCheckedType(userAgencyInfos,TENDEREE);
	}
	
	

	/**
	 * 判断这个人 是否有专家权限
	 * 
	 * <pre>
	 * Author: cairw
	 * Created Time: 2015-09-18 10:02:39
	 * </pre>
	 * 
	 * @param 
	 * @return 
	 */
	public static boolean hasExpert(List<SysUserAgencyInfo> userAgencyInfos) {
		return isCheckedType(userAgencyInfos,EXPERT);
	}
	
	/**
	 * 判断这个人是否有监标人权限
	 * 
	 * <pre>
	 * Author: cairw
	 * Created Time: 2015-09-18 10:02:39
	 * </pre>
	 * 
	 * @param 
	 * @return 
	 */
	public static boolean hasSupervisor(List<SysUserAgencyInfo> userAgencyInfos) {
		return isCheckedType(userAgencyInfos,SUPERVISOR);
	}
	
	
	
	
	/**
	 * 
	 * @param userAgencyInfos
	 * @param checkType
	 * @return
	 */
	private static boolean isCheckedType(List <SysUserAgencyInfo> userAgencyInfos ,int checkType){
		for (SysUserAgencyInfo sysUserAgencyInfo : userAgencyInfos) {
			String userType = sysUserAgencyInfo.getIdentity().toString();
			int typeInt = Integer.valueOf(userType);
			if((typeInt & checkType) == checkType) return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param userType
	 * @param checkType
	 * @return
	 */
	private static boolean isCheckedType2(String userType ,int checkType){
			int typeInt = Integer.valueOf(userType);
			if((typeInt & checkType) == checkType) return true;
		return false;
	}
}
