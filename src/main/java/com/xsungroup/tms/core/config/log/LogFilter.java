package com.xsungroup.tms.core.config.log;


import com.alibaba.druid.util.PatternMatcher;
import com.alibaba.druid.util.ServletPathMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author 梁建军
 * @version 1.0
 * @since 1.0
 */
public class LogFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LogFilter.class);

    private Set<String> excludesPattern;
    protected PatternMatcher pathMatcher = new ServletPathMatcher();
    private final static String PARAM_NAME_EXCLUSIONS = "exclusions"; //判断是否js，图片

    @Override
    public void init(FilterConfig config) throws ServletException {

        String exclusions = config.getInitParameter(PARAM_NAME_EXCLUSIONS);
        if (exclusions != null && exclusions.trim().length() != 0) {
            excludesPattern = new HashSet<String>(Arrays.asList(exclusions.split("\\s*,\\s*")));
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {




        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        String contextPath = httpRequest.getContextPath();

        if (isExclusion(requestURI,contextPath)) {
            chain.doFilter(request, response);
            return;
        }



        Enumeration<String> stringEnumeration = httpRequest.getHeaderNames();
        Map<String, Object> map = new HashMap<>();
        map.put("url", httpRequest.getRequestURL());
        map.put("uri", httpRequest.getRequestURI());
        map.put("method", httpRequest.getMethod());
        map.put("ip", httpRequest.getRemoteAddr());
        map.put("header", httpRequest.getRequestURL());
        if (stringEnumeration != null) {
            while (stringEnumeration.hasMoreElements()) {
                String name = stringEnumeration.nextElement();
                map.put(name, httpRequest.getHeader(name));
            }
        }
        logger.info("request {} {} {}", httpRequest.getMethod(), httpRequest.getRequestURL(), map);

        LogHttpServletRequestWrapper logHttpServletRequestWrapper = new LogHttpServletRequestWrapper(httpRequest);

        LogHttpServletResponseWrapper logHttpServletResponseWrapper = new LogHttpServletResponseWrapper(httpResponse);

        chain.doFilter(logHttpServletRequestWrapper, logHttpServletResponseWrapper);

        map = new HashMap<>();
        Collection<String> headerNames = httpResponse.getHeaderNames();
        StringBuilder stringBuilder = new StringBuilder();
        if (headerNames != null) {
            for (String headerName : headerNames) {
                String headerValue = httpResponse.getHeader(headerName);
                stringBuilder.append(headerName).append("=").append(headerValue).append(" ");
                map.put(headerName, headerValue);
            }
        }
        map.put("status", httpResponse.getStatus());
        logger.info("response status={} body={} header={}", httpResponse.getStatus(), new String(logHttpServletResponseWrapper.getBody(), logHttpServletResponseWrapper.getCharacterEncoding()), stringBuilder.toString());
    }

    @Override
    public void destroy() {

    }


    /**
     * 可读
     */
    private static final Set<String> READABLE_CONTENT_TYPE = new HashSet<>();

    static {
        READABLE_CONTENT_TYPE.add("xml");
        READABLE_CONTENT_TYPE.add("json");
        READABLE_CONTENT_TYPE.add("markdown");
        READABLE_CONTENT_TYPE.add("plain");
    }

    /**
     * @param contentType 内容类型
     * @return
     */
    public static boolean isReadBody(String contentType) {
        if (contentType == null) {
            return false;
        }

        MediaType mediaType = MediaType.parseMediaType(contentType);

        String subtype = mediaType.getSubtype();
        String[] split = subtype.split("\\+");
        return READABLE_CONTENT_TYPE.contains(split[split.length - 1]);
    }

    private static final Set<String> PARAMETER = new HashSet<>();

    static {
        PARAMETER.add("application/x-www-form-urlencoded");
        PARAMETER.add("application/form-data");
        PARAMETER.add("multipart/form-data");
    }

    public static boolean isParameter(String contentType) {
        if (contentType == null) {
            return false;
        }

        contentType = contentType.split(";")[0];

        return PARAMETER.contains(contentType);
    }

    public boolean isExclusion(String requestURI,String contextPath) {
        if (excludesPattern == null || requestURI == null) {
            return false;
        }

        if (contextPath != null && requestURI.startsWith(contextPath)) {
            requestURI = requestURI.substring(contextPath.length());
            if (!requestURI.startsWith("/")) {
                requestURI = "/" + requestURI;
            }
        }

        for (String pattern : excludesPattern) {
            if (pathMatcher.matches(pattern, requestURI)) {
                return true;
            }
        }

        return false;
    }


}
