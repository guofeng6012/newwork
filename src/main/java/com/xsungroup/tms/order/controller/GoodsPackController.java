package com.xsungroup.tms.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xsungroup.tms.core.common.AssertBuss;
import com.xsungroup.tms.core.common.BeanConverter;
import com.xsungroup.tms.core.common.Constant;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.order.common.OrderBusCode;
import com.xsungroup.tms.order.dto.GoodsPackDto;
import com.xsungroup.tms.order.entity.GoodsPackEntity;
import com.xsungroup.tms.order.service.GoodsPackService;
import com.xsungroup.tms.order.vo.GoodsPackVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 梁建军
 * 创建日期： 2019/8/9
 * 创建时间： 13:49
 * @version 1.0
 * @since 1.0
 * 商品包装
 */
@RestController
@RequestMapping("/api/goods/pack")
public class GoodsPackController {


    @Autowired
    private GoodsPackService goodsPackService;

    /**
     * 新增方法
     */
    @ApiOperation(value = "新增")
    @PostMapping("/create")
    public ResponseInfo create(@RequestBody @Validated(GoodsPackDto.Create.class) GoodsPackDto t) {
        t.checkRequest();
        GoodsPackEntity entity = t.convert(GoodsPackEntity.class);
        if (entity.getGoodsPackLength()!=null) {
            entity.setGoodsPackVolume(entity.getGoodsPackLength() * entity.getGoodsPackWidth() * entity.getGoodsPackHeight());
            entity.setGoodsPackVolumeShowUnit("cm³");
        }
        return ResponseUtil.result(goodsPackService.save(entity));
    }


    /**
     * 修改方法
     */
    @ApiOperation(value = "修改")
    @PutMapping("/{id}")
    public ResponseInfo updateData(
            @PathVariable("id") String id,
            @RequestBody @Validated(GoodsPackDto.Update.class) GoodsPackDto t) {
        t.checkRequest();
        QueryWrapper<GoodsPackEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_pack_id", id);
        wrapper.eq("is_able", Constant.AbleEnum.YES.getValue());
        GoodsPackEntity GoodsPackEntity = goodsPackService.getOne(wrapper);

        AssertBuss.notNull(GoodsPackEntity, OrderBusCode.INVALID_ID);

        GoodsPackEntity entity = t.convert(GoodsPackEntity.class);
        if (entity.getGoodsPackLength()!=null) {
            entity.setGoodsPackVolume(entity.getGoodsPackLength() * entity.getGoodsPackWidth() * entity.getGoodsPackHeight());
            entity.setGoodsPackVolumeShowUnit("cm³");
        }
        entity.setGoodsPackId(id);
        return ResponseUtil.result(goodsPackService.updateById(entity));
    }


    /**
     * 删除方法
     */
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping("/{id:.+}")
    public ResponseInfo delete(@PathVariable("id") String id) throws BussException {
        QueryWrapper<GoodsPackEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_pack_id", id);
        wrapper.eq("is_able", Constant.AbleEnum.YES.getValue());
        GoodsPackEntity entity = goodsPackService.getOne(wrapper, true);

        AssertBuss.notNull(entity, OrderBusCode.INVALID_ID);


        UpdateWrapper<GoodsPackEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_able", Constant.AbleEnum.NO.getValue());
        updateWrapper.eq("goods_pack_id", id);
        boolean result = goodsPackService.update(updateWrapper);
        return ResponseUtil.result(result);
    }

    /**
     * 列表
     */
    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ResponseInfo<List<GoodsPackVo>> list(
            @NotNull(message = "商品Id不能为空")
            @ApiParam("商品Id")
            @RequestParam String goodsId) {
        QueryWrapper<GoodsPackEntity> qw = new QueryWrapper<>();
        qw.eq("is_able", Constant.AbleEnum.YES.getValue());

        if (!StringUtils.isEmpty(goodsId)) {
            qw.eq("goods_id", goodsId);
        }
        qw.orderByDesc("weight");
        qw.orderByDesc("gmt_create");
        List<GoodsPackEntity> list = goodsPackService.list(qw);
        List<GoodsPackVo> listVo = BeanConverter.convert(GoodsPackVo.class, list);
        return ResponseUtil.success(listVo);
    }

}
