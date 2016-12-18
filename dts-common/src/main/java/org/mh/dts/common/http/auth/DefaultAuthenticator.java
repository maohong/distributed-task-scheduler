package org.mh.dts.common.http.auth;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.mh.dts.common.constant.DtsConstant;
import org.mh.dts.common.utils.MD5Generator;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by maohong on 2016/12/18.
 */
@Slf4j
public class DefaultAuthenticator implements ApiAuthenticator {

    @Resource
    private List<String> paramNamesForAuth;
    @Resource
    private String apiSecret;

    @Override
    public AuthResult authenticate(ServletRequest request) {

        if (CollectionUtils.isEmpty(paramNamesForAuth))
            return AuthResult.PASS;

        Map<String, Object> paramMap = request.getParameterMap();
        if (!paramMap.containsKey(DtsConstant.REQUEST_PARAM_NAME_TOKEN)) {
            return AuthResult.failForMissParam(DtsConstant.REQUEST_PARAM_NAME_TOKEN);
        }

        String token = (String) paramMap.get(DtsConstant.REQUEST_PARAM_NAME_TOKEN);
        StringBuffer str = new StringBuffer(apiSecret + "_");
        List<String> missParams = new ArrayList<>(paramNamesForAuth.size());
        for (String param : paramNamesForAuth)
        {
            if (!paramMap.containsKey(param)) {
                missParams.add(param);
            }
            else {
                str.append("_");
                str.append((String) paramMap.get(param));
            }
        }
        if (!missParams.isEmpty()) {
            return AuthResult.failForMissParam(missParams.toArray(new String[]{}));
        }

        String validToken = MD5Generator.generateTokenString(str.toString());
        if (validToken.equals(token))
            return AuthResult.PASS;
        else
            return new AuthResult(false, "authentication failed, apiSecret incorrect!");

    }

}
