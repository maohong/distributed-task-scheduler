package org.mh.dts.common.http.bean;


/**
 * 2016/12/5 17:07
 */
public enum RequestMethod {

    RUN_TASK("runTask", "立即执行任务"),
    ;

    private String method;
    private String desc;

    private RequestMethod(String method, String desc)
    {
        this.method = method;
        this.desc = desc;
    }

    public String getMethod() {
        return method;
    }

    public String getDesc() {
        return desc;
    }

}
