package org.mh.dts.scheduler.handler;

import org.mh.dts.common.http.servlet.DtsResponse;
import org.mh.dts.scheduler.api.dto.TaskInfoDto;
import org.mh.dts.scheduler.api.web.TaskScheduleRequestApi;

/**
 * Created by maohong on 2016/12/18.
 */
public class TaskScheduleRequestHandler implements TaskScheduleRequestApi {

    @Override
    public DtsResponse scheduleByTriggerId(Long triggerId) {


        return null;
    }

    @Override
    public DtsResponse runTaskNow(TaskInfoDto taskInfoDto) {

        return null;
    }

}
