package com.xsungroup.tms.matedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.entity.EventNoticeTypeEntity;
import com.xsungroup.tms.matedata.mapper.EventNoticeTypeDao;
import com.xsungroup.tms.matedata.service.EventNoticeTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 事件通知类型表 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2019-07-18
 */
@Service
public class EventNoticeTypeServiceImpl extends ServiceImpl<EventNoticeTypeDao, EventNoticeTypeEntity> implements EventNoticeTypeService {

    @Override
    public Object list(IPage<EventNoticeTypeEntity> page, EventNoticeTypeEntity eventNoticeTypeEntity) {
        QueryWrapper<EventNoticeTypeEntity> wrapper = new QueryWrapper<>();
//        wrapper.setEntity(EventNoticeTypeEntity);
        wrapper.eq("is_able", 1);//精确查询
        if (eventNoticeTypeEntity.getEventNoticeTypeName() != null && eventNoticeTypeEntity.getEventNoticeTypeName().length() > 0) {
            wrapper.like("event_notice_type_name", eventNoticeTypeEntity.getEventNoticeTypeName());//模糊查询
        }
        if (eventNoticeTypeEntity.getEventNoticeTypeCode() != null && eventNoticeTypeEntity.getEventNoticeTypeCode().length() > 0) {
            wrapper.like("event_notice_type_code", eventNoticeTypeEntity.getEventNoticeTypeCode());//模糊查询
        }
        wrapper.orderByDesc("event_notice_type_code");//排序
        IPage<EventNoticeTypeEntity> list = baseMapper.selectPage(page, wrapper);
        return list;
    }


    @Override
    public String addOrEdit(EventNoticeTypeEntity eventNoticeTypeEntity) throws BussException {
        check(eventNoticeTypeEntity);//先校验是否存在
        if (StringUtils.isBlank(eventNoticeTypeEntity.getEventNoticeTypeId())) {
            //这时是新增
            baseMapper.insert(eventNoticeTypeEntity);
        } else {
            //修改
            baseMapper.updateById(eventNoticeTypeEntity);
        }
        return eventNoticeTypeEntity.getEventNoticeTypeId();
    }


    public void check(EventNoticeTypeEntity eventNoticeTypeEntity) throws BussException {
        int num = 0;
        if (StringUtils.isBlank(eventNoticeTypeEntity.getEventNoticeTypeId())) {
            //新增判断
            //判断名称
            QueryWrapper<EventNoticeTypeEntity> name = new QueryWrapper<>();
            name.eq("event_notice_type_name", eventNoticeTypeEntity.getEventNoticeTypeName());//
            num = baseMapper.selectCount(name);
            if (num > 0) {
                throw new BussException("该名称已经存在");
            }
            //判断code
            QueryWrapper<EventNoticeTypeEntity> code = new QueryWrapper<>();
            code.eq("event_notice_type_code", eventNoticeTypeEntity.getEventNoticeTypeCode());//
            num = baseMapper.selectCount(code);
            if (num > 0) {
                throw new BussException("该代码已经存在");
            }
        } else {
            //修改判断（校验的时候要排除自身）
            //判断名称
            QueryWrapper<EventNoticeTypeEntity> name = new QueryWrapper<>();
            name.eq("event_notice_type_name", eventNoticeTypeEntity.getEventNoticeTypeName());
            name.ne("event_notice_type_id", eventNoticeTypeEntity.getEventNoticeTypeId());
            num = baseMapper.selectCount(name);
            if (num > 0) {
                throw new BussException("该名称已经存在");
            }
            //判断code
            QueryWrapper<EventNoticeTypeEntity> code = new QueryWrapper<>();
            code.eq("event_notice_type_code", eventNoticeTypeEntity.getEventNoticeTypeCode());
            code.ne("event_notice_type_id", eventNoticeTypeEntity.getEventNoticeTypeId());
            num = baseMapper.selectCount(code);
            if (num > 0) {
                throw new BussException("该代码已经存在");
            }
        }
    }

}
