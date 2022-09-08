package com.bm.common.swagger.ret;


import com.bm.common.swagger.SwaggerEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hex
 * @date 2022/3/24
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiReturnObjects {
    SwaggerEnum select() default SwaggerEnum.None;//默认不遍历返回对象属性
    String name() default "";
    ApiReturnObject[] value();
    String description() default "";
}
