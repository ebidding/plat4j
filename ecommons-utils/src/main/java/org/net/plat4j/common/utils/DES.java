package org.net.plat4j.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
/**
 * 加密String
 * @author Administrator
 *
 */
public class DES {

	private static String Algorithm = "DESede";
	   
    private static final String Default_Key = "A3F2DEI569WBCJSJEOTY45DYQWF68H1Y";
   
    public static String encryptString(String value) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        return encryptString(Default_Key, value);
    }
   
   
    public static String encryptString(String key, String value) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        byte[] bytesKey = null, bytes = null, bytesCipher = null;
        SecretKey deskey = null;
        if (value == null)
        	throw new IllegalArgumentException("待加密字串不允许为空");
        //密码器
        Cipher desCipher = Cipher.getInstance(Algorithm);
        try{
            bytesKey = Base64.decodeBase64(key);
            deskey = new SecretKeySpec(bytesKey, Algorithm);
            bytes = value.getBytes("utf-8");//待解密的字串
            desCipher.init(Cipher.ENCRYPT_MODE, deskey);//初始化密码器，用密钥deskey,进入解密模式 
            bytesCipher = desCipher.doFinal(bytes);
            return Base64.encodeBase64String(bytesCipher);
        }
        finally{
            bytesKey = null;
            bytes = null;
            bytesCipher = null;
        }
     }
   
    public static String decryptString(String value) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        return decryptString(Default_Key, value);
    }
   
    public static String decryptString(String key, String value) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        byte[] bytesKey = null, bytes = null, bytesCipher = null;
        SecretKey deskey = null;
        if (value == null)
            throw new IllegalArgumentException("待解密字串不允许为空");
        //密码器
        Cipher desCipher = Cipher.getInstance(Algorithm);
        try{
            bytesKey = Base64.decodeBase64(key);
            deskey = new SecretKeySpec(bytesKey, Algorithm);
            bytes = Base64.decodeBase64(value);//加密后的字串
            desCipher.init(Cipher.DECRYPT_MODE, deskey);//初始化密码器，用密钥deskey,进入解密模式 
            bytesCipher = desCipher.doFinal(bytes);
            return (new String(bytesCipher,"UTF-8"));
        }
        finally{
            bytesKey = null;
            bytes = null;
            bytesCipher = null;
        }
     }

}
