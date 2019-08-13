package com.xsungroup.tms.order.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.order.dto.PackDto;
import com.xsungroup.tms.order.vo.PackVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api("包装")
public interface PackApi {


    /**
     * 新增方法
     */
    @ApiOperation(value = "新增")
    @PostMapping("/api/pack/create")
    ResponseInfo create(@RequestBody PackDto t);


    /**
     * 修改方法
     */
    @ApiOperation(value = "修改")
    @PutMapping("/api/pack/{id}")
    ResponseInfo updateData(@PathVariable("id") String id,
                            @RequestBody PackDto t);


    /**
     * 删除方法
     */
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping("/api/pack/{id:.+}")
    ResponseInfo delete(@PathVariable("id") String id);


    /**
     * 分页
     */
    @ApiOperation(value = "分页")
    @GetMapping("/api/pack/page")
    ResponseInfo<Page<PackVo>> page();

}
