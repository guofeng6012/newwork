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
 * 商品分类
 * </p>
 *
 * @author 梁建军
 * @since 2019-08-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("o_goods_category")
public class GoodsCategoryEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 货物分类ID
     */
    @TableId(type = IdType.UUID)
    private String goodsCategoryId;
    /**
     * 父类ID
     */
    private String goodsCategoryParentId;
    /**
     * 等级
     */
    private Integer goodsCategoryLevel;
    /**
     * 货物分类编码
     */
    private String goodsCategoryCode;
    /**
     * 货物分类名称
     */
    private String goodsCategoryName;
    /**
     * 类型 1：普通货物，2：冷链运输，3：普通/冷链，4：危险品
     */
    private Integer goodsCategoryType;
    /**
     * 权重（用于排序，越大越靠前）
     */
    private Integer weight;

}
