package com.bm.common.annotation;

import java.lang.annotation.*;

/**
 * @author hex
 * @create 2022-05-24
 */
@Repeatable(Permissions.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    String value() default "";

    /**
     * false 或操作, true 与操作
     *
     * @return
     */
    boolean logic() default false;

}
