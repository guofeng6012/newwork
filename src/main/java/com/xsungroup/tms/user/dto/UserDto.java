package com.xsungroup.tms.user.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import lombok.Data;

import java.util.List;


@Data
public class UserDto extends Convert {

    /**
     * 主键
     */
    private String userId;


    /**
     * 用户名
     */
    private String userName;

    /**
     * 所属集团ID
     */
    private String groupId;

    /**
     * 单位ID
     */
    private String orgId;

    /**
     * 角色id
     */
    private String roleId;

    private Integer userStatus;


    /**
     * 顶级组织
     */
    private String topId;

    /**
     * 创建组织ID
     */
    private String companyId;

    /**
     * 创建组织范围id
     */
    private List<String> createList;

    /**
     * 查看组织范围id
     */
    private List<String> viewList;


    /**
     * 登陆密码
     */
    private String password;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 邮箱
     */
    private String email;

}
