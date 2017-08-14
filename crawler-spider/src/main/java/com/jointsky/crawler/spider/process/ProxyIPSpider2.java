package com.jointsky.crawler.spider.process;

import com.jointsky.crawler.entity.model.ProxyIp;
import com.jointsky.crawler.quartz.common.utils.IDGenerator;
import com.jointsky.crawler.spider.utils.UserAgentUtils;
import org.apache.http.HttpHost;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Author  zhangxiong
 * Date    17-7-26 下午3:51
 */
public class ProxyIPSpider2 implements PageProcessor {


    @Override
    public Site getSite() {
        return Site.me().setTimeOut(6000).setRetryTimes(3).setHttpProxy(new HttpHost("", 0, null))
                .setSleepTime(1000).setCharset("UTF-8").addHeader("Accept-Encoding", "/")
                .setUserAgent(UserAgentUtils.radomUserAgent());

    }

    @Override
    public void process(Page page) {
        List<String> ipList = page.getHtml().xpath("//table[@class='table table-bordered table-striped']/tbody/tr").all();
        List<ProxyIp> result = new ArrayList<>();

        if (ipList != null && ipList.size() > 0) {
            for (String tmp : ipList) {
                Html html = Html.create(tmp);
                ProxyIp proxyIp = new ProxyIp();
                String[] data = html.xpath("//body/text()").toString().trim().split("\\s+");
                String dataStr = html.xpath("//body/text()").toString();

                proxyIp.setIp(data[0]);
                proxyIp.setPort(Integer.valueOf(data[1]));
                proxyIp.setId(IDGenerator.getUUID(data[0] + data[1]));

                Pattern pattern = Pattern.compile("HTTPS?\\s(.*)?\\s\\d秒");
                Matcher matcher = pattern.matcher(dataStr);
                if (matcher.find()) {
                    proxyIp.setAddress(matcher.group(1));
                }

                proxyIp.setType(data[3]);
                proxyIp.setAddTime(new Date());
                proxyIp.setUpdateTime(new Date());

                result.add(proxyIp);
            }
        }
        page.putField("result", result);
        page.addTargetRequest("http://www.kuaidaili.com/free/inha/2/");
        page.addTargetRequest("http://www.kuaidaili.com/free/intr/1/");
    }

}
