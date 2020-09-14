package com.baizhi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassNmae: AddLog
 * @Author: yddm
 * @DateTime: 2020/8/31 20:40
 * @Description: TODO
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AddLog {
    String value();

    String name() default "";
}
