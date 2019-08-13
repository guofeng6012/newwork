package com.xsungroup.tms.user.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author GF
 * @Date 2019-8-8 13:54:02
 * @Description
 **/
@Data
public class UserAuditVO {

    private String userId;

    /**
     * 审核状态
     */
    private String auditStatus;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 角色
     */
    private String orgRoleName;

    /**
     * 联系方式
     */
    private String phoneNo;

    /**
     * 省份证号
     */
    private String legalPersonIdNo;
    /**
     * 有效期 开始时间
     */
    private String legalPersonIdCardValidityStart;

    /**
     * 有效期 结束时间
     */
    private String legalPersonIdCardValidityEnd;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 提交时间
     */
    private Date firstSubmitTime;

    /**
     * 审核方式
     */
    private String auditMode;

    /**
     * 审核人
     */
    private String auditUser;
    /**
     * 审核时间
     */
    private Date lastSubmitTime;
}
