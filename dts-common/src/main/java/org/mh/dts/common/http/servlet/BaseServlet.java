package org.mh.dts.common.http.servlet;

import lombok.extern.slf4j.Slf4j;
import org.mh.dts.common.constant.DtsConstant;
import org.mh.dts.common.utils.HttpUtils;
import org.mh.dts.common.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

    protected abstract DtsResponse processRequest(Map<String, Object> param,
                                                              HttpSession session) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        if (!validateRequest(request, response)) {
            return;
        }
        DtsResponse result = processRequest(request.getParameterMap(),
                request.getSession());
        HttpUtils.sendResponseData(request, response, JsonUtils.toJsonStringFromObject(result));

    }

    private boolean validateRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestMethod = request.getParameter(DtsConstant.REQUEST_PARAM_NAME_API_METHOD);
        boolean isOk = requestMethod!=null && !requestMethod.isEmpty();
        if (isOk) {
            HttpUtils.sendResponseData(request, response,
                    String.format("request parameter %s needed!", DtsConstant.REQUEST_PARAM_NAME_API_METHOD));
        }
        return isOk;
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
