package com.bm.utils;

import com.google.common.collect.ImmutableMap;
import org.springframework.data.annotation.Immutable;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 数字静态资源定义类
 * </p>
 *
 * @author hex
 * @since 2022-08-21
 */
public class ConfigNumber {

    public static final int CHECK_NUMBER = 6;

    /**
     * 1为登录模板，2为注册模板，3为修改模板
     */
    public static final Map<Integer, String> TEMPLATE = ImmutableMap.<Integer, String>builder()
            .put(1, "1539773")
            .put(2, "1539772")
            .put(3, "1539831")
            .put(4, "1540300")
            .build();
}
