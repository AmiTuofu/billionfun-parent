package com.billionfun.customer.controller;

import com.billionfun.common.entity.Customer;
import com.billionfun.common.vo.CustomerVo;
import com.billionfun.customer.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 11:18
 */
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/getAll")
    public List<Customer> getAll(){
        List list = customerService.getAll();
        return list;
    }

    @RequestMapping("/customer/{customerId}")
    public CustomerVo getCustomerById(@PathVariable("customerId") Long customerId){
        CustomerVo customerVo = new CustomerVo();
        Customer customer = customerService.getCustomerById(customerId);
        BeanUtils.copyProperties(customer,customerVo);
        return customerVo;
    }

    @GetMapping("/getServiceList")
    public List<ServiceInstance> getServiceList() {
        List<ServiceInstance> serviceInstanceList =  discoveryClient.getInstances("order-center");
        return serviceInstanceList;
    }
}