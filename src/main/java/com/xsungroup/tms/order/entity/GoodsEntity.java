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
 * 商品表
 * </p>
 *
 * @author admin
 * @since 2019-08-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("o_goods")
public class GoodsEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
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
     * 商品长度，宽度，高度显示单位（mm,cm,dm,m）
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
     * 权重（用于排序，越大越靠前）
     */
    private Integer weight;
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
     * 创建人ID
     */
    private String createUserId;
    /**
     * 创建人名称
     */
    private String createUserName;
    /**
     * 创建人所属组织Id
     */
    private String createUserOrgId;
    /**
     * 创建人所属组织名称
     */
    private String createUserOrgName;
    /**
     * 所属组织Id(暂时是所属集团，树顶)
     */
    private String subordinateOrgId;
    /**
     * 所属组织名称
     */
    private String subordinateOrgName;

}
