package org.net.plat4j.common.innerPortal.model;

import java.util.Date;


/**
 * AbstractYom2InnerBulletin entity provides the base persistence definition of the Yom2InnerBulletin entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractYom2InnerBulletin  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String theme;
     private String content;
     private Date publicityBeginTime;
     private Date publicityEndTime;
     private String recipientRole;
     private String promulgator;
     private String status;
     private String isDeleted;
     private Date createTime;
     private Long createUserId;
     private Date updateTime;
     private Long updateUserId;
     private String fileId;

    // Constructors

    public String getFileId() {
		return fileId;
	}


	public void setFileId(String fileId) {
		this.fileId = fileId;
	}


	/** default constructor */
    public AbstractYom2InnerBulletin() {
    }

    
    /** full constructor */
    public AbstractYom2InnerBulletin(Long id, String theme, String content,
			Date publicityBeginTime, Date publicityEndTime,
			String recipientRole, String promulgator, String status,
			String isDeleted, Date createTime, Long createUserId,
			Date updateTime, Long updateUserId, String fileId) {
		super();
		this.id = id;
		this.theme = theme;
		this.content = content;
		this.publicityBeginTime = publicityBeginTime;
		this.publicityEndTime = publicityEndTime;
		this.recipientRole = recipientRole;
		this.promulgator = promulgator;
		this.status = status;
		this.isDeleted = isDeleted;
		this.createTime = createTime;
		this.createUserId = createUserId;
		this.updateTime = updateTime;
		this.updateUserId = updateUserId;
		this.fileId = fileId;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTheme() {
		return theme;
	}


	public void setTheme(String theme) {
		this.theme = theme;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getPublicityBeginTime() {
		return publicityBeginTime;
	}


	public void setPublicityBeginTime(Date publicityBeginTime) {
		this.publicityBeginTime = publicityBeginTime;
	}


	public Date getPublicityEndTime() {
		return publicityEndTime;
	}


	public void setPublicityEndTime(Date publicityEndTime) {
		this.publicityEndTime = publicityEndTime;
	}


	public String getRecipientRole() {
		return recipientRole;
	}


	public void setRecipientRole(String recipientRole) {
		this.recipientRole = recipientRole;
	}


	public String getPromulgator() {
		return promulgator;
	}


	public void setPromulgator(String promulgator) {
		this.promulgator = promulgator;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
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


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public Long getUpdateUserId() {
		return updateUserId;
	}


	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}



}
