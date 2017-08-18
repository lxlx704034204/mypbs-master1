package com.pbs.ams.common.constant;

/**
 * Created by whb on 2017/6/28.
 * 状态码枚举类.
 */
public enum StatusCode {

    ERROR_NONE("1", "没有异常"),
    //1开头（系统级异常）
    SYSTEM_ERROR("A10000","系统内部异常"),
    PARAM_ERROR("A10001","参数异常"),
    PARAM_JSON_ERROR("A10002","参数非JSON格式"),
    FILE_WRITE_ERROR("A10003","文件写入异常"),

    //2开头（外部资源使用异常）
    SQL_ERROR("E21000","sql异常"),
    SQL_LINK_ERROR("E21001","数据库链接异常"),
    REDIS_ERROR("E21002","redis异常"),
    REDIS_LINK_ERROR("E21003","redis链接异常"),

    //3开头 (业务级异常)
    PWD_COUNT_ERROR("S30001","您的密码已连续5次输入错误,请24小时后重试"),
    INSTITUTION_STOPPED("S30002","机构已被停用"),
    ACCOUNT_LOCKED("S30003","账号被锁定"),
    USER_LOGIN_OUT_TIME("S30004","已离线！请重新登录"),
    INVALID_LENGTH("S30005", "字数超过限制"),
    EMPTY_USERNAME("S30006", "用户名不能为空"),
    EMPTY_PASSWORD("S30007", "密码不能为空"),
    INVALID_USERNAME("S3008", "账号不存在"),
    INVALID_PASSWORD("S3009", "密码错误"),
    INVALID_ACCOUNT("S30010", "账号无效"),
    INVALID_CODE("S30011", "无效code"),
    INVALID_INSERT("S30012", "插入数据异常"),
    INVALID_DELETE("S30013", "删除数据异常"),
    REPETE_USERNAME("S30014", "用户名重复"),
    FAILD_DELETE("S30015", "存在关联关系,不能删除!"),
    INVALID_PARAMETER("S30016", "参数不合法"),
    PWD_RESET_FAILD("S30017", "重置密码失败"),
    ILLEGAL_DATE("S30018", "输入的时间非法");

    private final String code;

    private final String message;

    StatusCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
