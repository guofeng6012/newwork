package com.xsungroup.tms.user.vo;


import com.xsungroup.tms.user.entity.PermEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


/**
 * <p>
 * 登录Vo
 * </p>
 *
 * @author 何立辉
 * @since 2019-07-31 10:39:37
 */

@Getter
@Setter
@ToString
public class CurrentUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String userId;
    /**
     * 账号
     */
    private String userName;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 状态
     */
    private int userStatus;

    /**
     * 企业审核状态
     */
    private int auditStatus;

    /**
     * 账户类型 0:企业 1:个人货主
     */
    private int bizType;


    /**
     * 所属组织名称
     */
    private String orgName;

    /**
     * 所属组织id
     */
    private String orgId;

    /**
     * 所属集团名称
     */
    private String groupName;

    /**
     * 所属集团id
     */
    private String groupId;

    /**
     * 所属公司（单位）名称
     */
    private String companyName;

    /**
     * 所属公司（单位）id
     */
    private String companyId;

    /**
     * token
     */
    private String token;

    /**
     * 刷新token
     */
    private String refreshToken;

    /**
     * 路由权限
     */
    private List<PermEntity> menus;

    /**
     * api接口权限
     */
    private List<PermEntity> opts;

}