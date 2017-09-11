package org.net.plat4j.api.outer.ca;

/**
 * CA 相关接口
 */
public interface ICAService {
    /**
     * 得到CA摘要.
     * 
     * @param CAOrg
     * @param fileId 文件id.
     * @return 摘要信息.
     */
    String getDigestFileId(String fileId) throws Exception;

    /**
     * 得到文件CA摘要 .
     * 
     * @param filePath
     *            文件路径.
     * @return　摘要内容.
     * @.
     */
    String getDigestFile(String filePath) throws Exception;

    /**
     * 验证签名文件.
     * 
     * @param filePath
     *            文件路径.
     * @param strSignedData
     *            签名数据.
     * @param strCeritificate
     *            证书信息.
     * @return true-验证成功,false-验证错误.
     * @.
     */
    boolean verifySignFile(String filePath, String strSignedData,
	    String strCeritificate) throws Exception;

    /**
     * 使用指定密钥对加密文件进行对称解密操作.
     * 
     * @param outFilePath
     *            解密后的输出文件路径,相对路径.
     * @param inFilePath
     *            待解密文件路径,相对路径.
     * @param key
     *            密钥.
     * @exception Exception.
     */
    String[] decryptFile(String outFilePath, String inFilePath,
	    String key, String decryptFileName) throws Exception;

    /**
     * 使用指定密钥对加密文件进行对称解密操作.
     * 
     * @param outFilePath
     *            解密后的输出文件路径,相对路径.
     * @param inFilePath
     *            待解密文件路径,相对路径.
     * @param inFilePath2
     *            拼接文件路径,相对路径.
     * @param key
     *            密钥.
     * @exception Exception.
     */
    String[] decryptFile(String outFilePath, String inFilePath,
	    String inFilePath2, String key, String decryptFileName) throws Exception;

    /**
     * 加密机签名
     * 
     * @param filePath
     *            文件路径.
     * @return @
     */
    String serverSignFile(String filePath) throws Exception;

    /**
     * 加密机签名
     * 
     * @param filePath
     *            文件路径.
     * @param serverDevType
     *            加密机证书位.
     * @return @
     */
    String serverSignFile(String filePath, String serverDevType, String serverDevPassword) throws Exception;

    /**
     * 加密机签名
     * 
     * @param src
     *            待签名的数据.
     * @return @
     */
    String serverSign(String src) throws Exception;

    /**
     * 加密机签名
     * 
     * @param src
     *            待签名的数据.
     * @param serverDevType
     *            加密机证书位.
     * @return @
     */
    String serverSign(String src, String serverDevType, String serverDevPassword) throws Exception;

    /**
     * 加密机签名
     * 
     * @param src
     *            待签名的数据.
     * @return @
     */
    byte[] serverSign(byte[] src) throws Exception;

    /**
     * 加密机签名
     * 
     * @param src
     *            待签名的数据.
     * @param serverDevType
     *            加密机证书位.
     * @return @
     */
    byte[] serverSign(byte[] src, String serverDevType, String serverDevPassword) throws Exception;

    /**
     * 数字信封打包.
     * 
     * @param i
     *            信封类型 1组成数字信封 2拆解数字信封.
     * @param data
     *            数据块.
     * @return.
     * @.
     */
    String envelope(Integer i, String data) throws Exception;

    /**
     * 
     * 数字信封打包.
     * 
     * @param i
     *            信封类型 1组成数字信封 2拆解数字信封.
     * @param data
     *            数据块.
     * @param serverDevType
     *            加密机证书位.
     * @param serverDevPassword
     *            加密机证书密码.
     * @return.
     * @.
     */
    String envelope(Integer i, String data, String serverDevType,
	    String serverDevPassword) throws Exception;
    
	/**
	 * PDF电子签章+PDF电子签名.
	 * @param agentId 签章机构ID.
	 * @param pdfPath WebRoot绝对路径.
	 * @param pdfPath 原始PDF相对路径.
	 * @param savePath 输出PDF相对路径.
	 * @param textPosition 定位文本.
	 * @throws Exception.
	 */
    String signaturePDF(Long agentId, String unsignedPDF,String signedPDF, String textPosition) throws Exception;
    /**
     * PDF电子签章+PDF电子签名.
     * @param agentId 签章机构ID.
     * @param unsignedPDFId
     * @param signedPDF 输出PDF相对路径.
     * @param textPosition 定位文本.
     * @return
     * @throws Exception
     */
    String signaturePDFByPdfId(Long agentId, String unsignedPDFId,String signedPDF, String textPosition) throws Exception;

	/**
	 * 获取时间戳.
	 * 
	 * @param filePath 请求获取时间戳的文件路径.
	 * @return 时间戳回包.
	 * @throws Exception.
	 */
    Long getTimestampFileId(String filePath) throws Exception;

	/**
	 * 验证是否绑定CA.
	 * 
	 * @param certPublicKey 证书公钥.
	 * @return 0-成功绑定,1-未绑定,2-绑定的证书不在有效期.
	 * @throws Exception.
	 */
    int verifyBingCa(String certPublicKey) throws Exception;
    
    /**
	 * 投标文件.
	 * 
	 * @param filePath 投标文件路径
	 * @return
	 * @throws Exception.
	 */
    String[] tenderFile(String filePath) throws Exception;
}
