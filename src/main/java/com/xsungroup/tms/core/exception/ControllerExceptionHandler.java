package com.xsungroup.tms.core.exception;

import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.common.BusCode;
import com.xsungroup.tms.core.supper.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 控制层异常处理类
 * @author: kingJing
 * @Date: 2019/7/8 15:55
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(value = { UnauthorizedException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseInfo unauthorizedException(UnauthorizedException ex) {
        log.error("unauthorizedException");
        return ResponseUtil.result(BusCode.PERMISSION_DENIED);
    }

    @ExceptionHandler(value = { AuthorizationException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseInfo authorizationException(AuthorizationException ex) {
        log.error("authorizationException");
        return ResponseUtil.result(BusCode.PERMISSION_DENIED);
    }


    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseInfo noHandlerFoundException(NoHandlerFoundException ex) {
        return ResponseUtil.result(BusCode.NOT_FOUND);
    }

    @ExceptionHandler(value = { BussException.class })
    @ResponseStatus(HttpStatus.OK)
    public ResponseInfo resCodeException(BussException ex) {
        log.debug("业务失败", ex);
        return ResponseUtil.result(ex.getCode(),ex.getMsg());
    }


    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseInfo exception(Exception ex) {
        log.error("Exception.msg = {}", ex.getMessage(), ex);
        return ResponseUtil.error(ex.getMessage());
    }

    @ExceptionHandler(value = { ServletRequestBindingException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseInfo missingServletRequestParameterException(ServletRequestBindingException ex) {
        log.warn("ServletRequestBindingException.msg = {}", ex.getMessage());
        return ResponseUtil.result(BusCode.PARAMETER_BIND_ERROR);
    }

    @ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseInfo httpMessageConversionException(HttpMessageConversionException ex) {
        log.warn("HttpMessageConversionException.msg = {}", ex.getMessage());
        return ResponseUtil.result(BusCode.PARAMETER_BIND_ERROR);
    }



    //参数验证异常捕获
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseInfo methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.warn("methodArgumentNotValidException.msg = {}", ex.getMessage());
        //按需重新封装需要返回的错误信息
        List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
            invalidArgument.setMessage(error.getDefaultMessage());
            invalidArgument.setField(error.getField());
            invalidArgument.setValue(error.getRejectedValue());
            invalidArguments.add(invalidArgument);
        }
        return ResponseUtil.result(invalidArguments);
    }

}
