package org.mh.dts.common.http.auth;

import org.apache.commons.lang.StringUtils;

/**
 * Created by maohong on 2016/12/18.
 */
public class AuthResult {

    private boolean authPass;
    private String msg;

    public static final AuthResult PASS = new AuthResult(true, "pass authentication");

    private AuthResult(boolean authPass, String msg) {
        this.authPass = authPass;
        this.msg = msg;
    }

    public static AuthResult fail(String msg) {
        return new AuthResult(false, msg);
    }

    public static AuthResult failForMissParam(String ... params) {
        return new AuthResult(false, String.format("param %s is empty!", StringUtils.join(params, ",")));
    }

    public boolean isAuthPass() {
        return authPass;
    }

    public void setAuthPass(boolean authPass) {
        this.authPass = authPass;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
