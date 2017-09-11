package org.net.plat4j.sr.core.base.asserts;

import org.net.plat4j.sr.core.base.BaseMessageModel;
import org.net.plat4j.sr.core.base.BaseServiceResultModel;
import org.net.plat4j.sr.core.base.BaseSrModelException;
import org.net.plat4j.sr.core.base.SrUserProcessModel;

/**
 * <h1>Spr的断言工具类.</h1>
 * <strong>使用说明</strong><ol>
 * <li>通常在Service、Business和Validate等地方使用，不再View层使用，包括Action、Webservcie</li>
 * </ol>
 * @author chenshiming
 * @since v1.3.1
 *
 */
@SuppressWarnings({"rawtypes"})
public class SprAssert {
	/**
	 * <h1>断言值一定要存在.</h1>
	 * <strong>Usage:</strong>断言ID不能为空<BR>
	 * <code>
	 *   assertNotNull("id",spModel.getId());
	 * </code>
	 * @param keyName
	 * 断言的字段名（用于显示给用户）
	 * @param obj
	 * 断言的对象
	 * @since 
	 * v1.3.1
	 */
	public static void assertNotNull(String keyName, Object obj) {
		if (obj == null) {
			throw new BaseSrModelException(SrUserProcessModel.RESULT_CODE_FAIL,
					new BaseMessageModel("resource", null, new String[] { keyName
							+ " require not-null" }));
		}
	}

	/**
	 * <h1>断言值不能为空和空字符串.</h1>
	 * <strong>Usage:</strong>断言name不能为空且不能为空字符串<BR>
	 * <code>
	 *   assertNotNullAndBlank("name",spModel.getName());
	 * </code>
	 * @param keyName
	 * 断言的字段名（用于显示给用户）
	 * @param obj
	 * 断言的对象
	 * @since 
	 * v1.3.1
	 */
	public static void assertNotNullAndBlank(String keyName, String obj) {
		if (obj == null || obj.equals("")) {
			throw new BaseSrModelException(SrUserProcessModel.RESULT_CODE_FAIL,
					new BaseMessageModel("resource", null, new String[] { keyName
							+ " require not-null and not-blank" }));
		}
	}


	
	/**
	 * <h1>断言srModel返回是info.</h1>
	 * @param srModel 断言的srModel
	 * @since v1.3.1
	 */
	public static void assertSrModelInfo(BaseServiceResultModel srModel){
		if(srModel==null || !srModel.getSrUserProcessModel().getResultCode().equals("info")){
			throw new BaseSrModelException(srModel);	
		}		
	}	
   

}
