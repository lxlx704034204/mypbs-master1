package com.pbs.ams.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TiAmo on 17/7/6.
 */
public enum ResultEnum {


    SUCCESS("1","成功"),

    FAILED("0", "失败");


    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private final String code;

    private final String message;




    // Implementing a fromString method on an enum type
    private static final Map<String, ResultEnum> stringToEnum = new HashMap<>();
    static {
        // Initialize map from constant name to enum constant
        for(ResultEnum enumObj : values()) {
            stringToEnum.put(enumObj.toString(), enumObj);
        }
    }
    // Returns GeneratorEnum for string, or null if string is invalid
    public static ResultEnum parse(String id) {
        return stringToEnum.get(id);
    }
}
