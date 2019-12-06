package com.cold.framework.dao.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "B_MERCH_AUD")
public class BMerchAud {
    /**
     * uuid
     */
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 审核组标识
     */
    @Column(name = "AUDIT_CODE")
    private String auditCode;

    /**
     * 申请人编码
     */
    @Column(name = "PROPOSER_CODE")
    private String proposerCode;

    /**
     * 申请人名称
     */
    @Column(name = "PROPOSER_NAME")
    private String proposerName;

    /**
     * 申请时间
     */
    @Column(name = "PROPOSER_TIME")
    private Date proposerTime;

    /**
     * 审核人编码
     */
    @Column(name = "AUDITOR_CODE")
    private String auditorCode;

    /**
     * 审核人名称
     */
    @Column(name = "AUDITOR_NAME")
    private String auditorName;

    /**
     * 审核时间
     */
    @Column(name = "AUDITOR_TIME")
    private Date auditorTime;

    /**
     * 审核操作：0:已提交，1:审核通过，2:已驳回，3:账户未同步,a:合同签约，b:驳回签约，c:商户签章
     */
    @Column(name = "AUDIT_OPERATION")
    private String auditOperation;

    /**
     * 审核意见
     */
    @Column(name = "AUDIT_OPINION")
    private String auditOpinion;

    /**
     * 商户标识
     */
    @Column(name = "USER_CODE")
    private String userCode;

    /**
     * 商户名称
     */
    @Column(name = "MERCH_NAME")
    private String merchName;

    /**
     * 营业名称
     */
    @Column(name = "MERCH_SNAME")
    private String merchSname;

    /**
     * 商户英文名称
     */
    @Column(name = "MERCH_ENAME")
    private String merchEname;

    /**
     * 签购单打印特约商户名称
     */
    @Column(name = "MERCH_PNAME")
    private String merchPname;

    /**
     * 商户类别码（即：MCC）
     */
    @Column(name = "MCC")
    private String mcc;

    /**
     * 商户类别码名称
     */
    @Column(name = "MCCNAME")
    private String mccname;

    /**
     * 所属代理商
     */
    @Column(name = "AGENT_ID")
    private String agentId;

    /**
     * 所属代理商名称
     */
    @Column(name = "AGENT_NAME")
    private String agentName;

    /**
     * 商户风险等级：0:优质，1:正常，2:有一定风险，3:高风险
     */
    @Column(name = "MERCH_LEVEL")
    private String merchLevel;

    /**
     * 是否资质核实：0:否，1:是
     */
    @Column(name = "VERIFY_FLAG")
    private String verifyFlag;

    /**
     * 商户类型: 0:标准，1:优惠；2:减免
     */
    @Column(name = "MERCH_TYPE")
    private String merchType;

    /**
     * 是否在银联报备：0:无，1:有
     */
    @Column(name = "CUPS_FLAG")
    private String cupsFlag;

    /**
     * 商户接入类型：Z：直连，J：间联
     */
    @Column(name = "CONN_TYPE")
    private String connType;

    /**
     * 商户拓展方式：1:自主拓展，2:委托外包
     */
    @Column(name = "EXPAND_TYPE")
    private String expandType;

    /**
     * 营业执照编号
     */
    @Column(name = "LICENSE_NO")
    private String licenseNo;

    /**
     * 商户注册地址
     */
    @Column(name = "LICENSE_ADDR")
    private String licenseAddr;

    /**
     * 组织机构代码
     */
    @Column(name = "GROUP_CODE")
    private String groupCode;

    /**
     * 法定代表人姓名
     */
    @Column(name = "LEGAL_NAME")
    private String legalName;

    /**
     * 法定代表人证件类型：01:身份证，02:军官证，03:护照，04:港澳居民来往内地通行证(回乡证),05:台湾同胞来往内地通行证,06:警官证,07:士兵证,08:户口薄，09:临时身份证，10:外国人居留证，00:其他证件
     */
    @Column(name = "LEGAL_CERT_TYPE")
    private String legalCertType;

    /**
     * 法定代表人证件号
     */
    @Column(name = "LEGAL_CERT_NO")
    private String legalCertNo;

