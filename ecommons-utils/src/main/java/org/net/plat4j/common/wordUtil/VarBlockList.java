package org.net.plat4j.common.wordUtil;
public class VarBlockList {
	 /// <summary>
    /// 是否合并.在VarValue的值只有1个时有效
    /// </summary>
    private boolean haveMerge = false;

    /// <summary>
    /// 合并的列数
    /// </summary>
    private int mergeColumn = 0;

    /// <summary>
    /// 值,以数组表示.只有1个值时可以设置是否合并
    /// </summary>
    private String[] varValue;

	public boolean isHaveMerge() {
		return haveMerge;
	}

	public void setHaveMerge(boolean haveMerge) {
		this.haveMerge = haveMerge;
	}

	public int getMergeColumn() {
		return mergeColumn;
	}

	public void setMergeColumn(int mergeColumn) {
		this.mergeColumn = mergeColumn;
	}

	public String[] getVarValue() {
		return varValue;
	}

	public void setVarValue(String[] varValue) {
		this.varValue = varValue;
	}
    
}
