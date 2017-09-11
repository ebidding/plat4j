package org.net.plat4j.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import org.net.plat4j.sr.core.utils.LogHelper;

public class FtpUtil {
	private static String Ftp_Host = AppConfig.getValue("Ftp_Host");
	private static String Ftp_Username = AppConfig.getValue("Ftp_Username");
	private static String FTP_Password = AppConfig.getValue("FTP_Password");
	private static String Ftp_Defart_Dir = AppConfig.getValue("Ftp_Defart_Dir"); 
	private static int Ftp_Port = AppConfig.getInt("Ftp_Port");

	protected static LogHelper logger = new LogHelper(FtpUtil.class);

	public static FTPClient getFtpClient() throws Exception {
		
		try {
			FTPClient ftpClient =new FTPClient();	        
			ftpClient.setDefaultTimeout(10 * 1000);
			ftpClient.setConnectTimeout(10 * 1000);
			ftpClient.setDataTimeout(30 * 1000);
			ftpClient.setDefaultPort(Ftp_Port);
			ftpClient.connect(Ftp_Host);

	        int reply = ftpClient.getReplyCode();
	        if (!FTPReply.isPositiveCompletion(reply)) {
	            disconnect(ftpClient);
	            throw new IOException("Can't connect to server '" + Ftp_Port + "'");
	        }
	        
	        // Login.
	        if (!ftpClient.login(Ftp_Username, FTP_Password)) {
	            disconnect(ftpClient);
	            throw new IOException("Can't login to server '" + Ftp_Port + "'");
	        }

			logger.info("FTP 已连接!");
			return ftpClient;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	public static void disconnect(FTPClient ftpClient) {	
		try	{
			if(ftpClient != null && ftpClient.isConnected()) {
				ftpClient.logout();
				ftpClient.disconnect();
				ftpClient = null;
				logger.info("FTP 断开连接!");
			}
		}
		catch(Exception e) {
			logger.error("FTP 断开连接错误!",e);
		}
	}

	public static void uploadFile(String desFileFullName, File file) throws Exception {
		try {
			uploadFile(desFileFullName, new FileInputStream(file));
		} catch (Exception e) {
			logger.error(e);
			 throw new Exception(e);
		}
	}

	public static void uploadFile(File srcFile) throws Exception {
		
		String desFilePath = srcFile.getAbsolutePath();
		uploadFile(desFilePath, new FileInputStream(srcFile));
	}

	public static void  uploadFile(String desFilePath, InputStream in) throws Exception {
		
		File file = new File(desFilePath);				
		String desFileDir = file.getParent() != null ? file.getParent() : ".";
		String desFileName = file.getName();
		desFileName = new String(desFileName.getBytes("gbk"), "ISO-8859-1");
		desFileDir = new String(desFileDir.getBytes("gbk"), "ISO-8859-1");
		
		uploadFile(desFileDir, desFileName, in);
	}

	public static void uploadFile(String desFileDir, String desFileName, File file){
		try{
			uploadFile(desFileDir, desFileName, new FileInputStream(file));
		}catch(Exception e){
			logger.error(e);
			throw new RuntimeException(e);
		}
	}
	public static void uploadFile(String desFileDir, String desFileName, InputStream fileIn) throws Exception {			
		logger.info("FTP上传：存储目录 = " + desFileDir + ", 文件名=" +  convertCharSet(desFileName));	
		FTPClient ftpClient = null;
		try {
			ftpClient = getFtpClient(); 
			desFileDir = fixFileName(desFileDir);
			boolean changeDirOk = ftpClient.changeWorkingDirectory(Ftp_Defart_Dir);
			if(desFileDir != null && desFileDir.length() > 0 && !".".equals(desFileDir)) {
				changeDirOk = ftpClient.changeWorkingDirectory(desFileDir);
				if(!changeDirOk) {
					makeDirs(ftpClient,desFileDir);
				}
			}
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // Use passive mode to pass firewalls.
//			ftpClient.enterLocalPassiveMode();
			boolean uploadOk = ftpClient.storeFile(desFileName, fileIn);
			logger.info("上传成功!");
			if(!uploadOk)throw new Exception("上传 " + desFileDir + FileUtil.separator +  convertCharSet(desFileName) + " 失败!");	
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		} finally { 
			IOUtils.closeQuietly(fileIn);
			disconnect(ftpClient);			 
		}		
	}

	public static File  downloadFile(String fileFullName, String saveFileName) throws Exception {
		
		File file = new File(saveFileName);	
		try {
			if(!file.exists()) {
				File parentFile = file.getParentFile();
				if(!parentFile.exists()) {
					parentFile.mkdirs();
				}
			}			
			downloadFile(fileFullName, new FileOutputStream(file));
			return file;
		} catch (Exception e) {
			logger.error(e);
			try {
				if(file.exists() && file.length() == 0) {
					file.delete();
				}
			} catch (Exception e1) {				
			}
	        throw new Exception(e);
	    } 		
	}

	public static void  downloadFile(String filePathName, OutputStream output) throws Exception {
		downloadFile(filePathName,output,0);
	}
	
	public static void  downloadFile(String filePathName, OutputStream output,long offset) throws Exception {

		filePathName = new String(filePathName.getBytes("gbk"), "ISO-8859-1"); 
		FTPClient ftpClient = null;
		logger.info("FTP下载：文件全名 = " + convertCharSet(filePathName));
		try {
			ftpClient = getFtpClient();

            // Use passive mode to pass firewalls.
//			ftpClient.enterLocalPassiveMode();
            
			filePathName = fixFileName(filePathName);
			ftpClient.changeWorkingDirectory(Ftp_Defart_Dir);
			ftpClient.setFileType(org.apache.commons.net.ftp.FTPClient.BINARY_FILE_TYPE);
			if(offset > 0)
			ftpClient.setRestartOffset(offset);
			boolean downloadOk = ftpClient.retrieveFile(filePathName, output);	
			logger.info("下载成功!");
			if(!downloadOk)throw new Exception("下载 " + convertCharSet(filePathName) + " 失败!");	
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		} finally {
			IOUtils.closeQuietly(output);		
			disconnect(ftpClient);		
		}			
	}

	public static void makeDirs(FTPClient ftpClient,String dirName) throws Exception {	
		
		File dir = new File(dirName);
		String currentDirName = dir.getName();
		if(dir.getParent() != null) {				
			makeDirs(ftpClient,dir.getParent());
		}
		makeDir(ftpClient,currentDirName);
	}

	public static void makeDir(FTPClient ftpClient,String dirName) throws Exception {
		
		try {
			boolean changeDirOk = ftpClient.changeWorkingDirectory(dirName);
			if(!changeDirOk) {
				ftpClient.makeDirectory(dirName);					
				changeDirOk = ftpClient.changeWorkingDirectory(dirName);
			}
			if(!changeDirOk)throw new Exception("创建目录" + dirName + "失败！");
		} catch (Exception e) {
			logger.error(e);
			throw e;
		} 
	}

	/**
	 * 修正不规范的文件名:不能从根目录开头,应以/为目录分隔符.
	 * @param fileName
	 * @return
	 */
	private static String fixFileName(String fileName) {
		
		if(fileName != null) {
			File file = new File(fileName);	
			fileName = file.getPath();
			fileName = StringUtils.replace(fileName, "\\", FileUtil.separator);	
			File file2 = new File(AppConfig.getValue("Location_Default_Dir"));	
			String fileName2 = file2.getPath();
			fileName2 = StringUtils.replace(fileName2, "\\", FileUtil.separator);	
			fileName = fileName.replaceFirst(fileName2, ""); 
			
			while(fileName.startsWith(FileUtil.separator)) {
				fileName = StringUtils.substring(fileName, 1);
			}
		}
		return fileName;
	}

	public static String convertCharSet(String str) {
		
		if (str == null || str.length() == 0) {
			return str;
		}
		
		try {
			return new String(str.getBytes("ISO-8859-1"), "GBK");
		} catch (Exception e) {
			logger.error("转换错误!", e);
			return str;
		}
	}
	
		/**
	 * 删除目录下所有文件
	 * @param remotePath
	 * @throws Exception
	 */
	public static void deleteFtpDirectory(String remotePath) throws Exception{
		FTPClient ftpClient = getFtpClient();
		ftpClient.changeWorkingDirectory(remotePath);
		FTPFile[] files = ftpClient.listFiles();
		for (int i = 0; i < files.length; i++) {
			ftpClient.deleteFile(remotePath+FileUtil.separator+files[i].getName());
		}
	}
	/**
	 * 处理特殊的字符串 用"_"代替"\/:*?<>|"
	 * @param fileName
	 * @return
	 */
	public static final String filteName(String fileName) {  
        return fileName.replaceAll("[?*<>:|/\\\\]", "_").trim();  
    }  
}
