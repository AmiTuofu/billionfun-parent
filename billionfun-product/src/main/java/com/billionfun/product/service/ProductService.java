package com.billionfun.product.service;

import com.billionfun.common.entity.Order;
import com.billionfun.common.entity.Product;

import java.util.List;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 11:18
 */
public interface ProductService {

    Product getProductById(Long id);
}