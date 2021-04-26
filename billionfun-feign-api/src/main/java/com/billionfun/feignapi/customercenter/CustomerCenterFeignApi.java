package com.billionfun.feignapi.customercenter;

import com.billionfun.common.entity.Customer;
import com.billionfun.common.vo.CustomerVo;
import com.billionfun.config.CustomerCenterFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 19:53
 */
@FeignClient(name = "customer-center",configuration = CustomerCenterFeignConfig.class)
public interface CustomerCenterFeignApi {
    @RequestMapping("/customer/{customerId}")
    CustomerVo getCustomerById(@PathVariable("customerId") Long customerId);
}