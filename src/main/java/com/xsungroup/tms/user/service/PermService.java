package com.xsungroup.tms.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.user.dto.RolePermSaveDTO;
import com.xsungroup.tms.user.entity.PermEntity;
import org.aabss.utils.tree.MenuTree;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author Alex
 * @since 2019-07-17
 */
public interface PermService extends IService<PermEntity> {
    List<MenuTree> findRoleOfMenu(String  UserId);

    List<com.xsungroup.tms.user.common.MenuTree> findByPermList(String roleId);

    void addRolePerm(RolePermSaveDTO rolePermSaveDTOList);
}
