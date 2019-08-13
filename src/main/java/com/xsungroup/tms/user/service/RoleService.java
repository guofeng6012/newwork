package com.xsungroup.tms.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.user.dto.RoleSelectDTO;
import com.xsungroup.tms.user.entity.RoleEntity;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Alex
 * @since 2019-07-17
 */
public interface RoleService extends IService<RoleEntity> {

    ResponseInfo list(Page page, RoleSelectDTO roleSelectDTO, String id);

    Object addOrEdit(RoleEntity roleEntity) throws BussException;

    int deleteRole(String roleId);

    List<RoleEntity> selectRoleByOrgId (String id);

}
