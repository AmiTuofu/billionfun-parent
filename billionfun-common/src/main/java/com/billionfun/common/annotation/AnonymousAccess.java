package com.billionfun.common.annotation;

import java.lang.annotation.*;

/**
 * @author zhuyi
 * @since 2021/4/29 11:10 上午
 */
@Inherited
@Documented
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {
}
