package com.jointsky.crawler.spider.process;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jointsky.crawler.entity.model.ProxyIp;
import com.jointsky.crawler.quartz.common.utils.IDGenerator;
import com.jointsky.crawler.spider.utils.UserAgentUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * Author  zhangxiong
 * Date    17-7-26 下午3:47
 */
public class ProxyIPSpider implements PageProcessor {

    @Override
    public Site getSite() {
        return Site.me().setTimeOut(6000).setRetryTimes(3)
                .setSleepTime(1000).setCharset("UTF-8").addHeader("Accept-Encoding", "/")
                .setUserAgent(UserAgentUtils.radomUserAgent());
    }

    @Override
    public void process(Page page) {
        List<String> ipList = page.getHtml().xpath("//table[@id='ip_list']/tbody/tr").all();
        List<ProxyIp> result = new ArrayList<>();

        if (ipList != null && ipList.size() > 0) {
            ipList.remove(0);  //移除表头
            for (String tmp : ipList) {
                Html html = Html.create(tmp);
                ProxyIp proxyIp = new ProxyIp();
                String[] data = html.xpath("//body/text()").toString().trim().split("\\s+");

                proxyIp.setIp(data[0]);
                proxyIp.setPort(Integer.valueOf(data[1]));
                proxyIp.setId(IDGenerator.getUUID(data[0] + data[1]));

                proxyIp.setAddress(html.xpath("//a/text()").toString());
                proxyIp.setType(data[3]);

                proxyIp.setAddTime(new Date());
                proxyIp.setUpdateTime(new Date());
                result.add(proxyIp);
            }
        }
        page.putField("result", result);
        page.addTargetRequest("http://www.xicidaili.com/nn/2");
        page.addTargetRequest("http://www.xicidaili.com/nt/");
    }

}
