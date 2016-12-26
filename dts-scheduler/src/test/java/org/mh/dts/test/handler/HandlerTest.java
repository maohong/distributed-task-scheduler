package org.mh.dts.test.handler;

import org.mh.dts.common.utils.JsonUtils;
import org.mh.dts.scheduler.api.web.TaskScheduleApi;
import org.mh.dts.scheduler.handler.TaskScheduleApiHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by maohong on 2016/12/25.
 */
public class HandlerTest {

    public static void main(String [] args) throws InvocationTargetException, IllegalAccessException {

        Map<String, Method> availableMethods = new ConcurrentHashMap<>();
        TaskScheduleApi apiHandler = new TaskScheduleApiHandler();

        for (Method method : TaskScheduleApi.class.getDeclaredMethods()) {
            availableMethods.put(method.getName(), method);
        }

        Method method = availableMethods.get("scheduleByTaskId");
        Object obj = JsonUtils.readObject("1234", method.getParameterTypes()[0]);
        method.invoke(apiHandler, obj);

        System.out.println();
    }
}
