package com.billionfun.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/2/23 18:54
 */
@EnableDubbo
@SpringBootApplication
public class SpringBootDubboServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDubboServerApplication.class, args);
        System.out.println("服务已开启");
    }
}