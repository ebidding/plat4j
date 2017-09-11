package org.net.plat4j.common.material.model;

import java.util.List;

public class SheetMaterialMain {
	private String code;
	private List<CfgSheetMaterialValue> csmv;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<CfgSheetMaterialValue> getCsmv() {
		return csmv;
	}
	public void setCsmv(List<CfgSheetMaterialValue> csmv) {
		this.csmv = csmv;
	}
}
