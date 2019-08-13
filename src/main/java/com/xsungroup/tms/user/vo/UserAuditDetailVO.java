package com.xsungroup.tms.user.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author GF
 * @Date 2019-8-8 16:15:44
 * @Description
 **/
@Data
public class UserAuditDetailVO {

    /**
     * 身份证 反面照片
     */
    private String legalPersonIdCardReUrl;

    /**
     * 身份证 证明照片
     */
    private String legalPersonIdCardUrl;

    /**
     *  姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String legalPersonIdNo;

    /**
     * 有效期 开始时间
     */
    private Date legalPersonIdCardValidityStart;

    /**
     * 有效期 结束时间
     */
    private Date legalPersonIdCardValidityEnd;

    /**
     *  用户id
     */
    private String userId;

}
