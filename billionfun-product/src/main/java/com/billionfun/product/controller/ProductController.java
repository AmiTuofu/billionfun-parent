package com.billionfun.product.controller;

import com.alibaba.fastjson.JSON;
import com.billionfun.common.entity.Customer;
import com.billionfun.common.entity.Order;
import com.billionfun.common.entity.Product;
import com.billionfun.common.vo.CustomerVo;
import com.billionfun.common.vo.OrderVo;
import com.billionfun.common.vo.ProductVo;
import com.billionfun.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/6 12:35
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/product/{productId}")
    public ProductVo getProduct(@PathVariable("productId") Long productId){
        Product product = productService.getProductById(productId);
        ProductVo productVo = new ProductVo();

        BeanUtils.copyProperties(product,productVo);

        stringRedisTemplate.opsForValue().set("product"+productId, JSON.toJSONString(productVo));
        return productVo;
    }
}