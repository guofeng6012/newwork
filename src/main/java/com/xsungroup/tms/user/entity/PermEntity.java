package com.xsungroup.tms.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xsungroup.tms.core.supper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author Alex
 * @since 2019-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_permission")
public class PermEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "permission_id")
    private String permissionId;

    /**
     * 代码
     */
    private String permissionCode;

    /**
     * 名称
     */
    private String permissionName;


    /**
     * 父级菜单 id
     */
    private String parentId;

    /**
     * 链接
     */
    private String url;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 类型  1:菜单  0:按钮
     */
    private String type;

    /**
     * 图标
     */
    private String iconClass;

    /**
     * 是否隐藏
     */
    private int flgHidden;


}
