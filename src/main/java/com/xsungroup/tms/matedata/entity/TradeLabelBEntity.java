package com.xsungroup.tms.matedata.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xsungroup.tms.core.supper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 行业标签子表
 * </p>
 *
 * @author Alex
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_trade_label_b")
public class TradeLabelBEntity extends BaseEntity {

    /**
     * ID
     */
    @TableId(value = "trade_label_b_id", type = IdType.UUID)
    private String tradeLabelBId;


    /**
     * 父级ID
     */
    private String tradeLabelId;

    /**
     * 行业编码
     */
    private String code;

    /**
     * 行业名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;


}
