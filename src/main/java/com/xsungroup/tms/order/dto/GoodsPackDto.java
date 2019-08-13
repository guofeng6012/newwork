package com.xsungroup.tms.order.dto;

import com.xsungroup.tms.core.common.AssertBuss;
import com.xsungroup.tms.core.common.modelmapper.Convert;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.order.common.CheckParameters;
import com.xsungroup.tms.order.common.OrderBusCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.xsungroup.tms.order.common.OrderBusCode.LACK_OF_WEIGHT_UNITS;
import static com.xsungroup.tms.order.common.OrderBusCode.WEIGHT_VOLUME_CANNOT_BE_EMPTY_SAME_TIME;

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
@EqualsAndHashCode(callSuper = false)
public class GoodsPackDto extends Convert implements Serializable, CheckParameters {

    private static final long serialVersionUID = 1L;
    /**
     * 商品ID
     */
    @NotNull(groups = {GoodsPackDto.Create.class, GoodsPackDto.Update.class}, message = "商品Id不能为空")
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
     * 包装长度，宽度，高度显示单位（cm,dm,m）
     */
    @Pattern(regexp = "cm|dm|m", groups = {PackDto.Create.class, PackDto.Update.class, GoodsAndPackDto.CreatePack.class}, message = "包装长度单位错误")
    @ApiModelProperty("包装长度，宽度，高度显示单位（cm,dm,m）")
    private String goodsPackLengthShowUnit;

    /**
     * 包装规格名称
     */
    @NotNull(groups = {GoodsPackDto.Create.class, GoodsPackDto.Update.class, GoodsAndPackDto.CreatePack.class}, message = "包装规格名称不能为空")
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
    @Pattern(regexp = "g|kg|t", groups = {PackDto.Create.class, PackDto.Update.class, GoodsAndPackDto.CreatePack.class}, message = "包装单位错误")
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
    @NotNull(groups = {PackDto.Create.class, PackDto.Update.class}, message = "包装Id不能为空")
    @ApiModelProperty("包装Id")
    private String packUnitId;
    /**
     * 包装单位code
     */
    @NotNull(groups = {PackDto.Create.class, PackDto.Update.class}, message = "包装单位code不能为空")
    @ApiModelProperty("包装单位code")
    private String packUnitCode;
    /**
     * 包装单位名称
     */
    @NotNull(groups = {PackDto.Create.class, PackDto.Update.class}, message = "包装单位名称不能为空")
    @ApiModelProperty("包装单位名称")
    private String packUnitName;

    /**
     * 是否是标准件 0 不是，1 是
     */
    @NotNull(groups = {PackDto.Create.class, PackDto.Update.class}, message = "是否是标准件不能为空")
    @ApiModelProperty("是否是标准件 0 不是，1 是")
    private Integer isStandardParts;

    public interface Create {
    }

    public interface Update {
    }

    @Override
    public void checkRequest() throws BussException {


        boolean lengthBool = getGoodsPackLength() != null;
        boolean widthBool = getGoodsPackWidth() != null;
        boolean heightBool = getGoodsPackHeight() != null;
        // 长宽高只要有一个，其他两都得有
        if (lengthBool || widthBool || heightBool) {
            if (!(lengthBool && widthBool && heightBool)) {
                throw new BussException(OrderBusCode.LENGTH_WIDTH_HEIGHT_MUST_COEXIST);
            }

            if (getGoodsPackLengthShowUnit() == null) {
                throw new BussException(OrderBusCode.LACK_OF_COMMODITY_LENGTH_DISPLAY_UNITS);
            }
        } else {
            AssertBuss.isFalse(getGoodsPackWeight() == null, WEIGHT_VOLUME_CANNOT_BE_EMPTY_SAME_TIME);
            AssertBuss.notNull(getGoodsPackWeightShowUnit(), LACK_OF_WEIGHT_UNITS);
        }
    }
}