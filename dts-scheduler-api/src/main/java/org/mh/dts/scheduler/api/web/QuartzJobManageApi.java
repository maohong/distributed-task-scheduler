package org.mh.dts.scheduler.api.web;

import org.mh.dts.scheduler.api.dto.QuartzJobDetail;
import org.mh.dts.scheduler.api.dto.SchedulerResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by maohong on 2016/12/18.
 */
public interface QuartzJobManageApi {

    public SchedulerResponse deleteJobByTaskId(Long taskId);

    public SchedulerResponse deleteJobByNameAndGroup(String name, String group);

    public SchedulerResponse<Map<Long,Boolean>> getTaskIsScheduledByTaskIds(List<Long> taskIds);

    public SchedulerResponse getTaskIsScheduledByTaskId(Long taskId);

    public SchedulerResponse<List<QuartzJobDetail>> getAllJobDetails();

}
