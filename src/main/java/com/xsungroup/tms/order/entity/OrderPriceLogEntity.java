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
 * 订单价格变化表
 * </p>
 *
 * @author admin
 * @since 2019-08-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("o_order_price_log")
public class OrderPriceLogEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
	private String orderPriceChangeId;
    /**
     * 订单号
     */
	private Long orderId;
    /**
     * 订单价格 变化前 单位：分
     */
	private Long orderPriceBefore;
    /**
     * 订单价格 变化前 超时时间（毫秒时间戳）（订单在没有确认前，超过这个时间的订单价格将无效，需要重新议价或计算价格）
     */
	private Long orderPriceBeforeOvertime;
    /**
     * 订单价格 变化后价格 单位：分
     */
	private Long orderPriceAfter;
    /**
     * 订单价格 变化后 超时时间（毫秒时间戳）（订单在没有确认前，超过这个时间的订单价格将无效，需要重新议价或计算价格）
     */
	private Long orderPriceAfterOvertime;
    /**
     * 备注
     */
	private String remark;
    /**
     * 变更用户ID（系统客服，货主）
     */
	private String userId;

}
