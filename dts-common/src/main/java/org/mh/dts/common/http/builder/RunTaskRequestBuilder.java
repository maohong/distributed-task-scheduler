package org.mh.dts.common.http.builder;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.mh.dts.common.http.bean.ContentTypes;
import org.mh.dts.common.http.bean.RequestMethod;
import org.mh.dts.common.http.bean.TaskRequestParamName;
import org.mh.dts.common.utils.MD5Generator;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 2016/12/5 16:59
 */
public class RunTaskRequestBuilder implements TaskRequestBuilder {

    private MultipartEntityBuilder builder = MultipartEntityBuilder.create();

    private RunTaskRequestBuilder() {
        this.builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        this.builder.setCharset(Charset.forName("GBK"));
        this.addTextBody(TaskRequestParamName.METHOD, RequestMethod.RUN_TASK.getMethod());
    }

    public static RunTaskRequestBuilder create() {
        return new RunTaskRequestBuilder();
    }

    private String taskName;
    private String clientName;
    private String operator;
    private String token;

    @Override
    public HttpEntity build() {

        this.builder.addTextBody(TaskRequestParamName.SECRET.getName(), MD5Generator.generateTokenString(token + taskName + clientName + operator));
        return this.builder.build();
    }

    private MultipartEntityBuilder addTextBody(TaskRequestParamName paramName, String text) {
        return this.builder.addTextBody(paramName.getName(), text, ContentTypes.GBK_TEXT_PLAIN);
    }

    public RunTaskRequestBuilder setToken(String token) {
        this.token = token;
        return this;
    }

    public RunTaskRequestBuilder setTaskName(String taskName) {
        this.addTextBody(TaskRequestParamName.TASK_NAME, taskName.trim());
        this.taskName = taskName;
        return this;
    }

    public RunTaskRequestBuilder setClientName(String clientName) {
        this.addTextBody(TaskRequestParamName.CLIENT_NAME, clientName.trim());
        this.clientName = clientName;
        return this;
    }

    public RunTaskRequestBuilder setOperator(String operator) {
        this.addTextBody(TaskRequestParamName.OPERATOR, operator.trim());
        this.operator = operator;
        return this;
    }

    public RunTaskRequestBuilder execWithDepend(boolean depend) {
        this.addTextBody(TaskRequestParamName.DEPEND, String.valueOf(depend));
        return this;
    }

    public RunTaskRequestBuilder execIntervalSeconds(long interval) {
        this.addTextBody(TaskRequestParamName.INTERVAL, String.valueOf(interval));
        return this;
    }

    public RunTaskRequestBuilder setTaskData(Map<String,String> taskDataMap) {

        if (taskDataMap == null || taskDataMap.isEmpty())
            return this;
        List<String> dataList = new ArrayList<String>(taskDataMap.size());
        for (String key : taskDataMap.keySet())
        {
            dataList.add(key.trim() + ":" + taskDataMap.get(key).trim());
        }
        this.addTextBody(TaskRequestParamName.DATA, StringUtils.join(dataList, "###"));
        return this;
    }
}
