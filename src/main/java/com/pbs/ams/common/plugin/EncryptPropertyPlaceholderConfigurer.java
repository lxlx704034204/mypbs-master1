package com.pbs.ams.common.plugin;

import com.pbs.ams.common.util.AESUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 支持加密配置文件插件
 * Created by ams on 2017/2/4.
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private String[] propertyNames = {
		"jdbc.password", "generator.jdbc.password", "redis.password"
	};

	/**
	 * 解密指定propertyName的加密属性值
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		for (String p : propertyNames) {
			if (p.equalsIgnoreCase(propertyName)) {
				String decode = AESUtil.AESDecode(propertyValue);
				return decode;
			}
		}
		return super.convertProperty(propertyName, propertyValue);
	}

}
