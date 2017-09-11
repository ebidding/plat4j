package org.net.plat4j.sr.core.base;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis.utils.StringUtils;

import net.plat4j.core.spring.BeanFactory;
import net.plat4j.core.spring.Service;

import org.net.plat4j.sr.core.utils.IBaseServiceUtils;
import org.net.plat4j.sr.core.utils.LogHelper;
/**
 * <h1>Service类的基础类,目前有servcie,business,validate和auth层;<BR>
 * Servie:面向画面的业务处理<BR>
 * business:面向对象的业务逻辑<BR>
 * valiate:面向对象的用于校验的逻辑<BR>
 * auth:面向画面的权限控制层<BR>
 * .</h1>
 * <strong>[为避免出现编码拼写错误,所有的方法尽量使用生成器生成]</strong><BR><BR>
 * <strong>Demo:</strong><ol>
 * <li>Servcie层:{@link org.net.plat4j.demo.company2.service.impl.Company2Service}</li>
 * </ol>
 * <strong>方法名编写规范 :</strong><ol>
 * <li>方法名规范:方法名是强动词+名词</li>
 * <li>输入参数规范:参数只有一个,命名为"方法名+SpModel",Model存在在当前目录的model中</li>
 * <li>输出参数命名:"方法名+SrModel",Model存在在当前目录的model中</li>
 * <li>方法不能有throw Exception</li>
 * 以查询公司的方法为例,格式如下:
 *   <pre>public QueryCompanySrModel queryCompany(QueryCompanySpModel spModel);</pre>
 * </ol>
 * 
 * <strong>方法内实现编写规范:</strong>
 * <li>方法中spModel是肯定有值的,不需要assertNotNull("spModel",spModel);</li>
 * <li>必须有返回标志,info或者fail</li>
 * <li>如果方法中出现Exception需要转为BaseException
 * 	 <pre>
 *   try{
 *     ...
 *   }catch(Exception){
 *   	 throw new BaseException(e);
 *   }
 *   </pre>
 * </li>
 * <li>方法必须返回info或者fail级别,且返回的语言包不能包含中文,都是需要从语言包中获取</li>
 * <li>方法编写的格式包含init,assert,business,info等内容,具体如下:
 *   <pre>
 *   //INIT
 *   XXXXSrModel srModel = new XXXXSrModel();
 *   //Assert
 *   SprAssert.assertNotNull("xxx",spModel.getxxx());
 *   //BUSINESS
 *   srModel = dao.queryCompany(spModel);
 *   //INFO
 *   BaseUserProcess process = new BaseUserProcess();
 *   process.setInfoResult(srModel,new BaseMessageModel("resource","message.success.delete", new String[] {""}));//或process.setInfoResult(srModel,null);
 *   return srModel;
 *   </pre>
 * </li>
 * </ol>
 * @author chenshiming
 *
 */
@SuppressWarnings({"rawtypes"})
public class BaseService implements Service,IBaseService{
	//protected Log logger = LogFactory.getLog(getClass().getName());
//	protected Logger logger = LoggerFactory.getLogger(getClass().getName());  
//	protected Logger logger = LogUtils.getLogger(getClass());
	protected LogHelper logger = new LogHelper(getClass());
	private String authBeanName = null;
	public String getAuthBeanName() {
		return authBeanName;
	}
	public void setAuthBeanName(String authBeanName) {
		this.authBeanName = authBeanName;
	}
	public BaseServiceResultModel invokeService(String serviceBean,Class serviceClass,String methodName,BaseServiceParamModel spModel) throws BaseException{
		BaseTimeMonitor monitor = new BaseTimeMonitor("invokeService#"+serviceBean+"#"+methodName);
		IBaseServiceUtils baseServiceUtils = BeanFactory.getBean("IBaseServiceUtils");
		BaseServiceResultModel srModel = baseServiceUtils.service(serviceBean, serviceClass, methodName, spModel);
		monitor.print();
		return srModel; 
	}	
	/**
	 * 
	 * @param spModel
	 * @return
	 */
	public Map <String,MoneyModelObject> moneyModelMap(BaseServiceParamModel spModel){
		Map<String,MoneyModelObject> moneyModelMap = new HashMap<>();
		SpecialFields specialFields = spModel.getEbsSpecialMaps().get("ebsPrice");
		if(specialFields==null) 
			throw new BaseException("this jsp could not find  name like  ebsPrice(name) please check it and we suggest name    start with english words ! ");
		Map<String, List<ValueObject>> listFieldsByGroup = specialFields.getListFieldsByGroup();
		for(Map.Entry<String, List<ValueObject>> entrySet :listFieldsByGroup.entrySet()){
			List<ValueObject> values = entrySet.getValue();
			if(values.size()>0){
				String key = entrySet.getKey();
				MoneyModelObject meoneyModelObject = new MoneyModelObject();
				ValueObject valueObject = values.get(0);
				String value= valueObject.getValue();
				Map<String, String> attrMap = valueObject.getAttrMap();
				meoneyModelObject.setValue(StringUtils.isEmpty(value) ? null :new BigDecimal(value));
				meoneyModelObject.setCurrency(attrMap.get("CURRENCY"));
				meoneyModelObject.setUnit(attrMap.get("UNIT"));
				moneyModelMap.put(key, meoneyModelObject);
			}
		}
		return moneyModelMap;
	}
	
	/**
	 * @deprecated 
	 * <strong>See Also</strong>assertValidate(String object,String method,String value,Object idValue)
	 */
	/*
	public void assertValidate(String object,String method,String value){
		assertValidate(object,method,value);
	}
	*/
	/**
	 * @deprecated
	 * <strong>See Also</strong>SprAssert.assertValidate
	 */
}
