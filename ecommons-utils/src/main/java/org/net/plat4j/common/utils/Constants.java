package org.net.plat4j.common.utils;

public class Constants {
	/**
	 * 平台id
	 */
	public static final Long PLATFORM_ID = 0L;
	//平台注册公司标志(sys_company_agency)
	public static final String Plat_Company_Id = "0";
	
	public static final String No = "0";//否
	public static final String Yes = "1";//是
	public static final String Is_Deleted_Saved = "-1";//数据暂存
	public static final String Is_Deleted_No = "0";
	public static final String Is_Deleted_Yes = "1";
	public static final String Is_STATUS_0="0";//禁用//待审批//保存
	public static final String Is_STATUS_1="1";//生效//发布
	public static final String Is_STATUS_2="2";//已保存//注册驳回
	public static final String Is_STATUS_3="3";//黑名单/失效了
	public static final String Is_STATUS_4="0";//已申请
	public static final String Is_STATUS_5="1";//已通过
	public static final String Is_STATUS_6="2";//已驳回
	
	//招标者信息（bid_bidder）
	public static final String Signup_Flag_Already="0";//已报名
	public static final String Signup_Flag_Accepted="1";//接受
	public static final String Signup_Flag_Rejected="2";//驳回
	
	public static final String Qual_Flag_Pending="0";//待审核
	public static final String Qual_Flag_Passed="1";//已通过
	public static final String Qual_Flag_Notpassed="2";//不通过
	public static final String Qual_Flag_Disqualification="3";//无资格预审
	
	public static final String Send_Flag_No = "0";//未投标
	public static final String Send_Flag_Yes = "1";//已投标
	
	/** 投标状态(BTB_TENDER) **/
	public static final String Wating_TenderFile  = "110";//已购标，待提交投标文件
	public static final String Withdraw_TenderFile  = "120";//已撤销投标文件
	public static final String Send_TenderFile = "210";//递交投标文件成功
	public static final String Wating_Second_TenderFile = "115";//待提交第二步投标文件
	
	public static final String Buy_Flag_No = "0";//未购标
	public static final String Buy_Flag_Yes = "1";//已购标
	
	//报名信息（btb_signup）
	public static final String Signup_Status_Saved="0";//已报名
	public static final String Signup_Status_Accepted="1";//接受
	public static final String Signup_Status_Rejected="2";//驳回
	
	public static final String Signup_Type_Independent="0";//自主报名
	public static final String Signup_Type_Agent ="1";//代为录入
	
	//公司信息（sys_company）
	public static final String Company_Type_Bidder="2";//投标人
	public static final String Company_Type_Tenderee="4";//招标人
	public static final String Company_Type_Agency="1";//代理机构
	
	//开标详细（bkb_opening_detail）
	public static final String Status_Not_Opening="0";//未开标
	public static final String Status_Yes_Opening="1";//已开标
	
	//标段（包）信息（bib_package） update by jiangxw at 2012-12-21
	public static final String Package_Status_Project_Rejected="1013";//项目审批驳回
	public static final String Package_Status_Bidplan_Saved="1110";//招标计划已保存
	public static final String Package_Status_Bidplan_Finished="1120";//招标计划已完成
	public static final String Package_Status2_Bidplan_Finished="3001";//招标计划已完成（标段（包）状态2）
	public static final String Package_Status_Signuping="3101";//报名中
	public static final String Package_Status_Signuped="3102";//报名已完成
	public static final String Package_Status_Prequaling="2018";//资格预审中
	public static final String Package_Status_Prequaled="2020";//资格预审已完成
	public static final String Package_Status_Expl_Saved="4510";//踏勘信息已保存（标段（包）状态2）3900
	public static final String Package_Status_Expl_Published="3901";//踏勘信息已发布（标段（包）状态2）
	public static final String Package_Status_Expl_Finished="4580";//踏勘完成（标段（包）状态2）3902
	public static final String Package_Status_Expl_Phase="39__";//踏勘信息阶段（PACKAGE_STATUS_GROUP）
	public static final String Package_Status_Bid_Opening="6010";//开标中
	public static final String Package_Status_Bid_Opened="6020";//开标结束
	
