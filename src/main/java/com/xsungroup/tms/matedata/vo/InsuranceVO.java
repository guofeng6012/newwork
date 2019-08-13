package com.xsungroup.tms.matedata.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InsuranceVO {

    private String insuranceId;

    /**险种分类编码 **/
    private String insuranceCode;

    /**险种分类名称 **/
    private String insuranceName;

    /**失效日期 **/
    private String expiryDate;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private LocalDateTime gmtModified;

    /**
     * 是否有效
     */
    @ApiModelProperty("是否有效")
    private Integer isAble;
}
