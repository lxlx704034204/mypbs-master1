package com.pbs.ams.web.service;
import com.pbs.ams.common.util.RedisUtil;
//import com.pbs.ams.web.mappers.UpmsCompanyMapper;
//import com.pbs.ams.web.model.UpmsCompany;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  单元测试
 Created by shuzheng on 2017/2/19.
 必须配置一下两个文件：
 <!-- 启动时初始化Spring上下文环境工具类 -->
 <bean id="springContextUtil" class="com.pbs.ams.common.util.SpringContextUtil"></bean>
 <!-- Spring容器初始化完成监听器 -->
 <bean class="com.pbs.ams.common.listener.ApplicationContextListener" lazy-init="false"></bean>

 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
//        "classpath:SpringMVC-servlet.xml", //"classpath:applicationContext.xml",
        "classpath:applicationContext-db.xml",//"classpath:META-INF/spring/applicationContext-jdbc.xml",
        //"classpath:META-INF/spring/applicationContext-listener.xml"
})
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
  @TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class UpmsCompanyServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired//@Autowired @Resource
//    UpmsCompanyService upmsCompanyService;
//
//    @Test //根据条件获取公司列表
//    public void listCompaniestest() throws Exception {
//        System.out.println("==========--test-1--==========: "+ upmsCompanyService); //
//
//        List<Long> companyIds = new ArrayList<Long>();//用来存放公司id
//        companyIds.add(1L);companyIds.add(2L);
//        Map<String, Object> params = new HashMap<String, Object>();
////        params.put("offset", offset);
////        params.put("limit", limit);
////        params.put("companyName", search);//search暂时为公司名
//        params.put("companyIds", companyIds);
//        System.out.println("==========--test-3--==========: "+ params.toString()); //
////        List<UpmsCompany> list = upmsCompanyService.listCompanies(params);
//        UpmsCompany po = upmsCompanyService.selectByPrimaryKey(1L);
//
//        System.out.println("==========--test-5--==========: "+ po.getCompanyName() ); //
//
////        System.out.println("==========--test-4--==========: "+ list); //
////        logger.info("--test-5--list={}", list); // 这里的"{}"是占位符 ，打印的时候 list的实际字串 会替换它
//    }



}
/*

需要在"classpath:applicationContext-db.xml"中添加：

<!-- 启动注解驱动的Spring MVC功能，注册请求url和注解POJO类方法的映射-->
    <mvc:annotation-driven/>

    <mvc:default-servlet-handler/>

    <!-- 启动包扫描功能，以便注册带有@Controller、@service、@repository、@Component等注解的类成为spring的bean  先注释掉 否则无法uniTest_1-->
    <context:component-scan base-package="com.pbs.ams.web.service" />

    <!-- transaction support-->
    <!-- PlatformTransactionMnager -->
    <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource" />
    </bean>

    <!-- enable transaction annotation support -->
    <tx:annotation-driven transaction-manager="txManager" />

    <tx:advice id="transactionAdvice" transaction-manager="txManager">
        <tx:attributes>
            <!-- 让所有的方法都加入事务管理 -->
            <tx:method name="*" propagation="REQUIRED" read-only="true" />
            <!--涉及修改,无法只读-->
            <tx:method name="delete*" propagation="REQUIRED" />
            <tx:method name="insert*" propagation="REQUIRED" />
            <tx:method name="update*" propagation="REQUIRED" />
            <!--查询设为只读提高效率-->
            <tx:method name="find*" read-only="true" />
            <tx:method name="get*" read-only="true" />
            <tx:method name="select*" read-only="true" />
        </tx:attributes>
    </tx:advice>



*/