package com.xsungroup.tms.user.vo;

import lombok.Data;

/**
 * @Author
 * @Date
 * @Description
 **/
@Data
public class OrgViewVO {

    /**
     * 企业id
     */
    private String orgId;

    /**
     * 审核状态
     */
    private String auditStatus;

    /**
     * 单位名称
     */
    private String orgName;

    /**
     * 是否核算组织
     */
    private String isAccountant;

    /**
     * 是否承运商
     */
    private String orgRole;

    /**
     * 创建来源
     */
    private String orgSource;

    /**
     * 社会统一信息用代码
     */
    private String licenseOrgCode;

    /**
     * 道路运输许可证
     */
    private String roadTransCode;

    /**
     * 危险品许可证
     */
    private String dangerTransCode;

    /**
     * 是否印章采集
     */
    private String sealGather;

    private String roadTransWord;
    private String roadTransProvince;
    private String roadTransNo;

}
