package com.xsungroup.tms.user.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class OrgExtendRegisterDTO extends Convert {

    /**
     * 企业名称
     */
    private String orgName;

    /**
     * 营业执照-图片URL
     */
    private String licenseUrl;

    /**
     * 营业执照-社会统一信用代码
     */
    private String licenseOrgCode;

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
     * 账号名
     */
    private String userName;

    /**
     * 名称
     */
    private String realName;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String phoneNo;

    /**
     * 用户类型
     */
    private int userType;

    private String smsCode;

}
