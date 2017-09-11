package org.net.plat4j.common.excelutil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.net.plat4j.common.utils.AppConfig;
import org.net.plat4j.sr.core.utils.LogHelper;
/**
 * <p>通过jxls操作excel文件。<em>使用xlsx保存的摸版,拷贝出来的sheet格式与原模板一致</em></p>
 * <ul>
 * <li>使用构造方法,传入一个模本文件路径获得{@link Workbook}
 * <li>使用copyNewSheet(String,String,String,String)在摸版中新增一个摸版sheet
 * <li>使用fillData2Excel(...)重载方法为摸版填充数据
 * <li>使用writeToFile(String)将操作后的{@link Workbook}保存到文件中
 * </ul>
 * @author <a href=" mailto:qujx@ebidding.net.cn">曲建秀</a>
 * @version 1.0.1
 * @see Jxls2ExcelUtils
 */
public class JxlsWorkBook {
	protected LogHelper logger = new LogHelper(getClass());
	
	private Workbook workbook;
	/**
	 * 根据摸版文件路径生成对应的workbook
	 * @param templateFilePath 模板文件路径
	 * @throws Exception - 当摸版文件不存在,或者传入的 templateFile不是一个文件时抛出异常
	 */
	public JxlsWorkBook(String templateFilePath)throws Exception{
		java.io.File _file = new java.io.File(templateFilePath);
		if(!_file.exists()) throw new Exception("模板文件不存在!请检查路径是否正确");
		if(!_file.isFile()) throw new Exception("传入的参数不是一个文件");
		java.io.FileInputStream _fis = new java.io.FileInputStream(templateFilePath);
		if(templateFilePath.endsWith(".xlsx")){
			this.workbook = new XSSFWorkbook(_fis);
		}else{
			this.workbook = new HSSFWorkbook(_fis);
		}
		_fis.close();
	}
	/**
	 * 根据摸版文件路径生成对应的workbook
	 * @param templateFile 模板文件
	 * @throws Exception - 当摸版文件不存在,或者传入的 templateFile不是一个文件时抛出异常
	 */
	public JxlsWorkBook(java.io.File templateFile)throws Exception{
		if(!templateFile.exists()) throw new Exception("模板文件不存在!请检查路径是否正确");
		if(!templateFile.isFile()) throw new Exception("传入的参数不是一个文件");
		java.io.FileInputStream _fis = new java.io.FileInputStream(templateFile);
		if(templateFile.getAbsoluteFile().toString().endsWith(".xlsx")){
			this.workbook = new XSSFWorkbook(_fis);
		}else{
			this.workbook = new HSSFWorkbook(_fis);
		}
		_fis.close();
	}
	/**
	 * 根据索引删除sheet
	 * @param index
	 * @return
	 */
	public Workbook removeSheet(int index){
		if(index >= 0 && index < workbook.getNumberOfSheets())
			workbook.removeSheetAt(index);
		return workbook;
	}
	/**
	 * 根据名称删除sheet
	 * @param sheetName
	 * @return
	 */
	public Workbook removeSheet(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		this.removeSheet(index);
		return workbook;
	}
	/**
	 * 根据参数删除sheet也
	 * @param sheetlist 待操作的sheet集合
	 * @param saveOrdel true表示sheetlist传入的集合为保留的sheet; false表示sheetlist传入的集合为要删除的sheet
	 * @return
	 */
	public Workbook removeSheet(List<String> sheetlist,boolean saveOrdel){
		Map<String,Object> map = new java.util.HashMap<String,Object>();
		//将list转化成map,这样处理保证不会出现重复的sheet,从而减少多余的操作
		for(String sheetname : sheetlist){
			map.put(sheetname, sheetname);
		}
		return this.removeSheet(map, saveOrdel);
	}
	/**
	 * 根据参数删除sheet也
	 * @param sheetarray 待操作的sheet集合
	 * @param saveOrdel true表示sheetlist传入的集合为保留的sheet; false表示sheetlist传入的集合为要删除的sheet
	 * @return
	 */
	public Workbook removeSheet(String[] sheetarray,boolean saveOrdel){
		Map<String,Object> map = new java.util.HashMap<String,Object>();
		//将list转化成map,这样处理保证不会出现重复的sheet,从而减少多余的操作
		for(String sheetname : sheetarray){
			map.put(sheetname, sheetname);
		}
		
		return this.removeSheet(map, saveOrdel);
	}
	
