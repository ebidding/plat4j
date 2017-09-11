package org.net.plat4j.common.excelImportUtils;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.directory.NoSuchAttributeException;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.net.plat4j.common.utils.FileUtil;
import org.net.plat4j.sr.core.utils.LogHelper;
/**
 * <p>通过Excel导入数据，返回List<E>。<em>实体中字段必须与Excel中的列一一对应！</em></p>
 * <ul>	getExcelData方法中：
 * <li>		fileId : 为文件的Id;
 * <li>		clazz : 为接受数据的实体Class
 * <li>		startRowNum:Excel中数据开始行
 * </ul>
 * @author xiawenyu
 *
 */
public class ExcelImportUtils {
	protected LogHelper logger = new LogHelper(getClass());
	private Workbook workbook;
	/**
	 * <ul>	
	 * <li>		fileId : 为文件的Id;
	 * <li>		clazz : 为接受数据的实体Class
	 * <li>		startRowNum:Excel中数据开始行
	 * </ul>
	 */
	public <T> List<T> getExcelData(String fileId,Class<T> clazz,int startRowNum) throws Exception{
		List<T> dateList = null;
		try {
			dateList = new ArrayList<T>();
			getWorkBook(fileId);
			Field[] fields = clazz.getDeclaredFields();
			int colNum = fields.length;
			logger.info("实体中有"+colNum+"个字段！");
			Sheet sheet = (Sheet) workbook.getSheetAt(0);
			int stratNum =  startRowNum -1;
			int rowNum = sheet.getLastRowNum();
			logger.info("Excel中有"+(colNum-stratNum+1)+"条数据！");
			if(rowNum < stratNum) throw new RuntimeException("Excel中数据请从第"+startRowNum+"行开始！");
			for(int i = stratNum ; i <= rowNum ; i++ ){
				T t = clazz.newInstance();
				Row row = sheet.getRow(i);
				for(int j = 0 ; row!=null&&j < colNum ; j++ ){
					try {
						fields[j].setAccessible(true);
						Cell cell = row.getCell(j);
						String cellVal = getCellVal(cell);
						this.setVaule(t,fields[j],cellVal);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.error(e);
						throw new RuntimeException("Excel中第"+(i+1)+"行，第"+(j+1)+"列数据，与实体中第"+(j+1)+"个字段类型不符合，请检查！");
					}
				}
				dateList.add(t);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new Exception(e);
		}
		
		
		return dateList;
	}
	
	private <T> void  setVaule(T t, Field field , String cellVal) throws Exception{
		Class<?> type = field.getType();
		Object value = null ;
		if(type.equals(int.class)){
			value = Integer.parseInt(cellVal);
		}else if(type.equals(String.class)){
			value = cellVal;
		}else if(type.equals(Double.class)){
			value = Double.valueOf(cellVal);
		}else if(type.equals(Long.class)){
			value = Long.valueOf(cellVal);
		}else if(type.equals(long.class)){
			value = Long.parseLong(cellVal);
		}else if (type.equals(Float.class)){
			value = Float.valueOf(cellVal);
		}else if (type.equals(Date.class)){
			value = DateUtils.parseDate(cellVal, new String[] { "yyyy-MM-dd","yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss" });
		}else if (type.equals(Integer.class)){
			value = Integer.valueOf(cellVal);
		}else if(type.equals(BigDecimal.class)){
			value = new BigDecimal(cellVal);
		}else {
			throw new NoSuchAttributeException("Unsupported type:" + type);
		}
		field.set(t, value);
	}

	private String getCellVal(Cell cell) throws Exception {
		// TODO Auto-generated method stub
		if (cell==null) return null;
		String cellVal = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC :
			if(HSSFDateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				cellVal = dt.format(cell.getDateCellValue()) ;
			}else{
				cellVal = String.valueOf(cell.getNumericCellValue()); 
			}
			
			break;
		case Cell.CELL_TYPE_STRING:
			cellVal = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			cellVal = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA: // 公式类型
			break;
		case Cell.CELL_TYPE_ERROR:
			break;
		case Cell.CELL_TYPE_BLANK:
			break;
		}
		if("".equals(cellVal.trim())) return null ;
		return cellVal.trim();
	}


	private void getWorkBook(String fileId) throws Exception{
		File file = null;
		try {
			file = FileUtil.getFileById(fileId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new RuntimeException(e);
		}
		if(!file.exists()) throw new RuntimeException("导入文件不存在,请核对！");
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			if(file.getAbsoluteFile().toString().endsWith(".xlsx")){
				this.workbook = new XSSFWorkbook(in);
			}else{
				this.workbook = new HSSFWorkbook(in);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			logger.error(e1);
			throw new RuntimeException(e1);
		} finally{
			in.close();
		}
		
	}
}
