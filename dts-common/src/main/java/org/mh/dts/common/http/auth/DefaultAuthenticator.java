package org.mh.dts.common.http.auth;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.mh.dts.common.constant.DtsConstant;
import org.mh.dts.common.utils.MD5Generator;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by maohong on 2016/12/18.
 */
@Slf4j
public class DefaultAuthenticator implements ApiAuthenticator {

    private List<String> paramsForAuth;
    private String apiSecret;

    @Override
    public AuthResult authenticate(ServletRequest request) {

        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String authInfo = httpServletRequest.getHeader(DtsConstant.REQUEST_HEADER_NAME_FOR_AUTH);
        if (StringUtils.isBlank(authInfo))
            return AuthResult.fail("miss auth info in header : " + DtsConstant.REQUEST_HEADER_NAME_FOR_AUTH);

        // authInfo contains a calculated token, which is the last element of authInfo
        String [] items = authInfo.split("\t");
        if (items.length-1 != paramsForAuth.size()) {
            return AuthResult.fail(String.format("auth param count incorrect! Expected %d, actually %d",
                                    paramsForAuth.size(), items.length-1));
        }

        String clientToken = items[items.length-1];
        StringBuffer str = new StringBuffer(apiSecret);
        StringBuffer paramInfo = new StringBuffer("[");
        for (int i=0; i<items.length-1; i++) {
            str.append("\t").append(items[i]);
            if (i != 0)
                paramInfo.append(", ");
            paramInfo.append(paramsForAuth.get(i)).append(":").append(items[i]);
        }
        paramInfo.append("]");

        log.info("authenticate request " + paramInfo.toString());
        String validToken = MD5Generator.generateTokenString(str.toString());
        if (validToken.equals(clientToken)) {
            log.info("authenticate request " + paramInfo.toString() + " : pass");
            return AuthResult.PASS;
        }
        else {
            log.info("authenticate request " + paramInfo.toString() + " : reject");
            return AuthResult.fail("authentication failed, apiSecret incorrect!");
        }

    }

    public List<String> getParamsForAuth() {
        return paramsForAuth;
    }

    public void setParamsForAuth(List<String> paramsForAuth) {
        this.paramsForAuth = paramsForAuth;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }
}
