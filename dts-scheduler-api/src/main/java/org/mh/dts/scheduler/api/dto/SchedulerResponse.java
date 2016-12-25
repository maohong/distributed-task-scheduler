package org.mh.dts.scheduler.api.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.mh.dts.common.http.servlet.DtsResponse;
import org.mh.dts.common.utils.JsonUtils;
import org.mh.dts.scheduler.api.web.QuartzJobManageApi;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maohong on 2016/12/23.
 */
public class SchedulerResponse<T> extends DtsResponse {

    private static final long serialVersionUID = -7896609569371317899L;

    private T data;

    public SchedulerResponse(){}

    public SchedulerResponse(boolean isOk, String msg) {
        this.isOk = isOk;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SchedulerResponse{" +
                "isOk=" + isOk +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static void main(String [] args) throws NoSuchMethodException {


        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        SchedulerResponse<Map<Long,Boolean>> response = new SchedulerResponse<>();
        response.setIsOk(true);
        response.setMsg("hahahaha");

        Map<Long,Boolean> map = new HashMap<Long, Boolean>();
        map.put(1L,true);
        map.put(2L, false);
        response.setData(map);

//        String str = JsonUtils.toJsonStringFromObject(response);
//        System.out.println(str);

        String str1 = JsonUtils.toJsonString(response);
        System.out.println(str1);

        SchedulerResponse<Map<Long,Boolean>> res = JsonUtils.readObject(str1, new TypeToken<SchedulerResponse<Map<Long,Boolean>>>(){});
        System.out.println(res);

        Method method = QuartzJobManageApi.class.getDeclaredMethod("getTaskIsScheduledByTaskIds", List.class);
        System.out.println(method.getName() + " -> " + method.getReturnType());
        SchedulerResponse res2 = (SchedulerResponse) JsonUtils.readObject(str1, method.getReturnType());
        System.out.println(res2);


//        gson.fromJson(str1, SchedulerResponse<Map<Long,Boolean>>);
//        SchedulerResponse<Map<Long,Boolean>> res =
//                (SchedulerResponse<Map<Long, Boolean>>) JsonUtils.readObject(str, response.getClass());
//        System.out.println(res);

//        System.out.println(JsonUtils.toJsonString(res));

        SchedulerResponse response1 = new SchedulerResponse();
        response1.setIsOk(false);
        System.out.println(response1);

//        new TypeToken<>(){};

        System.out.println();

        SchedulerResponse<List<QuartzJobDetail>> response2 = new SchedulerResponse<>();
        response2.setIsOk(true);
        response2.setMsg("i'm ok");
        List<QuartzJobDetail> list = new ArrayList<>();
        QuartzJobDetail job = new QuartzJobDetail();
        job.setName("jobname");
        job.setGroup("jobgroup");
        job.setDescription("job desc");
        list.add(job);
        list.add(job);
//        list.add(new JobDetail("name1", "group1", TaskExecuteResultDto.class));
//        list.add(new JobDetail("name2", "group2", TaskExecuteResultDto.class));
        response2.setData(list);

        str1 = JsonUtils.toJsonString(response2);
        System.out.println(str1);

        Method method1 = QuartzJobManageApi.class.getDeclaredMethod("getAllJobDetails", null);
        SchedulerResponse<List<QuartzJobDetail>> tores = (SchedulerResponse<List<QuartzJobDetail>>) JsonUtils.readObject(str1, method1.getReturnType());
        System.out.println(tores);

        System.out.println(gson.toJson(new Long(1234)));

    }
}
