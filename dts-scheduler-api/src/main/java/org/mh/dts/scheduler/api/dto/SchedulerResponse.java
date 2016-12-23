package org.mh.dts.scheduler.api.dto;

import org.mh.dts.common.http.servlet.DtsResponse;
import org.mh.dts.common.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maohong on 2016/12/23.
 */
public class SchedulerResponse<T> extends DtsResponse {

    private static final long serialVersionUID = -7896609569371317899L;

    private boolean isOk;
    private String msg;
    private T data;

    @Override
    public String toString() {
        return "SchedulerResponse{" +
                "isOk=" + isOk +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static void main(String [] args) {

        SchedulerResponse<Map<Long,Boolean>> response = new SchedulerResponse<>();
        response.setOk(true);
        response.setMsg("hahahaha");
        response.setData(new HashMap<Long, Boolean>(){{put(1L,true);put(2L, false);}});

        String str = JsonUtils.toJsonStringFromObject(response);
        System.out.println(str);

// TODO: 2016/12/23 json jiexi fanxing
        SchedulerResponse<Map<Long,Boolean>> res =
                (SchedulerResponse<Map<Long, Boolean>>) JsonUtils.readObject(str, response.getClass());
        System.out.println(res);

        System.out.println(JsonUtils.toJsonStringFromObject(res));
    }
}
