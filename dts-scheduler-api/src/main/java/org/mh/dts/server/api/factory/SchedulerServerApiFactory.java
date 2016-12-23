package org.mh.dts.server.api.factory;

import lombok.extern.slf4j.Slf4j;
import org.mh.dts.scheduler.api.taskproxy.TaskProxyCallbackApi;
import org.mh.dts.scheduler.api.web.TaskScheduleApi;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by maohong on 2016/12/17.
 */
@Slf4j
public class SchedulerServerApiFactory {

    private static List<Class> apiList = new ArrayList<>();
    public static Map<Class,Set<String>> apiMethodMap = new HashMap<>();
    static {
        apiList.add(TaskScheduleApi.class);
        apiList.add(TaskProxyCallbackApi.class);

        for (Class clazz : apiList) {
            apiMethodMap.put(clazz, new HashSet<String>());
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                apiMethodMap.get(clazz).add(method.getName());
            }
        }
        log.info("inited SchedulerServerApi map: " + apiMethodMap.toString());
    }

    // TODO: 2016/12/19 offer api visitor utils
}
