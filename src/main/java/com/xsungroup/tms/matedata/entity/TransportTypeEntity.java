package com.xsungroup.tms.matedata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xsungroup.tms.core.supper.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 运输类型表
 * </p>
 *
 * @author Alex
 * @since 2019-07-15
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel
@TableName("zc_trans_type")
public class TransportTypeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 运输类型编码
     */
    @TableId(type = IdType.UUID)
    @ApiModelProperty(hidden = true)
    private String transTypeId;


    /**
     * 运输类型编码
     */
    @TableField
    @ApiModelProperty(value = "运输类型编码")
    private String transTypeCode;

    /**
     * 运输类型名称
     */
    @TableField
    @ApiModelProperty(value = "运输类型名称")
    private String transTypeName;

    /**
     * 失效日期
     */
    @TableField
    @ApiModelProperty(value = "失效日期")
    private String expiryDate;


//    private int pageNum;
//    private int pageSize;

}
