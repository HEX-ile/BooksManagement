package com.bm.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

/**
 * @author hex
 * @date 2022/7/16
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 7200)
public class HttpSessionConfig {

    @Bean
    public HeaderHttpSessionIdResolver httpSessionStrategy() {
        return new HeaderHttpSessionIdResolver("Bm-Token");
    }

}