	//保证金状态(btb_fee_bail) update by jiangxw at 2013-01-31
	public static final String Fee_Bail_Status_Saved="00";//已保存
	public static final String Fee_Bail_Status_Submited="10";//已保存
	public static final String Fee_Bail_Status_PayedMargin="20";//已退保证金
	public static final String Fee_Bail_Status_Receivedcommission="30";//已收取佣金
	
	//收（付）款人联系单“类型(fee_bill_receive) update by jiangxw at 2013-03-27
	public static final String payMent_Type_FeeBail="8";//投标保证金
	public static final String payMent_Type_BidService="1";//投标服务费
	public static final String payMent_Type_TradingService="5";//交易服务费
	
	//收款人联系单“状态(fee_bill_receive) add by jiangxw at 2013-01-31
	public static final String Receive_Status_Saved="10";//已保存
	
	//保证金收取方式（bib_package） add by jiangxw at 2013-01-31
	public static final String FeeBail_ReceiveType_OwnOrg="1";//招标机构收取
	public static final String FeeBail_ReceiveType_OtherOrg="2";//其他机构收取
	
	//国际标（bib_main） add by jiangxw at 2013-02-20
	public static final String Project_Prop_International = "1";//国际标
	
	//中标服务费状态(btb_fee_bail)
	public static final String Fee_Service_Status_Receipted="20";//已生成收款联系单
		
	//电子支付业务类型(bid_pay_order)
	public static final String Business_Type_Earnest = "01";//投标保证金
	public static final String Business_Type_Consign = "02";//购标费
	public static final String Business_Type_Drawings = "03";//图纸资料押金
	
	//电子支付状态(bid_pay_order)
	public static final String Pay_Order_Status_No_Finish = "0";
	public static final String Pay_Order_Status_Finish = "1";
	
	//投标文件类型(btb_tender_file) add by jiangxw at 2013-05-22
	public static final String Price_Tender_File = "00";//价格部分
	public static final String Other_Price_Tender_File = "01";//价格部分其他附件
	public static final String Business_Tender_File = "10";//商务部分
	public static final String Business2_Tender_File = "30";//商务部分
	public static final String Other_Business_Tender_File = "11";//商务部分其他附件
	public static final String Technology_Tender_File = "20";//技术部分
	public static final String Other_Technology_Tender_File = "21";//技术部分其他附件
	
	//回执文件类型(BTB_RECEIPTION_FILE) add by jiangxw at 2013-05-28
	public static final String Send_Receiption = "10";//递交回执
	public static final String Withdraw_Receiption = "20";//撤销回执
	
	//中落标情况以及状态(v_bidder_info)
	public static final String statusDs0="未编辑";
	public static final String statusDs1="已保存";
	public static final String statusDs2="已完成";
	public static final String awardFlagDs0="落标";
	public static final String awardFlagDs1="中标";
	
	//专家所在公司
	public static final Long companyId = (long) 0;

	//购标(bfb_buybid_order)
	public static final String payType_wszf = "50"; //网上支付
	public static final String forwardType = "1"; //跳转类型
	public static final String businessType = "02"; //业务类型id
	public static final String backStatus = "10"; //返回status
	public static final Integer is_save = 1; //返回status
	public static final Integer is_submit = 2; //返回status
	public static final String sp_status = "30"; //审批status

	public static final String Bfb_Bf_Paper_Main_Status_Invalid = "0";//按版本主表状态 0-失效
	public static final String Bfb_Bf_Paper_Main_Status_Submit = "1";//按版本主表状态 1-有效
	public static final String Bfb_Bf_Paper_Main_Status_Save = "2";//按版本主表状态 2-保存
	
	public static final String Bid_Main_Bif_File_Model_Upload = "0";//招标文件管理模式，0--直接上传
	public static final String Bid_Main_Bif_File_Model_Help = "1";//招标文件管理模式，1--辅助生成
	
	public static final String  Bid_File_Upload_Flag_1 = "1"; //此标段（包）状态不能上传
	public static final String  Bid_File_Upload_Flag_2 = "2";//投标文件不是最新
	public static final String  Bid_File_Upload_Flag_3 = "3";//上传成功
	public static final String  Bid_File_Upload_Flag_4 = "4";//操作错误
	
