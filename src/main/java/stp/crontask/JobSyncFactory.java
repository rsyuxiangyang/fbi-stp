package stp.crontask;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stp.repository.quartz.model.ScheduleJob;
import stp.repository.quartz.model.not_auto.NotAutoScheduleJob;


/**
 * 同步的任务工厂类
 *
 * Created by liyd on 12/19/14.
 */
public class JobSyncFactory implements Job {

    /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(JobSyncFactory.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        LOG.info("JobSyncFactory execute");

        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        ScheduleJob scheduleJob = (ScheduleJob) mergedJobDataMap.get(NotAutoScheduleJob.JOB_PARAM_KEY);

        System.out.println("jobName:" + scheduleJob.getJobName() + "  " + scheduleJob);
//        TaskUtils.invokMethod(scheduleJob);


        /*int count=0;
        while (true) {
            if (count < 10) {
                try {
                    Thread.sleep(10000);
                    System.out.println("jobName:" + scheduleJob.getJobName() + "  " + scheduleJob+" 正在运行第"+count+"次");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }
            count++;
        }*/
    }
}
