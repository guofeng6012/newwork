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
 * 计量单位表
 * </p>
 *
 * @author Alex
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_unit_meas")
public class UnitMeasEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 计量单位ID
     */
    @TableId(value = "unit_meas_id", type = IdType.UUID)
    private String unitMeasId;

    /**
     * 计量单位编码
     */
    private String unitMeasCode;

    /**
     * 计量单位名称
     */
    private String unitMeasName;

    /**
     * 失效日期
     */
    private String expiryDate;



}
