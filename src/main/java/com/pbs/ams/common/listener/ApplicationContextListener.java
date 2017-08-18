package com.pbs.ams.common.listener;

import com.pbs.ams.common.annotation.BaseService;
import com.pbs.ams.common.base.BaseInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * spring容器初始化完成事件  给BaseServiceImpl.java中的 public Mapper mapper 注入实例对象;
 * Created by shuzheng on 2017/1/7.
 */
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger _log = LoggerFactory.getLogger(ApplicationContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("----test-0--ApplicationContextListener--onApplicationEvent(...)--- " ); //
        // root application context
        if(null == contextRefreshedEvent.getApplicationContext().getParent()) {
            System.out.println("----test-1--ApplicationContextListener-->>>>> spring初始化完毕 <<<<< " ); //
            _log.debug(">>>>> spring初始化完毕 <<<<<");
            // spring初始化完毕后，通过反射调用所有使用BaseService注解的initMapper方法
            Map<String, Object> baseServices = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(BaseService.class);
            for(Object service : baseServices.values()) {
                System.out.println("----test-2--ApplicationContextListener--service.getClass().getName()--: "+service.getClass().getName() ); //
                _log.debug(">>>>> {}.initMapper()", service.getClass().getName());
                try {
                    System.out.println("----test-3--ApplicationContextListener--try()-- " ); //
                    Method initMapper = service.getClass().getMethod("initMapper");
                    System.out.println("----test-4--ApplicationContextListener--try()--: "+initMapper ); //
                    initMapper.invoke(service);
                } catch (Exception e) {
                    System.out.println("----test-5--ApplicationContextListener--e.toString()--: "+ e.toString() ); //
                    _log.error("初始化BaseService的initMapper方法异常", e);
                    e.printStackTrace();
                }
            }

            // 系统入口初始化
            Map<String, BaseInterface> baseInterfaceBeans = contextRefreshedEvent.getApplicationContext().getBeansOfType(BaseInterface.class);
            for(Object service : baseInterfaceBeans.values()) {
                _log.debug(">>>>> {}.init()", service.getClass().getName());
                try {
                    Method init = service.getClass().getMethod("init");
                    init.invoke(service);
                } catch (Exception e) {
                    _log.error("初始化BaseInterface的init方法异常", e);
                    e.printStackTrace();
                }
            }

        }
    }

}
