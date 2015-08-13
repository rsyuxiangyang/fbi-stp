package stp.service.quartz;

import stp.repository.quartz.model.ScheduleJob;
import java.util.List;

/**
 * Created by XIANGYANG on 2015-8-7.
 */

public interface ScheduleJobService {

    /**
     * 初始化定时任务
     */
    public void initScheduleJob();

    /**
     * 新增
     * 
     * @param scheduleJobPara
     * @return
     */
    public int insert(ScheduleJob scheduleJobPara);

    /**
     * 直接修改 只能修改运行的时间，参数、同异步等无法修改
     * 
     * @param scheduleJobPara
     */
    public void update(ScheduleJob scheduleJobPara);

    /**
     * 删除重新创建方式
     * 
     * @param notAutoScheduleJob
     */
    public void delUpdate(ScheduleJob scheduleJobPara);

    /**
     * 删除
     * 
     * @param scheduleJobIdPara
     */
    public void delete(String scheduleJobIdPara);

    /**
     * 运行一次任务
     *
     * @param scheduleJobIdPara the schedule job id
     * @return
     */
    public void runOnce(String scheduleJobIdPara);

    /**
     * 暂停任务
     *
     * @param scheduleJobIdPara the schedule job id
     * @return
     */
    public void pauseJob(String scheduleJobIdPara);

    /**
     * 恢复任务
     *
     * @param scheduleJobIdPara the schedule job id
     * @return
     */
    public void resumeJob(String scheduleJobIdPara);

    /**
     * 获取任务对象
     * 
     * @param scheduleJobIdPara
     * @return
     */
    public ScheduleJob get(String scheduleJobIdPara);

    /**
     * 查询任务列表
     * @return
     */
    public List<ScheduleJob> queryList();

    /**
     * 获取运行中的任务列表
     *
     * @return
     */
    public List<ScheduleJob> queryExecutingJobList();

}
