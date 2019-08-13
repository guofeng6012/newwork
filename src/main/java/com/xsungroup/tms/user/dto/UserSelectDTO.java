package com.xsungroup.tms.user.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author GF
 * @Date 2019-8-1 17:17:29
 * @Description
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UserSelectDTO extends Convert {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 所属公司名称
     */
    private String companyId;

    /**
     * 所属角色
     */
    private String roleName;

    /**
     * 组织id
     */
    private String orgId;
}
