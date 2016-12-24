package org.mh.dts.scheduler.api.dto;

import org.mh.dts.common.annotation.Required;
import org.mh.dts.common.utils.JsonUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maohong on 2016/12/19.
 */
public class TaskInfoDto implements Serializable {

    private static final long serialVersionUID = -3105263026207861530L;

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

    @Override
    public String toString() {
        return "TaskInfoDto{" +
                "taskDetailId=" + taskDetailId +
                ", delayMs=" + delayMs +
                ", runWithDepend=" + runWithDepend +
                ", operator='" + operator + '\'' +
                ", traceId='" + traceId + '\'' +
                ", redoCount=" + redoCount +
                ", externalData=" + externalData +
                '}';
    }

    public static void main(String args[]) {

        TaskInfoDto dto = new TaskInfoDto();
        dto.setTaskDetailId(123L);
        dto.setDelayMs(12345L);
        dto.setExternalData(new HashMap<String, String>(){{put("a","1");put("b","2");}});
        dto.setOperator("maohong");
        dto.setRunWithDepend(false);
        String str = JsonUtils.toJsonString(dto);

        System.out.println(str);

        TaskInfoDto t = (TaskInfoDto) JsonUtils.readObject(str, TaskInfoDto.class);

        System.out.println(dto);
        System.out.println(t);

    }
}
