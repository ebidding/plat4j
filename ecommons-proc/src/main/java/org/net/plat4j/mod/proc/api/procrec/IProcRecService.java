package org.net.plat4j.mod.proc.api.procrec;

import java.util.List;

import org.net.plat4j.mod.proc.api.model.ProcNodeOp;

public interface IProcRecService {
	
	/**
	 * 获取推荐操作信息查询接口
	 * @param packageId
	 * @return
	 */
	List<ProcNodeOp> getRecommendedProcNodeOps(Long packageId);
	
	/**
	 * 获取推荐按钮操作div
	 * @param packageId
	 * @return
	 */
	String getBaseEbsRecommendedButtonsListTagSupport(Long packageId,Long bidId);
}
