package com.billionfun.common.exception;

import org.springframework.util.StringUtils;

/**
 * @author zhuyi
 * @since 2021/4/29 5:00 下午
 */
public class EntityExistException extends RuntimeException {

    public EntityExistException(Class clazz, String field, String val) {
        super(EntityExistException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " existed";
    }
}
