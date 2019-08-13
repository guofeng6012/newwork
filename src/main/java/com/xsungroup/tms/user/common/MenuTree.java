package com.xsungroup.tms.user.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author GF
 * @Date 2019-8-5 16:24:16
 * @Description
 **/
@Data
public class MenuTree{

    private String id;
    private String name;
    private String code;
    private String parentId;
    private String url;
    private String iconClass;
    private Integer sort;
    private String component;
    /**
     * 是否选中  1：选中  0：未选中
     */
    private String checked = "0";
    private List<MenuTree> menuList = new ArrayList<>();

}
