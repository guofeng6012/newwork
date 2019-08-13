package com.xsungroup.tms.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import com.xsungroup.tms.user.dto.RolePermSaveDTO;
import com.xsungroup.tms.user.entity.PermEntity;
import com.xsungroup.tms.user.entity.RolePermEntity;
import com.xsungroup.tms.user.entity.UserRoleEntity;
import com.xsungroup.tms.user.mapper.PermDao;
import com.xsungroup.tms.user.mapper.RolePermDao;
import com.xsungroup.tms.user.mapper.UserRoleDao;
import com.xsungroup.tms.user.service.PermService;
import lombok.extern.slf4j.Slf4j;
import org.aabss.utils.tree.MenuTree;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2019-07-17
 */
@Service
@Slf4j
public class PermServiceImpl extends ServiceImpl<PermDao, PermEntity> implements PermService {

    @Autowired
    private PermDao permDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RolePermDao rolePermDao;

    @Override
    public List<MenuTree> findRoleOfMenu(String UserId) {
        List<MenuTree> allMenu = getMenuList(UserId);
        return getMenuTree(allMenu);
    }

    @Override
    public List<com.xsungroup.tms.user.common.MenuTree> findByPermList(String roleId) {
        log.info("查询角色权限信息：{}", roleId);
        QueryWrapper<PermEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_able", 1);
        QueryWrapper<RolePermEntity> roleEntityQueryWrapper = new QueryWrapper<>();
        if(!Strings.isNullOrEmpty(roleId)){
            roleEntityQueryWrapper.eq("role_id", roleId);
        }
        List<com.xsungroup.tms.user.common.MenuTree> rolePermEntityList = permDao.findByPermList(roleId);

        return getByRolePerm(rolePermEntityList);
    }

    @Transactional
    @Override
    public void addRolePerm(RolePermSaveDTO list) {
        log.info("新增角色权限:{}", list);
        List<RolePermEntity> rolePermEntityList = new ArrayList<>();
        BeanUtils.copyProperties(list, rolePermEntityList);
        permDao.updatePermRole(list.getRoleId());
        for (String permId : list.getPermId()) {
            RolePermEntity rolePermEntity = new RolePermEntity();
            rolePermEntity.setPermissionId(permId);
            rolePermEntity.setRoleId(list.getRoleId());
            rolePermDao.insert(rolePermEntity);
        }
    }

    private List<com.xsungroup.tms.user.common.MenuTree> getByRolePerm(List<com.xsungroup.tms.user.common.MenuTree> permEntityList) {
        List<com.xsungroup.tms.user.common.MenuTree> list = new ArrayList<>();
        for (com.xsungroup.tms.user.common.MenuTree permEntity : permEntityList) {
            if ("0".equals(permEntity.getParentId())) {
                com.xsungroup.tms.user.common.MenuTree menuTree = new com.xsungroup.tms.user.common.MenuTree();
                BeanUtils.copyProperties(permEntity, menuTree);
                menuTree.setMenuList(getBychildrens(permEntityList, permEntity.getId()));
                list.add(menuTree);
            }
        }
        return list;
    }

    private List<com.xsungroup.tms.user.common.MenuTree> getBychildrens(List<com.xsungroup.tms.user.common.MenuTree> permEntityList, String permId) {
        List<com.xsungroup.tms.user.common.MenuTree> list = new ArrayList<>();
        for (com.xsungroup.tms.user.common.MenuTree permEntity : permEntityList) {
            if (permId.equals(permEntity.getParentId())) {
                com.xsungroup.tms.user.common.MenuTree menuTree = new com.xsungroup.tms.user.common.MenuTree();
                BeanUtils.copyProperties(permEntity, menuTree);
                menuTree.setMenuList(getBychildrens(permEntityList, permEntity.getId()));
                list.add(menuTree);
            }
        }
        return list;
    }


    private List<MenuTree> getMenuList(String currentUserId) {
        QueryWrapper wrapper = new QueryWrapper<UserRoleEntity>();
        wrapper.eq("user_id", currentUserId);
        List<UserRoleEntity> userRoleList = userRoleDao.selectList(wrapper);
        if (userRoleList == null || userRoleList.size() <= 0) {
            return new ArrayList<>();
        }
        UserRoleEntity userRole = userRoleList.get(0);
        List<PermEntity> permEntities = permDao.getPermByRoleId(userRole.getRoleId());
        return map2MenuList(permEntities);
    }


    private List<MenuTree> getMenuTree(List<MenuTree> allMenu) {
        // 一级菜单
        List<MenuTree> rootMenuList = new ArrayList<>();
        allMenu.stream().forEach(menu -> {
            if ("0".equals(menu.getParent_id())) {
                rootMenuList.add(menu);
            }
        });
        Collections.sort(rootMenuList);//排序
        // 为一级菜单设置子菜单
        for (MenuTree menu : rootMenuList) {
            /* 获取根节点下的所有子节点 使用getChild方法*/
            List<MenuTree> childMenuList = getChild(menu.getId(), allMenu);
            menu.setMenuList(childMenuList);//给根节点设置子节点
        }
        return rootMenuList;
    }


    /**
     * 获取子节点
     *
     * @param id      父节点 id
     * @param allMenu 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    private List<MenuTree> getChild(String id, List<MenuTree> allMenu) {
        List<String> childIdList = new ArrayList<>();
        //子菜单
        List<MenuTree> childList = new ArrayList<>();
        for (MenuTree menu : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if (menu.getParent_id().equals(id)) {
                childList.add(menu);
                childIdList.add(menu.getId());
            }
        }
        //递归
        for (MenuTree menu : childList) {
            menu.setMenuList(getChild(menu.getId(), allMenu));
        }
        Collections.sort(childList);//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return new ArrayList<>();
        }
        return childList;
    }


    private List<MenuTree> map2MenuList(List<PermEntity> permEntities) {
        List<MenuTree> roleOfMenu = new ArrayList<>();
        for (PermEntity permEntitie : permEntities) {
            MenuTree menuTree = new MenuTree();
            menuTree.setId(permEntitie.getPermissionId());
            menuTree.setName(permEntitie.getPermissionName());
            menuTree.setCode(permEntitie.getPermissionCode());
            menuTree.setIcon_class(permEntitie.getIconClass());
            menuTree.setSort(permEntitie.getSort());
            menuTree.setUrl(permEntitie.getUrl());
            menuTree.setParent_id(permEntitie.getParentId());
            menuTree.setComponent(permEntitie.getComponent());
            roleOfMenu.add(menuTree);
        }
        return roleOfMenu;
    }

}
