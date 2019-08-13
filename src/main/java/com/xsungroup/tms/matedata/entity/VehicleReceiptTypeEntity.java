package com.xsungroup.tms.matedata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xsungroup.tms.core.supper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 车辆接单类型表
 * </p>
 *
 * @author Alex
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_vehicle_receipt_type")
public class VehicleReceiptTypeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 车辆接单类型ID
     */
    @TableId(value = "vehicle_receipt_type_id", type = IdType.UUID)
    private String vehicleReceiptTypeId;

    /**
     * 车辆接单类型编码
     */
    private String vehicleReceiptTypeCode;

    /**
     * 车辆接单类型名称
     */
    private String vehicleReceiptTypeName;

    /**
     * 失效日期
     */
    private String expiryDate;

    /**
     * 是否下拉默认 0:不是 1:是
     */
    private String isDefault;

}
