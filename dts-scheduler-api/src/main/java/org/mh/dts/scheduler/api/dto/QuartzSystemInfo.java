package org.mh.dts.scheduler.api.dto;

import org.mh.dts.common.http.servlet.DtsResponse;

/**
 * Created by maohong on 2016/12/23.
 */
public class QuartzSystemInfo extends DtsResponse {

    private static final long serialVersionUID = 2928181319904035142L;

    private String schedulerName;
    private String state;
    private String runningSince;
    private Long numJobsExecuted;
    private String persistenceType;
    private Long threadPoolSiz;
    private String version;
    private String schedulerInstances;

    public String getSchedulerName() {
        return schedulerName;
    }

    public void setSchedulerName(String schedulerName) {
        this.schedulerName = schedulerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRunningSince() {
        return runningSince;
    }

    public void setRunningSince(String runningSince) {
        this.runningSince = runningSince;
    }

    public Long getNumJobsExecuted() {
        return numJobsExecuted;
    }

    public void setNumJobsExecuted(Long numJobsExecuted) {
        this.numJobsExecuted = numJobsExecuted;
    }

    public String getPersistenceType() {
        return persistenceType;
    }

    public void setPersistenceType(String persistenceType) {
        this.persistenceType = persistenceType;
    }

    public Long getThreadPoolSiz() {
        return threadPoolSiz;
    }

    public void setThreadPoolSiz(Long threadPoolSiz) {
        this.threadPoolSiz = threadPoolSiz;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSchedulerInstances() {
        return schedulerInstances;
    }

    public void setSchedulerInstances(String schedulerInstances) {
        this.schedulerInstances = schedulerInstances;
    }

    @Override
    public String toString() {
        return "QuartzSystemInfo{" +
                "schedulerName='" + schedulerName + '\'' +
                ", state='" + state + '\'' +
                ", runningSince='" + runningSince + '\'' +
                ", numJobsExecuted=" + numJobsExecuted +
                ", persistenceType='" + persistenceType + '\'' +
                ", threadPoolSiz=" + threadPoolSiz +
                ", version='" + version + '\'' +
                ", schedulerInstances='" + schedulerInstances + '\'' +
                '}';
    }
}
