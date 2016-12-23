package org.mh.dts.scheduler.handler;

import org.mh.dts.scheduler.api.dto.SchedulerResponse;
import org.mh.dts.scheduler.api.dto.TaskInfoDto;
import org.mh.dts.scheduler.api.web.TaskScheduleApi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by maohong on 2016/12/18.
 */
@Service
public class TaskScheduleApiHandler implements TaskScheduleApi {

    @Override
    public SchedulerResponse scheduleByTriggerId(Long triggerId) {
        return null;
    }

    @Override
    public SchedulerResponse scheduleByTaskId(Long taskId) {
        return null;
    }

    @Override
    public Map<Long, SchedulerResponse> scheduleByTriggerIds(List<Long> triggerIds) {
        return null;
    }

    @Override
    public Map<Long, SchedulerResponse> scheduleByTaskIds(List<Long> taskIds) {
        return null;
    }

    @Override
    public SchedulerResponse unScheduleTaskByTaskId(Long taskId) {
        return null;
    }

    @Override
    public Map<Long, SchedulerResponse> unScheduleTaskByTaskIds(List<Long> taskIds) {
        return null;
    }

    @Override
    public SchedulerResponse runTaskNow(TaskInfoDto runTaskDto) {
        return null;
    }
}
