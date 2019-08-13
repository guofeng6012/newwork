package com.xsungroup.tms.user.vo;

import com.xsungroup.tms.user.entity.TreeNode;
import lombok.Data;

/**
 * @Author GF
 * @Date 2019-8-5 13:35:30
 * @Description
 **/
@Data
public class PermTreeViewVO extends TreeNode {

    /**
     * 是否选中
     */
    private String isCheck;


}
