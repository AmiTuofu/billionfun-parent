package com.billionfun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 10:53
 */
@SpringBootApplication
@MapperScan("com.billionfun.dao")
public class BilliionfunUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilliionfunUserApplication.class,args);
    }
}