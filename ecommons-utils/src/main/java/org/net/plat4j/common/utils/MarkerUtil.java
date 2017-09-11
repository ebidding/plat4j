package org.net.plat4j.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import org.net.plat4j.sr.core.utils.LogHelper;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class MarkerUtil {

	protected static LogHelper logger = new LogHelper(MarkerUtil.class);
	
	public static File markerHTML(Object rootMap,File outFile,String tplFile) throws Exception {
		Configuration cfg = new Configuration(); 

		FileTemplateLoader loader;
		try {

			String FreeMarker_Tpl_Dir = AppConfig.getString("FreeMarker_Tpl_Dir");

			String tempFileName = replaceTemplateXml(FreeMarker_Tpl_Dir,tplFile);

			File tempDir = new File(FreeMarker_Tpl_Dir);

			OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(outFile),"utf-8");

			loader = new FileTemplateLoader(tempDir);
			cfg.setTemplateLoader(loader);  
			cfg.setClassicCompatible(true);
			cfg.setDefaultEncoding("utf-8");
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			/* 指定模版路径 */
			Template tpl = cfg.getTemplate(tempFileName);    
			tpl.setEncoding("utf-8");

			PrintWriter pw = new PrintWriter(output);   
			tpl.process(rootMap, pw);   
			pw.close();   
			return outFile;
		} catch (Exception e) {
			throw new Exception(e);
		}	
	}
	/**
	 * 根据模板的地址 读取模板 将模板中的特殊字符做替换
	 * @param templatePath
	 */
	public static String replaceTemplateXml(String tpl_Dir,String templateFileName){

		//File file = new File(tpl_Dir + FileUtil.separator + templateFileName);  
		String filePath =  tpl_Dir + "//" + templateFileName;
		StringBuffer str = new StringBuffer(templateFileName);
		String insertNew = "_NEW";//生成的新的模板文件名后缀
		str.insert(str.lastIndexOf("."),insertNew);
		String newFileName = str.toString();
		String newFilePath =  tpl_Dir + "//" + newFileName;
		//File newFile = new File(tpl_Dir + FileUtil.separator + "new_" + templateFileName);  
		try {
			//FileInputStream fis = new FileInputStream(filePath);
			//InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));  
			BufferedWriter writer  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFilePath),"UTF-8"));  
			String line = reader.readLine();  
			while(line!=null){
				line = replaceXmlKuoHaoToGan(line);
				writer.write(line);
				line = reader.readLine();  
			}  
			writer.flush();  
			reader.close();  
			writer.close();  
		} catch (FileNotFoundException e) {  
			logger.error(e);
		} catch (IOException e) {  
			logger.error(e);
		}  
		return newFileName;
	}
	/**
	 * 将${aaaa(bbb)} -- >${aaaa_bbb}
	 */
	public static String replaceXmlKuoHaoToGan(String content){
		Pattern pattern = Pattern.compile("(\\$\\{.*?\\(.*?\\).*?\\})");
		Matcher matcher = pattern.matcher(content);
		while(matcher.find()){
			String groupName = matcher.group(1);
			String newName = StringUtils.replace(groupName, "(", "_").replace(")", "");
			logger.info(groupName);
			logger.info(newName);
			content = content.replace(groupName, newName);
		}
		return content;
	}
}
