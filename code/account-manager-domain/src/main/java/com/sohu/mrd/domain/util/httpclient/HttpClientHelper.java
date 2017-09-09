package com.sohu.mrd.domain.util.httpclient;

import com.sohu.mrd.domain.util.protocol.Request;
import com.sohu.mrd.domain.util.protocol.Response;
import com.sohu.mrd.domain.util.protocol.impl.RequestImpl;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;

/**
 * User: Created by chenbaoan@360buy.com
 * Date: 2011-4-27
 * Time: 10:22:58.
 */
public class HttpClientHelper {
    private final static Logger log = Logger.getLogger(HttpClientHelper.class);
    private HttpClientManager httpClientManager;

    /**
     * get请求
     * @param url
     * @return
     * @throws HttpException
     * @throws java.io.IOException
     */
    public String sendMessage(final String url) throws HttpException, IOException {
        return sendGetOrPostMessage(url, null, null);
    }
    /**
     * post请求
     * @param url
     * @return
     * @throws HttpException
     * @throws java.io.IOException
     */
    public String sendPostMessage(final String url, final Map<String, String> nameValuePairs) throws HttpException, IOException {
        return sendGetOrPostMessage(url, nameValuePairs, null);
    }
    private String sendGetOrPostMessage(final String url, final Map<String, String> nameValuePairs, String code) throws HttpException, IOException {
        String message = null;
        int statusCode = 0;
        Response response = null;
        long s1 = System.currentTimeMillis();
        try {
            if (nameValuePairs == null) {//get传值
                Request request = new RequestImpl(url, HttpConstants.GET_REQUEST);
                httpClientManager.setup(request);
                response = httpClientManager.doRequest(request, null, null);
            }else {//post传值
                Request request = new RequestImpl(url, HttpConstants.POST_REQUEST);
                httpClientManager.setup(request);
                response = httpClientManager.doRequest(request, nameValuePairs,code);
            }
            statusCode = response.getStatusCode();
            if (response.getStatusCode() == HttpStatus.SC_OK) {
                message = response.getResponseBodyAsString();
            }
            else {
                log.error("url:" + url + ",statusCode:" + statusCode + ",message:" + response.getResponseBodyAsString());
            }
        }
        catch (Exception e) {
            log.error("HttpClientHelper sendMessage error," + "url:" + url, e);
        }
        finally {
            if (response != null) {
                response.close();
            }
        }
        log.info("[HttpClientHelper.sendMessage] used：" + (System.currentTimeMillis() - s1) + "ms" + ",url:" + url);
        return message;
    }
    /**
     * post请求
     * 多一个编码参数
     * @param url 地址 nameValuePairs 参数    code编码
     * @return
     * @throws HttpException
     * @throws java.io.IOException
     */
    public String sendPostMessage(final String url, final Map<String, String> nameValuePairs,String code) throws HttpException, IOException {
        return sendGetOrPostMessage(url, nameValuePairs, code);
    }
    public HttpClientManager getHttpClientManager() {
        return httpClientManager;
    }
    public void setHttpClientManager(HttpClientManager httpClientManager) {
        this.httpClientManager = httpClientManager;
    }
}
