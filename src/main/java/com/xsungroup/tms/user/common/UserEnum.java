package com.xsungroup.tms.user.common;

import com.xsungroup.tms.core.supper.IBusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface UserEnum{

    //token 缓存到 redis 中的时长
    int TOKEN_REDIS_TIME = 60 * 60 * 4;

    /**
     * 菜单权限
     */
    String PERM_TYPE_MENU = "1";
    /**
     * 按钮（操作权限）
     */
    String PERM_TYPE_OPT = "0";

    String PERM_TYPE_MENUOPT = "2";


    @Getter
    @AllArgsConstructor
    enum Code implements IBusCode {

        LOGIN_ACCOUNT_ERROR(1, "账号或密码错误"),
        LOGIN_CAPTHA_ERROR(2, "验证码错误"),
        LOGIN_USER_DISABLED(3, "账号被禁用"),
        LOGIN_USER_PWD_LIMIT(4, "密码输入错误超限制，请20分钟后重试！"),
        LOGIN_USER_TEL_ERROR(5, "用户手机号码不合法"),
        SEED_MESSAGE_ERROR(6, "短信发送失败"),
        LOGIN_MESSAGE_CAPTHA_ERROR(7, "短信验证码错误");

        private final int code;

        private final String msg;
    }


    enum Constant {

    }


}


