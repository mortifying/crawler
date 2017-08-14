package com.jointsky.crawler.quartz.model;

import org.quartz.JobExecutionContext;

/**
 * Author  zhangxiong
 * Date    17-7-26 下午3:43
 */
public interface IExecutable {
    /**
     * 执行某个操作
     *
     * @param context
     * @return
     */
    boolean execute(JobExecutionContext context) throws IllegalAccessException, InstantiationException;
}
