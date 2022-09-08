package com.bm.common.swagger.ret;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hex
 * @date 2022/3/20
 */

@Target({ElementType.METHOD,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiReturnProperty {

    String key()  default "";//key

    String example() default "";

    Class<?> dataType() default String.class;

    String description() default "";

}
