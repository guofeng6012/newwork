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
 * 订单商品信息
 * </p>
 *
 * @author admin
 * @since 2019-08-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("o_order_goods")
public class OrderGoodsEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
	private String orderGoodsId;
    /**
     * 商品ID
     */
	private String goodsId;
    /**
     * 商品名称
     */
	private String goodsName;
    /**
     * 商品代码
     */
	private String goodsCode;
    /**
     * 类型 1：普通货物，2：冷链运输，3：普通/冷链，4：危险品
     */
	private Integer goodsType;
    /**
     * 物品危险等级（需要了解）
     */
	private String goodsHazardLevel;
    /**
     * 所属商品分类
     */
	private String goodsCategoryId;
    /**
     * 商品图片地址
     */
	private String goodsPic;
    /**
     * 商品长度(cm)
     */
	private Integer goodsLength;
    /**
     * 商品宽度(cm)
     */
	private Integer goodsWidth;
    /**
     * 商品高度(cm)
     */
	private Integer goodsHeight;
    /**
     * 商品长度，宽度，高度显示单位（cm,dm,m）
     */
	private String goodsLengthShowUnit;
    /**
     * 商品体积(cm³,立方厘米)
     */
	private Integer goodsVolume;
    /**
     * 商品体积显示单位（cm³,dm³,m³）
     */
	private String goodsVolumeShowUnit;
    /**
     * 商品重量(g)
     */
	private Integer goodsWeight;
    /**
     * 商品重量显示单位（g,kg,t）
     */
	private String goodsWeightShowUnit;
    /**
     * 最低温(单位℃)
     */
	private Integer lowTemp;
    /**
     * 最高温(单位℃)
     */
	private Integer highTemp;
    /**
     * 运输备注
     */
	private String transportRemarks;
    /**
     * 商品包装ID
     */
	private String goodsPackId;
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
     * 包装内商品数量
     */
	private Integer goodsNum;
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
	private Boolean isStandardParts;

}
