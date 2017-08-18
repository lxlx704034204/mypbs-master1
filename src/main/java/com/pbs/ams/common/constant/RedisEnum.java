package com.pbs.ams.common.constant;

import com.pbs.ams.common.util.AESUtil;
import com.pbs.ams.common.util.PropertiesFileUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Redis参数枚举
 * @author TiAmo
 */
public enum RedisEnum {
    // Redis服务器IP
    IP(PropertiesFileUtil.getInstance("redis").get("master.redis.ip")),
    // Redis的端口号
    PORT(PropertiesFileUtil.getInstance("redis").getInt("master.redis.port")),
    // 访问密码
    PASSWORD(AESUtil.AESDecode(PropertiesFileUtil.getInstance("redis").get("master.redis.password"))),
    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    MAX_ACTIVE(PropertiesFileUtil.getInstance("redis").getInt("master.redis.max_active")),
    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    MAX_IDLE(PropertiesFileUtil.getInstance("redis").getInt("master.redis.max_idle")),
    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    MAX_WAIT(PropertiesFileUtil.getInstance("redis").getInt("master.redis.max_wait")),
    // 超时时间
    TIMEOUT(PropertiesFileUtil.getInstance("redis").getInt("master.redis.timeout")),
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    TEST_ON_BORROW(false),
    /**
     * redis过期时间,以秒为单位
     */
    //一小时
    EXRP_HOUR(60 * 60),
    //一天
    XRP_DAY (60 * 60 * 24),
    //一个月
    EXRP_MONTH(60 * 60 * 24 * 30);


	private final Object value;

	RedisEnum(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	// Implementing a fromString method on an enum type
    private static final Map<String, RedisEnum> stringToEnum = new HashMap<>();
    static {
        // Initialize map from constant name to enum constant
        for(RedisEnum enumObj : values()) {
            stringToEnum.put(enumObj.toString(), enumObj);
        }
    }
    // Returns GeneratorEnum for string, or null if string is invalid
    public static RedisEnum parse(String id) {
        return stringToEnum.get(id);
    }

    public String getString() {
        return  String.valueOf(this.getValue());
    }

    public Integer getInt(){
        return  Integer.valueOf(this.getString());
    }

    public boolean getBoolean(){
        return  Boolean.valueOf(this.getString());
    }

}