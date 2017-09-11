package org.net.plat4j.common.utils;

import org.net.plat4j.api.outer.ca.ICAService;

import net.plat4j.core.spring.BeanFactory;

public class CAUtils {
	/**
	 * 得到CA摘要.
	 * 
	 * @param CAOrg
	 * @param fileId 文件id.
	 * @return 摘要信息.
	 */
	public static String getDigestFileId(String fileId) throws Exception {
	    return getService().getDigestFileId(fileId);
	}
	
	/**
	 * 得到文件CA摘要 .
	 * 
	 * @param filePath 文件路径.
	 * @return　摘要内容.
	 * @throws Exception.
	 */
	public static String getDigestFile(String filePath) throws Exception {
	    return getService().getDigestFile(filePath);
	}	 
	
	/** 验证签名文件. */
	public static boolean verifySignFileId(String fileId, String strSignedData, String strCeritificate) throws Exception {
		return verifySignFile(FileUtil.getFilePathById(fileId), strSignedData, strCeritificate);
	}
	
	/**
	 * 验证签名文件.
	 * 
	 * @param filePath 文件路径.
	 * @param strSignedData 签名数据.
	 * @param strCeritificate 证书信息.
	 * @return true-验证成功,false-验证错误.
	 * @throws Exception.
	 */
	public static boolean verifySignFile(String filePath, String strSignedData, String strCeritificate) throws Exception {		
	    return getService().verifySignFile(filePath, strSignedData, strCeritificate);
	}
	
	/**
	 * 使用指定密钥对加密文件进行对称解密操作.
	 * 
	 * @param outFilePath 解密后的输出文件路径,相对路径.
	 * @param inFileId 待解密文件路径,相对路径.
	 * @param key 密钥.
	 * @param decryptFileName 解密后的文件名. 
	 * @exception Exception.
	 */
	public static String[] decryptFileId(String outFilePath, String inFileId, String key,String decryptFileName) throws Exception {
		return decryptFile(outFilePath, FileUtil.getFilePathById(inFileId), key,decryptFileName);
	}
	
	/**
	 * 使用指定密钥对加密文件进行对称解密操作.
	 * 
	 * @param outFilePath 解密后的输出文件路径,相对路径.
	 * @param inFileId 待解密文件路径,相对路径.
	 * @param inFileId2 拼接文件路径,相对路径.
	 * @param key 密钥.
	 * @param decryptFileName 解密后的文件名. 
	 * @exception Exception.
	 */
	public static String[] decryptFileId(String outFilePath, String inFileId,String inFileId2, String key,String decryptFileName) throws Exception {
		return decryptFile(outFilePath, FileUtil.getFilePathById(inFileId),FileUtil.getFilePathById(inFileId2), key,decryptFileName);
	}
	
	/**
	 * 使用指定密钥对加密文件进行对称解密操作.
	 * 
	 * @param outFilePath 解密后的输出文件路径,相对路径.
	 * @param inFilePath 待解密文件路径,相对路径.
	 * @param key 密钥.
	 * @exception Exception.
	 */
	public static String[] decryptFile(String outFilePath, String inFilePath, String key,String decryptFileName) throws Exception {		
	    return getService().decryptFile(outFilePath, inFilePath, key, decryptFileName);
	}
	
	/**
	 * 使用指定密钥对加密文件进行对称解密操作.
	 * 
	 * @param outFilePath 解密后的输出文件路径,相对路径.
	 * @param inFilePath 待解密文件路径,相对路径.
	 * @param inFilePath2 拼接文件路径,相对路径.
	 * @param key 密钥.
	 * 
	 * @exception Exception.
	 */
	public static String[] decryptFile(String outFilePath, String inFilePath, String inFilePath2, String key,String decryptFileName) throws Exception {
	    return getService().decryptFile(outFilePath, inFilePath, inFilePath2, key, decryptFileName);
	}
	 
	/**
	 * 得到签名.
	 * @param fileId 文件id.
	 * 
	 * @return 签名数据.
	 * @throws Exception.
	 */
	public static String serverSignFileId(String fileId) throws Exception {
		return serverSignFile(FileUtil.getFilePathById(fileId));
	}
	
	/**
	 * 得到签名.
	 * 
	 * @param fileId 文件id.
	 * @param serverDevType 加密机证书位.
	 * @return 签名数据.
	 * @throws Exception.
	 */
	public static String serverSignFileId(String fileId,String serverDevType) throws Exception {
		return serverSignFile(FileUtil.getFilePathById(fileId));
	}
	
	/**
	 * 加密机签名
	 * 
	 * @param filePath 文件路径.
	 * @return
	 * @throws Exception
	 */
	public static String serverSignFile(String filePath) throws Exception {
	    return getService().serverSignFile(filePath);
	}
	
