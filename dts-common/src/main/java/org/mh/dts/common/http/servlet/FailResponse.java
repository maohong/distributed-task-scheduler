package org.mh.dts.common.http.servlet;

/**
 * Created by maohong on 2016/12/18.
 */
public class FailResponse extends DtsResponse {

    private static final long serialVersionUID = -1991555155973828734L;

    public FailResponse(String msg) {
        super();
        isOk = false;
    }
}
