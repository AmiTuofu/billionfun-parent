package com.billionfun;

import com.billionfun.common.utils.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 10:53
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@MapperScan("com.billionfun.dao")
@EnableDiscoveryClient
@EnableFeignClients
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
@RestController
public class BilliionfunWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilliionfunWebApplication.class,args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}