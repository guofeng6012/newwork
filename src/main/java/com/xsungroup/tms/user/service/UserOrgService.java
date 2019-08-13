package com.xsungroup.tms.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.user.entity.UserOrgEntity;

import java.util.List;


/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author Alex
 * @since 2019-07-17
 */
public interface UserOrgService extends IService<UserOrgEntity> {

   // 通过用户ID获取用户的创建组织的id
   List<String> userCreateOrgIds(String userId);

   int inserUserOrg(String userId,String orgId);
}

