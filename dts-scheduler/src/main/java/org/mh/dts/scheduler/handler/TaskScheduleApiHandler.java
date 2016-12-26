package org.mh.dts.scheduler.handler;

import lombok.extern.slf4j.Slf4j;
import org.mh.dts.scheduler.api.dto.SchedulerResponse;
import org.mh.dts.scheduler.api.dto.TaskInfoDto;
import org.mh.dts.scheduler.api.web.TaskScheduleApi;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maohong on 2016/12/18.
 */
@Slf4j
@Service
public class TaskScheduleApiHandler implements TaskScheduleApi {

    @Override
    public SchedulerResponse scheduleByTriggerId(Long triggerId) {

        log.info("called scheduleByTriggerId");
        return new SchedulerResponse(true, "success");
    }

    @Override
    public SchedulerResponse scheduleByTaskId(Long taskId) {

        log.info("called scheduleByTaskId");
        return new SchedulerResponse(true, "success");
    }

    @Override
    public SchedulerResponse<Map<Long,Boolean>> scheduleByTriggerIds(List<Long> triggerIds) {
        return null;
    }

    @Override
    public SchedulerResponse<Map<Long,Boolean>> scheduleByTaskIds(List<Long> taskIds) {
        return null;
    }

    @Override
    public SchedulerResponse unScheduleTaskByTaskId(Long taskId) {
        return null;
    }

    @Override
    public SchedulerResponse<Map<Long,Boolean>> unScheduleTaskByTaskIds(List<Long> taskIds) {
        return null;
    }

    @Override
    public SchedulerResponse runTaskNow(TaskInfoDto runTaskDto) {
        return null;
    }

    @Override
    public SchedulerResponse<Map<Long,Boolean>> test() {

        SchedulerResponse<Map<Long,Boolean>> response = new SchedulerResponse<>();
        response.setIsOk(true);
        response.setMsg("hahahaha");

        Map<Long,Boolean> map = new HashMap<Long, Boolean>();
        map.put(1L,true);
        map.put(2L, false);
        response.setData(map);

        return response;
    }
}
