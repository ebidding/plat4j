package org.net.plat4j.common.config;

import java.util.Date;

/**
 * LdlBidAgency entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class LdlBidAgency extends AbstractLdlBidAgency implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public LdlBidAgency() {
	}

	/** minimal constructor */
	public LdlBidAgency(Long id, Long companyId) {
		super(id, companyId);
	}

	/** full constructor */
	public LdlBidAgency(Long id, Long companyId, String agencyCode,
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
		super(id, companyId, agencyCode, agencyName, linkBuybid, linkBuybidEn,
				linkBuybidPhone, linkBuybidFax, agencyAddress, agencyAddressEn,
				zipCode, netAddress, siteName, siteNameEn, servicePhone,
				serviceFax, swiftCode, priveCode, keyInfo, fbReceiveId,
				fbReceiveName, bfReceiveId, bfReceiveName, haveConsignMgr,
				bidMainMode, lastDelayBulletin, attr1, attr2, attr3, attr4,
				attr5, isDeleted, createTime, createUserId, updateTime,
				updateUserId, tenderOrgBank, tenderOrgBankEn, tenderOrgAccount,
				tenderOrgBankUs, tenderOrgBankUsEn, tenderOrgAccountUs,
				tenderOrgBankOu, tenderOrgBankOuEn, tenderOrgAccountOu,
				tenderOrgBankJp, tenderOrgBankJpEn, tenderOrgAccountJp,
				postFeeRmb, postFeeUsd, bullPublishMedia, dedicatedLanePic,
				buybidAddressOff, buybidAddressOn, buybidAddressOffEn,
				buybidAddressOnEn, ebiddingDomain, ebiddingDomainPara,
				pbRptHandleType, deviceManualRate, deviceDirection,
				deviceSaleService, deviceMainCost, bailForm, tenderCurAbroad,
				deviceManualRateEn, deviceDirectionEn, deviceSaleServiceEn,
				deviceMainCostEn, bailFormEn, tenderCurAbroadEn,
				ldlEvaMetionAgencyCpId, linkBuybidMobile, modelProjectMgr,
				modelConsignContract, modelEvaBid, modelPreQual,
				committDaysBeforeOpen, modelSendMail, fileBankAccountName,
				fileBankName, fileBankAccount, tenderFeeAccountNameUs,
				tenderFeeBankNameUs, tenderFeeAccountUs, tenderFeeSwiftUs,
				tenderFeeAccountNameUsEn, tenderFeeBankNameUsEn,
				tenderFeeAccountUsEn, tenderFeeSwiftUsEn, bailBankAccountName,
				bailBankName, bailBankAccount, bailFeeAccountNameUs,
				bailFeeBankNameUs, bailFeeAccountUs, bailFeeSwiftUs,
				bailFeeAccountNameUsEn, bailFeeBankNameUsEn,
				bailFeeAccountUsEn, bailFeeSwiftUsEn);
	}

}
