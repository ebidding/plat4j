/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: WGY
	Version: 1.0
	Created Time: 2017年8月21日 下午3:11:15
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年8月21日下午3:11:15		WGY			Create file
=========================================================================
*/

package org.net.plat4j.common.security;

import java.util.Base64;


/**
 * <p> 
 * Static methods for translating Base64 encoded strings to byte arrays and vice-versa.
 * </p> 
 * @author WGY
 *
 */
public class Base64Utils {
	 /**
     * <p> 
     * BASE64字符串解码为二进制数据 
     * </p> 
     *  
     * @param base64 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decode(byte[] base64) throws Exception {  
        return Base64.getDecoder().decode(base64);  
    }  
      
    /** 
     * <p> 
     * 二进制数据编码为BASE64字符串 
     * </p> 
     *  
     * @param bytes 
     * @return 
     * @throws Exception 
     */  
    public static byte[] encode(byte[] bytes) throws Exception {  
        return Base64.getEncoder().encode(bytes);  
    }  
    
}
