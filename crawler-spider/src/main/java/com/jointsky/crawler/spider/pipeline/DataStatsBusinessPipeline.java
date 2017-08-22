package com.jointsky.crawler.spider.pipeline;


import com.jointsky.crawler.entity.model.BusinessCategory;
import com.jointsky.crawler.quartz.common.utils.IDGenerator;
import com.jointsky.crawler.quartz.common.utils.SpringContextUtil;
import com.jointsky.crawler.service.datastats.DataStatsBusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.List;

/**
 * Author  zhangxiong
 * Date    17-8-14 下午4:38
 */
public class DataStatsBusinessPipeline implements Pipeline {

    private static Logger logger = LoggerFactory.getLogger(DataStatsBusinessPipeline.class);

    DataStatsBusinessService dataStatsBusinessService = (DataStatsBusinessService) SpringContextUtil.getBean(DataStatsBusinessService.class);

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<BusinessCategory> businessCategory = resultItems.get("result");
        for (BusinessCategory bc : businessCategory) {
            bc.setIsParent(bc.getIsParent().equals("true") ? "1" : "0");
            BusinessCategory bcategory = dataStatsBusinessService.findById(IDGenerator.getUUID(bc.getDbcode() + bc.getId()));
            if (null != bcategory) {
                bc.setUpdateTime(new Date());
                bc.setAddTime(bcategory.getAddTime());
                dataStatsBusinessService.update(bc);
                logger.info("update data stats business category......");
            } else {
                bc.setAddTime(new Date());
                bc.setUpdateTime(new Date());
                bc.setBid(bc.getId());
                bc.setId(IDGenerator.getUUID(bc.getDbcode() + bc.getId()));
                dataStatsBusinessService.insert(bc);
                logger.info("insert data stats business category......");
            }
        }
    }
}
