package com.jointsky.crawler.spider.pipeline;

import com.jointsky.crawler.entity.model.BusinessCategory;
import com.jointsky.crawler.entity.model.ProvinceMonthData;
import com.jointsky.crawler.quartz.common.utils.SpringContextUtil;
import com.jointsky.crawler.service.datastats.DataStatsBusinessService;
import com.jointsky.crawler.service.datastats.ProvinceMonthDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.List;

/**
 * Author  zhangxiong
 * Date    17-8-17 下午3:57
 */
public class ProvinceMonthDataPipeline implements Pipeline {

    private static Logger logger = LoggerFactory.getLogger(DataStatsBusinessPipeline.class);

    DataStatsBusinessService dataStatsBusinessService = (DataStatsBusinessService) SpringContextUtil.getBean(DataStatsBusinessService.class);

    ProvinceMonthDataService provinceMonthDataService = (ProvinceMonthDataService) SpringContextUtil.getBean(ProvinceMonthDataService.class);

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<BusinessCategory> businessCategory = resultItems.get("bc_result");
        for (BusinessCategory bc : businessCategory) {
            bc.setIsParent("0");
            BusinessCategory bcategory = dataStatsBusinessService.findById(bc.getId());
            if (null != bcategory) {
                bc.setUpdateTime(new Date());
                bc.setAddTime(bcategory.getAddTime());
                dataStatsBusinessService.update(bc);
                logger.info("update data stats business category......");
            } else {
                bc.setAddTime(new Date());
                bc.setUpdateTime(new Date());
                dataStatsBusinessService.insert(bc);
                logger.info("insert data stats business category......");
            }
        }


        List<ProvinceMonthData> provinceMonthDataList = resultItems.get("pmd_result");
        for (ProvinceMonthData pmd : provinceMonthDataList) {
            ProvinceMonthData pMonthData = provinceMonthDataService.findById(pmd.getId());
            if (null != pMonthData) {
                pmd.setUpdateTime(new Date());
                pmd.setAddTime(pMonthData.getAddTime());
                provinceMonthDataService.update(pmd);
                logger.info("update province month data......");
            } else {
                pmd.setAddTime(new Date());
                pmd.setUpdateTime(new Date());
                provinceMonthDataService.insert(pmd);
                logger.info("insert province month data......");
            }
        }


    }
}
