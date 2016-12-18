package org.mh.dts.common.http.auth;

import javax.servlet.ServletRequest;

/**
 * Created by maohong on 2016/12/18.
 */
public interface ApiAuthenticator {

    public AuthResult authenticate(ServletRequest request);
}
