package org.net.plat4j.common.user;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class UserMenuModel {
	private Long id;
	private String menuCode;
	private String parentCode;
	private String menuEname;
	private String menuCname;
	private String menuType;
	private String menuUri;
	private String menuMethod;
	private String menuParam;
	private String target;
	private String menuShortcut;
	private String menuIcon;
	private String menuDesc;
	private String moduleName;
	
	private List childMenu = new ArrayList();
	
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getMenuEname() {
		return menuEname;
	}
	public void setMenuEname(String menuEname) {
		this.menuEname = menuEname;
	}
	public String getMenuCname() {
		return menuCname;
	}
	public void setMenuCname(String menuCname) {
		this.menuCname = menuCname;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getMenuUri() {
		return menuUri;
	}
	public void setMenuUri(String menuUri) {
		this.menuUri = menuUri;
	}
	public String getMenuMethod() {
		return menuMethod;
	}
	public void setMenuMethod(String menuMethod) {
		this.menuMethod = menuMethod;
	}
	public String getMenuParam() {
		return menuParam;
	}
	public void setMenuParam(String menuParam) {
		this.menuParam = menuParam;
	}
	public List getChildMenu() {
		return childMenu;
	}
	public void setChildMenu(List childMenu) {
		this.childMenu = childMenu;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getMenuShortcut() {
		return menuShortcut;
	}
	public void setMenuShortcut(String menuShortcut) {
		this.menuShortcut = menuShortcut;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((menuCname == null) ? 0 : menuCname.hashCode());
		result = prime * result
				+ ((menuEname == null) ? 0 : menuEname.hashCode());
		result = prime * result
				+ ((parentCode == null) ? 0 : parentCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserMenuModel other = (UserMenuModel) obj;
		if (menuCname == null) {
			if (other.menuCname != null)
				return false;
		} else if (!menuCname.equals(other.menuCname))
			return false;
		if (menuEname == null) {
			if (other.menuEname != null)
				return false;
		} else if (!menuEname.equals(other.menuEname))
			return false;
		if (parentCode == null) {
			if (other.parentCode != null)
				return false;
		} else if (!parentCode.equals(other.parentCode))
			return false;
		return true;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
