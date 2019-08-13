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
import com.xsungroup.tms.order.dto.PackDto;
import com.xsungroup.tms.order.entity.PackEntity;
import com.xsungroup.tms.order.service.PackService;
import com.xsungroup.tms.order.vo.PackVo;
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
 * 创建日期： 2019/8/7
 * 创建时间： 14:46
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Api(tags = "包装默认")
@RestController
@RequestMapping("/api/pack")
public class PackController extends SuperController {


    @Autowired
    private PackService packService;

    /**
     * 新增方法
     */
    @ApiOperation(value = "新增")
    @PostMapping("/create")
    public ResponseInfo create(@RequestBody @Validated(PackDto.Create.class) PackDto t) {
        t.checkRequest();
        PackEntity entity = t.convert(PackEntity.class);
        // TODO: 2019/8/6 假数据 等有用户信息时候去掉
        entity.setCreateUserId("setCreateUserId");
        entity.setCreateUserName("setCreateUserName");
        entity.setCreateUserOrgId("setCreateUserOrgId");
        entity.setCreateUserOrgName("setCreateUserOrgName");
        entity.setSubordinateOrgId("setSubordinateOrgId");
        entity.setSubordinateOrgName("setSubordinateOrgName");
        if (entity.getGoodsPackLength() != null) {
            entity.setGoodsPackVolume(entity.getGoodsPackLength() * entity.getGoodsPackWidth() * entity.getGoodsPackHeight());
            entity.setGoodsPackVolumeShowUnit("cm³");
        }


        return ResponseUtil.result(packService.save(entity));
    }


    /**
     * 修改方法
     */
    @ApiOperation(value = "修改")
    @PutMapping("/{id}")
    public ResponseInfo updateData(@PathVariable("id") String id,
                                   @RequestBody @Validated(PackDto.Update.class) PackDto t) {
        t.checkRequest();
        QueryWrapper<PackEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("pack_id", id);
        wrapper.eq("is_able", Constant.AbleEnum.YES.getValue());
        PackEntity packEntity = packService.getOne(wrapper);

        AssertBuss.notNull(packEntity, OrderBusCode.INVALID_ID);

        PackEntity entity = t.convert(PackEntity.class);

        if (entity.getGoodsPackLength() != null) {
            entity.setGoodsPackVolume(entity.getGoodsPackLength() * entity.getGoodsPackWidth() * entity.getGoodsPackHeight());
            entity.setGoodsPackVolumeShowUnit("cm³");
        }
        entity.setPackId(id);
        return ResponseUtil.result(packService.updateById(entity));
    }


    /**
     * 删除方法
     */
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping("/{id:.+}")
    public ResponseInfo delete(@PathVariable("id") String id) throws BussException {
        QueryWrapper<PackEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("pack_id", id);
        wrapper.eq("is_able", Constant.AbleEnum.YES.getValue());
        PackEntity entity = packService.getOne(wrapper, true);

        AssertBuss.notNull(entity, OrderBusCode.INVALID_ID);


        UpdateWrapper<PackEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_able", Constant.AbleEnum.NO.getValue());
        updateWrapper.eq("pack_id", id);
        boolean result = packService.update(updateWrapper);
        return ResponseUtil.result(result);
    }


    /**
     * 分页
     */
    @ApiOperation(value = "分页")
    @GetMapping("/page")
    public ResponseInfo<IPage<PackVo>> page(
            @ApiParam("编码")
            @RequestParam(required = false) String code,
            @ApiParam("名称")
            @RequestParam(required = false) String name) {
        QueryWrapper<PackEntity> qw = new QueryWrapper<>();
        qw.eq("is_able", Constant.AbleEnum.YES.getValue());
        // TODO: 2019/8/7 筛选条件
        if (!StringUtils.isEmpty(code)) {
            qw.likeRight("goods_pack_code", code);
        }
        if (!StringUtils.isEmpty(name)) {
            qw.likeRight("goods_pack_name", name);
        }
        qw.orderByDesc("weight");
        qw.orderByDesc("gmt_create");
        IPage<PackEntity> iPage = this.getPage(false);
        iPage = packService.page(iPage, qw);
        IPage<PackVo> listVo = BeanConverter.convert(PackVo.class, iPage);
        return ResponseUtil.success(listVo);
    }


}
