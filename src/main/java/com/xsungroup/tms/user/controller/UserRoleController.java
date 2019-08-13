package com.xsungroup.tms.user.controller;


import com.xsungroup.tms.core.supper.SuperController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户角色表 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-07-17
 */
@RestController
@Api("用户角色关联关系")
@RequestMapping("/api/userRole")
public class UserRoleController extends SuperController {

}
