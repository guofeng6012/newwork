package com.xsungroup.tms.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.core.common.AssertBuss;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.user.common.DriverBusCode;
import com.xsungroup.tms.user.dto.AuditRecordDto;
import com.xsungroup.tms.user.dto.DriverAcceptOrderDto;
import com.xsungroup.tms.user.dto.DriverDto;
import com.xsungroup.tms.user.entity.AuditRecordEntity;
import com.xsungroup.tms.user.entity.DriverEntity;
import com.xsungroup.tms.user.entity.UserEntity;
import com.xsungroup.tms.user.mapper.AuditRecordDao;
import com.xsungroup.tms.user.mapper.DriverDao;
import com.xsungroup.tms.user.mapper.UserDao;
import com.xsungroup.tms.user.service.DriverService;
import com.xsungroup.tms.user.vo.DriverVO;
import com.xsungroup.tms.utils.sms.SendSmsUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 司机表 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2019-08-01
 */
@Service
public class DriverServiceImpl extends ServiceImpl<DriverDao, DriverEntity> implements DriverService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private AuditRecordDao auditRecordDao;

    @Override
    public boolean addDriver(String userId, DriverDto driverDto) {
        DriverEntity driverEntity = new DriverEntity();
        BeanUtils.copyProperties(driverDto, driverEntity);
        //新增，配置那几个字段，直接insert即可
        //找到当前登录用户信息
//        UserEntity userEntity = userDao.selectById(userId);
//        if (userEntity != null) {
//            driverEntity.setCreateUser(userEntity.getUserId());
//        }
        driverEntity.setSource(1);//来源为新增
        driverEntity.setGmtCreate(LocalDateTime.now());
        driverEntity.setFirstSubmitTime(LocalDateTime.now());
        baseMapper.insert(driverEntity);
        return true;
    }


    @Override
    public boolean editDriver(String userId, DriverDto driverDto) {
        DriverEntity driverEntity = new DriverEntity();
        BeanUtils.copyProperties(driverDto, driverEntity);
        //修改，直接update即可
        //更新时间采用的是数据库时间  不用管
        UserEntity userEntity = userDao.selectById(userId);
        if (userEntity != null) {
            driverEntity.setUpdateUser(userEntity.getUserId());
        }
        driverEntity.setLastSubmitTime(LocalDateTime.now());
        baseMapper.updateById(driverEntity);
        return true;
    }


    @Override
    public Object list(IPage<DriverEntity> page, DriverDto driverDto) {
        QueryWrapper<DriverEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("is_able", 1);//精确查询
        if (driverDto.getIdCardName() != null && driverDto.getIdCardName().toString().length() > 0) {
            wrapper.like("id_card_name", driverDto.getIdCardName());
        }
        if (driverDto.getMobile() != null && driverDto.getMobile().toString().length() > 0) {
            wrapper.like("mobile", driverDto.getMobile());
        }
        if (driverDto.getAuditStatus() != null) {
            wrapper.eq("audit_status", driverDto.getAuditStatus());
        }
        wrapper.orderByDesc("update_time");
        IPage<DriverEntity> list = baseMapper.selectPage(page, wrapper);
        IPage<DriverVO> driverVOIPage = new Page<>();
        BeanUtils.copyProperties(list, driverVOIPage);
        return driverVOIPage;
    }


    @Override
    public boolean deleBatchIds(List<String> list) {
        if (list == null || list.size() == 0) {
            throw new BussException(DriverBusCode.SELECT_DATA);
        }
        List<DriverEntity> driverEntityList = baseMapper.selectBatchIds(list);
        if (driverEntityList == null || driverEntityList.size() == 0) {
            throw new BussException(DriverBusCode.SELECT_NO_DATA);
        }
        //逻辑删除，就是把is_able字段的值变为0
        List<String> ids = new ArrayList<>();
        for (DriverEntity driverEntity : driverEntityList) {
            ids.add(driverEntity.getDriverId());
        }
        removeByIds(ids);
//        driverDao.editBatchData(driverEntityList);
        return true;
    }


    //通过手机号判断，在司机表中，手机号是唯一的（产品说的）
    @Override
    public Map<String,Object> checkIsExist(DriverDto driverDto) {
        int flag = 0;
        Map<String,Object>  map = new HashMap<>();
        QueryWrapper<DriverEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",driverDto.getMobile());
        wrapper.eq("is_able",1);
        DriverEntity driverEntity = baseMapper.selectOne(wrapper);
        if(driverEntity != null && driverEntity.getOrgId().equals(driverDto.getOrgId())){
            //如果这次添加的是同组织的，则不能添加成功
            map.put("flag",2);
            return map;
        }
//        List<DriverEntity> driverEntity = baseMapper.selectList(wrapper);
        if(driverEntity != null ){
            flag = 1;
        }else{
            DriverEntity driver = new DriverEntity();
            BeanUtils.copyProperties(driverDto, driver);
            driver.setGmtCreate(LocalDateTime.now());
            baseMapper.insert(driver);
            driverEntity = driver;
        }
        map.put("driver",driverEntity);
        map.put("flag",flag);

        //预留  不管司机存不存在  都要给司机发条短信
        System.out.println("----------------这里调发短信接口-----------------");
//        SendSmsUtil.sendMsg(driverDto.getMobile());
        return map;
    }

    //解绑动作
    @Override
    public boolean unBound(DriverAcceptOrderDto acceptOrderDto) {
        DriverEntity driverEntities = baseMapper.selectById(acceptOrderDto.getDriverId());
        driverEntities.setIsBound(0);
        baseMapper.updateById(driverEntities);
        return true;
    }


    //重新绑定动作
    @Override
    public boolean bound(DriverAcceptOrderDto acceptOrderDto) {
        DriverEntity driverEntities = baseMapper.selectById(acceptOrderDto.getDriverId());
        driverEntities.setIsBound(1);
        baseMapper.updateById(driverEntities);
        return true;
    }


    @Override
    public boolean acceptOrder(String driverId) {
        if (driverId == null) {
            throw new BussException(DriverBusCode.SELECT_DATA);
        }
        DriverEntity driverEntity = baseMapper.selectById(driverId);
        if(driverEntity == null){
            throw new BussException(DriverBusCode.SELECT_DATA);
        }
        int flag = driverEntity.getIsSelfAcceptOrder()==0?1:0;
        driverEntity.setIsSelfAcceptOrder(flag);
        driverDao.updateById(driverEntity);
        return true;
    }


    @Override
    @Transactional
    public boolean auditRefuse(AuditRecordDto auditRecordDto) {
        AuditRecordEntity auditRecordEntity = new AuditRecordEntity();
        BeanUtils.copyProperties(auditRecordDto,auditRecordEntity);
        auditRecordEntity.setAuditMemo("被拒绝");
        auditRecordDao.insert(auditRecordEntity);
        DriverEntity driver = baseMapper.selectById(auditRecordDto.getAuditObjId());
        driver.setAuditStatus(1);//审核拒绝
        baseMapper.updateById(driver);
        return true;
    }


    @Override
    @Transactional
    public boolean auditSuccess(DriverDto driverDto) {
        DriverEntity driver = baseMapper.selectById(driverDto.getDriverId());
        BeanUtils.copyProperties(driverDto,driver);
        driver.setAuditStatus(2);//审核通过
        AuditRecordEntity auditRecordEntity = new AuditRecordEntity();
        auditRecordEntity.setAuditMemo("审核通过");
//        auditRecordEntity.setRefuseReason();
        auditRecordDao.insert(auditRecordEntity);
        return true;
    }


    @Override
    @Transactional
    public boolean auditCancel(AuditRecordDto auditRecordDto) {
        DriverEntity driver = baseMapper.selectById(auditRecordDto.getAuditObjId());
        driver.setAuditStatus(0);//取消审核
        baseMapper.insert(driver);
        //取消审核时也需要在审核记录表查数据
        AuditRecordEntity auditRecordEntity = new AuditRecordEntity();
        auditRecordEntity.setAuditMemo("取消审核");
        auditRecordEntity.setRefuseReason(auditRecordDto.getRefuseReason());
        auditRecordDao.insert(auditRecordEntity);
        return true;
    }

}
