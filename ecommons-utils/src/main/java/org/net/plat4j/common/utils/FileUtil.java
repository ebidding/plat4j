package org.net.plat4j.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import org.net.plat4j.common.file.ISysDocFileService;
import org.net.plat4j.sr.core.utils.LogHelper;
import org.net.plat4j.web.model.SysDocFile;

import net.plat4j.core.spring.BeanFactory;

public class FileUtil {

	private static final LogHelper logger = new LogHelper(FileUtil.class);
	public static final String separator = "/";
	private static final String WORK_FLOW_CONF_FILE_DIR = "workFlowFile/conf";
	private static final String WORK_FLOW_VIEW_FILE_DIR = "workFlowFile/view";
	private static final String TEMP_FILE_DIR = "tempFile";
	private static final String FOREVERE_FILE_DIR = "foreverFile";
	private static final String UPLOAD_FILE_DIR = "uploadFile";
	private static final String CLIENT_BID_FILE_DIR = "clientFile/bidFile";
	private static final String CLIENT_TPL_FILE_DIR = "clientFile/tplFile";
	private static String getCurDay(){
		return new SimpleDateFormat("yyyy/MMdd").format(new Date());
	}
	
	public static SysDocFile saveSysDocFile(File file,String id,String filePath, String isFtpFile){
		SysDocFile sysDocFile = new SysDocFile();
		sysDocFile.setId(id);
		sysDocFile.setFileName(FilenameUtils.getName(file.getName()));
		if(StringUtils.isNotEmpty(filePath)){
			sysDocFile.setFilePath(filePath);
		}else{
			sysDocFile.setFilePath(file.getAbsolutePath());
		}
		sysDocFile.setFileSize(new Double(file.length()));
		sysDocFile.setFileType(FilenameUtils.getExtension(file.getName()));
		sysDocFile.setIsDeleted("0");
		sysDocFile.setIsFtpFile(isFtpFile);
		sysDocFile.setCreateUserId(WebUtils.getUserId());
		sysDocFile.setCreateTime(new Date());
		ISysDocFileService fileSvc = BeanFactory.getBean("ISysDocFileService");
		fileSvc.save(sysDocFile);
		return sysDocFile;
	}
	
	/**
	 * 上传文件.
	 * @param agentId 招标代理机构id
	 * @param srcFile 源文件.
	 * @param descFileDir 目标文件目录.
	 */
	private static SysDocFile uploadFile(Long agentId, String srcFile, String descFileDir){
		
		File _srcFile = new File(srcFile);
		if(!_srcFile.exists()){
			logger.info("文件不存在，文件路径： [ "+srcFile+" ] ");
			return null;
		}
		return uploadFile(agentId, _srcFile, descFileDir);
	}
	/**
	 * 上传文件.
	 * @param agentId 招标代理机构id
	 * @param srcFile 源文件.
	 * @param descFileDir 目标文件目录.
	 */
	private static SysDocFile uploadFile(Long agentId, File srcFile, String descFileDir){
		if(agentId == null){
			agentId = 0l;
		}
		descFileDir = agentId + FileUtil.separator + descFileDir;
		if(srcFile == null || !srcFile.exists()){
			throw new RuntimeException("源文件不存在");
		}
		String id = getUUID();
//		String uploadFileName = srcFile.getName() + FilenameUtils.getExtension(srcFile.getName());
		String uploadFileName = id + "." + FilenameUtils.getExtension(srcFile.getName());
		FtpUtil.uploadFile(descFileDir, uploadFileName, srcFile);
		SysDocFile sysDocFile = saveSysDocFile(srcFile,id,descFileDir + separator + uploadFileName, "1");
		return sysDocFile;
	}
	
