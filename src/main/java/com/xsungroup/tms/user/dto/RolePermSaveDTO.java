package com.xsungroup.tms.user.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author GF
 * @Date 2019-8-5 16:40:45
 * @Description
 **/
@Data
public class RolePermSaveDTO {

    private List<String> permId;

    private String roleId;
}
