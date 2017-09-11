package org.net.plat4j.common.config;

import java.util.Date;

/**
 * AbstractLdlBidAgency entity provides the base persistence definition of the
 * LdlBidAgency entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractLdlBidAgency implements java.io.Serializable {

	// Fields

	private Long id;
	private Long companyId;
	private String agencyCode;
	private String agencyName;
	private String linkBuybid;
	private String linkBuybidEn;
	private String linkBuybidPhone;
	private String linkBuybidFax;
	private String agencyAddress;
	private String agencyAddressEn;
	private String zipCode;
	private String netAddress;
	private String siteName;
	private String siteNameEn;
	private String servicePhone;
	private String serviceFax;
	private String swiftCode;
	private String priveCode;
	private String keyInfo;
	private Long fbReceiveId;
	private String fbReceiveName;
	private Long bfReceiveId;
	private String bfReceiveName;
	private String haveConsignMgr;
	private String bidMainMode;
	private String lastDelayBulletin;
	private String attr1;
	private String attr2;
	private String attr3;
	private String attr4;
	private String attr5;
	private String isDeleted;
	private Date createTime;
	private Long createUserId;
	private Date updateTime;
	private Long updateUserId;
	private String tenderOrgBank;
	private String tenderOrgBankEn;
	private String tenderOrgAccount;
	private String tenderOrgBankUs;
	private String tenderOrgBankUsEn;
	private String tenderOrgAccountUs;
	private String tenderOrgBankOu;
	private String tenderOrgBankOuEn;
	private String tenderOrgAccountOu;
	private String tenderOrgBankJp;
	private String tenderOrgBankJpEn;
	private String tenderOrgAccountJp;
	private Long postFeeRmb;
	private Long postFeeUsd;
	private String bullPublishMedia;
	private String dedicatedLanePic;
	private String buybidAddressOff;
	private String buybidAddressOn;
	private String buybidAddressOffEn;
	private String buybidAddressOnEn;
	private String ebiddingDomain;
	private String ebiddingDomainPara;
	private String pbRptHandleType;
	private String deviceManualRate;
	private String deviceDirection;
	private String deviceSaleService;
	private String deviceMainCost;
	private String bailForm;
	private String tenderCurAbroad;
	private String deviceManualRateEn;
	private String deviceDirectionEn;
	private String deviceSaleServiceEn;
	private String deviceMainCostEn;
	private String bailFormEn;
	private String tenderCurAbroadEn;
	private Long ldlEvaMetionAgencyCpId;
	private String linkBuybidMobile;
	private String modelProjectMgr;
	private String modelConsignContract;
	private String modelEvaBid;
	private String modelPreQual;
	private Long committDaysBeforeOpen;
	private String modelSendMail;
	private String fileBankAccountName;
	private String fileBankName;
	private String fileBankAccount;
	private String tenderFeeAccountNameUs;
	private String tenderFeeBankNameUs;
	private String tenderFeeAccountUs;
	private String tenderFeeSwiftUs;
	private String tenderFeeAccountNameUsEn;
	private String tenderFeeBankNameUsEn;
	private String tenderFeeAccountUsEn;
	private String tenderFeeSwiftUsEn;
	private String bailBankAccountName;
	private String bailBankName;
	private String bailBankAccount;
	private String bailFeeAccountNameUs;
	private String bailFeeBankNameUs;
	private String bailFeeAccountUs;
	private String bailFeeSwiftUs;
	private String bailFeeAccountNameUsEn;
	private String bailFeeBankNameUsEn;
	private String bailFeeAccountUsEn;
	private String bailFeeSwiftUsEn;

	// Constructors

	/** default constructor */
	public AbstractLdlBidAgency() {
	}

	/** minimal constructor */
	public AbstractLdlBidAgency(Long id, Long companyId) {
		this.id = id;
		this.companyId = companyId;
	}

	/** full constructor */
	public AbstractLdlBidAgency(Long id, Long companyId, String agencyCode,
			String agencyName, String linkBuybid, String linkBuybidEn,
			String linkBuybidPhone, String linkBuybidFax, String agencyAddress,
			String agencyAddressEn, String zipCode, String netAddress,
			String siteName, String siteNameEn, String servicePhone,
			String serviceFax, String swiftCode, String priveCode,
			String keyInfo, Long fbReceiveId, String fbReceiveName,
			Long bfReceiveId, String bfReceiveName, String haveConsignMgr,
			String bidMainMode, String lastDelayBulletin, String attr1,
			String attr2, String attr3, String attr4, String attr5,
			String isDeleted, Date createTime, Long createUserId,
			Date updateTime, Long updateUserId, String tenderOrgBank,
			String tenderOrgBankEn, String tenderOrgAccount,
			String tenderOrgBankUs, String tenderOrgBankUsEn,
			String tenderOrgAccountUs, String tenderOrgBankOu,
			String tenderOrgBankOuEn, String tenderOrgAccountOu,
			String tenderOrgBankJp, String tenderOrgBankJpEn,
			String tenderOrgAccountJp, Long postFeeRmb, Long postFeeUsd,
			String bullPublishMedia, String dedicatedLanePic,
			String buybidAddressOff, String buybidAddressOn,
			String buybidAddressOffEn, String buybidAddressOnEn,
			String ebiddingDomain, String ebiddingDomainPara,
			String pbRptHandleType, String deviceManualRate,
			String deviceDirection, String deviceSaleService,
			String deviceMainCost, String bailForm, String tenderCurAbroad,
			String deviceManualRateEn, String deviceDirectionEn,
			String deviceSaleServiceEn, String deviceMainCostEn,
			String bailFormEn, String tenderCurAbroadEn,
			Long ldlEvaMetionAgencyCpId, String linkBuybidMobile,
			String modelProjectMgr, String modelConsignContract,
			String modelEvaBid, String modelPreQual,
			Long committDaysBeforeOpen, String modelSendMail,
			String fileBankAccountName, String fileBankName,
			String fileBankAccount, String tenderFeeAccountNameUs,
			String tenderFeeBankNameUs, String tenderFeeAccountUs,
			String tenderFeeSwiftUs, String tenderFeeAccountNameUsEn,
			String tenderFeeBankNameUsEn, String tenderFeeAccountUsEn,
			String tenderFeeSwiftUsEn, String bailBankAccountName,
			String bailBankName, String bailBankAccount,
			String bailFeeAccountNameUs, String bailFeeBankNameUs,
			String bailFeeAccountUs, String bailFeeSwiftUs,
			String bailFeeAccountNameUsEn, String bailFeeBankNameUsEn,
			String bailFeeAccountUsEn, String bailFeeSwiftUsEn) {
		this.id = id;
		this.companyId = companyId;
		this.agencyCode = agencyCode;
		this.agencyName = agencyName;
		this.linkBuybid = linkBuybid;
		this.linkBuybidEn = linkBuybidEn;
		this.linkBuybidPhone = linkBuybidPhone;
		this.linkBuybidFax = linkBuybidFax;
		this.agencyAddress = agencyAddress;
		this.agencyAddressEn = agencyAddressEn;
		this.zipCode = zipCode;
		this.netAddress = netAddress;
		this.siteName = siteName;
		this.siteNameEn = siteNameEn;
		this.servicePhone = servicePhone;
		this.serviceFax = serviceFax;
		this.swiftCode = swiftCode;
		this.priveCode = priveCode;
		this.keyInfo = keyInfo;
		this.fbReceiveId = fbReceiveId;
		this.fbReceiveName = fbReceiveName;
		this.bfReceiveId = bfReceiveId;
		this.bfReceiveName = bfReceiveName;
		this.haveConsignMgr = haveConsignMgr;
		this.bidMainMode = bidMainMode;
		this.lastDelayBulletin = lastDelayBulletin;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.attr3 = attr3;
		this.attr4 = attr4;
		this.attr5 = attr5;
		this.isDeleted = isDeleted;
		this.createTime = createTime;
		this.createUserId = createUserId;
		this.updateTime = updateTime;
		this.updateUserId = updateUserId;
		this.tenderOrgBank = tenderOrgBank;
		this.tenderOrgBankEn = tenderOrgBankEn;
		this.tenderOrgAccount = tenderOrgAccount;
		this.tenderOrgBankUs = tenderOrgBankUs;
		this.tenderOrgBankUsEn = tenderOrgBankUsEn;
		this.tenderOrgAccountUs = tenderOrgAccountUs;
		this.tenderOrgBankOu = tenderOrgBankOu;
		this.tenderOrgBankOuEn = tenderOrgBankOuEn;
		this.tenderOrgAccountOu = tenderOrgAccountOu;
		this.tenderOrgBankJp = tenderOrgBankJp;
		this.tenderOrgBankJpEn = tenderOrgBankJpEn;
		this.tenderOrgAccountJp = tenderOrgAccountJp;
		this.postFeeRmb = postFeeRmb;
		this.postFeeUsd = postFeeUsd;
		this.bullPublishMedia = bullPublishMedia;
		this.dedicatedLanePic = dedicatedLanePic;
		this.buybidAddressOff = buybidAddressOff;
		this.buybidAddressOn = buybidAddressOn;
		this.buybidAddressOffEn = buybidAddressOffEn;
		this.buybidAddressOnEn = buybidAddressOnEn;
		this.ebiddingDomain = ebiddingDomain;
		this.ebiddingDomainPara = ebiddingDomainPara;
		this.pbRptHandleType = pbRptHandleType;
		this.deviceManualRate = deviceManualRate;
		this.deviceDirection = deviceDirection;
		this.deviceSaleService = deviceSaleService;
		this.deviceMainCost = deviceMainCost;
		this.bailForm = bailForm;
		this.tenderCurAbroad = tenderCurAbroad;
		this.deviceManualRateEn = deviceManualRateEn;
		this.deviceDirectionEn = deviceDirectionEn;
		this.deviceSaleServiceEn = deviceSaleServiceEn;
		this.deviceMainCostEn = deviceMainCostEn;
		this.bailFormEn = bailFormEn;
		this.tenderCurAbroadEn = tenderCurAbroadEn;
		this.ldlEvaMetionAgencyCpId = ldlEvaMetionAgencyCpId;
		this.linkBuybidMobile = linkBuybidMobile;
		this.modelProjectMgr = modelProjectMgr;
		this.modelConsignContract = modelConsignContract;
		this.modelEvaBid = modelEvaBid;
		this.modelPreQual = modelPreQual;
		this.committDaysBeforeOpen = committDaysBeforeOpen;
		this.modelSendMail = modelSendMail;
		this.fileBankAccountName = fileBankAccountName;
		this.fileBankName = fileBankName;
		this.fileBankAccount = fileBankAccount;
		this.tenderFeeAccountNameUs = tenderFeeAccountNameUs;
		this.tenderFeeBankNameUs = tenderFeeBankNameUs;
		this.tenderFeeAccountUs = tenderFeeAccountUs;
		this.tenderFeeSwiftUs = tenderFeeSwiftUs;
		this.tenderFeeAccountNameUsEn = tenderFeeAccountNameUsEn;
		this.tenderFeeBankNameUsEn = tenderFeeBankNameUsEn;
		this.tenderFeeAccountUsEn = tenderFeeAccountUsEn;
		this.tenderFeeSwiftUsEn = tenderFeeSwiftUsEn;
		this.bailBankAccountName = bailBankAccountName;
		this.bailBankName = bailBankName;
		this.bailBankAccount = bailBankAccount;
		this.bailFeeAccountNameUs = bailFeeAccountNameUs;
		this.bailFeeBankNameUs = bailFeeBankNameUs;
		this.bailFeeAccountUs = bailFeeAccountUs;
		this.bailFeeSwiftUs = bailFeeSwiftUs;
		this.bailFeeAccountNameUsEn = bailFeeAccountNameUsEn;
		this.bailFeeBankNameUsEn = bailFeeBankNameUsEn;
		this.bailFeeAccountUsEn = bailFeeAccountUsEn;
		this.bailFeeSwiftUsEn = bailFeeSwiftUsEn;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getAgencyCode() {
		return this.agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getAgencyName() {
		return this.agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getLinkBuybid() {
		return this.linkBuybid;
	}

	public void setLinkBuybid(String linkBuybid) {
		this.linkBuybid = linkBuybid;
	}

	public String getLinkBuybidEn() {
		return this.linkBuybidEn;
	}

	public void setLinkBuybidEn(String linkBuybidEn) {
		this.linkBuybidEn = linkBuybidEn;
	}

	public String getLinkBuybidPhone() {
		return this.linkBuybidPhone;
	}

	public void setLinkBuybidPhone(String linkBuybidPhone) {
		this.linkBuybidPhone = linkBuybidPhone;
	}

	public String getLinkBuybidFax() {
		return this.linkBuybidFax;
	}

	public void setLinkBuybidFax(String linkBuybidFax) {
		this.linkBuybidFax = linkBuybidFax;
	}

	public String getAgencyAddress() {
		return this.agencyAddress;
	}

	public void setAgencyAddress(String agencyAddress) {
		this.agencyAddress = agencyAddress;
	}

	public String getAgencyAddressEn() {
		return this.agencyAddressEn;
	}

	public void setAgencyAddressEn(String agencyAddressEn) {
		this.agencyAddressEn = agencyAddressEn;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getNetAddress() {
		return this.netAddress;
	}

	public void setNetAddress(String netAddress) {
		this.netAddress = netAddress;
	}

	public String getSiteName() {
		return this.siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteNameEn() {
		return this.siteNameEn;
	}

	public void setSiteNameEn(String siteNameEn) {
		this.siteNameEn = siteNameEn;
	}

	public String getServicePhone() {
		return this.servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public String getServiceFax() {
		return this.serviceFax;
	}

	public void setServiceFax(String serviceFax) {
		this.serviceFax = serviceFax;
	}

	public String getSwiftCode() {
		return this.swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getPriveCode() {
		return this.priveCode;
	}

	public void setPriveCode(String priveCode) {
		this.priveCode = priveCode;
	}

	public String getKeyInfo() {
		return this.keyInfo;
	}

	public void setKeyInfo(String keyInfo) {
		this.keyInfo = keyInfo;
	}

	public Long getFbReceiveId() {
		return this.fbReceiveId;
	}

	public void setFbReceiveId(Long fbReceiveId) {
		this.fbReceiveId = fbReceiveId;
	}

	public String getFbReceiveName() {
		return this.fbReceiveName;
	}

	public void setFbReceiveName(String fbReceiveName) {
		this.fbReceiveName = fbReceiveName;
	}

	public Long getBfReceiveId() {
		return this.bfReceiveId;
	}

	public void setBfReceiveId(Long bfReceiveId) {
		this.bfReceiveId = bfReceiveId;
	}

	public String getBfReceiveName() {
		return this.bfReceiveName;
	}

	public void setBfReceiveName(String bfReceiveName) {
		this.bfReceiveName = bfReceiveName;
	}

	public String getHaveConsignMgr() {
		return this.haveConsignMgr;
	}

	public void setHaveConsignMgr(String haveConsignMgr) {
		this.haveConsignMgr = haveConsignMgr;
	}

	public String getBidMainMode() {
		return this.bidMainMode;
	}

	public void setBidMainMode(String bidMainMode) {
		this.bidMainMode = bidMainMode;
	}

	public String getLastDelayBulletin() {
		return this.lastDelayBulletin;
	}

	public void setLastDelayBulletin(String lastDelayBulletin) {
		this.lastDelayBulletin = lastDelayBulletin;
	}

	public String getAttr1() {
		return this.attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	public String getAttr2() {
		return this.attr2;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	public String getAttr3() {
		return this.attr3;
	}

	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}

	public String getAttr4() {
		return this.attr4;
	}

	public void setAttr4(String attr4) {
		this.attr4 = attr4;
	}

	public String getAttr5() {
		return this.attr5;
	}

	public void setAttr5(String attr5) {
		this.attr5 = attr5;
	}

	public String getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getTenderOrgBank() {
		return this.tenderOrgBank;
	}

	public void setTenderOrgBank(String tenderOrgBank) {
		this.tenderOrgBank = tenderOrgBank;
	}

	public String getTenderOrgBankEn() {
		return this.tenderOrgBankEn;
	}

	public void setTenderOrgBankEn(String tenderOrgBankEn) {
		this.tenderOrgBankEn = tenderOrgBankEn;
	}

	public String getTenderOrgAccount() {
		return this.tenderOrgAccount;
	}

	public void setTenderOrgAccount(String tenderOrgAccount) {
		this.tenderOrgAccount = tenderOrgAccount;
	}

	public String getTenderOrgBankUs() {
		return this.tenderOrgBankUs;
	}

	public void setTenderOrgBankUs(String tenderOrgBankUs) {
		this.tenderOrgBankUs = tenderOrgBankUs;
	}

	public String getTenderOrgBankUsEn() {
		return this.tenderOrgBankUsEn;
	}

	public void setTenderOrgBankUsEn(String tenderOrgBankUsEn) {
		this.tenderOrgBankUsEn = tenderOrgBankUsEn;
	}

	public String getTenderOrgAccountUs() {
		return this.tenderOrgAccountUs;
	}

	public void setTenderOrgAccountUs(String tenderOrgAccountUs) {
		this.tenderOrgAccountUs = tenderOrgAccountUs;
	}

	public String getTenderOrgBankOu() {
		return this.tenderOrgBankOu;
	}

	public void setTenderOrgBankOu(String tenderOrgBankOu) {
		this.tenderOrgBankOu = tenderOrgBankOu;
	}

	public String getTenderOrgBankOuEn() {
		return this.tenderOrgBankOuEn;
	}

	public void setTenderOrgBankOuEn(String tenderOrgBankOuEn) {
		this.tenderOrgBankOuEn = tenderOrgBankOuEn;
	}

	public String getTenderOrgAccountOu() {
		return this.tenderOrgAccountOu;
	}

	public void setTenderOrgAccountOu(String tenderOrgAccountOu) {
		this.tenderOrgAccountOu = tenderOrgAccountOu;
	}

	public String getTenderOrgBankJp() {
		return this.tenderOrgBankJp;
	}

	public void setTenderOrgBankJp(String tenderOrgBankJp) {
		this.tenderOrgBankJp = tenderOrgBankJp;
	}

	public String getTenderOrgBankJpEn() {
		return this.tenderOrgBankJpEn;
	}

	public void setTenderOrgBankJpEn(String tenderOrgBankJpEn) {
		this.tenderOrgBankJpEn = tenderOrgBankJpEn;
	}

	public String getTenderOrgAccountJp() {
		return this.tenderOrgAccountJp;
	}

	public void setTenderOrgAccountJp(String tenderOrgAccountJp) {
		this.tenderOrgAccountJp = tenderOrgAccountJp;
	}

	public Long getPostFeeRmb() {
		return this.postFeeRmb;
	}

	public void setPostFeeRmb(Long postFeeRmb) {
		this.postFeeRmb = postFeeRmb;
	}

	public Long getPostFeeUsd() {
		return this.postFeeUsd;
	}

	public void setPostFeeUsd(Long postFeeUsd) {
		this.postFeeUsd = postFeeUsd;
	}

	public String getBullPublishMedia() {
		return this.bullPublishMedia;
	}

	public void setBullPublishMedia(String bullPublishMedia) {
		this.bullPublishMedia = bullPublishMedia;
	}

	public String getDedicatedLanePic() {
		return this.dedicatedLanePic;
	}

	public void setDedicatedLanePic(String dedicatedLanePic) {
		this.dedicatedLanePic = dedicatedLanePic;
	}

	public String getBuybidAddressOff() {
		return this.buybidAddressOff;
	}

	public void setBuybidAddressOff(String buybidAddressOff) {
		this.buybidAddressOff = buybidAddressOff;
	}

	public String getBuybidAddressOn() {
		return this.buybidAddressOn;
	}

	public void setBuybidAddressOn(String buybidAddressOn) {
		this.buybidAddressOn = buybidAddressOn;
	}

	public String getBuybidAddressOffEn() {
		return this.buybidAddressOffEn;
	}

	public void setBuybidAddressOffEn(String buybidAddressOffEn) {
		this.buybidAddressOffEn = buybidAddressOffEn;
	}

	public String getBuybidAddressOnEn() {
		return this.buybidAddressOnEn;
	}

	public void setBuybidAddressOnEn(String buybidAddressOnEn) {
		this.buybidAddressOnEn = buybidAddressOnEn;
	}

	public String getEbiddingDomain() {
		return this.ebiddingDomain;
	}

	public void setEbiddingDomain(String ebiddingDomain) {
		this.ebiddingDomain = ebiddingDomain;
	}

	public String getEbiddingDomainPara() {
		return this.ebiddingDomainPara;
	}

	public void setEbiddingDomainPara(String ebiddingDomainPara) {
		this.ebiddingDomainPara = ebiddingDomainPara;
	}

	public String getPbRptHandleType() {
		return this.pbRptHandleType;
	}

	public void setPbRptHandleType(String pbRptHandleType) {
		this.pbRptHandleType = pbRptHandleType;
	}

	public String getDeviceManualRate() {
		return this.deviceManualRate;
	}

	public void setDeviceManualRate(String deviceManualRate) {
		this.deviceManualRate = deviceManualRate;
	}

	public String getDeviceDirection() {
		return this.deviceDirection;
	}

	public void setDeviceDirection(String deviceDirection) {
		this.deviceDirection = deviceDirection;
	}

	public String getDeviceSaleService() {
		return this.deviceSaleService;
	}

	public void setDeviceSaleService(String deviceSaleService) {
		this.deviceSaleService = deviceSaleService;
	}

	public String getDeviceMainCost() {
		return this.deviceMainCost;
	}

	public void setDeviceMainCost(String deviceMainCost) {
		this.deviceMainCost = deviceMainCost;
	}

	public String getBailForm() {
		return this.bailForm;
	}

	public void setBailForm(String bailForm) {
		this.bailForm = bailForm;
	}

	public String getTenderCurAbroad() {
		return this.tenderCurAbroad;
	}

	public void setTenderCurAbroad(String tenderCurAbroad) {
		this.tenderCurAbroad = tenderCurAbroad;
	}

	public String getDeviceManualRateEn() {
		return this.deviceManualRateEn;
	}

	public void setDeviceManualRateEn(String deviceManualRateEn) {
		this.deviceManualRateEn = deviceManualRateEn;
	}

	public String getDeviceDirectionEn() {
		return this.deviceDirectionEn;
	}

	public void setDeviceDirectionEn(String deviceDirectionEn) {
		this.deviceDirectionEn = deviceDirectionEn;
	}

	public String getDeviceSaleServiceEn() {
		return this.deviceSaleServiceEn;
	}

	public void setDeviceSaleServiceEn(String deviceSaleServiceEn) {
		this.deviceSaleServiceEn = deviceSaleServiceEn;
	}

	public String getDeviceMainCostEn() {
		return this.deviceMainCostEn;
	}

	public void setDeviceMainCostEn(String deviceMainCostEn) {
		this.deviceMainCostEn = deviceMainCostEn;
	}

	public String getBailFormEn() {
		return this.bailFormEn;
	}

	public void setBailFormEn(String bailFormEn) {
		this.bailFormEn = bailFormEn;
	}

	public String getTenderCurAbroadEn() {
		return this.tenderCurAbroadEn;
	}

	public void setTenderCurAbroadEn(String tenderCurAbroadEn) {
		this.tenderCurAbroadEn = tenderCurAbroadEn;
	}

	public Long getLdlEvaMetionAgencyCpId() {
		return this.ldlEvaMetionAgencyCpId;
	}

	public void setLdlEvaMetionAgencyCpId(Long ldlEvaMetionAgencyCpId) {
		this.ldlEvaMetionAgencyCpId = ldlEvaMetionAgencyCpId;
	}

	public String getLinkBuybidMobile() {
		return this.linkBuybidMobile;
	}

	public void setLinkBuybidMobile(String linkBuybidMobile) {
		this.linkBuybidMobile = linkBuybidMobile;
	}

	public String getModelProjectMgr() {
		return this.modelProjectMgr;
	}

	public void setModelProjectMgr(String modelProjectMgr) {
		this.modelProjectMgr = modelProjectMgr;
	}

	public String getModelConsignContract() {
		return this.modelConsignContract;
	}

	public void setModelConsignContract(String modelConsignContract) {
		this.modelConsignContract = modelConsignContract;
	}

	public String getModelEvaBid() {
		return this.modelEvaBid;
	}

	public void setModelEvaBid(String modelEvaBid) {
		this.modelEvaBid = modelEvaBid;
	}

	public String getModelPreQual() {
		return this.modelPreQual;
	}

	public void setModelPreQual(String modelPreQual) {
		this.modelPreQual = modelPreQual;
	}

	public Long getCommittDaysBeforeOpen() {
		return this.committDaysBeforeOpen;
	}

	public void setCommittDaysBeforeOpen(Long committDaysBeforeOpen) {
		this.committDaysBeforeOpen = committDaysBeforeOpen;
	}

	public String getModelSendMail() {
		return this.modelSendMail;
	}

	public void setModelSendMail(String modelSendMail) {
		this.modelSendMail = modelSendMail;
	}

	public String getFileBankAccountName() {
		return this.fileBankAccountName;
	}

	public void setFileBankAccountName(String fileBankAccountName) {
		this.fileBankAccountName = fileBankAccountName;
	}

	public String getFileBankName() {
		return this.fileBankName;
	}

	public void setFileBankName(String fileBankName) {
		this.fileBankName = fileBankName;
	}

	public String getFileBankAccount() {
		return this.fileBankAccount;
	}

	public void setFileBankAccount(String fileBankAccount) {
		this.fileBankAccount = fileBankAccount;
	}

	public String getTenderFeeAccountNameUs() {
		return this.tenderFeeAccountNameUs;
	}

	public void setTenderFeeAccountNameUs(String tenderFeeAccountNameUs) {
		this.tenderFeeAccountNameUs = tenderFeeAccountNameUs;
	}

	public String getTenderFeeBankNameUs() {
		return this.tenderFeeBankNameUs;
	}

	public void setTenderFeeBankNameUs(String tenderFeeBankNameUs) {
		this.tenderFeeBankNameUs = tenderFeeBankNameUs;
	}

	public String getTenderFeeAccountUs() {
		return this.tenderFeeAccountUs;
	}

	public void setTenderFeeAccountUs(String tenderFeeAccountUs) {
		this.tenderFeeAccountUs = tenderFeeAccountUs;
	}

	public String getTenderFeeSwiftUs() {
		return this.tenderFeeSwiftUs;
	}

	public void setTenderFeeSwiftUs(String tenderFeeSwiftUs) {
		this.tenderFeeSwiftUs = tenderFeeSwiftUs;
	}

	public String getTenderFeeAccountNameUsEn() {
		return this.tenderFeeAccountNameUsEn;
	}

	public void setTenderFeeAccountNameUsEn(String tenderFeeAccountNameUsEn) {
		this.tenderFeeAccountNameUsEn = tenderFeeAccountNameUsEn;
	}

	public String getTenderFeeBankNameUsEn() {
		return this.tenderFeeBankNameUsEn;
	}

	public void setTenderFeeBankNameUsEn(String tenderFeeBankNameUsEn) {
		this.tenderFeeBankNameUsEn = tenderFeeBankNameUsEn;
	}

	public String getTenderFeeAccountUsEn() {
		return this.tenderFeeAccountUsEn;
	}

	public void setTenderFeeAccountUsEn(String tenderFeeAccountUsEn) {
		this.tenderFeeAccountUsEn = tenderFeeAccountUsEn;
	}

	public String getTenderFeeSwiftUsEn() {
		return this.tenderFeeSwiftUsEn;
	}

	public void setTenderFeeSwiftUsEn(String tenderFeeSwiftUsEn) {
		this.tenderFeeSwiftUsEn = tenderFeeSwiftUsEn;
	}

	public String getBailBankAccountName() {
		return this.bailBankAccountName;
	}

	public void setBailBankAccountName(String bailBankAccountName) {
		this.bailBankAccountName = bailBankAccountName;
	}

	public String getBailBankName() {
		return this.bailBankName;
	}

	public void setBailBankName(String bailBankName) {
		this.bailBankName = bailBankName;
	}

	public String getBailBankAccount() {
		return this.bailBankAccount;
	}

	public void setBailBankAccount(String bailBankAccount) {
		this.bailBankAccount = bailBankAccount;
	}

	public String getBailFeeAccountNameUs() {
		return this.bailFeeAccountNameUs;
	}

	public void setBailFeeAccountNameUs(String bailFeeAccountNameUs) {
		this.bailFeeAccountNameUs = bailFeeAccountNameUs;
	}

	public String getBailFeeBankNameUs() {
		return this.bailFeeBankNameUs;
	}

	public void setBailFeeBankNameUs(String bailFeeBankNameUs) {
		this.bailFeeBankNameUs = bailFeeBankNameUs;
	}

	public String getBailFeeAccountUs() {
		return this.bailFeeAccountUs;
	}

	public void setBailFeeAccountUs(String bailFeeAccountUs) {
		this.bailFeeAccountUs = bailFeeAccountUs;
	}

	public String getBailFeeSwiftUs() {
		return this.bailFeeSwiftUs;
	}

	public void setBailFeeSwiftUs(String bailFeeSwiftUs) {
		this.bailFeeSwiftUs = bailFeeSwiftUs;
	}

	public String getBailFeeAccountNameUsEn() {
		return this.bailFeeAccountNameUsEn;
	}

	public void setBailFeeAccountNameUsEn(String bailFeeAccountNameUsEn) {
		this.bailFeeAccountNameUsEn = bailFeeAccountNameUsEn;
	}

	public String getBailFeeBankNameUsEn() {
		return this.bailFeeBankNameUsEn;
	}

	public void setBailFeeBankNameUsEn(String bailFeeBankNameUsEn) {
		this.bailFeeBankNameUsEn = bailFeeBankNameUsEn;
	}

	public String getBailFeeAccountUsEn() {
		return this.bailFeeAccountUsEn;
	}

	public void setBailFeeAccountUsEn(String bailFeeAccountUsEn) {
		this.bailFeeAccountUsEn = bailFeeAccountUsEn;
	}

	public String getBailFeeSwiftUsEn() {
		return this.bailFeeSwiftUsEn;
	}

	public void setBailFeeSwiftUsEn(String bailFeeSwiftUsEn) {
		this.bailFeeSwiftUsEn = bailFeeSwiftUsEn;
	}

}
