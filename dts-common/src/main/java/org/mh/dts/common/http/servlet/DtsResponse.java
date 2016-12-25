package org.mh.dts.common.http.servlet;

import java.io.Serializable;

/**
 * Created by maohong on 2016/12/18.
 */
public abstract class DtsResponse implements Serializable{

    private static final long serialVersionUID = 4558340474090209307L;

    protected boolean isOk;
    protected String msg;

    public boolean getIsOk() {
        return isOk;
    }

    public void setIsOk(boolean ok) {
        isOk = ok;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
