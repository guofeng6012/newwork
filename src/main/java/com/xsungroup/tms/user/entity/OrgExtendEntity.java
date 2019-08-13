package com.xsungroup.tms.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xsungroup.tms.core.supper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 组织扩展表
 * </p>
 *
 * @author GF
 * @since 2019-07-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_org_extend")
public class OrgExtendEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "org_id")
    private String orgId;

    /**
     * 企业名称
     */
    private String orgName;

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

    /**
     * 法人身份证_开始有效期
     */
    private Date legalPersonIdCardValidityStart;

    /**
     * 法人身份证_结束有效期
     */
    private Date legalPersonIdCardValidityEnd;

    /**
     * 法人身份证图片URL
     */
    private String legalPersonIdCardUrl;

    private String legalPersonIdCardReUrl;

    /**
     * 发证机关
     */
    private String legalPersonIdCardIssuedOrg;

    /**
     * 法人身份证号
     */
    private String legalPersonIdNo;

    /**
     * 法人姓名
     */
    private String legalPersonName;

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
     * 授权涵访问地址
     */
    private String orgWarrant;

    /**
     * 联系人
     */
    private String orgContactMan;

    /**
     * 公司联系号码
     */
    private String orgContactPhone;

    /**
     * 公司邮箱
     */
    private String orgEmail;

    /**
     * 公司传真
     */
    private String orgFax;

    /**
     * 框架协议（存路径）
     */
    private String agreementUrl;

    /**
     * 首次提交时间
     */
    private String firstSubmitTime;

    /**
     * 最近提交时间
     */
    private String lastSubmitTime;

    /**
     * 首次审核时间
     */
    private String firstAuditTime;

    /**
     * 最新审核时间
     */
    private String lastAuditTime;

    /**
     * 审核方式 0:自动  1:人工
     */
    private String auditMode;

    /**
     * 审核人pk
     */
    private String auditUser;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String updateUser;


}
