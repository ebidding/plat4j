package org.net.plat4j.common.openinghall;

import net.plat4j.core.spring.BeanFactory;

public class OpeninghallCreateUtil {

	public static void createOpeninghall(Long packageId){
		
		IOpeninghallCreateService service = (IOpeninghallCreateService) BeanFactory.getBean("IOpeninghallCreateService");
		service.createOpeninghall(packageId);
	}
}
