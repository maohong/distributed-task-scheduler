package org.mh.dts.common.http.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 *
 */
public class CharacterEncodingFilter implements Filter {
    String requestEncoding;
    String responseEncoding;
    boolean isSetContentType;
    

    public void init(FilterConfig filterConfig) throws ServletException {
        this.setRequestEncoding(filterConfig.getInitParameter("requestEncoding"));
        this.setResponseEncoding(filterConfig.getInitParameter("responseEncoding"));
        this.isSetContentType=(filterConfig.getInitParameter("isSetContentType").equals("true"));
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(this.requestEncoding);
        response.setCharacterEncoding(this.responseEncoding);
        if(this.isSetContentType) {
            response.setContentType("text/html; charset="+responseEncoding);
        }
        filterChain.doFilter(request, response);
    }

 
    public void destroy() {
        
    }

    public String getRequestEncoding() {
        return requestEncoding;
    }

    public void setRequestEncoding(String requestEncoding) {
        this.requestEncoding = requestEncoding;
    }

    public String getResponseEncoding() {
        return responseEncoding;
    }

    public void setResponseEncoding(String responseEncoding) {
        this.responseEncoding = responseEncoding;
    }

    public boolean getIsSetContentType() {
        return isSetContentType;
    }

    public void setIsSetContentType(boolean setContentType) {
        isSetContentType = setContentType;
    }
}
