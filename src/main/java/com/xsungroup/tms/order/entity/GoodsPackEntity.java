package com.xsungroup.tms.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xsungroup.tms.core.supper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品包装
 * </p>
 *
 * @author admin
 * @since 2019-08-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("o_goods_pack")
public class GoodsPackEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableId(type = IdType.UUID)
	private String goodsPackId;
    /**
     * 商品ID
     */
	private String goodsId;
    /**
     * 包装规格代码
     */
	private String goodsPackCode;
    /**
     * 包装规格名称
     */
	private String goodsPackName;
    /**
     * 包装长度(cm)
     */
	private Integer goodsPackLength;
    /**
     * 包装宽度(cm)
     */
	private Integer goodsPackWidth;
    /**
     * 包装高度(cm)
     */
	private Integer goodsPackHeight;
    /**
     * 包装长度，宽度，高度显示单位（mm,cm,dm,m）
     */
	private String goodsPackLengthShowUnit;
    /**
     * 包装体积(cm³,立方厘米)
     */
	private Integer goodsPackVolume;
    /**
     * 包装体积显示单位（cm³,dm³,m³）
     */
	private String goodsPackVolumeShowUnit;
    /**
     * 包装重量(g)
     */
	private Integer goodsPackWeight;
    /**
     * 包装重量显示单位（g,kg,t）
     */
	private String goodsPackWeightShowUnit;
	/**
	 * 权重（用于排序，越大越靠前）
	 */
	private Integer weight;
	/**
	 * 包装Id
	 */
	private String packUnitId;
	/**
	 * 包装单位code
	 */
	private String packUnitCode;
	/**
	 * 单位名称
	 */
	private String packUnitName;
	/**
	 * 是否是标准件 0 不是，1 是
	 */
	private Integer isStandardParts;

}
