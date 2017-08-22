package com.jointsky.crawler.service.datastats.impl;

import com.jointsky.crawler.entity.mapper.RegionMapper;
import com.jointsky.crawler.entity.model.Region;
import com.jointsky.crawler.service.datastats.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author  zhangxiong
 * Date    17-8-17 下午3:35
 */
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<Region> findAll() {
        return regionMapper.selectAll();
    }
}
