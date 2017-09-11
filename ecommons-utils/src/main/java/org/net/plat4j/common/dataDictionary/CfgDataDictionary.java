package org.net.plat4j.common.dataDictionary;


import java.util.Date;
import java.util.List;

/**
 * CfgDataDictionary entity. @author MyEclipse Persistence Tools
 */
public class CfgDataDictionary extends AbstractCfgDataDictionary implements
		java.io.Serializable {

	// Constructors

	private List<CfgDataDictionary> sonDicList;
	
	public List<CfgDataDictionary> getSonDicList() {
		return sonDicList;
	}

	public void setSonDicList(List<CfgDataDictionary> sonDicList) {
		this.sonDicList = sonDicList;
	}

	/** default constructor */
	public CfgDataDictionary() {
	}

	/** full constructor */
	public CfgDataDictionary(Long id,String dataDicDesc, String code,String val, String parentCode, String parentVal,
			String name, String groups,String modifyFlag, Long dispOrder, 
			String isDeleted, Long agentId, Long createUserId, Date createTime,
			Long updateUserId, Date updateTime) {
		super(id, dataDicDesc, code, val, parentCode, parentVal, name, groups,
				modifyFlag, dispOrder, isDeleted, agentId, createUserId, 
				createTime, updateUserId, updateTime );
	}

}
