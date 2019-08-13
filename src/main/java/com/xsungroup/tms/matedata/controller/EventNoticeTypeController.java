package com.xsungroup.tms.matedata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.matedata.entity.EventNoticeTypeEntity;
import com.xsungroup.tms.matedata.service.EventNoticeTypeService;
import com.xsungroup.tms.utils.TypeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 事件通知类型表 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-07-18
 */
@Api(tags = "事件通知类型信息")
@RestController
@RequestMapping("/api/eventNoticeType")
public class EventNoticeTypeController extends SuperController {

    @Autowired
    private EventNoticeTypeService eventNoticeTypeService;

    @ApiOperation(value="列表查询")
    @PostMapping("/list")
    public ResponseInfo list(@RequestBody EventNoticeTypeEntity eventNoticeTypeEntity) {
        QueryWrapper<EventNoticeTypeEntity> qw = new QueryWrapper<>();
        qw.eq("is_able",1);
        qw.likeRight("event_notice_type_name", TypeUtils.castToString(eventNoticeTypeEntity.getEventNoticeTypeName(),""));
        qw.likeRight("event_notice_type_code", TypeUtils.castToString(eventNoticeTypeEntity.getEventNoticeTypeCode(),""));
        IPage<EventNoticeTypeEntity> iPage = this.getPage(true);
        iPage = eventNoticeTypeService.page(iPage,qw);
        return  ResponseUtil.success(iPage);
    }


    @ApiOperation(value="新增或修改")
    @PostMapping("/addOrEdit")
    public ResponseInfo addOrEdit(@RequestBody EventNoticeTypeEntity eventNoticeTypeEntity) throws BussException {
        return  ResponseUtil.success(eventNoticeTypeService.addOrEdit(eventNoticeTypeEntity));
    }


}
