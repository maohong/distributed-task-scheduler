package org.mh.dts.scheduler.api.web;

import org.mh.dts.scheduler.api.dto.SchedulerResponse;
import org.quartz.Trigger;

import java.util.List;
import java.util.Map;

/**
 * Created by maohong on 2016/12/18.
 */
public interface QuartzTriggerManageApi {

    public SchedulerResponse deleteTriggerByTriggerId(Long triggerId);

    public Map<Long,Boolean> deleteTriggerByTriggerIds(List<Long> triggerIds);

    public SchedulerResponse deleteTriggerByTriggerNameAndGroup(String name, String group);

    public Map<Long,String> getTriggerNextFireTimeByIds(List<Long> triggerIds);

    public String getTriggerNextFireTimeById(Long triggerId);

    public Map<Long,Boolean> getTriggerIsScheduledByIds(List<Long> triggerIds);

    public boolean getTriggerIsScheduledByTriggerId(Long triggerId);

    public List<Trigger> getAllTriggers();

}
