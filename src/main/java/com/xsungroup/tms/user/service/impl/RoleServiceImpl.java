package com.xsungroup.tms.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.user.common.BusCode;
import com.xsungroup.tms.user.dto.RoleSelectDTO;
import com.xsungroup.tms.user.entity.RoleEntity;
import com.xsungroup.tms.user.entity.UserEntity;
import com.xsungroup.tms.user.entity.UserRoleEntity;
import com.xsungroup.tms.user.mapper.RoleDao;
import com.xsungroup.tms.user.mapper.UserDao;
import com.xsungroup.tms.user.mapper.UserRoleDao;
import com.xsungroup.tms.user.service.RoleService;
import com.xsungroup.tms.user.vo.RoleViewVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2019-07-17
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public ResponseInfo list(Page page, RoleSelectDTO roleSelectDTO,String id) {
        //获取当前用户企业信息 TODO 后期替换
        if(roleSelectDTO.getOrgId() == null || roleSelectDTO.getOrgId().isEmpty()){
            UserEntity userEntity = userDao.selectById(id);
            roleSelectDTO.setOrgId(userEntity.getOrgId());
        }
        page.setDesc("c.gmt_create");
        List<RoleViewVO> roleViewVOList = baseMapper.findByPageRole(roleSelectDTO,page);
        page.setRecords(roleViewVOList);
        return ResponseUtil.success(page);
    }

    @Override
    public Object addOrEdit(RoleEntity roleEntity) throws BussException {

        if (StringUtils.isBlank(roleEntity.getRoleCode())) {
            throw new BussException("缺少角色代码");
        }
        if (StringUtils.isBlank(roleEntity.getRoleName())) {
            throw new BussException("缺少角色名称");
        }
        if (StringUtils.isBlank(roleEntity.getRoleId())) {
            check(roleEntity);//先校验是否存在
            //这时是新增
            roleEntity.setGmtCreate(LocalDateTime.now());
            roleEntity.setGmtModified(LocalDateTime.now());
            baseMapper.insert(roleEntity);
        } else {
            QueryWrapper<RoleEntity> name = new QueryWrapper<>();
            RoleEntity roleEntityUpdate = baseMapper.selectById(roleEntity.getRoleId());
            if(!roleEntityUpdate.getRoleCode().equals(roleEntity.getRoleCode())){
                int codeNum = baseMapper.selectCount(name.clone()
                        .eq("role_code", roleEntity.getRoleCode()).eq("is_able","1").eq("org_id",roleEntity.getOrgId()));
                if(codeNum > 0 ){
                    throw new BussException(BusCode.ROLE_CODE_REPEAT);
                }
            }

            if(!roleEntityUpdate.getRoleName().equals(roleEntity.getRoleName())){
                int nameNum = baseMapper.selectCount(name.clone()
                        .eq("role_name", roleEntity.getRoleName()).eq("is_able","1").eq("org_id",roleEntity.getOrgId()));
                if(nameNum > 0 ){
                    throw new BussException(BusCode.ROLE_NAME_REPEAT);
                }
            }
            //修改
            roleEntity.setGmtCreate(null);
            roleEntity.setGmtModified(LocalDateTime.now());
            baseMapper.updateById(roleEntity);
        }
        return roleEntity.getRoleId();
    }


    public void check(RoleEntity eventNoticeTypeEntity) throws BussException {
        int num;
        //新增判断
        //判断名称
        QueryWrapper<RoleEntity> name = new QueryWrapper<>();
        if (!StringUtils.isEmpty(eventNoticeTypeEntity.getRoleId())) {
            name.eq("role_id", eventNoticeTypeEntity.getRoleId());//
        }

        num = baseMapper.selectCount(name.clone()
                .eq("role_code", eventNoticeTypeEntity.getRoleCode()).eq("is_able","1").eq("org_id",eventNoticeTypeEntity.getOrgId()));
        if (num > 0) {
            throw new BussException(BusCode.ROLE_CODE_REPEAT);
        }

        num = baseMapper.selectCount(name.clone()
                .eq("role_name", eventNoticeTypeEntity.getRoleName()).eq("is_able","1").eq("org_id",eventNoticeTypeEntity.getOrgId()));
        if (num > 0) {
            throw new BussException(BusCode.ROLE_NAME_REPEAT);
        }
    }



    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @Override
    public int deleteRole(String roleId) {
        log.info("角色删除id：{}",roleId);
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        queryWrapper.eq("is_able","1");
        List<UserRoleEntity> list = userRoleDao.selectList(queryWrapper);
        if(!list.isEmpty()){
            throw new BussException(BusCode.ROLE_DELETE_FAL);
        }
        //RoleEntity roleEntity = baseMapper.selectById(roleId);
        //roleEntity.setIsAble(0);
        return  baseMapper.deleteById(roleId);
    }




    @Override
    public List<RoleEntity> selectRoleByOrgId(String id) {
        UserEntity userEntity = userDao.selectById(id);
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("org_id",userEntity.getOrgId());
        wrapper.eq("is_able",1);
        List<RoleEntity>  roleEntityList = baseMapper.selectList(wrapper);
        if(roleEntityList == null || roleEntityList.size() == 0){
            RoleEntity  roleEntity = new RoleEntity();
            roleEntity.setRoleCode("001");
            roleEntity.setRoleName("无角色");
            roleEntity.setRoleId("001");
            roleEntityList.add(roleEntity);
        }
        return roleEntityList;
    }


}
