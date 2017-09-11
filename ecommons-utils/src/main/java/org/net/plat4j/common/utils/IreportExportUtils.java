package org.net.plat4j.common.utils;

import java.io.File;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.net.plat4j.sr.core.base.BaseException;
import org.net.plat4j.web.model.SysDocFile;

public class IreportExportUtils {


	/**
	 * 
	 * @param reportFilePath 
	 * @param reportName
	 * @param mapList 对应的数据转DataSource用
	 * @param parameters map 用于传递报表时使用的参数
	 * @param newReportName 报表的重新命名
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String createPDFByXmlUUid(String reportFilePath,String reportName, List mapList,Map parameters,String newReportName)  {  
		JasperReport jasperReport;  
		JasperPrint jasperPrint;  

		String pathTemp=AppConfig.getValue("Location_Default_Dir")+"/tempFile/reportPdfFile/";
		File dir = new File(pathTemp);  
		if(!dir.exists()){
			dir.mkdirs();
		}
		String newFilePath= AppConfig.getValue("Location_Default_Dir")+"/tempFile/reportPdfFile/"+newReportName+".pdf";
		try {  
			File file = new File(reportFilePath+"/"+reportName+ ".jasper");  
			//如果jasper文件不存在，就调用jrxml文件编译生成  
			//JasperCompileManager.compileReportToFile(String sourceFileName, String destFileName)  
			if (!file.exists()) {  
				JasperCompileManager.compileReportToFile(reportFilePath + "/"+ reportName + ".jrxml",reportFilePath + "/"+ reportName + ".jasper");  
			}  

			jasperReport = (JasperReport) JRLoader.loadObject(file);  

			JRDataSource dataSource  = new JRBeanCollectionDataSource(mapList);  
			//此处为关键，将对象列表设为数据源              parameters 为传递过来的参数
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);  

			try {                     
				JasperExportManager.exportReportToPdfFile(jasperPrint,newFilePath);  
			} catch (Exception e) {  
				throw new BaseException("报表异常 ："+e.getMessage()); 
			}  
			SysDocFile uploadTempFile = FileUtil.uploadTempFile(WebUtils.getUserAgentId(), newFilePath);
			return uploadTempFile.getId();
		}catch(Exception e){  
			throw new BaseException("生成PDF异常 ："+e.getMessage());  
		}  

	} 

	/**
	 * 
	 * @param reportFilePath 
	 * @param reportName
	 * @param mapList 对应的数据转DataSource用
	 * @param parameters map 用于传递报表是使用的参数
	 * @return
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	public static String createWordByXmlUUid(String reportFilePath,String reportName, List mapList,Map parameters,String newReportName){
		JasperReport jasperReport;  
		JasperPrint jasperPrint;    
		String pathTemp=AppConfig.getValue("Location_Default_Dir")+"/tempFile/reportPdfFile/";
		File dir = new File(pathTemp);  
		if(!dir.exists()){
			dir.mkdirs();
		}
		String newFilePath= AppConfig.getValue("Location_Default_Dir")+"/tempFile/reportPdfFile/"+newReportName+".doc";
		try {
			File file = new File(reportFilePath+"/"+reportName+ ".jasper");  
			if (!file.exists()) {  
				JasperCompileManager.compileReportToFile(reportFilePath + "/"+ reportName + ".jrxml",reportFilePath + "/"+ reportName + ".jasper");  
			}  

			jasperReport = (JasperReport) JRLoader.loadObject(file);  

			JRDataSource dataSource  = new JRBeanCollectionDataSource(mapList);  
			//此处为关键，将对象列表设为数据源              parameters 为传递过来的参数
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

			JRDocxExporter exporter=new JRDocxExporter();  
			//设置输入项  
			ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);  
			exporter.setExporterInput(exporterInput);  

			//设置输出项  
			OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(newFilePath);  
			exporter.setExporterOutput(exporterOutput);  
			exporter.exportReport(); 
			SysDocFile uploadTempFile = FileUtil.uploadTempFile(WebUtils.getUserAgentId(), newFilePath);
			return uploadTempFile.getId();

		} catch (JRException e) {
			throw new BaseException("生成Word异常 ："+e.getMessage());  
		}  
	}
}
