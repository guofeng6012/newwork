package com.xsungroup.tms.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xsungroup.tms.core.common.AssertBuss;
import com.xsungroup.tms.core.common.Constant;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.order.common.OrderBusCode;
import com.xsungroup.tms.order.common.GoodsCategoryType;
import com.xsungroup.tms.order.dto.GoodsCategoryDto;
import com.xsungroup.tms.order.entity.GoodsCategoryEntity;
import com.xsungroup.tms.order.service.GoodsCategoryService;
import com.xsungroup.tms.order.vo.GoodsCategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 梁建军
 * 创建日期： 2019/8/5
 * 创建时间： 14:58
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Api(tags = "商品分类")
@RestController
@RequestMapping("/api/goods/category")
public class GoodsCategoryController extends SuperController {


    @Autowired
    private GoodsCategoryService goodsCategoryService;

    /**
     * 新增方法
     */
    @ApiOperation(value = "新增")
    @PostMapping("/create")
    public ResponseInfo create(@RequestBody @Validated(GoodsCategoryDto.Create.class) GoodsCategoryDto t) {
        GoodsCategoryEntity entity = t.convert(GoodsCategoryEntity.class);


        String goodsCategoryParentId = entity.getGoodsCategoryParentId();

        if (!StringUtils.isEmpty(goodsCategoryParentId)) {
            QueryWrapper<GoodsCategoryEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("goods_category_id", goodsCategoryParentId);
            wrapper.eq("is_able", Constant.AbleEnum.YES.getValue());
            GoodsCategoryEntity entity1 = goodsCategoryService.getOne(wrapper);

            AssertBuss.notNull(entity1, OrderBusCode.PARENT_CLASS_NOT_FOUND);

            entity.setGoodsCategoryLevel(entity1.getGoodsCategoryLevel() + 1);
            entity.setGoodsCategoryType(entity1.getGoodsCategoryType());

        }


        return ResponseUtil.result(goodsCategoryService.save(entity));
    }


    /**
     * 修改方法
     */
    @ApiOperation(value = "修改")
    @PutMapping("/{id}")
    public ResponseInfo updateData(@PathVariable("id") String id,
                                   @RequestBody @Validated(GoodsCategoryDto.Update.class) GoodsCategoryDto t) {
        QueryWrapper<GoodsCategoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_category_id", id);
        wrapper.eq("is_able", Constant.AbleEnum.YES.getValue());
        GoodsCategoryEntity categoryEntity = goodsCategoryService.getOne(wrapper);

        AssertBuss.notNull(categoryEntity, OrderBusCode.INVALID_ID);

        GoodsCategoryEntity entity = t.convert(GoodsCategoryEntity.class);
        entity.setGoodsCategoryId(id);
        entity.setGoodsCategoryType(entity.getGoodsCategoryType());
        return ResponseUtil.result(goodsCategoryService.updateById(entity));
    }

//    @ApiOperation(value = "列表 不分页")
//    @GetMapping("/list")
//    public ResponseInfo<List<GoodsCategoryVo>> list(@ApiParam("商品分类类型 1：普通货物，2：冷链运输，3：普通/冷链，4：危险品") @RequestParam Integer type) {
//
//        QueryWrapper<GoodsCategoryEntity> wrapper = new QueryWrapper<>();
//
//        GoodsCategoryType categoryType = GoodsCategoryType.get(type);
//        Assert.notNull(categoryType, "商品分类类型不存在");
//        switch (categoryType) {
//            case COMMON_COLD_CHAIN:
//                wrapper.in("goods_category_type", GoodsCategoryType.COMMON, GoodsCategoryType.COLD_CHAIN);
//                break;
//            default:
//                wrapper.eq("goods_category_type", categoryType.getType());
//                break;
//        }
//        wrapper.eq("is_able", Constant.AbleEnum.YES.getValue());
//        wrapper.orderByDesc("weight");
//        List<GoodsCategoryEntity> list = goodsCategoryService.list(wrapper);
//        List<GoodsCategoryVo> listVo = BeanConverter.convert(GoodsCategoryVo.class, list);
//        return ResponseUtil.success(listVo);
//    }

//    /**
//     * 分页
//     */
//    @ApiOperation(value = "分页")
//    @GetMapping("/page")
//    public ResponseInfo<IPage<GoodsCategoryVo>> page(@ApiParam("货物分类编码") @RequestParam(required = false) String code,
//                                                     @ApiParam("货物分类名称") @RequestParam(required = false) String name) {
//        QueryWrapper<GoodsCategoryEntity> qw = new QueryWrapper<>();
//        qw.eq("is_able", Constant.AbleEnum.YES.getValue());
//        if (!StringUtils.isEmpty(code)) {
//            qw.likeRight("goods_category_code", code);
//        }
//        if (!StringUtils.isEmpty(name)) {
//            qw.likeRight("goods_category_name", code);
//        }
//        qw.orderByDesc("weight");
//        IPage<GoodsCategoryEntity> iPage = this.getPage(false);
//        iPage = goodsCategoryService.page(iPage, qw);
//        IPage<GoodsCategoryVo> listVo = BeanConverter.convert(GoodsCategoryVo.class, iPage);
//        return ResponseUtil.success(listVo);
//    }

