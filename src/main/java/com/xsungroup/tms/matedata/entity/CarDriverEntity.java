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
 * 车辆与司机关系表
 * </p>
 *
 * @author admin
 * @since 2019-08-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_car_driver")
public class CarDriverEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value = "car_driver_id", type = IdType.UUID)
	private String carDriverId;
    /**
     * 用户ID
     */
	private String carId;
    /**
     * 司机ID
     */
	private String driverId;
    /**
     * 司机名字
     */
	private String driverName;
    /**
     * 是否默认创建组织 0:否 1:是
     */
	private Integer isDefault;
    /**
     * 创建人
     */
	private String createUser;
    /**
     * 更新人
     */
	private String updateUser;


}
