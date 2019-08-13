package com.xsungroup.tms.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xsungroup.tms.core.supper.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户管理(此表不是备份，用于平台用户管理)
 * </p>
 *
 * @author Alex
 * @since 2019-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_user")
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.UUID)
    @ApiModelProperty(hidden = true)
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
     * 所属组织ID
     */
    private String orgId;

    /**
     * 顶级组织
     */
    private String topId;

    /**
     * 所属组织ID
     */
    private String companyId;


    /**
     * 顶级组织
     */
    private String roleId;

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

    /**
     * 状态 0:无效  1:有效  2:离职
     */
    private Integer userStatus;

    /**
     * 是否超级管理员
     */
    private Integer isSuper;


    /**
     * 最近登陆时间
     */
    private String lastLoginTime;

    private String createUser;

    private String updateUser;



}
