package com.pbs.ams.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 验证参数工具类
 * Created by Administrator on 2017/7/5.
 */
public class ValidateUtil {

    /**
     *验证传入的字段的长度是否符合要求
     * @param field 要判断的字段
     * @param min 最小长度
     * @param max 最大长度
     * @return
     */
    public static boolean verifyLength(String field, int min, int max) {
        if (null == field || field.length() > max || field.length() < min) {
            return false;
        }
        return true;
    }

    /**
     * 验证结束时候是否大于开始时间
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public static boolean verifyTime(Long beginTime, Long endTime) {
        if (beginTime == null || endTime == null || beginTime > endTime) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否为纯数字
     * @param str 要判断是否为纯数字的字符串
     * @return
     */
    public static boolean isNum(String str) {
        Pattern pattern = Pattern.compile("^[0-9]+");
        if (StringUtils.isNotEmpty(str) && pattern.matcher(str).matches()) {
            return true;
        } else {
            return false;
        }
    }

}
