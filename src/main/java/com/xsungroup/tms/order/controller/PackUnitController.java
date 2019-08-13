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
import com.xsungroup.tms.order.dto.PackUnitDto;
import com.xsungroup.tms.order.entity.PackUnitEntity;
import com.xsungroup.tms.order.service.PackUnitService;
import com.xsungroup.tms.order.vo.PackUnitVo;
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
 * 创建日期： 2019/8/8
 * 创建时间： 14:26
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Api(tags = "包装单位")
@RestController
@RequestMapping("/api/pack/unit")
public class PackUnitController extends SuperController {


    @Autowired
    private PackUnitService packUnitService;

    /**
     * 新增方法
     */
    @ApiOperation(value = "新增")
    @PostMapping("/create")
    public ResponseInfo create(@RequestBody @Validated(PackUnitDto.Create.class) PackUnitDto t) {
        PackUnitEntity entity = t.convert(PackUnitEntity.class);
        return ResponseUtil.result(packUnitService.save(entity));
    }


    /**
     * 修改方法
     */
    @ApiOperation(value = "修改")
    @PutMapping("/{id}")
    public ResponseInfo updateData(@PathVariable("id") String id,
                                   @RequestBody @Validated(PackUnitDto.Update.class) PackUnitDto t) {
        QueryWrapper<PackUnitEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("pack_unit_id", id);
        wrapper.eq("is_able", Constant.AbleEnum.YES.getValue());
        PackUnitEntity packUnitEntity = packUnitService.getOne(wrapper);

        AssertBuss.notNull(packUnitEntity, OrderBusCode.INVALID_ID);

        PackUnitEntity entity = t.convert(PackUnitEntity.class);

        entity.setPackUnitId(id);
        return ResponseUtil.result(packUnitService.updateById(entity));
    }


    /**
     * 删除方法
     */
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping("/{id:.+}")
    public ResponseInfo delete(@PathVariable("id") String id) throws BussException {
        QueryWrapper<PackUnitEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("pack_unit_id", id);
        wrapper.eq("is_able", Constant.AbleEnum.YES.getValue());
        PackUnitEntity entity = packUnitService.getOne(wrapper, true);

        AssertBuss.notNull(entity, OrderBusCode.INVALID_ID);


        UpdateWrapper<PackUnitEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_able", Constant.AbleEnum.NO.getValue());
        updateWrapper.eq("pack_unit_id", id);
        boolean result = packUnitService.update(updateWrapper);
        return ResponseUtil.result(result);
    }


    /**
     * 分页
     */
    @ApiOperation(value = "分页")
    @GetMapping("/page")
    public ResponseInfo<IPage<PackUnitVo>> page(
            @ApiParam("编码")
            @RequestParam(required = false) String code,
            @ApiParam("名称")
            @RequestParam(required = false) String name) {
        QueryWrapper<PackUnitEntity> qw = new QueryWrapper<>();
        qw.eq("is_able", Constant.AbleEnum.YES.getValue());
        if (!StringUtils.isEmpty(code)) {
            qw.likeRight("pack_unit_code", code);
        }
        if (!StringUtils.isEmpty(name)) {
            qw.likeRight("pack_unit_name", name);
        }
        qw.orderByDesc("weight");
        qw.orderByDesc("gmt_create");
        IPage<PackUnitEntity> iPage = this.getPage(false);
        iPage = packUnitService.page(iPage, qw);
        IPage<PackUnitVo> listVo = BeanConverter.convert(PackUnitVo.class, iPage);
        return ResponseUtil.success(listVo);
    }
}
