package org.mh.dts.scheduler.web;

import org.mh.dts.common.constant.DtsConstant;
import org.mh.dts.common.http.servlet.BaseServlet;
import org.mh.dts.common.http.servlet.DtsResponse;
import org.mh.dts.scheduler.handler.TaskScheduleRequestHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by maohong on 2016/12/17.
 */
public class TaskScheduleServlet extends BaseServlet {

    private static final long serialVersionUID = 6466459142819371789L;

    private String requestHandlerBeanName;
    private TaskScheduleRequestHandler taskScheduleRequestHandler;

    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext wac = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());
        taskScheduleRequestHandler = (TaskScheduleRequestHandler)wac.getBean(requestHandlerBeanName);
    }

    @Override
    protected DtsResponse processRequest(Map<String, Object> param, HttpSession session) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String requestMethod = (String)param.get(DtsConstant.REQUEST_PARAM_NAME_API_METHOD);
        Method method = taskScheduleRequestHandler.getClass().getDeclaredMethod(requestMethod, Map.class);
        DtsResponse response = (DtsResponse) method.invoke(taskScheduleRequestHandler, param);
        return response;
    }

    public String getRequestHandlerBeanName() {
        return requestHandlerBeanName;
    }

    public void setRequestHandlerBeanName(String requestHandlerBeanName) {
        this.requestHandlerBeanName = requestHandlerBeanName;
    }
}
