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
import com.xsungroup.tms.order.common.GoodsCategoryType;
import com.xsungroup.tms.order.common.OrderBusCode;
import com.xsungroup.tms.order.dto.GoodsAndPackDto;
import com.xsungroup.tms.order.dto.GoodsDto;
import com.xsungroup.tms.order.entity.GoodsEntity;
import com.xsungroup.tms.order.service.GoodsService;
import com.xsungroup.tms.order.vo.GoodsVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2019-08-08
 */

@RestController
@RequestMapping("/api/goods")
public class GoodsController extends SuperController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 新增方法
     */
    @ApiOperation(value = "新增")
    @PostMapping("/create")
    public ResponseInfo create(@RequestBody @Validated(GoodsDto.Create.class) GoodsDto t) {
        t.checkRequest();
        GoodsEntity entity = t.convert(GoodsEntity.class);
        // TODO: 2019/8/6 假数据 等有用户信息时候去掉
        entity.setCreateUserId("setCreateUserId");
        entity.setCreateUserName("setCreateUserName");
        entity.setCreateUserOrgId("setCreateUserOrgId");
        entity.setCreateUserOrgName("setCreateUserOrgName");
        entity.setSubordinateOrgId("setSubordinateOrgId");
        entity.setSubordinateOrgName("setSubordinateOrgName");

        goodsService.save(entity);
        return ResponseUtil.success(entity.getGoodsId());
    }

    /**
     * 新增方法
     */
    @ApiOperation(value = "新增")
    @PostMapping("/createAndPack")
    public ResponseInfo createAndPack(@RequestBody @Validated(GoodsAndPackDto.CreatePack.class) GoodsAndPackDto t) {
        t.checkRequest();
        goodsService.createAndPack(t);
        return ResponseUtil.success();
    }


    /**
     * 修改方法
     */
    @ApiOperation(value = "修改")
    @PutMapping("/{id}")
    public ResponseInfo updateData(@PathVariable("id") String id,
                                   @RequestBody @Validated(GoodsDto.Update.class) GoodsDto t) {
        t.checkRequest();
        QueryWrapper<GoodsEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id", id);
        wrapper.eq("is_able", Constant.AbleEnum.YES.getValue());
        GoodsEntity GoodsEntity = goodsService.getOne(wrapper);

        AssertBuss.notNull(GoodsEntity, OrderBusCode.INVALID_ID);

        GoodsEntity entity = t.convert(GoodsEntity.class);

        entity.setGoodsId(id);
        return ResponseUtil.result(goodsService.updateById(entity));
    }


    /**
     * 删除方法
     */
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping("/{id:.+}")
    public ResponseInfo delete(@PathVariable("id") String id) throws BussException {
        QueryWrapper<GoodsEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id", id);
        wrapper.eq("is_able", Constant.AbleEnum.YES.getValue());
        GoodsEntity entity = goodsService.getOne(wrapper, true);

        AssertBuss.notNull(entity, OrderBusCode.INVALID_ID);


        UpdateWrapper<GoodsEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_able", Constant.AbleEnum.NO.getValue());
        updateWrapper.eq("goods_id", id);
        boolean result = goodsService.update(updateWrapper);
        return ResponseUtil.result(result);
    }

    /**
     * 分页
     */
    @ApiOperation(value = "分页")
    @GetMapping("/page")
    public ResponseInfo<IPage<GoodsVo>> page(
            @ApiParam("编码") @RequestParam(required = false) String code,
            @ApiParam("1：普通货物，2：冷链运输，3：普通/冷链，4：危险品") @RequestParam(required = false) Integer type,
            @ApiParam("名称") @RequestParam(required = false) String name) {
        QueryWrapper<GoodsEntity> qw = new QueryWrapper<>();
        qw.eq("is_able", Constant.AbleEnum.YES.getValue());
        if (!StringUtils.isEmpty(code)) {
            qw.likeRight("goods_code", code);
        }
        if (!StringUtils.isEmpty(name)) {
            qw.likeRight("goods_name", code);
        }

        if (type != null) {
            GoodsCategoryType categoryType = GoodsCategoryType.get(type);

            AssertBuss.notNull(categoryType, OrderBusCode.CLASSIFICATION_TYPE_DOES_NOT_EXIST);

            switch (categoryType) {
                case COMMON_COLD_CHAIN:
                    qw.in("goods_type", GoodsCategoryType.COMMON.getType(), GoodsCategoryType.COLD_CHAIN.getType(), GoodsCategoryType.COMMON_COLD_CHAIN.getType());
                    break;
                default:
                    qw.eq("goods_type", categoryType.getType());
                    break;
            }
        }

        qw.orderByDesc("weight");
        qw.orderByDesc("gmt_create");
        IPage<GoodsEntity> iPage = this.getPage(false);
        iPage = goodsService.page(iPage, qw);
        IPage<GoodsVo> listVo = BeanConverter.convert(GoodsVo.class, iPage);
        return ResponseUtil.success(listVo);
    }

}

