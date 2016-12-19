package org.mh.dts.scheduler.api.web;

import org.mh.dts.common.http.servlet.DtsResponse;

import java.util.Map;

/**
 * Created by maohong on 2016/12/18.
 */
public interface TaskScheduleRequestApi {

    public DtsResponse scheduleByTriggerId(Map<String, Object> params);
    public DtsResponse runTaskNow(Map<String, Object> params);
}
