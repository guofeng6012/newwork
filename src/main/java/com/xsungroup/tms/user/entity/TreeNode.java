package com.xsungroup.tms.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形实体父类
 */


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TreeNode {

    private String id;
    private String parentId;
    private Integer level;

    private List<TreeNode> childrens = new ArrayList<>();

}