	/**
	 * 上传工作流配置文件
	 * @param agentId 招标代理机构id
	 * @param srcFilePath 配置文件绝对路径
	 * @return
	 */
	public static SysDocFile uploadWorkFlowConfFile(Long agentId, String srcFilePath){
		String descFileDir = WORK_FLOW_CONF_FILE_DIR + separator + getCurDay();
		return uploadFile(agentId, srcFilePath, descFileDir);
	}
	/**
	 * 上传工作流配置文件
	 * @param agentId 招标代理机构id
	 * @param srcFile 配置文件
	 * @return
	 */
	public static SysDocFile uploadWorkFlowConfFile(Long agentId, File srcFile){
		String descFileDir = WORK_FLOW_CONF_FILE_DIR + separator + getCurDay();
		return uploadFile(agentId, srcFile, descFileDir);
	}
	/**
	 * 上传工作流查看文件
	 * @param agentId 招标代理机构id
	 * @param srcFilePath 工作流查看文件绝对路径
	 * @return
	 */
	public static SysDocFile uploadWorkFlowViewFile(Long agentId, String srcFilePath){
		String descFileDir = WORK_FLOW_VIEW_FILE_DIR + separator + getCurDay();
		return uploadFile(agentId, srcFilePath, descFileDir);
	}
	/**
	 * 上传工作流查看文件
	 * @param agentId 招标代理机构id
	 * @param srcFile 工作流查看文件
	 * @return
	 */
	public static SysDocFile uploadWorkFlowViewFile(Long agentId, File srcFile){
		String descFileDir = WORK_FLOW_VIEW_FILE_DIR + separator + getCurDay();
		return uploadFile(agentId, srcFile, descFileDir);
	}
	/**
	 * 上传临时文件
	 * @param agentId 招标代理机构id
	 * @param srcFilePath 临时文件绝对路径
	 * @return
	 */
	public static SysDocFile uploadTempFile(Long agentId, String srcFilePath){
		String descFileDir = TEMP_FILE_DIR + separator + getCurDay();
		return uploadFile(agentId, srcFilePath, descFileDir);
	}
	/**
	 * 上传临时文件
	 * @param agentId 招标代理机构id
	 * @param srcFile 临时文件
	 * @return
	 */
	public static SysDocFile uploadTempFile(Long agentId, File srcFile){
		String descFileDir = TEMP_FILE_DIR + separator + getCurDay();
		return uploadFile(agentId, srcFile, descFileDir);
	}
	/**
	 * 上传永久文件
	 * @param agentId 招标代理机构id
	 * @param srcFilePath 永久文件相对路径
	 * @param moduleName 模块名称（必填）
	 * @return
	 */
	public static SysDocFile uploadForeverFile(Long agentId, String srcFilePath, String moduleName){
		if(StringUtils.isEmpty(moduleName)){
			throw new RuntimeException("模块名称不能为空");
		}
		String descFileDir = FOREVERE_FILE_DIR + separator + moduleName + separator + getCurDay();
		return uploadFile(agentId, srcFilePath, descFileDir);
	}
	/**
	 * 上传永久文件
	 * @param agentId 招标代理机构id
	 * @param srcFile 永久文件
	 * @param moduleName 模块名称（必填）
	 * @return
	 */
	public static SysDocFile uploadForeverFile(Long agentId, File srcFile, String moduleName){
		if(StringUtils.isEmpty(moduleName)){
			throw new RuntimeException("模块名称不能为空");
		}
		String descFileDir = FOREVERE_FILE_DIR + separator + moduleName + separator + getCurDay();
		return uploadFile(agentId, srcFile, descFileDir);
	}
	/**
	 * 上传永久文件
	 * @param agentId 招标代理机构id
	 * @param srcFilePath 永久文件相对路径
	 * @param moduleName 模块名称（必填）
	 * @return 文件id
	 */
	public static String uploadForeverFileReturnUUID(Long agentId, String srcFilePath, String moduleName){
		return uploadForeverFile(agentId, srcFilePath, moduleName).getId();
	}
	/**
	 * 上传永久文件
	 * @param agentId 招标代理机构id
	 * @param srcFile 永久文件
	 * @param moduleName 模块名称（必填）
	 * @return 文件id
	 */
	public static String uploadForeverFileReturnUUID(Long agentId, File srcFile, String moduleName){
		return uploadForeverFile(agentId, srcFile, moduleName).getId();
	}
	/**
	 * 页面上上文文件
	 * @param agentId 招标代理机构id
	 * @param srcFilePath 文件绝对路径
	 * @param moduleName 模块名称（必填）
	 * @return
	 */
	public static SysDocFile uploadPageFile(Long agentId, String srcFilePath, String moduleName){
		if(StringUtils.isEmpty(moduleName)){
			throw new RuntimeException("模块名称不能为空");
		}
		String descFileDir = UPLOAD_FILE_DIR + separator + moduleName + separator + getCurDay();
		return uploadFile(agentId, srcFilePath, descFileDir);
	}
	/**
	 * 页面上上文文件
	 * @param agentId 招标代理机构id
	 * @param srcFile 上传文件
	 * @param moduleName 模块名称（必填）
	 * @return
	 */
	public static SysDocFile uploadPageFile(Long agentId, File srcFile, String moduleName){
		if(StringUtils.isEmpty(moduleName)){
			throw new RuntimeException("模块名称不能为空");
		}
		String descFileDir = UPLOAD_FILE_DIR + separator + moduleName + separator + getCurDay();
		return uploadFile(agentId, srcFile, descFileDir);
	}
	/**
	 * 上传客户端招标文件
	 * @param agentId 招标代理机构id
	 * @param srcFilePath 客户端招标文件绝对路径
	 * @return
	 */
	public static SysDocFile uploadClientBidFile(Long agentId, String srcFilePath){
		String descFileDir = CLIENT_BID_FILE_DIR + separator + getCurDay();
		return uploadFile(agentId, srcFilePath, descFileDir);
	}
	/**
	 * 上传客户端招标文件
	 * @param agentId 招标代理机构id
	 * @param srcFile 客户端招标文件
	 * @return
	 */
	public static SysDocFile uploadClientBidFile(Long agentId, File srcFile){
		String descFileDir = CLIENT_BID_FILE_DIR + separator + getCurDay();
		return uploadFile(agentId, srcFile, descFileDir);
	}
	/**
	 * 上传客户端模版文件
	 * @param agentId 招标代理机构id
	 * @param srcFilePath 客户端模版文件相对路径
	 * @return
	 */
	public static SysDocFile uploadClientTplFile(Long agentId, String srcFilePath){
		String descFileDir = CLIENT_TPL_FILE_DIR + separator + getCurDay();
		return uploadFile(agentId, srcFilePath, descFileDir);
	}
	/**
	 * 上传客户端模版文件
	 * @param agentId 招标代理机构id
	 * @param srcFile 客户端模版文件
	 * @return
	 */
	public static SysDocFile uploadClientTplFile(Long agentId, File srcFile){
		String descFileDir = CLIENT_TPL_FILE_DIR + separator + getCurDay();
		return uploadFile(agentId, srcFile, descFileDir);
	}
	 
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	/**
	 * 下载文件.
	 * 
	 * @param filePathName 文件路径<相对CAConfig.getValue("PATH")的路径>.
	 * @param output 输入流<request输出流>.
	 * @throws Exception.
	 */
	public static void downloadFile(String filePathName,OutputStream output) throws Exception {
		
		downloadFile(filePathName,output,0);
	}
	
