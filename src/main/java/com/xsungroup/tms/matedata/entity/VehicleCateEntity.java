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
 * 车辆分类表
 * </p>
 *
 * @author Alex
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_vehicle_cate")
public class VehicleCateEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 车辆分类ID
     */
    @TableId(value = "vehicle_cate_id", type = IdType.UUID)
    private String vehicleCateId;

    /**
     * 车辆分类编码
     */
    private String vehicleCateCode;

    /**
     * 车辆分类名称
     */
    private String vehicleCateName;

    /**
     * 车辆类别    1:货车  2:挂车  3:牵引车  4:专项作业车 5:车辆运输车
     */
    private Integer vehicleType;

    /**
     * 失效日期
     */
    private String expiryDate;

}
