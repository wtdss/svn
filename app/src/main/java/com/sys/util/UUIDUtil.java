package com.sys.util;

import java.util.UUID;

public class UUIDUtil {

	/**
	 * @description:随机获取UUID值
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

}
