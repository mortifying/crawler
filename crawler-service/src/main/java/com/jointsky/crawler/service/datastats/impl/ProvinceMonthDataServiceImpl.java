package com.jointsky.crawler.service.datastats.impl;

import com.jointsky.crawler.entity.mapper.ProvinceMonthDataMapper;
import com.jointsky.crawler.entity.model.ProvinceMonthData;
import com.jointsky.crawler.service.datastats.ProvinceMonthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author  zhangxiong
 * Date    17-8-18 下午3:29
 */
@Service
public class ProvinceMonthDataServiceImpl implements ProvinceMonthDataService {


    @Autowired
    private ProvinceMonthDataMapper provinceMonthDataMapper;

    @Override
    public ProvinceMonthData findById(String id) {
        return provinceMonthDataMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(ProvinceMonthData provinceMonthData) {
        provinceMonthDataMapper.updateByPrimaryKey(provinceMonthData);
    }

    @Override
    public void insert(ProvinceMonthData provinceMonthData) {
        provinceMonthDataMapper.insertSelective(provinceMonthData);
    }
}
