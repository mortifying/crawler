package com.jointsky.crawler.quartz.common.utils;

import org.springframework.context.ApplicationContext;

/**
 * Author  zhangxiong
 * Date    17-7-27 下午4:47
 */
public class SpringContextUtil {
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static Object getBean(String beanId) {
        return applicationContext.getBean(beanId);
    }

    public static <E> E getBean(Class<E> clazz) {
        return applicationContext.getBean(clazz);
    }

}
