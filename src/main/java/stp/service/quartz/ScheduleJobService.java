package stp.service.quartz;

import stp.repository.quartz.model.not_auto.NotAutoScheduleJob;

import java.util.List;

/**
 * 定时任务service
 *
 * Created by liyd on 12/19/14.
 */
public interface ScheduleJobService {

    /**
     * 初始化定时任务
     */
    public void initScheduleJob();

    /**
     * 新增
     * 
     * @param notAutoScheduleJob
     * @return
     */
    public int insert(NotAutoScheduleJob notAutoScheduleJob);

    /**
     * 直接修改 只能修改运行的时间，参数、同异步等无法修改
     * 
     * @param notAutoScheduleJob
     */
    public void update(NotAutoScheduleJob notAutoScheduleJob);

    /**
     * 删除重新创建方式
     * 
     * @param notAutoScheduleJob
     */
    public void delUpdate(NotAutoScheduleJob notAutoScheduleJob);

    /**
     * 删除
     * 
     * @param scheduleJobId
     */
    public void delete(String scheduleJobId);

    /**
     * 运行一次任务
     *
     * @param scheduleJobId the schedule job id
     * @return
     */
    public void runOnce(String scheduleJobId);

    /**
     * 暂停任务
     *
     * @param scheduleJobId the schedule job id
     * @return
     */
    public void pauseJob(String scheduleJobId);

    /**
     * 恢复任务
     *
     * @param scheduleJobId the schedule job id
     * @return
     */
    public void resumeJob(String scheduleJobId);

    /**
     * 获取任务对象
     * 
     * @param scheduleJobId
     * @return
     */
    public NotAutoScheduleJob get(String scheduleJobId);

    /**
     * 查询任务列表
     * @return
     */
    public List<NotAutoScheduleJob> queryList();

    /**
     * 获取运行中的任务列表
     *
     * @return
     */
    public List<NotAutoScheduleJob> queryExecutingJobList();

}
