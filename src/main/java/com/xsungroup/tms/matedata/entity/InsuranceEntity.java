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
 * 险种大类表
 * </p>
 *
 * @author GF
 * @since 2019-07-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_insurance")
public class InsuranceEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 险种分类ID
     */
    @TableId(value = "insurance_id", type = IdType.UUID)
    private String insuranceId;

    /**
     * 险种分类编码
     */
    private String insuranceCode;

    /**
     * 险种父级id
     */
    private String parentId;

    /**
     * 险种类型 1-大险种 2-小险种
     */
    private Integer type;

    /**
     * 险种分类名称
     */
    private String insuranceName;

    /**
     * 失效日期
     */
    private String expiryDate;


}
