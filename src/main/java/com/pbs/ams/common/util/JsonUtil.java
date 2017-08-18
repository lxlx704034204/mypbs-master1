package com.pbs.ams.common.util;

import com.pbs.ams.common.base.BaseResult;
import com.pbs.ams.common.constant.Constants;
import com.pbs.ams.common.constant.StatusCode;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.UUID;

/**
 * Created by whb on 2017/6/28.
 */
public class JsonUtil {

//
//    /**
//     * 验证是否为json格式
//     * @param param
//     * @return
//     */
//    public static boolean isJson(String param){
//        boolean flag = true;
//        try {
//            JSONObject.fromObject(param);
//        } catch (Exception e) {
//            flag = false;
//        }
//        return flag;
//    }
//
//    /**
//     * 验证参数
//     * @param baseResult
//     * @param param
//     * @param params
//     * @return
//     */
//    public static JSONObject validateParam(BaseResult baseResult, String param, String ... params){
//        JSONObject json = null;
//        if(StringUtils.isBlank(param)||!isJson(param)){
//            setStatus(baseResult, StatusCode.PARAM_JSON_ERROR);
//            return json;
//        }
//        json = JSONObject.fromObject(param);
//        for (int i = 0; i < params.length; i++)
//        {
//            if(!json.has(params[i])||json.get(params[i])==null){
//                baseResult.setCode(StatusCode.PARAM_ERROR.getCode());
//                baseResult.setMessage(StatusCode.PARAM_ERROR.getMessage()+"缺少参数："+params[i]);
//                return json;
//            }
//        }
//        return json;
//    }
//
//    public static void setStatus(BaseResult baseResult,StatusCode statusCode){
//        baseResult.setCode(statusCode.getCode());
//        baseResult.setMessage(statusCode.getMessage());
//    }
//
//    public static String encryptPassword(String password, String salt) {
//        try {
//            byte[] hash = pbkdf2(new String(password.getBytes(Constants.APPLICATION_CHARACTER_ENCODING),
//                            Constants.APPLICATION_CHARACTER_ENCODING).toCharArray(),
//                    fromHex(salt),
//                    Constants.UTIL_ENCRYPT_PASSWORD_ALGORITHM_ITERATIONS,
//                    Constants.UTIL_ENCRYPT_PASSWORD_HASH_BYTE_SIZE);
//            return toHex(hash);
//        } catch (UnsupportedEncodingException e) {
//           e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static byte[] fromHex(String hex) {
//        byte[] binary = new byte[hex.length() / 2];
//        for (int i = 0; i < binary.length; i++) {
//            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
//        }
//        return binary;
//    }
//    public static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
//        try {
//            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
//            SecretKeyFactory skf = SecretKeyFactory.getInstance(Constants.UTIL_ENCRYPT_PASSWORD_ALGORITHM);
//            return skf.generateSecret(spec).getEncoded();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public static boolean slowEquals(byte[] a, byte[] b) {
//        int diff = a.length ^ b.length;
//        for (int i = 0; i < a.length && i < b.length; i++)
//            diff |= a[i] ^ b[i];
//        return diff == 0;
//    }
//
//    public static String toHex(byte[] array) {
//        BigInteger bi = new BigInteger(1, array);
//        String hex = bi.toString(16);
//        int paddingLength = (array.length * 2) - hex.length();
//        if (paddingLength > 0)
//            return String.format("%0" + paddingLength + "d", 0) + hex;
//        else
//            return hex;
//    }
//
//    /**
//     * token生成器
//     * @param code 动态获取值如cookie（大于10位）
//     * @return
//     */
//    public static String getToken(String code){
//        String random = UUID.randomUUID().toString().substring(0,10);
//        //token=cookie+随机数
//        String token = code.substring(0,10)+"_"+random;
//        return token;
//    }

}
