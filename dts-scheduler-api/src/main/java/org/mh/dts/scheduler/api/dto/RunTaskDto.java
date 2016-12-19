package org.mh.dts.scheduler.api.dto;

import org.mh.dts.common.annotation.Required;

import java.util.Map;

/**
 * Created by maohong on 2016/12/19.
 */
public class RunTaskDto {

    @Required
    public Long taskDetailId;
    @Required
    public Long delayMs;
    @Required
    public boolean runWithDepend;
    @Required
    public String operator;
    @Required
    public String traceId;
    @Required
    public Long redoCount;

    public Map<String,String> externalData;


    public Long getTaskDetailId() {
        return taskDetailId;
    }

    public void setTaskDetailId(Long taskDetailId) {
        this.taskDetailId = taskDetailId;
    }

    public Long getDelayMs() {
        return delayMs;
    }

    public void setDelayMs(Long delayMs) {
        this.delayMs = delayMs;
    }

    public boolean isRunWithDepend() {
        return runWithDepend;
    }

    public void setRunWithDepend(boolean runWithDepend) {
        this.runWithDepend = runWithDepend;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Long getRedoCount() {
        return redoCount;
    }

    public void setRedoCount(Long redoCount) {
        this.redoCount = redoCount;
    }

    public Map<String, String> getExternalData() {
        return externalData;
    }

    public void setExternalData(Map<String, String> externalData) {
        this.externalData = externalData;
    }
}
