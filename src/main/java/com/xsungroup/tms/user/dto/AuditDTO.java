package com.xsungroup.tms.user.dto;

import lombok.Data;

/**
 * @Author
 * @Date
 * @Description
 **/
@Data
public class AuditDTO {

    private String orgId;

    private int status;

    /**
     * 审核类型 企业  个人
     */
    private int type;

    private String refuseReason;

}
