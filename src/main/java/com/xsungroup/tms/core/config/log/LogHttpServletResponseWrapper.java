package com.xsungroup.tms.core.config.log;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @author 梁建军
 * 创建日期： 2018/10/22
 * 创建时间： 14:14
 * @version 1.0
 * @since 1.0
 */
public class LogHttpServletResponseWrapper extends HttpServletResponseWrapper {

    public static final String TAG = "--" + LogHttpServletResponseWrapper.class.getSimpleName();

    /**
     * 用于记录请求体，打印完日志就释放
     */
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);

    private ServletOutputStream servletOutputStream;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response The response to be wrapped
     * @throws IllegalArgumentException if the response is null
     */
    public LogHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public void setCharacterEncoding(String charset) {
        super.setCharacterEncoding(charset);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (servletOutputStream == null) {
            servletOutputStream = new RetainServletOutputStream(this,super.getOutputStream(), byteArrayOutputStream);
        }
        return servletOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(new OutputStreamWriter(getOutputStream(), getCharacterEncoding()), true);
    }

    public byte[] getBody() {
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 保留
     */
    private static class RetainServletOutputStream extends ServletOutputStream {

        private HttpServletResponse httpServletResponse;

        private final ServletOutputStream servletOutputStream;

        private ByteArrayOutputStream byteArrayOutputStream;

        private Boolean readBody;

        public RetainServletOutputStream(HttpServletResponse httpServletResponse, ServletOutputStream servletOutputStream, ByteArrayOutputStream byteArrayOutputStream) {
            this.httpServletResponse = httpServletResponse;

            this.servletOutputStream = servletOutputStream;


            this.byteArrayOutputStream = byteArrayOutputStream;
        }


        @Override
        public boolean isReady() {
            return servletOutputStream.isReady();
        }

        @Override
        public void setWriteListener(WriteListener listener) {
            servletOutputStream.setWriteListener(listener);
        }

        @Override
        public void write(int b) throws IOException {
            if (readBody == null) {
                readBody = LogFilter.isReadBody(httpServletResponse.getContentType());
            }
            if (readBody) {
                byteArrayOutputStream.write(b);
            }
            servletOutputStream.write(b);
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            super.write(b, off, len);
        }

        @Override
        public void write(byte[] b) throws IOException {
            super.write(b);
        }
    }
}