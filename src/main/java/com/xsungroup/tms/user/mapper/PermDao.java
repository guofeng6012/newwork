package com.xsungroup.tms.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xsungroup.tms.user.entity.PermEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermDao extends BaseMapper<PermEntity> {
    @Select("SELECT perm.permission_id, perm.permission_name, perm.permission_code, perm.icon_class, perm.sort, perm.url, perm.parent_id, perm.component\n" +
            "        from c_permission perm\n" +
            "        left join  c_role_permission relate  on perm.permission_id = relate.permission_id\n" +
            "        WHERE perm.type = '1'\n" +
            "        and relate.role_id = #{roleId}")
    List<PermEntity> getPermByRoleId(String roleId);

    List<com.xsungroup.tms.user.common.MenuTree> findByPermList(String roleId);

    int updatePermRole(String roleId);


    /**
     * 根据用户ID 和 组织ID（集团） 查询权限集合
     * 如果用户是管理员 查询企业权限  反之则查询用户权限
     * @param userId
     * @param orgId
     * @return
     */
    List<PermEntity> findUserPermList(@Param("userId") String userId,@Param("orgId") String orgId);

}
