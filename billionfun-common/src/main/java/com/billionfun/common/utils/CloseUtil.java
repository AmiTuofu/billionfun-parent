package com.billionfun.common.utils;

import java.io.Closeable;

/**
 * @author zhuyi
 * @since 2021/4/29 5:11 下午
 * @description 用于关闭各种连接
 */
public class CloseUtil {

    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }

    public static void close(AutoCloseable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }
}
