/**
 * 
 */
package org.net.plat4j.common.material.model;

/**
 * @author lhb
 * @date 2016年7月11日  上午10:49:26
 * @version 1.0
 * 
 */
public class SheetDataInfo {
	//列code
	private String colCode;
	//表头名称
	private String cellName;
	//行数
	private String lineNum;
	
	public String getColCode() {
		return colCode;
	}
	public void setColCode(String colCode) {
		this.colCode = colCode;
	}
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public String getLineNum() {
		return lineNum;
	}
	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
	}
}
