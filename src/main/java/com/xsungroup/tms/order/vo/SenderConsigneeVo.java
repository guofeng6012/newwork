package com.xsungroup.tms.order.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 梁建军
 * 创建日期： 2019/8/6
 * 创建时间： 15:10
 * @version 1.0
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SenderConsigneeVo implements Serializable {
    /**
     * 收发件地址ID
     */
    @ApiModelProperty("收发件地址ID")
    private String senderConsigneeId;
    /**
     * 收发件人类型 1：收件人，2：发件人，3：收/发件人
     */
    @ApiModelProperty("收发件人类型 1：收件人，2：发件人，3：收/发件人")
    private Integer type;
    /**
     * 发货人名称
     */
    @ApiModelProperty("发货人名称")
    private String name;
    /**
     * 区域ID 省ID
     */
    @ApiModelProperty("区域ID 省ID")
    private String areaProvinceId;
    /**
     * 区域ID 省名称
     */
    @ApiModelProperty("区域ID 省名称")
    private String areaProvinceName;
    /**
     * 区域ID 市ID
     */
    @ApiModelProperty("区域ID 市ID")
    private String areaCityId;
    /**
     * 区域ID 市名称
     */
    @ApiModelProperty("区域ID 市名称")
    private String areaCityName;
    /**
     * 区域ID 县ID
     */
    @ApiModelProperty("区域ID 县ID")
    private String areaCountyId;
    /**
     * 区域ID 县名称
     */
    @ApiModelProperty("区域ID 县名称")
    private String areaCountyName;
    /**
     * 区域ID 镇ID
     */
    @ApiModelProperty("区域ID 镇ID")
    private String areaTownId;
    /**
     * 区域ID 镇名称
     */
    @ApiModelProperty("区域ID 镇名称")
    private String areaTownName;
    /**
     * 发货人详细地址
     */
    @ApiModelProperty("发货人详细地址")
    private String areaDetail;
    /**
     * 发货人联系方式-手机号
     */
    @ApiModelProperty("发货人联系方式-手机号")
    private String contactPhone;
    /**
     * 发货人联系方式-固话
     */
    @ApiModelProperty("发货人联系方式-固话")
    private String contactTel;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

    /**
     * 单位ID （这个字段便于之后建立企业直接关系，但是当有这个字段的时候，单位名称字段就不允许修改了，这个跟需求会有一些碰撞）
     */
    @ApiModelProperty("单位ID")
    private String orgId;
    /**
     * 单位名称
     */
    @ApiModelProperty("单位名称")
    private String orgName;

    /**
     * 创建人ID
     */
    @ApiModelProperty("创建人ID")
    private String createUserId;
    /**
     * 创建人名称
     */
    @ApiModelProperty("创建人名称")
    private String createUserName;
    /**
     * 创建人所属组织Id
     */
    @ApiModelProperty("创建人所属组织Id")
    private String createUserOrgId;
    /**
     * 创建人所属组织名称
     */
    @ApiModelProperty("创建人所属组织名称")
    private String createUserOrgName;
    /**
     * 所属组织Id
     */
    @ApiModelProperty("所属组织Id")
    private String subordinateOrgId;
    /**
     * 所属组织名称
     */
    @ApiModelProperty("所属组织名称")
    private String subordinateOrgName;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private LocalDateTime gmtModified;

    /**
     * 是否有效
     */
    @ApiModelProperty("是否有效")
    private Integer isAble;
}
