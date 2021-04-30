package com.billionfun.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/9 17:45
 */
public class OrderUtil {
    public static String getOrderIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
//         0 代表前面补充0
//         4 代表长度为4
//         d 代表参数为正数型
        return  machineId+ String.format("%015d", hashCodeV);
    }

    public static String getOrderIdByTime() {
        String newDate=DateUtil.getCurrentDate("yyyyMMddHHmmss");
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        return newDate+result;
    }
}