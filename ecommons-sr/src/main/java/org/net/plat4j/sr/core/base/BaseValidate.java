package org.net.plat4j.sr.core.base;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.net.plat4j.sr.core.utils.IBaseBeanUtils;

import net.plat4j.core.spring.BeanFactory;
/**
 * <h1>spr validate校验框架是基于jquery和validate框架为基础开发实现的,提供前台校验和后台校验统一的实现.</h1><br>
 * <strong>使用场景</strong><ol>
 * <li>一些关键字的校验</li>
 * <li>涉及到数据库的查询校验</li>
 * </ol>
 * <strong>DEMO:</strong>{@link org.net.plat4j.demo.company2.validate.impl.Company2ValidateImpl}<BR>
 * <strong>单字段校验使用说明</strong><ol>
 * <li>继承BaseValidate<pre>
 * public class Company2ValidateImpl extends BaseValidate implements ICompany2Validate
 * <pre></li>
 * <li>方法实现(如果是对单字段进行校验可以不用定义spModel和srModel)
 * <pre>
 * public BaseValidateSrModel checkCompanyName(BaseValidateSpModel spModel) {
 * 	BaseValidateSrModel srModel = new BaseValidateSrModel();
 * 	BaseUserProcess process = new BaseUserProcess();  
 * 	process.setInfoResult(srModel,null);
 * 	
 * 	validateRequire(spModel.getValue());
 * 	validateRangelength(spModel.getValue(), 6, 100);
 * 	
 * 	List list = dao.getDemoCompanyByCompanyName(spModel.getValue());
 * 	list = getListAfterRemoveIdValue(list, "id", spModel.getIdValue());
 * 	if(list.size()>0){
 * 		process.setFailResult(srModel,new BaseMessageModel("company2","company2.fail.companyName.validate.exists", new String[] {}));
 * 	}
 * 		
 * 	return srModel;	
 * }
 * </pre>
 * </li>
 * <li>JSP层对输入框进行校验
 * <pre>
 * $(document).ready(function(){ 
 * 	$("#validate-form").validate({
 * 		validClass:"checked", 
 * 		success: function(label) {  
 * 		    label.html(" ").addClass("checked");  
 * 		},	
 * 		rules:{
 * 			"v(companyName@submitCompanySpModel#String)":{
 * 				required: true,
 * 				sprremote:{
 * 					 object:"ICompany2Validate",
 * 					 method:"checkCompanyName",
 * 					 value:$(this).val(),
 * 					 idValue:$("input[name=v(id@submitCompanySpModel#Long)]").val()			 
 * 				}
 * 			}		
 * 		}
 * 	});
 * });
 * </pre></li>
 * <li>Servcie层在Servcie对字段的校验
 * <pre>
 * assertValidate("ICompany2Validate","checkCompanyName",spModel.getCompanyName(),spModel.getId());
 * </pre>
 * </li>
 * </ol>
 * <strong>复合字段校验使用说明,</strong>比如要实现境内公司名是中文名不能重复,境外则英文名不能重复<ol>
 * <li>整个结构上与单字段校验一样,区别在于validate层方法的写法和jsp及service调用有区别</li>
 * <li>Service方法实现(srModel不用定义,spModel需要定义):
 * <pre>
 * public BaseValidateSrModel checkCompanyName(CheckCompanyCodeSpModel spModel) {
 *   validateRequire(spModel.getZoneFlag());
 *   validateRequire(spModel.getCompanyName());
 *   
 *   BaseValidateSrModel srModel = new BaseValidateSrModel();
 *   BaseUserProcess process = new BaseUserProcess();  
 *   process.setInfoResult(srModel,null);
 *   
 *   validateRequire(spModel.getCompanyName());
 *   if(spModel.getZoneFlag().equals("1")){
 *     logger.info("境外的都是境内,公司名是10到100位:"+spModel.getZoneFlag());
 *     validateRangelength(spModel.getCompanyName(), 10, 100); * 
 *   }else{
 *     logger.info("非境外的都是境内,公司名是6到100位:"+spModel.getZoneFlag());
 *     validateRangelength(spModel.getCompanyName(), 6, 100);
 *   }
 *   
 * 
 *   
 *   List list = dao.getDemoCompanyByCompanyName(spModel.getCompanyName());
 *   list = getListAfterRemoveIdValue(list, "id", spModel.getIdValue());
 *   if(list.size()>0){
 *     process.setFailResult(srModel,new BaseMessageModel("company2","company2.fail.companyName.validate.exists", new String[] {}));
 *   }
 *   
 *   return srModel;   
 * }
 * </pre>
 * </li>
 * <li>JSP调用
 * <pre>
 * $("#validate-form").validate({
 * 	rules:{
 * 		"v(companyName@submitCompanySpModel#String)":{
 * 			required: true,
 * 			sprremote:{
 * 				 object:"ICompany2Validate",
 * 				 method:"checkCompanyName",
 * 				 valueJson:function(){return arrayToJson({
 * 				 	"zoneFlag":$("select[name=v(zoneFlag@submitCompanySpModel#String)]").val(),
 * 				 	"companyName":$("input[name=v(companyName@submitCompanySpModel#String)]").val()
 * 				 });},
 * 				 idValue:$("input[name=v(id@submitCompanySpModel#Long)]").val()			 
 * 			}
 * 		}	
 * 	}
 * });
 * </pre>
 * </li>
 * <li>Service调用
 * <pre>
 * CheckCompanyCodeSpModel checkCompanyCodeSpModel = new CheckCompanyCodeSpModel();
 * checkCompanyCodeSpModel.setZoneFlag(spModel.getZoneFlag());
 * checkCompanyCodeSpModel.setCompanyName(spModel.getCompanyName());
 * SprAssert.assertValidate("ICompany2Validate","checkCompanyName",checkCompanyCodeSpModel,spModel.getId());
 * </pre>
 * </li>
 * </ol>
 * @since v1.1.1
 * @author chenshiming
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class BaseValidate extends BaseService {
	/**
	 * <h1>必填检验</h1>
	 * @param value
	 */
	public void validateRequire(String value){
		if(value==null||value.length()==0){
			throw new BaseSrModelException(new BaseMessageModel("sr.spvalidate","required",new String[]{})); 
		}
	}
	/**
	 * <h1>数字格式校验</h1>
	 * @param value
	 */
	public void validateNumber(String value){
		Pattern pattern = Pattern.compile("[0-9\\.]*"); 
		if(!pattern.matcher(value).matches()){
			throw new BaseSrModelException(new BaseMessageModel("sr.spvalidate","number",new String[]{})); 
			
		}
	}	
	/**
	 * <h1>Email格式校验</h1>
	 * @param value
	 */
	public void validateEmail(String value){
		Pattern pattern = Pattern.compile("^/w+([-.]/w+)*@/w+([-]/w+)*/.(/w+([-]/w+)*/.)*[a-z]{2,3}$");
		if(!pattern.matcher(value).matches()){
			throw new BaseSrModelException(new BaseMessageModel("sr.spvalidate","email",new String[]{})); 
			
		}
	}	
	/**
	 * <h1>最大值校验</h1>
	 * @param value
	 */
	public void validateMaxlength(String value,int length){
		if(value!=null&&value.length()>length){
			throw new BaseSrModelException(new BaseMessageModel("sr.spvalidate","maxlength",new String[]{length+""})); 
		}
	}	
	/**
	 * <h1>最小值校验</h1>
	 * @param value
	 */
	public void validateMinlength(String value,int length){
		if(value!=null&&value.length()<length){
			throw new BaseSrModelException(new BaseMessageModel("sr.spvalidate","minlength",new String[]{length+""})); 
		}
	}	
	/**
	 * <h1>长度区间校验</h1>
	 * @param value
	 */
	public void validateRangelength(String value,int minLength,int maxLength){
		if(value!=null&&(value.length()<minLength||value.length()>maxLength)){
			throw new BaseSrModelException(new BaseMessageModel("sr.spvalidate","rangelength",new String[]{minLength+"",maxLength+""})); 
		}
	}	
	/**
	 * <h1>最大值校验</h1>
	 * @param value
	 */
	public void validateMax(String value,int max){
		validateNumber(value);
		double vv = new Long(value).doubleValue();
		if(vv>max){
			throw new BaseSrModelException(new BaseMessageModel("sr.spvalidate","max",new String[]{max+""})); 
		}
	}	
	/**
	 * <h1>最小值校验</h1>
	 * @param value
	 */
	public void validateMin(String value,int min){
		validateNumber(value);
		double vv = new Long(value).doubleValue();
		if(vv<min){
			throw new BaseSrModelException(new BaseMessageModel("sr.spvalidate","min",new String[]{min+""})); 
		}
	}	
	/**
	 * <h1>值区间校验</h1>
	 * @param value
	 * @param min 最小值
	 * @param max 最大值
	 */
	public void validateRange(String value,int min,int max){
		validateNumber(value);
		double vv = new Long(value).doubleValue();
		if(vv<min||vv>min){
			throw new BaseSrModelException(new BaseMessageModel("sr.spvalidate","range",new String[]{min+"",max+""})); 
		}
	}	
	/**
	 * <h1>排除id的值相等记录的List</h1><BR>excludeIdValue仅支持String,Long型
	 * <strong>Usage</strong>在list中排除记录id为1的值
	 * <pre>
	 * list = getListAfterRemoveIdValue(list, "id", "1");
	 * </pre>
	 * @param list Model的数据集合
	 * @param excludeIdField 排除记录的字段名
	 * @param excludeIdValue 排除记录的值
	 * @return 返回排除后的list记录
	 */
	public List getListAfterRemoveIdValue(List list,String excludeIdField,String excludeIdValue){
		IBaseBeanUtils baseBeanUtils = BeanFactory.getBean("IBaseBeanUtils"); 
		List ret = new ArrayList();
		if(list!=null){
			for(int i=0,j=list.size();i<j;i++){
				Object listModelValue = baseBeanUtils.getField(list.get(i), excludeIdField);
				if(excludeIdValue==null && listModelValue==null){
					continue;
				}				
				if(excludeIdValue!=null && listModelValue!=null && excludeIdValue.equals(listModelValue.toString())){
					continue;
				}
				ret.add(listModelValue);
			}
		}			
		return ret;
	}
}
