package com.xsungroup.tms.matedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.entity.VehicleCateEntity;
import com.xsungroup.tms.matedata.mapper.VehicleCateDao;
import com.xsungroup.tms.matedata.service.VehicleCateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 车辆分类表 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2019-07-19
 */
@Service
public class VehicleCateServiceImpl extends ServiceImpl<VehicleCateDao, VehicleCateEntity> implements VehicleCateService {

    @Override
    public Object list(IPage<VehicleCateEntity> page, VehicleCateEntity vehicleCateEntity) {
        QueryWrapper<VehicleCateEntity> wrapper = new QueryWrapper<>();
//        wrapper.setEntity(EventNoticeTypeEntity);
        wrapper.eq("is_able", 1);//精确查询
        if (vehicleCateEntity.getVehicleCateName() != null && vehicleCateEntity.getVehicleCateName().length() > 0) {
            wrapper.like("vehicle_cate_name", vehicleCateEntity.getVehicleCateName());//模糊查询
        }
        if (vehicleCateEntity.getVehicleCateCode() != null && vehicleCateEntity.getVehicleCateCode().length() > 0) {
            wrapper.like("vehicle_cate_code", vehicleCateEntity.getVehicleCateCode());//模糊查询
        }
        wrapper.orderByDesc("vehicle_cate_code");//排序
        IPage<VehicleCateEntity> list = baseMapper.selectPage(page, wrapper);
        return list;
    }

    @Override
    public String addOrEdit(VehicleCateEntity vehicleCateEntity) throws BussException {
        check(vehicleCateEntity);//先校验是否存在
        if (StringUtils.isBlank(vehicleCateEntity.getVehicleCateId())) {
            //这时是新增
            baseMapper.insert(vehicleCateEntity);
        } else {
            //修改
            baseMapper.updateById(vehicleCateEntity);
        }
        return vehicleCateEntity.getVehicleCateId();
    }


    public void check(VehicleCateEntity vehicleCateEntity) throws BussException {
        int num = 0;
        if (StringUtils.isBlank(vehicleCateEntity.getVehicleCateId())) {
            //新增判断
            //判断名称
            QueryWrapper<VehicleCateEntity> name = new QueryWrapper<>();
            name.eq("vehicle_cate_name", vehicleCateEntity.getVehicleCateName());//
            num = baseMapper.selectCount(name);
            if (num > 0) {
                throw new BussException("该名称已经存在");
            }
            //判断code
            QueryWrapper<VehicleCateEntity> code = new QueryWrapper<>();
            code.eq("vehicle_cate_code", vehicleCateEntity.getVehicleCateCode());//
            num = baseMapper.selectCount(code);
            if (num > 0) {
                throw new BussException("该代码已经存在");
            }
        } else {
            //修改判断（校验的时候要排除自身）
            //判断名称
            QueryWrapper<VehicleCateEntity> name = new QueryWrapper<>();
            name.eq("vehicle_cate_name", vehicleCateEntity.getVehicleCateName());
            name.ne("vehicle_cate_id", vehicleCateEntity.getVehicleCateId());
            num = baseMapper.selectCount(name);
            if (num > 0) {
                throw new BussException("该名称已经存在");
            }
            //判断code
            QueryWrapper<VehicleCateEntity> code = new QueryWrapper<>();
            code.eq("vehicle_cate_code", vehicleCateEntity.getVehicleCateCode());
            code.ne("vehicle_cate_id", vehicleCateEntity.getVehicleCateId());
            num = baseMapper.selectCount(code);
            if (num > 0) {
                throw new BussException("该代码已经存在");
            }
        }
    }

}
