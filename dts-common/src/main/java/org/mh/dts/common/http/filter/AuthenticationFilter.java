package org.mh.dts.common.http.filter;

import lombok.extern.slf4j.Slf4j;
import org.mh.dts.common.http.auth.ApiAuthenticator;
import org.mh.dts.common.http.auth.AuthResult;
import org.mh.dts.common.utils.HttpUtils;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 */
@Slf4j
public class AuthenticationFilter implements Filter {

    private WebApplicationContext applicationContext;
    private String apiAuthenticatorBeanName;
    private ApiAuthenticator apiAuthenticator;

    public void init(FilterConfig filterConfig) throws ServletException {
        applicationContext = (WebApplicationContext) filterConfig.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        apiAuthenticator = (ApiAuthenticator) applicationContext.getBean(apiAuthenticatorBeanName);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        AuthResult authResult = apiAuthenticator.authenticate(request);
        if (!authResult.isAuthPass()) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            HttpUtils.sendResponseData((HttpServletRequest) request, httpResponse, authResult.getMsg());
            httpResponse.flushBuffer();
            return;
        }

        filterChain.doFilter(request, response);
    }

    public void destroy() {
        
    }

    public String getApiAuthenticatorBeanName() {
        return apiAuthenticatorBeanName;
    }

    public void setApiAuthenticatorBeanName(String apiAuthenticatorBeanName) {
        this.apiAuthenticatorBeanName = apiAuthenticatorBeanName;
    }

}
