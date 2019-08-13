package com.xsungroup.tms.user.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsungroup.tms.core.common.CachePre;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.shiro.ShiroUtil;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.user.common.BusCode;
import com.xsungroup.tms.user.dto.AuditDTO;
import com.xsungroup.tms.user.dto.UserDto;
import com.xsungroup.tms.user.dto.UserSelectDTO;
import com.xsungroup.tms.user.entity.UserEntity;
import com.xsungroup.tms.user.service.UserService;
import com.xsungroup.tms.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户管理(此表不是备份，用于平台用户管理) 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-07-17
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping("/api/user")
public class UserController extends SuperController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "获取角色信息和角色对应的权限信息", notes = "获取角色信息和角色对应的权限信息")
    @PostMapping(value = "/getPermInfo")
    public ResponseInfo getPermInfo(@RequestBody UserEntity userEntity) {

        return ResponseUtil.success(userService.getPermInfo(userEntity));
    }

    @GetMapping("/viewCreateOrg/{userId}")
    public ResponseInfo getUserCreateViewPerm(@PathVariable ("userId") String userId){

        return  ResponseUtil.success(userService.listPermUserOrg(userId));
    }


    @PostMapping("/add")
    @ApiOperation(value="新增用户信息")
    public ResponseInfo addUser(@RequestHeader(value = "token") String token, @RequestBody UserDto userDto){
        String userId = ShiroUtil.currentUserId();
        return  ResponseUtil.success(userService.addUser(userId,userDto));
    }


    @PostMapping("/edit")
    @ApiOperation(value="修改用户信息")
    public ResponseInfo editUser(@RequestHeader(value = "token") String token, @RequestBody UserDto userDto){
        String userId = ShiroUtil.currentUserId();
        return  ResponseUtil.success(userService.editUser(userId,userDto));
    }


    @GetMapping(value = "/list")
    public ResponseInfo findByPageUser(@RequestHeader(value = "token") String token,UserSelectDTO userSelectDTO){
        Page page = this.getPage(false);
        String id = ShiroUtil.currentUserId();
        ResponseInfo responseInfo = userService.findByPageUser(userSelectDTO,id,page);
        return responseInfo;
    }

    /**
     * 重置密码
     * @param userId
     * @param password
     * @return
     */
    @PostMapping("/resetPassword")
    public ResponseInfo updatePassword(String userId,String password){
        return ResponseUtil.success(userService.editPassword(userId,password));
    }


    @DeleteMapping("/del/{userId}")
    public ResponseInfo delUser(@RequestHeader(value = "token") String token,@PathVariable("userId") String userId){
        String userIds = ShiroUtil.currentUserId();
        if(userIds.equals(userId)){
            throw new BussException(BusCode.user_check_err_21);
        }
        return ResponseUtil.success(userService.delUser(userId));
    }

    /**
     * 单个查询方法.
     *
     * @param userId
     */
    @ApiOperation(value = "单个查询")
    @GetMapping("/{userId}")
    public ResponseInfo selectById(@PathVariable String userId){
        UserEntity userEntity = userService.getById(userId);
        return  ResponseUtil.success(userEntity);
    }

    /**
     * 查询个人审核列表 分页
     * @param auditStatus
     * @param orgRole
     * @param realName
     * @param phoneNo
     * @return
     */
    @GetMapping("/audit/list")
    public ResponseInfo findByUserAuditPage(String auditStatus,String orgRole,String realName,String phoneNo){
        Page page = this.getPage(false);
        return ResponseUtil.success(userService.findByUserAuditPage(page,phoneNo,realName,auditStatus,orgRole));
    }

    /**
     * 查询个人审核用户详情
     * @param userId
     * @return
     */
    @GetMapping("/audit/{userId}")
    public ResponseInfo getByUserAuditDetail(@PathVariable("userId") String userId){
        return ResponseUtil.success(userService.getUserAuditDetail(userId));
    }

     /**
     * 审核个人信息
     * @return
     */
    @PostMapping("/audit/user")
    public ResponseInfo auditUser(@RequestBody AuditDTO auditDTO){
        String userIds = ShiroUtil.currentUserId();
        if(userService.auditUser(auditDTO,userIds) > 0 ){
            return ResponseUtil.success("审核成功");
        }
        return ResponseUtil.error("审核失败");
    }

    @GetMapping("/phoneNo/list/{phoneNo}")
    public ResponseInfo findByPhoneNo(@PathVariable("phoneNo") String phoneNo){
        List<UserEntity> list = userService.listByPhone(phoneNo);
        List<Map<String,String>> listMap = new ArrayList<>();
        list.stream().forEach(r ->{
            Map<String,String> map = new HashMap<>();
            map.put("userId",r.getUserId());
            map.put("realName",r.getRealName());
            listMap.add(map);
        });
        return ResponseUtil.success(listMap);
    }
}
