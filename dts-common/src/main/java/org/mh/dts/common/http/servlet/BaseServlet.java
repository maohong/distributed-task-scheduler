package org.mh.dts.common.http.servlet;

import lombok.extern.slf4j.Slf4j;
import org.mh.dts.common.constant.HttpRequestParamName;
import org.mh.dts.common.utils.HttpUtils;
import org.mh.dts.common.utils.JsonUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * base servlet template
 * @param <T> refers to specific API class
 */
@Slf4j
public abstract class BaseServlet<T> extends HttpServlet {


    private static final long serialVersionUID = 4567980217807141580L;

    protected String apiHandlerBeanName;

    // NOTE: key is method name, so currently we do not support method overload
    protected Map<String, Method> availableMethods = new ConcurrentHashMap<>();
    protected T apiHandler;

    @Override
    final public void init() throws ServletException {
        super.init();
        WebApplicationContext wac = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());
        apiHandler = (T)wac.getBean(apiHandlerBeanName);
        for (Method method : apiHandler.getClass().getDeclaredMethods()) {
            availableMethods.put(method.getName(), method);
        }
    }

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

    protected DtsResponse processRequest(Map<String, Object> param, HttpSession session) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String requestMethod = getParam(param, HttpRequestParamName.REQUEST_PARAM_NAME_API_METHOD.getParam());
        if (!availableMethods.containsKey(requestMethod)) {
            throw new NoSuchMethodException("invalid request for API method : " + requestMethod);
        }

        DtsResponse response;
        Method method = availableMethods.get(requestMethod);
        Class [] paramTypes = method.getParameterTypes();
        if (paramTypes == null || paramTypes.length == 0) {
            response = (DtsResponse) method.invoke(apiHandler);
        }
        else {
            List<Object> apiParams = new ArrayList<>(paramTypes.length);
            for (int i=0; i<paramTypes.length; i++) {
                String parameter = getParam(param,
                        HttpRequestParamName.REQUEST_PARAM_NAME_API_PARAMS.getParam() + i);
                Object apiParam = JsonUtils.readObject(parameter, paramTypes[i]);
                apiParams.add(apiParam);
            }
            response = (DtsResponse) method.invoke(apiHandler, apiParams);
        }
        return response;
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        if (!validateRequest(request, response)) {
            return;
        }
        DtsResponse result = processRequest(request.getParameterMap(), request.getSession());
        HttpUtils.sendResponseData(request, response, JsonUtils.toJsonString(result));

    }

    private boolean validateRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestMethod = request.getParameter(HttpRequestParamName.REQUEST_PARAM_NAME_API_METHOD.getParam());
        boolean isOk = requestMethod!=null && !requestMethod.isEmpty();
        if (isOk) {
            HttpUtils.sendResponseData(request, response,
                    String.format("request parameter %s needed!",
                            HttpRequestParamName.REQUEST_PARAM_NAME_API_METHOD.getParam()));
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

}
