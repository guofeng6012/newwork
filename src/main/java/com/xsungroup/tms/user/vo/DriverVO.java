package com.xsungroup.tms.user.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DriverVO {

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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标记 0:已删除 1:未删除
     */
    private Integer isAble;

    /**
     * 司机状态 0:空闲  1:出车中  2:请假
     */

    private Integer driverStatus;

    /**
     * 审核状态 0:待审核  1:审核拒绝  2:审核通过
     */
    private Integer auditStatus;


}
