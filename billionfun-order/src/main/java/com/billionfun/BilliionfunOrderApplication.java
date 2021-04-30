package com.billionfun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 10:53
 */

@MapperScan("com.billionfun.dao")
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@EnableTransactionManagement
public class BilliionfunOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilliionfunOrderApplication.class,args);
    }
}