	/**
	 * 根据参数删除sheet页
	 * @param sheetmap 待操作的sheet集合
	 * @param saveOrdel true表示sheetlist传入的集合为保留的sheet; false表示sheetlist传入的集合为要删除的sheet
	 * @return
	 */
	public Workbook removeSheet(Map<String,Object> sheetmap,boolean saveOrdel){
		if(!saveOrdel){
			java.util.Iterator<String> it = sheetmap.keySet().iterator();
			while(it.hasNext()){
				String sheetname = it.next();
				this.removeSheet(sheetname);
			}
		}else{
			for (int i = 0; i < workbook.getNumberOfSheets(); ) {
				String sheetname = workbook.getSheetAt(i).getSheetName();
				if(sheetmap.get(sheetname) == null){
					this.removeSheet(i);
				}else{
					i++;
				}
			}
		}
		return workbook;
	}
	
	
	/**
	 * 根据摸版填充数据
	 * @param targetFile 根据摸版生成的数据文件
	 * @param datas Map数据集合
	 */
	public void fillData2Excel(String targetFile,Map<String,Object> datas){
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		dataList.add(datas);
		this.fillData2Excel(targetFile, dataList, "dataList");
	}
	
	/**
	 * 根据摸版填充数据
	 * @param targetFile 根据摸版生成的数据文件
	 * @param datas 数据集合
	 */
	public void fillData2Excel(String targetFile,List<Map<String,Object>> datas){
		this.fillData2Excel(targetFile, datas, "dataList");
	}
	/**
	 * 根据摸版填充数据
	 * @param targetFile 根据摸版生成的数据文件
	 * @param datas 数据集合
	 * @param beanName 模板中对应的bean名称
	 */
	public void fillData2Excel(String targetFile,List<Map<String,Object>> datas,String beanName){
		java.util.List<String> list = new java.util.ArrayList<String>();
		list.add(workbook.getSheetAt(0).getSheetName());
		this.fillData2Excel(targetFile, datas, list, beanName, 0);
	}
	/**
	 * 根据摸版填充数据
	 * @param targetFile 根据摸版生成的数据文件
	 * @param datas 数据集合
	 * @param sheetNames sheet的名称
	 * @param beanName 模板中对应的bean名称
	 * @param index 填充数据时的索引位置
	 */
	public void fillData2Excel(String targetFile,List<Map<String,Object>> datas,List<String> sheetNames,String beanName,int index){
		File templateFile = copyTemplate(targetFile);
		try(InputStream is = new BufferedInputStream(new java.io.FileInputStream(templateFile))){
			XLSTransformer tran = new XLSTransformer();
			workbook = tran.transformMultipleSheetsList(is, datas, sheetNames, beanName, null, index);
		}catch(Exception e){
			logger.error(e);
		}
	}
	public File copyTemplate(String targetFile){
		File _file = new File(targetFile);
		String templateFilePath = AppConfig.getProperty("Location_Default_Dir") + "/workbook/" + _file.getName();
		File templateFile = new File(templateFilePath);
		if(!templateFile.getParentFile().exists()){
			templateFile.getParentFile().mkdirs();
		}
		try(FileOutputStream _fos = new FileOutputStream(templateFile);){
			workbook.write(_fos);
			_fos.flush();
		}catch(Exception e){
			logger.error(e);
			throw new RuntimeException(e);
		}
		return templateFile;
	}
	/**
	 * 根据摸版填充数据
	 * @param targetFile 根据摸版生成的数据文件
	 * @param data 数据集合(键值对的方式存放)
	 */
	public void fillData2SimpleExcel(Map<String,Object> data){
		try{
			net.sf.jxls.transformer.XLSTransformer tran = new net.sf.jxls.transformer.XLSTransformer();
//			java.io.InputStream is = new java.io.BufferedInputStream(new java.io.FileInputStream(targetFile));
//			workbook = tran.transformXLS(is, data);
			tran.transformWorkbook(workbook, data);
//			is.close();
		}catch(Exception e){
			logger.error(e);
		}
	}
	
