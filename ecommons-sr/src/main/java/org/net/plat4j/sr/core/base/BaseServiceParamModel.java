package org.net.plat4j.sr.core.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <h1>Spr Servcie的输入参数基类.</h1>
 * <Strong>编写规范:</strong><ol>
 * <li>只能Java基本的对象类型和对象类型的数组型,具体包含Boolean,Byte,Class,Double,Float,Integer,Long和其类型的数组类型</li>
 * <li>不能包含boolean,byte,dobule,float,int等小写的java基本型</li>
 * <li>不能包含自定义的Bean,List,Map等高级对象</li>
 * <li>dparames 动态表单专用  层次结构如下第一层次的 String 为formCode 
 *      Map<String,List<ValueObject>>中的String为fieldCode
 * 		List  里面放置了一个对象 对象为 value 和 attr   attr诸如packageIds
 * </li>
 * <li>spModel除了复杂查询外不复用
 * </ol>
 */
@SuppressWarnings("serial")
public class BaseServiceParamModel implements  Serializable{
	private SpPageModel spPageModel;
	private HttpSession session;
	private HttpServletRequest request;
	private String isAdvancedSearch;
	/**
	 * 四层结构  第一层为(V|P|D)
	 * 
	 */
	private Map<String, SpecialFields> ebsSpecialMaps= new HashMap<>();
	
	public SpPageModel getSpPageModel() {
		return spPageModel;
	}

	public void setSpPageModel(SpPageModel spPageModel) {
		this.spPageModel = spPageModel;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getIsAdvancedSearch() {
		return isAdvancedSearch;
	}

	public void setIsAdvancedSearch(String isAdvancedSearch) {
		this.isAdvancedSearch = isAdvancedSearch;
	}

	/**
	 * @return the ebsSpecialMaps
	 */
	public Map<String, SpecialFields> getEbsSpecialMaps() {
		return ebsSpecialMaps;
	}

	/**
	 * @param ebsSpecialMaps the ebsSpecialMaps to set
	 */
	public void setEbsSpecialMaps(Map<String, SpecialFields> ebsSpecialMaps) {
		this.ebsSpecialMaps = ebsSpecialMaps;
	}

	public int getPageNum() {
		if(null != spPageModel){
			return this.getSpPageModel().getPageNo().intValue();
		}else{
			return 0;
		}
		 
	}

	public int getPageSize() {
		if(null != spPageModel){
			return this.getSpPageModel().getPageSize().intValue();
		}else{
			return 0;
		}
	}
	
}
