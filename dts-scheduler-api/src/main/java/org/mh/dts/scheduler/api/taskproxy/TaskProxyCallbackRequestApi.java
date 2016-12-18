package org.mh.dts.scheduler.api.taskproxy;

import org.mh.dts.common.http.servlet.DtsResponse;

import java.util.Map;

/**
 * Created by maohong on 2016/12/19.
 */
public interface TaskProxyCallbackRequestApi {

    public DtsResponse taskSuccess(Map<String, Object> params);

    public DtsResponse taskFail(Map<String, Object> params);
}