	/**
	 * 断点下载文件.
	 * 
	 * @param filePathName 文件路径<相对CAConfig.getValue("PATH")的路径>.
	 * @param output 输入流<request输出流>.
	 * @param offset 开始下载位置.
	 * @throws Exception.
	 */
	public static void downloadFile(String filePathName,OutputStream output,long offset) throws Exception {
		String ftpPriorDownload = AppConfig.getString("Ftp_Prior_Download");
		if("true".equals(ftpPriorDownload)) {
			FtpUtil.downloadFile(filePathName, output,offset);
		} else {
			FileInputStream input = null;
			try{
				String localFilePath = AppConfig.getString("Path") + separator + filePathName;
				File localFile = new File(localFilePath);
				if(localFile.exists()) {
					input = new FileInputStream(localFile);
					IOUtils.copy(input, output);
				}
			}catch(Exception e){
				logger.error(e.getMessage(),e);
				throw e;
			}finally{
				IOUtils.closeQuietly(input);
				IOUtils.closeQuietly(output);
			}
		}
	}
	
	/**
	 * 得到文件信息。
	 * 
	 * @param fileId 文件 id
	 * @return 文件信息
	 */
	public static SysDocFile getSysDocFileById(String fileId) {	
		ISysDocFileService fileSvc = BeanFactory.getBean("ISysDocFileService");
		SysDocFile sysDocFile = fileSvc.getSysDocFileById(fileId);
		return sysDocFile;
	}

	/**
	 * 得到文件名,通过文件id.
	 * 
	 * @param fileId 文件id.
	 * @return.
	 * @throws Exception.
	 */
	public static String getNameById(String fileId) throws Exception {		
		ISysDocFileService fileSvc = BeanFactory.getBean("ISysDocFileService");
		SysDocFile sysDocFile = fileSvc.getSysDocFileById(fileId);
		if(sysDocFile == null) return null;
		return sysDocFile.getFileName();
	}

