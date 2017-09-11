package org.net.plat4j.common.innerPortal.model;
import java.util.Date;


/**
 * Yom2InnerBulletin entity. @author MyEclipse Persistence Tools
 */
public class Yom2InnerBulletin extends AbstractYom2InnerBulletin implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Yom2InnerBulletin() {

	}
	/** full constructor */
	public Yom2InnerBulletin(Long id, String theme, String content,
			Date publicityBeginTime, Date publicityEndTime,
			String recipientRole, String promulgator, String status,
			String isDeleted, Date createTime, Long createUserId,
			Date updateTime, Long updateUserId, String fileId) {
		super(id, theme, content, publicityBeginTime, publicityEndTime, recipientRole,
				promulgator, status, isDeleted, createTime, createUserId, updateTime,
				updateUserId, fileId);
		// TODO Auto-generated constructor stub
	}



}
