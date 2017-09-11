/**
 * 
 */
package org.net.plat4j.common.material;

import java.util.List;

import net.plat4j.core.spring.BeanFactory;

import org.net.plat4j.common.material.ISheetOfMaterialDetailService;
import org.net.plat4j.common.material.model.SheetDataInfo;
import org.net.plat4j.common.material.model.SheetMaterialMain;

/**
 * @author lhb
 * @date 2016年7月11日  上午10:54:48
 * @version 1.0
 */
public class SheetOfMaterialDetailUtil {
	public static void syncSheetOfMaterialInfo (Long packageId,String sheetCode,List< SheetDataInfo> datalist){
		ISheetOfMaterialDetailService service = (ISheetOfMaterialDetailService) BeanFactory.getBean("ISheetOfMaterialDetailService");
		service.syncSheetOfMaterialInfo (packageId,sheetCode, datalist);
	}
	
	public static SheetMaterialMain querySheetMaterialMain (String tableName,Long agentId){
		ISheetOfMaterialDetailService service = (ISheetOfMaterialDetailService) BeanFactory.getBean("ISheetOfMaterialDetailService");
		return service.querySheetMaterialMain (tableName,agentId);
	}
}
