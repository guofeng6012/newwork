package com.xsungroup.tms.order.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * @author 梁建军
 * 创建日期： 2019/8/5
 * 创建时间： 15:29
 * @version 1.0
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsCategoryDto extends Convert implements Serializable {

    /**
     * 父类ID
     */
    @Null(groups = {GoodsCategoryDto.Update.class}, message = "货物分类名称不能为空")
    @ApiModelProperty("父类ID")
    private String goodsCategoryParentId;
    /**
     * 货物分类编码
     */
    @ApiModelProperty("货物分类编码")
    private String goodsCategoryCode;
    /**
     * 货物分类名称
     */
    @NotNull(groups = {GoodsCategoryDto.Create.class, GoodsCategoryDto.Update.class}, message = "货物分类名称不能为空")
    @ApiModelProperty(value = "货物分类名称",required = true)
    private String goodsCategoryName;
    /**
     * 类型 1：普通货物，2：冷链运输，3：普通/冷链，4：危险品
     */
    @NotNull(groups = {GoodsCategoryDto.Create.class, GoodsCategoryDto.Update.class}, message = "货物类型不能为空")
    @ApiModelProperty(value = "当有父类的时候，这个参数会继承父类，类型 1：普通货物，2：冷链运输，3：普通/冷链，4：危险品",required = true)
    private Integer goodsCategoryType;
    /**
     * 权重（用于排序，越大越靠前）
     */
    @ApiModelProperty(value = "权重（用于排序，越大越靠前）",required = true)
    private Integer weight;

    public interface Create {
    }

    public interface Update {
    }
}
