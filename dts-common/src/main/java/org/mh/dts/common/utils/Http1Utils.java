package org.mh.dts.common.utils;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/**
 * Created by maohong on 2016/12/17.
 */
public class Http1Utils {




    public static void main(String [] args) throws InterruptedException {

        long t = System.currentTimeMillis();

        while (true) {
            String url = "http://app1.vote.cntv.cn/viewVoteAction.do";

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("voteId", "15423");
            builder.addTextBody("type", "json");

            HttpHost proxy = new HttpHost("120.221.32.220", 80);
            RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();

            HttpClientUtils.post(url, builder.build(), requestConfig);

            Thread.sleep(15000L);

        }
    }

}
