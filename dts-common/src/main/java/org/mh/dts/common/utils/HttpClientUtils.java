package org.mh.dts.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 2016/12/5 17:42
 */
public final class HttpClientUtils {

    private static final Logger logger = Logger.getLogger(HttpClientUtils.class.getName());
    private static final RequestConfig DEFAULT_REQUEST_CONFIG = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(5000).build();
    private static final List<Integer> INVALID_HTTP_CODES = Arrays.asList(new Integer[]{Integer.valueOf(403), Integer.valueOf(404), Integer.valueOf(499), Integer.valueOf(500), Integer.valueOf(502)});
    private static final PoolingHttpClientConnectionManager POOLING_CONNECTION_MANAGER = new PoolingHttpClientConnectionManager();
    private static CloseableHttpClient httpclient = null;

    public HttpClientUtils() {
    }

    public static boolean isValidHttpCode(int code) {
        return !INVALID_HTTP_CODES.contains(Integer.valueOf(code));
    }

    public static RequestConfig createRequestConfig(int socketTimeout, int connectionTimtout) {
        return RequestConfig.custom().setConnectTimeout(connectionTimtout).setSocketTimeout(socketTimeout).build();
    }

    public static <K, V> String post(String url, HttpEntity entity) {
        return post(url, entity, DEFAULT_REQUEST_CONFIG);
    }

    public static <K, V> String post(String url, HttpEntity entity, RequestConfig requestConfig) {
        HttpPost request = new HttpPost(url);
        ((HttpRequestBase)HttpRequestBase.class.cast(request)).setConfig(requestConfig != null?requestConfig:DEFAULT_REQUEST_CONFIG);
        request.setEntity(entity);
        request.addHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36");
        request.addHeader("X-FORWARDED-FOR", "111.222.333.4");
        request.addHeader("CLIENT-IP", "111.222.333.4");
        return execute(request);
    }

    private static String execute(HttpUriRequest request) {
        String response = null;
        CloseableHttpResponse closeableHttpResponse = null;

        try {
            BasicHttpContext e = new BasicHttpContext();
            e.setAttribute("HttpUriRequest", request);
            closeableHttpResponse = httpclient.execute(request, e);
            StatusLine statusLine = closeableHttpResponse.getStatusLine();
            int code = statusLine.getStatusCode();
            if(isValidHttpCode(code)) {
                HttpEntity entity = closeableHttpResponse.getEntity();
                if(entity != null) {
                    String encoding = entity.getContentEncoding() != null ? entity.getContentEncoding().getValue() : "GBK";
                    response = EntityUtils.toString(entity, encoding);
                }
            } else {
                System.out.println(code);
                logger.error(String.format("StatusCodeError:[rawPath:%s,method:%s,rawQuery:%s,statusCode:%s,statusText:%s]", new Object[]{request.getURI().getRawPath(), request.getMethod(), request.getURI().getRawQuery(), Integer.valueOf(code), statusLine.getReasonPhrase()}));
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(String.format("IOException:[rawPath:%s,method:%s,rawQuery:%s,errorMessage:%s]", new Object[]{request.getURI().getRawPath(), request.getMethod(), request.getURI().getRawQuery(), e.getMessage()}), e);
        } finally {
            if(closeableHttpResponse != null) {
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }

        }

        return response;
    }

    static {
        HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if(executionCount > 3) {
                    return false;
                } else {
                    Object clientContext = context.getAttribute("HttpUriRequest");
                    if(clientContext != null) {
                        HttpUriRequest request = (HttpUriRequest)clientContext;
                        HttpClientUtils.logger.error("retry count " + executionCount + " with url : " + request.getURI().toString());
                    }

                    if(exception instanceof InterruptedIOException) {
                        return false;
                    } else if(exception instanceof UnknownHostException) {
                        return false;
                    } else if(exception instanceof ConnectTimeoutException) {
                        return false;
                    } else if(exception instanceof SSLException) {
                        return false;
                    } else {
                        HttpClientContext httpClientContext = HttpClientContext.adapt(context);
                        HttpRequest request = httpClientContext.getRequest();
                        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                        return idempotent;
                    }
                }
            }
        };
        POOLING_CONNECTION_MANAGER.setDefaultMaxPerRoute(100);
        POOLING_CONNECTION_MANAGER.setMaxTotal(500);
        httpclient = HttpClientBuilder.create().setConnectionManager(POOLING_CONNECTION_MANAGER).setDefaultRequestConfig(DEFAULT_REQUEST_CONFIG).setRetryHandler(retryHandler).useSystemProperties().build();
        (new HttpClientUtils.IdleConnectionMonitorThread(POOLING_CONNECTION_MANAGER)).start();

    }

    private static class IdleConnectionMonitorThread extends Thread {
        private final HttpClientConnectionManager httpClientConnectionManager;
        private volatile boolean shutdown;

        public IdleConnectionMonitorThread(HttpClientConnectionManager httpClientConnectionManager) {
            this.setName("idle-connection-monitor");
            this.setDaemon(true);
            this.httpClientConnectionManager = httpClientConnectionManager;
        }

        public void run() {
            while(true) {
                try {
                    if(!this.shutdown) {
                        synchronized(this) {
                            this.wait(5000L);
                            this.httpClientConnectionManager.closeExpiredConnections();
                            this.httpClientConnectionManager.closeIdleConnections(30L, TimeUnit.SECONDS);
                            continue;
                        }
                    }
                } catch (InterruptedException e) {
                    HttpClientUtils.logger.error(e.getMessage());
                    e.printStackTrace();
                }

                return;
            }
        }
    }
}
