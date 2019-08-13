package com.xsungroup.tms.order.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 梁建军
 * 创建日期： 2019/8/5
 * 创建时间： 15:29
 * @version 1.0
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsCategoryVo implements Serializable {

    /**
     * 货物分类ID
     */
    @ApiModelProperty("货物分类ID")
    private String goodsCategoryId;
    /**
     * 父类ID
     */
    @ApiModelProperty("父类ID")
    private String goodsCategoryParentId;
    /**
     * 等级
     */
    @ApiModelProperty("等级")
    private Integer goodsCategoryLevel;
    /**
     * 货物分类编码
     */
    @ApiModelProperty("货物分类编码")
    private String goodsCategoryCode;
    /**
     * 货物分类名称
     */
    @ApiModelProperty("货物分类名称")
    private String goodsCategoryName;
    /**
     * 类型 1：普通货物，2：冷链运输，3：危险品
     */
    @ApiModelProperty("类型 1：普通货物，2：冷链运输，3：危险品")
    private Integer goodsCategoryType;
    /**
     * 权重（用于排序，越大越靠前）
     */
    @ApiModelProperty("权重（用于排序，越大越靠前）")
    private Integer weight;

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
    /**
     * 子类
     */
    @ApiModelProperty("子类")
    private List<GoodsCategoryVo> children;
}
