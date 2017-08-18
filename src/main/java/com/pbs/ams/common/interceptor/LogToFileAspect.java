package com.pbs.ams.common.interceptor;

import net.sf.json.JSONArray;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by whb on 2017/6/28.
 * 本切面的切点是注解指定所以只能用@Component注入管理.
 */
@Component
@Aspect
public class LogToFileAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(com.pbs.ams.common.annotation.Log)")
    private void cut() { }

    @Before("cut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        //防止参数转换报错
        try{
            //方法名称
            String methodName = joinPoint.getSignature().getName();
            //方法参数
            String argsJsonStr = JSONArray.fromObject(joinPoint.getArgs()).toString();
            logger.info("【"+methodName+"】开始执行,参数："+argsJsonStr);
        }catch (Exception e){
            logger.warn("日志参数转换异常，不影响业务逻辑！信息："+e.getMessage());
        }
    }

    @AfterReturning(returning = "ret", pointcut = "cut()")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) throws Throwable {
        //防止参数转换报错
        try{
            //方法名称
            String methodName = joinPoint.getSignature().getName();
            String retStr = ret.toString();
            logger.info("【"+methodName+"】执行完毕,返回值："+retStr);
        }catch (Exception e){
            logger.warn("日志参数转换异常，不影响业务逻辑！信息："+e.getMessage());
        }

    }

}