	/**
	 * 加密机签名
	 * 
	 * @param filePath 文件路径.
	 * @param serverDevType 加密机证书位.
	 * @return
	 * @throws Exception
	 */
	public static String serverSignFile(String filePath,String serverDevType, String serverDevPassword) throws Exception {
	    return getService().serverSignFile(filePath, serverDevType,serverDevPassword);
	}
	
	/**
	 * 加密机签名
	 * 
	 * @param src 待签名的数据.
	 * @return
	 * @throws Exception
	 */
	public static String serverSign(String src) throws Exception {
	    return getService().serverSign(src);
	}
	
	/**
	 * 加密机签名
	 * 
	 * @param src 待签名的数据.
	 * @param serverDevType 加密机证书位.
	 * @return
	 * @throws Exception
	 */
	public static String serverSign(String src,String serverDevType,String serverDevPassword) throws Exception {
	    return getService().serverSign(src, serverDevType,serverDevPassword);
	}
	
	/**
	 * 加密机签名
	 * 
	 * @param src 待签名的数据.
	 * @return
	 * @throws Exception
	 */
	public static byte[] serverSign(byte[] src) throws Exception {
	    return getService().serverSign(src);
	}
	
	/**
	 * 加密机签名
	 * 
	 * @param src 待签名的数据.
	 * @param serverDevType 加密机证书位.
	 * @return
	 * @throws Exception
	 */
	public static byte[] serverSign(byte[] src,String serverDevType,String serverDevPassword) throws Exception {
	    return getService().serverSign(src, serverDevType,serverDevPassword);
	}
	
	/**
	 * 数字信封打包.
	 * 
	 * @param i 信封类型 1组成数字信封 2拆解数字信封.
	 * @param data 数据块.
	 * @return.
	 * @throws Exception.
	 */
	public static String envelope(Integer i, String data) throws Exception {
	    return getService().envelope(i, data);
	}
	
	/**
	 * 
	 * 数字信封打包.
	 * 
	 * @param i 信封类型 1组成数字信封 2拆解数字信封.
	 * @param data 数据块.
	 * @param serverDevType 加密机证书位.
	 * @param serverDevPassword 加密机证书密码.
	 * @return.
	 * @throws Exception.
	 */
	public static String envelope(Integer i,String data,String serverDevType,String serverDevPassword) throws Exception {
	    return getService().envelope(i, data, serverDevType, serverDevPassword);
	}
	
	/**
	 * PDF电子签章+PDF电子签名.
	 * @param agentId 签章机构ID.
	 * @param pdfPath WebRoot绝对路径.
	 * @param pdfPath 原始PDF相对路径.
	 * @param savePath 输出PDF相对路径.
	 * @param textPosition 定位文本.
	 * @throws Exception.
	 */
	public static String signaturePDF(Long agentId, String unsignedPDF,String signedPDF, String textPosition) throws Exception {
	    return getService().signaturePDF(agentId, unsignedPDF, signedPDF, textPosition);
	}
	
	public static String signaturePDFByPdfId(Long agentId, String unsignedPDFId,String signedPDF, String textPosition)
			throws Exception{
	    return getService().signaturePDFByPdfId(agentId, unsignedPDFId, signedPDF, textPosition);
	}
	/**
	 * 获取时间戳.
	 * 
	 * @param filePath 请求获取时间戳的文件路径.
	 * @return 时间戳回包.
	 * @throws Exception.
	 */
	public static Long getTimestampFileId(String filePath) throws Exception {
	    return getService().getTimestampFileId(filePath);
	}
	
	/**
	 * 验证是否绑定CA.
	 * 
	 * @param certPublicKey 证书公钥.
	 * @return 0-成功绑定,1-未绑定,2-绑定的证书不在有效期.
	 * @throws Exception.
	 */
	public static int verifyBingCa(String certPublicKey) throws Exception {
	    return getService().verifyBingCa(certPublicKey);
	}
	
	/**
	 * 投标文件.
	 * 
	 * @param fileId 文件id.
	 * @return.
	 */
	public static String[] tenderFileId(String fileId) throws Exception {
		return tenderFile(FileUtil.getFilePathById(fileId));
	}
	
	/**
	 * 投标文件.
	 * 
	 * @param filePath 投标文件路径
	 * @return
	 * @throws Exception.
	 */
	public static String[] tenderFile(String filePath) throws Exception {
		return getService().tenderFile(filePath);
	}
	public static ICAService getService(){
		String caOrg = ConfigUtils.getPlatformConfigValue("FUNCTION_SWITCH", "CA_ORG");
		System.out.println(caOrg);
		if("ZRHY".equals(caOrg)){
			return BeanFactory.getBean("ZrhyCaService");
		}else{
			return BeanFactory.getBean("ICAService");
		}
	}
}