    /**
     * 商户经营地址
     */
    @Column(name = "MERCH_ADDR")
    private String merchAddr;

    /**
     * 税务登记证号
     */
    @Column(name = "TAX_CODE")
    private String taxCode;

    /**
     * 联系人
     */
    @Column(name = "CONTACTS")
    private String contacts;

    /**
     * 联系人证件类型
     */
    @Column(name = "CONTACTS_CERT_TYPE")
    private String contactsCertType;

    /**
     * 联系人证件号
     */
    @Column(name = "CONTACTS_CERT_NO")
    private String contactsCertNo;

    /**
     * 联系人证件发证机关
     */
    @Column(name = "ISSUING_AUTHO")
    private String issuingAutho;

    /**
     * 联系人证件有效期(开始)
     */
    @Column(name = "CERT_START_DATE")
    private String certStartDate;

    /**
     * 联系人证件有效期(结束)
     */
    @Column(name = "CERT_END_DATE")
    private String certEndDate;

    /**
     * 联系人证件地址
     */
    @Column(name = "CERT_ADDR")
    private String certAddr;

    /**
     * 联系人邮编
     */
    @Column(name = "ZIP_CODE")
    private String zipCode;

    /**
     * 联系人电话
     */
    @Column(name = "TEL")
    private String tel;

    /**
     * 联系人移动电话
     */
    @Column(name = "MOBILE")
    private String mobile;

    /**
     * 邮件
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 联系人地址
     */
    @Column(name = "ADDR")
    private String addr;

    /**
     * 经营范围
     */
    @Column(name = "BUSI_SCOPE")
    private String busiScope;

    /**
     * 地区码(省)
     */
    @Column(name = "PROVINCE_CODE")
    private String provinceCode;

    /**
     * 地区码(省)名称
     */
    @Column(name = "PROVINCE_NAME")
    private String provinceName;

    /**
     * 地区码(市)
     */
    @Column(name = "CITY_CODE")
    private String cityCode;

    /**
     * 地区码(市)名称
     */
    @Column(name = "CITY_NAME")
    private String cityName;

    /**
     * 地区码(区/县)
     */
    @Column(name = "COUNTY_CODE")
    private String countyCode;

    /**
     * 地区码(区/县)名称
     */
    @Column(name = "COUNTY_NAME")
    private String countyName;

    /**
     * 合同编号
     */
    @Column(name = "CONTRACT_ID")
    private String contractId;

    /**
     * 签约时间
     */
    @Column(name = "OPEN_DATE")
    private String openDate;

    /**
     * 合同到期日
     */
    @Column(name = "EXPIRE_DATE")
    private String expireDate;

    /**
     * 清算类型：0:平台清算，1:手工提现，2:无需清算
     */
    @Column(name = "SETTLE_TYPE")
    private String settleType;

    /**
     * 结算方式：0:日结，1:周结，2:月结
     */
    @Column(name = "SETTLE_MODE")
    private String settleMode;

    /**
     * 结算周期(保留字段，不使用)：当settle_type为0时(日结)：0-23；为1时(周结):1-7；为2时(月结):1-31
     */
    @Column(name = "SETTLE_DATE")
    private String settleDate;

    /**
     * 账户性质:S:对私，G:对公
     */
    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    /**
     * 账户账号
     */
    @Column(name = "ACCOUNT_ID")
    private String accountId;

    /**
     * 账户户名
     */
    @Column(name = "ACCOUNT_NAME")
    private String accountName;

    /**
     * 开户行行号
     */
    @Column(name = "BANK_CODE")
    private String bankCode;

    /**
     * 开户行名称
     */
    @Column(name = "BANK_NAME")
    private String bankName;

    /**
     * 信用卡手续费费率数值，取值范围0-100，单位：%
     */
    @Column(name = "C_FEE_RATE")
    private BigDecimal cFeeRate;

    /**
     * 信用卡手续费封顶值，0表示无封顶
     */
    @Column(name = "C_FEE_MAX")
    private BigDecimal cFeeMax;

