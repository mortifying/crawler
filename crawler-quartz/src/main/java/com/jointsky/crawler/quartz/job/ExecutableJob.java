package com.jointsky.crawler.quartz.job;

import com.jointsky.crawler.entity.model.JobInfo;
import com.jointsky.crawler.quartz.model.AbstractExecutableJob;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Author  zhangxiong
 * Date    17-7-27 下午2:04
 */
public class ExecutableJob extends AbstractExecutableJob {

    private static Logger LOGGER = LoggerFactory.getLogger(ExecutableJob.class);

    public ExecutableJob(JobInfo jobInfo) {
        super(jobInfo);
    }

    @Override
    public boolean execute(JobExecutionContext context) {
        String pageProcessorClassName = this.jobInfo.getPageProcessorClass();
        String pipelineClassName = this.jobInfo.getPipelineClass();
        String crawlerUrl = this.jobInfo.getCrawlerUrl();
        int threadNum = this.jobInfo.getThreadNum();
        boolean exitWithCpmplete = this.jobInfo.getExitWhithComplete().equals("1");

        if (null == crawlerUrl || "".equals(crawlerUrl)) {
            LOGGER.error("crawler url 不能为空");
            return false;
        }

        Class pageProcessorInstance = null;
        Class pipelineInstance = null;
        try {
            pageProcessorInstance = Class.forName(pageProcessorClassName);
            pipelineInstance = Class.forName(pipelineClassName);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), "类对象映射失败,pageProcessClass:" + pageProcessorClassName + ",pipelineClass:" + pipelineClassName);
            return false;
        }

        PageProcessor pageProcessor = null;
        Pipeline pipeline = null;
        try {
            assert pageProcessorInstance != null;
            pageProcessor = (PageProcessor) pageProcessorInstance.newInstance();
            assert pipelineInstance != null;
            pipeline = (Pipeline) pipelineInstance.newInstance();
        } catch (InstantiationException e) {
            LOGGER.error(e.getMessage(), "类实例化失败,pageProcessClass:" + pageProcessorClassName + ",pipelineClass:" + pipelineClassName);
            return false;
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), "类访问权限受限,pageProcessClass:" + pageProcessorClassName + ",pipelineClass:" + pipelineClassName);
            return false;
        }

        assert pageProcessor != null;
        Spider spider = OOSpider.create(pageProcessor)
                .addUrl(crawlerUrl)
                .addPipeline(pipeline)
                .setExitWhenComplete(exitWithCpmplete);
        if (threadNum > 0) {
            spider.thread(threadNum);
        }
        spider.run();
        return true;
    }
}
