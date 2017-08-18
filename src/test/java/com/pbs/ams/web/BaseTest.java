package com.pbs.ams.web;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置spring和junit整合
 * jar: spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class) //junit启动时加载springIOC容器
// 告诉junit 本项目的spring配置文件位置和name(以验证mybatis整合spring、db连接池是否正常):
@ContextConfiguration({
//        "classpath:SpringMVC-servlet.xml", //"classpath:applicationContext.xml",
        "classpath:applicationContext-db.xml",//"classpath:META-INF/spring/applicationContext-jdbc.xml",
        //"classpath:META-INF/spring/applicationContext-listener.xml"
})
public abstract class BaseTest {
}
