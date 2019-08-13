package com.xsungroup.tms.matedata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xsungroup.tms.matedata.entity.VehicleReceiptTypeEntity;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 车辆接单类型表 Mapper 接口
 * </p>
 *
 * @author Alex
 * @since 2019-07-22
 */
public interface VehicleReceiptTypeDao extends BaseMapper<VehicleReceiptTypeEntity> {


    @Update("UPDATE c_vehicle_receipt_type set is_default = '0'  where is_able = 1 ")
    void isDefaultHandle();


    @Update("UPDATE c_vehicle_receipt_type set is_default = '0'  where is_able = 1 and vehicle_receipt_type_id != #{vehicleReceiptTypeId}")
    void isDefaultHandleById(String vehicleReceiptTypeId);


}
