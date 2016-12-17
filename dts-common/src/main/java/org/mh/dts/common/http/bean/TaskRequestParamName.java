package org.mh.dts.common.http.bean;


/**
 * @Author maohong@sogou-inc.com
 * 2016/12/5 17:07
 */
public enum TaskRequestParamName {

    METHOD("method", "调用的方法"),
    TASK_NAME("taskName", "任务名"),
    CLIENT_NAME("clientName", "客户端名"),
    OPERATOR("operator", "操作人"),
    SECRET("secret", "认证信息"),
    DATA("data", "任务参数"),
    DEPEND("depend", "任务参数"),
    INTERVAL("interval", "任务参数"),

    ;

    private String name;
    private String desc;

    private TaskRequestParamName(String name, String desc)
    {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
