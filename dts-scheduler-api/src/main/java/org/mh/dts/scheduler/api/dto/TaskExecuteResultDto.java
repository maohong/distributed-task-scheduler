package org.mh.dts.scheduler.api.dto;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.Serializable;

/**
 * Created by maohong on 2016/12/23.
 */
public class TaskExecuteResultDto implements Serializable, Job {

    private static final long serialVersionUID = -721288823731317339L;

    private Long taskId;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }

    @Override
    public String toString() {
        return "TaskExecuteResultDto{" +
                "taskId=" + taskId +
                '}';
    }
}
