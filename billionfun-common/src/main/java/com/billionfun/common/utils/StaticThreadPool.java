package com.billionfun.common.utils;

import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/5/4 15:12
 */
public class StaticThreadPool {
    private static int corePoolSize;// 池中所保存的线程数，包括空闲线程
    private static int maximumPoolSize;// 池中允许的最大线程数
    private static int keepAliveTime;// 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
    private static int workQueue; // 执行前用于保持任务的队列。此队列仅保持由 execute 方法提交的 Runnable
    // 任务。
    private static ThreadPoolExecutor executor = null;


    private static String resource = "activeMQStaticThreadPool";
    private static ResourceBundle rb = null;


    public static ThreadPoolExecutor threadPool() {
        if(executor!=null){
            return executor;
        }
        if (rb == null) {
            corePoolSize = 10;
            maximumPoolSize = 100;
            keepAliveTime = 60000;
            workQueue = 10;
            //          rb = ResourceBundle.getBundle(resource);
//            if (rb != null) {
//                corePoolSize = 10;
//                maximumPoolSize = 50;
//                keepAliveTime = 60000;
//                workQueue = 10;
//            } else {
//                System.out.println("thread pool property initialized failed!");
//            }
        }
        if (executor == null) {
            System.out.println(" Initialize StaticThreadPool Executor");
            executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(workQueue), new ThreadPoolExecutor.AbortPolicy());
        }
        return executor;
    }
}