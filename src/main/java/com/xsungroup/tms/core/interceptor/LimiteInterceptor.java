package com.xsungroup.tms.core.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : Lilei
 * @Description : 幂等拦截器（2s）
 * @Date : 2019/4/24
 */
public class LimiteInterceptor implements HandlerInterceptor {

    private static String LIMITE_INTERCEPTOR = "pass:limite:";
    private static long LIMITE_TIME = 2L;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        Map<String, Object> param =  WebUtil.request2Map(request);
//        String token = request.getHeader("token");
//        token = StringUtils.isEmpty(token)?WebUtil.getIpAddr(request):token;
//        String key =LIMITE_INTERCEPTOR+request.getRequestURI()+":"+token+":"+new Gson().toJson(param);
//        if (RedisUtil.hasKey(key)){
//            JSONResult jsonResult = ResultUtil.setFailedResult("请求过于频繁，请稍后重试!");
//            WebUtil.write(new Gson().toJson(jsonResult),response);
//            return false;
//        }
//        RedisUtil.set(key,1,LIMITE_TIME);
        return true;
    }
}