    /**
     * 信用卡手续费保底值(保留)
     */
    @Column(name = "C_FEE_MIN")
    private BigDecimal cFeeMin;

    /**
     * 借记卡标准类手续费费率数值，取值范围0-100，单位：%
     */
    @Column(name = "D_FEE_RATE")
    private BigDecimal dFeeRate;

    /**
     * 借记卡标准类手续费封顶值，0表示无封顶
     */
    @Column(name = "D_FEE_MAX")
    private BigDecimal dFeeMax;

    /**
     * 借记卡标准类手续费保底值(保留)
     */
    @Column(name = "D_FEE_MIN")
    private BigDecimal dFeeMin;

    /**
     * 微信手续费费率数值，取值范围0-100，单位：%
     */
    @Column(name = "WX_FEE_RATE")
    private BigDecimal wxFeeRate;

    /**
     * 微信手续费封顶值，0表示无封顶
     */
    @Column(name = "WX_FEE_MAX")
    private BigDecimal wxFeeMax;

    /**
     * 微信手续费保底值(保留)
     */
    @Column(name = "WX_FEE_MIN")
    private BigDecimal wxFeeMin;

    /**
     * 支付宝手续费费率数值，取值范围0-100，单位：%
     */
    @Column(name = "BB_FEE_RATE")
    private BigDecimal bbFeeRate;

    /**
     * 支付宝手续费封顶值，0表示无封顶
     */
    @Column(name = "BB_FEE_MAX")
    private BigDecimal bbFeeMax;

    /**
     * 支付宝手续费保底值(保留)
     */
    @Column(name = "BB_FEE_MIN")
    private BigDecimal bbFeeMin;

    /**
     * D0手续费费率数值
     */
    @Column(name = "D0_FEE_RATE")
    private BigDecimal d0FeeRate;

    /**
     * 住所
     */
    @Column(name = "RESIDENCE")
    private String residence;

    /**
     * 法人移动电话
     */
    @Column(name = "LEGAL_MOBILE")
    private String legalMobile;

    /**
     * D0单笔提现，单位：元
     */
    @Column(name = "D0_SINGLE_CASH_DRAWAL")
    private BigDecimal d0SingleCashDrawal;

    /**
     * 账户身份证号
     */
    @Column(name = "ID_CARD")
    private String idCard;

    /**
     * 账户预留手机号
     */
    @Column(name = "ACCOUNT_MOBILE")
    private String accountMobile;

    /**
     * 发送短信次数
     */
    @Column(name = "SEND_CNT")
    private String sendCnt;

    /**
     * 发送短信最后时间
     */
    @Column(name = "SEND_TIME")
    private Date sendTime;

    /**
     * 云闪付信用卡双免费率
     */
    @Column(name = "YC_FREE_FEE_RATE")
    private BigDecimal ycFreeFeeRate;

    /**
     * 云闪付借记卡双免费率
     */
    @Column(name = "YD_FREE_FEE_RATE")
    private BigDecimal ydFreeFeeRate;

    /**
     * 云闪付信用卡二维码费率(保留)
     */
    @Column(name = "YC_QRCODE_FEE_RATE")
    private BigDecimal ycQrcodeFeeRate;

    /**
     * 云闪付借记卡二维码费率(保留)
     */
    @Column(name = "YD_QRCODE_FEE_RATE")
    private BigDecimal ydQrcodeFeeRate;

    /**
     * 云闪付信用卡NFC费率(保留)
     */
    @Column(name = "YC_NFC_FEE_RATE")
    private BigDecimal ycNfcFeeRate;

    /**
     * 云闪付借记卡NFC费率(保留)
     */
    @Column(name = "YD_NFC_FEE_RATE")
    private BigDecimal ydNfcFeeRate;

    /**
     * 法人姓名密文
     */
    @Column(name = "LEGAL_NAME_ENCRYPT")
    private String legalNameEncrypt;

    /**
     * 法人证件号密文
     */
    @Column(name = "LEGAL_CERT_NO_ENCRYPT")
    private String legalCertNoEncrypt;

    /**
     * 法人移动电话密文
     */
    @Column(name = "LEGAL_MOBILE_ENCRYPT")
    private String legalMobileEncrypt;

