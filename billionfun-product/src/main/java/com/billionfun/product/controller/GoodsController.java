package com.billionfun.product.controller;

import com.alibaba.fastjson.JSON;
import com.billionfun.common.entity.Goods;
import com.billionfun.common.entity.Product;
import com.billionfun.common.vo.GoodsVo;
import com.billionfun.common.vo.ProductVo;
import com.billionfun.product.service.GoodsService;
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
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/goods/{goodsId}")
    public GoodsVo getProduct(@PathVariable("goodsId") Long goodsId){
        Goods goods = goodsService.getGoodsById(goodsId);
        GoodsVo goodsVo = new GoodsVo();

        BeanUtils.copyProperties(goods,goodsVo);

        stringRedisTemplate.opsForValue().set("goods"+goodsId, JSON.toJSONString(goodsVo));
        return goodsVo;
    }

    @RequestMapping("/goods/subtract/{goodsId}")
    public GoodsVo subtractStock(@PathVariable("goodsId") Long goodsId){
        goodsService.subtractStock(goodsId,1);

        Goods goods = goodsService.getGoodsById(goodsId);
        GoodsVo goodsVo = new GoodsVo();

        BeanUtils.copyProperties(goods,goodsVo);

        return goodsVo;
    }
}