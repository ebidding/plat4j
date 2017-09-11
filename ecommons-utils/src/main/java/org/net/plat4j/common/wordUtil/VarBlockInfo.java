package org.net.plat4j.common.wordUtil;
public class VarBlockInfo {
	 /// <summary>
    /// 变量名称
    /// </summary>
    private String varName = "";

    /// <summary>
    /// 变量值
    /// </summary>
    private VarBlockList[] varValue;
    
    /// <summary>
    /// 表头的信息,如果没有表头需要动态添加列的.则赋NULL值
    /// </summary>
  	private VarTitleInfo titleInfo;

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public VarBlockList[] getVarValue() {
		return varValue;
	}

	public void setVarValue(VarBlockList[] varValue) {
		this.varValue = varValue;
	}

	public VarTitleInfo getTitleInfo() {
		return titleInfo;
	}

	public void setTitleInfo(VarTitleInfo titleInfo) {
		this.titleInfo = titleInfo;
	}
	

}

