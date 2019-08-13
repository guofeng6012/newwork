package com.xsungroup.tms.order.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.order.dto.SenderConsigneeDto;
import com.xsungroup.tms.order.vo.SenderConsigneeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author 梁建军
 * 创建日期： 2019/8/6
 * 创建时间： 14:18
 * @version 1.0
 * @since 1.0
 */
@Component
@FeignClient("xinya")
@Api("商品分类")
public interface SenderConsigneeApi {


    /**
     * 新增方法
     */
    @ApiOperation(value = "新增")
    @PostMapping(value = "/api/sender/consignee/create",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseInfo create(@RequestBody SenderConsigneeDto t);

    /**
     * 修改方法
     */
    @ApiOperation(value = "修改")
    @PutMapping(value = "/api/sender/consignee/{id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseInfo updateData(@PathVariable("id") String id,
                            @RequestBody SenderConsigneeDto t);

    /**
     * 删除方法
     */
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/api/sender/consignee/{id:.+}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseInfo delete(@PathVariable("id") String id);


    /**
     * 分页
     */
    @ApiOperation(value = "分页")
    @GetMapping(value = "/api/sender/consignee/page",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseInfo<Page<SenderConsigneeVo>> page(@ApiParam("姓名")
                                                @RequestParam(required = false, name = "name") String name,
                                               @ApiParam("联系手机号")
                                                @RequestParam(required = false, name = "contactPhone") String contactPhone,
                                               @ApiParam("公司名称")
                                                @RequestParam(required = false, name = "orgName") String orgName,
                                               @ApiParam("地址类型")
                                                @RequestParam(required = false, name = "type") Integer type,
                                               @ApiParam("详细地址")
                                                @RequestParam(required = false, name = "areaDetail") String areaDetail,
                                               @ApiParam("创建组织")
                                                @RequestParam(required = false, name = "createUserOrgId") String createUserOrgId);
}
