package com.xsungroup.tms.matedata.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.xsungroup.tms.core.supper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 何立辉
 * @since 2019-07-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_area_new")
public class AreaEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "area_id", type = IdType.UUID)
    private String id;

    /**
     * 区域编码
     */
    private String code;
    private String gbtCode;

    /**
     * 名称
     */
    private String name;

    private String parentId;

    /**
     * 等级
     */
    @TableField(value = "area_level")
    private Integer level;

    /**
     * 备注
     */
    private String memo;

    /**
     * 是否锁定
     */
    private String lockedFlag;

    /**
     * 显示序号
     */
    private Integer displayOrder;

    @TableField(value = "create_user")
    private String createUser;

    @TableField(value = "modify_user")
    private String modifyUser;

}
