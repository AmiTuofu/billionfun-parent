package com.billionfun.product.service.impl;


import com.billionfun.common.entity.Product;
import com.billionfun.dao.ProductMapper;
import com.billionfun.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 11:18
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getProductById(Long id) {
        Product product = productMapper.getProductById(id);
        return product;
    }
}