package com.xsungroup.tms.matedata.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CarDto  extends Convert implements Serializable {

    private String carId;
    /**
     * 所属单位ID
     */
    private String orgId;
    /**
     * 所属组织名称
     */
    private String orgName;
    /**
     * 选择组织ID
     */
    private String companyId;
    /**
     * 选择组织名称
     */
    private String companyName;
    /**
     * 车辆识别代号
     */
    private String vinNo;
    /**
     * 车牌号
     */
    private String carNo;
    /**
     * 车辆来源 0:自有  1:挂靠  2:亲友车
     */
    private Integer carOrigin;
    /**
     * 审核状态 0:待审核  1:审核拒绝  2:审核通过
     */
    private Integer auditStatus;
    /**
     * 车辆状态 0:空闲  1:出运输中  2:维修
     */
    private Integer carStatus;
    /**
     * 车辆类型
     */
    private String carType;
    /**
     * 车辆分类
     */
    private String carSort;
    //集装箱属性
    private  String containerAttributes;
    //集装箱尺寸
    private  String containerSize;
    //冷藏车类别C
    private  String refrigeratorCategory;
    //危化车类别
    private  String dangerCategory;
    /**
     * 运输类型
     */
    private String transType;
    /**
     * 行驶证正页
     */
    private String licenseMianPage;
    /**
     * 行驶证所有人
     */
    private String licenseName;
    /**
     * 使用性质
     */
    private String userNature;
    /**
     * 行驶证正页
     */
    private String licenseVicePage;
    /**
     * 行驶证有效结束日期
     */
    private String qualificationTime;
    /**
     * 行驶证车辆页
     */
    private String licenseCarPage;
    /**
     * 道路运输证
     */
    private String roadTransCertificate;
    /**
     * 道路运输证号-省
     */
    private String roadTransCertificateProvince;
    /**
     * 道路运输证号-字
     */
    private String roadTransCertificateWord;
    /**
     * 道路运输证号-编号
     */
    private String roadTransCertificateCode;
    /**
     * 车辆载重(单位：克)
     */
    private Integer loadWeight;
    /**
     * 车辆长度
     */
    private String lenght;
    /**
     * 车辆宽度
     */
    private String width;
    /**
     * 车辆高度
     */
    private String high;
    /**
     * 牌照类型
     */
    private String licenseType;
    /**
     * 业户名称
     */
    private String householdName;
    /**
     * 道路运输证有效开始日期
     */
    private String roadTransCertificateStartTime;
    /**
     * 道路运输证有效结束日期
     */
    private String roadTransCertificateEndTime;
    /**
     * 经营范围
     */
    private String roadTransScope;
    /**
     * 危险品运输通行证
     */
    private String dangerLicense;
    /**
     * 车辆正面图
     */
    private String carDirectUrl;
    /**
     * 车辆45度角照
     */
    private String carBevelUrl;

    /**
     * 创建人id
     */
    private String createUser;
    /**
     * 创建人姓名
     */
    private String createUserName;
    /**
     * 关联司机
     */
    private List<CarDriverDto> boundDriver;

}
