package com.xsungroup.tms.user.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.user.dto.AuditDTO;
import com.xsungroup.tms.user.dto.OrgExtendRegisterDTO;
import com.xsungroup.tms.user.dto.UserDto;
import com.xsungroup.tms.user.dto.UserSelectDTO;
import com.xsungroup.tms.user.entity.UserEntity;
import com.xsungroup.tms.user.vo.UserAuditDetailVO;
import com.xsungroup.tms.user.vo.UserOrgViewVO;


import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户管理(此表不是备份，用于平台用户管理) 服务类
 * </p>
 *
 * @author Alex
 * @since 2019-07-17
 */
public interface UserService extends IService<UserEntity> {

    Map<String, Object> getPermInfo(UserEntity userEntity);

    int editPassword(String userId,String password);

    /**
     * 查询用户列表
     * @param userSelectDTO
     * @return
     */
    ResponseInfo findByPageUser(UserSelectDTO userSelectDTO,String id, Page page);

    UserOrgViewVO listPermUserOrg(String roleId);

    int addUserPersonal(OrgExtendRegisterDTO orgExtendRegisterDTO);

    int delUser(String uid);

    boolean addUser(String userId,UserDto userDto);

    boolean editUser(String userId,UserDto userDto);

    Page findByUserAuditPage(Page page,String phoneNo,String realName,String auditStatus,String orgRole);

    UserAuditDetailVO getUserAuditDetail(String userId);

    int auditUser(AuditDTO auditDTO, String userIdCreate);

    List<UserEntity> listByPhone(String phone);

    boolean checkUserIdCard(String idCard);


}
