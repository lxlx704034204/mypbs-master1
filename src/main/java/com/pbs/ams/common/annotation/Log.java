package com.pbs.ams.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by whb on 2017/6/28.
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface Log {
    /**
     * 描述
     * @return
     */
    String value() default "";
}
