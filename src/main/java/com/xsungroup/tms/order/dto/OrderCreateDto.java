package com.xsungroup.tms.order.dto;

import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.order.common.CheckParameters;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 梁建军
 * 创建日期： 2019/8/12
 * 创建时间： 16:56
 * @version 1.0
 * @since 1.0
 */
@Data
public class OrderCreateDto implements CheckParameters {

    /**
     * 发件-收发件人信息 ID
     */
    @ApiModelProperty(value = "发件-收发件人信息 ID")
    private String senderId;
    /**
     * 发件-单位ID （这个字段便于之后建立企业直接关系，但是当有这个字段的时候，单位名称字段就不允许修改了，这个跟需求会有一些碰撞）
     */
    @ApiModelProperty(value = "发件-单位ID （这个字段便于之后建立企业直接关系，但是当有这个字段的时候，单位名称字段就不允许修改了，这个跟需求会有一些碰撞）")
    private String senderOrgId;
    /**
     * 发货-单位名称
     */
    @NotNull(message = "发货单位名称不能为空")
    @NotEmpty(message = "发货单位名称不能为空")
    @ApiModelProperty(value = "发货-单位名称", required = true)
    private String senderOrgName;
    /**
     * 发货-发货人名称
     */
    @NotNull(message = "发货人名称不能为空")
    @NotEmpty(message = "发货人名称不能为空")
    @ApiModelProperty(value = "发货-发货人名称", required = true)
    private String senderName;
    /**
     * 发货-区域ID 省ID
     */
    @NotNull(message = "发货-区域ID 省ID不能为空")
    @NotEmpty(message = "发货-区域ID 省ID不能为空")
    @ApiModelProperty(value = "发货-区域ID 省ID", required = true)
    private String senderAreaProvinceId;
    /**
     * 发货-区域ID 省名称
     */
    @NotNull(message = "发货-区域ID 省名称不能为空")
    @NotEmpty(message = "发货-区域ID 省名称不能为空")
    @ApiModelProperty(value = "发货-区域ID 省名称", required = true)
    private String senderAreaProvinceName;
    /**
     * 发货-区域ID 市ID
     */
    @NotNull(message = "发货-区域ID 市ID不能为空")
    @NotEmpty(message = "发货-区域ID 市ID不能为空")
    @ApiModelProperty(value = "发货-区域ID 市ID", required = true)
    private String senderAreaCityId;
    /**
     * 发货-区域ID 市名称
     */
    @NotNull(message = "发货-区域ID 市名称不能为空")
    @NotEmpty(message = "发货-区域ID 市名称不能为空")
    @ApiModelProperty(value = "发货-区域ID 市名称", required = true)
    private String senderAreaCityName;
    /**
     * 发货-区域ID 县ID
     */
    @NotNull(message = "发货-区域ID 县ID不能为空")
    @NotEmpty(message = "发货-区域ID 县ID不能为空")
    @ApiModelProperty(value = "发货-区域ID 县ID", required = true)
    private String senderAreaCountyId;
    /**
     * 发货-区域ID 县名称
     */
    @NotNull(message = "发货-区域ID 县名称不能为空")
    @NotEmpty(message = "发货-区域ID 县名称不能为空")
    @ApiModelProperty(value = "发货-区域ID 县名称", required = true)
    private String senderAreaCountyName;
    /**
     * 发货-区域ID 镇ID
     */
    @NotNull(message = "发货-区域ID 镇ID不能为空")
    @NotEmpty(message = "发货-区域ID 镇ID不能为空")
    @ApiModelProperty(value = "发货-区域ID 镇ID", required = true)
    private String senderAreaTownId;
    /**
     * 发货-区域ID 镇名称
     */
    @NotNull(message = "发货-区域ID 镇名称不能为空")
    @NotEmpty(message = "发货-区域ID 镇名称不能为空")
    @ApiModelProperty(value = "发货-区域ID 镇名称", required = true)
    private String senderAreaTownName;
    /**
     * 发货-发货人详细地址
     */
    @NotNull(message = "发货-发货人详细地址不能为空")
    @NotEmpty(message = "发货-发货人详细地址不能为空")
    @ApiModelProperty(value = "发货-发货人详细地址", required = true)
    private String senderAreaDetail;
    /**
     * 发货-发货人联系方式-手机号
     */
    @NotNull(message = "发货-发货人联系方式-手机号不能为空")
    @NotEmpty(message = "发货-发货人联系方式-手机号不能为空")
    @ApiModelProperty(value = "发货-发货人联系方式-手机号", required = true)
    private String senderContactPhone;
    /**
     * 发货-发货人联系方式-固话
     */
    @ApiModelProperty(value = "发货-发货人联系方式-固话")
    private String senderContactTel;
    /**
     * 发件-提货时间-开始
     */
    @NotNull(message = "发件-提货时间-开始不能为空")
    @ApiModelProperty(value = "发件-提货时间-开始", required = true)
    private LocalDateTime senderPickUpStartTime;
    /**
     * 发件-提货时间-结束
     */
    @NotNull(message = "发件-提货时间-结束不能为空")
    @ApiModelProperty(value = "发件-提货时间-结束", required = true)
    private LocalDateTime senderPickUpEndTime;
    /**
     * 发件-提货方式 1：上面提货，2：客户自送
     */
    @NotNull(message = "提货方式 1：上面提货，2：客户自送")
    @Min(value = 1,message = "提货方式 1：上面提货，2：客户自送")
    @Max(value = 2,message = "提货方式 1：上面提货，2：客户自送")
    @ApiModelProperty(value = "发件-提货方式 1：上面提货，2：客户自送", required = true)
    private Integer senderPickUpType;
    /**
     * 收件-收发件人信息 ID
     */
    @ApiModelProperty(value = "收件-收发件人信息 ID")
    private String consigneeId;
    /**
     * 收件-单位ID （这个字段便于之后建立企业直接关系，但是当有这个字段的时候，单位名称字段就不允许修改了，这个跟需求会有一些碰撞）
     */
    @ApiModelProperty(value = "收件-单位ID （这个字段便于之后建立企业直接关系，但是当有这个字段的时候，单位名称字段就不允许修改了，这个跟需求会有一些碰撞）")
    private String consigneeOrgId;
    /**
     * 收件-单位名称
     */
    @NotNull(message = "收件-单位名称不能为空")
    @NotEmpty(message = "收件-单位名称不能为空")
    @ApiModelProperty(value = "收件-单位名称", required = true)
    private String consigneeOrgName;
    /**
     * 收件-发货人名称
     */
    @NotNull(message = "收件名称不能为空")
    @NotEmpty(message = "收件名称不能为空")
    @ApiModelProperty(value = "收件-发货人名称", required = true)
    private String consigneeName;
    /**
     * 收件-区域ID 省ID
     */
    @NotNull(message = "收件-区域ID 省ID不能为空")
    @NotEmpty(message = "收件-区域ID 省ID不能为空")
    @ApiModelProperty(value = "收件-区域ID 省ID", required = true)
    private String consigneeAreaProvinceId;
    /**
     * 收件-区域ID 省名称
     */
    @NotNull(message = "收件-区域ID 省ID不能为空")
    @NotEmpty(message = "收件-区域ID 省ID不能为空")
    @ApiModelProperty(value = "收件-区域ID 省名称", required = true)
    private String consigneeAreaProvinceName;
    /**
     * 收件-区域ID 市ID
     */
    @NotNull(message = "收件-区域ID 市ID不能为空")
    @NotEmpty(message = "收件-区域ID 市ID不能为空")
    @ApiModelProperty(value = "收件-区域ID 市ID", required = true)
    private String consigneeAreaCityId;
    /**
     * 收件-区域ID 市名称
     */
    @NotNull(message = "收件-区域ID 市名称不能为空")
    @NotEmpty(message = "收件-区域ID 市名称不能为空")
    @ApiModelProperty(value = "收件-区域ID 市名称", required = true)
    private String consigneeAreaCityName;
    /**
     * 收件-区域ID 县ID
     */
    @NotNull(message = "收件-区域ID 县ID不能为空")
    @NotEmpty(message = "收件-区域ID 县ID不能为空")
    @ApiModelProperty(value = "收件-区域ID 县ID", required = true)
    private String consigneeAreaCountyId;
    /**
     * 收件-区域ID 县名称
     */
    @NotNull(message = "收件-区域ID 县名称不能为空")
    @NotEmpty(message = "收件-区域ID 县名称不能为空")
    @ApiModelProperty(value = "收件-区域ID 县名称", required = true)
    private String consigneeAreaCountyName;
    /**
     * 收件-区域ID 镇ID
     */
    @NotNull(message = "收件-区域ID 镇ID不能为空")
    @NotEmpty(message = "收件-区域ID 镇ID不能为空")
    @ApiModelProperty(value = "收件-区域ID 镇ID", required = true)
    private String consigneeAreaTownId;
    /**
     * 收件-区域ID 镇名称
     */
    @NotNull(message = "收件-区域ID 镇名称不能为空")
    @NotEmpty(message = "收件-区域ID 镇名称不能为空")
    @ApiModelProperty(value = "收件-区域ID 镇名称", required = true)
    private String consigneeAreaTownName;
    /**
     * 收件-发货人详细地址
     */
    @NotNull(message = "收件详细地址不能为空")
    @NotEmpty(message = "收件人详细地址不能为空")
    @ApiModelProperty(value = "收件-发货人详细地址", required = true)
    private String consigneeAreaDetail;
    /**
     * 收件-发货人联系方式-手机号
     */
    @NotNull(message = "收件人联系方式-手机号不能为空")
    @NotEmpty(message = "收件人联系方式-手机号不能为空")
    @ApiModelProperty(value = "收件-发货人联系方式-手机号", required = true)
    private String consigneeContactPhone;
    /**
     * 收件-发货人联系方式-固话
     */
    @ApiModelProperty(value = "收件-发货人联系方式-固话")
    private String consigneeContactTel;
    /**
     * 收件-送货方式 1：配送，2：客提
     */
    @NotNull(message = "收件-送货方式 1：配送，2：客提")
    @Min(value = 1,message = "收件-送货方式 1：配送，2：客提")
    @Max(value = 2,message = "收件-送货方式 1：配送，2：客提")
    @ApiModelProperty(value = "收件-送货方式 1：配送，2：客提", required = true)
    private Integer consigneeReceivingType;
    /**
     * 期望送达时效
     */
    @ApiModelProperty(value = "期望送达时效", required = true)
    private String limitationOfService;
    /**
     * 运输方式 1：整车，2：零担(默认)
     */
    @NotNull(message = "运输方式 1：整车，2：零担(默认)")
    @Min(value = 1,message = "运输方式 1：整车，2：零担(默认)")
    @Max(value = 2,message = "运输方式 1：整车，2：零担(默认)")
    @ApiModelProperty(value = "运输方式 1：整车，2：零担(默认)", required = true)
    private Integer transportMode;
    /**
     * 运输类型 1：普通货物运输，2：冷链运输，4：危险品
     */
    @NotNull(message = "运输类型 1：普通货物运输，2：冷链运输，4：危险品")
    @Min(value = 1,message = " 运输类型 1：普通货物运输，2：冷链运输，4：危险品")
    @Max(value = 4,message = " 运输类型 1：普通货物运输，2：冷链运输，4：危险品")
    @ApiModelProperty(value = "运输类型 1：普通货物运输，2：冷链运输，4：危险品", required = true)
    private Integer transportType;
    /**
     * 保价 单位：分
     */
    @NotNull(message = "保价不能为空")
    @ApiModelProperty(value = "保价 单位：分",required = true)
    private Long insuredPrice;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 回单是否寄回（0:否,1:是）
     */
    @NotNull(message = "回单是否寄回（0:否,1:是）")
    @Min(value = 0,message = "回单是否寄回（0:否,1:是）")
    @Max(value = 1,message = "回单是否寄回（0:否,1:是）")
    @ApiModelProperty(value = "回单是否寄回（0:否,1:是）", required = true)
    private Integer isReceiptBack;

