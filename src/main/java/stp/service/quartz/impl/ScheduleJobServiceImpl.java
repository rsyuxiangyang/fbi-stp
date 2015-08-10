package stp.service.quartz.impl;

import java.util.ArrayList;
import java.util.List;

import stp.repository.quartz.dao.ScheduleJobMapper;
import stp.repository.quartz.dao.not_auto.NotAutoScheduleJobMapper;
import stp.repository.quartz.model.ScheduleJob;
import stp.repository.quartz.model.ScheduleJobExample;
import stp.repository.quartz.model.not_auto.NotAutoScheduleJob;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stp.common.assistant.bean.BeanConverter;
import stp.service.quartz.ScheduleJobService;
import stp.common.utils.ScheduleUtils;

/**
 * 定时任务
 *
 * Created by liyd on 12/19/14.
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

    @Autowired
    private NotAutoScheduleJobMapper notAutoScheduleJobMapper;

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

    public int insert(NotAutoScheduleJob notAutoScheduleJob) {
        ScheduleJob scheduleJob = notAutoScheduleJob.getTargetObject(ScheduleJob.class);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
        return scheduleJobMapper.insert(scheduleJob);
    }

    public void update(NotAutoScheduleJob notAutoScheduleJob) {
        ScheduleJob scheduleJob = notAutoScheduleJob.getTargetObject(ScheduleJob.class);
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
        scheduleJobMapper.updateByPrimaryKey(scheduleJob);
    }

    public void delUpdate(NotAutoScheduleJob notAutoScheduleJob) {
        ScheduleJob scheduleJob = notAutoScheduleJob.getTargetObject(ScheduleJob.class);
        //先删除
        ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(),
            scheduleJob.getJobGroup());
        //再创建
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
        //数据库直接更新即可
        scheduleJobMapper.updateByPrimaryKey(scheduleJob);
    }

    public void delete(String scheduleJobId) {
        ScheduleJob scheduleJob = scheduleJobMapper.selectByPrimaryKey(scheduleJobId);
        //删除运行的任务
        ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(),
            scheduleJob.getJobGroup());
        //删除数据
        scheduleJobMapper.deleteByPrimaryKey(scheduleJobId);
    }

    public void runOnce(String scheduleJobId) {

        ScheduleJob scheduleJob = scheduleJobMapper.selectByPrimaryKey(scheduleJobId);
        ScheduleUtils.runOnce(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
    }

    public void pauseJob(String scheduleJobId) {

        ScheduleJob scheduleJob = scheduleJobMapper.selectByPrimaryKey(scheduleJobId);
        ScheduleUtils.pauseJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());

        //演示数据库就不更新了
    }

    public void resumeJob(String scheduleJobId) {
        ScheduleJob scheduleJob = scheduleJobMapper.selectByPrimaryKey(scheduleJobId);
        ScheduleUtils.resumeJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());

        //演示数据库就不更新了
    }

    public NotAutoScheduleJob get(String scheduleJobId) {
        ScheduleJob scheduleJob = scheduleJobMapper.selectByPrimaryKey(scheduleJobId);
//        return scheduleJob.getTargetObject(ScheduleJobVo.class);
        return new NotAutoScheduleJob();
    }



    public List<NotAutoScheduleJob> queryList() {
        ScheduleJobExample example=new ScheduleJobExample();
        List<ScheduleJob> scheduleJobs = scheduleJobMapper.selectByExample(example);

        List<NotAutoScheduleJob> notAutoScheduleJobList = BeanConverter.convert(NotAutoScheduleJob.class,
            scheduleJobs);
        try {
            for (NotAutoScheduleJob item : notAutoScheduleJobList) {

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
        return notAutoScheduleJobList;
    }

    public List<NotAutoScheduleJob> queryExecutingJobList() {
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            List<NotAutoScheduleJob> jobList = new ArrayList<NotAutoScheduleJob>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                NotAutoScheduleJob job = new NotAutoScheduleJob();
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
