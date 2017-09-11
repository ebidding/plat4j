package org.net.plat4j.common.excelutil;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
/***
 * @author 刘正
 * 辅助生成打印
 */
public class ExportUtil {
	
	public static final short defaultTitleRowHeight = 500;//指定大标题行的默认高度
	public static final short defaultRowHeight = 20;  //指定每一行的默认高度
	public static final float defaultSignRowHeight = 12.00f;  //指定签名行的默认高度
	public static final int defaultPrintWidth = 33600;	  //指定打印区域的总宽度
	
	public static String setCellData(String data,boolean showData){
		if(showData){
			return data;
		}
		return "";
	}
	
	public static String format(DecimalFormat fm,Double num){
		if(num==null)num=new Double(0);
		
		return fm.format(num);
	}
	
	public static String format(DecimalFormat fm,double num){
		
		return fm.format(num);
	}
	
	public static void setCellData(HSSFCell cell,Double data,boolean showData){
		if(showData){
			if(data!=null){
				cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell.setCellValue(data.doubleValue());
			}				
		}else{
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(""));
		}
	}
	
	public static void setCellData(HSSFCell cell,String data,boolean showData){
		if(showData){
			if(data!=null){
				cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell.setCellValue(data);
			}				
		}else{
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(""));
		}
	}	
	
	public static String getResultName(String data,boolean showData){
		if(showData && "1".equals(data)){
			return "合格";
		}else if(showData && "0".equals(data)){
			return "不合格";
		}
		return "";
	}
	
	public static String getResultSign(String data,boolean showData){
		if(showData && "1".equals(data)){
			return "√";
		}else if(showData && "0".equals(data)){
			return "×";
		}else if(showData && ("".equals(data)) || data==null){
			return "--";
		}
		
		return "";
	}
	
	public static Workbook defaultPagePrintSet(Workbook workbook){
		for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
			Sheet sheet = workbook.getSheetAt(numSheet);
			PrintSetup printSetup = sheet.getPrintSetup();//getPrintSetup();  
			//A4纸setPrintArea
			printSetup.setLandscape(false); //打印方向，true:横向，false:纵向
			printSetup.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);  
			printSetup.setFitWidth(defaultTitleRowHeight);
			printSetup.setFitHeight(defaultRowHeight);
			//横向打印
			printSetup.setLandscape(true);  
	        sheet.setMargin(HSSFSheet.BottomMargin, (double)0.3); //页边距（下）
	        sheet.setMargin(HSSFSheet.LeftMargin, (double)0.3); //页边距（左）
	        sheet.setMargin(HSSFSheet.RightMargin, (double)0.3); //页边距（右）
	        sheet.setMargin(HSSFSheet.TopMargin, (double)0.3); //页边距（上）
	        sheet.setHorizontallyCenter(true); //设置打印页面为水平居中
			sheet.setVerticallyCenter(false);   //设置打印页面为垂直居中 
		}
		
		return workbook;

	}
	public static short getWidthMax(short maxWidth,short totalcol){
		if(totalcol<=0){
			return maxWidth;
		}
		
		return (short) (maxWidth / totalcol);
	}
	
	public static short getWidthMax(int maxWidth,int totalcol){
		if(totalcol<=0){
			return (short)maxWidth;
		}
		
		return (short) (maxWidth / totalcol);
	}
	
	public static float getExcelCellAutoHeight(String str, float fontCountInline) {
	        float defaultCount = 0.00f;	       
	        float height = 0;
	        if(str==null)return defaultRowHeight;
	        str = str.replaceAll("\r\n", "\r");
	        str = str.replaceAll("\n\r", "\r");
	        for (int i = 0; i < str.length(); i++) {
	            float ff = getregex(str.substring(i, i + 1));
	            if(ff==defaultRowHeight){	
	            	float heightChar = 0;
	            	if(defaultCount!=0f){
	            		heightChar =((int)(defaultCount / fontCountInline) + 1) * defaultRowHeight;
	            	}	  
	            	if(heightChar <=defaultRowHeight){
	            		height += defaultRowHeight;
	            	}else{
	            		height += heightChar;
	            	}
	            	
	            	defaultCount = 0f;
	            }else{
	            	defaultCount = defaultCount + ff;
	            }
	        }
	        if(defaultCount!=0f){
	        	height +=((int)(defaultCount / fontCountInline) + 1) * defaultRowHeight;
	        }
	        if(height<defaultRowHeight)height = defaultRowHeight;
	        return (int) height;//计算
	}
	
	public static float getExcelCellAutoHeight(String str, float fontCountInline,float rowHeight) {
        float defaultCount = 0.00f;	       
        float height = 0;
        if(str==null)return rowHeight;
        str = str.replaceAll("\r\n", "\r");
        str = str.replaceAll("\n\r", "\r");
        for (int i = 0; i < str.length(); i++) {
            float ff = getregex(str.substring(i, i + 1));
            if(ff==rowHeight){	
            	float heightChar = 0;
            	if(defaultCount!=0f){
            		heightChar =((int)(defaultCount / fontCountInline) + 1) * rowHeight;
            	}	  
            	if(heightChar <=rowHeight){
            		height += rowHeight;
            	}else{
            		height += heightChar;
            	}
            	
            	defaultCount = 0f;
            }else{
            	defaultCount = defaultCount + ff;
            }
        }
        if(defaultCount!=0f){
        	height +=((int)(defaultCount / fontCountInline) + 1) * rowHeight;
        }
        if(height<rowHeight)height = rowHeight;
        return (int) height;//计算
}	
	
    public static float getregex(String charStr) {        
        if(" ".equals(charStr))
        {
            return 0.5f;
        }
        if("\n".equals(charStr))
        {
            return defaultRowHeight;
        }
        if("\r".equals(charStr))
        {
            return defaultRowHeight;
        }
        // 判断是否为字母或字符
        if (Pattern.compile("^[A-Za-z0-9]+$").matcher(charStr).matches()) {
            return 0.5f;
        }
        // 判断是否为全角

        if (Pattern.compile("[\u4e00-\u9fa5]+$").matcher(charStr).matches()) {
            return 1.00f;
        }
        //全角符号 及中文
        if (Pattern.compile("[^x00-xff]").matcher(charStr).matches()) {
            return 0.5f;
        }

        return 0.5f;
    }
	
}
