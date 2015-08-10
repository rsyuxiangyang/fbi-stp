package stp.crontask;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stp.common.utils.TaskUtils;
import stp.repository.quartz.model.ScheduleJob;
import stp.repository.quartz.model.not_auto.NotAutoScheduleJob;

/**
 * 任务工厂类,非同步
 * Created by XIANGYANG on 2015-8-10.
 */
@DisallowConcurrentExecution
public class JobFactory implements Job {

    /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(JobFactory.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {

        LOG.info("JobFactory execute");

        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(
            NotAutoScheduleJob.JOB_PARAM_KEY);

        System.out.println("jobName:" + scheduleJob.getJobName() + "  " + scheduleJob);

        TaskUtils.invokMethod(scheduleJob);
    }
}
