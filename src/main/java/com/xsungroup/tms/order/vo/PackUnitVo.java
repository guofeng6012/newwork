package com.xsungroup.tms.order.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 梁建军
 * 创建日期： 2019/8/8
 * 创建时间： 14:16
 * @version 1.0
 * @since 1.0
 */
@Data
public class PackUnitVo {


    /**
     * 包装ID，主键
     */
    @ApiModelProperty("包装ID，主键")
    private String packUnitId;

    /**
     * 包装规格代码
     */
    @ApiModelProperty("包装规格代码")
    private String packUnitCode;

    /**
     * 包装规格名称
     */
    @ApiModelProperty("包装规格名称")
    private String packUnitName;

    /**
     * 权重（用于排序，越大越靠前）
     */
    @ApiModelProperty("权重（用于排序，越大越靠前）")
    private String weight;

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
