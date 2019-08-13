package com.xsungroup.tms.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xsungroup.tms.core.supper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author 梁建军
 * 创建日期： 2019/8/8
 * 创建时间： 14:16
 * @version 1.0
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("o_pack_unit")
public class PackUnitEntity extends BaseEntity {


    /**
     * 包装ID，主键
     */
    @TableId(type = IdType.UUID)
    private String packUnitId;
    
    /**
     * 包装规格代码
     */
    private String packUnitCode;

    /**
     * 包装规格名称
     */
    private String packUnitName;

    /**
     * 权重（用于排序，越大越靠前）
     */
    private String weight;
}
