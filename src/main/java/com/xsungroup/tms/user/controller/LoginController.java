package com.xsungroup.tms.user.controller;

import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.user.dto.LoginDto;
import com.xsungroup.tms.user.service.LoginService;
import com.xsungroup.tms.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("登录相关")
@RequestMapping(value = "/api/login")
public class LoginController extends SuperController {


    @Autowired
    private LoginService service;


    private static final String REDIS_LOGIN_NUMBER="pass:loginNumber:";
    private static final String REDIS_LOGIN_LIMIT="pass:loginLimit:";

    @ApiOperation(value="登陆并且生成token")
    @PostMapping("/login")
    public ResponseInfo userLogin(@RequestBody @Validated LoginDto user) {
        return  ResponseUtil.success(service.login(user));
    }

    @GetMapping("/checkSms")
    public ResponseInfo checkSmsCode(String mobile,String smsCode){
       Object sms = RedisUtil.get(mobile);
       if(null == sms || !String.valueOf(sms).equals(smsCode)){
           return ResponseUtil.error("短信验证码错误");
       }else{
           return ResponseUtil.success("验证成功");
       }
    }


    /**
     * @Description : 退出登录
     * @param : token
     * @return : boolean
     * @auther : Alex
     * @date : 2019/7/31
     */
    @RequestMapping("/logout")
    public ResponseInfo logout(@RequestHeader("token") String token){
        RedisUtil.del(token);
        return  ResponseUtil.success(true);
    }


    /**
     * 发送短信验证码
     * @param mobile
     * @return
     */
    @GetMapping("/register/{mobile}")
    public ResponseInfo register(@PathVariable("mobile") String mobile){
        return service.smsSend(mobile);
    }

    private boolean loginNumber(String userCode) {
        String loginKey = REDIS_LOGIN_LIMIT+userCode;

        String key = REDIS_LOGIN_NUMBER+userCode;
        Object o = RedisUtil.get(key);
        Integer num = o==null?0: (Integer) o;

        RedisUtil.set(key,++num,-1);
        if (num >= 6){
            RedisUtil.set(loginKey,0,20*60);
            RedisUtil.expire(key,1);
            return true;
        }
        return false;
    }


//    @ApiOperation(value="登陆")
//    @RequestMapping("/pclogin")
//    public void pcLogin(HttpServletRequest request, HttpServletResponse response) throws BadRequestException {
//        Map<String, Object> map = WebUtil.request2Map(request);
//        map.put("ip",WebUtil.getIpAddr(request));
//        map.put("hostName",request.getRemoteHost());
//        if (loginLimit((String) map.get("user_name"))) {
//            WebUtil.write(new Gson().toJson(ResultUtil.setFailedResult("密码输入错误超限制，请20分钟后重试!")),response);
//        }
//        Map<String, Object> longinMap = service.login(map);
//        longinMap.put("user",service.getById("user_detail_tms", Long.parseLong(longinMap.get("id").toString())));
//        longinMap.put("permList",service.getPermInfo(Long.parseLong(longinMap.get("id").toString())));
//        longinMap.put("roleInfo",service.getRoleInfo(Long.parseLong(longinMap.get("id").toString())));
//        longinMap.put("roleOfMenu",service.findRoleOfMenu(Long.parseLong(longinMap.get("id").toString())));
//        WebUtil.write(new Gson().toJson(ResultUtil.setJsonResult(longinMap)),response);
//    }

}
