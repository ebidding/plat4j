package org.net.plat4j.mod.proc.api.procrec;

import java.util.List;

import net.plat4j.core.spring.BeanFactory;

import org.net.plat4j.mod.proc.api.model.ProcNodeOp;

public class ProcRecUtils {
	
	/**
	 * 获取推荐操作信息
	 * @param packageId 标段id<br>
	 */
	public static List<ProcNodeOp> getRecommendedProcNodeOps(Long packageId){
		IProcRecService service=(IProcRecService)BeanFactory.getBean("IProcRecommendService");
		return service.getRecommendedProcNodeOps(packageId);
		
		
	}
	/**
	 * 获取推荐按钮操作div
	 * @param packageId 标段id<br>
	 */
	public static String getBaseEbsRecommendedButtonsListTagSupport(Long packageId,Long bidId){
		IProcRecService service=(IProcRecService)BeanFactory.getBean("IProcRecommendService");
		return service.getBaseEbsRecommendedButtonsListTagSupport(packageId,bidId);
		
		
	}
}