	/**
	 * 得到文件,通过文件id.
	 * 
	 * @param fileId 文件id.
	 * @return.
	 * @throws Exception.
	 */
	public static File getFileById(String fileId) throws Exception {
		ISysDocFileService fileSvc = BeanFactory.getBean("ISysDocFileService");
		SysDocFile sysDocFile = fileSvc.getSysDocFileById(fileId);
		if(sysDocFile == null) return null;
		
		return getFile(sysDocFile.getFilePath());
	}
	
	
	public static String  getFileUrlById(String fileId) throws Exception {
		ISysDocFileService fileSvc = BeanFactory.getBean("ISysDocFileService");
		SysDocFile sysDocFile = fileSvc.getSysDocFileById(fileId);
		Date  createTime = sysDocFile.getCreateTime();
		String basePath = FileUtil.class.getClassLoader().getResource("").getPath()+"../../ebidding/ImgViewFile/preview/";
		String fileName = sysDocFile.getId() +"."+ FilenameUtils.getExtension(sysDocFile.getFileName());
		String dateDir = DateFormatUtils.format(createTime, "yyyyMMdd");
		String filePath =basePath+dateDir;
		String fullFilePath =filePath+"/"+fileName;
//		String filePath=basePath+sysDocFile.getFilePath();		

		String url="ebidding/ImgViewFile/preview/" + dateDir + "/" + fileName;
		File f = new File(fullFilePath);
		if(!f.exists()){
			// 创建文件
			logger.debug("从FTP文件服务器端获取文件");
			try {
				FtpUtil.downloadFile(sysDocFile.getFilePath(), fullFilePath);
			} catch (Exception e) {
				logger.warn("从FTP下载文件失败:" + sysDocFile.getFilePath());
				throw e;
			}
		}
		
		return url;
	}
	
	public static String getFilePathById(String fileId) throws Exception {
		ISysDocFileService fileSvc = BeanFactory.getBean("ISysDocFileService");
		SysDocFile sysDocFile = fileSvc.getSysDocFileById(fileId);
		if(sysDocFile == null) return null;
		
		return sysDocFile.getFilePath();
	}


	/**
	 * 获取文件
	 * @param filePathName 文件的相对路径
	 * @return
	 * @throws PlatException
	 */
	public static File getFile(String filePathName) throws Exception {	
		File tempFile = new File(filePathName);
		String fileName = tempFile.getPath();
		 
		fileName=fileName.replaceAll("\\\\", separator);
		
		String localFileName = null;
		if(fileName.startsWith(AppConfig.getValue("Location_Default_Dir"))) {
			localFileName = fileName;
			fileName = fileName.substring(AppConfig.getValue("Location_Default_Dir").length());	
			while(fileName.startsWith(File.separator)) {
				fileName = fileName.substring(File.separator.length());
			}
		} else {
			while(fileName.startsWith(File.separator)) {
				fileName = fileName.substring(File.separator.length());
			}
			localFileName = AppConfig.getValue("Location_Default_Dir") + File.separator + fileName;
		}
		
		File localFile = new File(localFileName);	
		if("true".equals(AppConfig.getProperty("Ftp_Prior_Download")) || !(localFile.exists() && localFile.length() > 0)) {
			logger.debug("从FTP文件服务器端获取文件");
			try {
				localFile = FtpUtil.downloadFile(fileName, localFileName);
			} catch (Exception e) {				
				if(localFile.exists() && localFile.length() > 0) {					
					logger.warn("从FTP下载失败，使用本地文件：" + convertCharSet(localFileName, "ISO-8859-1", "GBK"));
				} else {
					logger.info("文件不存在",e);
					throw e;
				}
			}
		}
		return localFile;
	}

	/**
	 * 转换字符串编码
	 * 
	 * @param str 字符串
	 * @param oldCharSet  旧编码
	 * @param newCharSet 新编码
	 * @return
	 */
	public static String convertCharSet(String str, String oldCharSet,
			String newCharSet) {
		if (str == null || str.length() == 0) {
			return str;
		}
		try {
			if (oldCharSet != null)
				return new String(str.getBytes(oldCharSet), newCharSet);
			else
				return new String(str.getBytes(), newCharSet);
		} catch (Exception e) {
			logger.info("转换错误",e);
			return str;
		}
	}
	
