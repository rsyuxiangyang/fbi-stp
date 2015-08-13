package stp.service.quartz.impl;

import java.util.ArrayList;
import java.util.List;
import stp.repository.quartz.dao.ScheduleJobMapper;
import stp.repository.quartz.model.ScheduleJob;
import stp.repository.quartz.model.ScheduleJobExample;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stp.service.quartz.ScheduleJobService;
import stp.common.utils.ScheduleUtils;

/**
 * Created by XIANGYANG on 2015-8-7.
 */

@Service
public class ScheduleJobServiceImpl implements ScheduleJobService  {

    /** 调度工厂Bean */
    @Autowired
    private Scheduler scheduler;

    /*@Autowired
    private scheduleJobMapper   scheduleJobMapper;
    *//** 通用dao */
    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    public void initScheduleJob() {
        ScheduleJobExample example=new ScheduleJobExample();
        List<ScheduleJob> scheduleJobList = scheduleJobMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(scheduleJobList)) {
            return;
        }
        for (ScheduleJob scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler,
                scheduleJob.getJobName(), scheduleJob.getJobGroup());

            //不存在，创建一个
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                //已存在，那么更新相应的定时设置
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    public int insert(ScheduleJob scheduleJobPara) {
        ScheduleUtils.createScheduleJob(scheduler, scheduleJobPara);
        return scheduleJobMapper.insert(scheduleJobPara);
    }

    public void update(ScheduleJob scheduleJobPara) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJobPara);
        scheduleJobMapper.updateByPrimaryKey(scheduleJobPara);
    }

    public void delUpdate(ScheduleJob scheduleJobPara) {
        //先删除
        ScheduleUtils.deleteScheduleJob(scheduler, scheduleJobPara.getJobName(),
                scheduleJobPara.getJobGroup());
        //再创建
        ScheduleUtils.createScheduleJob(scheduler, scheduleJobPara);
        //数据库直接更新即可
        scheduleJobMapper.updateByPrimaryKey(scheduleJobPara);
    }

    public void delete(String scheduleJobIdPara) {
        ScheduleJob scheduleJob = scheduleJobMapper.selectByPrimaryKey(scheduleJobIdPara);
        //删除运行的任务
        ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(),
            scheduleJob.getJobGroup());
        //删除数据
        scheduleJobMapper.deleteByPrimaryKey(scheduleJobIdPara);
    }

    public void runOnce(String scheduleJobIdPara) {

        ScheduleJob scheduleJob = scheduleJobMapper.selectByPrimaryKey(scheduleJobIdPara);
        ScheduleUtils.runOnce(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
    }

    public void pauseJob(String scheduleJobIdPara) {

        ScheduleJob scheduleJob = scheduleJobMapper.selectByPrimaryKey(scheduleJobIdPara);
        ScheduleUtils.pauseJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());

        //演示数据库就不更新了
    }

    public void resumeJob(String scheduleJobIdPara) {
        ScheduleJob scheduleJob = scheduleJobMapper.selectByPrimaryKey(scheduleJobIdPara);
        ScheduleUtils.resumeJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());

        //演示数据库就不更新了
    }

    public ScheduleJob get(String scheduleJobIdPara) {
        ScheduleJob scheduleJob = scheduleJobMapper.selectByPrimaryKey(scheduleJobIdPara);
        return scheduleJob;
    }



    public List<ScheduleJob> queryList() {
        ScheduleJobExample example=new ScheduleJobExample();
        List<ScheduleJob> scheduleJobs = scheduleJobMapper.selectByExample(example);
        try {
            for (ScheduleJob item : scheduleJobs) {

                JobKey jobKey = ScheduleUtils.getJobKey(item.getJobName(), item.getJobGroup());
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                if (CollectionUtils.isEmpty(triggers)) {
                    continue;
                }

                //这里一个任务可以有多个触发器， 但是我们一个任务对应一个触发器，所以只取第一个即可，清晰明了
                Trigger trigger = triggers.iterator().next();
//                notAutoScheduleJob.setJobTrigger(trigger.getKey().getName());

                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                item.setJobStatus(triggerState.name());
                item.setJobTrigger(trigger.getKey().getName());

                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    item.setCronExpression(cronExpression);
                }
            }
        } catch (SchedulerException e) {
            //演示用，就不处理了
        }
        return scheduleJobs;
    }

    public List<ScheduleJob> queryExecutingJobList() {
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                ScheduleJob job = new ScheduleJob();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setJobTrigger(trigger.getKey().getName());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setJobStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
            return jobList;
        } catch (SchedulerException e) {
            //演示用，就不处理了
            return null;
        }
    }
}
