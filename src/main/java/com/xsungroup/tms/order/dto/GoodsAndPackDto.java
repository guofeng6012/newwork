package com.xsungroup.tms.order.dto;

import com.xsungroup.tms.core.exception.BussException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author 梁建军
 * 创建日期： 2019/8/9
 * 创建时间： 15:06
 * @version 1.0
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsAndPackDto extends GoodsDto {


    @Valid
    @NotNull(groups = {GoodsAndPackDto.CreatePack.class, GoodsAndPackDto.CreatePack.class}, message = "包装列表不能为空")
    @ApiModelProperty(value = "包装列表", required = true)
    @Size(min = 1, groups = {GoodsAndPackDto.CreatePack.class, GoodsAndPackDto.CreatePack.class}, message = "包装列表不能为空")
    private List<GoodsPackDto> packList;

    @Override
    public void checkRequest() throws BussException {
        super.checkRequest();
        if (getPackList()!=null) {
            for (GoodsPackDto goodsPackDto : getPackList()) {
                goodsPackDto.checkRequest();
            }
        }
    }

    public interface CreatePack {

    }
}
