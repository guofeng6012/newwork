package com.xsungroup.tms.user.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author GF
 * @Date 2019-8-6 13:08:19
 * @Description
 **/
@Data
public class UserOrgViewVO {

    private List<String> createList;

    private List<String> viewList;
}
