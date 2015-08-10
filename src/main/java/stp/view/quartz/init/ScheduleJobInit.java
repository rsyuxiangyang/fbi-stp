package stp.view.quartz.init;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stp.service.quartz.ScheduleJobService;

/**
 * 定时任务初始化
 * */
@Component
public class ScheduleJobInit {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobInit.class);

    /** 定时任务service */
    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 项目启动时初始化
     */
    @PostConstruct
    public void init() {
        if (logger.isInfoEnabled()) {
            logger.info("init");
        }
        scheduleJobService.initScheduleJob();
        if (logger.isInfoEnabled()) {
            logger.info("end");
        }
    }

}
