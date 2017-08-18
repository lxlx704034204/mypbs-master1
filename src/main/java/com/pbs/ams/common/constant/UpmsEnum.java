package com.pbs.ams.common.constant;

import com.pbs.ams.common.util.PropertiesFileUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统级常量枚举 for UpmsSessionDao
 * @author TiAmo
 */
public enum UpmsEnum {

    AMS_UPMS_SHIRO_SESSION_ID ("pbs-ams-management-shiro-session-id"),      // 会话key                       for:UpmsSessionDao
    AMS_UPMS_SERVER_SESSION_ID ("pbs-ams-management-server-session-id"),    // 全局会话key                   for:UpmsSessionDao/pbs-ams-management-client.properties
    AMS_UPMS_SERVER_SESSION_IDS ("pbs-ams-management-server-session-ids"),  // 全局会话列表key               for:UpmsSessionDao
    AMS_UPMS_SERVER_CODE ("pbs-ams-management-server-code"),                // code key                     for:UpmsSessionDao
    AMS_UPMS_CLIENT_SESSION_ID("pbs-ams-management-client-session-id"),     // 局部会话key                  for:UpmsSessionDao/UpmsAuthenticationFilter
    AMS_UPMS_CLIENT_SESSION_IDS ("pbs-ams-management-client-session-ids"),  // 单点同一个code所有局部会话key for:UpmsSessionDao/UpmsAuthenticationFilter

    FORCE_LOGOUT("FORCE_LOGOUT"),//退出标识

    UPMSTYPE(PropertiesFileUtil.getInstance("pbs-ams-management-client").get("upms.type")),//访问类型

    SSO_SERVER_URL(PropertiesFileUtil.getInstance("pbs-ams-management-client").get("sso.server.url")),

    APPID(PropertiesFileUtil.getInstance("pbs-ams-management-client").get("appID"));

	private final Object value;

	UpmsEnum(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	// Implementing a fromString method on an enum type
    private static final Map<String, UpmsEnum> stringToEnum = new HashMap<>();
    static {
        // Initialize map from constant name to enum constant
        for(UpmsEnum enumObj : values()) {
            stringToEnum.put(enumObj.toString(), enumObj);
        }
    }
    // Returns GeneratorEnum for string, or null if string is invalid
    public static UpmsEnum parse(String id) {
        return stringToEnum.get(id);
    }

    public String getString() {
        return  String.valueOf(this.getValue());
    }

    public StringBuilder getStringBuilder() {
        return  new StringBuilder(this.getString());
    }
}