package com.xsungroup.tms.user.vo;

import com.xsungroup.tms.user.entity.TreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * <p>
 * 权限
 * </p>
 *
 * @author 何立辉
 * @since 2019-08-12 10:57:45
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PermVo extends TreeNode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组件地址
     */
    private String component;
    /**
     * 图标
     */
    private String iconClass;
    /**
     * 代码
     */
    private String permissionCode;
    /**
     * 名称
     */
    private String permissionName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 类型  1:菜单  0:按钮
     */
    private String type;
    /**
     * 链接
     */
    private String url;

    /**
     * 是否隐藏
     */
    private int flgHidden;



}