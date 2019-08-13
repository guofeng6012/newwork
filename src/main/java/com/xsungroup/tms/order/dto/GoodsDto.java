package com.xsungroup.tms.order.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.order.common.CheckParameters;
import com.xsungroup.tms.order.common.OrderBusCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author admin
 * @since 2019-08-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsDto extends Convert implements Serializable, CheckParameters {

    private static final long serialVersionUID = 1L;
    /**
     * 商品名称
     */
    @NotNull(groups = {GoodsDto.Create.class, GoodsDto.Update.class, GoodsAndPackDto.CreatePack.class}, message = "商品名称不能为空")
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
    @NotNull(groups = {GoodsDto.Create.class, GoodsDto.Update.class, GoodsAndPackDto.CreatePack.class}, message = "类型不能为空")
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
    @NotNull(groups = {GoodsDto.Create.class, GoodsDto.Update.class, GoodsAndPackDto.CreatePack.class}, message = "所属商品分类不能为空")
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

    @Override
    public void checkRequest() throws BussException {


        boolean lengthBool = getGoodsLength() != null;
        boolean weightBool = getGoodsWeight() != null;
        boolean heightBool = getGoodsHeight() != null;
        // 长宽高只要有一个，其他两都得有
        if (lengthBool || weightBool || heightBool) {
            if (lengthBool && weightBool && heightBool) {
                throw new BussException(OrderBusCode.LENGTH_WIDTH_HEIGHT_MUST_COEXIST);
            }

            if (getGoodsLengthShowUnit() == null) {
                throw new BussException(OrderBusCode.LACK_OF_COMMODITY_LENGTH_DISPLAY_UNITS);
            }
        }


//        boolean volume = getGoodsVolume() !=null;
//        boolean weight = getGoodsWeight() !=null;
//        //体积和重量必须有一个
//        if (volume && weight) {
//            throw new BussException(OrderBusCode.WEIGHT_VOLUME_MUST_HAVE_ONE);
//        }
//        if (volume) {
//            if (getGoodsVolumeShowUnit() == null) {
//                throw new BussException(OrderBusCode.LACK_OF_VOLUME_UNITS);
//            }
//        }
//        if (weight) {
//            if (getGoodsWeightShowUnit() == null) {
//                throw new BussException(OrderBusCode.LACK_OF_WEIGHT_UNITS);
//            }
//        }
    }


    public interface Create {
    }

    public interface Update {
    }

}
