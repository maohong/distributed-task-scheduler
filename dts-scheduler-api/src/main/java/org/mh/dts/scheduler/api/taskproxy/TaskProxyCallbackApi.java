package org.mh.dts.scheduler.api.taskproxy;

import org.mh.dts.scheduler.api.dto.TaskExecuteResultDto;
import org.mh.dts.scheduler.api.dto.TaskReportResponse;

/**
 * API for taskproxy call back when tasks done
 * Created by maohong on 2016/12/19.
 */
public interface TaskProxyCallbackApi {

    /**
     * report task result to DTS scheduler server
     * @param taskExecuteResultDto
     * @return
     */
    public TaskReportResponse reportTaskResultToScheduler(TaskExecuteResultDto taskExecuteResultDto);

}
