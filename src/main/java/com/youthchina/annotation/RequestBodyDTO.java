package com.youthchina.annotation;

import java.lang.annotation.*;

/**
 * Created by zhongyangwu on 3/24/19.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestBodyDTO {
    Class value();

    boolean required() default true;
}
