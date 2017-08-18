package com.pbs.ams.web.mappers;
import com.pbs.ams.common.util.RedisUtil;
//import com.pbs.ams.web.model.UpmsCompany;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单元测试
 * Created by shuzheng on 2017/2/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
//        "classpath:SpringMVC-servlet.xml", //"classpath:applicationContext.xml",
        "classpath:applicationContext-db.xml",//"classpath:META-INF/spring/applicationContext-jdbc.xml",
        //"classpath:META-INF/spring/applicationContext-listener.xml"
})
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//  @TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class UpmsCompanyMapperTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired//@Autowired @Resource
//    UpmsCompanyMapper upmsCompanyMapper;
//
//    @Test //根据条件获取公司列表
//    public void getRedisTest() throws Exception {
//        System.out.println("==========--test-1--==========: "+ RedisUtil.getJedis()); //
//    }
//
//
//    @Test //根据条件获取公司列表
//    public void listCompaniestest() throws Exception {
//        System.out.println("==========--test-1--==========: " + upmsCompanyMapper); //
//
//        List<Long> companyIds = new ArrayList<Long>();//用来存放公司id
//        companyIds.add(1L);
//        companyIds.add(2L);
//        Map<String, Object> params = new HashMap<String, Object>();
////        params.put("offset", offset);
////        params.put("limit", limit);
////        params.put("companyName", search);//search暂时为公司名
//        params.put("companyIds", companyIds);
//        System.out.println("==========--test-3--==========: " + params.toString()); //
////        List<UpmsCompany> list = upmsCompanyMapper.listCompanies(params);
//        UpmsCompany po = upmsCompanyMapper.selectByPrimaryKey(1L);
//
//        logger.info("--test-5--list={}", po.getCompanyName()); //
//
////        System.out.println("==========--test-4--==========: "+ list); //
////        logger.info("--test-5--list={}", list); // 这里的"{}"是占位符 ，打印的时候 list的实际字串 会替换它
//
//
////        System.out.println("==========--test-4--==========: "+ list); //
////        logger.info("--test-5--list={}", list); // 这里的"{}"是占位符 ，打印的时候 list的实际字串 会替换它
//    }




}
