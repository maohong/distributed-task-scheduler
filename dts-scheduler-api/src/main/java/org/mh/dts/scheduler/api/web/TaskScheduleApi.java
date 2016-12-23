package org.mh.dts.scheduler.api.web;

import org.mh.dts.scheduler.api.dto.SchedulerResponse;
import org.mh.dts.scheduler.api.dto.TaskInfoDto;

import java.util.List;
import java.util.Map;

/**
 * Created by maohong on 2016/12/18.
 */
public interface TaskScheduleApi {

    /**
     * schedule a task by scheduling all its triggers
     * @param triggerId
     * @return
     */
    public SchedulerResponse scheduleByTriggerId(Long triggerId);

    /**
     * scheduler a task, start scheduling all of its triggers
     * @param taskId
     * @return
     */
    public SchedulerResponse scheduleByTaskId(Long taskId);

    /**
     * start scheduling a number of triggers, may be not only one task
     * @param triggerIds
     * @return
     */
    public Map<Long,SchedulerResponse> scheduleByTriggerIds(List<Long> triggerIds);

    /**
     * scheduler a number of tasks, start scheduling all of their triggers
     * @param taskIds
     * @return
     */
    public Map<Long,SchedulerResponse> scheduleByTaskIds(List<Long> taskIds);

    /**
     * unschedule a task by stop scheduling all its triggers
     * @param taskId
     * @return
     */
    public SchedulerResponse unScheduleTaskByTaskId(Long taskId);

    /**
     * unschedule a number of tasks by stop scheduling all their triggers
     * @param taskIds
     * @return
     */
    public Map<Long,SchedulerResponse> unScheduleTaskByTaskIds(List<Long> taskIds);

    /**
     * run a task right now, task info is defined in runTaskDto
     * @param runTaskDto
     * @return
     */
    public SchedulerResponse runTaskNow(TaskInfoDto runTaskDto);



}