    /**
     * 商品列表是
     */
    @NotNull(message = "订单必须有商品")
    @Size(min = 1,message = "订单必须有商品")
    @ApiModelProperty(value = "商品列表", required = true)
    private List<Goods> goodsList;


    @Override
    public void checkRequest() throws BussException {

    }

    public interface Create {
    }

    @Data
    public static class Goods {
        /**
         * 商品ID
         */
        @ApiModelProperty(value = "商品ID")
        private String goodsId;
        /**
         * 商品名称
         */
        @NotNull(message = "商品名称不能为空")
        @NotEmpty(message = "商品名称不能为空")
        @ApiModelProperty(value = "商品名称", required = true)
        private String goodsName;
        /**
         * 商品代码
         */
        @ApiModelProperty(value = "商品代码")
        private String goodsCode;
        /**
         * 类型 1：普通货物，2：冷链运输，3：普通/冷链，4：危险品
         */
        @NotNull(message = "运输类型 1：普通货物运输，2：冷链运输，4：危险品")
        @Min(value = 1,message = " 运输类型 1：普通货物运输，2：冷链运输，4：危险品")
        @Max(value = 4,message = " 运输类型 1：普通货物运输，2：冷链运输，4：危险品")
        @ApiModelProperty(value = "类型 1：普通货物，2：冷链运输，3：普通/冷链，4：危险品", required = true)
        private Integer goodsType;
        /**
         * 物品危险等级（需要了解）
         */
        @ApiModelProperty(value = "物品危险等级（需要了解）")
        private String goodsHazardLevel;
        /**
         * 所属商品分类
         */
        @ApiModelProperty(value = "所属商品分类")
        private String goodsCategoryId;
        /**
         * 商品图片地址
         */
        @ApiModelProperty(value = "商品图片地址")
        private String goodsPic;
        /**
         * 商品长度(cm)
         */
        @ApiModelProperty(value = "商品长度(cm)")
        private Integer goodsLength;
        /**
         * 商品宽度(cm)
         */
        @ApiModelProperty(value = "商品宽度(cm)")
        private Integer goodsWidth;
        /**
         * 商品高度(cm)
         */
        @ApiModelProperty(value = "商品高度(cm)")
        private Integer goodsHeight;
        /**
         * 商品长度，宽度，高度显示单位（cm,dm,m）
         */
        @ApiModelProperty(value = "商品长度，宽度，高度显示单位（cm,dm,m）")
        private String goodsLengthShowUnit;
        /**
         * 商品重量(g)
         */
        @ApiModelProperty(value = "商品重量(g)")
        private Integer goodsWeight;
        /**
         * 商品重量显示单位（g,kg,t）
         */
        @ApiModelProperty(value = "商品重量显示单位（g,kg,t）")
        private String goodsWeightShowUnit;
        /**
         * 最低温(单位℃)
         */
        @ApiModelProperty(value = "最低温(单位℃)")
        private Integer lowTemp;
        /**
         * 最高温(单位℃)
         */
        @ApiModelProperty(value = "最高温(单位℃)")
        private Integer highTemp;
        /**
         * 运输备注
         */
        @ApiModelProperty(value = "运输备注")
        private String transportRemarks;
        /**
         * 商品包装ID
         */
        @ApiModelProperty(value = "商品包装ID")
        private String goodsPackId;
        /**
         * 包装规格代码
         */
        @ApiModelProperty(value = "包装规格代码")
        private String goodsPackCode;
        /**
         * 包装规格名称
         */
        @ApiModelProperty(value = "包装规格名称")
        private String goodsPackName;
        /**
         * 包装长度(cm)
         */
        @ApiModelProperty(value = "包装长度(cm)")
        private Integer goodsPackLength;
        /**
         * 包装宽度(cm)
         */
        @ApiModelProperty(value = "包装宽度(cm)")
        private Integer goodsPackWidth;
        /**
         * 包装高度(cm)
         */
        @ApiModelProperty(value = "包装高度(cm)")
        private Integer goodsPackHeight;
        /**
         * 包装长度，宽度，高度显示单位（mm,cm,dm,m）
         */
        @ApiModelProperty(value = "包装长度，宽度，高度显示单位（mm,cm,dm,m）")
        private String goodsPackLengthShowUnit;
        /**
         * 包装重量(g)
         */
        @ApiModelProperty(value = "包装重量(g)")
        private Integer goodsPackWeight;
        /**
         * 包装重量显示单位（g,kg,t）
         */
        @ApiModelProperty(value = "包装重量显示单位（g,kg,t）")
        private String goodsPackWeightShowUnit;
        /**
         * 包装内商品数量
         */
        @ApiModelProperty(value = "包装内商品数量")
        private Integer goodsNum;
        /**
         * 包装Id
         */
        @ApiModelProperty(value = "包装Id")
        private String packUnitId;
        /**
         * 包装单位code
         */
        @ApiModelProperty(value = "包装单位code")
        private String packUnitCode;
        /**
         * 包装单位名称
         */
        @ApiModelProperty(value = "包装单位名称")
        private String packUnitName;
        /**
         * 是否是标准件 0 不是，1 是
         */
        @NotNull(message = "是否是标准件 0 不是，1 是")
        @Min(value = 0,message = "是否是标准件 0 不是，1 是")
        @Max(value = 1,message = "是否是标准件 0 不是，1 是")
        @ApiModelProperty(value = "是否是标准件 0 不是，1 是", required = true)
        private Boolean isStandardParts;
    }
}
