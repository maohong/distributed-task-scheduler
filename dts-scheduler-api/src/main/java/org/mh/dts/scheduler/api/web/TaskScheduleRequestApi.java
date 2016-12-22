package org.mh.dts.scheduler.api.web;

import org.mh.dts.common.http.servlet.DtsResponse;
import org.mh.dts.scheduler.api.dto.TaskInfoDto;

/**
 * Created by maohong on 2016/12/18.
 */
public interface TaskScheduleRequestApi {

    public DtsResponse scheduleByTriggerId(Long triggerId);

    public DtsResponse runTaskNow(TaskInfoDto runTaskDto);

}
