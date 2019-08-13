package com.xsungroup.tms.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xsungroup.tms.core.supper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单状态变化日志
 * </p>
 *
 * @author admin
 * @since 2019-08-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("o_order_status_log")
public class OrderStatusLogEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
	private String orderStatusLogId;
    /**
     * 订单状态 变化之前
     */
	private Integer orderStatusBefore;
    /**
     * 订单状态 变化之后
     */
	private Integer orderStatusAfter;
    /**
     * 变更用户ID（系统客服，货主） 系统自动触发的 将为空
     */
	private String userId;
    /**
     * 备注
     */
	private String remark;
    /**
     * 触发方式 1：手动，2：自动
     */
	private Integer triggerMode;

}
