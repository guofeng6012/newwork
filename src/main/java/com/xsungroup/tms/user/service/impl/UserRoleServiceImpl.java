package com.xsungroup.tms.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.user.entity.UserRoleEntity;
import com.xsungroup.tms.user.mapper.UserRoleDao;
import com.xsungroup.tms.user.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2019-07-17
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleEntity> implements UserRoleService {

}
