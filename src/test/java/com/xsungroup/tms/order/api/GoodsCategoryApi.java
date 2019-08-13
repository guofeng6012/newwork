package com.xsungroup.tms.order.api;

import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.order.dto.GoodsCategoryDto;
import com.xsungroup.tms.order.vo.GoodsCategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 梁建军
 * 创建日期： 2019/8/5
 * 创建时间： 14:58
 * @version 1.0
 * @since 1.0
 */
@Component
@FeignClient("xinya")
@Api("商品分类")
public interface GoodsCategoryApi {


    /**
     * 新增方法
     */
    @ApiOperation(value = "新增")
    @PostMapping(value = "/api/goods/category/create",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseInfo create(@RequestBody GoodsCategoryDto t);


    /**
     * 修改方法
     */
    @ApiOperation(value = "修改")
    @PutMapping(value = "/api/goods/category/{id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseInfo updateData(@PathVariable("id") String id,
                            @RequestBody GoodsCategoryDto t);

    @ApiOperation(value = "列表 不分页")
    @GetMapping(value = "/api/goods/category/list",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseInfo<List<GoodsCategoryVo>> list(@ApiParam("商品分类类型 1：普通货物，2：冷链运输，3：普通/冷链，4：危险品") @RequestParam Integer type);

    /**
     * 商品分类树
     */
    @ApiOperation(value = "商品分类树")
    @GetMapping("/api/goods/category/tree")
    ResponseInfo<List<GoodsCategoryVo>> tree();

    /**
     * 删除方法
     */
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/api/goods/category/{id:.+}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseInfo delete(@PathVariable("id") String id);

    /**
     * 分页
     */
    @ApiOperation(value = "分页")
    @GetMapping(value = "/api/goods/category/page"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseInfo page(@ApiParam("货物分类编码") @RequestParam(value = "code", required = false) String code,
                      @ApiParam("货物分类名称") @RequestParam(value = "name", required = false) String name,
                      @RequestParam(value = "cursor", required = false) Integer cursor,
                      @RequestParam(value = "limit", required = false) Integer limit);
}
