package org.mh.dts.scheduler.api.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.mh.dts.common.constant.DtsConstant;
import org.mh.dts.common.utils.HttpClientUtils;
import org.mh.dts.common.utils.HttpRequestBuilder;
import org.mh.dts.common.utils.JsonUtils;
import org.mh.dts.common.utils.MD5Generator;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * Created by maohong on 2016/12/24.
 */
@Slf4j
public class SchdeulerClientForWeb implements InitializingBean {

    private List<String> schedulerUrls;
    private String clientName;
    private String apiSecret;

    @Override
    public void afterPropertiesSet() throws Exception {

        if (schedulerUrls == null || schedulerUrls.isEmpty()) {
            throw new IllegalArgumentException("scheduler server url list is empty!");
        }
        if (clientName == null || clientName.isEmpty()) {
            throw new IllegalArgumentException("clientName is empty!");
        }
        if (apiSecret == null || apiSecret.isEmpty()) {
            throw new IllegalArgumentException("apiSecret is empty!");
        }
    }

    class APIProxy implements InvocationHandler {

        private Class apiClazz;
        private List<String> apiPaths;
        private Random rand = new Random();

        public Object bind(List<String> apiPaths, Class apiClazz) {
            this.apiPaths = apiPaths;
            this.apiClazz = apiClazz;
            return Proxy.newProxyInstance(apiClazz.getClassLoader(),
                    apiClazz.getInterfaces(), this);
        }

        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {

            log.info("Calling API[" + apiClazz.getName() + "], Method["
                    + method.getName() + "]");

            int index = rand.nextInt(apiPaths.size());
            String selectedPath = apiPaths.get(index);
            String token = calculateToken(selectedPath);

            try {
                Class rtnClazz = method.getReturnType();
                HttpRequestBuilder requestBuilder = HttpRequestBuilder.create();
                requestBuilder.setApiMethod(method.getName());
                if (args != null) {
                    for (int i = 0; i < args.length; i++) {
                        // add args
                        requestBuilder.addApiParameter(i, JsonUtils.toJsonString(args[i]));
                    }
                }
                String response = HttpClientUtils.post(apiPaths.get(index), requestBuilder.build(), null, token);
                Object obj = JsonUtils.readObject(response, rtnClazz);
                return obj;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw e;
            } finally {
                log.info("End calling API[" + apiClazz.getName() + "], Method["
                        + method.getName() + "]");
            }
        }
    }

    private String calculateToken(String apiPath) {

        String ts = System.currentTimeMillis() + "";
        List<String> items = new ArrayList<>();
        items.add(DtsConstant.ipAddress);
        items.add(clientName);
        items.add(ts);
        items.add(apiPath);
        String secret = MD5Generator.generateTokenString(apiSecret + "\t" + StringUtils.join(items, "\t"));
        return StringUtils.join(items, "\t") + "\t" + secret;
    }

    private class ApiClient {

        private HashMap<String, Object> contextPath2Proxy = new HashMap<String, Object>();

        private ApiClient(){}

        private Object getService(String contextPath, Class clazz) {

            if (contextPath2Proxy.get(contextPath) == null) {
                Object proxy = createProxy(contextPath, clazz);
                contextPath2Proxy.put(contextPath, proxy);
            }

            return contextPath2Proxy.get(contextPath);
        }

        private Object createProxy(String contextPath, Class clazz) {
            List<String> apiPaths = new ArrayList<>();
            for (String url : schedulerUrls) {
                apiPaths.add(url + contextPath);
            }
            return new APIProxy().bind(apiPaths, clazz);
        }
    }

    private final ApiClient apiClient = new ApiClient();

    public QuartzJobManageApi getQuartzJobManageApi() {
        return (QuartzJobManageApi) apiClient.getService("/quartzJobManage.do", QuartzJobManageApi.class);
    }

    public QuartzTriggerManageApi getQuartzTriggerManageApi() {
        return (QuartzTriggerManageApi) apiClient.getService("/quartzTriggerManage.do", QuartzTriggerManageApi.class);
    }

    public QuartzSchedulerManageApi getQuartzSchedulerManageApi() {
        return (QuartzSchedulerManageApi) apiClient.getService("/quartzSchedulerManage.do", QuartzSchedulerManageApi.class);
    }

    public TaskScheduleApi getTaskScheduleApi() {
        return (TaskScheduleApi) apiClient.getService("/taskSchedule.do", TaskScheduleApi.class);
    }

    public static void main(String [] args) {

        SchdeulerClientForWeb client = new SchdeulerClientForWeb();
        List<String> urls = Arrays.asList(new String[]{"http://localhost:9090"});
        client.schedulerUrls = urls;
        client.apiSecret = "dev";
        client.clientName = "test";

        client.getTaskScheduleApi().test();

    }
}
