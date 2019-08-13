package com.xsungroup.tms.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.user.common.OrgEnum;
import com.xsungroup.tms.user.entity.UserOrgEntity;
import com.xsungroup.tms.user.mapper.UserOrgDao;
import com.xsungroup.tms.user.service.UserOrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("userOrgService")
public class UserOrgServiceImpl extends ServiceImpl<UserOrgDao,UserOrgEntity> implements UserOrgService {


    @Override
    public List<String> userCreateOrgIds(String userId) {
        QueryWrapper<UserOrgEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("check_type",1);
        wrapper.eq("user_id",userId);
        List<UserOrgEntity> orgList = baseMapper.selectList(wrapper);
        List<String> list = new ArrayList<>();
        for(UserOrgEntity org : orgList){
            list.add(org.getOrgId());
        }
        return list;
    }

    @Override
    public int inserUserOrg(String userId, String orgId) {
        log.info("新增用户企业操作权限：userId:{} ,orgId:{}",userId,orgId);
        UserOrgEntity userOrgEntity = new UserOrgEntity();
        userOrgEntity.setOrgId(orgId);
        userOrgEntity.setUserId(userId);
        userOrgEntity.setCheckType(OrgEnum.OrgCheckTypeEnum.ORG_BIZ_TYPE_0.getType());
        baseMapper.insert(userOrgEntity);
        UserOrgEntity userOrgEntity2 = new UserOrgEntity();
        userOrgEntity2.setOrgId(orgId);
        userOrgEntity2.setUserId(userId);
        userOrgEntity2.setCheckType(OrgEnum.OrgCheckTypeEnum.ORG_BIZ_TYPE_1.getType());
        baseMapper.insert(userOrgEntity2);
        return 1;
    }
}
