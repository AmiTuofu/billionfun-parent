package com.billionfun.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/1/14 11:11
 */
@SpringBootApplication
public class TestApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class,args);
    }
}