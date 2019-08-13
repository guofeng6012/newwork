package com.xsungroup.tms.order.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.order.dto.GoodsAndPackDto;
import com.xsungroup.tms.order.dto.GoodsDto;
import com.xsungroup.tms.order.vo.GoodsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author 梁建军
 * 创建日期： 2019/8/7
 * 创建时间： 19:28
 * @version 1.0
 * @since 1.0
 */
@Component
@FeignClient("xinya")
@Api("商品")
public interface GoodsApi {


    /**
     * 新增方法
     */
    @ApiOperation(value = "新增")
    @PostMapping("/api/goods/create")
    ResponseInfo create(@RequestBody GoodsDto t);

    /**
     * 新增方法
     */
    @ApiOperation(value = "新增")
    @PostMapping("/api/goods/createAndPack")
    ResponseInfo createAndPack(@RequestBody GoodsAndPackDto t);

    /**
     * 修改方法
     */
    @ApiOperation(value = "修改")
    @PutMapping("/api/goods/{id}")
    ResponseInfo updateData(@PathVariable("id") String id,
                            @RequestBody GoodsDto t);


    /**
     * 删除方法
     */
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping("/api/goods/{id:.+}")
    ResponseInfo delete(@PathVariable("id") String id);


    /**
     * 分页
     */
    @ApiOperation(value = "分页")
    @GetMapping("/api/goods/page")
    ResponseInfo<Page<GoodsVo>> page(
            @ApiParam("编码") @RequestParam(required = false) String code,
            @ApiParam("名称") @RequestParam(required = false) String name);

}
