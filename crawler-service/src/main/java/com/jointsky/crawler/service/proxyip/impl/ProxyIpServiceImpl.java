package com.jointsky.crawler.service.proxyip.impl;

import com.jointsky.crawler.entity.mapper.ProxyIpMapper;
import com.jointsky.crawler.entity.model.ProxyIp;
import com.jointsky.crawler.service.proxyip.ProxyIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author  zhangxiong
 * Date    17-7-27 下午5:07
 */
@Service
public class ProxyIpServiceImpl implements ProxyIpService {


    @Autowired
    private ProxyIpMapper proxyIpMapper;

    @Override
    public void insert(ProxyIp proxyIp) {
        proxyIpMapper.insertSelective(proxyIp);
    }

    @Override
    public ProxyIp findById(String id) {
        return proxyIpMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(ProxyIp proxyIp) {
        proxyIpMapper.updateByPrimaryKey(proxyIp);
    }

}