	/**
	 * 合并单元格
	 * @param sheetName sheet名
	 * @param firstRow 合并起始行
	 * @param lastRow 合并结束行
	 * @param firstCol 合并起始列
	 * @param lastCol 合并结束列
	 * @return 操作后的{@link org.apache.poi.ss.usermodel.Workbook}
	 */
	public Workbook megerCell(String sheetName,int firstRow,int lastRow,int firstCol,int lastCol){
		Sheet sheet = this.workbook.getSheet(sheetName);
		if(sheet != null){
			CellRangeAddress range = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
			sheet.addMergedRegion(range);
		}
		return this.workbook;
	}

	/**
	 * 拷贝sheet
	 * @param srcSheet 源sheet
	 * @param destSheet 目标sheet
	 * @return 操作后的{@link org.apache.poi.ss.usermodel.Workbook}
	 */
	public Workbook copySheet(String srcSheet,String destSheet){
		return this.copyNewSheet(srcSheet, destSheet, "same", "same");
	}
	
	/**
	 * 根据模板已有sheet新增sheet模板。(<em>使用xlsx保存的摸版,拷贝出来的sheet格式与原模板一致</em>)<br/>
	 * 如果srcBean与replaceBean相同则实现拷贝功能
	 * @param srcSheet 源sheet
	 * @param destSheet 目标sheet
	 * @param srcBean 需要替换的源bean名称
	 * @param replaceBean 替换后的bean名称
	 * @return 操作后的{@link org.apache.poi.ss.usermodel.Workbook}
	 */
	public Workbook copyNewSheet(String srcSheet,String destSheet,String srcBean,String replaceBean){
		Sheet srcsheet = workbook.getSheet(srcSheet);
		if(srcsheet != null){
			Sheet destsheet = workbook.createSheet(destSheet);
			Util.copySheets(destsheet,srcsheet);
			if(!srcBean.equals(replaceBean)){
				Iterator<Row> it = destsheet.rowIterator();
				int rownum = 0;
				System.out.println("Sheet Name :" + destsheet.getSheetName());
				while(it.hasNext()){
					Row row =(Row)it.next();
					Iterator<Cell> cells = row.cellIterator();
					System.out.println("Row Index:"+rownum);
					while(cells.hasNext()){
						Cell cell = (Cell)cells.next();
						int cellType = cell.getCellType();
						if(cellType == Cell.CELL_TYPE_STRING){
							String cellValue = cell.getStringCellValue();
							System.out.print(cellValue);
							if(cellValue.indexOf(srcBean) != -1){
								System.out.print("#^#" + cellValue.replaceFirst(srcBean, replaceBean));
								cell.setCellValue(cellValue.replaceFirst(srcBean, replaceBean));
							}
						}else{
							System.out.print(cell.getCellType());
						}
						System.out.print(",");
					}
					rownum++;
					System.out.println();
				}
			}
		}
		
		return this.workbook;
	}

