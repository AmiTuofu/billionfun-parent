package com.billionfun.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/1/16 17:45
 */
@FeignClient(name = "feign-provider")
public interface UserApi {

    @RequestMapping("/getList")
    List getList();

    @RequestMapping("/user/{id}")
    Object findById(@PathVariable Integer id);
}