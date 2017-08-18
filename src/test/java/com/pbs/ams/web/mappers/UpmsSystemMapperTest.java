package com.pbs.ams.web.mappers;

import com.pbs.ams.web.BaseTest;
import com.pbs.ams.web.model.UpmsSystem;
import com.pbs.ams.web.model.UpmsSystemExample;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by lx on 2017/8/16.
 */
public class UpmsSystemMapperTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired//@Autowired @Resource
    UpmsSystemMapper upmsSystemMapper;

    @Test //根据条件获取公司列表
    public void selectByExampleTest() throws Exception {
        System.out.println("==========--test-1--==========: " + upmsSystemMapper); //
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        List<UpmsSystem> upmsSystems = upmsSystemMapper.selectByExample(upmsSystemExample);
        System.out.println("==========--test-5--==========: "+ upmsSystems.size()); //

//        System.out.println("==========--test-4--==========: "+ list); //
//        logger.info("--test-5--list={}", list); // 这里的"{}"是占位符 ，打印的时候 list的实际字串 会替换它
    }

}
