package org.net.plat4j.common.wordUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import org.net.plat4j.common.utils.AppConfig;
import org.net.plat4j.common.utils.FileUtil;
import org.net.plat4j.common.utils.TemplateFileUtil;
import org.net.plat4j.sr.core.utils.LogHelper;

public class WordUtils {
	
	private static LogHelper logger = new LogHelper(WordUtils.class);
	private static HttpClient httpClient = new DefaultHttpClient();
	/**
	 * 綦洋
	 * 2013 10 11 号研究出来的
	 * serverUrl 请求服务的地址
	 * templeteFile 模板文件
	 * paramList 参数集
	 */
	public static ResponseModel postFileRequest(String serverUrl,File templateFile,Map<String, List> paramList)throws Exception{
		
        ResponseModel result = new ResponseModel();
		//请求的服务默认地址
		String url = AppConfig.getString("exportWordServerUrl");
		if(StringUtils.isEmpty(url)){
			throw new RuntimeException("读取配置项 exportWordServerUrl 错误，请联系管理员");
		}
		//创建请求的对象
		HttpPost httppost = new HttpPost(StringUtils.isEmpty(serverUrl)?url:serverUrl);
		
		//将对象参数转换成json格式数据
		String jsonParam = JSONObject.fromObject(paramList).toString();
		
		jsonParam = jsonParam.replaceAll("\"key\":", "\"Key\":");
		jsonParam = jsonParam.replaceAll("\"value\":", "\"Value\":");
		
		//封装请求的参数和文件流
		logger.info("转码前："+jsonParam);
		jsonParam = URLEncoder.encode(jsonParam, "UTF-8");
		StringBody sb = new StringBody(jsonParam, ContentType.MULTIPART_FORM_DATA);
		FileBody fb = new FileBody(templateFile,ContentType.DEFAULT_BINARY);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addPart("fileName",fb);
		builder.addPart("varValue",sb);
		logger.info("发送请求的Json参数值为："+jsonParam);
		
		HttpEntity entity = builder.build();
		httppost.setEntity(entity);
		HttpResponse response = httpClient.execute(httppost);
		
		//获取请求的状态值
		int status = response.getStatusLine().getStatusCode();
		
		//读取返回的流
		try { 
		    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"utf-8")); 
		    StringBuffer stringBuffer = new StringBuffer(); 
		    String line; 
		    while ((line = rd.readLine()) != null) { 
		        stringBuffer .append(line); 
		    } 
		    rd.close(); 
		    logger.info(">>>>>>>" + "请求状态：" + status+ "返回的结果内容为：" + stringBuffer);
		    result.setStatus(status);
		    result.setResponseContent(stringBuffer.toString());
		} catch (Exception e) { 
			result.setStatus(status);
			result.setResponseContent("文件生成失败，请联系管理员！");
		    throw new RuntimeException("error",e);
		}
		//返回结果内容
		return result;
	}
	
	/**
	 * 綦洋
	 * 2013 10 11 号研究出来的
	 * templeteFileId 模板文件在sys_doc_file 的id
	 * paramList 参数集
	 * ftpPath 将文件上传到ftp上的哪个路径下 如 uploadDir/bpbReport
	 * 传递ftpPath = bpbReport即可 前提是ftp上有这个目录
	 * 返回值为 fileId
	 */
	public static String getWordFileIdByTempleteFileId(Long agentId, String templeteFileId,Map<String, List> paramList,String newFileName,String moduleName){
		
		String fileId = "";
		
		try {
			File templeteFile = FileUtil.getFileById(templeteFileId);
			ResponseModel result = WordUtils.postFileRequest(null, templeteFile, paramList);

			//文件的读写
			URL url =new URL(result.getResponseContent());
			File outFile = new File(AppConfig.getString("FreeMarker_Tpl_Dir")+"/"+newFileName+".doc");
			if(!outFile.getParentFile().exists()){
				outFile.getParentFile().mkdirs();
			}
			FileUtils.copyURLToFile(url, outFile);
			fileId = FileUtil.uploadForeverFile(agentId, outFile, moduleName).getId();
		} catch (Exception e) {
			fileId = templeteFileId;
			logger.error(e);
		}
		return fileId;
	}
	
	/**
	 * 綦洋
	 * 2013 10 11 号研究出来的
	 * templeteFileUrl 模板文件ftp上的路径：如/uploadDir/oasDtpmain/oasDtpl.xls
	 * 传入oasDtpmain+FileUtil.separator+oasDtpl.xls即可
	 * paramList 参数集
	 * ftpPath 将文件上传到ftp上的哪个路径下 如 uploadDir/bpbReport
	 * 传递ftpPath = bpbReport即可 前提是ftp上有这个目录
	 * 返回值为 fileId
	 * @throws Exception 
	 */
	public static String getWordFileIdByTempleteFileUrl(Long agentId, HttpServletRequest request,String templateCode,Map<String, List> paramList,String newFileName,String moduleName) throws Exception{
		
		String fileId = null;
		File templeteFile = TemplateFileUtil.getTemplateFile(agentId, request, templateCode);
		ResponseModel result = WordUtils.postFileRequest(null, templeteFile, paramList);
		//文件的读写
		URL url =new URL(result.getResponseContent());
		
		File outFile = new File(AppConfig.getString("FreeMarker_Tpl_Dir")+FileUtil.separator+"newWordFile"+FileUtil.separator+newFileName+".doc");
		if(!outFile.getParentFile().exists()){
			outFile.getParentFile().mkdirs();
		}
		FileUtils.copyURLToFile(url, outFile);
		fileId = FileUtil.uploadForeverFile(agentId,outFile, moduleName).getId();
		return fileId;
	}
}
