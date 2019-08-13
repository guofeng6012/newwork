package com.xsungroup.tms.order.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 梁建军
 * 创建日期： 2019/8/8
 * 创建时间： 14:16
 * @version 1.0
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PackUnitDto extends Convert implements Serializable {
    
    /**
     * 包装规格代码
     */
    @NotNull(groups = {PackUnitDto.Create.class, PackUnitDto.Update.class},message = "包装规格代码不能为空")
    @ApiModelProperty(value = "包装规格代码",required = true)
    private String packUnitCode;

    /**
     * 包装规格名称
     */
    @NotNull(groups = {PackUnitDto.Create.class, PackUnitDto.Update.class},message = "包装规格名称不能为空")
    @ApiModelProperty(value = "包装规格名称",required = true)
    private String packUnitName;

    /**
     * 权重（用于排序，越大越靠前）
     */
    @ApiModelProperty(" 权重（用于排序，越大越靠前）")
    private String weight;


    public interface Create {
    }

    public interface Update {
    }
}
