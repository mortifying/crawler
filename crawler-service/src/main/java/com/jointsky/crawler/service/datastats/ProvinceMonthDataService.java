package com.jointsky.crawler.service.datastats;

import com.jointsky.crawler.entity.model.ProvinceMonthData;

/**
 * Author  zhangxiong
 * Date    17-8-18 下午3:28
 */
public interface ProvinceMonthDataService {

    /**
     * @param id
     * @return
     */
    ProvinceMonthData findById(String id);


    /**
     * @param provinceMonthData
     */
    void update(ProvinceMonthData provinceMonthData);

    /**
     * @param provinceMonthData
     */
    void insert(ProvinceMonthData provinceMonthData);

}
