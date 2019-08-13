package com.xsungroup.tms.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xsungroup.tms.core.common.AssertBuss;
import com.xsungroup.tms.core.common.BeanConverter;
import com.xsungroup.tms.core.common.Constant;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.order.common.OrderBusCode;
import com.xsungroup.tms.order.common.SenderConsigneeType;
import com.xsungroup.tms.order.dto.SenderConsigneeDto;
import com.xsungroup.tms.order.entity.SenderConsigneeEntity;
import com.xsungroup.tms.order.service.SenderConsigneeService;
import com.xsungroup.tms.order.vo.SenderConsigneeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 梁建军
 * 创建日期： 2019/8/6
 * 创建时间： 13:40
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Api(tags = "收发货地址")
@RestController
@RequestMapping("/api/sender/consignee")
public class SenderConsigneeController extends SuperController {

    @Autowired
    private SenderConsigneeService senderConsigneeService;

    /**
     * 新增方法
     */
    @ApiOperation(value = "新增")
    @PostMapping("/create")
    public ResponseInfo create(@RequestBody @Validated(SenderConsigneeDto.Create.class) SenderConsigneeDto t) {
        SenderConsigneeEntity entity = t.convert(SenderConsigneeEntity.class);
        // TODO: 2019/8/6 假数据 等有用户信息时候去掉
        entity.setCreateUserId("setCreateUserId");
        entity.setCreateUserName("setCreateUserName");
        entity.setCreateUserOrgId("setCreateUserOrgId");
        entity.setCreateUserOrgName("setCreateUserOrgName");
        entity.setSubordinateOrgId("setSubordinateOrgId");
        entity.setSubordinateOrgName("setSubordinateOrgName");

        return ResponseUtil.result(senderConsigneeService.save(entity));
    }


    /**
     * 修改方法
     */
    @ApiOperation(value = "修改")
    @PutMapping("/{id}")
    public ResponseInfo updateData(@PathVariable("id") String id,
                                   @RequestBody @Validated(SenderConsigneeDto.Update.class) SenderConsigneeDto t) {
        QueryWrapper<SenderConsigneeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("sender_consignee_id", id);
        wrapper.eq("is_able", Constant.AbleEnum.YES.getValue());
        SenderConsigneeEntity categoryEntity = senderConsigneeService.getOne(wrapper);

        AssertBuss.notNull(categoryEntity, OrderBusCode.INVALID_ID);


        SenderConsigneeEntity entity = t.convert(SenderConsigneeEntity.class);
        entity.setSenderConsigneeId(id);
        return ResponseUtil.result(senderConsigneeService.updateById(entity));
    }


    /**
     * 删除方法
     */
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping("/{id:.+}")
    public ResponseInfo delete(@PathVariable("id") String id) throws BussException {
        QueryWrapper<SenderConsigneeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("sender_consignee_id", id);
        wrapper.eq("is_able", Constant.AbleEnum.YES.getValue());
        SenderConsigneeEntity entity = senderConsigneeService.getOne(wrapper, true);

        AssertBuss.notNull(entity, OrderBusCode.INVALID_ID);


        UpdateWrapper<SenderConsigneeEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_able", Constant.AbleEnum.NO.getValue());
        updateWrapper.eq("sender_consignee_id", id);
        boolean result = senderConsigneeService.update(updateWrapper);
        return ResponseUtil.result(result);
    }


    /**
     * 分页
     */
    @ApiOperation(value = "分页")
    @GetMapping("/page")
    public ResponseInfo<IPage<SenderConsigneeVo>> page(@ApiParam("姓名")
                                                       @RequestParam(required = false) String name,
                                                       @ApiParam("联系手机号")
                                                       @RequestParam(required = false) String contactPhone,
                                                       @ApiParam("公司名称")
                                                       @RequestParam(required = false) String orgName,
                                                       @ApiParam("地址类型")
                                                       @RequestParam(required = false) Integer type,
                                                       @ApiParam("详细地址")
                                                       @RequestParam(required = false) String areaDetail,
                                                       @ApiParam("创建组织")
                                                       @RequestParam(required = false) String createUserOrgId) {
        QueryWrapper<SenderConsigneeEntity> qw = new QueryWrapper<>();
        qw.eq("is_able", Constant.AbleEnum.YES.getValue());
        if (!StringUtils.isEmpty(name)) {
            qw.likeRight("name", name);
        }
        if (!StringUtils.isEmpty(contactPhone)) {
            qw.likeRight("contact_phone", contactPhone);
        }
        if (!StringUtils.isEmpty(orgName)) {
            qw.likeRight("org_name", orgName);
        }

        if (type != null) {
            SenderConsigneeType categoryType = SenderConsigneeType.get(type);

            AssertBuss.notNull(categoryType, OrderBusCode.ADDRESS_TYPE_DOES_NOT_EXIST);

            switch (categoryType) {
                case SENDER_CONSIGNEE:
                    qw.in("type", SenderConsigneeType.RECEIVING_GOODS.getType(), SenderConsigneeType.DELIVER_GOODS.getType(), SenderConsigneeType.SENDER_CONSIGNEE.getType());
                    break;
                case DELIVER_GOODS:
                    qw.in("type", SenderConsigneeType.DELIVER_GOODS.getType(), SenderConsigneeType.SENDER_CONSIGNEE.getType());
                    break;
                case RECEIVING_GOODS:
                    qw.in("type", SenderConsigneeType.RECEIVING_GOODS.getType(), SenderConsigneeType.SENDER_CONSIGNEE.getType());
                    break;
                default:
                    qw.eq("type", categoryType.getType());
                    break;
            }
        }
        if (!StringUtils.isEmpty(areaDetail)) {
            qw.likeRight("area_detail", areaDetail);
        }
        if (!StringUtils.isEmpty(createUserOrgId)) {
            qw.likeRight("create_user_org_id", createUserOrgId);
        }
        qw.orderByDesc("weight");
        IPage<SenderConsigneeEntity> iPage = this.getPage(false);
        iPage = senderConsigneeService.page(iPage, qw);
        IPage<SenderConsigneeVo> listVo = BeanConverter.convert(SenderConsigneeVo.class, iPage);
        return ResponseUtil.success(listVo);
    }

}
