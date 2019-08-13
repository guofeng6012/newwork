package com.xsungroup.tms.order.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author admin
 * @since 2019-08-08
 */
@Data
public class GoodsVo {

    private static final long serialVersionUID = 1L;
    /**
     * 商品名称
     */
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
    @ApiModelProperty(value = "类型 1：普通货物，2：冷链运输，3：普通/冷链，4：危险品", required = true)
    private Integer goodsType;
    /**
     * 物品危险等级（需要了解）
     * todo
     */
    @ApiModelProperty(value = "物品危险等级（需要了解）")
    private String goodsHazardLevel;
    /**
     * 所属商品分类
     */
    @ApiModelProperty(value = "所属商品分类", required = true)
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
    @ApiModelProperty(value = "商品高度")
    private Integer goodsHeight;
    /**
     * 商品长度，宽度，高度显示单位（mm,cm,dm,m）
     */
    @ApiModelProperty(value = "商品长度，宽度，高度显示单位（mm,cm,dm,m）")
    private String goodsLengthShowUnit;
    /**
     * 商品体积(cm³,立方厘米)
     */
    @ApiModelProperty(value = "商品体积(cm³,立方厘米)")
    private Integer goodsVolume;
    /**
     * 商品体积显示单位（cm³,dm³,m³）
     */
    @ApiModelProperty(value = "商品体积显示单位（cm³,dm³,m³）")
    private String goodsVolumeShowUnit;
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
     * 权重（用于排序，越大越靠前）
     */
    @ApiModelProperty(value = "权重（用于排序，越大越靠前）")
    private Integer weight;
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
