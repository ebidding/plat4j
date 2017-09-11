package org.net.plat4j.common.excelutil;
/***
 * @author 刘正
 */
public class JxlsBean {
	private String sheetName;
	private int firstRow;
	private int lastRow;
	private int firstCell;
	private int lastCell;
	
	public JxlsBean(String sheetName, int firstRow, int lastRow, int firstCell,int lastCell) {
		this.sheetName = sheetName;
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.firstCell = firstCell;
		this.lastCell = lastCell;
	}

	
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public int getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}
	public int getLastRow() {
		return lastRow;
	}
	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}
	public int getFirstCell() {
		return firstCell;
	}
	public void setFirstCell(int firstCell) {
		this.firstCell = firstCell;
	}
	public int getLastCell() {
		return lastCell;
	}
	public void setLastCell(int lastCell) {
		this.lastCell = lastCell;
	}
	
	
}
