package org.net.plat4j.common.dataDictionary;

import java.util.Date;

/**
 * AbstractCfgDataDictionary entity provides the base persistence definition of
 * the CfgDataDictionary entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCfgDataDictionary implements java.io.Serializable {

	// Fields

	private Long id;
	private String dataDicDesc;
	private String code;
	private String val;
	private String parentCode;
	private String parentVal;
	private String name;
	private String groups;
	private String modifyFlag;
	private Long dispOrder;
	private String isDeleted;
	private Long agentId;
	private Long createUserId;
	private Date createTime;
	private Long updateUserId;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public AbstractCfgDataDictionary() {
	}

	/** full constructor */
	public AbstractCfgDataDictionary(Long id,String dataDicDesc,String code,String val, String parentCode,
			String parentVal, String name,
			String groups, String modifyFlag, Long dispOrder,
			String isDeleted,Long agentId,
			Long createUserId, Date createTime, Long updateUserId,
			Date updateTime) {
		this.id = id;
		this.dataDicDesc = dataDicDesc;
		this.code = code;
		this.val = val;
		this.parentCode = parentCode;
		this.parentVal = parentVal;
		this.name = name;
		this.groups = groups;
		this.modifyFlag = modifyFlag;
		this.dispOrder = dispOrder;
		this.isDeleted = isDeleted;
		this.agentId = agentId;
		this.createUserId = createUserId;
		this.createTime = createTime;
		this.updateUserId = updateUserId;
		this.updateTime = updateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
	public String getDataDicDesc() {
		return dataDicDesc;
	}

	public void setDataDicDesc(String dataDicDesc) {
		this.dataDicDesc = dataDicDesc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentVal() {
		return parentVal;
	}

	public void setParentVal(String parentVal) {
		this.parentVal = parentVal;
	}
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public Long getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(Long dispOrder) {
		this.dispOrder = dispOrder;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	// Property accessors

	

}
