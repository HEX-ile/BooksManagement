package com.bm.common.interceptor;


import com.bm.common.annotation.Permission;
import com.bm.common.exception.WebException;
import com.bm.common.numeration.ExceptionEnum;
import com.bm.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author hex
 * @date 2022/4/13
 */

@Component
public class SysAdminLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object user = request.getSession().getAttribute("user");
        List<String> userPermissions = (List<String>)request.getSession().getAttribute("permission");
        //未登录
        if (user == null){
            throw new WebException(ExceptionEnum.ERROR_NOLOGIN);
        }
        hasAuthority(handler, userPermissions);
        return true;
    }

    private boolean hasAuthority(Object handler, List<String> userPermissions) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Permission[] permissions = method.getAnnotationsByType(Permission.class);
        if (permissions == null || permissions.length == 0) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        } else {
            boolean isPass = false;
            //顺序操作，遇到logic“与”“假”则“假”，遇到"或"“真”则“真”
            for (Permission permission : permissions) {
                if (StringUtils.isNotEmpty(permission.value())) {
                    String value = permission.value();
                    boolean hasAuth = hasAuthStr(value, userPermissions);
                    if (permission.logic()) {
                        //与操作
                        if (!hasAuth) {
                            //无权限
                            throw new WebException(ExceptionEnum.ERROR_NO_AUTH);
                        } else {
                            isPass = true;
                        }
                    } else {
                        //或操作
                        if (hasAuth) {
                            return true;
                        }
                    }
                }
            }
            if (isPass) {
                return true;
            }
            throw new WebException(ExceptionEnum.ERROR_NO_AUTH);
        }
    }

    private boolean hasAuthStr(String value, List<String> userPermissions) {
        boolean flag = false;
        for (String permission : userPermissions) {
            if (permission.equals(value)) {
                flag = true;
            }
        }
        return flag;
    }

}
