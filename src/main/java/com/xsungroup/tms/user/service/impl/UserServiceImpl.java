package com.xsungroup.tms.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.core.common.CachePre;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.user.common.BaseUserStatic;
import com.xsungroup.tms.user.common.BusCode;
import com.xsungroup.tms.user.common.OrgEnum;
import com.xsungroup.tms.user.dto.AuditDTO;
import com.xsungroup.tms.user.dto.OrgExtendRegisterDTO;
import com.xsungroup.tms.user.dto.UserDto;
import com.xsungroup.tms.user.dto.UserSelectDTO;
import com.xsungroup.tms.user.entity.*;
import com.xsungroup.tms.user.mapper.*;
import com.xsungroup.tms.user.service.PermService;
import com.xsungroup.tms.user.service.RoleService;
import com.xsungroup.tms.user.service.UserOrgService;
import com.xsungroup.tms.user.service.UserService;
import com.xsungroup.tms.user.vo.UserAuditDetailVO;
import com.xsungroup.tms.user.vo.UserAuditVO;
import com.xsungroup.tms.user.vo.UserOrgViewVO;
import com.xsungroup.tms.user.vo.UserViewVO;
import com.xsungroup.tms.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aabss.utils.tree.MenuTree;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户管理(此表不是备份，用于平台用户管理) 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2019-07-17
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermService permService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserOrgDao userOrgDao;

    @Autowired
    private OrgDao orgDao;

    @Autowired
    private OrgExtendDao orgExtendDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserOrgService userOrgService;

    @Autowired
    private AuditRecordDao auditRecordDao;


    @Override
    public Map<String, Object> getPermInfo(UserEntity userEntity) {
        Map<String, Object> resMap = new HashMap();
        List<MenuTree> roleOfMenu = permService.findRoleOfMenu(userEntity.getUserId());
        resMap.put("roleOfMenu",roleOfMenu);
        UserEntity roleInfo = userDao.getRoleInfo(userEntity.getUserId()).get(0);
        resMap.put("roleInfo",roleInfo);
        List<Object> permList = permInfo(userEntity.getUserId());
        resMap.put("permList",permList);
        return resMap;
    }

    @Override
    public UserOrgViewVO listPermUserOrg(String userId) {
        log.info("查询用户组织企业范围：{}",userId);
        QueryWrapper<UserOrgEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("is_able",1);
        List<UserOrgEntity> orgEntityList = userOrgDao.selectList(queryWrapper);
        List<String> create = new ArrayList<>();
        List<String> view = new ArrayList<>();
        orgEntityList.forEach(m -> {
            if(1 == m.getCheckType()){
                create.add(m.getOrgId());
            }else{
                view.add(m.getOrgId());
            }
        });
        UserOrgViewVO userOrgViewVO = new UserOrgViewVO();
        userOrgViewVO.setCreateList(create);
        userOrgViewVO.setViewList(view);
        return userOrgViewVO;
    }

    /**
     * 新增个人 注册 用户
     * @param orgExtendRegisterDTO
     * @return
     */
    @Transactional
    @Override
    public int addUserPersonal(OrgExtendRegisterDTO orgExtendRegisterDTO) {
        log.info("新增用户个人信息：{}",orgExtendRegisterDTO);
        if(checkUserIdCard(orgExtendRegisterDTO.getLegalPersonIdNo())){
            throw new BussException("身份证号已存在");
        }
        OrgEntity orgEntity = new OrgEntity();
        orgEntity.setGroupId("0");
        orgEntity.setParentId("0");
        orgEntity.setOrgName(orgExtendRegisterDTO.getUserName());
        orgEntity.setOrgRole(OrgEnum.OrgRoleEnum.ORG_ROLE_TYPE_0.getType());
        orgEntity.setOrgType(OrgEnum.OrgTypeEnum.ORG_TYPE_0.getType());
        orgEntity.setOrgSource(OrgEnum.OrgSourceEnum.ORG_SOURCE_0.getType());
        orgEntity.setBizType(OrgEnum.OrgBizTypeEnum.ORG_BIZ_TYPE_1.getType());
        orgEntity.setIsLegal(OrgEnum.OrgChooseStatusEnum.Org_Choose_Status_on.getKey());
        //新增个人 企业信息
        orgDao.insert(orgEntity);
        orgEntity.setGroupId(orgEntity.getOrgId());
        orgDao.updateById(orgEntity);
        OrgExtendEntity orgExtendEntity = orgExtendRegisterDTO.convert(OrgExtendEntity.class);
        orgExtendEntity.setOrgName(orgExtendRegisterDTO.getUserName());
        orgExtendEntity.setOrgId(orgEntity.getOrgId());
        orgExtendEntity.setCreateUser("");
        //新增个人 企业详情信息
        orgExtendDao.insert(orgExtendEntity);
        UserEntity userEntity = orgExtendRegisterDTO.convert(UserEntity.class);
        userEntity.setOrgId(orgEntity.getOrgId());
        userEntity.setGroupId(orgEntity.getOrgId());
        //userEntity.setTopId("0");
        QueryWrapper<RoleEntity> roleEntityQueryWrapper = new QueryWrapper<>();
        roleEntityQueryWrapper.eq("role_code", BaseUserStatic.ROLECODEUSER);
        roleEntityQueryWrapper.eq("is_able","1");
        RoleEntity roleEntity = roleDao.selectOne(roleEntityQueryWrapper);
        userEntity.setRoleId(roleEntity.getRoleId());
        userEntity.setCompanyId("0");
        userEntity.setIsSuper(1);
        //新增个人 用户
        baseMapper.insert(userEntity);
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRoleId(roleEntity.getRoleId());
        userRoleEntity.setUserId(userEntity.getUserId());
        //新增个人 角色关系
        userRoleDao.insert(userRoleEntity);
        //新增用户企业操作关系
        userOrgService.inserUserOrg(userEntity.getUserId(),orgEntity.getOrgId());
        return 1;
    }

    @Override
    public int editPassword(String userId, String password) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("is_able","1");
        UserEntity userEntity = userDao.selectOne(queryWrapper);
        if(null == userEntity){
            throw new IllegalArgumentException("未查询到修改对象");
        }
        userEntity.setPassword(password);
        userDao.updateById(userEntity);
        return userDao.updateById(userEntity);
    }

    @Override
    public ResponseInfo findByPageUser(UserSelectDTO userSelectDTO,String id, Page page) {
        log.info("分页查询用户管理请求参数{}",userSelectDTO);
        if(userSelectDTO.getOrgId().isEmpty()){
             UserEntity userEntity = userDao.selectById(id);
             userSelectDTO.setOrgId(userEntity.getOrgId());
        }
        page.setDesc("a.gmt_create");
        List<UserViewVO> list = userDao.findByPageUser(userSelectDTO,page);
        page.setRecords(list);
        return ResponseUtil.success(page);
    }

    @Override
    public int delUser(String uid) {
        log.info("删除用户id：{}",uid);
        UserEntity userEntity = userDao.selectById(uid);
        if(null == userEntity){
            throw new BussException(BusCode.user_check_err_22);
        }
        if(userEntity.getIsSuper() == 1 ){
            throw new BussException(BusCode.user_check_err_21);
        }
//        QueryWrapper<UserRoleEntity> userRoleEntityQueryWrapper = new QueryWrapper<>();
//        userRoleEntityQueryWrapper.eq("user_id",uid);
//        userRoleEntityQueryWrapper.eq("is_able",1);
//        UserRoleEntity userRoleEntity = userRoleDao.selectOne(userRoleEntityQueryWrapper);
//        userEntity.setIsAble(0);
//        userRoleEntity.setIsAble(0);
        userRoleDao.delete(new QueryWrapper<UserRoleEntity>().eq("user_id",uid));
        return userDao.deleteById(uid);
    }

    @Override
    @Transactional
    public boolean addUser(String userId, UserDto userDto) {
        //用户名不能重复
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userDto.getUserName());
        if(baseMapper.selectCount(wrapper) > 0){
            throw new BussException(BusCode.user_check_err_23);
        }
        //找到用户信息
        UserEntity userEntity =  userDao.selectById(userId);
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(userDto, entity);
        //完善下面几个字段
        entity.setIsSuper(0);
        entity.setCreateUser(userEntity.getUserId());
        entity.setGroupId(userEntity.getGroupId());
        entity.setUserStatus(1);
        entity.setIsAble(1);
        baseMapper.insert(entity);

        //用户和角色的关系
        UserRoleEntity  userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRoleId(entity.getRoleId());
        userRoleEntity.setUserId(entity.getUserId());
        userRoleEntity.setIsAble(1);
        userRoleDao.insert(userRoleEntity);

        //用户和企业的关系,批量保存
        List<UserOrgEntity>  userOrgEntityList = new ArrayList<>();
        //创建组织的ids
        List<String> createIds = userDto.getCreateList();
        if(createIds != null && createIds.size() > 0){
            for(String createId : createIds){
                UserOrgEntity  userOrgEntity = new UserOrgEntity();
                userOrgEntity.setUserId(entity.getUserId());
                userOrgEntity.setOrgId(createId);
                userOrgEntity.setCheckType(1);
                userOrgEntity.setCreateUser(userEntity.getUserId());
                userOrgEntityList.add(userOrgEntity);
            }
        }
        //查看组织的ids
        List<String> seeIds = userDto.getViewList();
        if(createIds != null && createIds.size() > 0){
            for(String seeId : seeIds){
                UserOrgEntity  userOrgEntity = new UserOrgEntity();
                userOrgEntity.setUserId(entity.getUserId());
                userOrgEntity.setOrgId(seeId);
                userOrgEntity.setCheckType(0);
                userOrgEntity.setCreateUser(userEntity.getUserId());
                userOrgEntityList.add(userOrgEntity);
            }
        }
        if(userOrgEntityList != null && userOrgEntityList.size() > 0){
            userOrgDao.addBatchData(userOrgEntityList);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean editUser(String userId, UserDto userDto) {
        //用户名不能重复
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.ne("user_id",userDto.getUserId());
        wrapper.eq("user_name",userDto.getUserName());
        if(baseMapper.selectCount(wrapper) > 0){
            throw new BussException(BusCode.user_check_err_23);
        }
        UserEntity userEntity =  userDao.selectById(userDto.getUserId());
        BeanUtils.copyProperties(userDto, userEntity);
        /*//找到当前登录人的用户信息
        UserEntity userEntity =  userDao.selectById(userId);
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(userDto, entity);
        //完善下面几个字段
        entity.setAuditStatus(0);
        entity.setUpdateUser(userEntity.getUserId());
        entity.setUserStatus(1);
        entity.setIsAble(1);*/
        baseMapper.updateById(userEntity);

        //修改的时候 把这个用户之前关联的角色、企业的中间表都删除，建新的
        // 1、用户角色表
        QueryWrapper<UserRoleEntity>  userRoleWrapper = new QueryWrapper<>();
        userRoleWrapper.eq("user_id",userDto.getUserId());
        List<UserRoleEntity>  userRoleEntities = userRoleDao.selectList(userRoleWrapper);
        if(userRoleEntities != null && userRoleEntities.size() >0){
            List<String> userRoleIds = new ArrayList<>();
            for(UserRoleEntity userRoleEntity : userRoleEntities){
                userRoleIds.add(userRoleEntity.getUserRoleId());
            }
            userRoleDao.deleteBatchIds(userRoleIds);
        }
        // 2、用户企业表
        QueryWrapper<UserOrgEntity>  userOrgWrapper = new QueryWrapper<>();
        userOrgWrapper.eq("user_id",userDto.getUserId());
        List<UserOrgEntity>  userOrgEntities = userOrgDao.selectList(userOrgWrapper);
        if(userOrgEntities != null && userOrgEntities.size() > 0){
            List<String>  userOrgIds = new ArrayList<>();
            for(UserOrgEntity userOrgEntity : userOrgEntities){
                userOrgIds.add(userOrgEntity.getUserOrgId());
            }
            userOrgDao.deleteBatchIds(userOrgIds);
        }

        //用户和角色的关系
        UserRoleEntity  userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(userEntity.getUserId());
        userRoleEntity.setRoleId(userEntity.getRoleId());
        userRoleEntity.setIsAble(1);
        userRoleDao.insert(userRoleEntity);

        //用户和企业的关系,批量保存
        List<UserOrgEntity>  userOrgEntityList = new ArrayList<>();
        //创建组织的ids
        List<String> createIds = userDto.getCreateList();
        if(createIds != null && createIds.size() > 0){
            for(String createId : createIds){
                UserOrgEntity  userOrgEntity = new UserOrgEntity();
                userOrgEntity.setUserId(userEntity.getUserId());
                userOrgEntity.setOrgId(createId);
                userOrgEntity.setCheckType(1);
                userOrgEntity.setCreateUser(userEntity.getUserId());
                userOrgEntityList.add(userOrgEntity);
            }
        }
        //查看组织的ids
        List<String> seeIds = userDto.getViewList();
        if(createIds != null && createIds.size() > 0){
            for(String seeId : seeIds){
                UserOrgEntity  userOrgEntity = new UserOrgEntity();
                userOrgEntity.setUserId(userEntity.getUserId());
                userOrgEntity.setOrgId(seeId);
                userOrgEntity.setCheckType(0);
                userOrgEntity.setCreateUser(userEntity.getUserId());
                userOrgEntityList.add(userOrgEntity);
            }
        }
        if(userOrgEntityList != null && userOrgEntityList.size() > 0){
            userOrgDao.addBatchData(userOrgEntityList);
        }
        return true;
    }

    @Override
    public Page findByUserAuditPage(Page page, String phoneNo, String realName, String auditStatus, String orgRole) {
        log.info("分页查询个人审核信息参数：page：{}, phoneNo:{}, realName :{}, auditStatus:{},orgRole:{}",
                page,phoneNo,realName,auditStatus,orgRole);
        List<UserAuditVO> list = userDao.findByUserAuditPage(phoneNo,realName,auditStatus,orgRole,page);
        page.setRecords(list);
        return page;
    }

    @Override
    public UserAuditDetailVO getUserAuditDetail(String userId) {
        log.info("查询个人审核参数:userId:{} ",userId);
        return userDao.getByUserAuditDetail(userId);
    }

    @Transactional
    @Override
    public int auditUser(AuditDTO auditDTO, String userIdCreate) {
        log.info("个人审核信息参数 :{}", auditDTO);
        AuditRecordEntity auditRecordEntity = new AuditRecordEntity();
        if(OrgEnum.OrgStatusEnum.ORG_STATUS_1.getType() == auditDTO.getStatus()){
            Assert.isNull(auditDTO.getRefuseReason(),"拒绝理由不能为空");
            auditRecordEntity.setRefuseReason(auditDTO.getRefuseReason());
        }
        OrgEntity orgEntity = orgDao.selectById(auditDTO.getOrgId());
        if(null == orgEntity){
            throw new BussException("审核对象不存在");
        }
        orgEntity.setAuditStatus(auditDTO.getStatus());
        auditRecordEntity.setAuditObjId(orgEntity.getOrgId());
        //auditRecordEntity.
        auditRecordEntity.setCreateUserName(userIdCreate);
        orgDao.updateById(orgEntity);
        auditRecordDao.insert(auditRecordEntity);
        //清除企业登录用户token
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("org_id",auditDTO.getOrgId());
        queryWrapper.eq("is_able","1");
        List<UserEntity> userEntityList = userDao.selectList(queryWrapper);
        userEntityList.stream().forEach(user->{
            RedisUtil.del(CachePre.LOING_SHIRO_JWT_ID + user.getUserId());
        });
        return 1;
    }

    @Override
    public List<UserEntity> listByPhone(String phone) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone_no",phone).eq("is_able","1");
        List<UserEntity> userEntityList = userDao.selectList(queryWrapper);
        return userEntityList;
    }

    /**
     * 校验个人注册 身份证校验
     * @param idCard
     * @return
     */
    @Override
    public boolean checkUserIdCard(String idCard) {
        log.info("查询个人身份证是否注册参数：idCard:{}",idCard);
        Integer count = orgDao.countIdCardUser(idCard);
        return count > 0  ? true : false;
    }


    public List<Object> permInfo(String id) {
        List<Map<String, Object>> maps = userDao.permInfo(id);
        List<Object> permList = new ArrayList<>();
        maps.stream().forEach(perm ->
                permList.add(perm.get("permission_id"))
        );
        return permList;
    }



}
