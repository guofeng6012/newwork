package com.xsungroup.tms.core.shiro;

import com.xsungroup.tms.core.common.AssertBuss;
import com.xsungroup.tms.core.common.BusCode;
import com.xsungroup.tms.core.common.CachePre;
import com.xsungroup.tms.user.vo.CurrentUser;
import com.xsungroup.tms.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;

@Slf4j
public class ShiroUtil {


    /**
     * 获取当前登录人信息
     * @return
     */
    public static CurrentUser currentUser(){
        //从shrio中获取存放的用户key  对应 JWTToken类
        String userId = (String) SecurityUtils.getSubject().getPrincipal();
        AssertBuss.notNull(userId, BusCode.TOKEN_INVALID);
//        String md5TOken = MD5Util.MD5Encode(token);
        CurrentUser user = RedisUtil.getObject(CachePre.LOING_SHIRO_JWT_ID + userId);
        AssertBuss.notNull(user,BusCode.TOKEN_INVALID);
        log.info("当前登录用户 : " + user.toString());
        return user;
    }

    /**
     * 获取当前登录人id
     * @return
     */
    public static String currentUserId(){
        CurrentUser user = ShiroUtil.currentUser();
        return user.getUserId();
    }
}
