package com.xsungroup.tms.order.entity;

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
 * 订单表
 * </p>
 *
 * @author admin
 * @since 2019-08-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("o_order")
public class OrderEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(type = IdType.ID_WORKER)
    private Long orderId;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 订单价格 单位：分
     */
    private Long orderPrice;
    /**
     * 超时时间（毫秒时间戳）（订单在没有确认前，超过这个时间的订单价格将无效，需要重新议价或计算价格）
     */
    private Long orderPriceOvertime;
    /**
     * 支付方式（0:在线支付,1:发货方付,2:收货方付,3:货到付款,4:合同月结）
     */
    private Integer payType;
    /**
     * 创建人ID
     */
    private String createUserId;
    /**
     * 创建人名称
     */
    private String createUserName;
    /**
     * 创建人所属组织Id
     */
    private String createUserOrgId;
    /**
     * 创建人所属组织名称
     */
    private String createUserOrgName;
    /**
     * 所属组织Id
     */
    private String subordinateOrgId;
    /**
     * 所属组织名称
     */
    private String subordinateOrgName;
    /**
     * 发件-收发件人信息 ID
     */
    private String senderId;
    /**
     * 发件-单位ID （这个字段便于之后建立企业直接关系，但是当有这个字段的时候，单位名称字段就不允许修改了，这个跟需求会有一些碰撞）
     */
    private String senderOrgId;
    /**
     * 发货-单位名称
     */
    private String senderOrgName;
    /**
     * 发货-发货人名称
     */
    private String senderName;
    /**
     * 发货-区域ID 省ID
     */
    private String senderAreaProvinceId;
    /**
     * 发货-区域ID 省名称
     */
    private String senderAreaProvinceName;
    /**
     * 发货-区域ID 市ID
     */
    private String senderAreaCityId;
    /**
     * 发货-区域ID 市名称
     */
    private String senderAreaCityName;
    /**
     * 发货-区域ID 县ID
     */
    private String senderAreaCountyId;
    /**
     * 发货-区域ID 县名称
     */
    private String senderAreaCountyName;
    /**
     * 发货-区域ID 镇ID
     */
    private String senderAreaTownId;
    /**
     * 发货-区域ID 镇名称
     */
    private String senderAreaTownName;
    /**
     * 发货-发货人详细地址
     */
    private String senderAreaDetail;
    /**
     * 发货-发货人联系方式-手机号
     */
    private String senderContactPhone;
    /**
     * 发货-发货人联系方式-固话
     */
    private String senderContactTel;
    /**
     * 发件-提货时间-开始
     */
    private LocalDateTime senderPickUpStartTime;
    /**
     * 发件-提货时间-结束
     */
    private LocalDateTime senderPickUpEndTime;
    /**
     * 发件-提货方式 1：上面提货，2：客户自送
     */
    private Integer senderPickUpType;
    /**
     * 收件-收发件人信息 ID
     */
    private String consigneeId;
    /**
     * 收件-单位ID （这个字段便于之后建立企业直接关系，但是当有这个字段的时候，单位名称字段就不允许修改了，这个跟需求会有一些碰撞）
     */
    private String consigneeOrgId;
    /**
     * 收件-单位名称
     */
    private String consigneeOrgName;
    /**
     * 收件-发货人名称
     */
    private String consigneeName;
    /**
     * 收件-区域ID 省ID
     */
    private String consigneeAreaProvinceId;
    /**
     * 收件-区域ID 省名称
     */
    private String consigneeAreaProvinceName;
    /**
     * 收件-区域ID 市ID
     */
    private String consigneeAreaCityId;
    /**
     * 收件-区域ID 市名称
     */
    private String consigneeAreaCityName;
    /**
     * 收件-区域ID 县ID
     */
    private String consigneeAreaCountyId;
    /**
     * 收件-区域ID 县名称
     */
    private String consigneeAreaCountyName;
    /**
     * 收件-区域ID 镇ID
     */
    private String consigneeAreaTownId;
    /**
     * 收件-区域ID 镇名称
     */
    private String consigneeAreaTownName;
    /**
     * 收件-发货人详细地址
     */
    private String consigneeAreaDetail;
    /**
     * 收件-发货人联系方式-手机号
     */
    private String consigneeContactPhone;
    /**
     * 收件-发货人联系方式-固话
     */
    private String consigneeContactTel;
    /**
     * 收件-送货方式 1：配送，2：客提
     */
    private Integer consigneeReceivingType;
    /**
     * 期望送达时效
     */
    private String limitationOfService;
    /**
     * 运输方式 1：整车，2：零担(默认)
     */
    private Integer transportMode;
    /**
     * 运输类型 1：普通货物运输，2：冷链运输，4：危险品
     */
    private Integer transportType;
    /**
     * 保价 单位：分
     */
    private Long insuredPrice;
    /**
     * 商品名称（对商品名称用，好分割）
     */
    private String goodsName;
    /**
     * 订单总数量
     */
    private Integer totalQty;
    /**
     * 订单总体积
     */
    private Integer totalVolume;
    /**
     * 订单总重量
     */
    private Integer totalWeight;
    /**
     * 备注
     */
    private String remark;
    /**
     * 客服备注
     */
    private String customerServiceRemark;
    /**
     * 回单是否寄回（0:否,1:是）
     */
    private Boolean isReceiptBack;

}
