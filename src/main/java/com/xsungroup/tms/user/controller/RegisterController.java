package com.xsungroup.tms.user.controller;

import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.user.dto.ForgetPasswordDTO;
import com.xsungroup.tms.user.dto.OrgExtendRegisterDTO;
import com.xsungroup.tms.user.service.RegisterService;
import com.xsungroup.tms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author GF
 * @Date 2019-7-29 09:50:57
 **/
@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    /**
     * 注册企业用户
     * @param orgExtendDTO
     * @return
     */
    @PostMapping("/org")
    public ResponseInfo register(@RequestBody OrgExtendRegisterDTO orgExtendDTO){
        return registerService.register(orgExtendDTO);
    }

    /**
     * 忘记密码 第一步校验
     * @param phoneNo
     * @param smsCode
     * @return
     */
    @GetMapping("/forget/check")
    public ResponseInfo forgetCheck(String phoneNo,String smsCode){

        return registerService.forgetCheck(smsCode,phoneNo);
    }

    /**
     * 忘记密码 确认
     * @param forgetPasswordDTO
     * @return
     */
    @PostMapping("/forget/password")
    public ResponseInfo forgetPassword(@RequestBody ForgetPasswordDTO forgetPasswordDTO){

        return registerService.forgetPassword(forgetPasswordDTO);
    }

    /**
     * 个人注册身份证号 校验
     * @param idCard
     * @return
     */
    @GetMapping("/check/userIdCard")
    public ResponseInfo checkUserIdCard(String idCard){
        if(userService.checkUserIdCard(idCard)){
            return ResponseUtil.error("个人身份证号重复");
        }else{
            return ResponseUtil.success();
        }
    }
}
