package com.xsungroup.tms.core.shiro;

import com.google.common.base.Strings;
import com.xsungroup.tms.core.common.BusCode;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.user.entity.PermEntity;
import com.xsungroup.tms.user.vo.CurrentUser;
import com.xsungroup.tms.utils.MD5Util;
import com.xsungroup.tms.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class MyRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);


    @Autowired
    private JWTUtil jwtUtil;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String token = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (!Strings.isNullOrEmpty(token) && RedisUtil.hasKey(token)) {
            String md5TOken = MD5Util.MD5Encode(token);
            CurrentUser currentUser = RedisUtil.getObject(md5TOken);
            List<PermEntity> routers = currentUser.getMenus();
            List<PermEntity> apis = currentUser.getOpts();
            apis.addAll(routers);
            if (!CollectionUtils.isEmpty(apis)) {
                List<String> perms = apis.stream().map(PermEntity::getPermissionCode).collect(Collectors.toList());
                simpleAuthorizationInfo.addStringPermissions(perms);
            }
        }
        return simpleAuthorizationInfo;
    }


    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws BussException, AuthenticationException {
        String token = (String) auth.getCredentials();
        if (Strings.isNullOrEmpty(token)) {
            logger.error("token丢失");
            throw new BussException(BusCode.TOKEN_LOSE);
        }
        try {
            Claims claims = jwtUtil.getClaimFromToken(token);
//            String hostName = HttpServletUtil.getRequest().getRemoteHost();
//            String ip = HttpServletUtil.getIpAddress();
//
//            String cip = String.valueOf(claims.get("ip"));
//            String chostName = String.valueOf(claims.get("hostName"));
//
//            if(Objects.equals(ip,cip) && Objects.equals(hostName,chostName)){
//                logger.error("token无效: 签发IP和hostName 不对！ IP{}| HOST{}", cip,chostName);
//                throw new BussException(BusCode.TOKEN_INVALID);
//            }
            String userId = claims.getSubject();
            logger.info("userId : {}" , userId);
            return new SimpleAuthenticationInfo(userId, token, getName());
        } catch (ExpiredJwtException e) {
            logger.error("token过期:token:{}", token);
            throw new BussException(BusCode.TOKEN_EXPIRATION);
        } catch (JwtException e) {
            logger.error("token无效:token:{}", token);
            throw new BussException(BusCode.TOKEN_INVALID);
        }
    }
}
