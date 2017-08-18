package com.pbs.ams.common.annotation;

import java.lang.annotation.*;

/**
 * 初始化继承BaseService的service
 * Created by ams on 2017/2/11.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseService {
}
