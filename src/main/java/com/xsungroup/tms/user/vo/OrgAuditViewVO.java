package com.xsungroup.tms.user.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author GF
 * @Date 2019-8-9 14:14:48
 * @Description
 **/
@Data
public class OrgAuditViewVO {

    /**
     *状态
     */
    private String auditStatus;
    /**
     *公司名称
     */
    private String orgName;
    /**
     *联系方式
     */
    private String phoneNo;
    /**
     *统一社会信用代码
     */
    private String licenseOrgCode;
    /**
     *
     */
    private String roadTransWord;
    /**
     *
     */
    private String roadTransProvince;
    /**
     *
     */
    private String roadTransNo;
    /**
     *首次提交时间
     */
    private Date firstSubmitTime;
    /**
     *首次审核时间
     */
    private Date firstAuditTime;
    /**
     *最近提交时间
     */
    private Date lastSubmitTime;
    /**
     *最近审核时间
     */
    private Date lastAuditTime;
    /**
     *审核方式
     */
    private Integer auditMode;
    /**
     *审核人
     */
    private String auditUser;

    private String orgId;
}
