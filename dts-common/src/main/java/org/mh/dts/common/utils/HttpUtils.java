package org.mh.dts.common.utils;

import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.Collections;
import java.util.List;

/**
 * Created by maohong on 2016/12/17.
 */
public class HttpUtils {



    public static List<String> getProxyList()
    {
        String url = "http://www.kuaidaili.com/free/inha/1/";
        String list = HttpClientUtils.post(url, MultipartEntityBuilder.create().build());
        System.out.println(list);
        return Collections.emptyList();
//        return Arrays.asList(list.split("\r\n"));
    }

    public static void main(String [] args) throws InterruptedException {

        long t = System.currentTimeMillis();
        int i=0;
        while (true) {
            String url = "http://app2.vote.cntv.cn/makeClickAction.do";

            String time = "" + t;
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("voteId", " 15423");
            builder.addTextBody("items_1130347", "191330");
            builder.addTextBody("time", time);

            System.out.println("count " + i++);
//            HttpHost proxy = new HttpHost("120.221.32.220", 80);
//            RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
//            HttpClientUtils.post(url, builder.build(), requestConfig);
            String response = HttpClientUtils.post(url, builder.build(), null);
            System.out.println(response);
            t += (24*3600 + 1);
//            Thread.sleep(15000L);

        }

//        getProxyList();
    }

}
