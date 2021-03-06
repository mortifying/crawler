package com.jointsky.crawler.quartz.common.utils;

import java.util.UUID;

/**
 * Author  zhangxiong
 * Date    17-7-28 上午11:19
 */
public class IDGenerator {

    private static long num = 0;

    /**
     * 随机生成UUID
     *
     * @return
     */
    public static synchronized String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replace("-", "");
    }

    /**
     * 根据字符串生成固定UUID
     *
     * @param name
     * @return
     */
    public static synchronized String getUUID(String name) {
        UUID uuid = UUID.nameUUIDFromBytes(name.getBytes());
        String str = uuid.toString();
        return str.replace("-", "");
    }

}
