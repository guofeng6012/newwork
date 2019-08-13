package com.xsungroup.tms.user.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author GF
 * @Date 2019-8-3 11:39:51
 * @Description
 **/
@Data
public class RoleViewVO {

    private String roleId;

    /**
     * 角色代码
     */
    private String roleCode;

    /**
     * 角色名字
     */
    private String roleName;

    /**
     * 创建组织
     */
    private String orgName;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     *
     */
    private String orgId;
}
