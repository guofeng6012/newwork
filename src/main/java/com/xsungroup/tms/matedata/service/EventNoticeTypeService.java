package com.xsungroup.tms.matedata.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.entity.EventNoticeTypeEntity;

/**
 * <p>
 * 事件通知类型表 服务类
 * </p>
 *
 * @author Alex
 * @since 2019-07-18
 */
public interface EventNoticeTypeService extends IService<EventNoticeTypeEntity> {

    Object list(IPage<EventNoticeTypeEntity> page, EventNoticeTypeEntity eventNoticeTypeEntity);

    String addOrEdit(EventNoticeTypeEntity eventNoticeTypeEntity) throws BussException;

}
