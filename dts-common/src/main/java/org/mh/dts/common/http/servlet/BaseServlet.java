package org.mh.dts.common.http.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Slf4j
public abstract class BaseServlet extends HttpServlet {


    private static final long serialVersionUID = 4567980217807141580L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            process(request, response);
        } catch (Exception e) {
            log.error("处理post请求失败" + request, e);
            throw new ServletException("内部异常", e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            process(request, response);
        } catch (Exception e) {
            log.error("处理get请求失败" + request, e);
            throw new ServletException("内部异常", e);

        }
    }

    protected abstract SchedulerServerResponse processRequest(Map<String, Object> param,
                                                              HttpSession session);

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        SchedulerServerResponse result = processRequest(request.getParameterMap(),
                request.getSession());
        sendData(request, response, result == null ? "" : result.toJson());

    }

    private void sendData(HttpServletRequest request,
                          HttpServletResponse response, String data) throws IOException {
        OutputStream os = null;
        try {
            response.setContentType("text/javascript;charset=UTF-8");
            os = response.getOutputStream();
            os.write(data.getBytes("UTF-8"));
            os.flush();
        } finally {
            if (null != os) {
                os.close();
            }

        }
    }

    /**
     * get the first parameter by parameter name
     *
     * @param param is a map whose key represent parameter name and value represent parameter values
     * @param name  is parameter name
     * @return the first parameter value
     */
    protected String getParam(Map<String, Object> param, String name) {
        if (null == param.get(name)) {
            return null;
        }

        return ((String[]) param.get(name))[0];
    }

    /**
     * get the parameter array by parameter name
     *
     * @param param is a map whose key represent parameter name and value represent parameter values
     * @param name  is parameter name
     * @return the parameter array
     */
    protected String[] getParams(Map<String, Object> param, String name) {
        if (null == param.get(name)) {
            return null;
        }

        return (String[]) param.get(name);
    }
}
