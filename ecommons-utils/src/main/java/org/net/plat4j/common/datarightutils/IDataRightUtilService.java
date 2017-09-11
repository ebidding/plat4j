package org.net.plat4j.common.datarightutils;

import org.net.plat4j.sr.core.base.IBaseService;

public interface IDataRightUtilService extends IBaseService{
	public String getControlSQL(String businessCode);
	
	public boolean hasDataRight(String businessCode , String idVal);
}
