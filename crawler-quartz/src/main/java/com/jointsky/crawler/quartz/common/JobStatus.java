package com.jointsky.crawler.quartz.common;

/**
 * Author  zhangxiong
 * Date    17-7-28 下午1:24
 */
public interface JobStatus {
    /**
     * 正常状态
     */
    String NORMAL = "0";

    /**
     * 暂停状态
     */
    String PAUSE = "1";
    /**
     * 删除状态
     */
    String DELETE = "-1";
}
