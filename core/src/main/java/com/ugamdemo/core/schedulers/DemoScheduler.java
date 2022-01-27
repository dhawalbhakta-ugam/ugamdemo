package com.ugamdemo.core.schedulers;

import com.ugamdemo.core.config.SchedulerConfiguration;
import com.ugamdemo.core.models.DateUpdate;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, service = Runnable.class)
@Designate(ocd = SchedulerConfiguration.class)
public class DemoScheduler implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(DemoScheduler.class);

    private int schedulerId;
    String path ="/content/ugamdemo/us/en/demoabc/jcr:content/root/container/container/schedulerdate";

    @Reference
    private Scheduler scheduler;

    @Reference
    DateUpdate dateUpdate;

    @Activate
    protected void activate(SchedulerConfiguration config) {
        schedulerId = config.schedulerName().hashCode();
        addScheduler(config);
    }

    @Deactivate
    protected void deactivate(SchedulerConfiguration config) {
        removeScheduler();
    }

    private void removeScheduler() {
        scheduler.unschedule(String.valueOf(schedulerId));
    }

    private void addScheduler(SchedulerConfiguration config) {
        ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
        scheduleOptions.name(String.valueOf(schedulerId));
        scheduleOptions.canRunConcurrently(true);
        scheduler.schedule(this, scheduleOptions);

    }
    @Override
    public void run() {
        LOG.info("\n ====> DEMO RUN METHOD ");
        dateUpdate.updateDate(path);
    }
}