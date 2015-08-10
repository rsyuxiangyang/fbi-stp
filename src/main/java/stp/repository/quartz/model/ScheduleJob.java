package stp.repository.quartz.model;

import java.io.Serializable;

public class ScheduleJob implements Serializable{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCHEDULE_JOB.PKID
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    private String pkid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCHEDULE_JOB.CREATE_TIME
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCHEDULE_JOB.UPDATE_TIME
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCHEDULE_JOB.JOB_NAME
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    private String jobName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCHEDULE_JOB.JOB_GROUP
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    private String jobGroup;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCHEDULE_JOB.JOB_STATUS
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    private String jobStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCHEDULE_JOB.CRON_EXPRESSION
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    private String cronExpression;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCHEDULE_JOB.DESCRIPTION
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCHEDULE_JOB.BEAN_CLASS
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    private String beanClass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCHEDULE_JOB.IS_CONCURRENT
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    private String isConcurrent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCHEDULE_JOB.SPRING_ID
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    private String springId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCHEDULE_JOB.METHOD_NAME
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    private String methodName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCHEDULE_JOB.JOB_TRIGGER
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    private String jobTrigger;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCHEDULE_JOB.IS_SYNC
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    private String isSync;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCHEDULE_JOB.PKID
     *
     * @return the value of SCHEDULE_JOB.PKID
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public String getPkid() {
        return pkid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCHEDULE_JOB.PKID
     *
     * @param pkid the value for SCHEDULE_JOB.PKID
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public void setPkid(String pkid) {
        this.pkid = pkid == null ? null : pkid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCHEDULE_JOB.CREATE_TIME
     *
     * @return the value of SCHEDULE_JOB.CREATE_TIME
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCHEDULE_JOB.CREATE_TIME
     *
     * @param createTime the value for SCHEDULE_JOB.CREATE_TIME
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCHEDULE_JOB.UPDATE_TIME
     *
     * @return the value of SCHEDULE_JOB.UPDATE_TIME
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCHEDULE_JOB.UPDATE_TIME
     *
     * @param updateTime the value for SCHEDULE_JOB.UPDATE_TIME
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCHEDULE_JOB.JOB_NAME
     *
     * @return the value of SCHEDULE_JOB.JOB_NAME
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCHEDULE_JOB.JOB_NAME
     *
     * @param jobName the value for SCHEDULE_JOB.JOB_NAME
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCHEDULE_JOB.JOB_GROUP
     *
     * @return the value of SCHEDULE_JOB.JOB_GROUP
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public String getJobGroup() {
        return jobGroup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCHEDULE_JOB.JOB_GROUP
     *
     * @param jobGroup the value for SCHEDULE_JOB.JOB_GROUP
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup == null ? null : jobGroup.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCHEDULE_JOB.JOB_STATUS
     *
     * @return the value of SCHEDULE_JOB.JOB_STATUS
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public String getJobStatus() {
        return jobStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCHEDULE_JOB.JOB_STATUS
     *
     * @param jobStatus the value for SCHEDULE_JOB.JOB_STATUS
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus == null ? null : jobStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCHEDULE_JOB.CRON_EXPRESSION
     *
     * @return the value of SCHEDULE_JOB.CRON_EXPRESSION
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCHEDULE_JOB.CRON_EXPRESSION
     *
     * @param cronExpression the value for SCHEDULE_JOB.CRON_EXPRESSION
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCHEDULE_JOB.DESCRIPTION
     *
     * @return the value of SCHEDULE_JOB.DESCRIPTION
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCHEDULE_JOB.DESCRIPTION
     *
     * @param description the value for SCHEDULE_JOB.DESCRIPTION
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCHEDULE_JOB.BEAN_CLASS
     *
     * @return the value of SCHEDULE_JOB.BEAN_CLASS
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public String getBeanClass() {
        return beanClass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCHEDULE_JOB.BEAN_CLASS
     *
     * @param beanClass the value for SCHEDULE_JOB.BEAN_CLASS
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass == null ? null : beanClass.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCHEDULE_JOB.IS_CONCURRENT
     *
     * @return the value of SCHEDULE_JOB.IS_CONCURRENT
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public String getIsConcurrent() {
        return isConcurrent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCHEDULE_JOB.IS_CONCURRENT
     *
     * @param isConcurrent the value for SCHEDULE_JOB.IS_CONCURRENT
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public void setIsConcurrent(String isConcurrent) {
        this.isConcurrent = isConcurrent == null ? null : isConcurrent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCHEDULE_JOB.SPRING_ID
     *
     * @return the value of SCHEDULE_JOB.SPRING_ID
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public String getSpringId() {
        return springId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCHEDULE_JOB.SPRING_ID
     *
     * @param springId the value for SCHEDULE_JOB.SPRING_ID
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public void setSpringId(String springId) {
        this.springId = springId == null ? null : springId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCHEDULE_JOB.METHOD_NAME
     *
     * @return the value of SCHEDULE_JOB.METHOD_NAME
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCHEDULE_JOB.METHOD_NAME
     *
     * @param methodName the value for SCHEDULE_JOB.METHOD_NAME
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCHEDULE_JOB.JOB_TRIGGER
     *
     * @return the value of SCHEDULE_JOB.JOB_TRIGGER
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public String getJobTrigger() {
        return jobTrigger;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCHEDULE_JOB.JOB_TRIGGER
     *
     * @param jobTrigger the value for SCHEDULE_JOB.JOB_TRIGGER
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public void setJobTrigger(String jobTrigger) {
        this.jobTrigger = jobTrigger == null ? null : jobTrigger.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCHEDULE_JOB.IS_SYNC
     *
     * @return the value of SCHEDULE_JOB.IS_SYNC
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public String getIsSync() {
        return isSync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCHEDULE_JOB.IS_SYNC
     *
     * @param isSync the value for SCHEDULE_JOB.IS_SYNC
     *
     * @mbggenerated Mon Aug 10 15:52:45 CST 2015
     */
    public void setIsSync(String isSync) {
        this.isSync = isSync == null ? null : isSync.trim();
    }
}