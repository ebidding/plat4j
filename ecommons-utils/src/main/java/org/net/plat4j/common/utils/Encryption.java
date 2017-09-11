package org.net.plat4j.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.net.plat4j.sr.core.utils.LogHelper;
/**
 * Title:        字符串MD5加密  位移性加密
 * Description:
 * Copyright:    Copyright (c) 2002
*/
public class Encryption
{
  // input
  //   b:   输入串
  //   alg: 算法,可取的值有:SRA, SRA-1, MD5.
  // return:
  //   若算法存在,则返回输入串b的信息摘要;
  //   否则,返回原来的输入串.
private static LogHelper logger = new LogHelper(Encryption.class);
  public static String computeDigest(String str, String alg)
  {
    MessageDigest currentAlgorithm = null;

    try
    {
      currentAlgorithm = MessageDigest.getInstance(alg);
    }
    catch(NoSuchAlgorithmException e)
    {
      return str;
    }

    currentAlgorithm.reset();
    currentAlgorithm.update(str.getBytes());
    byte[] hash = currentAlgorithm.digest();
    String d = "";
    int usbyte = 0;  // unsigned byte
    for (int i = 0; i < hash.length; i++)
    {
      usbyte = hash[i] & 0xFF ;   // byte-wise AND converts signed byte to unsigned.
      if(usbyte<16)
        d += "0" + Integer.toHexString(usbyte);   // pad on left if single hex digit.
      else
        d +=       Integer.toHexString(usbyte);
    }// for

    return d.toUpperCase();
  }

  public static String computeDigest(String str)
  {
    return computeDigest(str, "MD5");
  }

  public static String createRegisterCode(String str)
  {
    return computeDigest(str);
  }

  public static String encrypt( String str )
  {
    String digestStr = computeDigest(str);

    return computeDigest(digestStr);
  }

  public static String encode( String str )
  {
    byte[] bytesStr = str.getBytes( ) ;
    for (int i = 0; i < bytesStr.length; i++)
      bytesStr[i] ^= 0x1A;

    return new String( bytesStr ) ;
  }

  public static String decode( String str )
  {
    byte[] bytesStr = str.getBytes( ) ;
    for (int i = 0; i < bytesStr.length; i++)
      bytesStr[i] ^= 0x1A;

    return new String( bytesStr ) ;
  }
  /**
	 * 将字符串加密成16进制字符串
	 * @param str
	 * @return
	 */
	public static String encodeStringToHexString(String str){
		byte[] keyword=str.getBytes();
		String result="";
		for (int i = 0; i < keyword.length; i++) {
			byte b=keyword[i];
			//java下标从0开始，所以需要+1，与8取余，作为左移的位数
			int j=(i+1)%8;
			//循环左移
			byte b2=(byte)(b<<j | b>>(8-j));
			//转换成十六进制
			String s=Integer.toHexString(0xff & b2);
			//十六进制长度为1位时，前面补0
			result+=((s.length()==1?"0":"")+s);
		}
		return result;
	}
	/**
	 * 将16进制字符串解密成字符串
	 * @param hexStr
	 * @return
	 */
	public static String decodeHexStringToString(String hexStr){
		byte[] baKeyword = new byte[hexStr.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				Integer b=Integer.parseInt(hexStr.substring(i * 2, i * 2 + 2), 16);
				//java下标从0开始，所以需要+1，与8取余，作为左移的位数
				int j=(i+1)%8;
				//循环右移
				byte b2=(byte)(b>>j | b<<(8-j));
				
				baKeyword[i] = b2;
			} catch (Exception e) {
				logger.error(e);
			}
		}
		try {
			hexStr = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			logger.error(e1);
		}
		return hexStr;
	}

}
