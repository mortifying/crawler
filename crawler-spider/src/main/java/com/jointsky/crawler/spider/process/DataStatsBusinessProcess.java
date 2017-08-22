package com.jointsky.crawler.spider.process;

import com.alibaba.fastjson.JSON;
import com.jointsky.crawler.entity.model.BusinessCategory;
import com.jointsky.crawler.spider.utils.UserAgentUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Author  zhangxiong
 * Date    17-8-14 上午10:56
 */
public class DataStatsBusinessProcess implements PageProcessor {

    @Override
    public Site getSite() {
        return Site.me().setTimeOut(6000).setRetryTimes(3)
                .setSleepTime(1000).setCharset("UTF-8").addHeader("Accept-Encoding", "/")
                .setUserAgent(UserAgentUtils.radomUserAgent());
    }

    @Override
    public void process(Page page) {
        String rawText = page.getRawText();
        System.out.println("+++++++++++++" + rawText);
        List<BusinessCategory> businessCategory = JSON.parseArray(rawText, BusinessCategory.class);
        page.putField("result", businessCategory);

    }

}
