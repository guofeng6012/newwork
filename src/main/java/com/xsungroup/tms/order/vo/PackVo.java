package com.xsungroup.tms.order.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 *
 * </p>
 *
 * @author admin
 * @since 2019-08-07 16:03:17
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PackVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 包装ID，主键
     */
    @ApiModelProperty("包装宽度")
    private String packId;
    /**
     * 创建人ID
     */
    @ApiModelProperty("包装宽度")
    private String createUserId;
    /**
     * 创建人名称
     */
    @ApiModelProperty("包装宽度")
    private String createUserName;
    /**
     * 创建人所属组织Id
     */
    @ApiModelProperty("包装宽度")
    private String createUserOrgId;
    /**
     * 创建人所属组织名称
     */
    @ApiModelProperty("包装宽度")
    private String createUserOrgName;
    /**
     * 包装规格代码
     */
    @ApiModelProperty("包装宽度")
    private String goodsPackCode;
    /**
     * 包装体积(cm³,立方厘米)
     */
    @ApiModelProperty("包装宽度")
    private Integer goodsPackColume;
    /**
     * 包装体积显示单位（cm³,dm³,m³）
     */
    @ApiModelProperty("包装宽度")
    private String goodsPackColumeShowUnit;
    /**
     * 包装高度(cm)
     */
    @ApiModelProperty("包装宽度")
    private Integer goodsPackHeight;
    /**
     * 包装长度(cm)
     */
    @ApiModelProperty("包装宽度")
    private Integer goodsPackLength;
    /**
     * 包装长度，宽度，高度显示单位（mm,cm,dm,m）
     */
    @ApiModelProperty("包装宽度")
    private String goodsPackLengthShowUnit;
    /**
     * 包装规格名称
     */
    @ApiModelProperty("包装宽度")
    private String goodsPackName;
    /**
     * 包装重量(g)
     */
    @ApiModelProperty("包装宽度")
    private Integer goodsPackWeight;
    /**
     * 包装重量显示单位（g,kg,t）
     */
    @ApiModelProperty("包装宽度")
    private String goodsPackWeightShowUnit;
    /**
     * 包装宽度(cm)
     */
    @ApiModelProperty("包装宽度")
    private Integer goodsPackWidth;
    /**
     * 删除标记 0:已删除 1:未删除
     */
    @ApiModelProperty("包装宽度")
    private Integer isAble;
    /**
     * 所属组织Id
     */
    @ApiModelProperty("包装宽度")
    private String subordinateOrgId;
    /**
     * 所属组织名称
     */
    @ApiModelProperty("包装宽度")
    private String subordinateOrgName;
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
     * 是否是标准件 null 未知 0 不是，1 是
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


}