	public static final String ROOM_STATUS_1 = "10";//待使用状态
	public static final String ROOM_STATUS_2 = "20";//已使用完
	public static final String ROOM_STATUS_3 = "30";//已撤销 
	
	public static final String INVALID = "0"; //是否有效标记 0 无效 1 有效
	public static final String VALID = "1";   //是否有效标记 0 无效 1 有效
	
	//变更确认状态
	public static final String IS_CONFIRMED="1";//已确认
	public static final String IS_NOT_CONFIRMED="0";//未确认
	
	//证件/印章 状态
	public static final String Is_OASTATUS_0="0";// 保存
	public static final String Is_OASTATUS_1="1";// 生效
	public static final String Is_OASTATUS_2="2";// 失效
	public static final String Is_OASBORR="0";
	public static final String Is_OASBORR_10="10";//待借用
	public static final String Is_OASBORR_20="20";//申请中
	public static final String Is_OASBORR_30="30";//已借用
	
	//是否开通手机、邮箱登录标志位
	public static final String IS_EMAIL_LOGIN_YES="1";//开通
	public static final String IS_EMAIL_LOGIN_NO="0";//关闭
	public static final String IS_MOBILE_LOGIN_YES="1";//开通
	public static final String IS_MOBILE_LOGIN_NO="0";//关闭
	public static final String IS_ACCOUNT_ACTIVE_YES="1";//已激活
	public static final String IS_ACCOUNT_ACTIVE_NO="0";//未激活
	public static final String IS_ACCOUNT_LOCKED="2";//已锁定
	
	/**委托招标-10*/
	public static final String BID_ORGANIZATION_WAY_10 = "10";
	/**自行招标-20*/
	public static final String BID_ORGANIZATION_WAY_20 = "20";
	/**网上售标*/
	public static final String IS_SELL_BID_ONLINE_1 = "1";
	/**线下售标*/
	public static final String IS_SELL_BID_ONLINE_0 = "0";
	/**资格预审*/
	public static final String QUAL_MODE_10 = "10";
	/**资格后审*/
	public static final String QUAL_MODE_20 = "10";
	
	/**
	 * 公司类型常量及其相关方法。<br/>
	 * <br/>
	 * @author yujie
	 */
	public static class CompanyType {
		/**
		 * 代理机构。 1
		 */
		public static final int AGENCY = 0x01 << 0;
		/**
		 * 投标人。2
		 */
		public static final int SUPPLIER = 0x01 << 1;
		/**
		 * 招标人。4
		 */
		public static final int TENDEREE = 0x01 << 2;
		/**
		 * 专家。 8
		 */
		public static final int EXPERT = 0x01 << 3;
		/**
		 * 监标人。16
		 */
		public static final int SUPERVISOR = 0x01 << 4;
		
		/**
		 *系统管理员  32
		 */
		public static final int ADMIN=0X01<<5;
		/**
		 *无身份的个人用户  64
		 */
		public static final int SELFUSER=0X01<<6;
		/**
		 *无身份的个人企业 128
		 */
		public static final int CORPUSER=0X01<<7;
		
