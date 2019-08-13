package com.xsungroup.tms.user.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author GF
 * @Date 2019-7-30 19:39:53
 * @Description
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class OrgByPageSelectDTO extends Convert {

    /**
     *
     */
    private String groupId;

    /**
     *
     */
    private String orgId;

    /**
     * 企业名称
     */
    private String orgName;

    /**
     * 企业来源 0:注册 1:创建',
     */
    private String orgSource;

    /**
     * 企业类型 0:承运商 1:货主 2:两者都是',
     */
    private String orgRole;

    /**
     * 是否审核 '审核状态 0:待审核  1:审核拒绝  2:审核通过',
     */
    private String auditStatus;
}
