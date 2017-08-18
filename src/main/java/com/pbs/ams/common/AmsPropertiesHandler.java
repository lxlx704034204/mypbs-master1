package com.pbs.ams.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by whb on 2017/7/4.
 */
@Configuration
@PropertySource("classpath:prod.properties")
public class AmsPropertiesHandler {

    @Value("jdbc.driver")
    public String jdbcDriver;

    @Value("${jdbc.url}")
    public  String jdbcUrl;

    @Value("${jdbc.username}")
    public  String username;

    @Value("${jdbc.password}")
    public  String password;
}