		/**
		 * 组合多个公司类型为一个类型。
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types 要组合的公司类型，可以为多个参数，也可以为一个数组。
		 * @return 组合后的公司类型。
		 */
		public static  String join(String... types) {
			int ret = 0;
			for(String type : types) {
				int val = Integer.parseInt(type);
				ret += val;
			}
			return String.valueOf(ret);
		}
		/**
		 * 判断这个组合里面是否包含这个公司类型
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types 需要判断的公司类型 companyType。
		 * @param val 存在数据库中的组合的值
		 * @return 
		 */
		public static boolean containsType(String val, String type) {
			int valInt = Integer.parseInt(val);
			int typeInt = Integer.parseInt(type);
			return (valInt & typeInt) == typeInt;
		}
		/**
		 * 判断这个公司是否是代理机构
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types  需要判断的公司类型 companyType。
		 * @return 
		 */
		public static boolean isAgent(String type) {
			int typeInt = Integer.parseInt(type);
			return (typeInt & AGENCY) == AGENCY;
		}
		/**
		 * 判断这个公司是否是代理机构
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types  需要判断的公司类型 companyType。
		 * @return 
		 */
		public static boolean isAgent(int type) {
			return (type & AGENCY) == AGENCY;
		}
		/**
		 * 判断这个公司是否是投标人
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types  需要判断的公司类型 companyType。
		 * @return 
		 */
		public static boolean isSupplier(int type) {
			return (type & SUPPLIER) == SUPPLIER;
		}
		/**
		 * 判断这个公司是否是投标人
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types  需要判断的公司类型 companyType。
		 * @return 
		 */
		public static boolean isSupplier(String type) {
			int typeInt = Integer.parseInt(type);
			return (typeInt & SUPPLIER) == SUPPLIER;
		}
		/**
		 * 判断这个公司是否是招标人
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types  需要判断的公司类型 companyType。
		 * @return 
		 */
		public static boolean isTenderee(String type) {
			int typeInt = Integer.parseInt(type);
			return (typeInt & TENDEREE) == TENDEREE;
		}
		/**
		 * 判断这个公司是否是招标人
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types  需要判断的公司类型 companyType。
		 * @return 
		 */
		public static boolean isTenderee(int type) {
			return (type & TENDEREE) == TENDEREE;
		}
		/**
		 * 判断这个公司是否是专家
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types  需要判断的公司类型 companyType。
		 * @return 
		 */
		public static boolean isExpert(String type) {
			int typeInt = Integer.parseInt(type);
			return (typeInt & EXPERT) == EXPERT;
		}
		/**
		 * 判断这个公司是否是专家
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types  需要判断的公司类型 companyType。
		 * @return 
		 */
		public static boolean isExpert(int type) {
			return (type & EXPERT) == EXPERT;
		}
		/**
		 * 判断这个公司是否是监标人
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types  需要判断的公司类型 companyType。
		 * @return 
		 */
		public static boolean isSupervisor(String type) {
			int typeInt = Integer.parseInt(type);
			return (typeInt & SUPERVISOR) == SUPERVISOR;
		}
		/**
		 * 判断这个公司是否是监标人
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types  需要判断的公司类型 companyType。
		 * @return 
		 */
		public static boolean isSupervisor(int type) {
			return (type & SUPERVISOR) == SUPERVISOR;
		}
		
		/**
		 * 判断这个公司是否是管理员
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types  需要判断的公司类型 companyType。
		 * @return 
		 */
		public static boolean isAdmin(int type) {
			return (type & ADMIN) == ADMIN;
		}
		
		/**
		 * 判断这个公司是否是管理员
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types  需要判断的公司类型 companyType。
		 * @return 
		 */
		public static boolean isAdmin(String type) {
			int typeInt = Integer.parseInt(type);
			return (typeInt & ADMIN) == ADMIN;
		}
		/**
		 * 判断这个公司是否是无身份的个人用户
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types  需要判断的公司类型 companyType。
		 * @return 
		 */
		public static boolean isSelfUser(int type) {
			return (type & SELFUSER) == SELFUSER;
		}
		
		/**
		 * 判断这个公司是否是无身份的个人用户
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types  需要判断的公司类型 companyType。
		 * @return 
		 */
		public static boolean isSelfUser(String type) {
			int typeInt = Integer.parseInt(type);
			return (typeInt & SELFUSER) == SELFUSER;
		}
		/**
		 * 判断这个公司是否是无身份的企业用户
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types  需要判断的公司类型 companyType。
		 * @return 
		 */
		public static boolean isCorpUser(int type) {
			return (type & CORPUSER) == CORPUSER;
		}
		
		/**
		 * 判断这个公司是否是无身份的企业用户
		 * 
		 * <pre>
		 * Author: yujie
		 * Created Time: 2014-09-28 10:02:39
		 * </pre>
		 * 
		 * @param types  需要判断的公司类型 companyType。
		 * @return 
		 */
		public static boolean isCorpUser(String type) {
			int typeInt = Integer.parseInt(type);
			return (typeInt & CORPUSER) == CORPUSER;
		}
	}
}
