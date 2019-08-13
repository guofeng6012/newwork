package com.xsungroup.tms.order.api;

import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.order.dto.GoodsPackDto;
import com.xsungroup.tms.order.vo.GoodsPackVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
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
@Component
@FeignClient("xinya")
@Api("商品包装")
public interface GoodsPackApi {

    /**
     * 新增方法
     */
    @ApiOperation(value = "新增")
    @PostMapping("/api/goods/pack/create")
    ResponseInfo create(@RequestBody GoodsPackDto t);

    /**
     * 修改方法
     */
    @ApiOperation(value = "修改")
    @PutMapping("/api/goods/pack/{id}")
    ResponseInfo updateData(
            @PathVariable("id") String id,
            @RequestBody GoodsPackDto t);


    /**
     * 删除方法
     */
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping("/api/goods/pack/{id:.+}")
    ResponseInfo delete(@PathVariable("id") String id);

    /**
     * 列表
     */
    @ApiOperation(value = "列表")
    @GetMapping("/api/goods/pack/list")
    ResponseInfo<List<GoodsPackVo>> list(
            @NotNull(message = "商品Id不能为空")
            @ApiParam("商品Id")
            @RequestParam String goodsId);

}
