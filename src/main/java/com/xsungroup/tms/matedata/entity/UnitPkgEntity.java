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
 * 包装单位表
 * </p>
 *
 * @author Alex
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_unit_pkg")
public class UnitPkgEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 包装单位ID
     */
    @TableId(value = "unit_pkg_id", type = IdType.UUID)
    private String unitPkgId;

    /**
     * 包装单位编码
     */
    private String unitPkgCode;

    /**
     * 包装单位名称
     */
    private String unitPkgName;

    /**
     * 失效日期
     */
    private String expiryDate;



}
