package com.billionfun.feignapi.productcenter;

import com.billionfun.common.entity.Order;
import com.billionfun.common.entity.Product;
import com.billionfun.common.vo.GoodsVo;
import com.billionfun.common.vo.ProductVo;
import com.billionfun.config.OrderCenterFeignConfig;
import com.billionfun.config.ProductCenterFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 19:53
 */
@FeignClient(name = "product-center",configuration = ProductCenterFeignConfig.class)
public interface ProductCenterFeignApi {
    @RequestMapping("/product/{productId}")
    ProductVo getProductById(@PathVariable("productId") Long productId);

    @RequestMapping("/goods/{goodsId}")
    GoodsVo getGoodsById(@PathVariable("goodsId") Long goodsId);

    @RequestMapping("/goods/subtract/{goodsId}")
    GoodsVo subtractStock(@PathVariable("goodsId") Long goodsId);
}