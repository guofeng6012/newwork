package com.xsungroup.tms.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsungroup.tms.user.dto.UserSelectDTO;
import com.xsungroup.tms.user.entity.UserEntity;
import com.xsungroup.tms.user.vo.CurrentUser;
import com.xsungroup.tms.user.vo.UserAuditDetailVO;
import com.xsungroup.tms.user.vo.UserAuditVO;
import com.xsungroup.tms.user.vo.UserViewVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao extends BaseMapper<UserEntity> {

    @Select("select * from c_user user INNER JOIN c_user_role on user.user_id = c_user_role.user_id INNER JOIN c_role on c_user_role.role_id = c_role.role_id where user.user_id = #{userId}")
    List<UserEntity> getRoleInfo(String userId);

    @Select("select c_role_permission.role_permission_id,c_role_permission.role_id,c_role_permission.permission_id" +
            " from c_user user " +
            " INNER JOIN c_user_role on user.user_id = c_user_role.user_id " +
            " INNER JOIN c_role on c_role.role_id = c_user_role.role_id " +
            " INNER JOIN c_role_permission on c_role.role_id = c_role_permission.role_id " +
            " where user.user_id = #{userId}")
    List<Map<String, Object>> permInfo(String userId);

    /**
     * 查询用户信息
     * @param userSelectDTO
     * @param page
     * @return
     */
    List<UserViewVO> findByPageUser(@Param("user") UserSelectDTO userSelectDTO, Page page);

    List<UserAuditVO> findByUserAuditPage(@Param("phoneNo") String phoneNo,@Param("realName") String realName,
                                          @Param("auditStatus") String auditStatus,@Param("orgRole") String orgRole,Page page);

    UserAuditDetailVO getByUserAuditDetail(String userId);

    CurrentUser findLoginUserById(@Param("userId") String userId);
}
