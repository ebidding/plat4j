package org.net.plat4j.common.wordUtil;
import org.apache.commons.lang3.StringUtils;

public class VarInfom {

    private String varName;
	
	private String varValue;
	
	
	public String getVarValue() {
		return varValue;
	}
	public void setVarValue(String varValue) {
		if(StringUtils.isEmpty(varValue)){varValue ="（未填）";}
		this.varValue = varValue;
	}
	public String getVarName() {
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}
}
