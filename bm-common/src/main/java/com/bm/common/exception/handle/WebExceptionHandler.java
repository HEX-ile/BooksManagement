package com.bm.common.exception.handle;

import com.bm.common.exception.WebException;
import com.bm.common.numeration.ExceptionEnum;
import com.bm.common.utils.ErrorResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@ControllerAdvice
public class WebExceptionHandler {
    Logger log = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ExceptionHandler(WebException.class)
    public void webExceptionHandler(HttpServletRequest request, HttpServletResponse response, WebException e) {
        ErrorResponseUtils.handler(log,request,response,e);
    }

    /**
     * 处理实体字段校验不通过异常
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public void validationError(HttpServletRequest request, HttpServletResponse response,BindException e) {
        BindingResult result = e.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();
        for (FieldError error : fieldErrors) {
            builder.append( error.getField()+":"+error.getDefaultMessage()+",");
        }
        ErrorResponseUtils.handler(log,request,response,new WebException(ExceptionEnum.ERROR_EMPTY_PARAM.getCode(),builder.toString()));
    }

    @ExceptionHandler(Exception.class)
    public void excetionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        if (e instanceof MissingServletRequestParameterException){
            ErrorResponseUtils.handler(log,request,response,new WebException(ExceptionEnum.ERROR_EMPTY_PARAM));
        }else {
            ErrorResponseUtils.handler(log,request,response,e);
        }

    }

}
