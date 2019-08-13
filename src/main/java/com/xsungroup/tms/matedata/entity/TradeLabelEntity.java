package com.xsungroup.tms.matedata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xsungroup.tms.core.supper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 行业标签表
 * </p>
 *
 * @author Alex
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_trade_label")
public class TradeLabelEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "trade_label_id", type = IdType.UUID)
    private String tradeLabelId;

    /**
     * 行业编码
     */
    private String tradeLabelCode;

    /**
     * 行业名称
     */
    private String tradeLabelName;

    /**
     * 备注
     */
    private String remark;



    /**
     * 子表信息
     */
    @TableField(exist = false)
    private List<TradeLabelBEntity> tradeLabelBEntitys;

}
