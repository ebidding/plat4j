package org.net.plat4j.sr.core.utils.impl;

import java.lang.reflect.Array;
import java.util.List;

import net.plat4j.core.pageTools.PageTools;

import org.net.plat4j.sr.core.base.BaseServiceResultModel;
import org.net.plat4j.sr.core.base.SrPageModel;
import org.net.plat4j.sr.core.utils.IBaseServiceResultModelUtils;
import org.net.plat4j.sr.core.utils.LogHelper;

@SuppressWarnings({"rawtypes"})
public class BaseServiceResultModelUtils implements IBaseServiceResultModelUtils{
	private static  BaseServiceResultModelUtils baseserviceresultmodelutils;
	private BaseServiceResultModelUtils(){
		
	}
	
	public static BaseServiceResultModelUtils getInstance(){
		if(baseserviceresultmodelutils ==  null){
			synchronized (BaseServiceResultModelUtils.class) {
				if(baseserviceresultmodelutils ==  null){
					baseserviceresultmodelutils = new BaseServiceResultModelUtils();
				}
			}
		}
		return baseserviceresultmodelutils;
	} 
	
	
	protected LogHelper logger = new LogHelper(getClass());
	public void setPage(BaseServiceResultModel srModel,PageTools pageTools){
		SrPageModel srPageModel = new SrPageModel();
		srPageModel.setPageCount(new Long(pageTools.getPageCount()));
		srPageModel.setPageNo(new Long(pageTools.getPageNo()));
		srPageModel.setPageSize(new Long(pageTools.getPageSize()));
		srPageModel.setRecordCount(new Long(pageTools.getRecordCount()));
		srModel.setSrPageModel(srPageModel);
	}
	@SuppressWarnings("unchecked")
	public Object[] toArray(List list, Class cls) {
		Object[] obj = (Object[])Array.newInstance(cls, list.size());
		list.toArray(obj);
		return obj;
	}	
 
}
