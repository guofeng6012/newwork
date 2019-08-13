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
 * 收发件人信息
 * </p>
 *
 * @author
 * @since 2019-08-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("o_sender_consignee")
public class SenderConsigneeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String senderConsigneeId;
    /**
     * 收发件人类型 1：收件人，2：发件人，3：收/发件人
     */
    private Integer type;
    /**
     * 单位ID （这个字段便于之后建立企业直接关系，但是当有这个字段的时候，单位名称字段就不允许修改了，这个跟需求会有一些碰撞）
     */
    private String orgId;
    /**
     * 单位名称
     */
    private String orgName;
    /**
     * 发货人名称
     */
    private String name;
    /**
     * 区域ID 省ID
     */
    private String areaProvinceId;
    /**
     * 区域ID 省名称
     */
    private String areaProvinceName;
    /**
     * 区域ID 市ID
     */
    private String areaCityId;
    /**
     * 区域ID 市名称
     */
    private String areaCityName;
    /**
     * 区域ID 县ID
     */
    private String areaCountyId;
    /**
     * 区域ID 县名称
     */
    private String areaCountyName;
    /**
     * 区域ID 镇ID
     */
    private String areaTownId;
    /**
     * 区域ID 镇名称
     */
    private String areaTownName;
    /**
     * 发货人详细地址
     */
    private String areaDetail;
    /**
     * 发货人联系方式-手机号
     */
    private String contactPhone;
    /**
     * 发货人联系方式-固话
     */
    private String contactTel;
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
     * 权重（用于排序，越大越靠前）
     */
    private Integer weight;
    /**
     * 备注
     */
    private String remarks;

}
