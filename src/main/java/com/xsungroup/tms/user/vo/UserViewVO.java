package com.xsungroup.tms.user.vo;

import lombok.Data;

/**
 * @Author GF
 * @Date 2019-8-1 19:31:28
 * @Description
 **/
@Data
public class UserViewVO {

    /**
     * 账户
     */
    private String  userName;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 关联手机号
     */
    private String phoneNo;
    /**
     *  角色名称
     */
    private String roleName;
    /**
     *  顶级组织
     */
    private String groupName;
    /**
     * 最后登录时间
     */
    private String lastLoginTime;
    /**
     *  账户状态
     */
    private String auditStatus;

    private String userStatus;

    private String roleId;

    /**
     *
     */
    private String isSuper;
    /**
     *  所属单位
     */
    private String orgName;
    /**
     *  创建组织
     */
    private String createOrgName;

    private String userId;

    private String orgId;
}
