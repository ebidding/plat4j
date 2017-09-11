package org.net.plat4j.common.config;

import java.util.Date;

// default package

/**
 * LdlConfigPara entity. @author MyEclipse Persistence Tools
 */
public class LdlConfigPara extends AbstractLdlConfigPara implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public LdlConfigPara() {
	}

	/** full constructor */
	public LdlConfigPara(Long id, Long agencyCpId,
			String agencyCpName, String configCode, String configValue,
			String attr1, String attr2, String attr3, String attr4,
			String attr5, String isDeleted, Date createTime,
			Long createUserId, String comments) {
		super(id, agencyCpId,
				agencyCpName, configCode, configValue,
				attr1, attr2, attr3, attr4,
				attr5, isDeleted, createTime,
				createUserId, comments);
	}

}
