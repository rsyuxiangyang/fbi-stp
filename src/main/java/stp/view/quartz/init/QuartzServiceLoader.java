package stp.view.quartz.init;

import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by XIANGYANG on 2015-8-7.
 */

public class QuartzServiceLoader implements ServletContextListener {
    private SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            Scheduler schedule = schedulerFactoryBean.getScheduler();
            if (!schedule.isShutdown()) {
                schedule.shutdown();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContextEvent.getServletContext());
            schedulerFactoryBean=context.getBean(SchedulerFactoryBean.class);
            Scheduler schedule=schedulerFactoryBean.getScheduler();
            if (!schedule.isStarted()){
                schedule.start();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