	/**
	 * 根据摸版填充数据(设置打印区域)
	 * @param targetFile 根据摸版生成的数据文件
	 * @param datas 数据集合
	 * @param maxRow 打印区域最大行
	 * @param maxCol 打印区域最大列
	 * @param setColHeight 设置行高
	 * @arg0 arg0 表头超出配置
	 */
	public void fillData2Excel2(String targetFile,List<Map<String,Object>> datas,int maxRow,int maxCol, int setColHeight, int arg0){
		this.fillData2Excel2(targetFile, datas, "dataList",maxRow, maxCol,setColHeight,arg0);
	}
	/**
	 * 根据摸版填充数据(设置打印区域)
	 * @param targetFile 根据摸版生成的数据文件
	 * @param datas 数据集合
	 * @param beanName 模板中对应的bean名称
	 * @param maxRow 打印区域最大行
	 * @param maxCol 打印区域最大列
	 * @param setColHeight 设置行高
	 * @arg0 arg0 表头超出配置
	 */
	public void fillData2Excel2(String targetFile,List<Map<String,Object>> datas,String beanName,int maxRow,int maxCol, int setColHeight, int arg0){
		java.util.List<String> list = new java.util.ArrayList<String>();
		list.add(workbook.getSheetAt(0).getSheetName());
		this.fillData2Excel2(targetFile, datas, list, beanName, 0, maxRow, maxCol, setColHeight,arg0);
	}
	/**
	 * 根据摸版填充数据(设置打印区域)
	 * @param targetFile 根据摸版生成的数据文件
	 * @param datas 数据集合
	 * @param sheetNames sheet的名称
	 * @param beanName 模板中对应的bean名称
	 * @param index 填充数据时的索引位置
	 * @param maxRow 打印区域最大行
	 * @param maxCol 打印区域最大列
	 * @param setColHeight 设置行高
	 * @arg0 arg0 表头超出配置
	 */
	public void fillData2Excel2(String targetFile,List<Map<String,Object>> datas,List<String> sheetNames,String beanName,int index,int maxRow,int maxCol,int setColHeight, int arg0){
		File templateFile = copyTemplate(targetFile);
		try{
			XLSTransformer tran = new XLSTransformer();
			InputStream is = new java.io.BufferedInputStream(new FileInputStream(templateFile));
			workbook = tran.transformMultipleSheetsList(is, datas, sheetNames, beanName, null, index);
			workbook.setPrintArea(0, 0, maxCol, 0, maxRow);
			is.close();
		}catch(Exception e){
			logger.error(e);
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * 为sheet重新命名
	 * @param srcName 源名
	 * @param reName 重命名
	 * @return
	 */
	public Workbook renameSheet(int index,String reName){
		if(index != -1 && reName != null){
			workbook.setSheetName(index, reName);
		}
		return workbook;
	}
	/**
	 * 为sheet重新命名
	 * @param srcName 源名
	 * @param reName 重命名
	 * @return
	 */
	public Workbook renameSheet(String srcName,String reName){
		return this.renameSheet(workbook.getSheetIndex(srcName), reName);
	}
	
	/**
	 * 保存workbook
	 * @param targetFile 保存的文件
	 * @return 保存后的{@link java.io.File}
	 */
	public File writeToFile(String targetFile){
		File _file = new File(targetFile);
		if(!_file.getParentFile().exists()){
			_file.getParentFile().mkdirs();
		}
		try(FileOutputStream _fos = new FileOutputStream(_file);){
			workbook.write(_fos);
			_fos.flush();
		}catch(Exception e){
			logger.error(e);
			throw new RuntimeException(e);
		}
		return _file;
	}
	public Workbook getWorkbook(){
		return this.workbook;
	}
	public static void main(String[] args) {
		try{
		JxlsWorkBook jwb = new JxlsWorkBook("E:\\uploadDir\\bidEvaFile\\tableTemplate\\package_id_3.xls");
		Workbook workbook = jwb.getWorkbook();
		workbook.setSheetOrder("1.符合性检查", 0);
		workbook.setSheetOrder("11.开标记录", 10);
		workbook.setSheetOrder("3.商务一般指标", 2);
		workbook.setSheetOrder("4.技术重要指标", 3);
		workbook.setSheetOrder("10.授标建议", 9);
		workbook.setSheetOrder("9.评委会意见",8);
		workbook.setSheetOrder("2.1.商务重要指标",1);
		workbook.setSheetOrder("8.价格评分",7);
		jwb.writeToFile("D:\\package_id" + new java.util.Date().getTime() +".xls");
		}catch(Exception e){
			
		}
	}
}
