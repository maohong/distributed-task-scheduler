package org.mh.dts.scheduler.api.web;

import org.mh.dts.scheduler.api.dto.QuartzSystemInfo;
import org.mh.dts.scheduler.api.dto.SchedulerResponse;

/**
 * Created by maohong on 2016/12/18.
 */
public interface QuartzSchedulerManageApi {

    /**
     * start current quartz scheduler instance
     * @return
     */
    public SchedulerResponse startScheduler();

    /**
     * standby current quartz scheduler instance
     * @return
     */
    public SchedulerResponse standbyScheduler();

    /**
     * get current quartz system info
     * @return
     */
    public QuartzSystemInfo getQuartzSystemInfo();

}
