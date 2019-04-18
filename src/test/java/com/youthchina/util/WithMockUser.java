package com.youthchina.util;

import java.lang.annotation.*;

/**
 * Created by zhongyangwu on 4/17/19.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WithMockUser {
    int value() default 1;
}
