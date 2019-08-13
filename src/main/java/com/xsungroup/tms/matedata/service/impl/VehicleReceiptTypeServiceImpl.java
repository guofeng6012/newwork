package com.xsungroup.tms.matedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.entity.VehicleReceiptTypeEntity;
import com.xsungroup.tms.matedata.mapper.VehicleReceiptTypeDao;
import com.xsungroup.tms.matedata.service.VehicleReceiptTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 车辆接单类型表 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2019-07-22
 */
@Service
public class VehicleReceiptTypeServiceImpl extends ServiceImpl<VehicleReceiptTypeDao, VehicleReceiptTypeEntity> implements VehicleReceiptTypeService {

    @Autowired
    private VehicleReceiptTypeDao vehicleReceiptTypeDao;


    @Override
    public Object list(IPage<VehicleReceiptTypeEntity> page, VehicleReceiptTypeEntity vehicleReceiptTypeEntity) {
        QueryWrapper<VehicleReceiptTypeEntity> wrapper = new QueryWrapper<>();
//        wrapper.setEntity(EventNoticeTypeEntity);
        wrapper.eq("is_able", 1);//精确查询
        if (vehicleReceiptTypeEntity.getVehicleReceiptTypeName() != null && vehicleReceiptTypeEntity.getVehicleReceiptTypeName().length() > 0) {
            wrapper.like("vehicle_receipt_type_name", vehicleReceiptTypeEntity.getVehicleReceiptTypeName());//模糊查询
        }
        if (vehicleReceiptTypeEntity.getVehicleReceiptTypeCode() != null && vehicleReceiptTypeEntity.getVehicleReceiptTypeCode().length() > 0) {
            wrapper.like("vehicle_receipt_type_code", vehicleReceiptTypeEntity.getVehicleReceiptTypeCode());//模糊查询
        }
        wrapper.orderByDesc("vehicle_receipt_type_code");//排序
        IPage<VehicleReceiptTypeEntity> list = baseMapper.selectPage(page, wrapper);
        return list;
    }


    @Override
    @Transactional
    public String addOrEdit(VehicleReceiptTypeEntity vehicleReceiptTypeEntity) throws BussException {
        check(vehicleReceiptTypeEntity);//先校验是否存在
        //这里需要判断是否是默认下拉来源，如果这条数据是默认下拉来源，则把数据库中原来的默认下拉的数据取消掉,如果不是默认下拉来源就不处理，整个数据库只能有一条数据是默认下来的来源，（修改也是同样的逻辑）
        if("1".equals(vehicleReceiptTypeEntity.getIsDefault())){
            //勾选了默认下拉来源
            isDefaultHandle(vehicleReceiptTypeEntity.getVehicleReceiptTypeId());
        }
        if (StringUtils.isBlank(vehicleReceiptTypeEntity.getVehicleReceiptTypeId())) {
            //这时是新增
            baseMapper.insert(vehicleReceiptTypeEntity);
        } else {
            //修改
            baseMapper.updateById(vehicleReceiptTypeEntity);
        }
        return vehicleReceiptTypeEntity.getVehicleReceiptTypeId();
    }

    public void isDefaultHandle(String id){
        //先判断是新增还是修改
        if(StringUtils.isBlank(id)){
            //新增,将数据库所有有用的数据的默认下拉来源去掉
            vehicleReceiptTypeDao.isDefaultHandle();
        }else{
            vehicleReceiptTypeDao.isDefaultHandleById(id);
        }
    }


    public void check(VehicleReceiptTypeEntity vehicleReceiptTypeEntity) throws BussException {
        int num = 0;
        if (StringUtils.isBlank(vehicleReceiptTypeEntity.getVehicleReceiptTypeId())) {
            //新增判断
            //判断名称
            QueryWrapper<VehicleReceiptTypeEntity> name = new QueryWrapper<>();
            name.eq("vehicle_receipt_type_name", vehicleReceiptTypeEntity.getVehicleReceiptTypeName());//
            num = baseMapper.selectCount(name);
            if (num > 0) {
                throw new BussException("该名称已经存在");
            }
            //判断code
            QueryWrapper<VehicleReceiptTypeEntity> code = new QueryWrapper<>();
            code.eq("vehicle_receipt_type_code", vehicleReceiptTypeEntity.getVehicleReceiptTypeCode());//
            num = baseMapper.selectCount(code);
            if (num > 0) {
                throw new BussException("该代码已经存在");
            }
        } else {
            //修改判断（校验的时候要排除自身）
            //判断名称
            QueryWrapper<VehicleReceiptTypeEntity> name = new QueryWrapper<>();
            name.eq("vehicle_receipt_type_name", vehicleReceiptTypeEntity.getVehicleReceiptTypeName());
            name.ne("vehicle_receipt_type_id", vehicleReceiptTypeEntity.getVehicleReceiptTypeId());
            num = baseMapper.selectCount(name);
            if (num > 0) {
                throw new BussException("该名称已经存在");
            }
            //判断code
            QueryWrapper<VehicleReceiptTypeEntity> code = new QueryWrapper<>();
            code.eq("vehicle_receipt_type_code", vehicleReceiptTypeEntity.getVehicleReceiptTypeCode());
            code.ne("vehicle_receipt_type_id", vehicleReceiptTypeEntity.getVehicleReceiptTypeId());
            num = baseMapper.selectCount(code);
            if (num > 0) {
                throw new BussException("该代码已经存在");
            }
        }
    }


}
