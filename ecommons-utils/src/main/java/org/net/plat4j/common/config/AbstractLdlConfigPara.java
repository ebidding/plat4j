package org.net.plat4j.common.config;

import java.util.Date;

// default package

/**
 * AbstractLdlConfigPara entity provides the base persistence definition of the
 * LdlConfigPara entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractLdlConfigPara implements java.io.Serializable {

	// Fields

	private Long id;
	private Long agencyCpId;
	private String agencyCpName;
	private String configCode;
	private String configValue;
	private String attr1;
	private String attr2;
	private String attr3;
	private String attr4;
	private String attr5;
	private String isDeleted;
	private Date createTime;
	private Long createUserId;
	private String comments;

	// Constructors

	/** default constructor */
	public AbstractLdlConfigPara() {
	}

	/** full constructor */
	public AbstractLdlConfigPara(Long id, Long agencyCpId,
			String agencyCpName, String configCode, String configValue,
			String attr1, String attr2, String attr3, String attr4,
			String attr5, String isDeleted, Date createTime,
			Long createUserId, String comments) {
		this.id = id;
		this.agencyCpId = agencyCpId;
		this.agencyCpName = agencyCpName;
		this.configCode = configCode;
		this.configValue = configValue;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.attr3 = attr3;
		this.attr4 = attr4;
		this.attr5 = attr5;
		this.isDeleted = isDeleted;
		this.createTime = createTime;
		this.createUserId = createUserId;
		this.comments = comments;
	}
	// Property accessors

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAgencyCpId() {
		return agencyCpId;
	}

	public void setAgencyCpId(Long agencyCpId) {
		this.agencyCpId = agencyCpId;
	}

	public String getAgencyCpName() {
		return agencyCpName;
	}

	public void setAgencyCpName(String agencyCpName) {
		this.agencyCpName = agencyCpName;
	}

	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	public String getAttr2() {
		return attr2;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	public String getAttr3() {
		return attr3;
	}

	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}

	public String getAttr4() {
		return attr4;
	}

	public void setAttr4(String attr4) {
		this.attr4 = attr4;
	}

	public String getAttr5() {
		return attr5;
	}

	public void setAttr5(String attr5) {
		this.attr5 = attr5;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}


}
