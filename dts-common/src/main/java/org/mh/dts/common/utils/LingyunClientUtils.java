package org.mh.dts.common.utils;

import org.apache.http.client.config.RequestConfig;
import org.mh.dts.common.http.builder.RunTaskRequestBuilder;

/**
 * @Author maohong@sogou-inc.com
 * 2016/12/5 16:53
 */
public class LingyunClientUtils {

    public static String runTask(String biztaskUrl, RunTaskRequestBuilder builder){

        return runTask(biztaskUrl, builder, (RequestConfig)null);
    }

    public static String runTask(String url, RunTaskRequestBuilder builder, RequestConfig requestConfig) {
        if(url != null && url.length() != 0) {
            if(builder == null) {
                throw new IllegalArgumentException("builder must not be null");
            } else {
                String response = HttpClientUtils.post(url, builder.build(), requestConfig);
                return response;
            }
        } else {
            throw new IllegalArgumentException("url must not be null");
        }
    }

}
