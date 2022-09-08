package com.bm.common.swagger.ret;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author hex
 * @date 2022/3/20
 */

//@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ApiReturnObjects.class)
public @interface ApiReturnObject {
	String key() default "";
    ApiReturnProperty[] value() default {};
    String description() default "";
}
