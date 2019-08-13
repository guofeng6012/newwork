package com.xsungroup.tms.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xsungroup.tms.core.supper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author GF
 * @Date 2019-8-5 13:53:30
 * @Description
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_role_permission")
public class RolePermEntity extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "role_permission_id", type = IdType.UUID)
    private String rolePermissionId;

    private String roleId;

    private String permissionId;
}
