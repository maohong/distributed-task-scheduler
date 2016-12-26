package org.mh.dts.common.utils;

import com.google.common.base.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.mh.dts.common.constant.HttpRequestParamName;
import org.mh.dts.common.http.bean.ContentTypes;
import org.mh.dts.common.http.builder.TaskRequestBuilder;

/**
 * @Author maohong@sogou-inc.com
 * 2016/12/5 16:59
 */
public class HttpRequestBuilder implements TaskRequestBuilder {

    private MultipartEntityBuilder builder = MultipartEntityBuilder.create();

    private HttpRequestBuilder() {
        this.builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        this.builder.setCharset(Charsets.UTF_8);
    }

    public static HttpRequestBuilder create() {
        return new HttpRequestBuilder();
    }

    @Override
    public HttpEntity build() {
        return this.builder.build();
    }

    private MultipartEntityBuilder addTextBody(String key, String text) {
        return this.builder.addTextBody(key, text, ContentTypes.UTF8_TEXT_PLAIN);
    }

    public HttpRequestBuilder setApiMethod(String apiMethod) {

        this.addTextBody(HttpRequestParamName.REQUEST_PARAM_NAME_API_METHOD.getParam(), apiMethod);
        return this;
    }

    public HttpRequestBuilder addApiParameter(int index, String parameter) {

        this.addTextBody(HttpRequestParamName.REQUEST_PARAM_NAME_API_PARAM_PREFIX.getParam() + index , parameter);
        return this;
    }
}
