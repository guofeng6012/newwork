package com.xsungroup.tms.order.controller;

import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.order.dto.OrderCreateDto;
import com.xsungroup.tms.order.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 梁建军
 * 创建日期： 2019/8/12
 * 创建时间： 11:45
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 新增方法
     */
    @ApiOperation(value = "新增")
    @PostMapping("/create")
    public ResponseInfo create(@RequestBody @Validated(OrderCreateDto.Create.class) OrderCreateDto t) {
        t.checkRequest();
        return ResponseUtil.success(orderService.create(t));
    }

}
