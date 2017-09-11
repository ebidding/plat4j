package org.net.plat4j.common.excelutil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
/***
 * @author 刘正
 */
public class CellStyleUtil {
	
	//如果想让sheet自动使用   workbook.getSheetAt(0).autoSizeColumn();
	//如果想让sheet自动换行  style.setWrapText(true);
	
	public static HSSFCellStyle createNormalStyle(HSSFWorkbook workbook){
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		return style;
	}
	
	public static HSSFCellStyle createLeftStyle(HSSFWorkbook workbook){
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setWrapText(true);	
		return style;
	}	
	
	public static HSSFCellStyle createLeftBorderStyle(HSSFWorkbook workbook){
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setWrapText(true);	
		style.setBorderBottom((short)1);
		style.setBorderLeft((short)1);
		style.setBorderRight((short)1);
		style.setBorderTop((short)1);
		return style;
	}
	
	public static HSSFCellStyle createNormalBorderStyle(HSSFWorkbook workbook){
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//style.setBorderBottom((short)1);  
		//style.setWrapText(true);	
		style.setBorderBottom((short)1);
		style.setBorderLeft((short)1);
		style.setBorderRight((short)1);
		style.setBorderTop((short)1);
		style.setWrapText(true);     		
		return style;
		 
	}
	//评标办法条款导出Excel样式
	public static HSSFCellStyle createEvaItemExcelStyle(HSSFWorkbook workbook){
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		//style.setBorderBottom((short)1);  
		//style.setWrapText(true);	
		//style.setBorderBottom((short)1);
		//style.setBorderLeft((short)1);
		//style.setBorderRight((short)1);
		//style.setBorderTop((short)1);
		style.setWrapText(true);     
		return style;
	}
	
	/**
	 * 刘力攻  2013/07/19
	 * @param workbook 传入的excel对象
	 * @return 日期转换后样式
	 */
	public static HSSFCellStyle createDateFormat(HSSFWorkbook workbook){
		HSSFCellStyle styleDate = workbook.createCellStyle();
		HSSFDataFormat format= workbook.createDataFormat();//为该文本创建日期对象
		styleDate.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		styleDate.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		styleDate.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		styleDate.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		styleDate.setWrapText(true);//设置自动换行
		styleDate.setDataFormat(format.getFormat("yyyy年M月d日 HH时:mm分"));
		return styleDate;
	}
	
	/**
	 * 刘力攻  2013/08/05
	 * 设置红色字体
	 * @param workbook 
	 * @return
	 */
	public static HSSFCellStyle createColor(HSSFWorkbook workbook){
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.RED.index);
		return style;
	}
	
	/**
	 * 刘力攻  2013/08/13
	 * 头部样式， 边框+居中+字体样式
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle createTopBorder(HSSFWorkbook workbook){
		HSSFCellStyle setBorder = workbook.createCellStyle();
		setBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		setBorder.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		setBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		setBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		setBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		setBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		HSSFFont font = workbook.createFont();//创建字体样式
		font.setBoldweight((short)4);//加粗
		font.setFontName("新宋体");
		font.setFontHeightInPoints((short) 10);//设置字体大小
		setBorder.setFont(font);//字体样式添加到样式
		setBorder.setWrapText(true);//设置自动换行
		return setBorder;
	}
	
	/**
	 * 刘力攻  2013/08/13
	 * body样式， 边框
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle createBodyBorder(HSSFWorkbook workbook){
		HSSFCellStyle setBorder = workbook.createCellStyle();
//		setBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		setBorder.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		setBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		setBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		setBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		setBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		setBorder.setWrapText(true);//设置自动换行
		return setBorder;
	}
}
