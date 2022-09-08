package com.bm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author hex
 * @since 2022-08-21
 */
@SpringBootApplication
@Controller
@EnableCaching
@EnableScheduling
public class BmMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(BmMainApplication.class, args);
    }

}
