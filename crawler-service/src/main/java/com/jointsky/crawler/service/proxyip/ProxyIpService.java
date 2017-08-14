package com.jointsky.crawler.service.proxyip;

import com.jointsky.crawler.entity.model.ProxyIp;

/**
 * Author  zhangxiong
 * Date    17-7-27 下午5:07
 */
public interface ProxyIpService {

    /**
     * 插入
     *
     * @param proxyIp
     */
    void insert(ProxyIp proxyIp);

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     */
    ProxyIp findById(String id);


    /**
     * 更新
     *
     * @param proxyIp
     */
    void update(ProxyIp proxyIp);

}
