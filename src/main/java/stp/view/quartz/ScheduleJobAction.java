package stp.view.quartz;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import skyline.util.MessageUtil;
import stp.common.enums.OperateType;
import stp.repository.quartz.model.ScheduleJob;
import stp.service.quartz.ScheduleJobService;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by XIANGYANG on 2015-8-7.
 */

@ManagedBean
@ViewScoped
public class ScheduleJobAction {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobAction.class);

    @ManagedProperty(value = "#{scheduleJobServiceImpl}")
    private ScheduleJobService scheduleJobService;

    private List<ScheduleJob> scheduleJobList;
    private List<ScheduleJob> executingJobList;

    private String strDialogHeader;
    private String isDisabled;
    private String isResetBtnRender;
    private OperateType operateType;
    private ScheduleJob scheduleJobTemplate;

    @PostConstruct
    public void init() {
        try {
            scheduleJobList=new ArrayList<ScheduleJob>();
            executingJobList=new ArrayList<ScheduleJob>();
            scheduleJobTemplate=new ScheduleJob();
        }catch (Exception e){
            logger.error("页面初始化错误", e);
        }
    }

    /**
     * 任务列表页
     * @return
     */
    public List<ScheduleJob> initScheduleJobList() {
        scheduleJobList = scheduleJobService.queryList();
        return scheduleJobList;
    }

    /**
     * 正在运行任务列表页
     * @return
     */
    public List<ScheduleJob> initExecutingJobList() {
        executingJobList = scheduleJobService.queryExecutingJobList();
        return executingJobList;
    }

    public void addRecordAction(){
        operateType=OperateType.ADD;
        strDialogHeader=operateType.getTitle();
        scheduleJobTemplate=new ScheduleJob();
        isDisabled="false";
        isResetBtnRender="true";
    }

    public void selectRecordAction(String strSubmitTypePara,ScheduleJob scheduleJobPara){
        try {
            scheduleJobTemplate =(ScheduleJob) BeanUtils.cloneBean(scheduleJobPara);
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

    public ScheduleJobService getScheduleJobService() {
        return scheduleJobService;
    }

    public void setScheduleJobService(ScheduleJobService scheduleJobService) {
        this.scheduleJobService = scheduleJobService;
    }

    public List<ScheduleJob> getScheduleJobList() {
        return scheduleJobList;
    }

    public void setScheduleJobList(List<ScheduleJob> scheduleJobList) {
        this.scheduleJobList = scheduleJobList;
    }

    public List<ScheduleJob> getExecutingJobList() {
        return executingJobList;
    }

    public void setExecutingJobList(List<ScheduleJob> executingJobList) {
        this.executingJobList = executingJobList;
    }

    public ScheduleJob getScheduleJobTemplate() {
        return scheduleJobTemplate;
    }

    public void setScheduleJobTemplate(ScheduleJob scheduleJobTemplate) {
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
