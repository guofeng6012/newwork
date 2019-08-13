package com.xsungroup.tms.user.entity;

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
 * 司机表
 * </p>
 *
 * @author Alex
 * @since 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_driver")
public class DriverEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "driver_id", type = IdType.UUID)
    private String driverId;

    /**
     * 所属组织ID
     */
    private String orgId;

    /**
     * 选择组织ID
     */
    private String companyId;

    /**
     * 司机手机号码
     */
    private String mobile;

    /**
     * 是否允许司机自行接单
     */
    private Integer isSelfAcceptOrder;

    /**
     * 身份证正面
     */
    private String idCardPos;

    /**
     * 身份证反面
     */
    private String idCardVer;

    /**
     * 身份证姓名
     */
    private String idCardName;

    /**
     * 身份证起始日期
     */
    private String idCardStartTime;

    /**
     * 身份证结束日期
     */
    private String idCardEndTime;

    /**
     * 身份证号码
     */
    private String idCardNo;

    /**
     * 驾驶证照片
     */
    private String driverLicenseUrl;

    /**
     * 驾驶证姓名
     */
    private String driverLicenseName;

    /**
     * 驾驶证编号
     */
    private String driverLicenseNo;

    /**
     * 准驾车型
     */
    private String driverLicenseLevel;

    /**
     * 驾驶证起始日期
     */
    private String driverLicenseStartTime;

    /**
     * 驾驶证有效期限（年）
     */
    private Integer driverLicenseLimit;

    /**
     * 人员从业资格证
     */
    private String qualificationUrl;

    /**
     * 从业资格证姓名
     */
    private String qualificationName;

    /**
     * 从业资格证编号
     */
    private String qualificationNo;

    /**
     * 从业资格证有效开始日期
     */
    private String qualificationStartTime;

    /**
     * 从业资格证有效结束日期
     */
    private String qualificationEntTime;

    /**
     * 创建人
     */
    private String createUser;


    /**
     * 更新人
     */
    private String updateUser;


    /**
     * 司机状态 0:空闲  1:出车中  2:请假
     */
    private Integer driverStatus;

    /**
     * 审核状态 0:待审核  1:审核拒绝  2:审核通过
     */
    private Integer auditStatus;

    /**
     * 是否绑定（0：未绑定，1：已绑定）
     */
    private Integer isBound;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 所属组织名称
     */
    private String orgName;

    /**
     * 选择组织名称
     */
    private String companyName;

    //解绑原因
    private String unbindReason;

    private Integer source;//来源（0：注册，1新增）
    private LocalDateTime firstSubmitTime;//首次提交时间
    private LocalDateTime lastSubmitTime;//最近提交时间
    private Integer auditMode;//审核方式（0：人工，1：自动）
    private String auditUserName;//审核人

}