    /**
     * 商品分类树
     */
    @ApiOperation(value = "商品分类树")
    @GetMapping("/tree")
    public ResponseInfo<List<GoodsCategoryVo>> tree(@ApiParam("类型 1：普通货物，2：冷链运输，3：普通/冷链，4：危险品")
                                                    @RequestParam(required = false) Integer type,
                                                    @ApiParam("货物分类编码") @RequestParam(required = false) String code,
                                                    @ApiParam("货物分类名称") @RequestParam(required = false) String name) throws BussException {
        QueryWrapper<GoodsCategoryEntity> qw = new QueryWrapper<>();
        qw.eq("is_able", Constant.AbleEnum.YES.getValue());
        qw.orderByAsc("goods_category_level");
        qw.orderByDesc("weight");
        qw.orderByDesc("gmt_create");
        if (!StringUtils.isEmpty(code)) {
            qw.likeRight("goods_category_code", code);
        }
        if (!StringUtils.isEmpty(name)) {
            qw.likeRight("goods_category_name", name);
        }
        if (type != null) {
            GoodsCategoryType categoryType = GoodsCategoryType.get(type);

            AssertBuss.notNull(categoryType, OrderBusCode.CLASSIFICATION_TYPE_DOES_NOT_EXIST);

            switch (categoryType) {
                case COMMON_COLD_CHAIN:
                    qw.in("goods_category_type", GoodsCategoryType.COMMON.getType(), GoodsCategoryType.COLD_CHAIN.getType(), GoodsCategoryType.COMMON_COLD_CHAIN.getType());
                    break;
                default:
                    qw.eq("goods_category_type", categoryType.getType());
                    break;
            }
        }

        List<GoodsCategoryEntity> list = goodsCategoryService.list(qw);

        if (list == null) {
            return ResponseUtil.success();
        }

        Map<String, GoodsCategoryVo> map = new HashMap<>();

        List<GoodsCategoryVo> voList = new ArrayList<>(list.size() > 32 ? list.size() / 2 : 16);
        for (
                GoodsCategoryEntity goodsCategoryEntity : list) {
            GoodsCategoryVo convert = goodsCategoryEntity.convert(GoodsCategoryVo.class);

            if (StringUtils.isEmpty(convert.getGoodsCategoryParentId())) {
                convert.setGoodsCategoryParentId(null);
                voList.add(convert);

            } else {
                GoodsCategoryVo goodsCategoryVo = map.get(convert.getGoodsCategoryParentId());

                if (goodsCategoryVo == null) {
                    convert.setGoodsCategoryParentId(null);
                    voList.add(convert);
                } else {

                    List<GoodsCategoryVo> child = goodsCategoryVo.getChildren();
                    if (child == null) {
                        child = new ArrayList<>();
                        goodsCategoryVo.setChildren(child);
                    }
                    child.add(convert);
                }

            }

            map.put(convert.getGoodsCategoryId(), convert);
        }

        return ResponseUtil.success(voList);

    }

    /**
     * 删除方法
     */
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping("/{id:.+}")
    public ResponseInfo delete(@PathVariable("id") String id) throws BussException {
        QueryWrapper<GoodsCategoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_category_id", id);
        wrapper.eq("is_able", Constant.AbleEnum.YES.getValue());
        GoodsCategoryEntity entity = goodsCategoryService.getOne(wrapper, true);

        AssertBuss.notNull(entity, OrderBusCode.INVALID_ID);

        QueryWrapper<GoodsCategoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_category_parent_id", id);
        queryWrapper.eq("is_able", Constant.AbleEnum.YES.getValue());
        int count = goodsCategoryService.count(queryWrapper);

        AssertBuss.leZero(count, OrderBusCode.EXISTENCE_SUBCLASS_NOT_DELETE);


        UpdateWrapper<GoodsCategoryEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_able", Constant.AbleEnum.NO.getValue());
        updateWrapper.eq("goods_category_id", id);
        boolean result = goodsCategoryService.update(updateWrapper);
        return ResponseUtil.result(result);
    }
}
