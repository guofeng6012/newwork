package com.xsungroup.tms.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.user.common.BaseUserStatic;
import com.xsungroup.tms.user.common.OrgEnum;
import com.xsungroup.tms.user.dto.ForgetPasswordDTO;
import com.xsungroup.tms.user.dto.OrgExtendRegisterDTO;
import com.xsungroup.tms.user.entity.*;
import com.xsungroup.tms.user.mapper.*;
import com.xsungroup.tms.user.service.RegisterService;
import com.xsungroup.tms.user.service.UserOrgService;
import com.xsungroup.tms.user.service.UserService;
import com.xsungroup.tms.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author GF
 * @Date 2019-7-29 09:54:50
 * @Description
 **/
@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {


    @Autowired
    private UserDao userDao;

    @Autowired
    private OrgDao orgDao;

    @Autowired
    private OrgExtendDao orgExtendDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserService userService;

    @Autowired
    private UserOrgService userOrgService;

    /**
     * 用户注册
     *
     * @return
     */
    @Transactional
    @Override
    public ResponseInfo register(OrgExtendRegisterDTO orgExtendDTO) {
        log.info("注册用户信息：{}", orgExtendDTO);
        Object smsCode = RedisUtil.get(orgExtendDTO.getPhoneNo());
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", orgExtendDTO.getUserName()).eq("is_able", "1");
        List<UserEntity> userEntityList = userDao.selectList(queryWrapper);
        if (!userEntityList.isEmpty()) {
            return ResponseUtil.error("当前用户名已经被占用，请修改名称");
        }
        if (null == smsCode || !orgExtendDTO.getSmsCode().equals(String.valueOf(smsCode))) {
            return ResponseUtil.error("短信验证码不正确");
        }



        //通过后情况缓存
        RedisUtil.del(orgExtendDTO.getPhoneNo());
        if (1 == orgExtendDTO.getUserType()) {
            userService.addUserPersonal(orgExtendDTO);
            return ResponseUtil.success("新增成功");
        }
        QueryWrapper<OrgEntity> orgEntityQueryWrapper = new QueryWrapper<>();
        orgEntityQueryWrapper.eq("org_name", orgExtendDTO.getOrgName());
        List<OrgEntity> orgEntityList = orgDao.selectList(orgEntityQueryWrapper);
        if (!orgEntityList.isEmpty()) {
            return ResponseUtil.error("当前企业已经被占用，请修改企业名称");
        }

        OrgExtendEntity orgExtendEntity = new OrgExtendEntity();
        OrgEntity orgEntity = new OrgEntity();
        BeanUtils.copyProperties(orgExtendDTO, orgExtendEntity);
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(orgExtendDTO, userEntity);
        BeanUtils.copyProperties(orgExtendDTO, orgEntity);
        //设置组织类型
        orgEntity.setOrgType(OrgEnum.OrgTypeEnum.ORG_TYPE_0.getType());
        String roleCode = BaseUserStatic.ROLECODEORGUSER;
        //设置角色类型
        int orgRoleType = OrgEnum.OrgRoleEnum.ORG_ROLE_TYPE_2.getType();
        if (orgExtendEntity.getRoadTransOrgName().isEmpty()) {
            orgRoleType = OrgEnum.OrgRoleEnum.ORG_ROLE_TYPE_1.getType();
            roleCode = BaseUserStatic.ROLECODEORG;
        }
        //查询系统角色 TODO 系统角色 考虑放入缓存
        QueryWrapper<RoleEntity> roleEntityQueryWrapper = new QueryWrapper<>();
        roleEntityQueryWrapper.eq("org_id", "0");
        roleEntityQueryWrapper.eq("is_able", "1");
        //TODO 传入 系统注册时角色 roleCode
        roleEntityQueryWrapper.eq("role_code", roleCode);
        RoleEntity roleEntities = roleDao.selectOne(roleEntityQueryWrapper);

        //设置企业角色
        orgEntity.setOrgRole(orgRoleType);
        //设置上级id
        orgEntity.setParentId("0");
        orgEntity.setBizType(OrgEnum.OrgBizTypeEnum.ORG_BIZ_TYPE_0.getType());
        //orgEntity.setGroupId("0");
        //设置法人组织状态
        orgEntity.setIsLegal(OrgEnum.OrgChooseStatusEnum.Org_Choose_Status_on.getKey());
        //新增企业信息
        orgDao.insert(orgEntity);
        orgEntity.setGroupId(orgEntity.getOrgId());
        orgDao.updateById(orgEntity);
        String orgId = orgEntity.getOrgId();
        //设置集团id
        userEntity.setGroupId(orgId);
        //设置上级id
        userEntity.setOrgId(orgId);
        //设置法人组织id
        userEntity.setCompanyId(orgId);
        //是否超级管理员
        userEntity.setIsSuper(1);

        userEntity.setRoleId(roleEntities.getRoleId());
        //新增用户信息
        userDao.insert(userEntity);

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRoleId(roleEntities.getRoleId());
        userRoleEntity.setUserId(userEntity.getUserId());
        userRoleDao.insert(userRoleEntity);

        //新增企业详情
        orgExtendEntity.setCreateUser(userEntity.getUserId());
        orgExtendEntity.setOrgId(orgId);
        orgExtendDao.insert(orgExtendEntity);

        //新增用户企业操作关系
        userOrgService.inserUserOrg(userEntity.getUserId(), orgEntity.getOrgId());
        return ResponseUtil.success("新增成功");
    }

    /**
     * 忘记密码校验
     * @param smsCode
     * @param mobile
     * @return
     */
    @Override
    public ResponseInfo forgetCheck(String smsCode, String mobile) {
        List<UserEntity> userEntityList = userService.listByPhone(mobile);
        if (null == userEntityList || userEntityList.isEmpty()) {
            throw new BussException("手机号不存在");
        }
        Object object = RedisUtil.get(mobile);
        if (object == null || !smsCode.equals(String.valueOf(object))) {
            throw new BussException("验证码不正确");
        }
        return ResponseUtil.success("验证通过");
    }

    @Override
    public ResponseInfo forgetPassword(ForgetPasswordDTO forgetPasswordDTO) {
        log.info("忘记密码参数 forgetPasswordDTO:{}",forgetPasswordDTO);
        UserEntity userEntity = userDao.selectById(forgetPasswordDTO.getUserId());
        if(null == userEntity){
            throw new BussException("用户不存在");
        }
        Object object = RedisUtil.get(userEntity.getPhoneNo());
        if (object == null || !forgetPasswordDTO.getSmsCode().equals(String.valueOf(object))) {
            throw new BussException("验证码不正确");
        }
        userEntity.setPassword(forgetPasswordDTO.getPassword());
        int status = userDao.updateById(userEntity);
        if(status > 0){
            RedisUtil.del(userEntity.getPhoneNo());
            return ResponseUtil.success("重置密码成功");
        }else{
            return ResponseUtil.error("重置密码失败");
        }
    }
}
