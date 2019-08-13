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
 * 事件通知类型表
 * </p>
 *
 * @author Alex
 * @since 2019-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_event_notice_type")
public class EventNoticeTypeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 事件通知类型ID
     */
    @TableId(value = "event_notice_type_id", type = IdType.UUID)
    private String eventNoticeTypeId;

    /**
     * 事件通知类型编码
     */
    private String eventNoticeTypeCode;

    /**
     * 事件通知类型名称
     */
    private String eventNoticeTypeName;

    /**
     * 通知文案
     */
    private String content;

    /**
     * 失效日期
     */
    private String expiryDate;


}
