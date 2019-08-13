package com.xsungroup.tms.user.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.shiro.ShiroUtil;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.user.dto.RoleSelectDTO;
import com.xsungroup.tms.user.entity.RoleEntity;
import com.xsungroup.tms.user.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-07-17
 */
@Api(tags = "角色信息")
@RestController
@RequestMapping("/api/role")
public class RoleController extends SuperController {


    @Autowired
    private RoleService roleService;


    @ApiOperation(value = "列表查询")
    @GetMapping("/list")
    public ResponseInfo list(@RequestHeader() String token,RoleSelectDTO selectDTO) {
        Page page = this.getPage(false);
        String id = ShiroUtil.currentUserId();
        return roleService.list(page, selectDTO,id);
    }


    @ApiOperation(value = "新增或修改")
    @PostMapping("/addOrEdit")
    public ResponseInfo addOrEdit(@RequestHeader() String token, @RequestBody RoleEntity roleEntity) throws BussException {
//        String id = ShiroUtil.currentUserId();
//        roleEntity.setUpdateUser(id);
        roleEntity.setUpdateUser("00000000");
        return  ResponseUtil.success(roleService.addOrEdit(roleEntity));
    }

    @DeleteMapping("/del/{roleId}")
    public ResponseInfo deleteRole(@PathVariable("roleId") String roleId){
        return ResponseUtil.success(roleService.deleteRole(roleId));
    }

    @ApiOperation(value = "通过组织获取角色信息")
    @GetMapping("/roles")
    public ResponseInfo selectRoleByOrgId(@RequestHeader() String token){
        String id = ShiroUtil.currentUserId();
        return  ResponseUtil.success(roleService.selectRoleByOrgId(id));
    }
}
