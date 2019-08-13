package com.xsungroup.tms.matedata.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.xsungroup.tms.core.supper.SuperController;

/**
 * <p>
 * 车辆与司机关系表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2019-08-07
 */
@Api("车辆和司机的关系")
@RestController
@RequestMapping("/api/carDriver")
public class CarDriverController extends SuperController {

}

