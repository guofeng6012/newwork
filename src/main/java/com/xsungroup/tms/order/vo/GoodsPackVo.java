package com.xsungroup.tms.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author admin
 * @since 2019-08-09 14:03:41
 */

@ApiModel
@Data
public class GoodsPackVo {

    private static final long serialVersionUID = 1L;
    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", required = true)
    private String goodsId;
    /**
     * 包装规格代码
     */
    @ApiModelProperty("包装规格代码")
    private String goodsPackCode;

    /**
     * 包装高度(cm)
     */
    @ApiModelProperty("包装高度(cm)")
    private Integer goodsPackHeight;

    /**
     * 包装长度(cm)
     */
    @ApiModelProperty("包装长度(cm)")
    private Integer goodsPackLength;

    /**
     * 包装长度，宽度，高度显示单位（mm,cm,dm,m）
     */
    @ApiModelProperty("包装长度，宽度，高度显示单位（mm,cm,dm,m）")
    private String goodsPackLengthShowUnit;

    /**
     * 包装规格名称
     */
    @ApiModelProperty(value = "包装规格名称", required = true)
    private String goodsPackName;

    /**
     * 包装重量(g)
     */
    @ApiModelProperty("包装重量(g)")
    private Integer goodsPackWeight;

    /**
     * 包装重量显示单位（g,kg,t）
     */
    @ApiModelProperty("包装重量显示单位（g,kg,t）")
    private String goodsPackWeightShowUnit;

    /**
     * 包装宽度(cm)
     */
    @ApiModelProperty("包装宽度(cm)")
    private Integer goodsPackWidth;
    /**
     * 包装Id
     */
    @ApiModelProperty("包装Id")
    private String packUnitId;
    /**
     * 包装单位code
     */
    @ApiModelProperty("包装单位code")
    private String packUnitCode;
    /**
     * 包装单位名称
     */
    @ApiModelProperty("包装单位名称")
    private String packUnitName;

    /**
     * 是否是标准件 0 不是，1 是
     */
    @ApiModelProperty("是否是标准件 0 不是，1 是")
    private Integer isStandardParts;
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