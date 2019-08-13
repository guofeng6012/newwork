package com.xsungroup.tms.user.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import lombok.Data;

@Data
public class AuditRecordDto extends Convert {
    /**
     * 审核对象的id（企业、个人、司机、车辆）
     */
    private String auditObjId;
    /**
     * 审核备注
     */
    private String auditMemo;
    private String refuseReason;// 拒绝原因
    /**
     * 创建人
     */
    private String createUserName;

}