    /**
     * 联系人密文
     */
    @Column(name = "CONTACTS_ENCRYPT")
    private String contactsEncrypt;

    /**
     * 联系人证件号密文
     */
    @Column(name = "CONTACTS_CERT_NO_ENCRYPT")
    private String contactsCertNoEncrypt;

    /**
     * 联系人电话密文
     */
    @Column(name = "TEL_ENCRYPT")
    private String telEncrypt;

    /**
     * 联系人移动电话密文
     */
    @Column(name = "MOBILE_ENCRYPT")
    private String mobileEncrypt;

    /**
     * 账户户名密文
     */
    @Column(name = "ACCOUNT_NAME_ENCRYPT")
    private String accountNameEncrypt;

    /**
     * 账户账号密文
     */
    @Column(name = "ACCOUNT_ID_ENCRYPT")
    private String accountIdEncrypt;

    /**
     * 账户预留电话密文
     */
    @Column(name = "ACCOUNT_MOBILE_ENCRYPT")
    private String accountMobileEncrypt;

    /**
     * 账户身份证号密文
     */
    @Column(name = "ID_CARD_ENCRYPT")
    private String idCardEncrypt;

    /**
     * 秘钥名称
     */
    @Column(name = "KEY_NAME")
    private String keyName;

    /**
     * 商户评级
     */
    @Column(name = "GRADE_MERCH_LEVEL")
    private String gradeMerchLevel;

    /**
     * 是否已鉴权  0：未鉴权  1：已鉴权
     */
    @Column(name = "AUTHENTICATION")
    private String authentication;

    /**
     * 1:手机入网2:PC入网
     */
    @Column(name = "CHANNEL_JOIN")
    private String channelJoin;

    /**
     * D0类型  0：D0、1：特殊D0
     */
    @Column(name = "D0_TYPE")
    private String d0Type;

    /**
     * 借记卡快捷无卡费率
     */
    @Column(name = "DQ_FEE_RATE")
    private BigDecimal dqFeeRate;

    /**
     * 贷记卡快捷无卡费率
     */
    @Column(name = "CQ_FEE_RATE")
    private BigDecimal cqFeeRate;

    /**
     * EPOS手续费费率
     */
    @Column(name = "E_D0_FEE_RATE")
    private BigDecimal eD0FeeRate;

    /**
     * EPOS单笔提现手续费
     */
    @Column(name = "E_D0_SINGLE_CASH_DRAWAL")
    private BigDecimal eD0SingleCashDrawal;

    /**
     * 法人证件号有效期开始时间
     */
    @Column(name = "LEGAL_CERTNO_START_TIME")
    private String legalCertnoStartTime;

    /**
     * 法人证件号有效期结束时间
     */
    @Column(name = "LEGAL_CERTNO_END_TIME")
    private String legalCertnoEndTime;

    /**
     * 营业执照有效期开始时间
     */
    @Column(name = "LICENSE_START_TIME")
    private String licenseStartTime;

    /**
     * 营业执照有效期结束时间
     */
    @Column(name = "LICENSE_END_TIME")
    private String licenseEndTime;

    /**
     * 结算人证件号有效期开始时间
     */
    @Column(name = "ACCOUNT_IDCARD_START_TIME")
    private String accountIdcardStartTime;

    /**
     * 结算人证件号有效期结束时间
     */
    @Column(name = "ACCOUNT_IDCARD_END_TIME")
    private String accountIdcardEndTime;

    /**
     * 联系人国籍
     */
    @Column(name = "CONTACTS_CITIZENSHIP")
    private String contactsCitizenship;

    /**
     * 职业
     */
    @Column(name = "CONTACTS_PROFESSION")
    private String contactsProfession;

    /**
     * 联系人性别(0女,1男)
     */
    @Column(name = "CONTACTS_GENDER")
    private String contactsGender;

    /**
     * 以JSON串形式记录变化前内容
     */
    @Column(name = "ORIGINAL_CONTENT")
    private String originalContent;

    /**
     * 注册资金
     */
    @Column(name = "REGISTER_CAPITAL")
    private String registerCapital;
}