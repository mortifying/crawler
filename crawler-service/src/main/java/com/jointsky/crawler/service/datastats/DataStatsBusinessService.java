package com.jointsky.crawler.service.datastats;

import com.jointsky.crawler.entity.model.BusinessCategory;

import java.util.List;

/**
 * Author  zhangxiong
 * Date    17-8-16 上午9:59
 */
public interface DataStatsBusinessService {

    /**
     * 插入
     *
     * @param businessCategory
     */
    void insert(BusinessCategory businessCategory);

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     */
    BusinessCategory findById(String id);


    /**
     * 更新
     *
     * @param businessCategory
     */
    void update(BusinessCategory businessCategory);


    /**
     * 根据是否存在parent查找
     *
     * @param hasParent
     * @return
     */
    List<BusinessCategory> findBusinessCategoryByHasParent(String hasParent);

}
