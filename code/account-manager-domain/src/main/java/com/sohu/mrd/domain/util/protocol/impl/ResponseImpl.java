package com.sohu.mrd.domain.util.protocol.impl;

import com.sohu.mrd.domain.util.httpclient.HttpException;
import com.sohu.mrd.domain.util.protocol.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;


/**
 * User: doushihui
 * Date: 2011-4-27
 * Time: 10:22:58.
 */
public class ResponseImpl implements Response {
    private final static Log log = LogFactory.getLog(ResponseImpl.class);

    HttpUriRequest method;
    HttpClient client;
    HttpResponse response;

    public ResponseImpl(HttpUriRequest method, HttpClient client) throws HttpException, IOException {
        this.client = client;
        this.method = method;
        long s1 = System.currentTimeMillis();
        try {
            this.response = this.client.execute(this.method);
            log.info("httpClient request used：" + (System.currentTimeMillis() - s1) + "ms");
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                log.info(("Method failed: " + response.getStatusLine()));
            }

        } catch (ConnectTimeoutException cte) {
            log.info("httpClient request exception! used：" + (System.currentTimeMillis() - s1) + "ms");
            throw new HttpException(cte);
        }
    }

    public InputStream getResponseBodyAsStream() {
        InputStream is = null;
        try {
            is = response.getEntity().getContent();
        } catch (IOException e) {
            log.error("ERROR:接收应答出现错误", e);
        }
        return is;
    }

    /**
     * @return string
     */
    public String getResponseBodyAsString() {
        return getResponseBodyAsString("GBK");
    }

    public String getResponseBodyAsString(final String charset) {
        org.apache.http.HttpEntity httpEntity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(httpEntity, charset);
        } catch (IOException e) {
            log.error("ERROR:接收应答出现错误", e);
        }
        return result;
    }

    public int getStatusCode() {
        return response.getStatusLine().getStatusCode();
    }

    public String getRequestUrl() {
        return method.getURI().toString();
    }


    public void close() {
        if (response == null) {
            return;
        }

        final org.apache.http.HttpEntity entity = response.getEntity();
        if (entity == null) {
            return;
        }

        try {
            EntityUtils.consume(entity);
        }
        catch (IOException e) {
            log.warn("warn:关闭连接异常", e);
        }
    }
}
