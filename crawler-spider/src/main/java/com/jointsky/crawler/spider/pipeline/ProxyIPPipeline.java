package com.jointsky.crawler.spider.pipeline;

import com.alibaba.fastjson.JSON;
import com.jointsky.crawler.entity.model.ProxyIp;
import com.jointsky.crawler.quartz.common.utils.CheckIPUtil;
import com.jointsky.crawler.quartz.common.utils.SpringContextUtil;
import com.jointsky.crawler.service.proxyip.ProxyIpService;
import org.springframework.kafka.core.KafkaTemplate;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * Author  zhangxiong
 * Date    17-7-26 下午3:41
 */
public class ProxyIPPipeline implements Pipeline {

    /**
     * 保存数据
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<ProxyIp> list = resultItems.get("result");

        ProxyIpService proxyIpService = (ProxyIpService) SpringContextUtil.getBean(ProxyIpService.class);

        KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate<String, String>) SpringContextUtil.getBean(KafkaTemplate.class);

        if (list != null && list.size() > 0) {
            for (ProxyIp proxyIp : list) {
                //检查IP地址是否可用
                if (!CheckIPUtil.checkValidIP(proxyIp.getIp(), proxyIp.getPort()))
                    continue;
                ProxyIp proxyip = proxyIpService.findById(proxyIp.getId());
                //存在，则更新
                if (null != proxyip) {
                    proxyIpService.update(proxyIp);
                } else {
                    proxyIpService.insert(proxyIp);
                }

                System.out.println("send message:" + proxyIp.getIp());
                //TODO just test kafka producer
                //kafkaTemplate.send("test_topic", JSON.toJSONString(proxyIp));
            }
        }

    }

}
