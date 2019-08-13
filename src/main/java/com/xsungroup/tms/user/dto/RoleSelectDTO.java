package com.xsungroup.tms.user.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author
 * @Date
 * @Description
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleSelectDTO extends Convert {

    /**
     * 角色代码
     */
    private String roleCode;

    /**
     * 角色名字
     */
    private String roleName;

    /**
     * 所属组织
     */
    private String groupId;

    /**
     * 创建组织
     */
    private String orgId;

    /**
     * id
     */
   // private String orgId;

}
