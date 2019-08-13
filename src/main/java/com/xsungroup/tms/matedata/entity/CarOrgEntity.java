package com.xsungroup.tms.matedata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xsungroup.tms.core.supper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 车辆企业关系表
 * </p>
 *
 * @author Alex
 * @since 2019-08-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_car_org")
public class CarOrgEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value = "car_org_id", type = IdType.UUID)
	private String carOrgId;
    /**
     * 车辆ID
     */
	private String carId;
    /**
     * 企业ID
     */
	private String orgId;
	/**
	 * 外协等级
	 */
	private Integer level;
    /**
     * 创建人
     */
	private String createUser;
    /**
     * 更新人
     */
	private String updateUser;
    /**
     * 删除标记 0:已删除 1:未删除
     */
//	private Boolean isAble;
//	private LocalDateTime gmtCreate;
//	private LocalDateTime gmtModified;



}