	/**
	 * 文件和业务表建立联系.
	 * 
	 * @param fileId 文件id,多个用逗号分开.
	 * @param tableId 业务表id.
	 * @param tableName 业务表名.
	 * @param tableColumn 业务表列.
	 */
	public static void businessRelation(String fileId,Long tableId,String tableName,String tableColumn){
		
		if(StringUtils.isEmpty(fileId)) return;
		String[] fileIds = StringUtils.split(fileId, ",");
		ISysDocFileService fileSvc = BeanFactory.getBean("ISysDocFileService");
		SysDocFile sysDocFile = null;
		for(int i = 0;i < fileIds.length;i++) {
			sysDocFile = new SysDocFile();
			sysDocFile.setTableId(tableId);
			sysDocFile.setTableName(tableName);
			sysDocFile.setTableColumn(tableColumn);
			fileSvc.update(NumberUtils.toLong(fileIds[i]), sysDocFile);
		}		
	}
	
	/**
	 * 删除文件和业务表联系.
	 * 
	 * @param fileId  文件id,多个用逗号分开.
	 */
	public static void delBusinessRelation(String fileId) {
		
		if(StringUtils.isEmpty(fileId)) return;
		String[] fileIds = StringUtils.split(fileId, ",");
		ISysDocFileService fileSvc = BeanFactory.getBean("ISysDocFileService");
		SysDocFile sysDocFile = null;
		for(int i = 0;i < fileIds.length;i++) {
			sysDocFile = new SysDocFile();
			sysDocFile.setTableId(null);
			sysDocFile.setTableName(null);
			sysDocFile.setTableColumn(null);
			sysDocFile.setIsDeleted(Constants.Is_Deleted_Yes);
			sysDocFile.setUpdateTime(new Date());
			sysDocFile.setUpdateUserId(WebUtils.getUserId());
			fileSvc.update(NumberUtils.toLong(fileIds[i]), sysDocFile);
		}	
	}
	
	public static String registerFileId(String filePath, long size) throws Exception {
		 
		String id = getUUID();

		/** 保存数据 */
		SysDocFile sysDocFile = new SysDocFile();
		sysDocFile.setId(id);
		sysDocFile.setFileName(FilenameUtils.getName(filePath));
		sysDocFile.setFilePath(filePath);
		sysDocFile.setFileSize(NumberUtils.toDouble(Long.toString(size)));
		sysDocFile.setFileType(FilenameUtils.getExtension(filePath));
		sysDocFile.setCreateUserId(WebUtils.getUserId());
		sysDocFile.setCreateTime(new Date());
		ISysDocFileService fileSvc = BeanFactory.getBean("ISysDocFileService");
		fileSvc.save(sysDocFile);
		
		return id;
		 
	}
	
	public static String registerFileId(String filePath, long size,String fileName) throws Exception {
		 
		String id = getUUID();

		/** 保存数据 */
		SysDocFile sysDocFile = new SysDocFile();
		sysDocFile.setId(id);
		sysDocFile.setFileName(StringUtils.isEmpty(fileName)?FilenameUtils.getName(filePath):fileName);
		sysDocFile.setFilePath(filePath);
		sysDocFile.setFileSize(NumberUtils.toDouble(Long.toString(size)));
		sysDocFile.setFileType(FilenameUtils.getExtension(filePath));
		sysDocFile.setIsDeleted("0");
		sysDocFile.setIsFtpFile("1");
		sysDocFile.setCreateUserId(WebUtils.getUserId());
		sysDocFile.setCreateTime(new Date());
		ISysDocFileService fileSvc = BeanFactory.getBean("ISysDocFileService");
		fileSvc.save(sysDocFile);
		
		return id;
	}
	
	public static String uploadTenderFile(String fileId) throws Exception {
		ISysDocFileService fileSvc = BeanFactory.getBean("ISysDocFileService");
		SysDocFile sysDocFile = fileSvc.getSysDocFileById(fileId);
		String[] value = CAUtils.tenderFileId(fileId);
		String result = fileId;
		String[] fileInfo = null;
		String fileInfoId = null;
		for(String str:value) {
			if(StringUtils.isEmpty(str)) continue;
			result += "*";
			fileInfo = StringUtils.split(str, "*");
			fileInfoId = registerFileId(fileInfo[0],NumberUtils.toLong(fileInfo[2]),fileInfo[1]);
			result += fileInfoId;
		}
		sysDocFile.setTableName(result);		
		fileSvc.update(sysDocFile);
		return result;
	} 
	
	public static List <SysDocFile>  files(String fileIds) throws Exception{
		if(StringUtils.isEmpty(fileIds)) return null;
		List<SysDocFile> files=new ArrayList<SysDocFile>();
		String[] fIds = StringUtils.split(fileIds, ",");
		for (int i = 0; i < fIds.length; i++) {
			files.add(getSysDocFileById(fIds[i]));
		}
		return files;
	}
}
