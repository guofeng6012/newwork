package com.xsungroup.tms.matedata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xsungroup.tms.core.supper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 审核方式表
 * </p>
 *
 * @author Alex
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_audit_type")
public class AuditTypeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 审核方式ID
     */
    @TableId(value = "audit_type_id", type = IdType.UUID)
    private String auditTypeId;

    /**
     * 审核方式名称
     */
    private String auditTypeName;

    /**
     * 是否自动 0:不是 1:是
     */
    private int isAuto;


}
