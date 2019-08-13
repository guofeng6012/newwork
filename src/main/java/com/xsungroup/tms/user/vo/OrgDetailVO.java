package com.xsungroup.tms.user.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author GF
 * @Date 2019-7-31 11:23:24
 * @Description
 **/
@Data
public class OrgDetailVO {

    private String orgName;

    /**
     * 企业角色
     */
    private Integer orgRole;

    /**
     * 审核状态 0:待审核  1:审核拒绝  2:审核通过
     */
    private Integer auditStatus;

    /**
     * '组织类型 0:公司 1:网点'
     */
    private Integer orgType;

    /**
     * 来源  0:注册 1:创建
     */
    private Integer orgSource;

    /**
     * 0:企业 1:个人货主
     */
    private Integer bizType;

    /**
     * 法人组织 0:未选中  1:选中
     */
    private Integer isLegal;

    /**
     * 核算组织 0:未选中  1:选中
     */
    private Integer isAccountant;

    /**
     * 运营组织 0:未选中  1:选中
     */
    private Integer isOperation;



    /**
     * 企业-税号
     */
    private String orgTaxNo;

    /**
     * 企业-地址
     */
    private String orgAddress;

    /**
     * 企业-开户行名称
     */
    private String orgBankName;

    /**
     * 企业-公司银行账号
     */
    private String orgBankAccountNo;

    /**
     * 营业执照-图片URL
     */
    private String licenseUrl;

    /**
     * 营业执照-发证机关
     */
    private String licenseIssuance;

    /**
     * 营业执照-社会统一信用代码
     */
    private String licenseOrgCode;

    /**
     * 营业执照-企业名称
     */
    private String licenseOrgName;

    /**
     * 营业执照-注册资本
     */
    private String licenseRegisteredCapital;

    /**
     * 营业执照-资本类型
     */
    private String licenseCapitalType;

    /**
     * 营业执照-企业地址
     */
    private String licenseAddress;

    /**
     * 营业执照-有效期开始时间
     */
    private String licenseValidTimeStart;

    /**
     * 营业执照-有效期结束时间
     */
    private String licenseValidTimeEnd;

    private String legalPersonIdCardReUrl;

    /**
     * 法人身份证_签发机关
     */
    private String legalPersonIdCardIssuedOrg;

    /**
     * 法人身份证图片URL
     */
    private String legalPersonIdCardUrl;

    /**
     * 法人身份证号
     */
    private String legalPersonIdNo;

    /**
     * 法人姓名
     */
    private String legalPersonName;

    /**
     * 法人身份证_开始有效期
     */
    private Date legalPersonIdCardValidityStart;

    /**
     * 法人身份证_结束有效期
     */
    private Date legalPersonIdCardValidityEnd;

    /**
     * 道路运输许可证
     */
    private String roadTransUrl;

    /**
     * 道路运输许可证-业户名称
     */
    private String roadTransOrgName;

    /**
     * 道路运输许可证-许可字
     */
    private String roadTransWord;

    /**
     * 道路运输许可证-许可省
     */
    private String roadTransProvince;

    /**
     * 道路运输许可证-许可号
     */
    private String roadTransNo;

    /**
     * 道路运输许可证-经营范围
     */
    private String roadTransScope;

    /**
     * 道路运输许可证-地址
     */
    private String roadTransAddress;

    /**
     * 道路运输许可证-核发机关
     */
    private String roadTransIssuance;

    /**
     * 道路运输证有效期开始时间
     */
    private String roadValidTimeStart;

    /**
     * 道路运输证有效期截至时间
     */
    private String roadValidTimeEnd;

    /**
     * 危险品运输许可证-图片地址
     */
    private String dangerTransUrl;

    /**
     * 危险品运输许可证-证书号
     */
    private String dangerTransCode;

    /**
     * 危险品运输许可证-单位名称
     */
    private String dangerTransName;

    /**
     * 危险品运输许可证-单位负责人
     */
    private String dangerTransLeader;

    /**
     * 危险品运输许可证-单位地址
     */
    private String dangerTransAddress;

    /**
     * 危险品运输许可证-单位类型
     */
    private String dangerTransOrgType;

    /**
     * 危险品运输许可证-经营类型
     */
    private String dangerTransBizType;

    /**
     * 危险品运输许可证-经营范围
     */
    private String dangerTransScope;

    /**
     * 危险品运输许可证-发证机关
     */
    private String dangerTransIssuance;

    /**
     * 有效期截至时间
     */
    private String dangerValidTimeEnd;

    /**
     * 有效期开始时间
     */
    private String dangerValidTimeStart;

    /**
     * 企业邮箱
     */
        private String orgEmail;

    /**
     * 联系人
     */
    private String orgContactMan;

    /**
     * 联系号码
     */
    private String orgContactPhone;

    /**
     * 企业授权书
     */
    private String orgWarrant;
}
