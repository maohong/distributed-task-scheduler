package org.mh.dts.scheduler.api.dto;

import java.io.Serializable;

/**
 * Created by maohong on 2016/12/24.
 */
public class QuartzJobDetail implements Serializable {

    private static final long serialVersionUID = -8698797900918983278L;

    private String name;
    private String group;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "QuartzJobDetail{" +
                "name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
