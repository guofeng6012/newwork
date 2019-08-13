package com.xsungroup.tms.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xsungroup.tms.core.supper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 审核记录表
 * </p>
 *
 * @author Alex
 * @since 2019-08-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_audit_record")
public class AuditRecordEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value = "audit_record_id", type = IdType.UUID)
	private String auditRecordId;
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
    /**
     * 删除标记 0:已删除 1:未删除
//     */
//	private Boolean isAble;
//	private LocalDateTime gmtCreate;



}
