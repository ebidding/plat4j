package org.net.plat4j.common.syspage.model;

/**
 * @author sunnn
 *
 */
public class SysPageModel {
	private Long id;
	private String pageCode;
	private String pageName;
	private String pageUri;
	private String pageMethod;
	private String authorizedFlag;
	private String parentCode;
	private SysPageModel parentPage;//父页面
	private String url;//跳转到法律法规的页面路径
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public SysPageModel getParentPage() {
		return parentPage;
	}
	public void setParentPage(SysPageModel parentPage) {
		this.parentPage = parentPage;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPageCode() {
		return pageCode;
	}
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getPageUri() {
		return pageUri;
	}
	public void setPageUri(String pageUri) {
		this.pageUri = pageUri;
	}
	public String getPageMethod() {
		return pageMethod;
	}
	public void setPageMethod(String pageMethod) {
		this.pageMethod = pageMethod;
	}
	public String getAuthorizedFlag() {
		return authorizedFlag;
	}
	public void setAuthorizedFlag(String authorizedFlag) {
		this.authorizedFlag = authorizedFlag;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
}
