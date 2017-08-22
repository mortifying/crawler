package com.jointsky.crawler.service.datastats.impl;

import com.jointsky.crawler.entity.mapper.BusinessCategoryMapper;
import com.jointsky.crawler.entity.model.BusinessCategory;
import com.jointsky.crawler.service.datastats.DataStatsBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author  zhangxiong
 * Date    17-8-16 上午10:00
 */
@Service
public class DataStatsBusinessServiceImpl implements DataStatsBusinessService {

    @Autowired
    private BusinessCategoryMapper businessCategoryMapper;

    @Override
    public void insert(BusinessCategory businessCategory) {
        businessCategoryMapper.insertSelective(businessCategory);
    }

    @Override
    public BusinessCategory findById(String id) {
        return businessCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(BusinessCategory businessCategory) {
        businessCategoryMapper.updateByPrimaryKey(businessCategory);
    }

    @Override
    public List<BusinessCategory> findBusinessCategoryByHasParent(String hasParent) {
        return businessCategoryMapper.findBusinessCategoryByHasParent(hasParent);
    }
}
