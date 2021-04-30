package com.billionfun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 10:53
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.billionfun.dao")
@EnableDiscoveryClient
@EnableFeignClients
public class BilliionfunProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilliionfunProductApplication.class,args);
    }
}