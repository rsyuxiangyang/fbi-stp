package stp.view.quartz;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import skyline.util.MessageUtil;
import stp.common.enums.OperateType;
import stp.repository.quartz.model.not_auto.NotAutoScheduleJob;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import stp.service.quartz.ScheduleJobService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by liyd on 2015/4/3.
 */
@ManagedBean
@ViewScoped
public class ScheduleJobAction {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobAction.class);

    @ManagedProperty(value = "#{scheduleJobServiceImpl}")
    private ScheduleJobService scheduleJobService;

    private List<NotAutoScheduleJob> scheduleJobList;
    private List<NotAutoScheduleJob> executingJobList;

    private String strDialogHeader;
    private String isDisabled;
    private String isResetBtnRender;
    private OperateType operateType;
    private NotAutoScheduleJob scheduleJobTemplate;

    @PostConstruct
    public void init() {
        try {
            scheduleJobList=new ArrayList<NotAutoScheduleJob>();
            executingJobList=new ArrayList<NotAutoScheduleJob>();
            scheduleJobTemplate=new NotAutoScheduleJob();
        }catch (Exception e){
            logger.error("页面初始化错误", e);
        }
    }

    /**
     * 任务列表页
     * @return
     */
    public List<NotAutoScheduleJob> initScheduleJobList() {
        scheduleJobList = scheduleJobService.queryList();
        return scheduleJobList;
    }

    /**
     * 正在运行任务列表页
     * @return
     */
    public List<NotAutoScheduleJob> initExecutingJobList() {
        executingJobList = scheduleJobService.queryExecutingJobList();
        return executingJobList;
    }

    public void addRecordAction(){
        operateType=OperateType.ADD;
        strDialogHeader=operateType.getTitle();
        scheduleJobTemplate=new NotAutoScheduleJob();
        isDisabled="false";
        isResetBtnRender="true";
    }

    public void selectRecordAction(String strSubmitTypePara,NotAutoScheduleJob scheduleJobPara){
        try {
            scheduleJobTemplate =(NotAutoScheduleJob) BeanUtils.cloneBean(scheduleJobPara);
            operateType=OperateType.valueOf(strSubmitTypePara);
            strDialogHeader=operateType.getTitle();
//            if (operateType.equals(OperateType.UPD)||operateType.equals(OperateType.DEL_CREATE)){
            if (operateType.equals(OperateType.UPD)){
                isDisabled="false";
                isResetBtnRender="true";
            }else{
                isDisabled="true";
                isResetBtnRender="false";
            }
        } catch (Exception e) {
            MessageUtil.addError(e.getMessage());
        }

    }

    public void submitThisRecordAction(){
        try{
            if(OperateType.ADD.equals(operateType)){
                scheduleJobService.insert(scheduleJobTemplate);
            }else if(OperateType.UPD.equals(operateType)){
                scheduleJobService.update(scheduleJobTemplate);
            }/*else if(OperateType.DEL_CREATE.equals(operateType)){
                scheduleJobService.delUpdate(scheduleJobTemplate);
            }*/else if(OperateType.DEL.equals(operateType)){
                scheduleJobService.delete(scheduleJobTemplate.getPkid());
            }else if(OperateType.PAUSE.equals(operateType)){
                scheduleJobService.pauseJob(scheduleJobTemplate.getPkid());
            }else if(OperateType.RESUME.equals(operateType)){
                scheduleJobService.resumeJob(scheduleJobTemplate.getPkid());
            }else if(OperateType.RUN_ONCE.equals(operateType)){
                scheduleJobService.runOnce(scheduleJobTemplate.getPkid());
            }
            initScheduleJobList();
            initExecutingJobList();
            MessageUtil.addInfo(operateType.getTitle()+"成功");
        }catch (Exception e){
            logger.error("提交信息失败",e);
            MessageUtil.addError(operateType.getTitle()+"失败");
        }
    }

    /**
     * 任务页面
     * @return
     */
    @RequestMapping(value = "input-schedule-job", method = RequestMethod.GET)
    public String inputScheduleJob(NotAutoScheduleJob notAutoScheduleJob, ModelMap modelMap) {
        if (notAutoScheduleJob.getPkid() != null) {
            NotAutoScheduleJob scheduleJob = scheduleJobService.get(notAutoScheduleJob.getPkid());
            scheduleJob.setKeywords(notAutoScheduleJob.getKeywords());
            modelMap.put("scheduleJobVo", scheduleJob);
        }
        return "input-schedule-job";
    }

    /**
     * 删除任务
     * @return
     */
    @RequestMapping(value = "delete-schedule-job", method = RequestMethod.GET)
    public String deleteScheduleJob(String scheduleJobId) {

        scheduleJobService.delete(scheduleJobId);

        return "redirect:list-schedule-job.shtml";
    }

    /**
     * 运行一次
     * @return
     */
    @RequestMapping(value = "run-once-schedule-job", method = RequestMethod.GET)
    public String runOnceScheduleJob(String scheduleJobId) {

        scheduleJobService.runOnce(scheduleJobId);

        return "redirect:list-schedule-job.shtml";
    }

    /**
     * 暂停
     * @return
     */
    @RequestMapping(value = "pause-schedule-job", method = RequestMethod.GET)
    public String pauseScheduleJob(String scheduleJobId) {
        scheduleJobService.pauseJob(scheduleJobId);
        return "redirect:list-schedule-job.shtml";
    }

    /**
     * 恢复
     * @return
     */
    @RequestMapping(value = "resume-schedule-job", method = RequestMethod.GET)
    public String resumeScheduleJob(String scheduleJobId) {
        scheduleJobService.resumeJob(scheduleJobId);
        return "redirect:list-schedule-job.shtml";
    }

    /**
     * 保存任务
     * @param notAutoScheduleJob
     * @return
     */
    @RequestMapping(value = "save-schedule-job", method = RequestMethod.POST)
    public String saveScheduleJob(NotAutoScheduleJob notAutoScheduleJob) {

        //测试用随便设个状态
        notAutoScheduleJob.setJobStatus("1");

        if (notAutoScheduleJob.getPkid() == null) {
            scheduleJobService.insert(notAutoScheduleJob);
        } else if (StringUtils.equalsIgnoreCase(notAutoScheduleJob.getKeywords(),"delUpdate")){
            //直接拿keywords存一下，就不另外重新弄了
            scheduleJobService.delUpdate(notAutoScheduleJob);
        }else {
            scheduleJobService.update(notAutoScheduleJob);
        }
        return "redirect:list-schedule-job.shtml";
    }

    public ScheduleJobService getScheduleJobService() {
        return scheduleJobService;
    }

    public void setScheduleJobService(ScheduleJobService scheduleJobService) {
        this.scheduleJobService = scheduleJobService;
    }

    public List<NotAutoScheduleJob> getScheduleJobList() {
        return scheduleJobList;
    }

    public void setScheduleJobList(List<NotAutoScheduleJob> scheduleJobList) {
        this.scheduleJobList = scheduleJobList;
    }

    public List<NotAutoScheduleJob> getExecutingJobList() {
        return executingJobList;
    }

    public void setExecutingJobList(List<NotAutoScheduleJob> executingJobList) {
        this.executingJobList = executingJobList;
    }

    public NotAutoScheduleJob getScheduleJobTemplate() {
        return scheduleJobTemplate;
    }

    public void setScheduleJobTemplate(NotAutoScheduleJob scheduleJobTemplate) {
        this.scheduleJobTemplate = scheduleJobTemplate;
    }

    public String getStrDialogHeader() {
        return strDialogHeader;
    }

    public void setStrDialogHeader(String strDialogHeader) {
        this.strDialogHeader = strDialogHeader;
    }

    public String getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(String isDisabled) {
        this.isDisabled = isDisabled;
    }

    public String getIsResetBtnRender() {
        return isResetBtnRender;
    }

    public void setIsResetBtnRender(String isResetBtnRender) {
        this.isResetBtnRender = isResetBtnRender;
    }
}
