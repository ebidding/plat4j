package org.net.plat4j.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;

import org.net.plat4j.sr.core.utils.LogHelper;
import org.net.plat4j.web.model.SysDocFile;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 操作pdf工具类
 * @author Qisehua
 *
 */
public class PDFUtils{
	private static final LogHelper logger = new LogHelper(PDFUtils.class);
	/**
	 * 创建pdf文件
	 * @param filePath
	 * @param fileContent
	 */
	public void createPDF(String filePath, String fileContent){
		Document document = null;
		try {
			document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();
			//添加meta信息
			document.addAuthor("作者");
			document.addCreator("创建者");
			document.addTitle("标题");
			document.addSubject("主题");
			document.addCreationDate();
			document.addKeywords("关键字");
			//添加内容
			Paragraph conpar = new Paragraph("iText Ceate Pdf");
			conpar.setAlignment(Element.ALIGN_CENTER);
			document.add(conpar);
			document.add(Chunk.NEWLINE);
			
			BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font fontCN = new Font(baseFont, 12, Font.NORMAL, BaseColor.BLACK);
			document.add(new Paragraph("欢迎来到iTextPdf世界！", fontCN));
		} catch (Exception e) {
			logger.error(e);
		}finally{
			//关闭文档
			document.close();
		}
	}
	
	/**
	 * @param agentId 招标代理机构id
	 * @param wordFileId ftp上word文件的id
	 * @param pdfFileName 转换后pdf的文件名称，如果为null，则为word文件名称
	 * @return
	 * @throws Exception
	 */
	public static String word2Pdf(Long agentId,String wordFileId, String pdfFileName, String moduleName){
		String fileId = "";
		//请求的服务默认地址
		String postUrl = AppConfig.getString("word2pdf_server_url");
		InputStream data = null;
		if(StringUtils.isEmpty(postUrl)){
			throw new RuntimeException("配置文件中word2pdf_server_url为空！！！");
		}
		try{
			//创建请求的对象
			HttpPost httppost = new HttpPost(postUrl);
			File templeteFile = FileUtil.getFileById(wordFileId);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.addBinaryBody("wordFile", templeteFile, ContentType.DEFAULT_BINARY, templeteFile.getName());
			
			HttpEntity entity = builder.build();
			httppost.setEntity(entity);
			HttpClient httpClient = HttpClients.createDefault();
			HttpResponse response = httpClient.execute(httppost);
			//获取请求的状态值
			int status = response.getStatusLine().getStatusCode();
			//读取返回的流
			data = response.getEntity().getContent();
			BufferedReader rd = new BufferedReader(new InputStreamReader(data, "UTF-8")); 
			StringBuffer stringBuffer = new StringBuffer(); 
			String line; 
			while ((line = rd.readLine()) != null) { 
				stringBuffer.append(line); 
			} 
			rd.close();
			String str = stringBuffer.toString();
			logger.info("请求状态："+status+"，返回的结果内容为："+str);
			
			URL url =new URL(str);
			if(StringUtils.isEmpty(pdfFileName)){
				pdfFileName = templeteFile.getName();
			}
			File outFile = new File(AppConfig.getString("FreeMarker_Tpl_Dir")+"/"+pdfFileName+".pdf");
			FileUtils.copyURLToFile(url, outFile);
			fileId = FileUtil.uploadForeverFile(agentId, outFile, moduleName).getId();
			//返回结果内容
			return fileId;
		}catch(Exception e){
			logger.error(e);
			throw new RuntimeException(e);
		}finally{
			if(data != null){
				try {
					data.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 
	 * @param fileIds
	 * @return
	 * @throws Exception
	 */
	public static String word2PdfByFileIds(Long agentId,String fileIds,String moduleName) {
		String[] fIds = fileIds.split(",");
		String pdfFileIds="";
		for (int i = 0; i < fIds.length; i++) {
			String fileId = fIds[i];
			SysDocFile sysDocFileById = FileUtil.getSysDocFileById(fileId);
			String word2Pdf = word2Pdf(agentId, fileId, getFileNameString(sysDocFileById.getFileName()),moduleName);
			if(i==0){
				pdfFileIds=word2Pdf;
			}else{
				pdfFileIds=pdfFileIds+","+word2Pdf;
			}
		}
		return pdfFileIds;
	}
	private static String getFileNameString(String fileName){
		String str1="";
		if(fileName.indexOf(".")!=-1){
			str1=fileName.substring(0, fileName.indexOf("."));
		}else{
			str1=fileName;
		}
		return str1;
	}
}
