package com.xsungroup.tms.order.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 梁建军
 * 创建日期： 2019/8/6
 * 创建时间： 14:06
 * @version 1.0
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SenderConsigneeDto extends Convert implements Serializable {

    /**
     * 收发件人类型 1：收件人，2：发件人，3：收/发件人
     */
    @NotNull(groups = {SenderConsigneeDto.Create.class, SenderConsigneeDto.Update.class}, message = "收发件人类型不能为空")
    @ApiModelProperty(value = "收发件人类型 1：收件人，2：发件人，3：收/发件人",required = true)
    private Integer type;
    /**
     * 发货人名称
     */
    @NotNull(groups = {SenderConsigneeDto.Create.class, SenderConsigneeDto.Update.class}, message = "发货人名称不能为空")
    @ApiModelProperty(value = "发货人名称",required = true)
    private String name;
    /**
     * 单位名称
     */
    @NotNull(groups = {SenderConsigneeDto.Create.class, SenderConsigneeDto.Update.class}, message = "单位名称不能为空")
    @ApiModelProperty(value = "单位名称",required = true)
    private String orgName;
    /**
     * 区域ID 省ID
     */
    @NotNull(groups = {SenderConsigneeDto.Create.class, SenderConsigneeDto.Update.class}, message = "省ID不能为空")
    @ApiModelProperty(value = "区域ID 省ID",required = true)
    private String areaProvinceId;
    /**
     * 区域ID 省名称
     */
    @NotNull(groups = {SenderConsigneeDto.Create.class, SenderConsigneeDto.Update.class}, message = "省名称不能为空")
    @ApiModelProperty(value = "区域ID 省名称",required = true)
    private String areaProvinceName;
    /**
     * 区域ID 市ID
     */
    @NotNull(groups = {SenderConsigneeDto.Create.class, SenderConsigneeDto.Update.class}, message = "市ID不能为空")
    @ApiModelProperty(value = "区域ID 市ID",required = true)
    private String areaCityId;
    /**
     * 区域ID 市名称
     */
    @NotNull(groups = {SenderConsigneeDto.Create.class, SenderConsigneeDto.Update.class}, message = "市名称不能为空")
    @ApiModelProperty(value = "区域ID 市名称",required = true)
    private String areaCityName;
    /**
     * 区域ID 县ID
     */
    @NotNull(groups = {SenderConsigneeDto.Create.class, SenderConsigneeDto.Update.class}, message = "县ID不能为空")
    @ApiModelProperty(value = "区域ID 县ID",required = true)
    private String areaCountyId;
    /**
     * 区域ID 县名称
     */
    @NotNull(groups = {SenderConsigneeDto.Create.class, SenderConsigneeDto.Update.class}, message = "县名称不能为空")
    @ApiModelProperty(value = "区域ID 县名称",required = true)
    private String areaCountyName;
    /**
     * 区域ID 镇ID
     */
    @NotNull(groups = {SenderConsigneeDto.Create.class, SenderConsigneeDto.Update.class}, message = "镇ID不能为空")
    @ApiModelProperty(value = "区域ID 镇ID",required = true)
    private String areaTownId;
    /**
     * 区域ID 镇名称
     */
    @NotNull(groups = {SenderConsigneeDto.Create.class, SenderConsigneeDto.Update.class}, message = "镇名称不能为空")
    @ApiModelProperty(value = "区域ID 镇名称",required = true)
    private String areaTownName;
    /**
     * 发货人详细地址
     */
    @NotNull(groups = {SenderConsigneeDto.Create.class, SenderConsigneeDto.Update.class}, message = "详细地址不能为空")
    @ApiModelProperty(value = "发货人详细地址",required = true)
    private String areaDetail;
    /**
     * 发货人联系方式-手机号
     */
    @NotNull(groups = {SenderConsigneeDto.Create.class, SenderConsigneeDto.Update.class}, message = "手机号不能为空")
    @ApiModelProperty(value = "发货人联系方式-手机号",required = true)
    private String contactPhone;
    /**
     * 发货人联系方式-固话
     */
    @ApiModelProperty(value = "发货人联系方式-固话")
    private String contactTel;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;


    public interface Create {
    }

    public interface Update {
    }
}
