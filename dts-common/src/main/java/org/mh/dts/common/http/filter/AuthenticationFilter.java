package org.mh.dts.common.http.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.mh.dts.common.utils.HttpUtils;
import org.mh.dts.common.utils.MD5Generator;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 
 */
@Slf4j
public class AuthenticationFilter implements Filter {

    private WebApplicationContext applicationContext;
    private String paramsForAuth;
    private String paramSecretBeanName;
    private final String paramsToken = "token";
    private String [] authParams;
    private String apiSecret;

    public void init(FilterConfig filterConfig) throws ServletException {

        applicationContext = (WebApplicationContext) filterConfig.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        apiSecret = (String) applicationContext.getBean(paramSecretBeanName);
        if (StringUtils.isBlank(paramsForAuth))
            throw new ServletException("authentication param is not defined!");
        authParams = paramsForAuth.split(",");
        log.info(this.getClass().getSimpleName() + " authentication params: " + paramsForAuth);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        if (!passAuthentication(request)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            HttpUtils.sendResponseData((HttpServletRequest) request, httpResponse, "authentication failed");
            httpResponse.flushBuffer();
            return;
        }

        filterChain.doFilter(request, response);
    }



    private boolean passAuthentication(ServletRequest request) {

        Map<String, Object> paramMap = request.getParameterMap();
        if (!paramMap.containsKey(paramsToken)) {
            return false;
        }
        String token = (String) paramMap.get(paramsToken);
        StringBuffer str = new StringBuffer(apiSecret + "_");
        for (String param : authParams)
        {
            if (paramMap.containsKey(param)) {
                return false;
            }
            str.append("_");
            str.append((String) paramMap.get(param));
        }
        String validToken = MD5Generator.generateTokenString(str.toString());
        return validToken.equals(token);
    }


    public void destroy() {
        
    }

    public String getParamsForAuth() {
        return paramsForAuth;
    }

    public void setParamsForAuth(String paramsForAuth) {
        this.paramsForAuth = paramsForAuth;
    }

    public String getParamSecretBeanName() {
        return paramSecretBeanName;
    }

    public void setParamSecretBeanName(String paramSecretBeanName) {
        this.paramSecretBeanName = paramSecretBeanName;
    }
}
