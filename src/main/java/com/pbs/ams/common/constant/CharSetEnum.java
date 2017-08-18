package com.pbs.ams.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 编码枚举.
 * @author TiAmo
 */
public enum CharSetEnum {

    UTF8("UTF-8"),

    UTF16("UTF-16"),

    UTF16BE("UTF-16BE"),

    UTF16LE("UTF-16LE"),

    GBK("GBK"),

    GB18030("GB18030"),

    ISO88591("ISO-8859-1"),

    ASCII("ASCII"),

    GB2312("GB2312");

    private final String value;

    CharSetEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // Implementing a fromString method on an enum type
    private static final Map<String, CharSetEnum> stringToEnum = new HashMap<String, CharSetEnum>();

    static {
        // Initialize map from constant name to enum constant
        for (CharSetEnum enumObj : values()) {
            stringToEnum.put(enumObj.toString().toUpperCase(), enumObj);
        }
    }

    // Returns SecurityIDSourceEnum for string, or null if string is invalid
    public static CharSetEnum parse(String charSetName) {
        return stringToEnum.get(charSetName.toUpperCase());
    }

    @Override
    public String toString() {
        return value;
    }
}
