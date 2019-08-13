package com.xsungroup.tms.core.shiro;

import com.xsungroup.tms.core.common.BusCode;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.common.SpringBeanUtil;
import com.xsungroup.tms.core.config.properties.JwtProperties;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.IBusCode;
import com.xsungroup.tms.utils.HttpServletUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * shiro token 过滤器
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private JwtProperties jwtProperties;


    /**
     *
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws RuntimeException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(jwtProperties.getHeader());
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login( new JWTToken(token));
        // 如果没有抛出异常则代表登入成功，返回true  抛出异常后  直接写出报错信息
        return true;
    }


    /**
     * 这里我们详细说明下为什么最终返回的都是true，即允许访问
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws RuntimeException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();
        logger.info("shiro 拦截 URI = {}", uri);
        if(ObjectUtils.isEmpty(jwtProperties)){
            jwtProperties = SpringBeanUtil.getBean(JwtProperties.class);
        }
        if(ignoreUri(uri)){
            return true;
        }

        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        try {
            // 如果没有抛出异常则代表登入成功，返回true
            return executeLogin(request,response);
        } catch (AuthenticationException e) {
            HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
            IBusCode code = BusCode.TOKEN_EXPIRATION;
            if(e.getCause() instanceof BussException){
                BussException lmsException = (BussException)e.getCause();
                code = lmsException.getCode();
            }

            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            HttpServletUtil.responseWrite(httpServletResponse, ResponseUtil.result(code));
            return false;
        }

    }

    /**
     * 忽略检查的uri
     * @param uri
     * @return true 需要登录 false 不需要登陆
     */
    private boolean ignoreUri(String uri) {
        List<String> excludeList = new ArrayList<>();
        List<String> list = jwtProperties.getFilterUris();
        if(!CollectionUtils.isEmpty(list)){
            excludeList.addAll(list);
        }

        boolean loginFlag = false;
        for (String exclude : excludeList) {
            if (uri.startsWith(exclude)) {
                loginFlag = true;
                break;
            }
        }
        return loginFlag;
    }


}
