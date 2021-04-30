package com.billionfun.product.service;

import com.billionfun.common.entity.Goods;
import com.billionfun.common.entity.Product;
import com.billionfun.common.vo.GoodsVo;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 11:18
 */
public interface GoodsService {

    Goods getGoodsById(Long id);

    boolean subtractStock(Long id, Integer count);
}