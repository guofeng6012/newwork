package com.xsungroup.tms.core.config.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

/**
 * @author 梁建军
 * 创建日期： 2018/10/22
 * 创建时间： 14:14
 * @version 1.0
 * @since 1.0
 */
public class LogHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public static final String TAG = "--" + LogHttpServletRequestWrapper.class.getSimpleName();

    private static final Logger logger = LoggerFactory.getLogger(LogHttpServletRequestWrapper.class);

    private ServletInputStream servletInputStream;

    /**
     * 读取体
     */
    private boolean readBody;

    public LogHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        readBody = LogFilter.isReadBody(request.getContentType());
        if (!readBody) {
            if (LogFilter.isParameter(request.getContentType())) {
                Map<String, String[]> objectMap = request.getParameterMap();
                StringBuilder stringBuilder = new StringBuilder();
                if (objectMap != null) {
                    objectMap.forEach((s, strings) -> stringBuilder.append(s).append("->").append(Arrays.toString(strings)).append(" "));
                }
                logger.info("request body={}", stringBuilder.toString());
            }
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream(), getContentType()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (servletInputStream == null) {
            servletInputStream = new RetainServletInputStream(super.getInputStream(), this, readBody);
        }
        return servletInputStream;
    }


    /**
     * 保留
     */
    private static class RetainServletInputStream extends ServletInputStream {

        private final ServletInputStream servletInputStream;

        private LogHttpServletRequestWrapper logHttpServletRequestWrapper;
        private boolean readBody;
        /**
         * 用于记录请求体，打印完日志就释放
         */
        private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024 * 10);

        public RetainServletInputStream(ServletInputStream servletInputStream, LogHttpServletRequestWrapper logHttpServletRequestWrapper, boolean readBody) {

            this.servletInputStream = servletInputStream;
            this.logHttpServletRequestWrapper = logHttpServletRequestWrapper;
            this.readBody = readBody;
        }

        @Override
        public boolean isFinished() {
            return servletInputStream.isFinished();
        }

        @Override
        public boolean isReady() {
            return servletInputStream.isReady();
        }

        @Override
        public void setReadListener(ReadListener listener) {
            servletInputStream.setReadListener(listener);
        }

        @Override
        public int read() throws IOException {
            int b = servletInputStream.read();
            if (readBody) {
                if (b != -1) {
                    byteArrayOutputStream.write(b);
                } else if (byteArrayOutputStream != null) {
                    logger.info("request body={}", new String(byteArrayOutputStream.toByteArray(), logHttpServletRequestWrapper.getCharacterEncoding()));
                    byteArrayOutputStream = null;
                }
            }
            return b;
        }
    }
}