package org.mh.dts.common.constant;

/**
 * Created by maohong on 2016/12/24.
 */
public enum HttpRequestParamName {

    REQUEST_HEADER_NAME_FOR_AUTH("REQUEST_AUTHENTICATION_INFO"),
    REQUEST_PARAM_NAME_API_METHOD("apiMethod"),
    REQUEST_PARAM_NAME_API_PARAM_PREFIX("apiParam");

    private String param;
    private HttpRequestParamName(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}

