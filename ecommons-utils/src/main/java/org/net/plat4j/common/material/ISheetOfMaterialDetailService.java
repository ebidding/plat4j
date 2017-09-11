/**
 * 
 */
package org.net.plat4j.common.material;

import java.util.List;

import org.net.plat4j.common.material.model.SheetDataInfo;
import org.net.plat4j.common.material.model.SheetMaterialMain;

/**
 * @author lhb
 * @date 2016年7月11日  上午10:46:45
 * @version 1.0
 * @物料明细专用表单化接口
 */
public interface ISheetOfMaterialDetailService {
	/**
	  * @param packageId
	  * @param datalist物料明细
	  */
	public void syncSheetOfMaterialInfo (Long packageId,String sheetCode, List< SheetDataInfo> datalist);
	
	public SheetMaterialMain querySheetMaterialMain(String tableName,Long agentId);
}
