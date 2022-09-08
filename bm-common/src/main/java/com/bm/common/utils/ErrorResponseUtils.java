package com.bm.common.utils;

import com.alibaba.fastjson.JSON;
import com.bm.common.exception.WebException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author hex
 * @date 2022/3/31
 */
public class ErrorResponseUtils {
    public static void handler(Logger logger, HttpServletRequest request, HttpServletResponse response, Throwable e){
        Enumeration<String> paramsEnumeration=request.getParameterNames();
        StringBuffer params=new StringBuffer();
        while (paramsEnumeration.hasMoreElements()){
            String key=paramsEnumeration.nextElement();
            params=params.append(key);
            params=params.append(":");
            params=params.append(request.getParameter(key));
            if (paramsEnumeration.hasMoreElements()){
                params=params.append("&");
            }
        }

        if ("/error".equals(request.getServletPath())){
            logger.error(request.getAttribute("path")+"|"+params);
        }else {
            logger.error(request.getRequestURL()+"|"+params);
        }
        Reply error;
        if (e instanceof WebException){
            WebException ex=(WebException)e;
            logger.error("code:"+ex.getCode()+"  msg:"+e.getMessage(),e);
            error= Reply.fail().setCode(ex.getCode()).setMsg(e.getMessage());
        }else {
            logger.error("msg:"+e.getMessage(),e);
            error= Reply.fail().setMsg("服务器错误！");
        }
        try {
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(error));
        } catch (IOException ex) {
            Logger log = LoggerFactory.getLogger(ErrorResponseUtils.class);
            log.error("---writeJson:"+ex.getMessage(),ex);
        }
    }
}
