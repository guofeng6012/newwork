package com.xsungroup.tms.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xsungroup.tms.core.supper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户与组织关系
 * </p>
 *
 * @author admin
 * @since 2019-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_user_org")
public class UserOrgEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.UUID)
    private String userOrgId;
    /**
     * 用户ID
     */
	private String userId;
    /**
     * 组织ID
     */
	private String orgId;
    /**
     * 权限类型 查看:0 创建:1
     */
	private Integer checkType;
    /**
     * 是否默认创建组织 0:否 1:是
     */
	private Integer isDefault;
    /**
     * 创建时间
     */
	private LocalDateTime createTime;
    /**
     * 创建人
     */
	private String createUser;
    /**
     * 更新人
     */
	private String updateUser;
    /**
     * 删除标记 0:已删除 1:未删除
     */
//	private Integer isAble;



}
