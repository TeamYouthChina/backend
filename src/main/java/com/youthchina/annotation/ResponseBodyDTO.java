package com.youthchina.annotation;

import java.lang.annotation.*;

/**
 * Created by zhongyangwu on 3/21/19.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseBodyDTO {
    Class value();
}
