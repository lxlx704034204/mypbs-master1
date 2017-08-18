package com.pbs.ams.common.util;

//import com.pbs.ams.web.service.impl.SysSequenceServiceImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.locks.ReentrantLock;

/**
 * id生成器
 * User : zpf
 * Date : 2017/6/19/019
 */
public class IdGeneratorUtil {

//    private static long DEFAULT_START_VALUE = 10000;// 默认的初始序列号
//    private static final  ReentrantLock lock = new ReentrantLock();
//    private static final SysSequenceServiceImpl sequenceService = (SysSequenceServiceImpl) SpringContextUtil.getBean(SysSequenceServiceImpl.class);// 获取序列表的service
//    /**
//     *根据code和指定的开始值开始获取id
//     * @param sequenCode  服务或表code
//     * @param startValue  本服务要从多少开始的序列值
//     * @return 返回key值，异常返回-1
//     */
//    public static long getKey(String sequenCode, long startValue) {
//        lock.lock();
//        long key = -1l;
//        try {
//            if (StringUtils.isNotEmpty(sequenCode)) {//参数合法
//                key = getNextValue(sequenCode, startValue);
//            }
//        } finally {
//            lock.unlock();
//        }
//        return key;
//    }
//    /**
//     *根据code获取id(默认从10000开始,可指定)
//     * @param sequenCode  服务或表code
//     * @return 返回key值，异常返回-1
//     */
//    public static long getKey(String sequenCode) {
//        lock.lock();
//        long key = -1l;
//        try {
//            if (StringUtils.isNotEmpty(sequenCode)) {//参数合法
//                key = getNextValue(sequenCode, DEFAULT_START_VALUE);
//            }
//        } finally {
//            lock.unlock();
//        }
//        return key;
//    }
//    private static long getNextValue(String sequenceCode, long startValue) {
//        long seq = -1l;
//        try {
//           seq = sequenceService.selectSequeceByCode(sequenceCode, startValue);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return seq;
//    }
}
