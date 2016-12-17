package org.mh.dts.common.http.servlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

public class DelegateServletProxy extends GenericServlet {

  private static final long serialVersionUID = 1L;
  private String targetBean;
  private Servlet proxy;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    proxy.service(req, res);
  }

  public void init() throws ServletException {
    this.targetBean = getServletName();
    getServletBean();
    proxy.init(getServletConfig());
  }

  private void getServletBean() {
    WebApplicationContext wac = WebApplicationContextUtils
        .getRequiredWebApplicationContext(getServletContext());
    this.proxy = (Servlet) wac.getBean(targetBean);
  }

}