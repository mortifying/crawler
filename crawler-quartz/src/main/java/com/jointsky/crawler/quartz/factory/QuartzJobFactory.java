package com.jointsky.crawler.quartz.factory;

import com.jointsky.crawler.quartz.model.IExecutable;
import com.jointsky.crawler.quartz.common.Constant;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author  zhangxiong
 * Date    17-7-26 下午3:47
 */
public class QuartzJobFactory implements Job {
    private static Logger LOGGER = LoggerFactory.getLogger(QuartzJobFactory.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Object job = context.getMergedJobDataMap().get(Constant.JOB_PARAM_KEY);
        if (job != null && job instanceof IExecutable) {
            try {
                if (((IExecutable) job).execute(context))
                    LOGGER.info("任务{}执行成功……", job.toString());
                else LOGGER.info("任务{}执行失败！", job.toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
