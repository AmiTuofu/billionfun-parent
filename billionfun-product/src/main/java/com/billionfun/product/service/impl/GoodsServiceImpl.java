package com.billionfun.product.service.impl;


import com.billionfun.common.entity.Goods;
import com.billionfun.common.entity.Product;
import com.billionfun.common.vo.GoodsVo;
import com.billionfun.dao.GoodsMapper;
import com.billionfun.dao.ProductMapper;
import com.billionfun.product.service.GoodsService;
import com.billionfun.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 11:18
 */
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Goods getGoodsById(Long id) {
        Goods goods = goodsMapper.getGoodsById(id);
        return goods;
    }

    @Override
    public boolean subtractStock(Long id, Integer count) {
        Integer record = goodsMapper.subtractStock(id,count);
        logger.info("goodsId is {},record is {}",id,record);
        return true;
    }
}