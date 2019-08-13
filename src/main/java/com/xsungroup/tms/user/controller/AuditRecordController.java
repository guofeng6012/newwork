package com.xsungroup.tms.user.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.xsungroup.tms.core.supper.SuperController;

/**
 * <p>
 * 审核记录表 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-08-08
 */
@Api("审核记录")
@RestController
@RequestMapping("/auditRecord")
public class AuditRecordController extends SuperController {

}

