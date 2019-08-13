package com.xsungroup.tms.user.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import lombok.Data;

import java.util.List;

/**
 * @Author GF
 * @Date 2019-8-1 09:25:07
 * @Description
 **/
@Data
public class OrgTreeDTO extends Convert {

    private String orgId;

    private String orgName;

    private Integer auditStatus;

    private Integer orgRole;

    private List<OrgTreeDTO> treeDTOList;
}
