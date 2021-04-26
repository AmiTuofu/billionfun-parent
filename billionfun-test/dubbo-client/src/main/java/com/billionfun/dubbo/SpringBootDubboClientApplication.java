package com.billionfun.dubbo;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/2/23 18:54
 */
@EnableDubbo
@SpringBootApplication
public class SpringBootDubboClientApplication {
    @Reference
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDubboClientApplication.class, args).close();
    }


    @Bean
    public ApplicationRunner getBean() {
        return args -> {
            System.out.println(userService.getUser("2"));
        };
    }
}