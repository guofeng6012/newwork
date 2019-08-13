package com.xsungroup.tms.user.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Author GF
 * @Date 2019-7-29 19:07:22
 * @Description
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class OrgSaveDTO extends Convert {


    private String orgId;

    /**
     * 上级组织id
     */
    private String parentId;

    /**
     *
     */
    private String orgRole;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 组织性质 法人组织
     */
    private Integer isLegal;

    /**
     * 组织性质 核算组织
     */
    private Integer isAccountant;

    /**
     * 组织性质 运营组织
     */
    private Integer isOperation;

    /**
     * 税号
     */
    private String orgTaxNo;

    /**
     * 企业地址
     */
    private String orgAddress;

    /**
     * 开户银行 名称
     */
    private String orgBankName;

    /**
     * 开户行 账户
     */
    private String orgBankAccountNo;

    /**
     * 企业邮箱
     */
    private String orgEmail;

    /**
     * 联系人名称
     */
    private String orgContactMan;

    /**
     * 联系人手机号
     */
    private String orgContactPhone;

    private String legalPersonIdCardReUrl;

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
     * 法人身份证_签发机关
     */
    private String legalPersonIdCardIssuedOrg;

    /**
     * 营业执照
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
     * 营业执照-注册资本类型
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

    /**
     * 道路运输许可证
     */
    private String roadTransUrl;
    /**
     * 道路运输许可证-业户名称
     */
    private String roadTransOrgName;
    /**
     * 道路运输许可证-许可省
     */
    private String roadTransProvince;

    /**
     * 道路运输许可证-许可字
     */
    private String roadTransWord;
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
     * 道路运输证有效期结束时间
     */
    private String roadValidTimeEnd;

    /**
     *危险品运输许可证-图片
     */
    private String dangerTransUrl;
    /**
     *危险品运输许可证-证书号
     */
    private String dangerTransCode;
    /**
     *危险品运输许可证-单位名称
     */
    private String dangerTransName;
    /**
     *危险品运输许可证-单位负责人
     */
    private String dangerTransLeader;
    /**
     * 危险品运输许可证-单位地址
     */
    private String dangerTransAddress;
    /**
     * 危险品运输许可证-经营范围
     */
    private String dangerTransScope;
    /**
     * 危险品运输许可证-有效期截至时间
     */
    private String dangerValidTimeEnd;
    /**
     * 危险品运输许可证-有效期开始时间
     */
    private String dangerValidTimeStart;

    /**
     * 危险品运输许可证-发证机关
     */
    private String dangerTransIssuance;

    /**
     * 企业授权书
     */
    private String orgWarrant;

}
